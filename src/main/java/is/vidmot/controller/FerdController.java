package is.vidmot.controller;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import is.vidmot.view.FerdSpjald;
import is.vinnsla.Ferd;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * FerdController
 */
public class FerdController implements GognInterface {

  @FXML
  TextField heitiField;

  @FXML
  TextField afangastadurField;

  @FXML
  TextField dagsetningField;

  @FXML
  private FerdSpjald fxFerdSpjald;

  @Override
  public void setGogn(Ferd f) {
    fxFerdSpjald.getHeitiProperty().bind(f.getFerd());
    fxFerdSpjald.getAfangastadurProperty().bind(f.getStadur());
    fxFerdSpjald.getDagsetningProperty().bind(f.getDate());
  }

  /**
   * Fara til baka í main view
   */
  @FXML
  private void fxOnBack() {
    ViewSwitcher.switchTo(View.MAIN);
  }
}
