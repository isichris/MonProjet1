package testProjet1.testProjet1;

import javafx.stage.FileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImporterListe {
	/**
	 * Importe une liste de stagiaires à partir d'un fichier texte.
	 *
	 * @return une liste de stagiaires
	 */
	public List<Stagiaire> importerListeStagiaires() {
		// Crée une liste vide pour stocker les stagiaires
		List<Stagiaire> stagiaires = new ArrayList<>();

		// Crée un FileChooser pour permettre à l'utilisateur de sélectionner un fichier
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Sélectionner le fichier annuaire");
		File file = fileChooser.showOpenDialog(null);

		// Si un fichier a été sélectionné
		if (file != null) {
			// Utilisez un BufferedReader pour lire le fichier ligne par ligne
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				// Continuez à lire tant qu'il y a des lignes dans le fichier
				while ((line = br.readLine()) != null) {
					// Pour chaque stagiaire, lisez les informations nécessaires
					String nom = line;
					String prenom = br.readLine();
					String departement = br.readLine();
					String formation = br.readLine();
					int anneeInscription = Integer.parseInt(br.readLine());
					br.readLine(); // Lire et ignorer le séparateur "*"

					// Crée un objet Stagiaire et l'ajoute à la liste des stagiaires
					Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, formation, anneeInscription);
					stagiaires.add(stagiaire);
				}
			} catch (IOException e) {
				// Affiche les erreurs éventuelles lors de la lecture du fichier
				e.printStackTrace();
			}
		}
		// Retourne la liste des stagiaires
		return stagiaires;
	}
}