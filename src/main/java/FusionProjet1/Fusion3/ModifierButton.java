package FusionProjet1.Fusion3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModifierButton {

	/**Constructeur par défaut de la classe ModifierButton.*/
	public ModifierButton() {}

	/** On affiche une interface de modification pour un stagiaire sélectionné.*/
	public void showModifierInterface(Stagiaire selectedStagiaire) {

	    // Création de la fenêtre de modification
	    Stage modifierStage = new Stage();
	    modifierStage.initModality(Modality.APPLICATION_MODAL);
	    modifierStage.setTitle("Modifier un stagiaire");

	    // Création d'un GridPane pour organiser les composants graphiques
	    GridPane gridPane = createGridPane(selectedStagiaire);

	    // Création d'une HBox pour contenir les boutons de la fenêtre
	    HBox buttonBox = createButtonBox(modifierStage);

	    // Ajout de la HBox à la fin du GridPane
	    gridPane.add(buttonBox, 0, 5, 2, 1);

	    // Affichage de la fenêtre de modification
	    modifierStage.setScene(new Scene(gridPane, 400, 300));
	    modifierStage.show();
	}

	/**Crée un GridPane pour organiser les composants graphiques de la fenêtre de modification.*/
	private GridPane createGridPane(Stagiaire selectedStagiaire) {

	    // Création du GridPane
	    GridPane gridPane = new GridPane();
	    gridPane.setHgap(10);
	    gridPane.setVgap(10);
	    gridPane.setPadding(new Insets(10, 10, 10, 10));
	    gridPane.setAlignment(Pos.CENTER);

	    // Création des composants graphiques pour les champs de modification
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

	   /**Si on a sélectionné un stagiaire, on préremplit les champs avec ses informations*/
	    if (selectedStagiaire != null) {
	        nomTextField.setText(selectedStagiaire.getNom());
	        prenomTextField.setText(selectedStagiaire.getPrenom());
	        localisationTextField.setText(selectedStagiaire.getLocalisation());
	        formationTextField.setText(selectedStagiaire.getFormation());
	        anneeEntreeTextField.setText(Integer.toString(selectedStagiaire.getAnneeEntree()));
	    }

	    return gridPane;
	}

	/**On crée une boîte de boutons qui permet de valider ou d'annuler les modifications.*/
	private HBox createButtonBox(Stage stage) {
	    // Création des boutons de la boîte
	    Button soumettreBtn = new Button("Soumettre");
	    Button retourBtn = createRetourButton(stage);

	    // Création de la HBox pour contenir les boutons
	    HBox buttonBox = new HBox(10);
	    buttonBox.setAlignment(Pos.CENTER);
	    buttonBox.getChildren().addAll(soumettreBtn, retourBtn);

	    return buttonBox;
	}

	/**On crée un bouton "Retour" qui permet de fermer la fenêtre de modification.*/
	private Button createRetourButton(Stage stage) {
	    Button retourBtn = new Button("Retour");
	    // Action du bouton "Retour" : fermer la fenêtre de modification
	    retourBtn.setOnAction(event -> stage.close());
	    return retourBtn;
	}
}