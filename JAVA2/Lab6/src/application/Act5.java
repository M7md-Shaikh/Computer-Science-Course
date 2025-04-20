package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Act5 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = new Pane();
		
		double[] scores = { 35, 55, 10};
		
		String[] grades = { "Scientific", "Literary", "Other"};
		
		Text t1 = new Text(110, 70, grades[0] + " -- " + scores[0] + "%");
		Text t2 = new Text(5, 100, grades[1] + " -- " + scores[1] + "%");
		Text t3 = new Text(110, 110, grades[2] + " -- " + scores[2] + "%");
		
		Arc arc1 = new Arc(100, 100, 80, 80, 0, 360 * (scores[0] / 100));
		arc1.setFill(Color.RED);
		arc1.setType(ArcType.ROUND);
		
		Arc arc2 = new Arc(100, 100, 80, 80, arc1.getStartAngle() + arc1.getLength(), 
		360 * (scores[1] / 100));
		arc2.setFill(Color.LIGHTBLUE);
		arc2.setType(ArcType.ROUND);
		
		Arc arc3 = new Arc(100, 100, 80, 80, arc2.getStartAngle() + arc2.getLength(), 
		360 * (scores[2] / 100));
		arc3.setFill(Color.GREEN);
		arc3.setType(ArcType.ROUND);
		
		pane.getChildren().addAll(arc1, arc2, arc3, t1, t2, t3);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
