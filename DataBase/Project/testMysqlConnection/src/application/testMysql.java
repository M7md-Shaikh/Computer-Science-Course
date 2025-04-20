package application;

import java.sql.*;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class testMysql extends Application {
	
	BorderPane bp = new BorderPane();
	Label l = new Label ("Select You'r Choice : ");
	Button bET = new Button("Employee");
	Button bMT = new Button ("Manager");
	Button bDT = new Button ("Driver");
	
	// for insert EMPLOYEE
	Button bIE = new Button("Insert Employee");
	Button bDE = new Button("Delete Employee");
	Button bUE = new Button("Update Employee");
	
	TextField tfINE = new TextField(); // insert name in Employee table
	TextField tfIPE = new TextField(); // insert phone in Employee table
	TextField tfIAE = new TextField(); // insert address in Employee table
	TextField tfIIE = new TextField(); // insert ID in Employee table
	Button addE = new Button("Add");
	
	TextField tfUIE = new TextField(); // update ID in Employee table
	TextField tfUNE = new TextField(); // update name in Employee table
	TextField tfUPE = new TextField(); // update phone in Employee table
	TextField tfUAE = new TextField(); // update address in Employee table
	Button updateE = new Button("Update");
	
	TextField tfDIE = new TextField(); // Delete by ID from Employee table
	Button deleteE = new Button("Delete");
	
	public void start(Stage primaryStage) {
		bET.setMaxWidth(150);
		bMT.setMaxWidth(150);
		bDT.setMaxWidth(150);
		Scene scene = new Scene(bp,400,400);
		VBox vb = new VBox(10);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(l,bET,bMT,bDT);
		
		bET.setOnAction(e -> EmployeeScreen());
		
		bp.setCenter(vb);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public Stage EmployeeScreen() {
		
		Stage stage = new Stage();
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp,1000,500);
		
		VBox vbI = new VBox(10);
		vbI.setAlignment(Pos.CENTER);
		VBox vbU = new VBox(10);
		vbU.setAlignment(Pos.CENTER);
		VBox vbD = new VBox(10);
		vbD.setAlignment(Pos.CENTER);
		
		Label lHI = new Label ("Insert Employee : ");
		Label lHD = new Label ("Delete Employee : ");
		Label lHU = new Label ("Update Employee : ");

		
		Label lII = new Label("Employee ID");		
		Label lIN = new Label("Employee Name");
		Label lIP = new Label("Employee Phone");
		Label lIA = new Label("Employee Address");
		
		GridPane gpI = new GridPane();
		gpI.setAlignment(Pos.CENTER);
		gpI.add(lII, 0, 0);
		gpI.add(tfIIE, 1, 0);
		gpI.add(lIN, 0, 1);
		gpI.add(tfINE, 1, 1);
		gpI.add(lIP, 0, 2);
		gpI.add(tfIPE, 1, 2);
		gpI.add(lIA, 0, 3);
		gpI.add(tfIAE, 1, 3);
		gpI.add(addE, 1, 4);
		gpI.setVgap(10);
		gpI.setHgap(10);
		
		vbI.getChildren().addAll(lHI,gpI);
		
		
		Label lDI = new Label("Employee ID");
		GridPane gpD = new GridPane();
		gpD.setAlignment(Pos.CENTER);
		gpD.add(lDI, 0, 0);
		gpD.add(tfDIE, 1, 0);
		gpD.add(deleteE, 1, 1);
		gpD.setVgap(10);
		gpD.setHgap(10);
		
		vbD.getChildren().addAll(lHD,gpD);
		
		
		Label lUI = new Label("Current Employee ID");		
		Label lUN = new Label("Employee Name");
		Label lUP = new Label("Employee Phone");
		Label lUA = new Label("Employee Address");
		
		GridPane gpU = new GridPane();
		gpU.setAlignment(Pos.CENTER);
		gpU.add(lUI, 0, 1);
		gpU.add(tfUIE, 1,1);
		gpU.add(lUN, 0, 2);
		gpU.add(tfUNE, 1, 2);
		gpU.add(lUP, 0, 3);
		gpU.add(tfUPE, 1, 3);
		gpU.add(lUA, 0, 4);
		gpU.add(tfUAE, 1, 4);
		gpU.add(updateE, 1, 5);
		gpU.setAlignment(Pos.CENTER);
		gpU.setVgap(10);
		gpU.setHgap(10);
		
		vbU.getChildren().addAll(lHU,gpU);
		
		HBox hb = new HBox(50);
		hb.getChildren().addAll(vbI,vbU,vbD);
		hb.setAlignment(Pos.CENTER);
		
		bp.setTop(hb);
		stage.setTitle("Employee Screen");
		stage.setScene(scene);
		stage.show();
		return stage;
		
	}
	
	
	public static void main(String[] args) {
		launch(args);	
	}
}
