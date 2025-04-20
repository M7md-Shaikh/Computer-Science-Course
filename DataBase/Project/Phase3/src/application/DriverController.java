package application;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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


public class DriverController {

    private VBox root = new VBox(10);
    private TableView<Driver> tableView = new TableView<>();
    private ObservableList<Driver> data = FXCollections.observableArrayList();
    private TextField tfDriverId = new TextField();
    private TextField tfManagerId = new TextField();
    private TextField tfName = new TextField();
    private TextField tfPhone = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfCurrentDriverId = new TextField();
    private TextField tfNewDriverId = new TextField();
    private TextField tfNewManagerId = new TextField();

    public DriverController() {
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Manage Drivers");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
        Button insertButton = createStyledButton("Insert Driver");
        insertButton.setOnAction(e -> showInsertScreen());

        Button updateButton = createStyledButton("Update Driver");
        updateButton.setOnAction(e -> showUpdateScreen());

        Button deleteButton = createStyledButton("Delete Driver");
        deleteButton.setOnAction(e -> showDeleteScreen());

        tableView.getColumns().addAll(
                createTableColumn("Driver ID", "driverId"),
                createTableColumn("Manager ID", "managerId"),
                createTableColumn("Name", "name"),
                createTableColumn("Phone", "phone"),
                createTableColumn("Address", "address")
        );

        HBox hb = new HBox(10);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(insertButton, updateButton, deleteButton);
        root.getChildren().addAll(titleLabel,hb, tableView);

        loadData();
    }

    public VBox getView() {
        return root; // Returns the VBox containing the driver interface
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
    
    private void loadData() {
        data.clear();
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Driver")) {
            while (rs.next()) {
                data.add(new Driver(rs.getInt("Driver_ID"), rs.getInt("Manager_ID"), rs.getString("Name"), rs.getString("Phone"), rs.getString("Address")));
            }
            tableView.setItems(data);
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }
    }


    private void showInsertScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Label titleLabel = new Label("Insert Driver");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Driver ID:"), tfDriverId);
        gridPane.addRow(1, new Label("Manager ID:"), tfManagerId);
        gridPane.addRow(2, new Label("Name:"), tfName);
        gridPane.addRow(3, new Label("Phone:"), tfPhone);
        gridPane.addRow(4, new Label("Address:"), tfAddress);

        Button insertButton = new Button("Insert");
        insertButton.setOnAction(e -> handleInsert());

        gridPane.addRow(5, insertButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Insert Driver");
        stage.show();
    }

    private void showUpdateScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Label titleLabel = new Label("Update Driver");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Current Driver ID:"), tfCurrentDriverId);
        gridPane.addRow(1, new Label("New Driver ID:"), tfNewDriverId);
        gridPane.addRow(2, new Label("New Manager Id:"), tfNewManagerId);
        gridPane.addRow(3, new Label("Name:"), tfName);
        gridPane.addRow(4, new Label("Phone:"), tfPhone);
        gridPane.addRow(5, new Label("Address:"), tfAddress);

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> handleUpdate());

        gridPane.addRow(6, updateButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Update Driver");
        stage.show();
    }

    private void showDeleteScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Label titleLabel = new Label("Delete Driver");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Driver ID:"), tfCurrentDriverId);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> handleDelete());

        gridPane.addRow(1, deleteButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Delete Driver");
        stage.show();
    }

    private void handleInsert() {
        try {
            int driverId = Integer.parseInt(tfDriverId.getText());
            int managerId = Integer.parseInt(tfManagerId.getText());
            String name = tfName.getText();
            String phone = tfPhone.getText();
            String address = tfAddress.getText();
            insertDriver(driverId, managerId, name, phone, address);
            loadData();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid IDs.");
            System.out.println(e.getMessage());
        } catch (SQLIntegrityConstraintViolationException e) {
            showAlert("Error", "Manager ID does not exist.");
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void handleUpdate() {
        try {
            int currentDriverId = Integer.parseInt(tfCurrentDriverId.getText());
            int newDriverId = Integer.parseInt(tfNewDriverId.getText());
            int newManagerId = Integer.parseInt(tfNewManagerId.getText());
            String name = tfName.getText();
            String phone = tfPhone.getText();
            String address = tfAddress.getText();

            if (!driverExists(currentDriverId)) {
                showAlert("Error", "Current Driver ID does not exist.");
                return;
            }

            updateDriver(currentDriverId, newDriverId, newManagerId, name, phone, address);
            loadData();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid IDs.");
            System.out.println(e.getMessage());
        } catch (SQLIntegrityConstraintViolationException e) {
            showAlert("Error", "New Driver ID already exists.");
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void handleDelete() {
        try {
            int driverId = Integer.parseInt(tfCurrentDriverId.getText());
            deleteDriver(driverId);
            loadData();
            clearFields();
        } catch (NumberFormatException | SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }


    private void insertDriver(int driverId, int managerId, String name, String phone, String address) throws SQLException {
        String sql = "INSERT INTO Driver (Driver_ID, Manager_ID, Name, Phone, Address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, driverId);
            pstmt.setInt(2, managerId);
            pstmt.setString(3, name);
            pstmt.setString(4, phone);
            pstmt.setString(5, address);
            pstmt.executeUpdate();
        }
    }

    public void updateDriver(int currentDriverId, int newDriverId, int newManagerId, String newName, String newPhone, String newAddress) throws SQLException {
        Connection conn = DatabaseConnector.getConnection();
        try {
            conn.setAutoCommit(false);

            // Disable foreign key checks
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
            }

            // Update Orders to new driver
            String updateOrdersSql = "UPDATE Orders SET Driver_ID = ? WHERE Driver_ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateOrdersSql)) {
                pstmt.setInt(1, newDriverId);
                pstmt.setInt(2, currentDriverId);
                pstmt.executeUpdate();
            }

            // Update Car to new driver
            String updateCarSql = "UPDATE Car SET Driver_ID = ? WHERE Driver_ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateCarSql)) {
                pstmt.setInt(1, newDriverId);
                pstmt.setInt(2, currentDriverId);
                pstmt.executeUpdate();
            }

            // Now update the driver
            String updateDriverSql = "UPDATE Driver SET Driver_ID = ?, Manager_ID = ?, Name = ?, Phone = ?, Address = ? WHERE Driver_ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateDriverSql)) {
                pstmt.setInt(1, newDriverId);
                pstmt.setInt(2, newManagerId);
                pstmt.setString(3, newName);
                pstmt.setString(4, newPhone);
                pstmt.setString(5, newAddress);
                pstmt.setInt(6, currentDriverId);
                pstmt.executeUpdate();
            }

            // Re-enable foreign key checks
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
            }

            // Commit transaction
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    private boolean managerExists(int managerId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Manager WHERE Manager_ID = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, managerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    private void deleteDriver(int driverId) throws SQLException {
        String sql = "DELETE FROM Driver WHERE Driver_ID = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, driverId);
            pstmt.executeUpdate();
        }
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
        tfDriverId.clear();
        tfManagerId.clear();
        tfName.clear();
        tfPhone.clear();
        tfAddress.clear();
        tfCurrentDriverId.clear();
        tfNewDriverId.clear();
        tfNewManagerId.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private TableColumn<Driver, String> createTableColumn(String title, String property) {
        TableColumn<Driver, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        return col;
    }
}
