package Affichage_graphique;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import Application.ArbreBinaire;
import Application.Constantes;

public class AffichageArbre {

	public static JTree getArbreGraphique(ArbreBinaire arbre) {
		JTree monarbre = null ; 
		if (!arbre.isArbreVide()) {
			DefaultMutableTreeNode racine = new DefaultMutableTreeNode("racine");
			getNoeudsArbreGraphique(arbre, racine, Constantes.TypeFils.RACINE);
			monarbre = new JTree(racine);
		}
		return monarbre;

	}

	private static DefaultMutableTreeNode getNoeudsArbreGraphique(
			ArbreBinaire arbre, DefaultMutableTreeNode root, String type) {

		DefaultMutableTreeNode newNode = null;
		if (!arbre.isArbreVide()) {

			Integer node = arbre.getVal();
			// noeud racine
			newNode = new DefaultMutableTreeNode(type + " ( " + node.toString()
					+ " ) ");
			// noeuds enfants
			DefaultMutableTreeNode sagNodes = getNoeudsArbreGraphique(
					arbre.getSag(), newNode, Constantes.TypeFils.GAUCHE);
			DefaultMutableTreeNode sadNodes = getNoeudsArbreGraphique(
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
}
