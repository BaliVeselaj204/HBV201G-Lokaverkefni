package is.vidmot.view;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RecipeSpjald extends VBox {

  @FXML
  private TextField heitiField;

  @FXML
  private TextField afangastadurField;

  @FXML
  private TextField dagsetningField;

  private SimpleStringProperty heiti = new SimpleStringProperty();
  private SimpleStringProperty afangastadur = new SimpleStringProperty();
  private SimpleStringProperty dagsetning = new SimpleStringProperty();

  public RecipeSpjald() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/is/vidmot/recipe-spjald.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    try {
      fxmlLoader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    heitiField.textProperty().bindBidirectional(heiti);
    afangastadurField.textProperty().bindBidirectional(afangastadur);
    dagsetningField.textProperty().bindBidirectional(dagsetning);
  }

  public void setEditable(boolean editable) {
    heitiField.setEditable(editable);
    afangastadurField.setEditable(editable);
    dagsetningField.setEditable(editable);
  }

  public SimpleStringProperty getHeitiProperty() {
    return heiti;
  }

  public SimpleStringProperty getAfangastadurProperty() {
    return afangastadur;
  }

  public SimpleStringProperty getDagsetningProperty() {
    return dagsetning;
  }
}
