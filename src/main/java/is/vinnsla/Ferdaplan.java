package is.vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Ferdaplan
 */
public class Ferdaplan {
  private ObservableList<Ferd> list;

  public Ferdaplan() {
    list = FXCollections.observableArrayList();
  }

  public ObservableList<Ferd> getList() {
    return list;
  }

  /**
   * @param ferd
   *
   *             Bætir við nýju ferði í lista
   */
  public void nyFerd(Ferd ferd) {
    getList().add(ferd);
  }

  /**
   * @param ferd
   *
   *             Eyðir ferð úr lista
   */
  public void eydaFerd(Ferd ferd) {
    getList().remove(ferd);
  }
}
