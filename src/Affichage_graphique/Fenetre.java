package Affichage_graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;

import Application.AABRR;
import Application.App;
import Application.ArbreBinaire;
import Application.Constantes;
import Application.Suppression;
import Affichage_graphique.JNumberField;

/**
 * 
 * @author Etonam et Dan
 * Cette classe consiste à afficher l'application en un jar
 * 
 * @return L'application 
 */

public class Fenetre extends JFrame {

	
	private static final long serialVersionUID = 1L;

	App application = new App();
	private JTable tableau;
	private JTable listeArbres;
	private JPanel container = new JPanel();
	private JTextField champsNomABR = new JTextField();
	private JTextField jtf = new JTextField();
	private JTextField jtf2 = new JTextField();
	private JTextField jtf3 = new JNumberField();
	private JTextField jtf4 = new JNumberField();
	private JTextField jtf5 = new JNumberField();
	private JTextField jtf6 = new JNumberField();
	private JTextField jtf7 = new JNumberField();
	private JTextField champsNombreIntervallesABR = new JNumberField();
	private JLabel label0 = new JLabel("Menu des AABRR :  ");
	private JLabel labelBoutonsAABRR = new JLabel("Fonctions des AABRR :  ");
	private JLabel labelBoutonsABR = new JLabel("Fonctions des ABRR :  ");
	private JLabel label = new JLabel("Nom arbre  : ");
	private JLabel label2 = new JLabel("Nom new fichier : ");
	private JLabel label3 = new JLabel("Nb noeuds arbre : ");
	private JLabel label4 = new JLabel("Valeur max M : ");
	private JLabel label5 = new JLabel("Val à insérer : ");
	private JLabel label6 = new JLabel("Val à supprimer : ");
	private JLabel label8 = new JLabel("Val à rechercher : ");
	private JLabel label7 = new JLabel("Menu des ABRR :  ");
	private JLabel labelNomABR = new JLabel("Nom de l'arbre  : ");

	private File fichier = null;
	Map<String, AABRR> arbresAppli = new HashMap<String, AABRR>();
	Map<String, ArbreBinaire> arbresBinaireAppli = new HashMap<String, ArbreBinaire>();

	
	/**
	 * Représente la fonction main de l'application celle qui permet de tout générer 
	 * @param args
	 * 
	 * @return La visibilité de l'application 
	 */
	public static void main(String[] args) {
		new Fenetre().setVisible(true);
	}

	private class ImporterAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private ImporterAction() {
			super("Importer fichier AABRR");

		}

