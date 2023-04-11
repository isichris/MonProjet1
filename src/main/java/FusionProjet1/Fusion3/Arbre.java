package FusionProjet1.Fusion3;

import java.io.File;
import java.io.IOException;

import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * La classe Arbre représente un arbre binaire de recherche contenant des nœuds
 * de type Noeud.
 */
public class Arbre {

	/**
	 * La racine de l'arbre. Un fichier binaire contenant les données de l'arbre. Un
	 * objet GestionFichier utilisé pour gérer les fichiers.
	 */
	private Noeud racine;
	private RandomAccessFile raf;
	GestionFichier fichier = new GestionFichier();

	/**
	 * Constructeur de la classe Arbre qui initialise la racine de l'arbre et ouvre
	 * un fichier binaire pour stocker les données.
	 * 
	 * @throws IOException        si une erreur d'entrée/sortie se produit lors de
	 *                            l'ouverture du fichier.
	 * @throws URISyntaxException si une erreur se produit lors de la création de
	 *                            l'URI.
	 */
	public Arbre() throws IOException, URISyntaxException {
		this.racine = new Noeud();
		URI u = getClass().getClassLoader().getResource("monFichier.bin").toURI();
		File fichierBin = new File("u");
		raf = new RandomAccessFile(fichierBin, "rw");
	}

	/**
	 * Retourne la racine de l'arbre.
	 */
	public Noeud getRacine() {
		return racine;
	}

	/**
	 * Modifie la racine de l'arbre.
	 */
	public void setRacine(Noeud racine) {
		this.racine = racine;
	}

	/**
	 * Parcourt l'arbre en mode infixe et renvoie une liste de tous les stagiaires.
	 * 
	 * @return une liste de tous les stagiaires.
	 * @throws IOException si une erreur d'entrée/sortie se produit lors de la
	 *                     lecture du fichier.
	 */
	public ArrayList<Stagiaire> affichageInfixe() throws IOException {
		ArrayList<Stagiaire> listeStagiaires = new ArrayList<>();
		if (raf.length() == 0) { //
			System.out.println("arbre vide");
		} else { // sinon lit le premier element du fichier
			raf.seek(0);
			Noeud noeudCourant = racine.lireStagiaire(raf);
			noeudCourant.affichageInfixeNoeud(raf, listeStagiaires); // appel la methode recursive depuis la racine
		}
		return listeStagiaires;
	}

	/**
	 * Ajoute un nouveau stagiaire dans l'arbre.
	 * 
	 * @param nomAjout le stagiaire à ajouter.
	 * @throws IOException si une erreur d'entrée/sortie se produit lors de
	 *                     l'écriture dans le fichier.
	 */
	public void ajouterDansArbre(Stagiaire nomAjout) throws IOException {
		// si arbre vide
		if (raf.length() == 0) {
			raf.seek(0);
			racine.ecrireStagiaire(nomAjout, raf); // ajout le nouveau stagiaire dans l'arbre.
		} else { // si l'arbre n'est pas vide
			raf.seek(0);
			Noeud noeudCourant = racine.lireStagiaire(raf); // lit le premier noeud de l'arbre
			noeudCourant.ajouterStagiaire(nomAjout, raf); // ajout le nouveau stagiaire dans l'arbre.
		}
	}

	/**
	 * Recherche les nœuds de l'arbre binaire de recherche contenant un stagiaire
	 * avec le nom.
	 * 
	 * @param string le nom à rechercher.
	 * @param raf    le fichier binaire de l'arbre binaire de recherche.
	 * @return une liste de stagiaires ayant le nom spécifié, ou null si l'arbre est
	 *         vide.
	 * @throws IOException si une erreur d'entrée/sortie se produit lors de la
	 *                     lecture dans le fichier.
	 */
	public List<Stagiaire> rechercherNoeud(Stagiaire string, RandomAccessFile raf) throws IOException {
		List<Stagiaire> resultat = new ArrayList<>();

		if (raf.length() == 0) {
			System.out.println("arbre vide");
			System.out.println("Aucun nom n'a été trouvé");
			return null;
		} else {
			raf.seek(0);
			racine = racine.lireStagiaire(raf);
			racine.rechercherStagiaire(raf, string, resultat);

		}
		return resultat;
	}

	public List<Stagiaire> rechercherSt(Stagiaire string) throws IOException {
		return rechercherNoeud(string, raf);
	}

