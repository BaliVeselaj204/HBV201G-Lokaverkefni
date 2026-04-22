package is.vidmot.controller;

import java.io.IOException;
import java.util.Optional;

import is.vinnsla.Ingredient;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Window;

/**
 * IngredientDialogWrapper
 */
public class IngredientDialogWrapper {

  /**
   * @param owner
   * @return
   *
   */
  public static Optional<Ingredient> birtaDialog(Window owner) {
    FXMLLoader loader = new FXMLLoader(
        IngredientDialogController.class.getResource("/is/vidmot/newIngredient-dialog.fxml"));

    try {
      DialogPane pane = loader.load();
      IngredientDialogController controller = loader.getController();

      Ingredient newIngredient = new Ingredient();
      controller.setGogn(newIngredient);

      Dialog<Ingredient> dialog = new Dialog<>();
      dialog.setDialogPane(pane);
      dialog.initOwner(owner);
      dialog.setTitle("New Ingredient");

      dialog.setResultConverter(buttonType -> {
        if (buttonType == ButtonType.OK && !controller.isEmpty()) {
          return controller.getIngredient();
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
