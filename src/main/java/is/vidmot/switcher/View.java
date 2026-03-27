package is.vidmot.switcher;

public enum View {
  MAIN("/is/vidmot/main-view.fxml"),
  FERD("/is/vidmot/recipe-view.fxml");

  private String fileName;

  View(String filename) {
    this.fileName = filename;
  }

  public String getFileName() {
    return fileName;
  }
}
