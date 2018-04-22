package Application;

/**
 * Cette classe représente les messages à afficher sur notre application 
 * @author Etonam et Dan
 */
public class Constantes {

	public interface ItemsMenu {
		final String CHARGER = "/import";
		final String EXPORTER = "/export";
		final String AFFICHER = "/afficher";
	}

	public interface LibellesBoutons {
		final String CHARGER = "Importer un fichier";
		final String EXPORTER = "Exporter un AABRR";
		final String ALEATOIRE = "Créer un AABRR aléatoire";
		final String AFFICHER = " Afficher ";
		final String VERIFIER = "Vérifier un AABRR";
		final String INSERER = "Insérer un entier";
		final String SUPPRIMER = "Supprimer un entier";
		final String RECHERCHER = "Rechercher un entier";
		final int TAILLE_MAX_LIBELLE = 60;
	}

	public interface TypeFils {
		final String GAUCHE = "Fils gauche";
		final String DROIT = "Fils droit";
		final String RACINE = "Racine";
	}

	final int MAX_TAILLE_ABRI = 20;
}
