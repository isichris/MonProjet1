package isika.cda24.model;

public class Noeud {
	private Stagiaire stagiaire;
	private Noeud filsGauche;
	private Noeud filsDroit;

	public Noeud(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
		this.filsGauche = null;
		this.filsDroit = null;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Noeud getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}

	public Noeud getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	}

	public void ajouterNoeud(Stagiaire stagiaireAjout) {
		int comparaison = this.stagiaire.compareTo(stagiaireAjout);

		if (comparaison > 0) {
			if (this.filsGauche == null) { 
				this.filsGauche = new Noeud(stagiaireAjout);
			} else {
				this.filsGauche.ajouterNoeud(stagiaireAjout);
			}
		} else {
			if (this.filsDroit == null) {
				this.filsDroit = new Noeud(stagiaireAjout);
			} else {
				this.filsDroit.ajouterNoeud(stagiaireAjout);
			}
		}
	}

	public void affichageInfixeNoeud() {
		if (this.filsGauche != null) {
			this.filsGauche.affichageInfixeNoeud();
		}
		System.out.println(this.stagiaire);
		if (this.filsDroit != null) {
			this.filsDroit.affichageInfixeNoeud();
		}
	}

	@Override
	public String toString() {
		return "Noeud [stagiaire=" + stagiaire + ", filsGauche=" + filsGauche + ", filsDroit=" + filsDroit + "]";
	}
	
	
}
