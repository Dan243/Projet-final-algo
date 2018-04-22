package Application;

import java.util.Set;

/**
 * Cette classe représente un arbre binaire et toutes les méthodes associées
 * @author Etonam et Dan
 *
 */
public class ArbreBinaire extends Arbre {
	private int val;
	private ArbreBinaire sag, sad;
	private boolean arbreVide;

	/**
	 * Construit un nouveau noeud avec une valeur, et ses sous-arbres droit et gauche
	 * @param x
	 * @param g
	 * @param d
	 */
	public ArbreBinaire(int x, ArbreBinaire g, ArbreBinaire d){
		super(x, g, d);
		this.val = x;
		this.sag = g;
		this.sad = d;
	}

	/**
	 * Construit un nouvel arbre binaire, en définissant s'il est vide ou non
	 * @param b true pour construire un arbre vide
	 */
	public ArbreBinaire(boolean b) {
		super(b);
		this.arbreVide = b;
	}

	/**
	 * Construit un nouvel arbre binaire, en dÃ©finissant s'il est vide ou non
	 * @param b true pour construire un arbre vide
	 */
	public boolean  estVide() {
		return this.estVide();
	}

	// LES ACCESSEURS 
	
	/**
	 * Retourne la valeur du val de l'arbre binaire
	 * @return
	 */
	public int getVal() {
		return val;
	}

	/**
	 * Définit la valeur du val
	 * @param val la nouvelle valeur du val
	 */
	public void setVal(int val) {
		this.val = val;
	}

	/**
	 * Retourne le sous-arbre gauche du val
	 * @return
	 */
	public ArbreBinaire getSag() {
		return sag;
	}

	/**
	 * DÃ©finit le sous-arbre gauche du val
	 * @param sag le nouveau sous-arbre gauche
	 */
	public void setSag(ArbreBinaire sag) {
		this.sag = sag;
	}

	/**
	 * Retourne le sous-arbre droit du val
	 * @return
	 */
	public ArbreBinaire getSad() {
		return this.sad;
	}

	/**
	 * DÃ©finit le sous-arbre droit du val
	 * @param sag le nouveau sous-arbre droit
	 */
	public void setSad(ArbreBinaire sad) {
		this.sad = sad;
	}

	
	/**
	 * ContrÃ´le si l'arbre est vide
	 * @return
	 */
	public boolean isArbreVide() {
		return arbreVide;
	}

	public void setArbreVide(boolean arbreVide) {
		this.arbreVide = arbreVide;
	}

	/**
     * Fonction qui teste si l'arbre est vide  
     * @param a un arbre
     * @return un boolean indiquant si a est un arbre binaire de recherche
     */
    public static boolean estABR(Arbre a) {
		if (a == null) {
		    return true;
		}
		if ((a.getSousArbreGauche() != null) && (a.getSousArbreGauche().getValeur() > a.getValeur())) {
		    return false;
		}
		if ((a.getSousArbreDroit() != null) && (a.getSousArbreDroit().getValeur() < a.getValeur())) {
		    return false;
		}
		return (estABR(a.getSousArbreGauche()) && estABR(a.getSousArbreDroit()));
    }
    		/**
    		 * 
    		 * @param a
    		 * @return
    		 */
    public static boolean estABRaRebours(Arbre a) {
		if (a == null) {
		    return true;
		}
		if ((a.getSousArbreGauche() != null) && (a.getSousArbreGauche().getValeur() > a.getValeur())) {
		    return false;
		}
		if ((a.getSousArbreDroit() != null) && (a.getSousArbreDroit().getValeur()) <= a.getValeur()) {
		    return false;
		}
		return (estABR(a.getSousArbreGauche()) && estABR(a.getSousArbreDroit()));
    }
    
    /**
	 * Retourne la plus petite valeur d'un ABR 
	 * @param l'arbre binaire de recherche 
	 * @return la plus petite valeur de l'arbre 
	 * @pre l'arbre ne doit pas etre vide 
	 */
	public int getValMinNoeudABR( ArbreBinaire arbre ){
		if (arbre.getSag().isArbreVide()){
			return arbre.getVal();
		}
		return getValMinNoeudABR(arbre.getSag());
	}
	
	/**
	 * Retourne la plus grande valeur d'un ABR
	 * @param l'arbre binaire de recherche 
	 * @return la plus grande valeur de l'arbre
	 * @pre l'abre ne doit pas etre vide 
	 */
	
	public int getValMaxNoeudABR (ArbreBinaire arbre){
		if (arbre.getSad().isArbreVide()){
			return arbre.getVal();
		}
		return getValMaxNoeudABR(arbre.getSad());
	}
	
