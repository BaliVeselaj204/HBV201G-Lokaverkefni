package is.vidmot.controller;

import java.io.IOException;
import java.util.Optional;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import is.vinnsla.DatabaseManager;
import is.vinnsla.Recipe;
import is.vinnsla.RecipeManager;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Window;
import javafx.util.Callback;

public class MainController {
  @FXML
  private ListView<Recipe> fxListView;

  @FXML
  private ComboBox<String> sortComboBox;

  @FXML
  private Label fxLabel;

  @FXML
  private Button fxViewButton;

  @FXML
  private Button fxNewButton;

  @FXML
  private Button fxRemoveButton;

  RecipeManager recipeManager = RecipeManager.getInstance();

  /**
   * @throws IOException Keyrist sjálkrafa með forritinu.
   */
  public void initialize() throws IOException {
    displayListView();
    disableButtons();
    initializeSortComboBox();
  }

  /**
   * Hreinsar label
   */
  private void hreinsaLabel() {
    fxLabel.setText("");
  }

  /**
   * Svo að ekki sé hægt að ýtta á skoða eða eyða buttons nema uppskrift sé valið
   */
  private void disableButtons() {
    fxViewButton.disableProperty().bind(fxListView.getSelectionModel().selectedItemProperty().isNull());
    fxRemoveButton.disableProperty().bind(fxListView.getSelectionModel().selectedItemProperty().isNull());
  }

  /**
   * Hér birtum við uppskriftirnar í ListView
   */
  private void displayListView() {
    fxListView.setItems(recipeManager.getList());

    // Set custom cell factory to display multiple fields in the ListView
    fxListView.setCellFactory(new Callback<ListView<Recipe>, ListCell<Recipe>>() {
      @Override
      public ListCell<Recipe> call(ListView<Recipe> param) {
        return new ListCell<Recipe>() {
          @Override
          protected void updateItem(Recipe item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
              setText(null);
              setGraphic(null);
            } else {
              // Create the custom layout for the ListCell
              HBox hbox = new HBox(10); // 10px spacing between the elements

              Label nameLabel = new Label(item.getName() + "   |");
              Label timeLabel = new Label(String.valueOf(item.getCookTime()) + " mins   |");
              Label difficultyLabel = new Label(item.getDifficulty() + "   |");
              Label caloriesLabel = new Label(String.valueOf(item.getCalories()) + " kcal");

              hbox.getChildren().addAll(nameLabel, timeLabel, difficultyLabel, caloriesLabel);

              setGraphic(hbox);  // Set the custom layout for the cell
            }
          }
        };
      }
    });
  }

  /**
   * Fall sem keyrist þegar ýtt er á skoða hnapp
   */
  @FXML
  private void onView() {
    Recipe currRecipe = fxListView.getSelectionModel().getSelectedItem();
    ViewSwitcher.switchTo(View.FERD, false, currRecipe);
    hreinsaLabel();
  }

  /**
   * Fall sem keyrist þegar ýtt er á nýja hnapp
   */
  @FXML
  private void onNew() {
    Window owner = fxNewButton.getScene().getWindow();
    Optional<Recipe> result = RecipeDialogWrapper.birtaDialog(owner);
    result.ifPresent(recipe -> {
      recipeManager.newRecipe(recipe);
      DatabaseManager.saveRecipe(recipe);
    });

    if (result.isPresent()) {
      hreinsaLabel();
    } else {
      fxLabel.setText("Fylltu inn alla reiti");
    }
  }

  /**
   * Fall sem keyrist þegar ýtt er á eyða hnapp
   */
  @FXML
  private void onRemove() {
    boolean confirmed = RemoveRecipeDialogWrapper.birtaDialog(fxRemoveButton.getScene().getWindow());

    if (confirmed) {
      Recipe chosenRecipe = fxListView.getSelectionModel().getSelectedItem();
      recipeManager.removeRecipe(chosenRecipe);
      DatabaseManager.deleteRecipe(chosenRecipe.getName());
    }
    hreinsaLabel();
  }

  // Method to initialize ComboBox with sorting options
  private void initializeSortComboBox() {
    sortComboBox.getItems().addAll("Name", "Time", "Difficulty", "Calories", "Protein");
    sortComboBox.setOnAction(e -> onSort());  // Trigger sort when selection changes
  }

  private void onSort() {
    String selectedSort = sortComboBox.getSelectionModel().getSelectedItem();
    if (selectedSort != null) {
      switch (selectedSort) {
        case "Name":
          sortByName();
          break;
        case "Time":
          sortByCookTime();
          break;
        case "Protein":
          sortByProtein();
          break;
        case "Calories":
          sortByCalories();
          break;
        case "Difficulty":
          sortByDifficulty();
          break;
      }
    }
  }

  // Sorting methods
  private void sortByName() {
    SortedList<Recipe> sortedRecipes = new SortedList<>(recipeManager.getList());
    sortedRecipes.setComparator((r1, r2) -> r1.getName().compareToIgnoreCase(r2.getName()));
    fxListView.setItems(sortedRecipes);
  }

  private void sortByCookTime() {
    SortedList<Recipe> sortedRecipes = new SortedList<>(recipeManager.getList());
    sortedRecipes.setComparator((r1, r2) -> Integer.compare(r1.getCookTime(), r2.getCookTime()));
    fxListView.setItems(sortedRecipes);
  }

  private void sortByProtein() {
    SortedList<Recipe> sortedRecipes = new SortedList<>(recipeManager.getList());
    sortedRecipes.setComparator((r1, r2) -> Double.compare(r1.getProtein(), r2.getProtein()));
    fxListView.setItems(sortedRecipes);
  }

  private void sortByCalories() {
    SortedList<Recipe> sortedRecipes = new SortedList<>(recipeManager.getList());
    sortedRecipes.setComparator((r1, r2) -> Integer.compare(r1.getCalories(), r2.getCalories()));
    fxListView.setItems(sortedRecipes);
  }

  private void sortByDifficulty() {
    SortedList<Recipe> sortedRecipes = new SortedList<>(recipeManager.getList());
    sortedRecipes.setComparator((r1, r2) -> {
      return Integer.compare(
              getDifficultyValue(r1.getDifficulty()),
              getDifficultyValue(r2.getDifficulty())
      );
    });
    fxListView.setItems(sortedRecipes);
  }

  private int getDifficultyValue(String difficulty) {
    if (difficulty == null) return 0;

    switch (difficulty) {
      case "Easy": return 1;
      case "Medium": return 2;
      case "Hard": return 3;
      default: return 0;
    }
  }
}
