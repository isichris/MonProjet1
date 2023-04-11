package FusionProjet1.Fusion3;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * La classe Admin représente l'interface pour accéder aux fonctionnalités
 * d'administration de l'application. Elle permet de se connecter en tant
 * qu'administrateur pour ensuite accéder aux fonctionnalités de modification et
 * de suppression de stagiaires dans la TableView.
 */
public class Admin {

	private VBox vbox;
	private Label loginLabel;
	private Label passwordLabel;
	private TextField loginField;
	private PasswordField passwordField;
	private Button okBtn;
	private Button modifierBtn;
	private Button supprimerBtn;
	private Button adminBtn;
	private boolean isLoggedIn = false;
	private TableView<Stagiaire> tableView;

	/**
	 * Construit une instance de la classe Admin.
	 * 
	 * @param vbox          la VBox qui contient l'interface de l'application
	 * @param mainInterface l'instance de MainInterface qui gère l'interface
	 *                      principale de l'application
	 * @param tableView     la TableView qui affiche la liste des stagiaires
	 */
	public Admin(VBox vbox, MainInterface mainInterface, TableView<Stagiaire> tableView) {
		this.vbox = vbox;
		this.mainInterface = mainInterface;
		this.tableView = tableView;

	}

	private MainInterface mainInterface;

	/**
	 * Affiche l'interface de connexion pour l'administrateur. Cette méthode est
	 * appelée lorsque l'administrateur clique sur le bouton "Admin".
	 */
	public void showLoginInterface() {
		loginLabel = new Label("Login:");
		passwordLabel = new Label("Mot de passe:");
		loginField = new TextField();
		passwordField = new PasswordField();
		okBtn = new Button("OK");

		// On définit l'action à réaliser lorsque l'utilisateur clique sur le bouton
		// "OK".
		okBtn.setOnAction(event -> {
			if (loginField.getText().equals("Groupe4") && passwordField.getText().equals("Groupe4")) {
				isLoggedIn = true;
				hideLoginInterface();
				showAdminButtons();
			} else {
				loginField.clear();
				passwordField.clear();
				hideLoginInterface();
				showAdminLoginButton();
			}
		});

		vbox.getChildren().addAll(loginLabel, loginField, passwordLabel, passwordField, okBtn);
	}

	/**
	 * Masque l'interface de connexion de l'administrateur. Cette méthode est
	 * appelée lorsque l'administrateur est connecté.
	 */
	private void hideLoginInterface() {
		vbox.getChildren().removeAll(loginLabel, loginField, passwordLabel, passwordField, okBtn);
	}

	/**
	 * Affiche le bouton "Admin" pour permettre la connexion de l'administrateur.
	 */
	private void showAdminLoginButton() {
		if (!isLoggedIn) {
			if (adminBtn == null || !vbox.getChildren().contains(adminBtn)) {
				adminBtn = new Button("Admin");
				adminBtn.setOnAction(event -> showLoginInterface());
				vbox.getChildren().add(0, adminBtn);
			}
		}
	}

	/**
	 * Méthode pour afficher les boutons d'administration dans la VBox.
	 */
	private void showAdminButtons() {
		modifierBtn = new Button("Modifier");
		ModifierButton modifierButton = new ModifierButton();
		modifierBtn.setOnAction(event -> {
			// On récupère le stagiaire sélectionné dans la TableView de l'interface
			// principale.
			Stagiaire selectedStagiaire = mainInterface.getSelectedStagiaire();
			if (selectedStagiaire != null) {
				modifierButton.showModifierInterface(selectedStagiaire);
			}
		});

		supprimerBtn = new Button("Supprimer");
		supprimerBtn.setOnAction(event -> {
			// Récupère le stagiaire sélectionné dans la TableView
			tableView.getSelectionModel();// .setSelectionMode(SelectionMode.SINGLE);
			Stagiaire stagiaireASupprimer = tableView.getSelectionModel().getSelectedItem();
			if (stagiaireASupprimer != null) {
				Arbre arbre;
				// Supprime le stagiaire de l'arbre binaire de recherche
				try {
					arbre = new Arbre();
					arbre.supprimerStagiaire(stagiaireASupprimer.getNom());

					// Met à jour la TableView en enlevant le stagiaire supprimé
//				    tableView.getItems().clear();
					tableView.setItems(FXCollections.observableList(arbre.affichageInfixe()));

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				// Aucun élément n'est sélectionné dans la TableView, afficher un message
				// d'erreur ou un avertissement
				System.out.println("Veuillez sélectionner un stagiaire à supprimer.");
			}
		});

		Button logoutBtn = new Button("Logout");
		logoutBtn.setOnAction(event -> {
			isLoggedIn = false;
			hideAdminButtons();
			vbox.getChildren().removeAll(logoutBtn, modifierBtn, supprimerBtn);
			showAdminLoginButton();
		});
		vbox.getChildren().addAll(modifierBtn, supprimerBtn, logoutBtn);
	}

	/**
	 * Méthode pour masquer les boutons d'administration dans la VBox.
	 */
	private void hideAdminButtons() {
		vbox.getChildren().removeAll(modifierBtn, supprimerBtn);
	}
}