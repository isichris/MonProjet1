package FusionProjet1.Fusion3;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StagiaireTableView extends TableView<Stagiaire> {

	/**
	 * Cette TableView contient cinq colonnes : Nom, Prénom, Département, Formation
	 * et Année d'inscription. Les données sont obtenues à partir d'une liste
	 * d'objets Stagiaire.
	 */
	public StagiaireTableView() {
		TableColumn<Stagiaire, String> nomColonne = new TableColumn<>("Nom");
		nomColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
		nomColonne.setMinWidth(100);

		TableColumn<Stagiaire, String> prenomColonne = new TableColumn<>("Prenom");
		prenomColonne.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		prenomColonne.setMinWidth(100);

		TableColumn<Stagiaire, String> departementColonne = new TableColumn<>("Departement");
		departementColonne.setCellValueFactory(new PropertyValueFactory<>("localisation"));
		departementColonne.setMinWidth(100);

		TableColumn<Stagiaire, String> formationColonne = new TableColumn<>("Formation");
		formationColonne.setCellValueFactory(new PropertyValueFactory<>("formation"));
		formationColonne.setMinWidth(100);

		TableColumn<Stagiaire, Integer> anneeInscriptionColonne = new TableColumn<>("Année d'inscription");
		anneeInscriptionColonne.setCellValueFactory(new PropertyValueFactory<>("anneeEntree"));
		anneeInscriptionColonne.setMinWidth(100);

		getColumns().addAll(nomColonne, prenomColonne, departementColonne, formationColonne, anneeInscriptionColonne);

		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
}