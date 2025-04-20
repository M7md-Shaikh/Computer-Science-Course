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
import javafx.stage.Stage;

public class Act1 extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		BorderPane bp = new BorderPane();
		HBox hb = new HBox(10);
		Button b= new Button("save");
		b.setLayoutY(100);
		
		hb.getChildren().addAll(new Label("File Name : ") , new TextField (), new Button("Load"));
		hb.setAlignment(Pos.CENTER);
		
		StackPane sp = new StackPane();
		sp.getChildren().add(new Label("I Like JavaFX"));
		
		bp.setCenter(sp);
		bp.setTop(hb);
		bp.setAlignment(b, Pos.BOTTOM_CENTER);
		bp.setBottom(b);
		
		bp.setPadding(new Insets(10 , 10 , 10 , 10));
		Scene scene = new Scene(bp,400,400);
		arg0.setTitle("Activite 1");
		arg0.setScene(scene);
		arg0.show();	
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
