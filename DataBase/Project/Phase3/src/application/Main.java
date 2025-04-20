package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
/*
            TabPane tabPane = new TabPane();
            
           
            CarController car = new CarController();
            car.show();
            
            CustomerController customer = new CustomerController();
            customer.show();
            
            DriverController driver = new DriverController();
            driver.show();
            
            ManagerController manager = new ManagerController();
            manager.show();
            
            OrderController order = new OrderController();
            order.show();
            
           ProductController product = new ProductController();
            product.show();
            
            
            Tab tab1 = new Tab("Car Operation");
            Tab tab2 = new Tab("Customer Operation");
            Tab tab3 = new Tab("Driver Operation");
            Tab tab4 = new Tab("Manager Operation");
            Tab tab5 = new Tab("Order Operation");
            Tab tab6 = new Tab("Product Operation");


            tab1.setContent(car.bp);
            tab2.setContent(customer.bp);
            tab3.setContent(driver.bp);
            tab4.setContent(manager.bp);
            tab5.setContent(order.bp);
            tab6.setContent(product.bp);

            tabPane.getTabs().addAll(tab1,tab2,tab3,tab4,tab5,tab6);

            Scene scene = new Scene(tabPane, 800, 600);

            primaryStage.setScene(scene);
            primaryStage.setTitle("JavaFX Application with Tabs");
            primaryStage.show();  */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
