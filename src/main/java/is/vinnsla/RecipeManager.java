package is.vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * RecipeManager
 */
public class RecipeManager {
  private ObservableList<Recipe> list;

    public RecipeManager() {
    list = FXCollections.observableArrayList(
            new Recipe("Hot dog", "Put the hot dog into water and heat until boiling",
                    10, 400, 10.0, 25.0, 15.0, 1, "Easy"),
            new Recipe("Pizza", "Flatten out the dough, put the toppings on and heat in a 200°C oven for 12-15min.",
                    30, 600, 20.0, 40.0, 15.0, 2, "Easy")
    );;
  }

  public ObservableList<Recipe> getList() {
    return list;
  }

  /**
   * @param recipe
   *
   *             Bætir við nýju uppskrift í lista
   */
  public void newRecipe(Recipe recipe) {
    getList().add(recipe);
  }

  /**
   * @param recipe
   *
   *             Eyðir uppskrift úr lista
   */
  public void removeRecipe(Recipe recipe) {
    getList().remove(recipe);
  }
}
