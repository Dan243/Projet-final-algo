package Application;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import Affichage_graphique.FenetreListeArbre;
import Affichage_graphique.FenetreMessage;

public class App {

	/**
	 * @author Etonam et Dan
	 * Ces classes repr�sentent des outils pour pouvoir afficher � l'�cran 
	 */
	private static FenetreMessage message;

	private static FenetreListeArbre listeArbre;

	/**
	 * @author Etonam et Dan
	 * Constructeur 
	 */
	public App() {
		message = new FenetreMessage();
		listeArbre = new FenetreListeArbre();
	}

	public FenetreMessage getMessage() {
		return message;
	}

	public void setMessage(FenetreMessage message) {
		App.message = message;
	}

	public FenetreListeArbre getMessageArbre() {
		return listeArbre;
	}

	public void setMessageArbre(FenetreListeArbre message) {
		App.listeArbre = message;
	}
	
	
	public static AABRR convertirABRenAABRR(ArbreBinaire arbre, int k) throws Exception {
		// cr�ation de l'arbre vide
		AABRR res = new AABRR(true);
		// le minimum de l'arbre
		Integer min = arbre.getValMinNoeudABR(arbre);
		Integer max = arbre.getValMaxNoeudABR(arbre);
		if(max - min < k) {
			throw new Exception("Nombre d'intervalles trop grand par rapport au nombre de valeurs");
		}
		int pas = (max-min)/k;
		int i=min;
		int j=i+pas;
		Set<Integer> valuesABR;
		boolean maxAjoute = false;
		while(j <= max && !maxAjoute) {
			if(j == max) {
				maxAjoute = true;
			}
			// le noeud de l'AABRI contenant les valeurs comprises entre Ai et Bi
			AABRR noeud = new AABRR(true);
			
			// d�finition des valeurs de m et M
			noeud.setPetitM(i);
			noeud.setGrandM(j);
			
			// l'ABRI contenu dans le noeud courant
			ArbreBinaire abrr = new ArbreBinaire(true);
			
			// r�cup�ration des valeurs de l'ABR comprises entre i et j
			valuesABR = new HashSet<Integer>();
			arbre.getValuesABRFromInterval(i, j, arbre, valuesABR);
			
			// ins�rer chaque valeur dans l'ABRI
			for(Integer n : valuesABR) {
				abrr.insererNoeudABR(abrr, n);
			}
			
			// ins�rer les noeuds
			noeud.setArbre(abrr);
			res.insererNoeudAABRR(res, noeud);
			
			// incr�ment de i
			i = j+1;
			
			// incr�ment de j
			j= (i+pas <= max) ? i+pas : max;
		}
		return res;
	}
	
	/**
	 * Convertit un AABRR en ABR
	 * @param arbre, l'AABRR à convertir
	 * @return un ABR
	 */
	public static ArbreBinaire convertirAABRRenABR(AABRR arbre) {
		// cr�ation de l'arbre vide
		ArbreBinaire res = new ArbreBinaire(true);
		convertirAABRRAux(arbre, res);
		return res;
	}
	
	
	/**
	 * Cette fonction sert convertir un AABRR en ABR
	 * @param arbre
	 * @param res
	 */
	private static void convertirAABRRAux(AABRR arbre, ArbreBinaire res) {
		if (!arbre.isArbreVide()) {
			insererABRRDansABR(arbre.getArbre(), res);
			convertirAABRRAux(arbre.getSag(), res);
			convertirAABRRAux(arbre.getSad(), res);
		}
	}
	/**
	 * Ins�rer un arbre dans un arbre binaire
	 * @param abri
	 * @param res
	 */
	private static void insererABRRDansABR(ArbreBinaire abri, ArbreBinaire res) {
		if (!abri.isArbreVide()) {
			insererNoeudABR(res, abri.getVal());
			insererABRRDansABR(abri.getSag(), res);
			insererABRRDansABR(abri.getSad(), res);
		}
	}

