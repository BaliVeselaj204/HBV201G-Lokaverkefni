package is.vidmot.controller;

import java.io.IOException;
import java.util.Optional;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import is.vinnsla.Ferd;
import is.vinnsla.Ferdaplan;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Window;
import javafx.scene.control.Button;

public class AdalController {
  @FXML
  private ListView<Ferd> fxListView;

  @FXML
  private Label fxLabel;

  @FXML
  private Button fxSkodaButton;

  @FXML
  private Button fxNyrButton;

  @FXML
  private Button fxEydaButton;

  Ferdaplan ferdaplan = new Ferdaplan();

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
    Ferd currFerd = fxListView.getSelectionModel().getSelectedItem();
    ViewSwitcher.switchTo(View.FERD, false, currFerd);
    hreinsaLabel();
  }

  /**
   * Fall sem keyrist þegar ýtt er á nýja hnapp
   */
  @FXML
  private void onNyr() {
    Window owner = fxNyrButton.getScene().getWindow();
    Optional<Ferd> result = FerdDialogWrapper.birtaDialog(owner);
    result.ifPresent(ferd -> ferdaplan.nyFerd(ferd));

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
      Ferd valinFerd = fxListView.getSelectionModel().getSelectedItem();
      ferdaplan.eydaFerd(valinFerd);
    }
    hreinsaLabel();
  }
}
