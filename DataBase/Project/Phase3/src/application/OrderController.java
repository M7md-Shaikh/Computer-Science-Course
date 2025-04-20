package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;


import java.sql.*;

public class OrderController {
    VBox root = new VBox(10);

    private TextField tfOrderId = new TextField();
    private TextField tfCustomerId = new TextField();
    private ComboBox<Product> cbProduct = new ComboBox<>();
    private TextField tfQuantity = new TextField();
    private DatePicker dpOrderDate = new DatePicker();
    private ComboBox<Manager> cbManager = new ComboBox<>();
    private ComboBox<Driver> cbDriver = new ComboBox<>();
    private ComboBox<Car> cbCar = new ComboBox<>();
    private TextField tfNewOrderId = new TextField();
    private TextField tfNewCustomerId = new TextField();
    private TextField tfNewProductName = new TextField();
    private ComboBox<Manager> cbNewManager = new ComboBox<>();
    private ComboBox<Driver> cbNewDriver = new ComboBox<>();
    private ComboBox<Car> cbNewCar = new ComboBox<>();
    private TableView<Order> tableView = new TableView<>();
    private ObservableList<Order> data = FXCollections.observableArrayList();

    public OrderController() {
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Manage Orders");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button insertButton = createStyledButton("Insert Order");
        insertButton.setOnAction(e -> showInsertScreen());

        Button updateButton = createStyledButton("Update Order");
        updateButton.setOnAction(e -> showUpdateScreen());

        Button deleteButton = createStyledButton("Delete Order");
        deleteButton.setOnAction(e -> showDeleteScreen());
        
        loadComboBoxData();

        tableView.getColumns().addAll(
            createTableColumn("Order ID", "orderId"),
            createTableColumn("Customer ID", "customerId"),
            createTableColumn("Product Name", "productName"),
            createTableColumn("Quantity", "quantity"),
            createTableColumn("Order Date", "orderDate"),
            createTableColumn("Status", "status"),
            createTableColumn("Manager ID", "managerId"),
            createTableColumn("Driver ID", "driverId"),
            createTableColumn("Car ID", "carId")
        );

        HBox hb = new HBox(10);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(insertButton, updateButton, deleteButton);
        root.getChildren().addAll(titleLabel,hb, tableView);

        loadData();
    }

    public VBox getView() {
        return root; // Returns the VBox containing the order interface
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
        BorderPane bp = new BorderPane();

        Label titleLabel = new Label("Insert Order");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Customer ID:"), tfCustomerId);
        gridPane.addRow(1, new Label("Product Name:"), cbProduct);
        gridPane.addRow(2, new Label("Quantity:"), tfQuantity);
        gridPane.addRow(3, new Label("Order Date:"), dpOrderDate);
        gridPane.addRow(4, new Label("Manager:"), cbManager);
        gridPane.addRow(5, new Label("Driver:"), cbDriver);
        gridPane.addRow(6, new Label("Car:"), cbCar);
        gridPane.setAlignment(Pos.CENTER);

        Button insertButton = new Button("Insert");
        insertButton.setOnAction(e -> handleInsert());

        gridPane.addRow(7, insertButton);

        root.getChildren().addAll(titleLabel, gridPane);

        loadManagersAndDriversBasedOnCustomer(cbManager, cbDriver, cbCar, tfCustomerId);

        bp.setCenter(root);
        Scene scene = new Scene(bp, 800, 500);
        stage.setScene(scene);
        stage.setTitle("Insert Order");
        stage.show();
    }

