package application;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Act4 extends Application {
	
	public void start(Stage stage) throws Exception {
		Rectangle project = new Rectangle();
		project.setWidth(100);
		project.setHeight(60);
		project.setFill(Color.RED);
		Label projectLabel = new Label("Project --"+(project.getHeight()/3)+"%");
		
		Rectangle quiz = new Rectangle();
		quiz.setWidth(100);
		quiz.setHeight(30);
		quiz.setFill(Color.BLUE);
		Label quizLabel = new Label("Quiz --"+(quiz.getHeight()/3)+"%");
		
		Rectangle midterm = new Rectangle();
		midterm.setWidth(100);
		midterm.setHeight(90);
		midterm.setFill(Color.GREEN);
		Label midtermLabel = new Label("Midterm --"+(midterm.getHeight()/3)+"%");
		
		Rectangle finall = new Rectangle();
		finall.setWidth(100);
		finall.setHeight(120);
		finall.setFill(Color.ORANGE);
		Label finaLabel = new Label("Final --"+(finall.getHeight()/3)+"%");
		
		
		FlowPane pPane = new FlowPane(projectLabel , project);
		pPane.setOrientation(Orientation.VERTICAL);
		FlowPane qPane = new FlowPane(quizLabel , quiz);
		qPane.setOrientation(Orientation.VERTICAL);
		FlowPane mPane = new FlowPane(midtermLabel , midterm);
		mPane.setOrientation(Orientation.VERTICAL);
		FlowPane fPane = new FlowPane(finaLabel , finall);
		fPane.setOrientation(Orientation.VERTICAL);
		
		ArrayList<FlowPane> flowPanes = new ArrayList<>(Arrays.asList(pPane , qPane ,
				mPane , fPane));
		
		Pane pane = new Pane(pPane , qPane , mPane , fPane);
		
		for (int i = 0; i < flowPanes.size(); i++) {
			flowPanes.get(i).setLayoutX(i*110 + 10);
			Rectangle rectangle =(Rectangle)(flowPanes.get(i).getChildren().get(1));
			flowPanes.get(i).setLayoutY(110 * 3 - rectangle.getHeight());
		}
		
		pane.setPadding(new Insets(10 , 10 , 10 , 10));
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Bar Chart");
		stage.show();
		}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}