	/**
	 * Charge un ABR � partir d'un fichier
	 * @param filePath, le fichier � charger
	 * @return l'ABR cr��
	 */
	public static ArbreBinaire chargerABR(File filePath) {
		// c�ation d'un ARBE BINAIRE
		ArbreBinaire arbre = new ArbreBinaire(true);
		if (filePath != null) {
			try {
				FileReader fr = new FileReader(filePath);
				BufferedReader br = new BufferedReader(fr);
				try {
					String line = br.readLine();
					while (line != null) {

						if (line.length() > 0) {
							// tableau contenant toute la ligne
							String[] tab = line.split(":");

							insererLigneFichierDansABR(arbre, tab);
						}
						// lecture de la ligne suivante
						line = br.readLine();
					}
					br.close();
					fr.close();
				} catch (IOException exception) {
					message.addLigne("Erreur lors de la lecture :"
							+ exception.getMessage());
				}
			} catch (FileNotFoundException exception) {

				message.addLigne("Le fichier n'a pas �t� trouv�e");
			}
		}
		return arbre;

	}

	/**
	 * Charge un AABRI � partir d'un fichier
	 * @param filePath, le fichier � charger
	 * @return l'AABRI cr��
	 */
	public static AABRR chargerAABRR(File filePath) {

		// cr�ation d'un AABRI vide
		AABRR arbre = new AABRR(true);
		if (filePath != null) {

			try {

				FileReader fr = new FileReader(filePath);
				BufferedReader br = new BufferedReader(fr);
				int petitM;
				int grandM;
				try {
					String line = br.readLine();
					while (line != null) {

						if (line.length() > 0) {
							// tableau contenant toute la ligne
							String[] tab = line.split(";");

							// tableau contenant la partie m et M
							String[] mTab = tab[0].split(":");
							petitM = Integer.parseInt(mTab[0]);
							grandM = Integer.parseInt(mTab[1]);

							// tableau contenant les �l�ments du noeud
							String[] tabNoeud = tab[1].split(":");
							insererLigneFichierDansAABRR(arbre, petitM, grandM,
									tabNoeud);
						}
						// lecture de la ligne suivante
						line = br.readLine();
					}
					br.close();
					fr.close();
				} catch (IOException exception) {
					message.addLigne("Erreur lors de la lecture :"
							+ exception.getMessage());
				}
			} catch (FileNotFoundException exception) {

				message.addLigne("Le fichier n'a pas �t� trouv�e");
			}
		}

		return arbre;
	}

	/**
	 * Affiche un AABRI (sous le m�me format que le fichier)
	 * @param arbre, l'arbre � afficher
	 */
	public static void afficherAABRR(AABRR arbre) {
		parcoursPrefixeAABRR(arbre);
	}

	/**
	 * Affiche un ABR (sous le m�me format que le fichier)
	 * @param arbre, l'arbre � afficher
	 */
	public static void afficherABR(ArbreBinaire arbre) {

		parcoursPrefixeABR(arbre);
		message.addLigne(message.getMessage().getMessagePrint());
		message.getMessage().setMessagePrint("");

	}

	private static void parcoursPrefixeABR(ArbreBinaire arbre) {

		if (!arbre.isArbreVide()) {

			message.print("\n " + arbre.getVal() + ":");
			parcoursPrefixeABR(arbre.getSag());
			parcoursPrefixeABR(arbre.getSad());

		}

	}

