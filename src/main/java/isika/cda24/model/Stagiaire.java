package isika.cda24.model;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Stagiaire {

	private String nom;
	private String prenom;
	private String localisation;
	private String formation;
	private int anneeEntree;

	public Stagiaire(String nom, String prenom, String localisation, String formation, int anneeEntree) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.localisation = localisation;
		this.formation = formation;
		this.anneeEntree = anneeEntree;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public int getAnneeEntree() {
		return anneeEntree;
	}

	public void setAnneeEntree(int anneeEntree) {
		this.anneeEntree = anneeEntree;
	}

	@Override
	public String toString() {
		return " [nom=" + nom + ", prenom=" + prenom + ", localisation=" + localisation + ", formation=" + formation
				+ ", anneeEntree=" + anneeEntree + "]";
	}

	public int compareTo(Stagiaire monStagiaire) {

		if (this.nom.compareToIgnoreCase(monStagiaire.nom) == 0) {
			return 0;

		} else {

			return this.nom.compareToIgnoreCase(monStagiaire.nom);
		}

	}

}
