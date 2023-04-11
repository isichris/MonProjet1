package FusionProjet1.Fusion3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.net.URI;

import java.util.ArrayList;

public class GestionFichier {


	public ArrayList<Stagiaire> lireListStagiaireTxt() {

		ArrayList<Stagiaire> listeStagiaire = new ArrayList<>(); // Créer une liste pour stocker les stagiaires lus
																	// depuis le fichier texte

		try {
			URI u = getClass().getClassLoader().getResource("monFichier.txt").toURI();
			File file = new File(u);
			FileReader fr = new FileReader(file); // Créer un objet
													// FileReader pour lire
													// le contenu du fichier
													// texte
			BufferedReader br = new BufferedReader(fr); // Créer un objet BufferedReader pour lire les données ligne par
														// ligne

			String line; // Créer une variable pour stocker chaque ligne lue

			while ((line = br.readLine()) != null) { // lire le contenu du fichier texte ligne par ligne. La boucle
														// while continue à s'exécuter tant que la méthode readLine() ne
														// renvoie pas "null", ce qui signifie que toutes les lignes du
														// fichier texte ont été lues.
				if (line.equals("*")) { // si la chaîne de caractères "line" est égale à l'expression "*". Si c'est le
										// cas, alors la ligne "---------------" est affichée dans la console.
					System.out.println("--------------------");
				} else {
					String nom = line;
					String prenom = br.readLine();
					String localisation = br.readLine();
					String formation = br.readLine();
					int anneeEntree = Integer.parseInt(br.readLine());

					Stagiaire s = new Stagiaire(nom, prenom, localisation, formation, anneeEntree);
					// Afficher les valeurs
					System.out.println("Nom : " + nom);
					System.out.println("Prenom : " + prenom);
					System.out.println("Localisation : " + localisation);
					System.out.println("Formation : " + formation);
					System.out.println("Annee : " + anneeEntree);

					listeStagiaire.add(s); // Ajouter l'objet Stagiaire à la liste de stagiaires
				}
			}

			// Fermer les objets BufferedReader et FileReader
			br.close();
			fr.close();
		} catch (Exception e) {
			System.out.println("probleme");
			e.printStackTrace();
		}
		return listeStagiaire;
	}

//			while (br.ready()) {
//				
//				stagiaire.setNom(br.readLine());
//				stagiaire.setPrenom(br.readLine());
//				stagiaire.setLocalisation(br.readLine());
//				stagiaire.setFormation(br.readLine());
//				int annee = Integer.parseInt(br.readLine());
//				stagiaire.setAnneeEntree(annee);
//				br.readLine();
//				
//			}

}
//File fichier = new File("src/main/java/isika/cda24/model/fichier");
//
//fichier.mkdir();