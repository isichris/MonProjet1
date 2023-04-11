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


	// Constructeur de la classe Admin.
	public Admin(VBox vbox, MainInterface mainInterface, TableView<Stagiaire> tableView) {
		this.vbox = vbox;
		this.mainInterface = mainInterface;
        this.tableView = tableView;

	}

	private MainInterface mainInterface;

	// Méthode pour afficher l'interface de connexion.
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

	// Méthode pour masquer l'interface de connexion.
	private void hideLoginInterface() {
		vbox.getChildren().removeAll(loginLabel, loginField, passwordLabel, passwordField, okBtn);
	}

	// Méthode pour afficher le bouton "Admin" dans la VBox.
	private void showAdminLoginButton() {
		if (!isLoggedIn) {
			if (adminBtn == null || !vbox.getChildren().contains(adminBtn)) {
				adminBtn = new Button("Admin");
				adminBtn.setOnAction(event -> showLoginInterface());
				vbox.getChildren().add(0, adminBtn);
			}
		}
	}

	// Méthode pour afficher les boutons d'administration dans la VBox.
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
			tableView.getSelectionModel();//.setSelectionMode(SelectionMode.SINGLE);
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
			    // Aucun élément n'est sélectionné dans la TableView,  afficher un message d'erreur ou un avertissement
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

	// Méthode pour masquer les boutons d'administration dans la VBox.
	private void hideAdminButtons() {
		vbox.getChildren().removeAll(modifierBtn, supprimerBtn);
	}
}