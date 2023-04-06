package testProjet1.testProjet1;

// Importations nécessaires pour le code
import testProjet1.testProjet1.Imprimer;
import testProjet1.testProjet1.Stagiaire;
import testProjet1.testProjet1.Admin;
import java.io.InputStream;
import java.net.URL;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainInterface extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	// Méthode start() qui démarre l'application JavaFX
	@Override
	public void start(Stage primaryStage) {
		// Charger le logo de l'application
		InputStream logoStream = getClass().getResourceAsStream("/logo.png");
		ImageView logo = null; // Initialisez "logo" à null
		if (logoStream == null) {
			System.out.println("Erreur : le fichier logo.png n'a pas été trouvé.");
		} else {
			logo = new ImageView(new Image(logoStream));
			// Utilisez l'objet "logo" pour votre interface graphique
		}

		// Titre de la fenêtre
		primaryStage.setTitle("Annuaire ISIKA");

		// Création du GridPane comme conteneur principal
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setAlignment(Pos.CENTER);

		ColumnConstraints column1 = new ColumnConstraints();
		column1.setHgrow(Priority.ALWAYS);
		gridPane.getColumnConstraints().add(column1);

		// Ajout du logo en haut de la fenêtre

		logo.setPreserveRatio(true);
		logo.setFitWidth(200);
		GridPane.setConstraints(logo, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER);
		gridPane.getChildren().add(logo);

		// Bouton Rechercher sous le logo
		Button rechercherBtn = new Button("Rechercher");
		GridPane.setConstraints(rechercherBtn, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER);
		gridPane.getChildren().add(rechercherBtn);

		// Création de la TableView
		TableView<Stagiaire> tableView = creerTableView();
		GridPane.setConstraints(tableView, 0, 2);
		gridPane.getChildren().add(tableView);

		// VBox pour contenir les boutons à droite de la TableView
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.setMaxWidth(150);

		// Créez une instance de la classe Admin en passant la VBox vbox en paramètre
		Admin admin = new Admin(vbox);

		// Bouton pour importer une liste de stagiaires
		Button importerListeBtn = new Button("Importer Liste");
		importerListeBtn.setOnAction(event -> {
			ImporterListe importerListe = new ImporterListe();
			tableView.getItems().setAll(importerListe.importerListeStagiaires());
		});

		// Bouton pour imprimer la liste des stagiaires
		Button imprimerBtn = new Button("Imprimer");
		imprimerBtn.setOnAction(event -> {
			ObservableList<Stagiaire> stagiaires = tableView.getItems();
			if (stagiaires != null) {
				Imprimer imprimer = new Imprimer(stagiaires);
				imprimer.imprimerAnnuaire();
			} else {
				System.out.println("Aucune liste de stagiaires à imprimer.");
			}
		});

		// Bouton pour accéder à l'interface d'administration
		Button adminBtn = new Button("Admin");
		adminBtn.setOnAction(event -> {
			admin.showLoginInterface();
			vbox.getChildren().remove(adminBtn);
		});

		// Ajout des boutons à la VBox
		Button ajouterBtn = new Button("Ajouter");
		AjouterStagiaire ajouterStagiaire = new AjouterStagiaire();
		ajouterBtn.setOnAction(event -> ajouterStagiaire.showAddInterface(tableView));
		vbox.getChildren().addAll(adminBtn, imprimerBtn, importerListeBtn, ajouterBtn);

		// Ajout de la VBox au GridPane
		GridPane.setConstraints(vbox, 1, 2);
		gridPane.getChildren().add(vbox);

		// Configuration de la scène et affichage
		primaryStage.setScene(new Scene(gridPane, 800, 600));
		primaryStage.show();
	}

	/**
	 * Crée une TableView pour afficher les informations des stagiaires.
	 *
	 * @return une TableView configurée
	 */
	private TableView<Stagiaire> creerTableView() {
		TableView<Stagiaire> tableView = new TableView<>();

		TableColumn<Stagiaire, String> nomColonne = new TableColumn<>("Nom");
		nomColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
		nomColonne.setMinWidth(100);

		TableColumn<Stagiaire, String> prenomColonne = new TableColumn<>("Prenom");
		prenomColonne.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		prenomColonne.setMinWidth(100);

		TableColumn<Stagiaire, String> departementColonne = new TableColumn<>("Departement");
		departementColonne.setCellValueFactory(new PropertyValueFactory<>("departement"));
		departementColonne.setMinWidth(100);

		TableColumn<Stagiaire, String> formationColonne = new TableColumn<>("Formation");
		formationColonne.setCellValueFactory(new PropertyValueFactory<>("formation"));
		formationColonne.setMinWidth(100);

		TableColumn<Stagiaire, Integer> anneeInscriptionColonne = new TableColumn<>("Année d'inscription");
		anneeInscriptionColonne.setCellValueFactory(new PropertyValueFactory<>("anneeInscription"));
		anneeInscriptionColonne.setMinWidth(100);

		// Ajout des colonnes à la TableView
		tableView.getColumns().addAll(nomColonne, prenomColonne, departementColonne, formationColonne,
				anneeInscriptionColonne);

		// Ajustement de la largeur des colonnes pour supprimer l'espace inutile à
		// droite de la dernière colonne
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return tableView;
	}
}