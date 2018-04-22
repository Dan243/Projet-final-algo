package Application;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Classe d'arbres d'arbres binaires de recherche à rebours 
 * @author Etonam et Dan
 *
 */

public class AABRR {

	private ArbreBinaire arbre;
	private int petitM;
	private int grandM;
	private AABRR sag;
	private AABRR sad;
	private boolean arbreVide;

	public AABRR() {
		this.arbre = null;
		this.petitM = 0;
		this.grandM = 0;
		this.arbreVide = false;
	}

	/**
	 * Construit un nouvel AABRR, en dÃ©finissant s'il est vide ou non
	 * @param b true pour construire un arbre vide
	 */
	public AABRR(boolean b) {
		this.arbreVide = b;
	}

	/**
	 * Retourne la valeur du arbre
	 * @return
	 */
	public ArbreBinaire getArbre() {
		return this.arbre;
	}

	public void setArbre(ArbreBinaire arbre) {
		this.arbre = arbre;
	}

	public AABRR getSag() {
		return sag;
	}

	public void setSag(AABRR sag) {
		this.sag = sag;
	}

	public AABRR getSad() {
		return sad;
	}

	public void setSad(AABRR sad) {
		this.sad = sad;
	}

	public int getPetitM() {
		return petitM;
	}

	public void setPetitM(int petitM) {
		this.petitM = petitM;
	}

	public int getGrandM() {
		return grandM;
	}

	public void setGrandM(int grandM) {
		this.grandM = grandM;
	}

	public boolean isArbreVide() {
		return arbreVide;
	}

	public void setArbreVide(boolean arbreVide) {
		this.arbreVide = arbreVide;
	}
	

	/**
	 * Insère un noeud dans un AABRI
	 * @param a, l'arbre dans lequel insérer
	 * @param noeudAInserer, le noeud à insérer
	 */
	public void insererNoeudAABRR(AABRR a, AABRR noeudAInserer) {
		if (a.isArbreVide()) {
			a.setGrandM(noeudAInserer.getGrandM());
			a.setPetitM(noeudAInserer.getPetitM());
			a.setArbre(noeudAInserer.getArbre());
			a.setArbreVide(false);

			// feuilles arbres vides
			a.setSad(new AABRR(true));
			a.setSag(new AABRR(true));
		} else {
			if (noeudAInserer.getPetitM() < a.getPetitM()) {
				insererNoeudAABRR(a.getSag(), noeudAInserer);
			} else if (noeudAInserer.getPetitM() > a.getPetitM()) {
				insererNoeudAABRR(a.getSad(), noeudAInserer);
			}
		}
	}
	
	/**
	 * Effectue un tirage aléatoire de m et M
	 * 
	 * @param forbiddenValues
	 *            , l'ensemble des valeurs appartenant aux intervalles déjà
	 *            tirés
	 * @param maxM
	 *            , le nb max, saisi par l'utilisateur
	 * @param nbNoeudsRestants
	 *            , nb de noeuds restants à tirer
	 * @return un tableau de taille 2, contenant m à l'indice 0 et M à l'indice
	 *         1
	 */
	public int[] tirerPetitEtGrandM(Set<Integer> forbiddenValues,
			int maxM, int nbNoeudsRestants) {
		Random generator = new Random();
		boolean tirageOk = false;
		int[] tirage = new int[2];
		int petitM = -1;
		int grandM = -1;
		while (!tirageOk) {

			int alea1 = 1 + generator.nextInt(maxM);
			int alea2 = 1 + generator.nextInt(maxM);

			petitM = Math.min(alea1, alea2);
			grandM = Math.max(alea1, alea2);

			tirageOk = (petitM != grandM)
					&& (petitM < grandM)
					&& isDistinctInterval(getIntervalValues(petitM, grandM),
							forbiddenValues)
					&& (grandM - petitM) <= (maxM - forbiddenValues.size())
							/ nbNoeudsRestants;
		}

		tirage[0] = petitM;
		tirage[1] = grandM;

		return tirage;
	}

	/**
	 * Retourne un ensemble d'entiers contenant toutes les valeurs comprises entre les deux nombre passés en paramètre
	 * @param start la borne inférieure de l'intervalle
	 * @param end la borne supérieure de l'intervalle
	 * @return
	 */
	public Set<Integer> getIntervalValues(int start, int end) {
		Set<Integer> res = new HashSet<Integer>();
		for (int i = start; i <= end; i++) {
			res.add(i);
		}
		return res;
	}

	/**
	 * Détermine si les ensembles passés en paramètres sont entièrement disjoint
	 * @param int1 le premier ensemble
	 * @param int2 le second ensemble
	 * @return true si les int1 et int2 sont entièrement disjoints, false sinon
	 */
	private static boolean isDistinctInterval(Set<Integer> int1,
			Set<Integer> int2) {
		for (Integer i : int1) {
			if (int2.contains(i)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Génère un tableau d'entiers compris entre min et max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public Integer[] generateIntegerTab(int min, int max) {
		// générateur de nombres aléatoires
		Random generator = new Random();
		int taille = 1 + generator.nextInt(max - min);
		Set<Integer> set = new HashSet<Integer>();
		Integer[] res = new Integer[taille];
		for (int i = 0; i < taille; i++) {
			Integer temp = generator.nextInt((max - min) + 1) + min;
			while (set.contains(temp)) {
				temp = generator.nextInt((max - min) + 1) + min;
			}
			set.add(temp);
		}
		res = set.toArray(new Integer[taille]);
		return res;
	}
}