		public void actionPerformed(ActionEvent e) {

			clear();
			if (jtf.getText().isEmpty()) {

				application.getMessage().addLigne(
								"Avant tout, saisissez le nom de l'arbre dans le champ correspondant svp");

			} else {
				// création de la boîte de dialogue
				JFileChooser dialogue = new JFileChooser();

				// affichage
				dialogue.showOpenDialog(null);

				// récupération du fichier sélectionné
				fichier = dialogue.getSelectedFile();

				if (fichier != null) {
					setTitle("Arbres d'arbres binaires de recherche à rebours (AABRR) : "
							+ fichier.getName());

					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					application.getMessage().addLigne(
							"Chargement du fichier...");

					if (!arbresAppli.containsKey(jtf.getText())) {
						application.getMessageArbre().addLigne(
								jtf.getText() + " (AABRR)");
					}

					AABRR nouv_arbre = App.chargerAABRR(fichier);

					arbresAppli.put(jtf.getText(), nouv_arbre);

					application.getMessage().addLigne(
							"Chargement du fichier terminé avec succès !");

					arbresAppli.put(jtf.getText(), nouv_arbre);

					jtf.setText("");
				} else {
					application.getMessage().addLigne("Le fichier que vous avez choisi est inexistant !");
				}

			}

		}

	}

	/**
	 * 
	 * @author Etonam et Dan
	 * Consiste à afficher l'arbre dans le fichier 
	 * @return l'affichage de l'arbre dans l'application 
	 */
	private class AfficherAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private AfficherAction() {
			super("Afficher");
		}

		public void actionPerformed(ActionEvent e) {

			clear();
			if (jtf.getText().isEmpty()) {

				application
						.getMessage()
						.addLigne(
								"Avant tout, saisissez le nom de l'arbre dans le champ correspondant svp");

			} else {

				if (!arbresAppli.containsKey(jtf.getText())) {
					application.getMessage().addLigne(
							"Le nom de l'arbre que vous avez saisi est n'existe pas. Veuillez en saisir un nouveau !");
				} else {

					application.getMessage().addLigne(
							"|******************| Affichage de l'arbre : "
									+ jtf.getText() + "|******************|");
					App.afficherAABRR(arbresAppli.get(jtf.getText()));
					application.getMessage().addLigne(
							"|******************| Affichage terminé !|******************|");
				}

			}
		}
	}
	
	/**
	 * 
	 * @author Etonam et Dan
	 * Cette fonction vérifie si un arbre binaire de recherche à rebours en est bien un 
	 * @return Un message qui dit si l'arbre est AABRR ou pas 
	 */
	private class VerifierAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private VerifierAction() {
			super(Constantes.LibellesBoutons.VERIFIER);
		}

		public void actionPerformed(ActionEvent e) {
			clear();
			if (jtf.getText().isEmpty()) {

				application.getMessage().addLigne(
						"Avant tout, saisissez le nom de l'arbre dans le champ correspondant svp");

			} else {
				String nomArbreSaisi = jtf.getText();
				if (!arbresAppli.containsKey(nomArbreSaisi)) {
					application.getMessage().addLigne(
							"Le nom de l'arbre que vous avez saisi est n'existe pas. Veuillez en saisir un nouveau !");
				} else {
					boolean isValid = App.isAABRR(
							arbresAppli.get(nomArbreSaisi), Integer.MIN_VALUE,
							Integer.MAX_VALUE);
					if (isValid) {
						application.getMessage().addLigne(
								nomArbreSaisi + " est un AABRR valide !");
					} else {
						application
								.getMessage()
								.addLigne(
										nomArbreSaisi
												+ " ne répond pas aux spécifications d'un AABRR désolé !");
					}
				}

			}
		}
	}

	// Efface toutes les lignes de la fenêtre
	/**
	 * @author Etonam et Dan 
	 * Cette fonction efface toutes les lignes de la fenetre
	 */
	private void clear() {

		for (int i = tableau.getRowCount() - 1; i >= 0; i--) {
			application.getMessage().clearLigne(i);
		}

	}

	private class ExportAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private ExportAction() {
			super(Constantes.LibellesBoutons.EXPORTER);
		}

		public void actionPerformed(ActionEvent e) {

			clear();
			if (jtf.getText().isEmpty() || jtf2.getText().isEmpty()) {

				application
						.getMessage()
						.addLigne(
								"Avant tout, saisissez le nom de l'arbre et un nom de fichier dans les champs correspondants svp");
			} else {
				if (!arbresAppli.containsKey(jtf.getText())) {
					application.getMessage().addLigne(
							"Nom d'arbre inexistant !");
				} else {
					application.getMessage().addLigne(
							"|******************| Export de l'arbre |******************|");

					App.exporterAABRR(arbresAppli.get(jtf.getText()),
							jtf2.getText());

					application.getMessage().addLigne(
							"|******************| L'export de l'arbre est fini |******************|");
				}

			}
		}
	}

	private class AleatoireAction extends AbstractAction {
		/**
				 * 
				 */
		private static final long serialVersionUID = 1L;

		private AleatoireAction() {
			super(Constantes.LibellesBoutons.ALEATOIRE);

		}

		public void actionPerformed(ActionEvent e) {

			clear();

			boolean donneesOK = !jtf.getText().isEmpty()
					&& !jtf3.getText().isEmpty() && !jtf4.getText().isEmpty();
			if (!donneesOK) {

				application
						.getMessage()
						.addLigne("Avant tout, saisissez le nom de l'arbre, un nombre de noeuds et une valeur maximum dans les champs correspondants svp");

			} else {

				int nbNoeuds = Integer.parseInt(jtf3.getText());
				int nbMax = Integer.parseInt(jtf4.getText());

				if ((nbMax - 1) / nbNoeuds < 2) {
					application
							.getMessage()
							.addLigne(
									"L'intervalle est trop petit pour le nombre de noeuds saisis. Veuillez agrandir l'intervalle ou réduire le nombre de noeuds");
					return;
				}
				application.getMessage().addLigne("Création de l'arbre aléatoire...");

				AABRR nouv_arbre = App.genererAABRRAleatoire(
						Integer.parseInt(jtf3.getText()),
						Integer.parseInt(jtf4.getText()));
				arbresAppli.put(jtf.getText(), nouv_arbre);

				application.getMessage().addLigne(
						"Création terminée avec succès !");

				arbresAppli.put(jtf.getText(), nouv_arbre);
				application.getMessageArbre().addLigne(jtf.getText() + "(AABRR)");

				jtf.setText("");
				jtf3.setText("");
				jtf4.setText("");
			}

		}
	}

	private class InsererAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private InsererAction() {
			super(Constantes.LibellesBoutons.INSERER);

		}

		public void actionPerformed(ActionEvent e) {
			clear();
			boolean donneesOK = !jtf.getText().isEmpty()
					&& !jtf5.getText().isEmpty();
			if (!donneesOK) {

				application
						.getMessage()
						.addLigne(
								"Veuillez saisir un nom d'arbre, et une valeur à insérer SVP ");

			} else {

				String nomArbre = jtf.getText();
				int valeur = Integer.parseInt(jtf5.getText());

				if (!arbresAppli.containsKey(nomArbre)) {
					application.getMessage().addLigne(
							"L'arbre " + nomArbre + " n'existe pas !");
					return;
				}

				application.getMessage().addLigne(
						"Insertion de " + valeur + " dans " + nomArbre + "...");

				AABRR arbre = arbresAppli.get(nomArbre);

				if (App.insererEntierAABRR(arbre, valeur)) {
					application.getMessage().addLigne(
							"Insertion terminée avec succès !");
				} else {
					application.getMessage().addLigne(
							"Erreur lors de l'insertion de " + valeur
									+ " dans " + nomArbre);
					application.getMessage().addLigne(
							"L'intervalle contenant la valeur est inexistant");
				}

				jtf.setText("");
				jtf5.setText("");
			}

		}
	}
	
	/**
	 * 
	 * @author Etonam et Dan
	 *
	 */
	private class RechercherAction extends AbstractAction{
		private static final long serialVersionUID = 1L;
		
		private RechercherAction(){
			super(Constantes.LibellesBoutons.RECHERCHER);
		}
		
			

		public void actionPerformed(ActionEvent e) {
			clear();
			
			boolean donneesOK = !jtf.getText().isEmpty()
			&& !jtf7.getText().isEmpty();
	
			if (!donneesOK) {
				application.getMessage().addLigne(
						"Avant tout, saisissez un nom d'abre et une valeur à rechercher svp");

			} else {

				String nomArbre = jtf.getText();
				int valeur = Integer.parseInt(jtf7.getText());

				if (!arbresAppli.containsKey(nomArbre)) {
					application.getMessage().addLigne(
							"L'arbre " + nomArbre + " n'existe pas !");
					return;
				}

				application.getMessage().addLigne(
						"Recherche de " + valeur + " dans " + nomArbre + "...");

				AABRR arbre = arbresAppli.get(nomArbre);

				if (App.rechercheABRR(arbre, valeur)) {
					application.getMessage().addLigne(
							"La valeur se trouve bien dans l'arbre");
				} else {
					application.getMessage().addLigne(
							"La valeur " + valeur
									+ " ne se trouve pas " + nomArbre);
					application.getMessage().addLigne(
							"Il n'y a pas d'intervalle qui contienne cette valeur ");
				}

				jtf.setText("");
				jtf7.setText("");
			}
		}
	}
	
	private class SupprimerAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private SupprimerAction() {
			super(Constantes.LibellesBoutons.SUPPRIMER);

		}

		public void actionPerformed(ActionEvent e) {

			clear();

			boolean donneesOK = !jtf.getText().isEmpty()
					&& !jtf6.getText().isEmpty();
			if (!donneesOK) {

				application
						.getMessage()
						.addLigne(
								"Veuillez saisir un nom d'arbre, et une valeur à supprimer SVP ");

			} else {

				String nomArbre = jtf.getText();
				int valeur = Integer.parseInt(jtf6.getText());

				if (!arbresAppli.containsKey(nomArbre)) {
					application.getMessage().addLigne(
							"L'arbre " + nomArbre + " n'existe pas !");
					return;
				}

				application.getMessage().addLigne(
						"Suppression de " + valeur + " dans " + nomArbre
								+ "...");

				AABRR arbre = arbresAppli.get(nomArbre);

				Suppression ret = App.supprimerEntierAABRR(arbre,
						valeur);

				switch (ret) {
				case SUCCES:
					application.getMessage().addLigne(
							"Suppression terminée avec succès !");
					break;
				case INTERVALLE_MANQUANT:
					application.getMessage().addLigne(
							"Erreur lors de la suppression de " + valeur
									+ " dans " + nomArbre);
					application.getMessage().addLigne(
							"L'intervalle contenant la valeur est inexistant");
					break;

				case ENTIER_INEXISTANT:
					application.getMessage().addLigne(
							"Erreur lors de la suppression de " + valeur
									+ " dans " + nomArbre);
					application.getMessage().addLigne("La valeur n'existe pas");
					break;
				}

				jtf.setText("");
				jtf6.setText("");
			}

		}
	}

	private class ChargerABRAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private ChargerABRAction() {
			super(" Importer fichier ABR ");

		}

		public void actionPerformed(ActionEvent e) {

			clear();
			if (champsNomABR.getText().isEmpty()) {

				application
						.getMessage()
						.addLigne(
								"Veuillez saisir un nom d'arbre avant le chargement  SVP ");

			} else {
				// création de la boîte de dialogue
				JFileChooser dialogue = new JFileChooser();

				// affichage
				dialogue.showOpenDialog(null);

				// récupération du fichier sélectionné
				fichier = dialogue.getSelectedFile();

				if (fichier != null) {

					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					application.getMessage().addLigne(
							"Chargement du fichier...");

					if (!arbresBinaireAppli.containsKey(champsNomABR.getText())) {
						application.getMessageArbre().addLigne(
								champsNomABR.getText() + " (Arbre Binaire)");
					}

					ArbreBinaire nouv_arbre = App.chargerABR(fichier);

					arbresBinaireAppli.put(champsNomABR.getText(), nouv_arbre);

					application.getMessage().addLigne(
							"Chargement terminé avec succès !");

					arbresBinaireAppli.put(champsNomABR.getText(), nouv_arbre);

					champsNomABR.setText("");
				} else {
					application.getMessage().addLigne("Fichier inexistant !");
				}

			}

		}

	}

	private class AfficherABRAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private AfficherABRAction() {
			super("Afficher");
		}

		public void actionPerformed(ActionEvent e) {

			clear();
			if (champsNomABR.getText().isEmpty()) {

				application
						.getMessage()
						.addLigne(
								"Veuillez saisir un nom d'arbre avant l'affichage SVP ");

			} else {

				if (!arbresBinaireAppli.containsKey(champsNomABR.getText())) {
					application.getMessage().addLigne(
							"Nom d'arbre inexistant !");
				} else {

					application.getMessage().addLigne(
							"|******************| Affichage de l'arbre ABR: "
									+ champsNomABR.getText() + "|******************|");
					App.afficherABR(arbresBinaireAppli.get(champsNomABR
							.getText()));
					application.getMessage().addLigne(
							"|******************| Affichage terminé !|******************|");
				}

			}
		}
	}

	private class AABRRVersABRAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;

		private AABRRVersABRAction() {
			super("AABRR Vers ABR");
		}

		public void actionPerformed(ActionEvent e) {

			clear();
			boolean donnesOK = true;

			String aabrrAConvertir = jtf.getText();
			String abrACreer = champsNomABR.getText();

			if (aabrrAConvertir.isEmpty()) {

				application.getMessage().addLigne(
						"Veuillez saisir un nom d'AABRR à convertir ");
				donnesOK = false;

			} else if (!arbresAppli.containsKey(aabrrAConvertir)) {
				application.getMessage().addLigne(
						"Nom d'AABRR " + aabrrAConvertir + " inexistant !");
				donnesOK = false;
			}

			if (abrACreer.isEmpty()) {
				application.getMessage().addLigne(
						"Veuillez saisir le nom du nouvel ABR créé");
				donnesOK = false;
			}

			if (donnesOK) {
				application.getMessage().addLigne(
						"|******************| Conversion de l'AABRR: " + aabrrAConvertir
								+ "|******************|");

				ArbreBinaire arbre = App
						.convertirAABRRenABR(arbresAppli.get(aabrrAConvertir));

				arbresBinaireAppli.put(abrACreer, arbre);
				
				

				application.getMessage().addLigne(
						"|******************| Conversion terminé !|******************|");
				application.getMessageArbre().addLigne(
						abrACreer + " (Arbre Binaire)");
				
				application.getMessage().addLigne(
						"|******************| Affichage de l'arbre ABR: "
								+ champsNomABR.getText() + "|******************|");
				application.getMessage().addLigne(" 	Suite préfixe Sp(A)	 ");
				
				App.afficherABR(arbresBinaireAppli.get(champsNomABR
						.getText()));
				application.getMessage().addLigne(
						"|******************| Affichage terminé !|******************|");
			}
		}
	}

	
	private class ABRverAABRRAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private ABRverAABRRAction() {
			super("ABR vers AABRR");
		}

		public void actionPerformed(ActionEvent e) {

			clear();
			boolean donnesOK = true;

			String aabriACreer = jtf.getText();
			String abrAConvertir = champsNomABR.getText();
			String nbIntervalleKString = champsNombreIntervallesABR.getText();

			if (abrAConvertir.isEmpty()) {

				application.getMessage().addLigne(
						"Veuillez saisir un nom d'ABR à convertir ");
				donnesOK = false;

			} else if (!arbresBinaireAppli.containsKey(abrAConvertir)) {
				application.getMessage().addLigne(
						"Nom d'AABRR " + abrAConvertir + " inexistant !");
				donnesOK = false;
			}

			if(nbIntervalleKString.isEmpty()) {
				application.getMessage().addLigne(
						"Veuillez saisir un nombre d'intervalle");
				donnesOK = false;
			}
			
			if (aabriACreer.isEmpty()) {
				application.getMessage().addLigne(
						"Veuillez saisir le nom du nouvel AABRR créé");
				donnesOK = false;
			}

			if (donnesOK) {
				application.getMessage().addLigne(
						"|******************| Conversion de l'ABR: " + abrAConvertir
								+ "|******************|");
				int nbIntervalleK = Integer.parseInt(nbIntervalleKString);

				try {
				AABRR arbre = App
						.convertirABRenAABRR(arbresBinaireAppli.get(abrAConvertir), nbIntervalleK);
				arbresAppli.put(aabriACreer, arbre);
				} catch (Exception ex) {
					application.getMessage().addLigne(ex.getMessage());
				}
				

				application.getMessage().addLigne(
						"|******************| Conversion terminé !|******************|");
				application.getMessageArbre().addLigne(
						aabriACreer + " (AABRR)");
			}
		}
	}
	
	private class AfficherABRGraphiqueAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private AfficherABRGraphiqueAction() {
			super("Affichage graphique ABR");
		}

		public void actionPerformed(ActionEvent e) {

			clear();
			if (champsNomABR.getText().isEmpty()) {

				application
						.getMessage()
						.addLigne(
								"Veuillez saisir un nom d'arbre avant l'affichage SVP ");

			} else {

				if (!arbresBinaireAppli.containsKey(champsNomABR.getText())) {
					application.getMessage().addLigne(
							"Nom d'arbre inexistant !");
				} else {

					JFrame frame = new JFrame("Affichage de l'arbre");
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					frame.setPreferredSize(new Dimension(640, 480));

					JPanel contentPane = new JPanel();
					contentPane.setPreferredSize(new Dimension(640, 480));

					JTree tree;

					tree = AffichageArbreGraphique.getArbreBinaireGraphique(arbresBinaireAppli
							.get(champsNomABR.getText()));

					for(int i=0;i<tree.getRowCount();i++)
					{
					    tree.expandRow(i);
					}
					
					contentPane.add(tree, "Panel 1");
					frame.setContentPane(contentPane);
					frame.pack();
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					frame.setLocationByPlatform(true);
					frame.setVisible(true);

				}

			}
		}
	}
