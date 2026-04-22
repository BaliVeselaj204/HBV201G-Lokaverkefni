package is.vinnsla;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

/**
 * DatabaseManager
 */
public class DatabaseManager {

  private static final String DB_URL = "jdbc:sqlite:recipes.db";

  /**
   * Búa til gagnagrunn
   */
  public static void initialize() {
    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {

      stmt.executeUpdate("""
              CREATE TABLE IF NOT EXISTS recipes (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  name TEXT NOT NULL,
                  description TEXT,
                  cookTime INTEGER,
                  calories INTEGER,
                  protein REAL,
                  carbs REAL,
                  fat REAL,
                  servings INTEGER,
                  difficulty TEXT,
                  imagePath TEXT
              )
          """);

      stmt.executeUpdate("""
              CREATE TABLE IF NOT EXISTS ingredients (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  recipe_id INTEGER NOT NULL,
                  name TEXT,
                  amount REAL,
                  unit TEXT,
                  FOREIGN KEY (recipe_id) REFERENCES recipes(id) ON DELETE CASCADE
              )
          """);

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Setja uppskrift inn í gagnagrunn
   * 
   * @param recipe
   */
  public static void saveRecipe(Recipe recipe) {
    String sql = """
            INSERT INTO recipes (name, description, cookTime, calories, protein, carbs, fat, servings, difficulty, imagePath)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

    try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      pstmt.setString(1, recipe.getName());
      pstmt.setString(2, recipe.getDescription());
      pstmt.setInt(3, recipe.getCookTime());
      pstmt.setInt(4, recipe.getCalories());
      pstmt.setDouble(5, recipe.getProtein());
      pstmt.setDouble(6, recipe.getCarbs());
      pstmt.setDouble(7, recipe.getFat());
      pstmt.setInt(8, recipe.getServings());
      pstmt.setString(9, recipe.getDifficulty());
      pstmt.setString(10, recipe.getImagePath());
      pstmt.executeUpdate();

      ResultSet keys = pstmt.getGeneratedKeys();
      if (keys.next()) {
        int recipeId = keys.getInt(1);
        saveIngredients(conn, recipeId, recipe.getIngredientsList());
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Setja inn hráefni fyrir hverja uppskrift
   * 
   * @param conn
   * @param recipeId
   * @param ingredients
   * @throws SQLException
   */
  private static void saveIngredients(Connection conn, int recipeId, List<Ingredient> ingredients) throws SQLException {
    String sql = "INSERT INTO ingredients (recipe_id, name, amount, unit) VALUES (?, ?, ?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      for (Ingredient ingredient : ingredients) {
        pstmt.setInt(1, recipeId);
        pstmt.setString(2, ingredient.getName());
        pstmt.setDouble(3, ingredient.getAmount());
        pstmt.setString(4, ingredient.getUnit());
        pstmt.addBatch();
      }
      pstmt.executeBatch();
    }
  }

  /**
   * Birta allar uppskriftir með SELECT fyrirspurn
   * 
   * @return
   */
  public static List<Recipe> loadAllRecipes() {
    List<Recipe> recipes = new ArrayList<>();
    String sql = "SELECT * FROM recipes";

    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        int id = rs.getInt("id");

        String imagePath = rs.getString("imagePath");
        Image image = null;
        if (imagePath != null) {
          image = new Image(new java.io.File(imagePath).toURI().toString());
        }

        Recipe recipe = new Recipe(
            rs.getString("name"),
            rs.getString("description"),
            rs.getInt("cookTime"),
            rs.getInt("calories"),
            rs.getDouble("protein"),
            rs.getDouble("carbs"),
            rs.getDouble("fat"),
            rs.getInt("servings"),
            rs.getString("difficulty"),
            loadIngredients(conn, id),
            image);
        recipe.setId(id);
        recipe.setImagePath(imagePath);
        recipes.add(recipe);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return recipes;
  }

  /**
   * Birta öll hráefni fyrir hverja uppskrift
   * 
   * @param conn
   * @param recipeId
   * @return
   * @throws SQLException
   */
  private static List<Ingredient> loadIngredients(Connection conn, int recipeId) throws SQLException {
    List<Ingredient> ingredients = new ArrayList<>();
    String sql = "SELECT * FROM ingredients WHERE recipe_id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, recipeId);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        ingredients.add(new Ingredient(
            rs.getString("name"),
            rs.getDouble("amount"),
            rs.getString("unit")));
      }
    }
    return ingredients;
  }

  /**
   * Eyða uppskrift úr gagnagrunn
   * 
   * @param recipeName
   */
  public static void deleteRecipe(String recipeName) {
    String sql = "DELETE FROM recipes WHERE name = ?";
    try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, recipeName);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * update recipe í gagnagrunn
   * 
   * @param recipeId
   * @param recipe
   */
  public static void updateRecipe(int recipeId, Recipe recipe) {
    String sql = """
            UPDATE recipes
            SET name = ?, description = ?, cookTime = ?, calories = ?, protein = ?,
                carbs = ?, fat = ?, servings = ?, difficulty = ?, imagePath = ?
            WHERE id = ?
        """;

    try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, recipe.getName());
      pstmt.setString(2, recipe.getDescription());
      pstmt.setInt(3, recipe.getCookTime());
      pstmt.setInt(4, recipe.getCalories());
      pstmt.setDouble(5, recipe.getProtein());
      pstmt.setDouble(6, recipe.getCarbs());
      pstmt.setDouble(7, recipe.getFat());
      pstmt.setInt(8, recipe.getServings());
      pstmt.setString(9, recipe.getDifficulty());
      pstmt.setString(10, recipe.getImagePath());
      pstmt.setInt(11, recipeId);
      pstmt.executeUpdate();

      deleteIngredients(conn, recipeId);
      saveIngredients(conn, recipeId, recipe.getIngredientsList());

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Eyða hráefni þegar eytt eru uppskrift
   * 
   * @param conn
   * @param recipeId
   * @throws SQLException
   */
  private static void deleteIngredients(Connection conn, int recipeId) throws SQLException {
    String sql = "DELETE FROM ingredients WHERE recipe_id = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, recipeId);
      pstmt.executeUpdate();
    }
  }

  /**
   * Eyða valið hráefni
   * 
   * @param ingredient
   */
  public static void deleteIngredient(Ingredient ingredient) {
    String sql = "DELETE FROM ingredients WHERE name = ?";
    try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, ingredient.getName());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Geyma eitt hráefni
   * 
   * @param recipeId
   * @param ingredient
   */
  public static void saveIngredient(int recipeId, Ingredient ingredient) {
    String sql = "INSERT INTO ingredients (recipe_id, name, amount, unit) VALUES (?, ?, ?, ?)";
    try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, recipeId);
      pstmt.setString(2, ingredient.getName());
      pstmt.setDouble(3, ingredient.getAmount());
      pstmt.setString(4, ingredient.getUnit());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
