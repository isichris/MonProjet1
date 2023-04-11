package FusionProjet1.Fusion3;

import FusionProjet1.Fusion3.Stagiaire;
import javafx.collections.ObservableList;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public class Imprimer {
    private ObservableList<Stagiaire> annuaire;

    // On crée le constructeur de la classe Imprimer
    public Imprimer(ObservableList<Stagiaire> annuaire) {
        this.annuaire = annuaire;
    }

    /**
     * Imprime l'annuaire des stagiaires.
     */
    // On crée la méthode pour imprimer l'annuaire
    public void imprimerAnnuaire() {
        // On récupère l'imprimante par défaut
        Printer printer = Printer.getDefaultPrinter();
        if (printer != null) {
            PrinterJob printerJob = PrinterJob.createPrinterJob(printer);
            // On affiche la boîte de dialogue d'impression et vérifie si l'utilisateur a confirmé l'impression
            if (printerJob != null && printerJob.showPrintDialog(null)) {
                // On crée une GridPane avec les en-têtes de colonnes
                GridPane gridPane = new GridPane();
                gridPane.setHgap(10);
                gridPane.setVgap(5);
                gridPane.addRow(0, new Text("Nom"), new Text("Prénom"), new Text("Département"), new Text("Formation"), new Text("Année d'inscription"));
                gridPane.getRowConstraints().add(new RowConstraints(30));

                // On ajoute le texte de chaque stagiaire à imprimer dans la GridPane
                int row = 1;
                for (Stagiaire stagiaire : annuaire) {
                    Text nom = new Text(stagiaire.getNom());
                    Text prenom = new Text(stagiaire.getPrenom());
                    Text localisation = new Text(stagiaire.getLocalisation());
                    Text formation = new Text(stagiaire.getFormation());
                    Text annee = new Text(String.valueOf(stagiaire.getAnneeEntree()));

                    gridPane.addRow(row, nom, prenom, localisation, formation, annee);
                    row++;

                    // On ajoute une deuxième page si nécessaire
                    if (row % 38 == 0) {
                        printerJob.printPage(gridPane);
                        gridPane.getChildren().clear();
                        gridPane.addRow(0, new Text("Nom"), new Text("Prénom"), new Text("Département"), new Text("Formation"), new Text("Année d'inscription"));
                        gridPane.getRowConstraints().add(new RowConstraints(30));
                        row = 1;
                    }
                }

                // On imprime le contenu
                printerJob.printPage(gridPane);
                // On termine le travail d'impression
                printerJob.endJob();
            }
        } else {
            // On affiche un message d'erreur si aucune imprimante n'a été trouvée
            System.out.println("Aucune imprimante trouvée.");
        }
    }
}