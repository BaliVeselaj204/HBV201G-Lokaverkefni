package is.vidmot.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Window;

public class RemoveRecipeDialogWrapper {

  /**
   * @param owner
   * @return
   *
   *         Opnar dialog pane til að eyða ferð
   */
  public static boolean birtaDialog(Window owner) {
    FXMLLoader loader = new FXMLLoader(
        RemoveRecipeDialogController.class.getResource("/is/vidmot/removeRecipe-dialog.fxml"));
    try {
      DialogPane pane = loader.load();
      Dialog<Boolean> dialog = new Dialog<>();
      dialog.setDialogPane(pane);
      dialog.initOwner(owner);
      dialog.setTitle("Remove recipe");
      dialog.setResultConverter(buttonType -> buttonType == ButtonType.OK);
      return dialog.showAndWait().orElse(false);

    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }
}
