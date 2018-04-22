package Application;
/**
 * Classe de l'arbre 
 * @author Etonam et Dan
 *
 */
public class Arbre {

	    private int valeur;
	    private Arbre gauche, droit;   
		private boolean arbreVide;
	 
	    /**
	     * Constructeurs d'un arbre contenant juste une valeur 
	     * @param x
	     * Créer une arbre 
	     */
	    public Arbre(int x) {
	        this.valeur = x;
	    }
	   
		/**
		 * Construit un nouvel arbre binaire, en définissant s'il est vide ou non
		 * @param b true pour construire un arbre vide
		 */
	    public Arbre(boolean b){
	    	this.arbreVide = b;
	    }
	    /**
	     * Constructeur d'un arbre ayant une racine, un fils gauche et un fils droit 
	     * @param x
	     * @param g
	     * @param d
	     */
	    public Arbre(int x, Arbre g, Arbre d) {
	        this.valeur = x;
	        this.gauche = g;
	        this.droit = d;
	    } 

	    // ACCESSEURS
	    /**
	     * Recupère la valeur de l'arbre 
	     * @return
	     */
	    public int getValeur() {
	        return(this.valeur);
	    }
	    /**
	     * Renvoie le sous-arbre gauche de l'arbre 
	     * @return
	     */
	    public Arbre getSousArbreGauche() {
	        return(this.gauche);
	    }   
	    /**
	     * Renvoie le sous-arbre droit de l'arbre 
	     * @return
	     */
	    public Arbre getSousArbreDroit() {
	        return(this.droit);
	    }
	    /**
	     * Renvoie le booléen qui croit que l'arbre est vide ou non
	     * @return
	     */
	    public boolean isArbreVide() {
			return arbreVide;
		}

		public void setArbreVide(boolean arbreVide) {
			this.arbreVide = arbreVide;
		}

	    // AFFICHAGE
	    public String toString() {
	        return toString("\t");
	    }

	    public String toString(String s) {
			if (gauche!=null) {
			if (droit!=null) 
			    return(s+valeur+"\n"+gauche.toString(s+"\t")+droit.toString(s+"\t"));
			else
			    return(s+valeur+"\n"+gauche.toString(s+"\t")+"\n");
		        }
		        else 
			if (droit!=null) 
			    return(s+valeur+"\n\n"+droit.toString(s+"\t"));
			else
			    return(s+valeur+"\n");
	    }

	   /**
	     * Affiche l'arbre selon un parcours prefixe
	     */
	    public void ParcoursPrefixe() {
			System.out.println(getValeur());
			if (getSousArbreGauche() != null) {
			    getSousArbreGauche().ParcoursPrefixe();
			}
			if (getSousArbreDroit() != null) {
			    getSousArbreDroit().ParcoursPrefixe();
			}
	    }

	   /**
	     * Affiche l'arbre selon un parcours infixe
	     */
	    public void ParcoursInfixe() {
			if (getSousArbreGauche() != null) 
			    getSousArbreGauche().ParcoursInfixe();
			System.out.println(getValeur());
			if (getSousArbreDroit() != null)
			    getSousArbreDroit().ParcoursInfixe();
	    }

	   /**
	     * Affiche l'arbre selon un parcours postfixe
	     */
	    public void ParcoursPostfixe() {
			if (getSousArbreGauche() != null)
			    getSousArbreGauche().ParcoursPostfixe();
			if (getSousArbreDroit() != null)
			    getSousArbreDroit().ParcoursPostfixe();
			System.out.println(getValeur());
	    }

	   /**
	     * Teste si deux arbres sont egaux, meme valeurs et meme disposition
	     * @param a l'arbre a comparer a b
	     * @param b l'arbre a comparer a a
	     * @return un boolean indiquant si les arbres sont egaux
	     */
	    public static boolean arbresEgaux(Arbre a, Arbre b) {
			if ((a == null) && (b == null))
			    return true;
			if ((a == null) && (b != null))
			    return false;
			if ((a != null) && (b == null))
			    return false;
	
			// A ce point, a et b != null, on peut acceder a leurs champs
			if (a.getValeur() != b.getValeur())
			    return false;
			return (arbresEgaux(a.getSousArbreGauche(), b.getSousArbreGauche()) 
				&& arbresEgaux(a.getSousArbreDroit(), b.getSousArbreDroit()));
	    }

	   /**
	    	 *  Fonction qui retourne la profondeur d'un arbre 
	     * @param a un arbre
	     * @return la hauteur de l'arbre a
	     */
	    public static int hauteur(Arbre a) {
		if (a == null)
		    return 0;
		else
		    return (1 + Math.max(hauteur(a.getSousArbreGauche()), hauteur(a.getSousArbreDroit())));
	    }


	   /**
	     * @param value la valeur a recherche dans l'arbre
	     * @return un boolean indiquant si value a ete trouve ou non
	     */
	    public boolean recherche(int value) {
			if (value == getValeur())
			    return true;
			if ((value < getValeur()) && (getSousArbreGauche() != null))
			    return (getSousArbreGauche().recherche(value));
			if ((value > getValeur()) && (getSousArbreDroit() != null))
			    return (getSousArbreDroit().recherche(value));
			return false;
	    }

	   /**
	     * @param value la valeur a inserer dans l'arbre
	     */
	    public void insertion(int value) {
			if (value == getValeur())
			    return;  // la valeur est deja dans l'arbre
			if (value < getValeur()) {
			    if (getSousArbreGauche() != null)
				getSousArbreGauche().insertion(value);
			    else
				this.gauche = new Arbre(value);
			}
			if (value > getValeur()) {
			    if (getSousArbreDroit() != null)
				getSousArbreDroit().insertion(value);
			    else 
				this.droit = new Arbre(value);
			}
	    }
	    
	    public boolean estVide() {
	    		return this.gauche == null && this.droit == null; 
	    }
}