	/**
	 * Retourne la plus grande valeur de l'ABRI a (valeur la plus à gauche). 
	 * Pré-condition : a n'est pas un arbre vide
	 * @param a
	 * @return
	 */
	public static ArbreBinaire getValMaxNoeudABRR(ArbreBinaire a) {
		if (a.getSag().isArbreVide()) {
			return a;
		}
		return getValMaxNoeudABRR(a.getSag());
	}
	
	
	/**
	 * Met dans un ensemble les valeurs d'un ABR comprises dans un intervalle
	 * @param a la borne inférieure de l'intervalle
	 * @param b la borne supérieure de l'intervalle
	 * @param arbre l'ABR
	 * @param res l'ensemble contenant les valeurs
	 * @return
	 */
	public void getValuesABRFromInterval(int a, int b, ArbreBinaire arbre, Set<Integer> res) {
		if(!arbre.isArbreVide()) {
			if(arbre.getVal() >= a && arbre.getVal() <= b) {
				res.add(arbre.getVal());
				getValuesABRFromInterval(a, b, arbre.getSag(), res);
				getValuesABRFromInterval(a, b, arbre.getSad(), res);
			} else if(arbre.getVal() < a) {
				getValuesABRFromInterval(a, b, arbre.getSad(), res);
			} else if(arbre.getVal() > b) {
				getValuesABRFromInterval(a, b, arbre.getSag(), res);
			}
		}
	}
	
	/**
	 * Supprime un noeud d'un ABRI
	 * @param noeudASupprimer, le noeud à supprimer
	 */
	private static void supprimerNoeudABRR(ArbreBinaire noeudASupprimer) {
		ArbreBinaire temp;
		// si c'est une feuille
		if (noeudASupprimer.getSad().isArbreVide()
				&& noeudASupprimer.getSag().isArbreVide()) {
			noeudASupprimer.setVal((Integer) null);
			noeudASupprimer.setArbreVide(true);
		} else if (noeudASupprimer.getSad().isArbreVide()
				&& !noeudASupprimer.getSag().isArbreVide()) {
			// si le noeud possède un fils gauche uniquement
			temp = noeudASupprimer.getSag();
			noeudASupprimer.setVal(temp.getVal());
			noeudASupprimer.setSag(temp.getSag());
			noeudASupprimer.setSad(temp.getSad());
		} else if (!noeudASupprimer.getSad().isArbreVide()
				&& noeudASupprimer.getSag().isArbreVide()) {
			// si le noeud possède un fils droit uniquement
			temp = noeudASupprimer.getSad();
			noeudASupprimer.setVal(temp.getVal());
			noeudASupprimer.setSag(temp.getSag());
			noeudASupprimer.setSad(temp.getSad());
		} else {

			// si le noeud possède 2 fils, récupération de la plus grande valeur
			// du sous-arbre droit
			temp = noeudASupprimer.getSad();
			ArbreBinaire maxNoeud = getValMaxNoeudABRR(noeudASupprimer.getSad());
			noeudASupprimer.setVal(maxNoeud.getVal());
			noeudASupprimer.setSad(maxNoeud.getSad());
			noeudASupprimer.getSad().setSad(temp);

			maxNoeud.setVal((Integer) null);
			maxNoeud.setArbreVide(true);
		}
	}
	
	/**
	 * Supprime un entier d'un Arbre Binaire
	 * @param a : l'arbre binaire
	 * @param i : l'entier à supprimer
	 * @return true si i existe dans a, false sinon
	 */
	
	public Suppression supprimerEntierABR(ArbreBinaire a, int i) {
		if (a.isArbreVide()) {
			return Suppression.ENTIER_INEXISTANT;
		} else if (a.getVal() == i) {
			supprimerNoeudABRR(a);
			return Suppression.SUCCES;
		} else if (i > a.getVal()) {
			return supprimerEntierABR(a.getSag(), i);
		} else {
			return supprimerEntierABR(a.getSad(), i);
		}
	}
	
	/**
	 * Insère un entier dans un ABRI
	 * @param a, l'arbre dans lequel insérer
	 * @param n, l'entier à insérer
	 */
	public void insererNoeudABR(ArbreBinaire a, Integer n) {
		// cas d'arret
		if (a.isArbreVide()) {
			a.setVal(n);
			a.setArbreVide(false);
			a.setSad(new ArbreBinaire(true));
			a.setSag(new ArbreBinaire(true));
		} else if (a.getVal() > n) {
			insererNoeudABR(a.getSad(), n);
		} else if (a.getVal() < n) {
			insererNoeudABR(a.getSag(), n);
		}
	}
}