	private static void parcoursPrefixeAABRR(AABRR arbre) {
		// cas d'arret
		if (!arbre.isArbreVide()) {

			message.print("\n ( m=" + arbre.getPetitM() + " et  M="
					+ arbre.getGrandM() + " ) ");

			parcoursPrefixeABRR(arbre.getArbre());
			if (!message.getMessage().getMessagePrint().isEmpty()) {
				message.addLigne(enleverSeparateur(message.getMessage()
						.getMessagePrint(), ':'));
			}
			message.getMessage().setMessagePrint("");

			parcoursPrefixeAABRR(arbre.getSag());
			if (!message.getMessage().getMessagePrint().isEmpty()) {
				message.addLigne(enleverSeparateur(message.getMessage()
						.getMessagePrint(), ':'));
			}
			message.getMessage().setMessagePrint("");

			parcoursPrefixeAABRR(arbre.getSad());
			if (!message.getMessage().getMessagePrint().isEmpty()) {
				message.addLigne(enleverSeparateur(message.getMessage()
						.getMessagePrint(), ':'));
			}
			message.getMessage().setMessagePrint("");

		}
	}

	private static void parcoursPrefixeABRR(ArbreBinaire arbre) {
		if (arbre.getVal() != 0) {
			message.print(arbre.getVal() + ":");
			parcoursPrefixeABRR(arbre.getSag());
			parcoursPrefixeABRR(arbre.getSad());
		}
	}

	private static void insererLigneFichierDansAABRR(AABRR a, int petitM,
			int grandM, String[] tabNoeud) {
		AABRR arbre = new AABRR(true);
		arbre.setPetitM(petitM);
		arbre.setGrandM(grandM);
		ArbreBinaire noeud = getNoeudAABRIFromLigneFichier(tabNoeud);
		arbre.setArbre(noeud);
		a.insererNoeudAABRR(a, arbre);

	}
	/**
	 * Cette fonction sert � ins�rer une ligne d'un fichier dans un ABR
	 * @param arbre
	 * @param ligne
	 */
	private static void insererLigneFichierDansABR(ArbreBinaire arbre,
			String[] ligne) {
		for (String str : ligne) {
			Integer noeud = Integer.parseInt(str);
			insererNoeudABR(arbre, noeud);
		}
	}

	/**
	 * Ins�re un noeud dans un ABR
	 * @param arbre
	 * @param noeudAInserer
	 */
	private static void insererNoeudABR(ArbreBinaire arbre,
			Integer noeudAInserer) {
		if (arbre.isArbreVide()) {
			arbre.setVal(noeudAInserer);
			arbre.setSag(new ArbreBinaire(true));
			arbre.setSad(new ArbreBinaire(true));
			arbre.setArbreVide(false);
		} else if (noeudAInserer < arbre.getVal()) {
			insererNoeudABR(arbre.getSag(), noeudAInserer);
		} else {
			insererNoeudABR(arbre.getSad(), noeudAInserer);
		}
	}



	/**
	 * R�cup�re les noeuds d'un AABRI � partir d'une ligne d'un fichier (sous forme de tableau)
	 * @param elementsNoeud
	 * @return
	 */
	private static ArbreBinaire getNoeudAABRIFromLigneFichier(
			String[] elementsNoeud) {
		ArbreBinaire res = new ArbreBinaire(true);
		for (int i = 0; i < elementsNoeud.length; ++i) {
			res.insererNoeudABR(res, Integer.parseInt(elementsNoeud[i]));
		}
		return res;
	}

