package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class CarController {
	VBox root = new VBox(20);
	private TextField tfRegNumber = new TextField();
	private TextField tfCarType = new TextField();
	private TextField tfCarColor = new TextField();
	private TextField tfDriverId = new TextField();
	private TextField tfCurrentRegNumber = new TextField();
	private TextField tfNewRegNumber = new TextField();
	private TableView<Car> tableView = new TableView<>();
	private ObservableList<Car> data = FXCollections.observableArrayList();
    public CarController() {
    	root.setPadding(new javafx.geometry.Insets(20));
    	root.setAlignment(javafx.geometry.Pos.CENTER);

        Label titleLabel = new Label("Manage Cars");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button insertButton = createStyledButton("Insert Car");
        insertButton.setOnAction(e -> showInsertScreen());

        Button updateButton = createStyledButton("Update Car");
        updateButton.setOnAction(e -> showUpdateScreen());

        Button deleteButton = createStyledButton("Delete Car");
        deleteButton.setOnAction(e -> showDeleteScreen());

        tableView.getColumns().addAll(
            createTableColumn("Reg Number", "regNumber"),
            createTableColumn("Car Type", "carType"),
            createTableColumn("Car Color", "carColor"),
            createTableColumn("Driver ID", "driverId")
        );

        HBox hb = new HBox(10);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(insertButton, updateButton, deleteButton);
        root.getChildren().addAll(titleLabel, hb, tableView);

        loadData();
    }

    public VBox getView() {
        return root; // Returns the VBox containing the car interface
    }
   
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(
            "-fx-background-color: #34495E; " +           // Button background color
            "-fx-text-fill: white; " +                      // Button text color
            "-fx-font-size: 14px; " +                       // Font size
            "-fx-padding: 10px 20px; " +                    // Padding around the text
            "-fx-border-radius: 5px; " +                    // Rounded corners
            "-fx-background-radius: 5px; " +                // Rounded background
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 2, 2);"  // Shadow effect
        );
        return button;
    }

    private void showInsertScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Label titleLabel = new Label("Insert Car");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Reg Number:"), tfRegNumber);
        gridPane.addRow(1, new Label("Car Type:"), tfCarType);
        gridPane.addRow(2, new Label("Car Color:"), tfCarColor);
        gridPane.addRow(3, new Label("Driver ID:"), tfDriverId);

        Button insertButton = new Button("Insert");
        insertButton.setOnAction(e -> handleInsert());

        gridPane.addRow(4, insertButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Insert Car");
        stage.show();
    }

    private void showUpdateScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Label titleLabel = new Label("Update Car");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Current Reg Number:"), tfCurrentRegNumber);
        gridPane.addRow(1, new Label("New Reg Number:"), tfNewRegNumber);
        gridPane.addRow(2, new Label("Car Color:"), tfCarColor);
        gridPane.addRow(3, new Label("Driver ID:"), tfDriverId);

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> handleUpdate());

        gridPane.addRow(4, updateButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Update Car");
        stage.show();
    }

    private void showDeleteScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Label titleLabel = new Label("Delete Car");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Reg Number:"), tfCurrentRegNumber);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> handleDelete());

        gridPane.addRow(1, deleteButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Delete Car");
        stage.show();
    }

    private void handleInsert() {
        try {
            int regNumber = Integer.parseInt(tfRegNumber.getText());
            String carType = tfCarType.getText();
            String carColor = tfCarColor.getText();
            int driverId = Integer.parseInt(tfDriverId.getText());

            if (!driverExists(driverId)) {
                showAlert("Error", "Driver ID does not exist.");
                return;
            }

            insertCar(regNumber, carType, carColor, driverId);
            loadData();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid IDs.");
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void handleUpdate() {
        try {
            int currentRegNumber = Integer.parseInt(tfCurrentRegNumber.getText());
            int newRegNumber = Integer.parseInt(tfNewRegNumber.getText());
            String carColor = tfCarColor.getText();
            int driverId = Integer.parseInt(tfDriverId.getText());

            if (!carExists(currentRegNumber)) {
                showAlert("Error", "Current Reg Number does not exist.");
                return;
            }

            if (!driverExists(driverId)) {
                showAlert("Error", "Driver ID does not exist.");
                return;
            }

            updateCar(currentRegNumber, newRegNumber, carColor, driverId);
            loadData();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid IDs.");
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void handleDelete() {
        try {
            int regNumber = Integer.parseInt(tfCurrentRegNumber.getText());
            deleteCar(regNumber);
            loadData();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid Reg Number.");
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void loadData() {
        data.clear();
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Car")) {
            while (rs.next()) {
                data.add(new Car(rs.getInt("Reg_Number"), rs.getString("Car_type"), rs.getString("Car_color"), rs.getInt("Driver_ID")));
            }
            tableView.setItems(data);
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }
    }

    private void insertCar(int regNumber, String carType, String carColor, int driverId) throws SQLException {
        String sql = "INSERT INTO Car (Reg_Number, Car_type, Car_color, Driver_ID) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, regNumber);
            pstmt.setString(2, carType);
            pstmt.setString(3, carColor);
            pstmt.setInt(4, driverId);
            pstmt.executeUpdate();
        }
    }

    private void updateCar(int currentRegNumber, int newRegNumber, String carColor, int driverId) throws SQLException {
        String sql = "UPDATE Car SET Reg_Number = ?, Car_color = ?, Driver_ID = ? WHERE Reg_Number = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newRegNumber);
            pstmt.setString(2, carColor);
            pstmt.setInt(3, driverId);
            pstmt.setInt(4, currentRegNumber);
            pstmt.executeUpdate();
        }
    }

    private void deleteCar(int regNumber) throws SQLException {
        String sql = "DELETE FROM Car WHERE Reg_Number = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, regNumber);
            pstmt.executeUpdate();
        }
    }

    private boolean carExists(int regNumber) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Car WHERE Reg_Number = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, regNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    private boolean driverExists(int driverId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Driver WHERE Driver_ID = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, driverId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    private void clearFields() {
        tfRegNumber.clear();
        tfCarType.clear();
        tfCarColor.clear();
        tfDriverId.clear();
        tfCurrentRegNumber.clear();
        tfNewRegNumber.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private TableColumn<Car, String> createTableColumn(String title, String property) {
        TableColumn<Car, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        return col;
    }
}
