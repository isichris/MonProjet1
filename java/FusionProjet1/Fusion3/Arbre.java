package FusionProjet1.Fusion3;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Arbre {

	private Noeud racine;
	private RandomAccessFile raf;
	GestionFichier fichier = new GestionFichier();

	public Arbre() throws IOException, URISyntaxException {
		this.racine = new Noeud();
		URI u = getClass().getClassLoader().getResource("monFichier.bin").toURI();
//		InputStream inputStreamBin = getClass().getResourceAsStream("/monFichier.bin");
		File fichierBin = new File("u");
		raf = new RandomAccessFile(fichierBin, "rw");
	}

	public Noeud getRacine() {
		return racine;
	}

	public void setRacine(Noeud racine) {
		this.racine = racine;
	}

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

	public void supprimerStagiaire(String nom) throws IOException {
		if (raf.length() == 0) {
			System.out.println("Cet stagiaire n'existe pas");
		} else {
			raf.seek(0);
			// Noeud noeudCourant = racine.lireStagiaire(raf);
			racine.supprimer(raf, nom, 0);

			// noeudCourant.supprimerNoeud(noeudCourant, raf, 0);

		}

	}

	// Cette methode renvoie une liste observable de Stagiaires
	public ObservableList<Stagiaire> rechercherStagiaire(RandomAccessFile raf, Stagiaire stagiaireRecherche) {
		try {
			ObservableList<Stagiaire> stagiaire = FXCollections.observableArrayList(); // Une nouvelle liste observable
																						// de Stagiaires est créée à
																						// partir de la classe
																						// FXCollections de JavaFX.
			Noeud noeud = racine.lireStagiaire(raf); // lit le noeud courant
			if (noeud.filsGauche != -1) { // Si le nœud courant a un filsGauche
				raf.seek(noeud.filsGauche * Noeud.TAILLE_TOTAL_OCTETS); // on deplace le
				// curseur à la position du fils gauche

				stagiaire.addAll(rechercherStagiaire(raf, stagiaireRecherche)); // la methode "rechercheStagiaire" est
																				// appelée récursivement et La méthode
																				// "addAll" ajoute
																				// tous les éléments de la liste
																				// observable retournée par la méthode
																				// "rechercheStagiaire"
			}

			// Cette condition vérifie si un noeud dans un arbre binaire de recherche
			// contient toutes les informations de recherche d'un stagiaire. Si la condition
			// est vraie, alors le stagiaire stocké dans le noeud est ajouté à une liste
			// observable. Le code commence par vérifier si l'attribut nom de
			// stagiaireRecherche est vide (isBlank()) ou si la valeur de l'attribut nom de
			// l'objet noeud.getStagiaire() est égale à la valeur de l'attribut nom de
			// stagiaireRecherche, après avoir supprimé les espaces de début et de fin
			// (trim()) de ces deux valeurs. Si c'est le cas, alors la première
			// sous-condition est vraie.

			// La condition vérifie ensuite les autres attributs (prenom, departement,
			// nomPromo, et anneePromo) de la même manière que pour l'attribut nom.

			if ((stagiaireRecherche.getNom().isBlank() // verifie si le nom du stagiaire est vide ou s'il correspond au
														// nom du stagiaire stocké dans le noeud
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
				// ici on converti int
				// (anneeEntree) en String
				// avec la methode
				// String.valueOf

				// Si toutes les sous-conditions sont vraies, alors cela signifie que l'objet
				// noeud.getStagiaire() correspond aux critères de recherche et il
				// est ajouté à la liste stagiaire via la méthode add().

				stagiaire.add(noeud.getStagiaire());
			}

			if (noeud.filsDroit != -1) { // Si le nœud courant a un filsGauche
				raf.seek(noeud.filsDroit * Noeud.TAILLE_TOTAL_OCTETS); // on deplace le
																		// curseur à la position du fils gauche
				stagiaire.addAll(rechercherStagiaire(raf, stagiaireRecherche)); // // la methode "rechercheStagiaire"
																				// est
																				// appelée récursivement et La méthode
																				// "addAll" ajoute
																				// tous les éléments de la liste
																				// observable retournée par la méthode
																				// "rechercheStagiaire"
			}
			return stagiaire; // retourne la liste observable des stagiaires
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void modification(Stagiaire stagiaireAModifier, Stagiaire stagiaireAJour, RandomAccessFile raf)
			throws IOException {
		if (stagiaireAModifier.compareTo(stagiaireAJour) != 0) { // compare les 2 objets de la classe, Si cette méthode
																	// renvoie une valeur différente de zéro, cela
																	// signifie que les deux objets sont différents et
																	// que la condition est vraie.
			if (raf.length() != 0) { // si la fichier n'est pas vide
				racine.ajouterStagiaire(stagiaireAJour, raf); // appelle la methode ajouter pour ajouter le stagiaire
																// avec les
				// nouvelles informations

				racine.supprimer(raf, null, 0); // appelle la methode supprimer pour supprimer l'ancien stagiaires

			}
		}
	}

	public void afficherStagiaires(Arbre arbre, TableView<Stagiaire> tableView) throws IOException {
		// Récupérer une liste triée de tous les stagiaires dans l'arbre en utilisant la
		// méthode affichageInFixe()
		List<Stagiaire> stagiaires = arbre.affichageInfixe();

		// Ajouter chaque stagiaire dans le TableView
		for (Stagiaire stagiaire : stagiaires) {
			tableView.getItems().add(stagiaire);
		}
	}

}
