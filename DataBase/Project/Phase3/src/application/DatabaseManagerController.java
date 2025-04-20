package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DatabaseManagerController {

    private BorderPane mainPane = new BorderPane(); // Central layout for dynamic content

    public void show() {
        Stage stage = new Stage();

        // Header
        Label headerLabel = new Label("Database Manager Dashboard");
        headerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10px;");
        HBox header = new HBox(headerLabel);
        header.setStyle("-fx-background-color: #2C3E50; -fx-alignment: center; -fx-padding: 10;");
        headerLabel.setStyle("-fx-text-fill: white;");

        // Sidebar
        VBox sidebar = createSidebar();

        // Footer (optional)
        Label footerLabel = new Label("© 2025 Database Manager");
        footerLabel.setStyle("-fx-padding: 5px;");
        HBox footer = new HBox(footerLabel);
        footer.setStyle("-fx-background-color: #2C3E50; -fx-alignment: center;");
        footerLabel.setStyle("-fx-text-fill: white;");

        // Create an ImageView to display the image in the center
        Image image = new Image("file:C:/Users/mhmds/Pictures/Saved Pictures/shamasneh.jpg"); // Update the path to your image file
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(600);  // Adjust the width of the image
        imageView.setPreserveRatio(true);  // Maintain aspect ratio of the image

        // Set the image to the center of the BorderPane
        mainPane.setTop(header);
        mainPane.setLeft(sidebar);
        mainPane.setBottom(footer);
        mainPane.setCenter(imageView);  // Add image to the center

        // Set the scene
        Scene scene = new Scene(mainPane, 1000, 700);
        stage.setScene(scene);
        stage.setTitle("Database Manager");
        stage.show();
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #34495E;");

        // Create buttons
        Button managerButton = createStyledButton("Manage Managers");
        Button customerButton = createStyledButton("Manage Customers");
        Button orderButton = createStyledButton("Manage Orders");
        Button productButton = createStyledButton("Manage Products");
        Button driverButton = createStyledButton("Manage Drivers");
        Button carButton = createStyledButton("Manage Cars");
        Button processOrderButton = createStyledButton("Process Pending Orders");

        // Set actions for buttons
        managerButton.setOnAction(e -> showManagerScreen());
        customerButton.setOnAction(e -> showCustomerScreen());
        orderButton.setOnAction(e -> showOrderScreen());
        productButton.setOnAction(e -> showProductScreen());
        driverButton.setOnAction(e -> showDriverScreen());
        carButton.setOnAction(e -> showCarScreen());
        processOrderButton.setOnAction(e -> showManagerOrderProcessingScreen());

        // Logo Image
        Image logoImage = new Image("file:C:/Users/mhmds/Pictures/Saved Pictures/shamasneh.jpg");  // Use the correct path to your logo
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitHeight(100);  // Adjust the height of the logo
        logoImageView.setFitWidth(400);
        logoImageView.setPreserveRatio(true);  // Maintain aspect ratio of the logo

        // Add buttons and logo to the sidebar
        sidebar.getChildren().addAll(logoImageView,managerButton, customerButton, orderButton, productButton, driverButton, carButton, processOrderButton);
        
        return sidebar;
    }



    // Show the Manager interface in the center pane
    private void showManagerScreen() {
        ManagerController managerController = new ManagerController();
        mainPane.setCenter(managerController.getView());
    }

    // Show the Customer interface in the center pane
    private void showCustomerScreen() {
        CustomerController cc = new CustomerController();
        mainPane.setCenter(cc.getView());
    }

    // Show the Product interface in the center pane
    private void showProductScreen() {
        ProductController pc = new ProductController();
        mainPane.setCenter(pc.getView());
    }

    // Show the Driver interface in the center pane
    private void showDriverScreen() {
        DriverController dc = new DriverController();
        mainPane.setCenter(dc.getView());
    }

    // Show the Car interface in the center pane
    private void showCarScreen() {
        CarController cc = new CarController();
        mainPane.setCenter(cc.getView());
    }

    // Show the Order interface in the center pane
    private void showOrderScreen() {
        OrderController oc = new OrderController();
        mainPane.setCenter(oc.getView());
    }

    // Show the Order Processing interface in the center pane
    private void showManagerOrderProcessingScreen() {
        ManagerOrderProcessingController mopc = new ManagerOrderProcessingController();
        mainPane.setCenter(mopc.getView());
    }

    // Styled button creation
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #2C3E50; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 200;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #1ABC9C; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 200;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #2C3E50; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 200;"));
        return button;
    }
}
