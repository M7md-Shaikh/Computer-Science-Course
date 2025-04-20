

//Mohammad Sheikh 1221541  Q3

package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ques3 extends Application {
	
	private TextField tfID = new TextField();
	private TextField tfName= new TextField();
	private TextField tfQ1= new TextField();
	private TextField tfQ2= new TextField();
	private TextField tfProject= new TextField();
	private TextField tfME= new TextField();
	private TextField tfFE= new TextField();
	private TextField tfParti= new TextField();
	private Button bCal = new Button("Calculate Grade");
	private TextField tfGrade= new TextField();
	private ComboBox cb= new ComboBox();
	
	private Button badd = new Button("Add To The List");
	private Button bsave = new Button("Save to File");
	
	Label lID = new Label("Student ID");
	Label lN = new Label("Student Name");
	Label lQ1 =new Label("Quizz#1");
	Label lQ2=new Label("Quiz#2");
	Label lPro =new Label("Project");
	Label lME =new Label("Midterm Exam");
	Label lFE=new Label("Final Exam");
	Label lParti =new Label("Particiption");
	Label lCal =new Label("Calculated Grade");
	Label lG = new Label("Grade:");
	
	public void start(Stage primaryStage) {
		
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp , 800, 500);
		GridPane  gp = new GridPane();
	
		
		
		HBox hbID = new HBox(15);
		HBox hbName = new HBox(15);
		HBox hbQ1= new HBox(15);
		HBox hbQ2 = new HBox(15);
		HBox hbProject = new HBox(15);
		HBox hbME = new HBox(15);
		HBox hbFE = new HBox(15);
		HBox hbParti = new HBox(15);
		HBox hbCal = new HBox(15);
		VBox vb1 = new VBox(2);
		HBox hbC = new HBox(2);
		
		RadioButton rb1 = new RadioButton("Abdallah Karakra");
		RadioButton rb2 = new RadioButton("Mamoun Nawahdah");
		RadioButton rb3 = new RadioButton("Murad Najoum");
		RadioButton rb4 = new RadioButton("Fadi Khalil");

		
		
		
		hbID.getChildren().addAll(lID, tfID);
		hbName.getChildren().addAll(lN, tfName);
		hbQ1.getChildren().addAll(lQ1, tfQ1);
		hbQ2.getChildren().addAll(lQ2, tfQ2);
		hbProject.getChildren().addAll(lPro, tfProject);
		hbME.getChildren().addAll(lME, tfME);
		hbFE.getChildren().addAll(lFE, tfFE);
		hbParti.getChildren().addAll(lParti, tfParti);
		hbCal.getChildren().addAll(lCal, bCal , lG, tfGrade);
		
		gp.add(lID, 0, 0);
		gp.add(tfID, 1, 0);
		gp.add(lQ1, 0, 1);
		gp.add(tfQ1, 1, 1);
		gp.add(lQ2, 0,2);
		gp.add(tfQ2, 1,2);
		gp.add(lPro, 0,3);
		gp.add(tfProject, 1, 3);
		gp.add(lME, 0,4);
		gp.add(tfME, 1,4);
		gp.add(lFE, 0,5);
		gp.add(tfFE, 1,5);
		gp.add(lParti, 0,6);
		gp.add(tfParti, 1,6);
		gp.add(lCal, 0,7);
		gp.add(bCal, 1,7);
		gp.add(lG, 2,7);
		gp.add(tfGrade, 3,7);
		
		vb1.getChildren().addAll(gp);
		
		
		hbC.getChildren().addAll(rb1, rb2, rb3, rb4);
		hbC.setAlignment(Pos.CENTER);
		VBox vb2 = new VBox(5);
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(badd,bsave);
		hb2.setAlignment(Pos.CENTER);
		vb2.getChildren().addAll(hbC,hb2);
		vb2.setAlignment(Pos.CENTER);
		
		
		bp.setLeft(vb1);
		bp.setBottom(vb2);
		bp.setRight(cb);
		primaryStage.setTitle("Q3");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
	
}
