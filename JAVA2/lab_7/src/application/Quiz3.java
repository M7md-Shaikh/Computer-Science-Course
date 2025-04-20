package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Quiz3 extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		
		Label t = new Label("Welcome From Java 2");
		t.setFont(new Font(22));
		t.setStyle("-fx-background-color: yellow; -fx-background-radius:50%;");
		
		VBox v = new VBox(t);
		v.setAlignment(Pos.CENTER);
		
		Rectangle r1 = new Rectangle();
		r1.setWidth(30);
		r1.setHeight(90);
		r1.setRotate(90);
		r1.setFill(Color.YELLOW);
		
		Rectangle r2 = new Rectangle();
		r2.setWidth(30);
		r2.setHeight(90);
		r2.setFill(Color.GREEN);
	
		
		Rectangle r3 = new Rectangle();
		r3.setWidth(30);
		r3.setHeight(90);
		r3.setRotate(45);
		r3.setFill(Color.BLUE);
	
		Rectangle r4 = new Rectangle();
		r4.setWidth(30);
		r4.setHeight(90);
		r4.setRotate(135);
		r4.setFill(Color.RED);
		
		HBox h = new HBox(20);
		StackPane sp = new StackPane(r1,r2,r3,r4);
		Label l = new Label("Your NAME ");
		l.setTextFill(Color.RED);
		h.getChildren().addAll(new TextField() , new Button("Ok button"),new TextField());
		h.setAlignment(Pos.CENTER);
		
		BorderPane p = new BorderPane();
	
		p.setAlignment(l, Pos.CENTER);
		p.setRight(l);
		p.setTop(v);
		p.setCenter(sp);
		p.setBottom(h);
		
		


		Scene scene = new Scene(p,400,400);
		arg0.setScene(scene);
		arg0.setTitle("quiz");
		arg0.show();
		
		
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
