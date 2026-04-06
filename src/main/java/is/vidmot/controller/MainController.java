package is.vidmot.controller;

import java.io.IOException;
import java.util.Optional;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import is.vinnsla.DatabaseManager;
import is.vinnsla.Recipe;
import is.vinnsla.RecipeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Window;
import javafx.scene.control.Button;

public class MainController {
  @FXML
  private ListView<Recipe> fxListView;

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
   * @throws IOException
   *
   *                     Keyrist sjálkrafa með forritinu.
   */
  public void initialize() throws IOException {
    displayListView();
    disableButtons();
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
    }
    hreinsaLabel();
  }
}