    private void showUpdateScreen() {
    	BorderPane bp = new BorderPane();
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Update Order");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Current Order ID:"), tfOrderId);
        gridPane.addRow(1, new Label("New Order ID:"), tfNewOrderId);
        gridPane.addRow(2, new Label("New Customer ID:"), tfNewCustomerId);
        gridPane.addRow(3, new Label("New Product Name:"), tfNewProductName);
        gridPane.addRow(4, new Label("Quantity:"), tfQuantity);
        gridPane.addRow(5, new Label("Order Date:"), dpOrderDate);
        gridPane.addRow(6, new Label("New Manager:"), cbNewManager);
        gridPane.addRow(7, new Label("New Driver:"), cbNewDriver);
        gridPane.addRow(8, new Label("New Car:"), cbNewCar);
        gridPane.setAlignment(Pos.CENTER);

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> handleUpdate());

        gridPane.addRow(9, updateButton);

        root.getChildren().addAll(titleLabel, gridPane);

        loadManagersAndDriversBasedOnCustomer(cbNewManager, cbNewDriver, cbNewCar, tfNewCustomerId);
        
        bp.setCenter(root);
        Scene scene = new Scene(bp, 800, 500);
        stage.setScene(scene);
        stage.setTitle("Update Order");
        stage.show();
    }

    private void loadComboBoxData() {
        ObservableList<Product> products = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
            while (rs.next()) {
                products.add(new Product(rs.getString("Product_Name"), rs.getDouble("Price")));
            }
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }

        cbProduct.setItems(products);
    }
    
    private void showDeleteScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Delete Order");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Order ID:"), tfOrderId);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> handleDelete());

        gridPane.addRow(1, deleteButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Delete Order");
        stage.show();
    }

    private void handleInsert() {
        try {
            int customerId = Integer.parseInt(tfCustomerId.getText());
            String productName = cbProduct.getValue().getProductName();
            int quantity = Integer.parseInt(tfQuantity.getText());
            Date orderDate = Date.valueOf(dpOrderDate.getValue());
            Manager manager = cbManager.getValue();
            Driver driver = cbDriver.getValue();
            Car car = cbCar.getValue();

            if (manager == null || driver == null || car == null) {
                showAlert("Error", "Please select Manager, Driver, and Car.");
                return;
            }

            insertOrder(customerId, productName, quantity, orderDate, manager.getManagerId(), driver.getDriverId(), car.getRegNumber());
            loadData();
            clearFields();
        } catch (NumberFormatException | SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void handleUpdate() {
        try {
            int currentOrderId = Integer.parseInt(tfOrderId.getText());
            int newOrderId = Integer.parseInt(tfNewOrderId.getText());
            int newCustomerId = Integer.parseInt(tfNewCustomerId.getText());
            String newProductName = tfNewProductName.getText();
            int quantity = Integer.parseInt(tfQuantity.getText());
            Date orderDate = Date.valueOf(dpOrderDate.getValue());
            Manager newManager = cbNewManager.getValue();
            Driver newDriver = cbNewDriver.getValue();
            Car newCar = cbNewCar.getValue();

            if (!orderExists(currentOrderId)) {
                showAlert("Error", "Current Order ID does not exist.");
                return;
            }

            if (newManager == null || newDriver == null || newCar == null) {
                showAlert("Error", "Please select Manager, Driver, and Car.");
                return;
            }

            updateOrder(currentOrderId, newOrderId, newCustomerId, newProductName, quantity, orderDate, newManager.getManagerId(), newDriver.getDriverId(), newCar.getRegNumber());
            loadData();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid IDs.");
            e.printStackTrace();
        } catch (SQLIntegrityConstraintViolationException e) {
            showAlert("Error", "New Order ID already exists.");
            e.printStackTrace();
        } catch (SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void handleDelete() {
        try {
            int orderId = Integer.parseInt(tfOrderId.getText());
            deleteOrder(orderId);
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
             ResultSet rs = stmt.executeQuery("SELECT * FROM Orders")) {
            while (rs.next()) {
                data.add(new Order(
                    rs.getInt("Order_ID"),
                    rs.getInt("Customer_ID"),
                    rs.getString("Product_Name"),
                    rs.getInt("Quantity"),
                    rs.getDate("Order_date"),
                    rs.getString("Status"),
                    rs.getInt("Manager_ID"),
                    rs.getInt("Driver_ID"),
                    rs.getInt("Car_ID")
                ));
            }
            tableView.setItems(data);
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }
    }

    private void insertOrder(int customerId, String productName, int quantity, Date orderDate, int managerId, int driverId, int carId) throws SQLException {
        String status = (managerId == 0) ? "Pending" : "Processed";
        String sql = "INSERT INTO Orders (Customer_ID, Product_Name, Quantity, Order_date, Status, Manager_ID, Driver_ID, Car_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.setString(2, productName);
            pstmt.setInt(3, quantity);
            pstmt.setDate(4, orderDate);
            pstmt.setString(5, status);
            pstmt.setInt(6, managerId);
            pstmt.setInt(7, driverId);
            pstmt.setInt(8, carId);
            pstmt.executeUpdate();
        }
    }

    public void updateOrder(int currentOrderId, int newOrderId, int newCustomerId, String newProductName, int quantity, Date orderDate, int newManagerId, int newDriverId, int newCarId) throws SQLException {
        Connection conn = DatabaseConnector.getConnection();
        try {
            conn.setAutoCommit(false);

            // Check if the manager ID was changed from 0 to a valid manager ID
            String currentManagerSql = "SELECT Manager_ID FROM Orders WHERE Order_ID = ?";
            int currentManagerId = 0;
            try (PreparedStatement pstmt = conn.prepareStatement(currentManagerSql)) {
                pstmt.setInt(1, currentOrderId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        currentManagerId = rs.getInt("Manager_ID");
                    }
                }
            }

            String updateOrdersSql = "UPDATE Orders SET Order_ID = ?, Customer_ID = ?, Product_Name = ?, Quantity = ?, Order_date = ?, Manager_ID = ?, Driver_ID = ?, Car_ID = ?, Status = ? WHERE Order_ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateOrdersSql)) {
                pstmt.setInt(1, newOrderId);
                pstmt.setInt(2, newCustomerId);
                pstmt.setString(3, newProductName);
                pstmt.setInt(4, quantity);
                pstmt.setDate(5, orderDate);
                pstmt.setInt(6, newManagerId);
                pstmt.setInt(7, newDriverId);
                pstmt.setInt(8, newCarId);
                pstmt.setString(9, (currentManagerId == 0 && newManagerId != 0) ? "Processed" : "Pending");
                pstmt.setInt(10, currentOrderId);
                pstmt.executeUpdate();
            }

            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    private void deleteOrder(int orderId) throws SQLException {
        String sql = "DELETE FROM Orders WHERE Order_ID = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
        }
    }

    private boolean orderExists(int orderId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Orders WHERE Order_ID = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    private void loadManagersAndDriversBasedOnCustomer(ComboBox<Manager> managerComboBox, ComboBox<Driver> driverComboBox, ComboBox<Car> carComboBox, TextField customerIdField) {
        customerIdField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int customerId = Integer.parseInt(newValue);
                String customerAddress = getCustomerAddress(customerId);
                loadManagers(managerComboBox, customerAddress);
                managerComboBox.setOnAction(e -> {
                    Manager selectedManager = managerComboBox.getValue();
                    if (selectedManager != null) {
                        loadDriversBasedOnManager(selectedManager, driverComboBox);
                    } else {
                        loadAllDrivers(driverComboBox);
                    }
                });
                driverComboBox.setOnAction(e -> {
                    Driver selectedDriver = driverComboBox.getValue();
                    if (selectedDriver != null) {
                        loadCarsBasedOnDriver(selectedDriver, carComboBox);
                    } else {
                        loadAllCars(carComboBox);
                    }
                });
            } catch (NumberFormatException | SQLException e) {
                managerComboBox.setItems(FXCollections.observableArrayList());
                driverComboBox.setItems(FXCollections.observableArrayList());
                carComboBox.setItems(FXCollections.observableArrayList());
            }
        });
    }

    private String getCustomerAddress(int customerId) throws SQLException {
        String sql = "SELECT Address FROM Customer WHERE SSN = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Address");
                }
            }
        }
        return null;
    }

    private void loadManagers(ComboBox<Manager> managerComboBox, String address) {
        ObservableList<Manager> managers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Manager WHERE Address LIKE ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + address + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    managers.add(new Manager(rs.getInt("Manager_ID"), rs.getString("Name"), rs.getString("Phone"), rs.getString("Address")));
                }
            }
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }
        if (managers.isEmpty()) {
            loadAllManagers(managerComboBox);
        } else {
            managerComboBox.setItems(managers);
        }
    }

    private void loadAllManagers(ComboBox<Manager> managerComboBox) {
        ObservableList<Manager> managers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Manager";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                managers.add(new Manager(rs.getInt("Manager_ID"), rs.getString("Name"), rs.getString("Phone"), rs.getString("Address")));
            }
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }
        managerComboBox.setItems(managers);
    }

    private void loadDriversBasedOnManager(Manager manager, ComboBox<Driver> driverComboBox) {
        ObservableList<Driver> drivers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Driver WHERE Address LIKE ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + manager.getAddress() + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    drivers.add(new Driver(rs.getInt("Driver_ID"), rs.getInt("Manager_ID"), rs.getString("Name"), rs.getString("Phone"), rs.getString("Address")));
                }
            }
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }
        driverComboBox.setItems(drivers);
    }

    private void loadAllDrivers(ComboBox<Driver> driverComboBox) {
        ObservableList<Driver> drivers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Driver";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                drivers.add(new Driver(rs.getInt("Driver_ID"), rs.getInt("Manager_ID"), rs.getString("Name"), rs.getString("Phone"), rs.getString("Address")));
            }
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }
        driverComboBox.setItems(drivers);
    }

    private void loadCarsBasedOnDriver(Driver driver, ComboBox<Car> carComboBox) {
        ObservableList<Car> cars = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Car WHERE Driver_ID = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, driver.getDriverId());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    cars.add(new Car(rs.getInt("Reg_Number"), rs.getString("Car_Type"), rs.getString("Car_Color"), rs.getInt("Driver_ID")));
                }
            }
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }
        carComboBox.setItems(cars);
    }

    private void loadAllCars(ComboBox<Car> carComboBox) {
        ObservableList<Car> cars = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Car";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cars.add(new Car(rs.getInt("Reg_Number"), rs.getString("Car_Type"), rs.getString("Car_Color"), rs.getInt("Driver_ID")));
            }
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }
        carComboBox.setItems(cars);
    }

    private void clearFields() {
        tfOrderId.clear();
        tfCustomerId.clear();
        cbProduct.setValue(null);
        tfQuantity.clear();
        dpOrderDate.setValue(null);
        cbManager.getSelectionModel().clearSelection();
        cbDriver.getSelectionModel().clearSelection();
        cbCar.getSelectionModel().clearSelection();
        tfNewOrderId.clear();
        tfNewCustomerId.clear();
        tfNewProductName.clear();
        cbNewManager.getSelectionModel().clearSelection();
        cbNewDriver.getSelectionModel().clearSelection();
        cbNewCar.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private TableColumn<Order, String> createTableColumn(String title, String property) {
        TableColumn<Order, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        return col;
    }
}
