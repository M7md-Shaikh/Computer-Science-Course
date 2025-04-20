package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;

public class ManagerOrderProcessingController {
    VBox root = new VBox(20);

    private ComboBox<Order> cbPendingOrders = new ComboBox<>();
    private ComboBox<Driver> cbDriver = new ComboBox<>();
    private ComboBox<Car> cbCar = new ComboBox<>();
    private ComboBox<Manager> cbManager = new ComboBox<>();
    private Label lblCustomerName = new Label();
    private Label lblProduct = new Label();
    private Label lblQuantity = new Label();
    private Label lblCustomerAddress = new Label();
    private DatePicker dpOrderDate = new DatePicker(LocalDate.now());

    public ManagerOrderProcessingController() {
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Order Processing");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Pending Orders:"), cbPendingOrders);
        gridPane.addRow(1, new Label("Customer Name:"), lblCustomerName);
        gridPane.addRow(2, new Label("Product:"), lblProduct);
        gridPane.addRow(3, new Label("Quantity:"), lblQuantity);
        gridPane.addRow(4, new Label("Customer Address:"), lblCustomerAddress);
        gridPane.addRow(5, new Label("Order Date:"), dpOrderDate);
        gridPane.addRow(6, new Label("Assign Manager:"), cbManager);
        gridPane.addRow(7, new Label("Assign Driver:"), cbDriver);
        gridPane.addRow(8, new Label("Assign Car:"), cbCar);

        Button submitButton = new Button("Submit Order");
        submitButton.setStyle(
                "-fx-background-color: #34495E; " +           // Button background color
                "-fx-text-fill: white; " +                      // Button text color
                "-fx-font-size: 14px; " +                       // Font size
                "-fx-padding: 10px 20px; " +                    // Padding around the text
                "-fx-border-radius: 5px; " +                    // Rounded corners
                "-fx-background-radius: 5px; " +                // Rounded background
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 2, 2);"  // Shadow effect
            );
        submitButton.setOnAction(e -> handleSubmitOrder());

        root.getChildren().addAll(titleLabel, gridPane, submitButton);

        loadComboBoxData();
    }

    public VBox getView() {
        return root; // Returns the VBox containing the order processing interface
    }


    private void loadComboBoxData() {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        ObservableList<Manager> managers = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            // Load pending orders
            ResultSet rs = stmt.executeQuery("SELECT * FROM Orders WHERE Status = 'Pending'");
            while (rs.next()) {
                orders.add(new Order(
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

            // Load managers
            rs = stmt.executeQuery("SELECT * FROM Manager");
            while (rs.next()) {
                managers.add(new Manager(rs.getInt("Manager_ID"), rs.getString("Name"), rs.getString("Phone"), rs.getString("Address")));
            }

            // Set combo boxes
            cbPendingOrders.setItems(orders);
            cbPendingOrders.setOnAction(e -> {
                loadOrderDetails();
                loadManagersBasedOnCustomerAddress();
                loadDriversBasedOnCustomerAddress();
            });

            // Add listener for driver combo box to load relevant cars
            cbDriver.setOnAction(e -> loadRelevantCars());

        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }
    }

    private void loadManagersBasedOnCustomerAddress() {
        Order selectedOrder = cbPendingOrders.getValue();
        if (selectedOrder != null) {
            try (Connection conn = DatabaseConnector.getConnection();
                 PreparedStatement pstmtCustomer = conn.prepareStatement("SELECT * FROM Customer WHERE SSN = ?");
                 PreparedStatement pstmtManager = conn.prepareStatement("SELECT * FROM Manager WHERE Address LIKE ?")) {

                // Fetch customer address
                pstmtCustomer.setInt(1, selectedOrder.getCustomerId());
                ResultSet rsCustomer = pstmtCustomer.executeQuery();
                String customerAddress = null;
                if (rsCustomer.next()) {
                    customerAddress = rsCustomer.getString("Address");
                }

                // Load managers with a partial address match to the customer
                if (customerAddress != null) {
                    pstmtManager.setString(1, "%" + customerAddress + "%");
                    ResultSet rsManager = pstmtManager.executeQuery();
                    ObservableList<Manager> managers = FXCollections.observableArrayList();
                    while (rsManager.next()) {
                        managers.add(new Manager(rsManager.getInt("Manager_ID"), rsManager.getString("Name"), rsManager.getString("Phone"), rsManager.getString("Address")));
                    }
                    cbManager.setItems(managers);
                }

            } catch (SQLException e) {
                showAlert("Error", "Database error.");
                System.out.println(e.getMessage());
            }
        }
    }

    private void loadDriversBasedOnCustomerAddress() {
        Order selectedOrder = cbPendingOrders.getValue();
        if (selectedOrder != null) {
            try (Connection conn = DatabaseConnector.getConnection();
                 PreparedStatement pstmtCustomer = conn.prepareStatement("SELECT * FROM Customer WHERE SSN = ?");
                 PreparedStatement pstmtDriver = conn.prepareStatement("SELECT * FROM Driver WHERE Address LIKE ?")) {

                // Fetch customer address
                pstmtCustomer.setInt(1, selectedOrder.getCustomerId());
                ResultSet rsCustomer = pstmtCustomer.executeQuery();
                String customerAddress = null;
                if (rsCustomer.next()) {
                    customerAddress = rsCustomer.getString("Address");
                    lblCustomerAddress.setText(customerAddress);
                }

                // Load drivers with a partial address match to the customer
                if (customerAddress != null) {
                    pstmtDriver.setString(1, "%" + customerAddress + "%");
                    ResultSet rsDriver = pstmtDriver.executeQuery();
                    ObservableList<Driver> drivers = FXCollections.observableArrayList();
                    while (rsDriver.next()) {
                        drivers.add(new Driver(rsDriver.getInt("Driver_ID"), rsDriver.getInt("Manager_ID"), rsDriver.getString("Name"), rsDriver.getString("Phone"), rsDriver.getString("Address")));
                    }
                    cbDriver.setItems(drivers);
                }

            } catch (SQLException e) {
                showAlert("Error", "Database error.");
                System.out.println(e.getMessage());
            }
        }
    }

    private void updateDriverManager(int driverId, int managerId) {
        String sql = "UPDATE Driver SET Manager_ID = ? WHERE Driver_ID = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, managerId);
            pstmt.setInt(2, driverId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }
    }

    private void loadRelevantCars() {
        Driver selectedDriver = cbDriver.getValue();
        ObservableList<Car> relevantCars = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Car WHERE Driver_ID = ? OR Driver_ID = 0")) {

            if (selectedDriver != null) {
                pstmt.setInt(1, selectedDriver.getDriverId());
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    relevantCars.add(new Car(rs.getInt("Reg_Number"), rs.getString("Car_Type"), rs.getString("Car_Color"), rs.getInt("Driver_ID")));
                }
            }
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }

        cbCar.setItems(relevantCars);
    }

    private void loadOrderDetails() {
        Order selectedOrder = cbPendingOrders.getValue();
        if (selectedOrder != null) {
            try (Connection conn = DatabaseConnector.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Customer WHERE SSN = ?")) {
                pstmt.setInt(1, selectedOrder.getCustomerId());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    lblCustomerName.setText(rs.getString("Name"));
                    lblCustomerAddress.setText(rs.getString("Address"));
                }

                lblProduct.setText(selectedOrder.getProductName());
                lblQuantity.setText(String.valueOf(selectedOrder.getQuantity()));
                dpOrderDate.setValue(selectedOrder.getOrderDate().toLocalDate());
            } catch (SQLException e) {
                showAlert("Error", "Database error.");
                System.out.println(e.getMessage());
            }
        }
    }

    private void handleSubmitOrder() {
        try {
            Order selectedOrder = cbPendingOrders.getValue();
            Driver driver = cbDriver.getValue();
            Car car = cbCar.getValue();
            Manager manager = cbManager.getValue();

            if (selectedOrder == null || driver == null || car == null || manager == null) {
                showAlert("Error", "Please fill in all fields correctly.");
                return;
            }

            if (!lblCustomerAddress.getText().contains(manager.getAddress())) {
                showAlert("Error", "The manager's address does not match the customer's address.");
                return;
            }

            if (driver.getManagerId() != manager.getManagerId()) {
                showAlert("Error", "The selected driver does not belong to the chosen manager.");
                return;
            }

            if (car.getDriverId() != 0 && car.getDriverId() != driver.getDriverId()) {
                showAlert("Error", "The selected car is not assigned to the chosen driver.");
                return;
            }

            if (car.getDriverId() == 0) {
                updateCarDriver(car.getRegNumber(), driver.getDriverId());
            }

            updateOrder(selectedOrder.getOrderId(), manager.getManagerId(), driver.getDriverId(), car.getRegNumber());
            updateCustomerManager(selectedOrder.getCustomerId(), manager.getManagerId());
            showAlert("Success", "Order has been processed successfully!");
        } catch (SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void updateOrder(int orderId, int managerId, int driverId, int carId) throws SQLException {
        String sql = "UPDATE Orders SET Manager_ID = ?, Driver_ID = ?, Car_ID = ?, Status = 'Processed' WHERE Order_ID = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, managerId);
            pstmt.setInt(2, driverId);
            pstmt.setInt(3, carId);
            pstmt.setInt(4, orderId);
            pstmt.executeUpdate();
        }
    }

    private void updateCarDriver(int carId, int driverId) throws SQLException {
        String sql = "UPDATE Car SET Driver_ID = ? WHERE Reg_Number = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, driverId);
            pstmt.setInt(2, carId);
            pstmt.executeUpdate();
        }
    }

    private void updateCustomerManager(int customerId, int managerId) throws SQLException {
        String sql = "UPDATE Customer SET Manager_ID = ? WHERE SSN = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, managerId);
            pstmt.setInt(2, customerId);
            pstmt.executeUpdate();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}