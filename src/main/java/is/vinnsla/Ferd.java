package is.vinnsla;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Ferd
 */
public class Ferd {
  StringProperty ferd = new SimpleStringProperty();
  StringProperty stadur = new SimpleStringProperty();
  StringProperty date = new SimpleStringProperty();

  public Ferd(String ferd, String stadur, String date) {
    this.ferd.set(ferd);
    this.stadur.set(stadur);
    this.date.set(date);
  }

  @Override
  public String toString() {
    return stadur.get();
  }

  public StringProperty getFerd() {
    return ferd;
  }

  public StringProperty getStadur() {
    return stadur;
  }

  public StringProperty getDate() {
    return date;
  }
}
