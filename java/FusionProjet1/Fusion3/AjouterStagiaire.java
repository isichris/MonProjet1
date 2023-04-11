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

public class AjouterStagiaire {

	public void showAddInterface(TableView<Stagiaire> tableView) {
		Stage addStage = new Stage();
		addStage.initModality(Modality.APPLICATION_MODAL);
		addStage.setTitle("Ajouter un stagiaire");

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

		Button soumettreBtn = new Button("Soumettre");
		soumettreBtn.setOnAction(event -> {

			Stagiaire nouveauStagiaire = new Stagiaire(nomTextField.getText(), prenomTextField.getText(),
					localisationTextField.getText(), formationTextField.getText(),
					Integer.parseInt(anneeEntreeTextField.getText()));
			
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

		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(soumettreBtn, retourBtn);
		gridPane.add(hbox, 0, 5, 2, 1);

		addStage.setScene(new Scene(gridPane, 400, 300));
		addStage.show();
	}
}