/*************************************************************************************************************************/	
	/**
	 * @author Etonam et Dan
	 * Fonction qui consiste à afficher l'application 
	 * @return L'affichage complet de l'application 
	 */
	public Fenetre() {

		super();
		tableau = new JTable(application.getMessage());
		listeArbres = new JTable(application.getMessageArbre());
		setTitle("Arbres binaires de recherche à rebours");
		getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		getContentPane().add(new JScrollPane(listeArbres), BorderLayout.EAST);

		JPanel fields = new JPanel();

		fields.setLayout(new BorderLayout());

		JPanel fieldAABRR = new JPanel(new FlowLayout(FlowLayout.LEFT));
		fieldAABRR.setLayout(new FlowLayout(FlowLayout.LEFT));

		JPanel boutonsAABRR = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JPanel boutons = new JPanel();
		boutons.setLayout(new BorderLayout());
		Font police = new Font("Times New Roman", Font.BOLD, 25);

		jtf.setFont(police);
		jtf.setPreferredSize(new Dimension(100, 20));
		jtf.setForeground(Color.BLACK);

		jtf2.setFont(police);
		jtf2.setPreferredSize(new Dimension(100, 20));
		jtf2.setForeground(Color.BLACK);

		jtf3.setFont(police);
		jtf3.setPreferredSize(new Dimension(50, 20));
		jtf3.setForeground(Color.BLACK);

		jtf4.setFont(police);
		jtf4.setPreferredSize(new Dimension(50, 20));
		jtf4.setForeground(Color.BLACK);

		jtf5.setFont(police);
		jtf5.setPreferredSize(new Dimension(50, 20));
		jtf5.setForeground(Color.BLACK);

		jtf6.setFont(police);
		jtf6.setPreferredSize(new Dimension(50, 20));
		jtf6.setForeground(Color.BLACK);

		jtf7.setFont(police);
		jtf7.setPreferredSize(new Dimension(50, 20));
		jtf7.setForeground(Color.BLACK);

		fieldAABRR.add(label0);
		fieldAABRR.add(label);
		fieldAABRR.add(jtf);
		fieldAABRR.add(label2);
		fieldAABRR.add(jtf2);
		fieldAABRR.add(label3);
		fieldAABRR.add(jtf3);
		fieldAABRR.add(label4);
		fieldAABRR.add(jtf4);
		fieldAABRR.add(label5);
		fieldAABRR.add(jtf5);
		fieldAABRR.add(label6);
		fieldAABRR.add(jtf6);
		fieldAABRR.add(label8);
		fieldAABRR.add(jtf7);
		

		fields.add(fieldAABRR, BorderLayout.NORTH);
		
		JButton afficher = new JButton(new AfficherAction());
		afficher.setBackground(Color.magenta);
		JButton charger = new JButton(new ImporterAction());
		charger.setBackground(Color.magenta);
		JButton exporter = new JButton(new ExportAction());
		exporter.setBackground(Color.magenta);
		JButton verifier = new JButton(new VerifierAction());
		verifier.setBackground(Color.magenta);
		boutonsAABRR.add(labelBoutonsAABRR);
		boutonsAABRR.add(afficher);
		boutonsAABRR.add(charger);
		boutonsAABRR.add(exporter);
		boutonsAABRR.add(verifier);
		JButton aleatoire = new JButton(new AleatoireAction());
		aleatoire.setBackground(Color.magenta);
		boutonsAABRR.add(aleatoire);
		
		JButton inserer = new JButton(new InsererAction());
		inserer.setBackground(Color.magenta);
		boutonsAABRR.add(inserer);
		
		JButton supprimer = new JButton(new SupprimerAction());
		supprimer.setBackground(Color.magenta);
		boutonsAABRR.add(supprimer);
		
		JButton rechercher = new JButton(new RechercherAction());
		rechercher.setBackground(Color.magenta);
		boutonsAABRR.add(rechercher);
		
		boutons.add(boutonsAABRR, BorderLayout.NORTH);

		JPanel fieldsABR = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelNbIntervallesABR = new JLabel("Nombre d'intervalles souhaités : ");
		champsNombreIntervallesABR.setFont(police);
		champsNombreIntervallesABR.setPreferredSize(new Dimension(100, 20));
		champsNombreIntervallesABR.setForeground(Color.BLACK);

		champsNomABR.setFont(police);
		champsNomABR.setPreferredSize(new Dimension(100, 20));
		champsNomABR.setForeground(Color.BLACK);
		
		fieldsABR.add(label7);
		fieldsABR.add(labelNomABR);
		fieldsABR.add(champsNomABR);
		fieldsABR.add(labelNbIntervallesABR);
		fieldsABR.add(champsNombreIntervallesABR);
		fields.add(fieldsABR, BorderLayout.SOUTH);

		JPanel boutonsABR = new JPanel(new FlowLayout(FlowLayout.LEFT));
		boutonsABR.add(labelBoutonsABR);
		
		JButton chargerABR = new JButton(new ChargerABRAction());
		chargerABR.setBackground(Color.green);
		
		JButton afficherABR = new JButton(new AfficherABRAction());
		afficherABR.setBackground(Color.green);
		
		JButton convertirA = new JButton(new AABRRVersABRAction());
		convertirA.setBackground(Color.green);
		
		JButton convertirB = new JButton(new ABRverAABRRAction());
		convertirB.setBackground(Color.green);
		
		JButton graphique = new JButton(new AfficherABRGraphiqueAction());
		graphique.setBackground(Color.green);
		
		boutonsABR.add(afficherABR);
		boutonsABR.add(chargerABR);
		boutonsABR.add(convertirA);
		boutonsABR.add(convertirB);
		boutonsABR.add(graphique);
		boutons.add(boutonsABR, BorderLayout.SOUTH);
	
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		container.setLayout(new BorderLayout());
		getContentPane().add(fields, BorderLayout.SOUTH);
		getContentPane().add(boutons, BorderLayout.PAGE_START);
		pack();
	}

}
