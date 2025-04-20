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

public class ProductController {
    VBox root = new VBox(10);
    private TextField tfProductName = new TextField();
    private TextField tfNewProductName = new TextField();
    private TextField tfPrice = new TextField();
    private TableView<Product> tableView = new TableView<>();
    private ObservableList<Product> data = FXCollections.observableArrayList();

    public ProductController() {
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Manage Products");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button insertButton = createStyledButton("Insert Product");
        insertButton.setOnAction(e -> showInsertScreen());

        Button updateButton = createStyledButton("Update Product");
        updateButton.setOnAction(e -> showUpdateScreen());

        Button deleteButton = createStyledButton("Delete Product");
        deleteButton.setOnAction(e -> showDeleteScreen());


        tableView.getColumns().addAll(
            createTableColumn("Product Name", "productName"),
            createTableColumn("Price", "price")
        );
        
        HBox hb = new HBox(10);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(insertButton, updateButton, deleteButton);
        root.getChildren().addAll(titleLabel, hb, tableView);

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
        return root; // Returns the VBox containing the product interface
    }

    private void showInsertScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Insert Product");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Product Name:"), tfProductName);
        gridPane.addRow(1, new Label("Price:"), tfPrice);

        Button insertButton = new Button("Insert");
        insertButton.setOnAction(e -> handleInsert());

        gridPane.addRow(2, insertButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Insert Product");
        stage.show();
    }


    private void showUpdateScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Label titleLabel = new Label("Update Product");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Current Product Name:"), tfProductName);
        gridPane.addRow(1, new Label("New Product Name:"), tfNewProductName);
        gridPane.addRow(2, new Label("Price:"), tfPrice);

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> handleUpdate());

        gridPane.addRow(3, updateButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Update Product");
        stage.show();
    }

    private void showDeleteScreen() {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Label titleLabel = new Label("Delete Product");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Product Name:"), tfProductName);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> handleDelete());

        gridPane.addRow(1, deleteButton);

        root.getChildren().addAll(titleLabel, gridPane);

        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Delete Product");
        stage.show();
    }

    private void handleInsert() {
        try {
            String productName = tfProductName.getText();
            double price = Double.parseDouble(tfPrice.getText());
            insertProduct(productName, price);
            loadData();
            clearFields();
        } catch (NumberFormatException | SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void handleUpdate() {
        try {
            String currentProductName = tfProductName.getText();
            String newProductName = tfNewProductName.getText();
            double price = Double.parseDouble(tfPrice.getText());

            if (!productExists(currentProductName)) {
                showAlert("Error", "Current Product Name does not exist.");
                return;
            }

            updateProduct(currentProductName, newProductName, price);
            loadData();
            clearFields();
        } catch (NumberFormatException | SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void handleDelete() {
        try {
            String productName = tfProductName.getText();
            deleteProduct(productName);
            loadData();
            clearFields();
        } catch (SQLException e) {
            showAlert("Error", "Invalid input or database error.");
            System.out.println(e.getMessage());
        }
    }

    private void loadData() {
        data.clear();
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Product")) {
            while (rs.next()) {
                data.add(new Product(rs.getString("Product_Name"), rs.getDouble("Price")));
            }
            tableView.setItems(data);
        } catch (SQLException e) {
            showAlert("Error", "Database error.");
            System.out.println(e.getMessage());
        }
    }

    private void insertProduct(String productName, double price) throws SQLException {
        String sql = "INSERT INTO Product (Product_Name, Price) VALUES (?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productName);
            pstmt.setDouble(2, price);
            pstmt.executeUpdate();
        }
    }

    public void updateProduct(String currentProductName, String newProductName, double price) throws SQLException {
        Connection conn = DatabaseConnector.getConnection();
        try {
            conn.setAutoCommit(false);

            // Disable foreign key checks
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
            }

            // Update Orders to new product name
            String updateOrdersSql = "UPDATE Orders SET Product_Name = ? WHERE Product_Name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateOrdersSql)) {
                pstmt.setString(1, newProductName);
                pstmt.setString(2, currentProductName);
                pstmt.executeUpdate();
            }

            // Now update the product
            String updateProductSql = "UPDATE Product SET Product_Name = ?, Price = ? WHERE Product_Name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateProductSql)) {
                pstmt.setString(1, newProductName);
                pstmt.setDouble(2, price);
                pstmt.setString(3, currentProductName);
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

    private boolean productExists(String productName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Product WHERE Product_Name = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    private void deleteProduct(String productName) throws SQLException {
        String sql = "DELETE FROM Product WHERE Product_Name = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productName);
            pstmt.executeUpdate();
        }
    }

    private void clearFields() {
        tfProductName.clear();
        tfNewProductName.clear();
        tfPrice.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private TableColumn<Product, String> createTableColumn(String title, String property) {
        TableColumn<Product, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        return col;
    }
}
