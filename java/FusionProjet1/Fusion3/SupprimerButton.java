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

public class SupprimerButton {

    private Stagiaire selectedStagiaire;

    // On crée un constructeur avec un stagiaire sélectionné en paramètre
    public SupprimerButton(Stagiaire selectedStagiaire) {
        this.selectedStagiaire = selectedStagiaire;
    }

    // On affiche l'interface pour supprimer un stagiaire
    public void showSupprimerInterface() {
        Stage supprimerStage = new Stage();
        supprimerStage.initModality(Modality.APPLICATION_MODAL);
        supprimerStage.setTitle("Modifier un stagiaire");

        GridPane gridPane = createGridPane();
        HBox buttonBox = createButtonBox(supprimerStage);
        gridPane.add(buttonBox, 0, 5, 2, 1);

        supprimerStage.setScene(new Scene(gridPane, 400, 300));
        supprimerStage.show();
    }

    // On crée la grille pour l'interface de suppression de stagiaire
    private GridPane createGridPane() {
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

        // On remplit les champs avec les informations du stagiaire sélectionné, si présent
        if (selectedStagiaire != null) {
            nomTextField.setText(selectedStagiaire.getNom());
            prenomTextField.setText(selectedStagiaire.getPrenom());
            localisationTextField.setText(selectedStagiaire.getLocalisation());
            formationTextField.setText(selectedStagiaire.getFormation());
            anneeEntreeTextField.setText(Integer.toString(selectedStagiaire.getAnneeEntree()));
        }

        return gridPane;
    }
    // On crée une boîte contenant les boutons Soumettre et Retour
    private HBox createButtonBox(Stage stage) {
        Button soumettreBtn = new Button("Soumettre");
        Button retourBtn = createRetourButton(stage);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(soumettreBtn, retourBtn);

        return buttonBox;
    }

    // On crée un bouton Retour avec une action pour fermer la fenêtre
    private Button createRetourButton(Stage stage) {
        Button retourBtn = new Button("Retour");
        retourBtn.setOnAction(event -> stage.close());
        return retourBtn;
    }
}