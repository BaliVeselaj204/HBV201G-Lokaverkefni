module is.vidmot {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.base;
  requires java.sql;

  exports is.vidmot.view;

  opens is.vidmot to javafx.fxml;
  opens is.vidmot.controller to javafx.fxml;
  opens is.vidmot.view to javafx.fxml;

  exports is.vidmot;
}