	/**
	 * Exporter un AABRI vers un fichier
	 * @param arbre, l'AABRI � exporter
	 * @param nomFichier, le nom du fichier � cr�er
	 */
	public static void exporterAABRR(AABRR arbre, String nomFichier) {

		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					nomFichier)));

			parcoursPrefixeAABRRExporter(arbre, pw);

			pw.close();
		} catch (IOException exception) {
			message.addLigne("Erreur lors de la l'ecriture ");
		}

	}

	// Permet d'enlever les separateurs en trop
	public static String enleverSeparateur(String str, char c) {
		if (str.length() > 0 && str.charAt(str.length() - 1) == c) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	/**
	 * R�alise le parcours pr�fixe d'un AABRI pour l'export vers un fichier
	 * @param arbre, l'arbre � exporter
	 * @param pw, le printer vers le fichier
	 */
	private static void parcoursPrefixeAABRRExporter(AABRR arbre, PrintWriter pw) {
		// cas d'arret

		if (arbre.getGrandM() != 0 && arbre.getPetitM() != 0) {

			message.print("\n" + arbre.getPetitM() + ":" + arbre.getGrandM()
					+ ";");

			parcoursPrefixeABR(arbre.getArbre());
			if (!message.getMessage().getMessagePrint().isEmpty()) {
				pw.print(enleverSeparateur(message.getMessage()
						.getMessagePrint(), ':'));
			}
			message.getMessage().setMessagePrint("");

			parcoursPrefixeAABRRExporter(arbre.getSag(), pw);
			if (!message.getMessage().getMessagePrint().isEmpty()) {
				pw.print(enleverSeparateur(message.getMessage()
						.getMessagePrint(), ':'));
			}
			message.getMessage().setMessagePrint("");

			parcoursPrefixeAABRRExporter(arbre.getSad(), pw);
			if (!message.getMessage().getMessagePrint().isEmpty()) {
				pw.print(enleverSeparateur(message.getMessage()
						.getMessagePrint(), ':'));
			}
			message.getMessage().setMessagePrint("");

		}
	}
	/**
	 * Contr�le si un arbre est bien un AABRI
	 * @param arbre, l'arbre � contr�ler
	 * @param min, le minimum de l'AABRI courant
	 * @param max, le maximum de l'AABRI courant
	 * @return, true si arbre est un AABRI, false sinon
	 */
	public static boolean isAABRR(AABRR arbre, Integer min, Integer max) {

		if (arbre.isArbreVide()) {
			return true;
		}
		
		
		if (arbre.getPetitM() >= arbre.getGrandM()
				|| !isABRR(arbre.getArbre(), arbre.getPetitM(),
						arbre.getGrandM()) || arbre.getPetitM() < min
				|| arbre.getPetitM() > max) {
			return false;
		}

	  // Verifier les intervalles pour les Sag
     if (!arbre.getSag().isArbreVide()){
    	 
    	 	if ( !(arbre.getPetitM()>arbre.getSag().getPetitM()) || !(arbre.getPetitM()>arbre.getSag().getGrandM()) ){
			return false;
    	 	}
     }
     
     // Verifier les intervalles pour les Sad
     if (!arbre.getSad().isArbreVide()){
    	 
	       if ( !(arbre.getGrandM()<arbre.getSad().getPetitM()) || !(arbre.getGrandM()<arbre.getSad().getGrandM()) ) {			
				return false;
			}
     }
		
		return isAABRR(arbre.getSag(), min, arbre.getPetitM())
				&& isAABRR(arbre.getSad(), arbre.getPetitM(), max);
	}

	/**
	 * Verifier que les noeuds sont entre [m;M]
	 * @param arbre, l'arbre � contr�ler
	 * @param min, le minimum de l'ABR courant
	 * @param max, le maximum de l'ABR courant
	 * @return, true si arbre est un ABRI, false sinon
	 */
	private static boolean isABRR(ArbreBinaire arbre, Integer min, Integer max) {
		if (arbre.isArbreVide()) {
			return true;
		}
		
		if (arbre.getVal() < min || arbre.getVal() > max) {
			return false;
		}

		return isABRR(arbre.getSag(), arbre.getVal(), max)
				&& isABRR(arbre.getSad(), min, arbre.getVal());
	}

	
	/**
	 * G�n�re un AABRI al�atoire
	 * @param nbNoeuds, le nombre de noeuds souhait�. Il doit �tre <= au nombre de valeurs comprises dans l'intervalle [1; maxM]
	 * @param maxM, la valeur maximal de l'AABRI
	 * @return
	 */
	public static AABRR genererAABRRAleatoire(int nbNoeuds, int maxM) {

		// ensemble de valeurs appartenant aux intervalles d�j� tir�s
		Set<Integer> forbiddenValues = new HashSet<Integer>();

		// l'arbre r�sultat
		AABRR res = new AABRR(true);
		int petitM = 0;
		int grandM = 0;
		Integer[] valeursABRI;
		for (int i = 1; i <= nbNoeuds; ++i) {

			// effectuer un tirage tant que les deux nb ne sont pas distincts et
			// d�j� tir�s

			int[] tirage = res.tirerPetitEtGrandM(forbiddenValues,
					maxM, nbNoeuds - (i - 1));

			petitM = tirage[0];
			grandM = tirage[1];

			// ajout de l'intervalle des valeurs tir�s
			forbiddenValues.addAll(res.getIntervalValues(petitM, grandM));

			valeursABRI = res.generateIntegerTab(petitM, grandM);
			ArbreBinaire noeud = new ArbreBinaire(true);
			for (int j = 0; j < valeursABRI.length; ++j) {
				noeud.insererNoeudABR(noeud, valeursABRI[j]);
			}

			AABRR arbreInsert = new AABRR(true);
			arbreInsert.setPetitM(petitM);
			arbreInsert.setGrandM(grandM);
			arbreInsert.setArbre(noeud);

			res.insererNoeudAABRR(res, arbreInsert);
		}
		return res;
	}


	/**
	 * Ins�re un entier dans un AABRI
	 * @param arbre, l'arbre dans lequel on veut ins�rer
	 * @param i, l'entier � ins�rer
	 * @return, true si succ�s, false sinon (intervalle inexistant)
	 */
	public static boolean insererEntierAABRR(AABRR arbre, int i) {
		if (arbre.isArbreVide()) {
			return false;
		} else {
			ArbreBinaire noeud = new ArbreBinaire(i, null, null);
			if (i >= arbre.getPetitM() && i <= arbre.getGrandM()) {
				
				noeud.insererNoeudABR(arbre.getArbre(), i);
				return true;
			} else if (i < arbre.getPetitM()) {
				return insererEntierAABRR(arbre.getSag(), i);
			} else if (i > arbre.getGrandM()) {
				return insererEntierAABRR(arbre.getSad(), i);
			}
		}
		return false;
	}

	/**
	 * Tente de supprimer un entier d'un AABRI
	 * @param arbre, l'arbre dans lequel on veut effectuer la suppression
	 * @param i, l'entier � supprimer
	 * @return, le type d'erreur (entier inexistant, intervalle inexistant ou succ�s)
	 */

	public static Suppression supprimerEntierAABRR(AABRR arbre, int i) {
		if (arbre.isArbreVide()) {
			return Suppression.INTERVALLE_MANQUANT;
		} else {
			ArbreBinaire noeud = new ArbreBinaire(i, null, null);
			if (i >= arbre.getPetitM() && i <= arbre.getGrandM()) {
				return noeud.supprimerEntierABR(arbre.getArbre(), i);
			} else if (i < arbre.getPetitM()) {
				return supprimerEntierAABRR(arbre.getSag(), i);
			} else {
				return supprimerEntierAABRR(arbre.getSad(), i);
			}
		}
	}
	/**
	 * @author Etonam et Dan 
	 * Fonction qui recherche un entier dans un ABRR
	 * 
	 */
	public static boolean rechercheABRR(AABRR arbre, int i){
		if (arbre.isArbreVide()){
			return false;
		} else {
			ArbreBinaire noeud = new ArbreBinaire(i, null, null);
			if (i >= arbre.getPetitM() && i <= arbre.getGrandM()){
				return noeud.recherche(i);
			} else if (i < arbre.getPetitM()){
				return rechercheABRR(arbre.getSag(), i);
			} else {
				return rechercheABRR(arbre.getSad(), i);
			}
		}
		
	}
}
