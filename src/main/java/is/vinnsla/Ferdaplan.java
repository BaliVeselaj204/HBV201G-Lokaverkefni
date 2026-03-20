package is.vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Ferdaplan
 */
public class Ferdaplan {
  private ObservableList<Recipe> list;

  public Ferdaplan() {
    list = FXCollections.observableArrayList();
  }

  public ObservableList<Recipe> getList() {
    return list;
  }

  /**
   * @param ferd
   *
   *             Bætir við nýju ferði í lista
   */
  public void nyFerd(Recipe ferd) {
    getList().add(ferd);
  }

  /**
   * @param ferd
   *
   *             Eyðir ferð úr lista
   */
  public void eydaFerd(Recipe ferd) {
    getList().remove(ferd);
  }
}
