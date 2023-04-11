package FusionProjet1.Fusion3;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Rechercher {
	private final VBox searchContainer;

	public Rechercher(TableView<Stagiaire> tableView, Arbre arbre) throws IOException {
		// Créez les champs pour les critères de recherche que vous souhaitez permettre
		HBox hboxSearch = new HBox();
		hboxSearch.setSpacing(10);
		hboxSearch.setAlignment(Pos.CENTER);

		TextField Nom = new TextField();
		Nom.setPromptText("Nom");
		hboxSearch.getChildren().add(Nom);

		TextField Prenom = new TextField();
		Prenom.setPromptText("Prenom");
		hboxSearch.getChildren().add(Prenom);

		TextField Departement = new TextField();
		Departement.setPromptText("Departement");
		hboxSearch.getChildren().add(Departement);

		TextField Formation = new TextField();
		Formation.setPromptText("Formation");
		hboxSearch.getChildren().add(Formation);

		TextField Annee = new TextField();
		Annee.setPromptText("Annee");
		hboxSearch.getChildren().add(Annee);

		FilteredList<Stagiaire> filteredData = new FilteredList<>(FXCollections.observableList(arbre.affichageInfixe()), p -> true);
		//SortedList<Stagiaire> sortedData = new SortedList<>(filteredData);
		//sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		tableView.setItems(filteredData);
		//System.out.println("adress before"+(Object)tableView + "\n " + (Object)tableView.getItems());

		ChangeListener<String> textFieldListener = (observable, oldValue, newValue) -> {
			filteredData.setPredicate(stagiaire -> {
				String nomFilter = Nom.getText().toLowerCase();
				String prenomFilter = Prenom.getText().toLowerCase();
				String departementFilter = Departement.getText().toLowerCase();
				String formationFilter = Formation.getText().toLowerCase();
				String anneeFilter = Annee.getText();

				boolean matchesNom = stagiaire.getNom().toLowerCase().contains(nomFilter);
				boolean matchesPrenom = stagiaire.getPrenom().toLowerCase().contains(prenomFilter);
				boolean matchesDepartement = stagiaire.getLocalisation().toLowerCase().contains(departementFilter);
				boolean matchesFormation = stagiaire.getFormation().toLowerCase().contains(formationFilter);

				if (anneeFilter.isEmpty()) {
					return matchesNom && matchesPrenom && matchesDepartement && matchesFormation;
				} else {
					try {
						int intValue = Integer.parseInt(anneeFilter);
						boolean matchesAnneeEntree = stagiaire.getAnneeEntree() == intValue;
						return matchesNom && matchesPrenom && matchesDepartement && matchesFormation
								&& matchesAnneeEntree;
					} catch (NumberFormatException e) {
						return false;
					}
				}
			});
		};

		Nom.textProperty().addListener(textFieldListener);
		Prenom.textProperty().addListener(textFieldListener);
		Departement.textProperty().addListener(textFieldListener);
		Formation.textProperty().addListener(textFieldListener);
		Annee.textProperty().addListener(textFieldListener);

		Button clearBtn = new Button("Clear");
		clearBtn.setOnAction(event -> {
			Nom.clear();
			Prenom.clear();
			Departement.clear();
			Formation.clear();
			Annee.clear();
		});

		hboxSearch.getChildren().add(clearBtn);

		searchContainer = new VBox(hboxSearch);
		searchContainer.setSpacing(10);
	}

	public VBox getSearchContainer() {
		return searchContainer;
	}
}