package is.vidmot.switcher;

public enum View {
  MAIN("/is/vidmot/adal-view.fxml"),
  FERD("/is/vidmot/ferd-view.fxml");

  private String fileName;

  View(String filename) {
    this.fileName = filename;
  }

  public String getFileName() {
    return fileName;
  }
}
