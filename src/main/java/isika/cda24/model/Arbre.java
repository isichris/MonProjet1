package isika.cda24.model;

public class Arbre {

	private Noeud racine;

	public Arbre() {
		this.racine = null;
	}

	public void ajouterDansArbre(Stagiaire nomAjout) {
		// si arbre vide
		if (racine == null) {
			racine = new Noeud(nomAjout);
		} else {
			this.racine.ajouterNoeud(nomAjout);
		}
	}

	public void affichageInfixe() {
		if (racine == null) {
			System.out.println("arbre vide");
		} else {
			this.racine.affichageInfixeNoeud();
		}
	}

	public Noeud rechercherStagiaire(String nom) {
		return rechercherNoeud(racine, nom);
	}

	private Noeud rechercherNoeud(Noeud noeud, String nom) {
		if (noeud == null) {
			return null;
		} else if (noeud.getStagiaire().getNom().equalsIgnoreCase(nom)) {
			return noeud;
		} else if (nom.compareTo(noeud.getStagiaire().getNom()) < 0) {
			return rechercherNoeud(noeud.getFilsGauche(), nom);
		} else {
			return rechercherNoeud(noeud.getFilsDroit(), nom);
		}
	}
	
	public void afficherInfoStagiaire(String nom) {
	    Noeud noeud = rechercherStagiaire(nom);
	    if (noeud != null) {
	        Stagiaire stagiaire = noeud.getStagiaire();
	        System.out.println("Nom: " + stagiaire.getNom());
	        System.out.println("Prénom: " + stagiaire.getPrenom());
	        System.out.println("Localisation: " + stagiaire.getLocalisation());
	        System.out.println("Formation: " + stagiaire.getFormation());
	        System.out.println("Année d'entrée: " + stagiaire.getAnneeEntree());
	    } else {
	        System.out.println("Stagiaire non trouvé !");
	    }
	}


}
