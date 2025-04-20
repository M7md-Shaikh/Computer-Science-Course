package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.*;

public class ManagerLoginController {

    private TextField tfUsername = new TextField();
    private PasswordField pfPassword = new PasswordField();

    public void show() {
        Stage stage = new Stage();

        // Create a root HBox to hold the image and the GridPane
        HBox root = new HBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // Set background image on the left
        Image image = new Image("file:C:/Users/mhmds/Pictures/Saved Pictures/shamasneh.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);

        // Create the GridPane for the login form
        GridPane formPane = new GridPane();
        formPane.setAlignment(Pos.CENTER);
        formPane.setHgap(10);
        formPane.setVgap(10);
        formPane.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 20; -fx-border-width: 2; -fx-border-color: #d3d3d3; -fx-border-radius: 10;");

        Label titleLabel = new Label("Database Manager Login");
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle("-fx-text-fill: #555;");
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-text-fill: #555;");

        tfUsername.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-padding: 5;");
        pfPassword.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-padding: 5;");

        formPane.add(titleLabel, 0, 0, 2, 1);
        formPane.add(usernameLabel, 0, 1);
        formPane.add(tfUsername, 1, 1);
        formPane.add(passwordLabel, 0, 2);
        formPane.add(pfPassword, 1, 2);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> handleLogin(stage));
        loginButton.setStyle("-fx-background-color: #34495E; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-border-radius: 5; -fx-padding: 10 20;");

        loginButton.setOnMouseEntered(e -> loginButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-border-radius: 5; -fx-padding: 10 20;"));
        loginButton.setOnMouseExited(e -> loginButton.setStyle("-fx-background-color: #34495E; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-border-radius: 5; -fx-padding: 10 20;"));

        formPane.add(loginButton, 0, 3, 2, 1);

        // Add the image and the formPane to the root HBox
        root.getChildren().addAll(imageView, formPane);

        Scene scene = new Scene(root, 600, 300);
        stage.setScene(scene);
        stage.setTitle("Manager Login");
        stage.show();
    }

    private void handleLogin(Stage stage) {
        String username = tfUsername.getText();
        String password = pfPassword.getText();

        if (isValidManager(username, password)) {
            showDatabaseManagerScreen();
            stage.close();
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    private boolean isValidManager(String username, String password) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM DatabaseManager WHERE username = ? AND password = ?")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void showDatabaseManagerScreen() {
        new DatabaseManagerController().show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
