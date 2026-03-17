package is.vidmot.controller;

import is.vidmot.view.FerdSpjald;
import is.vinnsla.Ferd;
import javafx.fxml.FXML;

public class FerdDialogController implements GognInterface {

  @FXML
  private FerdSpjald fxFerdSpjald;

  private Ferd ferd;

  @Override
  public void setGogn(Ferd ferd) {
    this.ferd = ferd;
    fxFerdSpjald.setEditable(true);
    fxFerdSpjald.getHeitiProperty().bindBidirectional(ferd.getFerd());
    fxFerdSpjald.getAfangastadurProperty().bindBidirectional(ferd.getStadur());
    fxFerdSpjald.getDagsetningProperty().bindBidirectional(ferd.getDate());
  }

  public Ferd getFerd() {
    return ferd;
  }

  /**
   * @return
   *
   *         Skoðar hvort TextField sé tómt þegar við búum til nýjar ferðir
   */
  public boolean erTomur() {
    return fxFerdSpjald.getHeitiProperty().get().isBlank() ||
        fxFerdSpjald.getAfangastadurProperty().get().isBlank() ||
        fxFerdSpjald.getDagsetningProperty().get().isBlank();
  }
}
