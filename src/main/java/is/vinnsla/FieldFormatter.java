package is.vinnsla;

import java.util.function.UnaryOperator;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.DoubleStringConverter;

/**
 * FieldFormatter
 */
public class FieldFormatter {

  UnaryOperator<TextFormatter.Change> intFilter = change -> {
    String newText = change.getControlNewText();
    return newText.matches("\\d*") ? change : null;
  };

  UnaryOperator<TextFormatter.Change> doubleFilter = change -> {
    String newText = change.getControlNewText();
    return newText.matches("\\d*(\\.\\d*)?") ? change : null;
  };

  public void bindIntegerField(TextField field, IntegerProperty property) {
    TextFormatter<Integer> formatter = new TextFormatter<>(
        new IntegerStringConverter(),
        property.get(),
        intFilter);
    field.setTextFormatter(formatter);

    formatter.valueProperty().addListener((obs, oldVal, newVal) -> {
      if (newVal != null)
        property.set(newVal);
    });

    property.addListener((obs, oldVal, newVal) -> {
      formatter.setValue(newVal.intValue());
    });
  }

  public void bindDoubleField(TextField field, DoubleProperty property) {
    TextFormatter<Double> formatter = new TextFormatter<>(
        new DoubleStringConverter(),
        property.get(),
        doubleFilter);
    field.setTextFormatter(formatter);

    formatter.valueProperty().addListener((obs, oldVal, newVal) -> {
      if (newVal != null)
        property.set(newVal);
    });

    property.addListener((obs, oldVal, newVal) -> {
      formatter.setValue(newVal.doubleValue());
    });
  }
}
