package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class test extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Rectangle quiz = new Rectangle();
		quiz.setWidth(30);
		quiz.setHeight(90);
		quiz.setRotate(90);
		quiz.setFill(Color.RED);
		
		Rectangle midterm = new Rectangle();
		midterm.setWidth(30);
		midterm.setHeight(90);
		midterm.setFill(Color.BLUE);
	
		
		Rectangle finall = new Rectangle();
		finall.setWidth(30);
		finall.setHeight(90);
		finall.setRotate(45);
		finall.setFill(Color.GREEN);
	
		Rectangle midterm1 = new Rectangle();
		midterm1.setWidth(30);
		midterm1.setHeight(90);
		midterm1.setRotate(135);
		midterm1.setFill(Color.YELLOW);
		
		HBox h = new HBox(10);
		Button b = new Button("click me");
		h.getChildren().addAll(new Label("Your name "), new TextField() , new TextField() , new Button("click me"));
		
		BorderPane p = new BorderPane(quiz,midterm,finall,midterm1,h);

		Scene scene = new Scene(p);
		arg0.setScene(scene);
		arg0.setTitle("Bar Chart");
		arg0.show();
		
		
	}
	public static void main(String[] args) {
		Application.launch(args);
	}

}
