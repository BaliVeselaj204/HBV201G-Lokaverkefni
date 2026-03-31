package is.vinnsla;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Ingredient
 */
public class Ingredient {

  private StringProperty name = new SimpleStringProperty();
  private DoubleProperty amount = new SimpleDoubleProperty();
  private StringProperty unit = new SimpleStringProperty();

  public Ingredient(String name, double amount, String unit) {
    this.setName(name);
    this.setAmount(amount);
    this.setUnit(unit);
  }

  public StringProperty nameProperty() {
    return name;
  }

  public String getName() {
    return name.get();
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public DoubleProperty amountPropery() {
    return amount;
  }

  public double getAmount() {
    return amount.get();
  }

  public void setAmount(double amount) {
    this.amount.set(amount);
  }

  public StringProperty unitPropery() {
    return unit;
  }

  public String getUnit() {
    return unit.get();
  }

  public void setUnit(String unit) {
    this.unit.set(unit);
  }
}
