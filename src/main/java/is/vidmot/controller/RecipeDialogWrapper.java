package is.vidmot.controller;

import java.io.IOException;
import java.util.Optional;

import is.vinnsla.Recipe;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Window;

public class RecipeDialogWrapper {

  /**
   * @param owner
   * @return
   *
   *         Opnar Dialog pane og sendir tóma ferð sem controller bindir við gögn
   *         fyrir nýja ferð
   */
  public static Optional<Recipe> birtaDialog(Window owner) {
    FXMLLoader loader = new FXMLLoader(
        RecipeDialogController.class.getResource("/is/vidmot/newRecipe-dialog.fxml"));

    try {
      DialogPane pane = loader.load();
      RecipeDialogController controller = loader.getController();

      Recipe newRecipe = new Recipe();
      controller.setGogn(newRecipe);

      Dialog<Recipe> dialog = new Dialog<>();
      dialog.setDialogPane(pane);
      dialog.initOwner(owner);
      dialog.setTitle("New Recipe");

      dialog.setResultConverter(buttonType -> {
        if (buttonType == ButtonType.OK && !controller.erTomur()) {
          return controller.getRecipe();
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
