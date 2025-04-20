

//Mohammad Sheikh 1221541  Q3

package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ques3 extends Application {
	
	private TextField tfID;
	private TextField tfName;
	private TextField tfQ1;
	private TextField tfQ2;
	private TextField tfProject;
	private TextField tfME;
	private TextField tfFE;
	private TextField tfParti;
	private Button bCal;
	private TextField tfGrade;
	
	public void start(Stage primaryStage) {
	
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp , 800, 600);
		
		HBox hbID = new HBox(15);
		HBox hbName = new HBox(15);
		HBox hbQ1= new HBox(15);
		HBox hbQ2 = new HBox(15);
		HBox hbProject = new HBox(15);
		HBox hbME = new HBox(15);
		HBox hbFE = new HBox(15);
		HBox hbParti = new HBox(15);
		HBox hbCal = new HBox(15);
		VBox vb = new VBox(10);
		
		
		//hbID.getChildren().addAll(new Label("Student ID"), tfID);
		hbName.getChildren().addAll(new Label("Student Name"), tfName);
		hbQ1.getChildren().addAll(new Label("Quizz#1"), tfQ1);
		hbQ2.getChildren().addAll(new Label("Quiz#2"), tfQ2);
		hbProject.getChildren().addAll(new Label("Project"), tfProject);
		hbME.getChildren().addAll(new Label("Midterm Exam"), tfME);
		hbFE.getChildren().addAll(new Label("Final Exam"), tfFE);
		hbParti.getChildren().addAll(new Label("Particiption"), tfParti);
		hbCal.getChildren().addAll(new Label("Calculated Grade"), bCal , new Label("Grade:"), tfGrade);
		
		vb.getChildren().addAll(hbID,hbName,hbQ1,hbQ2,hbProject,hbME,hbFE,hbParti,hbCal);
		
		bp.setLeft(vb);
		primaryStage.setTitle("Q3");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
	
}