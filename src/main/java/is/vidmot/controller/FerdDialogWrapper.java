package is.vidmot.controller;

import java.io.IOException;
import java.util.Optional;

import is.vinnsla.Ferd;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Window;

public class FerdDialogWrapper {

  /**
   * @param owner
   * @return
   *
   *         Opnar Dialog pane og sendir tóma ferð sem controller bindir við gögn
   *         fyrir nýja ferð
   */
  public static Optional<Ferd> birtaDialog(Window owner) {
    FXMLLoader loader = new FXMLLoader(
        FerdDialogController.class.getResource("/is/vidmot/nyFerd-dialog.fxml"));

    try {
      DialogPane pane = loader.load();
      FerdDialogController controller = loader.getController();

      Ferd nyFerd = new Ferd("", "", "");
      controller.setGogn(nyFerd);

      Dialog<Ferd> dialog = new Dialog<>();
      dialog.setDialogPane(pane);
      dialog.initOwner(owner);
      dialog.setTitle("Ný Ferð");

      dialog.setResultConverter(buttonType -> {
        if (buttonType == ButtonType.OK && !controller.erTomur()) {
          return controller.getFerd();
        }
        return null;
      });

      return dialog.showAndWait();

    } catch (IOException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }
}
