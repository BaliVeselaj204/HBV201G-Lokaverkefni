package is.vidmot.controller;

import java.io.IOException;
import java.util.Optional;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
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
  private Button fxSkodaButton;

  @FXML
  private Button fxNyrButton;

  @FXML
  private Button fxEydaButton;

  RecipeManager ferdaplan = new RecipeManager();

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
   * Svo að ekki sé hægt að ýtta á skoða eða eyða buttons nema Ferd sé valið
   */
  private void disableButtons() {
    fxSkodaButton.disableProperty().bind(fxListView.getSelectionModel().selectedItemProperty().isNull());
    fxEydaButton.disableProperty().bind(fxListView.getSelectionModel().selectedItemProperty().isNull());
  }

  /**
   * Hér birtum við ferðirnar í ListView
   */
  private void displayListView() {
    fxListView.setItems(ferdaplan.getList());
  }

  /**
   * Fall sem keyrist þegar ýtt er á skoða hnapp
   */
  @FXML
  private void onSkoda() {
    Recipe currFerd = fxListView.getSelectionModel().getSelectedItem();
    ViewSwitcher.switchTo(View.FERD, false, currFerd);
    hreinsaLabel();
  }

  /**
   * Fall sem keyrist þegar ýtt er á nýja hnapp
   */
  @FXML
  private void onNyr() {
    Window owner = fxNyrButton.getScene().getWindow();
    Optional<Recipe> result = FerdDialogWrapper.birtaDialog(owner);
    result.ifPresent(ferd -> ferdaplan.newRecipe(ferd));

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
  private void onEyda() {
    boolean confirmed = StadfestingEydaDialogWrapper.birtaDialog(fxEydaButton.getScene().getWindow());

    if (confirmed) {
      Recipe valinFerd = fxListView.getSelectionModel().getSelectedItem();
      ferdaplan.removeRecipe(valinFerd);
    }
    hreinsaLabel();
  }
}
