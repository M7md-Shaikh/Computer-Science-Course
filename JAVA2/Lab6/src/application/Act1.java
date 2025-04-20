package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Act1 extends Application {
	
	@Override
	public void start(Stage stage ) throws Exception {
		ImageView iView = new ImageView("تنزيل.jpg");
		iView.setFitHeight(500);
		iView.setFitWidth(500);
		Pane pane = new Pane(iView);
		Scene scene = new Scene(pane);
		stage.setTitle("Activite 1");
		stage.setScene(scene);
		stage.show();		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
