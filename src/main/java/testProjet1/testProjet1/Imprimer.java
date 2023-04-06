package testProjet1.testProjet1;

import javafx.collections.ObservableList;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import testProjet1.testProjet1.Stagiaire;

public class Imprimer {
	private ObservableList<Stagiaire> annuaire;

	public Imprimer(ObservableList<Stagiaire> annuaire) {
		this.annuaire = annuaire;
	}

	/**
	 * Imprime l'annuaire des stagiaires.
	 */
	public void imprimerAnnuaire() {
		// Récupère l'imprimante par défaut
		Printer printer = Printer.getDefaultPrinter();
		if (printer != null) {
			PrinterJob printerJob = PrinterJob.createPrinterJob(printer);
			// Affiche la boîte de dialogue d'impression et vérifie si l'utilisateur a
			// confirmé l'impression
			if (printerJob != null && printerJob.showPrintDialog(null)) {
				// Crée un TextFlow avec le contenu à imprimer
				TextFlow textFlow = new TextFlow();
				textFlow.getChildren().add(new Text("Annuaire des stagiaires\n\n"));
				// Ajoute le texte de chaque stagiaire à imprimer
				for (Stagiaire stagiaire : annuaire) {
					textFlow.getChildren().add(new Text(stagiaire.toString() + "\n"));
				}

				// Imprime le contenu
				printerJob.printPage(textFlow);
				// Termine le travail d'impression
				printerJob.endJob();
			}
		} else {
			// Affiche un message d'erreur si aucune imprimante n'a été trouvée
			System.out.println("Aucune imprimante trouvée.");
		}
	}
}