	/**
	 * Supprime un `Stagiaire` de l'arbre en utilisant son nom comme clé.
	 *
	 * @param nom Le nom du `Stagiaire` à supprimer de l'arbre.
	 * @throws IOException Si une erreur se produit lors de la lecture du fichier.
	 */
	public void supprimerStagiaire(String nom) throws IOException {
		if (raf.length() == 0) {
			System.out.println("Cet stagiaire n'existe pas");
		} else {
			raf.seek(0);
			racine.supprimer(raf, nom, 0);
		}
	}

	/**
	 * 
	 * Cette méthode recherche des stagiaires correspondant à certains critères dans
	 * un arbre binaire de recherche représenté sous forme de fichier binaire. Elle
	 * prend en paramètres un objet de type RandomAccessFile qui représente le
	 * fichier binaire, et un objet de type Stagiaire contenant les critères de
	 * recherche. La méthode retourne une ObservableList<Stagiaire> contenant les
	 * stagiaires correspondant aux critères de recherche.
	 * 
	 * @param raf                le fichier binaire de l'arbre binaire de recherche
	 * @param stagiaireRecherche l'objet de type Stagiaire contenant les critères de
	 *                           recherche
	 * @return une ObservableList<Stagiaire> contenant les stagiaires correspondant
	 *         aux critères de recherche
	 */
	public ObservableList<Stagiaire> rechercherStagiaire(RandomAccessFile raf, Stagiaire stagiaireRecherche) {
		try {
			ObservableList<Stagiaire> stagiaire = FXCollections.observableArrayList();
			Noeud noeud = racine.lireStagiaire(raf);
			if (noeud.filsGauche != -1) {
				raf.seek(noeud.filsGauche * Noeud.TAILLE_TOTAL_OCTETS);

				stagiaire.addAll(rechercherStagiaire(raf, stagiaireRecherche));
			}

			if ((stagiaireRecherche.getNom().isBlank()
					|| noeud.getStagiaire().getNom().trim().equalsIgnoreCase(stagiaireRecherche.getNom().trim()))
					&& (stagiaireRecherche.getPrenom().isBlank() || noeud.getStagiaire().getPrenom().trim()
							.equalsIgnoreCase(stagiaireRecherche.getPrenom().trim()))
					&& (stagiaireRecherche.getLocalisation().isBlank() || noeud.getStagiaire().getLocalisation().trim()
							.equalsIgnoreCase(stagiaireRecherche.getLocalisation().trim()))
					&& (stagiaireRecherche.getFormation().isBlank() || noeud.getStagiaire().getFormation().trim()
							.equalsIgnoreCase(stagiaireRecherche.getFormation().trim()))
					&& (stagiaireRecherche.getAnneeEntree() == 0
							|| String.valueOf(noeud.getStagiaire().getAnneeEntree()).trim()
									.equalsIgnoreCase(String.valueOf(stagiaireRecherche.getAnneeEntree()).trim()))) {

				stagiaire.add(noeud.getStagiaire());
			}

			if (noeud.filsDroit != -1) {
				raf.seek(noeud.filsDroit * Noeud.TAILLE_TOTAL_OCTETS);

				stagiaire.addAll(rechercherStagiaire(raf, stagiaireRecherche));
			}
			return stagiaire;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Cette methode permet de modifier un stagiaire dans l'arbre binaire de
	 * recherche
	 * 
	 * @param stagiaireAModifier
	 * @param stagiaireAJour
	 * @param raf                le fichier dans lequel l'arbre est stocké
	 * @throws IOException si une erreur d'entrée/sortie
	 */
	public void modification(Stagiaire stagiaireAModifier, Stagiaire stagiaireAJour, RandomAccessFile raf)
			throws IOException {
		if (stagiaireAModifier.compareTo(stagiaireAJour) != 0) {
			if (raf.length() != 0) {
				racine.ajouterStagiaire(stagiaireAJour, raf);

				racine.supprimer(raf, null, 0);

			}
		}
	}

	/**
	 * 
	 * Affiche les stagiaires stockés dans l'arbre dans une TableView.
	 * 
	 * @param arbre     l'arbre contenant les stagiaires à afficher
	 * @param tableView la TableView dans laquelle afficher les stagiaires
	 * @throws IOException si une erreur se produit lors de la récupération des
	 *                     stagiaires depuis l'arbre
	 */
	public void afficherStagiaires(Arbre arbre, TableView<Stagiaire> tableView) throws IOException {

		List<Stagiaire> stagiaires = arbre.affichageInfixe();

		for (Stagiaire stagiaire : stagiaires) {
			tableView.getItems().add(stagiaire);
		}
	}

}
