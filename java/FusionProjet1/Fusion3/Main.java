package FusionProjet1.Fusion3;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;







public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException {

		GestionFichier fichier = new GestionFichier(); // instancie la classe GestionFichier

//		List<Stagiaire> stagiaires = fichier.lireListStagiaireTxt(); // appelle la methode lireListStagiaireTxt et
																		// stocke le resultat dans une list Stagiaire

		Arbre arbre = new Arbre(); // instancier l'objet Arbre
		
//		 ajouter dans l'arbre
//		for (Stagiaire stagiaire : stagiaires) { // parcourt chaque éelement de la liste stagiaires et stocke dans la
//													// variable stagiaire
//			arbre.ajouterDansArbre(stagiaire); // appelle la methode ajouterDansArbre, donc chaque Stagiaire de la
//													// liste stagiaires sera ajouté à l'arbre
//		}

		
		// appelle la methode affichageInfixe de l'objet arbre et stocke le resultat
		// dans une ArrayList de Stagiaire nommée listeStagiaires
		ArrayList<Stagiaire> listeStagiaires = arbre.affichageInfixe(); 

		for (Stagiaire stgr : listeStagiaires) { // parcourt tous les éléments de l'objet listeStagiaires qui est une
													// ArrayList de Stagiaire et stocke chaque element dans la variable
													// stag
			System.out.println(stgr);
		}
		
		
		// methode rechercher par nom
//		List<Stagiaire> stagiairesRecherche = arbre.rechercherNoeud(new Stagiaire("GARIJO", null, null, null, 0),raf);
//
//		for (Stagiaire stag : stagiairesRecherche) {
//			System.out.println(stag);
//		}
		
		
		// methode rechercher multicriteres
//		ObservableList<Stagiaire> stagiairesRechercher = arbre.rechercherStagiaire(raf, new Stagiaire("potin", "", "", "", 0));
//		
//		for (Stagiaire stag : stagiairesRechercher) {
//			System.out.println(stag);
//		}
		
		
		//methode modifier
//		arbre.modification(new Stagiaire("POTIN", "Thomas", "75", "ATOD 21", 2014), new Stagiaire("POTUN", "Thomas", "75", "ATOD 21", 2014), raf);
		
		


		System.out.println("-------------------------------SUPPRESSION------------------------");
//		arbre.supprimerStagiaire("UNG");
		ArrayList<Stagiaire> listeStagiairesSupp = arbre.affichageInfixe(); // appelle la methode affichageInfixe de
		// l'objet arbre et stocke le resultat dans
		// une ArrayList de Stagiaire
		// nommée listeStagiaires

		for (Stagiaire stgr : listeStagiairesSupp) { // parcourt tous les éléments de l'objet listeStagiaires qui est
														// une
			System.out.println(stgr);
		}



	}
}
