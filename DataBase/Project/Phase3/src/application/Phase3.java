package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Phase3 extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane bp = new BorderPane();
        VBox root = new VBox(20);
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.setStyle("-fx-padding: 20px; -fx-background-color: transparent;");  // Set background to transparent

        Label title = new Label("Shamasneh Concrete System");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Button visitorButton = createStyledButton("Visitor - Place Order & Show Orders");
        visitorButton.setOnAction(e -> showVisitorScreen());

        Button managerButton = createStyledButton("Database Manager");
        managerButton.setOnAction(e -> showManagerLoginScreen());

        root.getChildren().addAll(title, visitorButton, managerButton);

        // Add background image
        Image wallpaperImage = new Image("file:C:/Users/mhmds/Pictures/Saved Pictures/Concrete.jpg");

        BackgroundImage backgroundImage = new BackgroundImage(
            wallpaperImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );

        Background background = new Background(backgroundImage);

        bp.setCenter(root);
        bp.setBackground(background);

        Scene scene = new Scene(bp, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Shamasneh Concrete System");
        primaryStage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #34495E; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-background-radius: 20px;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #45A049; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-background-radius: 20px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #34495E; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-background-radius: 20px;"));
        button.setMaxWidth(400);
        return button;
    }

    private void showVisitorScreen() {
        new CustomerContactOrderController().show();
    }

    private void showManagerLoginScreen() {
        new ManagerLoginController().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
