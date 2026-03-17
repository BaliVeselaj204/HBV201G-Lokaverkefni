package is.vidmot.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StadfestingEydaController {

  @FXML
  private Button yesButton;

  private boolean confirmed = false;

  @FXML
  private void onYes() {
    confirmed = true;
    closeDialog();
  }

  @FXML
  private void onNo() {
    confirmed = false;
    closeDialog();
  }

  /**
   * Loka Dialog glugga
   */
  private void closeDialog() {
    Stage stage = (Stage) yesButton.getScene().getWindow();
    stage.close();
  }

  public boolean isConfirmed() {
    return confirmed;
  }
}
