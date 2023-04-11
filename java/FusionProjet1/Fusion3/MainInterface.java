package FusionProjet1.Fusion3;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
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

public class MainInterface extends Application {
	// On crée une TableView qui va contenir les stagiaires.
	private TableView<Stagiaire> tableView;

	public static void main(String[] args) {
		// On démarre l'application JavaFX.
		launch(args);
	}

	 /**La méthode init() est appelée avant la méthode start() et permet
	 d'initialiser certaines données avant de créer l'interface graphique.*/
	public void init() throws IOException, URISyntaxException {
		// On crée un objet Arbre.
		Arbre arb = new Arbre();
		// On crée un objet File pour le fichier binaire.
		File fichier = new File("monFichier.bin");
		// Si le fichier binaire existe déjà, on appelle la méthode afficherInfixe de
		// l'objet Arbre.
		if (fichier.isFile()) {
			arb.affichageInfixe();
		}
	}

	/**La méthode getSelectedStagiaire() retourne le stagiaire sélectionné dans la
	TableView.*/
	public Stagiaire getSelectedStagiaire() {
		return tableView.getSelectionModel().getSelectedItem();
	}

	/** La méthode start() est appelée pour démarrer l'interface graphique de
	l'application.*/
	@Override
	public void start(Stage primaryStage) throws IOException, URISyntaxException {
		// On crée une TableView des stagiaires et un objet Arbre.
		this.tableView = creerTableView();
		Arbre arbre = new Arbre();
		// On affiche les stagiaires dans la TableView en utilisant la méthode
		// afficherStagiaires de l'objet Arbre.
		arbre.afficherStagiaires(arbre, this.tableView);

		// On charge le logo de l'application depuis un fichier.
		InputStream logoStream = getClass().getResourceAsStream("/logo.png");
		ImageView logo = null;
		if (logoStream == null) {
			System.out.println("Erreur : le fichier logo.png n'a pas été trouvé.");
		} else {
			logo = new ImageView(new Image(logoStream));
			// On utilise l'objet "logo" pour votre interface graphique.
		}

		/**On donne un titre à la fenêtre.*/
		primaryStage.setTitle("Annuaire ISIKA");

		/**On crée un GridPane comme conteneur principal.*/
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setAlignment(Pos.CENTER);

		ColumnConstraints column1 = new ColumnConstraints();
		column1.setHgrow(Priority.ALWAYS);
		gridPane.getColumnConstraints().add(column1);

		/** On crée une instance de la classe Rechercher.*/
		Rechercher rechercher = new Rechercher(tableView, arbre);

		/**On ajoute le logo en haut de la fenêtre.*/
		logo.setPreserveRatio(true);
		logo.setFitWidth(500);
		GridPane.setConstraints(logo, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER);
		gridPane.getChildren().add(logo);

		// On crée un bouton "Rechercher".
		Button rechercherBtn = new Button("Rechercher");
		
		// On ajoute le conteneur de recherche sous le logo.
		GridPane.setConstraints(rechercher.getSearchContainer(), 0, 1, 2, 1, HPos.CENTER, VPos.CENTER);
		gridPane.getChildren().add(rechercher.getSearchContainer());

		/** On crée la TableView des stagiaires.*/
		GridPane.setConstraints(tableView, 0, 2);
		gridPane.getChildren().add(tableView);

		/** On crée une VBox pour contenir les boutons à droite de la TableView.*/
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.setMaxWidth(150);

		/** On crée une instance de la classe Admin en passant la VBox vbox en paramètre.*/
		Admin admin = new Admin(vbox, null, tableView);

		// On crée un bouton pour imprimer la liste des stagiaires.
		Button imprimerBtn = new Button("Imprimer");
		imprimerBtn.setOnAction(event -> {
			// On récupère la liste des stagiaires dans la TableView.
			ObservableList<Stagiaire> stagiaires = tableView.getItems();
			if (stagiaires != null) {
				Imprimer imprimer = new Imprimer(stagiaires);
				imprimer.imprimerAnnuaire();
			} else {
				System.out.println("Aucune liste de stagiaires à imprimer.");
			}
		});

		// On crée un bouton pour accéder à l'interface d'administration.
		Button adminBtn = new Button("Admin");
		adminBtn.setOnAction(event -> {
			admin.showLoginInterface();
			vbox.getChildren().remove(adminBtn);
		});

		/**On ajoute les boutons "Ajouter", "Imprimer" et "Admin" à la VBox.*/
		Button ajouterBtn = new Button("Ajouter");
		AjouterStagiaire ajouterStagiaire = new AjouterStagiaire();
		ajouterBtn.setOnAction(event -> ajouterStagiaire.showAddInterface(tableView));
		vbox.getChildren().addAll(adminBtn, imprimerBtn, ajouterBtn);

		/** On ajoute la VBox au GridPane.*/
		GridPane.setConstraints(vbox, 1, 2);
		gridPane.getChildren().add(vbox);

		// On configure la scène et on l'affiche.
		primaryStage.setScene(new Scene(gridPane, 900, 580));
		primaryStage.show();
	}

	/** On crée une TableView pour les stagiaires.*/
	private StagiaireTableView creerTableView() {
		return new StagiaireTableView();
	}
}