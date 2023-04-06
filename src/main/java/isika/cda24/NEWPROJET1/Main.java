package isika.cda24.NEWPROJET1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import isika.cda24.model.Arbre;
import isika.cda24.model.Stagiaire;

public class Main {

	public static void main(String[] args) {
		Arbre arbre = new Arbre();
		

		try {
			File fichier = new File("src/main/java/isika/cda24/model/monFichier.txt");
			Scanner scanner = new Scanner(fichier);

			while (scanner.hasNextLine()) {
                String nom = scanner.nextLine();
                String prenom = scanner.nextLine();
                String localisation = scanner.nextLine();
                String formation = scanner.nextLine();
                int anneeEntree = Integer.parseInt(scanner.nextLine());
                scanner.nextLine(); // Ignorer la ligne "*"

                Stagiaire stagiaire = new Stagiaire(nom, prenom, localisation, formation, anneeEntree);
                arbre.ajouterDansArbre(stagiaire);
            }

			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'a pas été trouvé.");
			e.printStackTrace();
		}
				
		arbre.afficherInfoStagiaire("");
		
		
        arbre.affichageInfixe();
		
	}
	

}
