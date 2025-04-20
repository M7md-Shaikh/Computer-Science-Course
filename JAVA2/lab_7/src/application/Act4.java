package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Act4 extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		TextField m = new TextField();
		m.setPrefColumnCount(1);
		BorderPane bp = new BorderPane();
		FlowPane fp1 = new FlowPane();
		FlowPane fp2 = new FlowPane();
		FlowPane fp3 = new FlowPane();
		
//		fp1.getChildren().addAll(new Label("ID"),new TextField() , new Label("Name ")
//				, new TextField() , new Label("Gender"),m,new Label("Seat Num"),new TextField() , new Label("Branch ")
//				, new TextField(),new Label("Year"),new TextField() , new Label("School ")
//				, new TextField());
		
		HBox hp1 = new HBox();
		hp1.getChildren().addAll(new Label("ID"),new TextField() , new Label("Name ")
				, new TextField() , new Label("Gender"),m);
		
		HBox hp2= new HBox();
		hp1.getChildren().addAll(new Label("Seat Num"),new TextField() , new Label("Branch ")
				, new TextField());
		
		HBox hp3 = new HBox();
		hp1.getChildren().addAll(new Label("Year"),new TextField() , new Label("School ")
			, new TextField());

		
		fp1.getChildren().addAll(hp1);
		fp2.getChildren().addAll(hp2);
		fp3.getChildren().addAll(hp3);
		
		bp.getChildren().addAll(fp1,fp2,fp3);
		
		bp.setPadding(new Insets(10 , 10 , 10 , 10));
		Scene scene = new Scene(bp,500,500);
		arg0.setTitle("Activite 1");
		arg0.setScene(scene);
		arg0.show();	
		
		
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
