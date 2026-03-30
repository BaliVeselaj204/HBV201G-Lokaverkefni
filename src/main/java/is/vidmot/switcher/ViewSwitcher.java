package is.vidmot.switcher;

import is.vidmot.controller.*;
import is.vinnsla.Recipe;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * ViewSwitcher
 */
public class ViewSwitcher {

  private static final Map<View, Parent> cache = new HashMap<>();
  private static Scene scene;

  /**
   * @param scene
   *
   *              Setur senu
   */
  public static void setScene(Scene scene) {
    ViewSwitcher.scene = scene;
  }

  /**
   * @param view
   *
   *             Overloaded method ef sett inn er bara View
   */
  public static void switchTo(View view) {
    switchTo(view, true, null);
  }

  /**
   * @param view
   * @param cacheView
   * @param data
   *
   *                  Skiptir um View
   *
   *                  view parameter til að breyta View
   *                  cacheView parameter til að geyma í hakkatöflu eða hlaða upp
   *                  á nýtt (true eða false)
   *                  data parameter fyrir gögn sem við viljum í næsta glugga
   *                  (null fyrir ekkert data)
   */
  public static void switchTo(View view, boolean cacheView, Recipe data) {
    if (scene == null) {
      System.out.println("No scene was set");
      return;
    }

    try {
      Parent root;

      if (cache.containsKey(view) && cacheView) {
        System.out.println("Loading from cache");
        root = cache.get(view);
      } else {
        System.out.println("Loading from FXML");
        FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFileName()));
        root = loader.load();

        if (cacheView) {
          cache.put(view, root);
        }

        Object controller = loader.getController();
        if (controller instanceof DataInterface receiver && data != null) {
          receiver.setGogn(data);
        }
      }

      scene.setRoot(root);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
