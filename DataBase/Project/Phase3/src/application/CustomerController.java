 
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

public class CustomerController {

    private VBox root = new VBox(10);
    private TextField tfNewManagerId = new TextField();
    private TextField tfManagerId = new TextField();
    private TextField tfName = new TextField();
    private TextField tfPhone = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfCurrentCustomerId = new TextField();
    private TextField tfNewCustomerId = new TextField();
    private TableView<Customer> tableView = new TableView<>();
    private ObservableList<Customer> data = FXCollections.observableArrayList();

    public CustomerController() {
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Manage Customers");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button insertButton = createStyledButton("Insert Customer");
        insertButton.setOnAction(e -> showInsertScreen());

        Button updateButton = createStyledButton("Update Customer");
        updateButton.setOnAction(e -> showUpdateScreen());

        Button deleteButton = createStyledButton("Delete Customer");
        deleteButton.setOnAction(e -> showDeleteScreen());


        tableView.getColumns().addAll(
                createTableColumn("Customer ID", "customerId"),
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

    public VBox getView() {
        return root; // Returns the VBox containing the customer interface
    }

    private void showDeleteScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Label titleLabel = new Label("Delete Customer");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Customer ID:"), tfCurrentCustomerId);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> handleDelete());

        gridPane.addRow(1, deleteButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Delete Customer");
        stage.show();
    }

	private void showInsertScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Label titleLabel = new Label("Insert Customer");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Manager ID:"), tfManagerId);
        gridPane.addRow(1, new Label("Name:"), tfName);
        gridPane.addRow(2, new Label("Phone:"), tfPhone);
        gridPane.addRow(3, new Label("Address:"), tfAddress);

        Button insertButton = new Button("Insert");
        insertButton.setOnAction(e -> handleInsert());

        gridPane.addRow(4, insertButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Insert Customer");
        stage.show();
    }

    private void showUpdateScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Label titleLabel = new Label("Update Customer");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Current Customer ID:"), tfCurrentCustomerId);
        gridPane.addRow(1, new Label("New Customer ID:"), tfNewCustomerId);
        gridPane.addRow(2, new Label("Name:"), tfName);
        gridPane.addRow(3, new Label("Phone:"), tfPhone);
        gridPane.addRow(4, new Label("Address:"), tfAddress);

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> handleUpdate());

        gridPane.addRow(5, updateButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Update Customer");
        stage.show();
    }

    private void showUpdateManagerScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Label titleLabel = new Label("Update Manager ID");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Customer ID:"), tfCurrentCustomerId);
        gridPane.addRow(1, new Label("New Manager ID:"), tfNewManagerId);

        Button updateManagerButton = new Button("Update");
        updateManagerButton.setOnAction(e -> handleUpdateManager());

        gridPane.addRow(2, updateManagerButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Update Manager ID");
        stage.show();
    }

    private void handleInsert() {
        try {
            int managerId = Integer.parseInt(tfManagerId.getText());
            String name = tfName.getText();
            String phone = tfPhone.getText();
            String address = tfAddress.getText();

            if (!managerExists(managerId)) {
                showAlert("Error", "Manager ID does not exist.");
                return;
            }

            insertCustomer(managerId, name, phone, address);
            loadData();
            clearFields();
        } catch (NumberFormatException | SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void handleUpdate() {
        try {
            int currentCustomerId = Integer.parseInt(tfCurrentCustomerId.getText());
            int newCustomerId = Integer.parseInt(tfNewCustomerId.getText());
            String name = tfName.getText();
            String phone = tfPhone.getText();
            String address = tfAddress.getText();

            if (!customerExists(currentCustomerId)) {
                showAlert("Error", "Current Customer ID does not exist.");
                return;
            }

            updateCustomer(currentCustomerId, newCustomerId, name, phone, address);
            loadData();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid IDs.");
            System.out.println(e.getMessage());
        } catch (SQLIntegrityConstraintViolationException e) {
            showAlert("Error", "New Customer ID already exists.");
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void handleUpdateManager() {
        try {
            int customerId = Integer.parseInt(tfCurrentCustomerId.getText());
            int newManagerId = Integer.parseInt(tfNewManagerId.getText());

            if (!customerExists(customerId)) {
                showAlert("Error", "Customer ID does not exist.");
                return;
            }

            if (!managerExists(newManagerId)) {
                showAlert("Error", "New Manager ID does not exist.");
                return;
            }

            updateManager(customerId, newManagerId);
            loadData();
            clearFields();
        } catch (NumberFormatException | SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void handleDelete() {
        try {
            int customerId = Integer.parseInt(tfCurrentCustomerId.getText());
            deleteCustomer(customerId);
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
             ResultSet rs = stmt.executeQuery("SELECT * FROM Customer")) {
            while (rs.next()) {
                data.add(new Customer(rs.getInt("SSN"), rs.getString("Name"), rs.getString("Phone"), rs.getString("Address"), rs.getInt("Manager_ID")));
            }
            tableView.setItems(data);
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }
    }

    private void insertCustomer(int managerId, String name, String phone, String address) throws SQLException {
        String sql = "INSERT INTO Customer (Manager_ID, Name, Phone, Address) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, managerId);
            pstmt.setString(2, name);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.executeUpdate();
        }
    }

    private void updateCustomer(int currentCustomerId, int newCustomerId, String name, String phone, String address) throws SQLException {
        String updateCustomerSQL = "UPDATE Customer SET SSN = ?, Name = ?, Phone = ?, Address = ? WHERE SSN = ?";

        Connection conn = DatabaseConnector.getConnection();
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtCustomer = conn.prepareStatement(updateCustomerSQL)) {
                pstmtCustomer.setInt(1, newCustomerId);
                pstmtCustomer.setString(2, name);
                pstmtCustomer.setString(3, phone);
                pstmtCustomer.setString(4, address);
                pstmtCustomer.setInt(5, currentCustomerId);
                pstmtCustomer.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    private void updateManager(int customerId, int newManagerId) throws SQLException {
        String sql = "UPDATE Customer SET Manager_ID = ? WHERE SSN = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newManagerId);
            pstmt.setInt(2, customerId);
            pstmt.executeUpdate();
        }
    }

    private void deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM Customer WHERE SSN = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.executeUpdate();
        }
    }

    private boolean customerExists(int customerId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Customer WHERE SSN = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
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
        tfManagerId.clear();
        tfNewManagerId.clear();
        tfName.clear();
        tfPhone.clear();
        tfAddress.clear();
        tfCurrentCustomerId.clear();
        tfNewCustomerId.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private TableColumn<Customer, String> createTableColumn(String title, String property) {
        TableColumn<Customer, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        return col;
    }

}
