package testProjet1.testProjet1;

public class Stagiaire {
	private String nom;
	private String prenom;
	private String departement;
	private String formation;
	private int anneeInscription;

	/**
	 * Constructeur pour créer une nouvelle instance de Stagiaire.
	 *
	 * @param nom              Le nom du stagiaire
	 * @param prenom           Le prénom du stagiaire
	 * @param departement      Le département du stagiaire
	 * @param formation        La formation du stagiaire
	 * @param anneeInscription L'année d'inscription du stagiaire
	 */
	public Stagiaire(String nom, String prenom, String departement, String formation, int anneeInscription) {
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.formation = formation;
		this.anneeInscription = anneeInscription;
	}

	// Getter et setter pour le nom du stagiaire
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	// Getter et setter pour le prénom du stagiaire
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	// Getter et setter pour le département du stagiaire
	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	// Getter et setter pour la formation du stagiaire
	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	// Getter et setter pour l'année d'inscription du stagiaire
	public int getAnneeInscription() {
		return anneeInscription;
	}

	public void setAnneeInscription(int anneeInscription) {
		this.anneeInscription = anneeInscription;
	}
}