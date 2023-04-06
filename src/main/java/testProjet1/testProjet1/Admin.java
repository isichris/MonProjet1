package testProjet1.testProjet1;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Admin {

	private VBox vbox;
	private Label loginLabel;
	private TextField loginField;
	private Button okBtn;
	private Button modifierBtn;
	private Button supprimerBtn;

	/**
	 * Constructeur pour créer une nouvelle instance de la classe Admin.
	 *
	 * @param vbox La VBox à utiliser pour afficher les éléments de l'interface.
	 */
	public Admin(VBox vbox) {
		this.vbox = vbox;
	}

	/**
	 * Affiche l'interface de connexion pour l'administrateur.
	 */
	public void showLoginInterface() {
		createLoginInterface();
	}

	/**
	 * Crée et ajoute les éléments de l'interface de connexion à la VBox.
	 */
	private void createLoginInterface() {
		loginLabel = new Label("Veuillez entrer votre login");
		loginField = new TextField();
		okBtn = new Button("OK");

		// Vérifie si le login est correct et affiche les boutons d'administration si
		// c'est le cas
		okBtn.setOnAction(event -> {
			if (loginField.getText().equals("Groupe4")) {
				hideLoginInterface();
				showAdminButtons();
				vbox.getChildren().remove(loginLabel);
				vbox.getChildren().remove(loginField);
				vbox.getChildren().remove(okBtn);
			}
		});

		vbox.getChildren().addAll(loginLabel, loginField, okBtn);
	}

	/**
	 * Supprime les éléments de l'interface de connexion de la VBox.
	 */
	private void hideLoginInterface() {
		vbox.getChildren().removeAll(loginLabel, loginField, okBtn);
	}

	/**
	 * Affiche les boutons d'administration (Modifier et Supprimer) dans la VBox.
	 */
	private void showAdminButtons() {
		modifierBtn = new Button("Modifier");
		supprimerBtn = new Button("Supprimer");
		vbox.getChildren().addAll(modifierBtn, supprimerBtn);
	}
}