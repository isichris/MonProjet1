package FusionProjet1.Fusion3;

public class Stagiaire {

    private String nom;
    private String prenom;
    private String localisation;
    private String formation;
    private int anneeEntree;

    // On définit des constantes pour les tailles maximales des attributs
    public static final int TAILLE_MAX_NOM = 21;
    public static final int TAILLE_MAX_PRENOM = 20;
    public static final int TAILLE_MAX_LOCALISATION = 2;
    public static final int TAILLE_MAX_FORMATION = 10;
    public static final int TAILLE_MAX_OCTET_STAGIAIRE = 53 * 2 + 4;

    // On crée un constructeur pour initialiser les attributs d'un stagiaire
    public Stagiaire(String nom, String prenom, String localisation, String formation, int anneeEntree) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.localisation = localisation;
        this.formation = formation;
        this.anneeEntree = anneeEntree;
    }

    // On crée un constructeur par défaut sans paramètres
    public Stagiaire() {
        // TODO Auto-generated constructor stub
    }

    // On récupère le nom du stagiaire
    public String getNom() {
        return nom;
    }

    // On définit le nom du stagiaire
    public void setNom(String nom) {
        this.nom = nom;
    }

    // On récupère le prénom du stagiaire
    public String getPrenom() {
        return prenom;
    }

    // On définit le prénom du stagiaire
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // On récupère la localisation du stagiaire
    public String getLocalisation() {
        return localisation;
    }

    // On définit la localisation du stagiaire
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    // On récupère la formation du stagiaire
    public String getFormation() {
        return formation;
    }

    // On définit la formation du stagiaire
    public void setFormation(String formation) {
        this.formation = formation;
    }

    // On récupère l'année d'entrée du stagiaire
    public int getAnneeEntree() {
        return anneeEntree;
    }

    // On définit l'année d'entrée du stagiaire
    public void setAnneeEntree(int anneeEntree) {
        this.anneeEntree = anneeEntree;
    }

    // On convertit les informations du stagiaire en chaîne de caractères
    @Override
    public String toString() {
        return " Stagiaire : [nom=" + nom + ", prenom=" + prenom + ", localisation=" + localisation + ", formation="
                + formation + ", anneeEntree=" + anneeEntree + "]";
    }
    // On compare deux stagiaires en fonction de leur nom
    public int compareTo(Stagiaire autreStagiaire) {

        if (this.nom.compareToIgnoreCase(autreStagiaire.nom) == 0) {
            return 0;

        } else {

            return this.nom.compareToIgnoreCase(autreStagiaire.nom);
        }

    }

    // On récupère le nom du stagiaire avec une longueur fixe (remplie d'espaces si nécessaire)
    public String getNomLong() {
        String valeur = getNom();
        for (int i = getNom().length(); i < TAILLE_MAX_NOM; i++) {
            valeur += " ";
        }
        return valeur;
    }

    // On récupère le prénom du stagiaire avec une longueur fixe (remplie d'espaces si nécessaire)
    public String getPrenomLong() {
        String valeur = getPrenom();
        for (int i = getPrenom().length(); i < TAILLE_MAX_PRENOM; i++) {
            valeur += " ";
        }
        return valeur;
    }

    // On récupère la localisation du stagiaire avec une longueur fixe (remplie d'espaces si nécessaire)
    public String getLocalisationLong() {
        String valeur = getLocalisation();
        for (int i = getLocalisation().length(); i < TAILLE_MAX_LOCALISATION; i++) {
            valeur += " ";
        }
        return valeur;
    }

    // On récupère la formation du stagiaire avec une longueur fixe (remplie d'espaces si nécessaire)
    public String getFormationLong() {
        String valeur = getFormation();
        for (int i = getFormation().length(); i < TAILLE_MAX_FORMATION; i++) {
            valeur += " ";
        }
        return valeur;
    }

}