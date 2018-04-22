package Affichage_graphique;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;



import Application.ArbreBinaire;
import Application.Constantes;

public class AffichageArbreGraphique {

	/**
	 * Construit un arbre graphique à partir d'un arbre binaire
	 * @param arbre l'arbre à afficher
	 * @return un JTree correspondant à l'affichage graphique de l'arbre
	 */
	public static JTree getArbreBinaireGraphique(ArbreBinaire arbre) {
		JTree tree = null;
		
		if (!arbre.isArbreVide()) {
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("");
			getNoeudsArbreBinaireGraphique(arbre, root, Constantes.TypeFils.RACINE);
			tree = new JTree();
		}
		
		// configurer l'apparence de l'arbre
		setConfigTree(tree);
	
		return tree;

	}

	/**
	 * Parcours un arbre binaire pour la construction des noeuds graphiques
	 * @param arbre l'arbre global à construire
	 * @param root le noeud courant
	 * @param type le type du noeud courant (racine, gauche ou droit)
	 * @return
	 */
	private static DefaultMutableTreeNode getNoeudsArbreBinaireGraphique(
			ArbreBinaire arbre, DefaultMutableTreeNode root, String type) {

		DefaultMutableTreeNode newNode = null;
		if (!arbre.isArbreVide()) {

			Integer node = arbre.getVal();
			// noeud racine
			newNode = new DefaultMutableTreeNode(type + " ( " + node.toString()
					+ " ) ");
			// noeuds enfants
			DefaultMutableTreeNode sagNodes = getNoeudsArbreBinaireGraphique(
					arbre.getSag(), newNode, Constantes.TypeFils.GAUCHE);
			DefaultMutableTreeNode sadNodes = getNoeudsArbreBinaireGraphique(
					arbre.getSad(), newNode, Constantes.TypeFils.DROIT);
			if (sagNodes != null) {
				newNode.add(sagNodes);
			}

			if (sadNodes != null) {
				newNode.add(sadNodes);
			}

			// add the child nodes to the root node
			root.add(newNode);

		}

		return newNode;
	}

	/**
	 * Configure l'apparence d'un arbre graphique
	 * @param tree l'arbre à configurer
	 */
	private static void setConfigTree(JTree tree) {
		// pour la définition de l'apparence
		
		DefaultTreeCellRenderer tCellRenderer = new  DefaultTreeCellRenderer();
		tCellRenderer.setOpenIcon(new ImageIcon("img/tree_node.png"));
		tCellRenderer.setClosedIcon(new ImageIcon("img/tree_node.png"));
		tCellRenderer.setLeafIcon(new ImageIcon("img/tree_leaf.png"));
		
		tree.setCellRenderer(tCellRenderer);
		tree.setRootVisible(false);
		
		tree.putClientProperty("JTree.lineStyle", "None");
		tree.putClientProperty("Tree.padding", 500);
		tree.putClientProperty("Tree.rightChildIndent", 80);
	}
}
