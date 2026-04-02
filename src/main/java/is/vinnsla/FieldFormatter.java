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
    TextFormatter<Integer> formatter = new TextFormatter<Integer>(
        new IntegerStringConverter(),
        0,
        intFilter);
    field.setTextFormatter(formatter);
    formatter.valueProperty().bindBidirectional(property.asObject());
  }

  public void bindDoubleField(TextField field, DoubleProperty property) {
    TextFormatter<Double> formatter = new TextFormatter<Double>(
        new DoubleStringConverter(),
        0.0,
        doubleFilter);
    field.setTextFormatter(formatter);
    formatter.valueProperty().bindBidirectional(property.asObject());
  }
}
