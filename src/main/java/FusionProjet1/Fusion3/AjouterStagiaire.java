package FusionProjet1.Fusion3;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Classe AjouterStagiaire permettant d'afficher une interface pour ajouter un
 * nouveau stagiaire à une TableView.
 */
public class AjouterStagiaire {

	/**
	 * Affiche une interface graphique permettant d'ajouter un nouveau stagiaire à
	 * la TableView passée en paramètre.
	 * 
	 * @param tableView La TableView à laquelle ajouter le nouveau stagiaire.
	 */
	public void showAddInterface(TableView<Stagiaire> tableView) {

		// Création d'une nouvelle fenêtre pour l'interface d'ajout
		Stage addStage = new Stage();
		addStage.initModality(Modality.APPLICATION_MODAL);
		addStage.setTitle("Ajouter un stagiaire");

		// Création du formulaire d'ajout avec des champs pour le nom, le prénom, le
		// département, la formation et l'année d'inscription
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setAlignment(Pos.CENTER);

		Label nomLabel = new Label("Nom :");
		TextField nomTextField = new TextField();
		gridPane.add(nomLabel, 0, 0);
		gridPane.add(nomTextField, 1, 0);

		Label prenomLabel = new Label("Prénom :");
		TextField prenomTextField = new TextField();
		gridPane.add(prenomLabel, 0, 1);
		gridPane.add(prenomTextField, 1, 1);

		Label localisationLabel = new Label("Département :");
		TextField localisationTextField = new TextField();
		gridPane.add(localisationLabel, 0, 2);
		gridPane.add(localisationTextField, 1, 2);

		Label formationLabel = new Label("Formation :");
		TextField formationTextField = new TextField();
		gridPane.add(formationLabel, 0, 3);
		gridPane.add(formationTextField, 1, 3);

		Label anneeEntreeLabel = new Label("Année d'inscription :");
		TextField anneeEntreeTextField = new TextField();
		gridPane.add(anneeEntreeLabel, 0, 4);
		gridPane.add(anneeEntreeTextField, 1, 4);

		// Création des boutons Soumettre et Retour
		Button soumettreBtn = new Button("Soumettre");
		soumettreBtn.setOnAction(event -> {
			// Récupération des informations du formulaire pour créer un nouveau stagiaire
			Stagiaire nouveauStagiaire = new Stagiaire(nomTextField.getText(), prenomTextField.getText(),
					localisationTextField.getText(), formationTextField.getText(),
					Integer.parseInt(anneeEntreeTextField.getText()));
			// Ajout du nouveau stagiaire à l'arbre et mise à jour de la TableView
			addStage.close();
			Arbre arbre;
			try {
				arbre = new Arbre();
				arbre.ajouterDansArbre(nouveauStagiaire);
				tableView.setItems(FXCollections.observableList(arbre.affichageInfixe()));

			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}

		});

		Button retourBtn = new Button("Retour");
		retourBtn.setOnAction(event -> addStage.close());

		// Ajout des boutons Soumettre et Retour dans une HBox
		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(soumettreBtn, retourBtn);
		gridPane.add(hbox, 0, 5, 2, 1);

		addStage.setScene(new Scene(gridPane, 400, 300));
		addStage.show();
	}
}