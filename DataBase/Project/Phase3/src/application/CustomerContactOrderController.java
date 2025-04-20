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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CustomerContactOrderController {

    private TextField tfCustomerName = new TextField();
    private TextField tfCustomerPhone = new TextField();
    private ComboBox<String> cbCustomerAddress = new ComboBox<>();
    private ComboBox<Product> cbProduct = new ComboBox<>();
    private TextField tfQuantity = new TextField();
    private DatePicker dpOrderDate = new DatePicker(LocalDate.now());

    public void show() {
        Stage stage = new Stage();
        VBox root = new VBox(20);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Customer Order Entry");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane customerPane = new GridPane();
        customerPane.setHgap(10);
        customerPane.setVgap(10);
        customerPane.addRow(0, createStyledLabel("Customer Name:"), tfCustomerName);
        customerPane.addRow(1, createStyledLabel("Customer Phone:"), tfCustomerPhone);
        customerPane.addRow(2, createStyledLabel("Customer Address:"), cbCustomerAddress);
        customerPane.addRow(3, createStyledLabel("Product:"), cbProduct);
        customerPane.addRow(4, createStyledLabel("Quantity:"), tfQuantity);
        customerPane.addRow(5, createStyledLabel("Order Date:"), dpOrderDate);

        Button submitButton = createStyledButton("Submit Order");
        submitButton.setOnAction(e -> handleSubmitOrder());

        Button showOrdersButton = createStyledButton("Show Orders");
        showOrdersButton.setOnAction(e -> showCustomerOrders());

        root.getChildren().addAll(titleLabel, customerPane, submitButton, showOrdersButton);

        loadComboBoxData();
        
        BorderPane bp = new BorderPane();

        Image wallpaperImage = new Image("file:C:/Users/mhmds/Pictures/Saved Pictures/Concrete2.jpg");
        
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


        Scene scene = new Scene(bp, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Customer Order Entry");
        stage.show();
    }

    private Label createStyledLabel(String text) {
    	Label label = new Label(text);
    	label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #333;");
    	return label;
    	}
    
    private Button createStyledButton(String text) {
    	Button button = new Button(text); 
    	button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 12px; -fx-padding: 8px 16px; -fx-background-radius: 14px; -fx-border-radius: 13px;");
    	button.setOnMouseEntered(e -> 
    		button.setStyle("-fx-background-color: #45A049; -fx-text-fill: white; -fx-font-size: 12px; -fx-padding: 8px 16px; -fx-background-radius: 14px; -fx-border-radius: 13px;"));
    	button.setOnMouseExited(e ->
    		button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 12px; -fx-padding: 8px 16px; -fx-background-radius: 14px; -fx-border-radius: 13px;"));
    	button.setMaxWidth(250);
    	return button;
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
        cbCustomerAddress.setItems(FXCollections.observableArrayList(getCities()));
    }

    private List<String> getCities() {
        // List of cities in Palestine
        return Arrays.asList(
                "al-Quds", "Ramallah", "Nablus", "Hebron", "Bethlehem",
                "Jenin", "Tulkarm", "Qalqilya", "Salfit", "Jericho"
        );
    }

    private void handleSubmitOrder() {
        try {
            String customerName = tfCustomerName.getText();
            String customerPhone = tfCustomerPhone.getText();
            String customerAddress = cbCustomerAddress.getValue();
            Product product = cbProduct.getValue();
            int quantity = Integer.parseInt(tfQuantity.getText());
            LocalDate orderDate = dpOrderDate.getValue();

            if (customerName.isEmpty() || customerPhone.isEmpty() || customerAddress == null || product == null || quantity <= 0) {
                showAlert("Error", "Please fill in all fields correctly.");
                return;
            }

            // Check if the order date is in the future
            if (!orderDate.isAfter(LocalDate.now())) {
                showAlert("Error", "Please select a future date for the order.");
                return;
            }

            int customerId = insertCustomer(customerName, customerPhone, customerAddress);
            insertOrder(customerId, product.getProductName(), quantity, Date.valueOf(orderDate));
            showAlert("Success", "Order has been submitted successfully!");
        } catch (NumberFormatException | SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private int insertCustomer(String customerName, String customerPhone, String customerAddress) throws SQLException {
        String sql = "INSERT INTO Customer (Name, Phone, Address) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, customerName);
            pstmt.setString(2, customerPhone);
            pstmt.setString(3, customerAddress);
            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating customer failed, no ID obtained.");
            }
        }
    }

    private void insertOrder(int customerId, String productName, int quantity, Date orderDate) throws SQLException {
        String sql = "INSERT INTO Orders (Customer_ID, Product_Name, Quantity, Order_date, Status) VALUES (?, ?, ?, ?, 'Pending')";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.setString(2, productName);
            pstmt.setInt(3, quantity);
            pstmt.setDate(4, orderDate);
            pstmt.executeUpdate();
        }
    }

    private void showCustomerOrders() {
        String customerName = tfCustomerName.getText();
        if (customerName.isEmpty()) {
            showAlert("Error", "Please enter the customer name.");
            return;
        }

        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Customer Orders for " + customerName);
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TableView<Order> tableView = new TableView<>();
        tableView.getColumns().addAll(
            createTableColumn("Order ID", "orderId"),
            createTableColumn("Product Name", "productName"),
            createTableColumn("Quantity", "quantity"),
            createTableColumn("Order Date", "orderDate"),
            createTableColumn("Status", "status")
        );

        ObservableList<Order> orders = loadCustomerOrders(customerName);
        tableView.setItems(orders);

        root.getChildren().addAll(titleLabel, tableView);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Customer Orders");
        stage.show();
    }

    private ObservableList<Order> loadCustomerOrders(String customerName) {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        String sql = "SELECT o.Order_ID, o.Customer_ID, o.Product_Name, o.Quantity, o.Order_date, o.Status, " +
                     "o.Manager_ID, o.Driver_ID, o.Car_ID FROM Orders o " +
                     "JOIN Customer c ON o.Customer_ID = c.SSN WHERE c.Name = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerName);
            ResultSet rs = pstmt.executeQuery();

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
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }

        return orders;
    }

    private TableColumn<Order, String> createTableColumn(String title, String property) {
        TableColumn<Order, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        return col;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
