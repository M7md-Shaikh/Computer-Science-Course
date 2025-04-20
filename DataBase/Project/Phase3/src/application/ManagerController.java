package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class ManagerController {
    private TextField tfManagerId = new TextField();
    private TextField tfName = new TextField();
    private TextField tfPhone = new TextField();
    private ComboBox<String> cbAddress = new ComboBox<>();
    private TextField tfCurrentManagerId = new TextField();
    private ComboBox<String> cbCurrentAddress = new ComboBox<>();
    private TextField tfNewManagerId = new TextField();
    private TableView<Manager> tableView = new TableView<>();
    private ObservableList<Manager> data = FXCollections.observableArrayList();
    
    private VBox root = new VBox(10);

    public ManagerController() {
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Manage Managers");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button insertButton = createStyledButton("Insert Manager");
        insertButton.setOnAction(e -> showInsertScreen());

        Button updateButton = createStyledButton("Update Manager");
        updateButton.setOnAction(e -> showUpdateScreen());

        Button deleteButton = createStyledButton("Delete Manager");
        deleteButton.setOnAction(e -> showDeleteScreen());


        tableView.getColumns().addAll(
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
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Insert Manager");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Manager ID:"), tfManagerId);
        gridPane.addRow(1, new Label("Name:"), tfName);
        gridPane.addRow(2, new Label("Phone:"), tfPhone);
        gridPane.addRow(3, new Label("Address:"), cbAddress);

        cbAddress.setItems(FXCollections.observableArrayList(getCities()));

        Button insertButton = new Button("Insert");
        insertButton.setOnAction(e -> handleInsert());

        gridPane.addRow(4, insertButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Insert Manager");
        stage.show();
    }

    private void showUpdateScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Update Manager");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Current Manager ID:"), tfCurrentManagerId);
        gridPane.addRow(1, new Label("New Manager ID:"), tfNewManagerId);
        gridPane.addRow(2, new Label("Name:"), tfName);
        gridPane.addRow(3, new Label("Phone:"), tfPhone);
        gridPane.addRow(4, new Label("Address:"), cbAddress);

        cbAddress.setItems(FXCollections.observableArrayList(getCities()));

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> handleUpdate());

        gridPane.addRow(5, updateButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Update Manager");
        stage.show();
    }

    private void showDeleteScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Delete Manager");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Manager ID:"), tfCurrentManagerId);
        gridPane.addRow(1, new Label("Address:"), cbCurrentAddress);

        cbCurrentAddress.setItems(FXCollections.observableArrayList(getCities()));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> handleDelete());

        gridPane.addRow(2, deleteButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Delete Manager");
        stage.show();
    }

    private void handleInsert() {
        try {
            int managerId = Integer.parseInt(tfManagerId.getText());
            String name = tfName.getText();
            String phone = tfPhone.getText();
            String address = cbAddress.getValue();
            insertManager(managerId, name, phone, address);
            loadData();
            clearFields();
        } catch (NumberFormatException | SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                showAlert("Error", "Manager ID already exists.");
            } else {
                showAlert("Error", "Invalid input or database error.");
            }
            System.out.println(e.getMessage());
        }
    }

    private void handleUpdate() {
        try {
            int currentManagerId = Integer.parseInt(tfCurrentManagerId.getText());
            int newManagerId = Integer.parseInt(tfNewManagerId.getText());
            String name = tfName.getText();
            String phone = tfPhone.getText();
            String address = cbAddress.getValue();
            
            if (!managerExists(currentManagerId)) {
                showAlert("Error", "Current Manager ID does not exist.");
                return;
            }
            
            updateManager(currentManagerId, newManagerId, name, phone, address);
            loadData();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid IDs.");
            System.out.println(e.getMessage());
        } catch (SQLIntegrityConstraintViolationException e) {
            showAlert("Error", "New Manager ID already exists.");
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void handleDelete() {
        try {
            int managerId = Integer.parseInt(tfCurrentManagerId.getText());
            String address = cbCurrentAddress.getValue();
            deleteManager(managerId, address);
            loadData();
            clearFields();
        } catch (NumberFormatException | SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void loadData() {
        data.clear();
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Manager")) {
            while (rs.next()) {
                data.add(new Manager(rs.getInt("Manager_ID"), rs.getString("Name"), rs.getString("Phone"), rs.getString("Address")));
            }
            tableView.setItems(data);
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }
    }
    
    public VBox getView() {
        return root; // Returns the VBox containing the manager interface
    }


    private void insertManager(int managerId, String name, String phone, String address) throws SQLException {
        String sql = "INSERT INTO Manager (Manager_ID, Name, Phone, Address) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, managerId);
            pstmt.setString(2, name);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.executeUpdate();
        }
    }

    public void updateManager(int currentManagerId, int newManagerId, String name, String phone, String address) throws SQLException {
        Connection conn = DatabaseConnector.getConnection();
        try {
            // Disable foreign key checks
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
            }

            // Update Manager_ID in Customer table
            String updateCustomerSQL = "UPDATE Customer SET Manager_ID = ? WHERE Manager_ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateCustomerSQL)) {
                pstmt.setInt(1, newManagerId);
                pstmt.setInt(2, currentManagerId);
                pstmt.executeUpdate();
            }

            // Update Manager_ID in Driver table
            String updateDriverSQL = "UPDATE Driver SET Manager_ID = ? WHERE Manager_ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateDriverSQL)) {
                pstmt.setInt(1, newManagerId);
                pstmt.setInt(2, currentManagerId);
                pstmt.executeUpdate();
            }

            // Update Manager_ID in Orders table
            String updateOrderSQL = "UPDATE Orders SET Manager_ID = ? WHERE Manager_ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateOrderSQL)) {
                pstmt.setInt(1, newManagerId);
                pstmt.setInt(2, currentManagerId);
                pstmt.executeUpdate();
            }

            // Update Manager_ID in Manager table
            String updateManagerSQL = "UPDATE Manager SET Manager_ID = ?, Name = ?, Phone = ?, Address = ? WHERE Manager_ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateManagerSQL)) {
                pstmt.setInt(1, newManagerId);
                pstmt.setString(2, name);
                pstmt.setString(3, phone);
                pstmt.setString(4, address);
                pstmt.setInt(5, currentManagerId);
                pstmt.executeUpdate();
            }

            // Re-enable foreign key checks
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            conn.close();
        }
    }

    public void deleteManager(int managerId, String address) throws SQLException {
        String deleteManagerSQL = "DELETE FROM Manager WHERE Manager_ID = ?";
        String updateDriverSQL = "UPDATE Driver SET Manager_ID = NULL WHERE Manager_ID = ?";
        String updateCustomerSQL = "UPDATE Customer SET Manager_ID = NULL WHERE Manager_ID = ?";
        String updateOrderSQL = "UPDATE Orders SET Manager_ID = NULL WHERE Manager_ID = ?";

        Connection conn = DatabaseConnector.getConnection();
        try {
            // Disable auto-commit mode
            conn.setAutoCommit(false);

            // Update Driver table
            try (PreparedStatement pstmtDriver = conn.prepareStatement(updateDriverSQL)) {
                pstmtDriver.setInt(1, managerId);
                pstmtDriver.executeUpdate();
            }

            // Update Customer table
            try (PreparedStatement pstmtCustomer = conn.prepareStatement(updateCustomerSQL)) {
                pstmtCustomer.setInt(1, managerId);
                pstmtCustomer.executeUpdate();
            }

            // Update Orders table
            try (PreparedStatement pstmtOrder = conn.prepareStatement(updateOrderSQL)) {
                pstmtOrder.setInt(1, managerId);
                pstmtOrder.executeUpdate();
            }

            // Delete Manager
            try (PreparedStatement pstmtManager = conn.prepareStatement(deleteManagerSQL)) {
                pstmtManager.setInt(1, managerId);
                pstmtManager.executeUpdate();
            }

            // Assign drivers to a new manager with the same address, if available
            String findManagerWithSameAddressSQL = "SELECT Manager_ID FROM Manager WHERE Address = ? LIMIT 1";
            try (PreparedStatement pstmtFindManager = conn.prepareStatement(findManagerWithSameAddressSQL)) {
                pstmtFindManager.setString(1, address);
                ResultSet rs = pstmtFindManager.executeQuery();
                if (rs.next()) {
                    int newManagerId = rs.getInt("Manager_ID");
                    String updateDriverWithNewManagerSQL = "UPDATE Driver SET Manager_ID = ? WHERE Address = ?";
                    try (PreparedStatement pstmtUpdateDriver = conn.prepareStatement(updateDriverWithNewManagerSQL)) {
                        pstmtUpdateDriver.setInt(1, newManagerId);
                        pstmtUpdateDriver.setString(2, address);
                        pstmtUpdateDriver.executeUpdate();
                    }

                    String updateOrderWithNewManagerSQL = "UPDATE Orders SET Manager_ID = ?, Status = 'Processed' WHERE Manager_ID IS NULL AND EXISTS (SELECT 1 FROM Customer c WHERE c.SSN = Orders.Customer_ID AND c.Address = ?)";
                    try (PreparedStatement pstmtUpdateOrder = conn.prepareStatement(updateOrderWithNewManagerSQL)) {
                        pstmtUpdateOrder.setInt(1, newManagerId);
                        pstmtUpdateOrder.setString(2, address);
                        pstmtUpdateOrder.executeUpdate();
                    }
                } else {
                    String updateOrderToPendingSQL = "UPDATE Orders SET Status = 'Pending' WHERE Manager_ID IS NULL";
                    try (PreparedStatement pstmtUpdateOrder = conn.prepareStatement(updateOrderToPendingSQL)) {
                        pstmtUpdateOrder.executeUpdate();
                    }
                }
            }

            // Commit the transaction
            conn.commit();
        } catch (SQLException e) {
            // Rollback the transaction if any error occurs
            conn.rollback();
            throw e;
        } finally {
            // Restore auto-commit mode
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

    private void clearFields() {
        tfCurrentManagerId.clear();
        tfNewManagerId.clear();
        tfManagerId.clear();
        tfName.clear();
        tfPhone.clear();
        cbAddress.setValue(null);
        cbCurrentAddress.setValue(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private TableColumn<Manager, String> createTableColumn(String title, String property) {
        TableColumn<Manager, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        return col;
    }


    private List<String> getCities() {
        // List of cities in Palestine
        return Arrays.asList(
                "al-Quds", "Ramallah", "Nablus", "Hebron", "Bethlehem",
                "Jenin", "Tulkarm", "Qalqilya", "Salfit", "Jericho"
        );
    }
}
