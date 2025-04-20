package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		BorderPane borderPane = new BorderPane();
		VBox vb = new VBox(10);
    	HBox bottomHbox = new HBox(50);


		
		Image wallpaperImage = new Image("Game.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(
                 wallpaperImage,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundPosition.CENTER,
                 new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        		);
        

        Label l1 = new Label("Enter Coins number :");
		l1.setFont(new Font(18));
        l1.setStyle("-fx-text-fill: white;"); // Set text color to red

        
		TextField t1 = new TextField();
		t1.setStyle("-fx-background-radius:100;\r\n"); // change the radius of the text field

		Label l2 = new Label("Enter the coins (with space between them):");
		l2.setFont(new Font(18));
        l2.setStyle("-fx-text-fill: white;");


		TextField t2 = new TextField();
		t2.setStyle("-fx-background-radius:100;\r\n");
		
		
		Button randomB = new Button("Random");
		randomB.setPrefSize(80, 10);
		randomB.setStyle(
            "-fx-background-color: linear-gradient(#6a1b9a, #8e24aa); " +
            "-fx-background-radius: 10; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: bold; " );

		
		Button fileB = new Button("File");
		fileB.setPrefSize(80, 10);
		fileB.setStyle(
            "-fx-background-color: linear-gradient(#6a1b9a, #8e24aa); " +
            "-fx-background-radius: 10; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: bold; " );
        
        
        Button restartB = new Button("Restart");
        restartB.setPrefSize(100, 10);
        restartB.setStyle(
		            "-fx-background-color: linear-gradient(#6a1b9a, #8e24aa); " +
		            "-fx-background-radius: 10; " +
		            "-fx-text-fill: white; " +
		            "-fx-font-weight: bold; " );
		//borderPane.getChildren().addAll(restartB);
	


		vb.setAlignment(Pos.CENTER);
		GridPane gp1 = new GridPane();
		gp1.add(l1, 0, 0);
		gp1.add(t1, 1,0);
		gp1.add(randomB, 5,0);
		gp1.add(l2, 0, 1);
		gp1.add(t2, 1, 1);
		gp1.add(fileB, 5,1);
		gp1.setAlignment(Pos.CENTER);
		gp1.setVgap(10);
		gp1.setHgap(10);
		vb.getChildren().addAll(gp1);
        BorderPane.setMargin(vb, new Insets(40)); // set margin around VBox in the BorderPane

        Button solveB = new Button("Solve");
        solveB.setPrefSize(100, 10);
        solveB.setStyle(
				"-fx-background-color: linear-gradient(#6a1b9a, #8e24aa); " +
				"-fx-background-radius: 10; " +
	            "-fx-text-fill: white; " +
	            "-fx-font-weight: bold; " );



		Button explainB = new Button("Explain");
		explainB.setPrefSize(100,10);
		explainB.setStyle("-fx-background-color: linear-gradient(#6a1b9a, #8e24aa); " +
				"-fx-background-radius: 10; " +
	            "-fx-text-fill: white; " +
	            "-fx-font-weight: bold; ");
		HBox hb = new HBox(10);
		hb.getChildren().addAll(restartB,solveB,explainB);
		hb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(hb);
		
		
		Label line0 = new Label(
					"-----------------------------------------------------------------------------------");
			line0.setFont(new Font(20));
			line0.setStyle("-fx-text-fill: white;");
			vb.getChildren().add(line0);
			
		
		Label l3 = new Label("Max ammount :");
		l3.setFont(new Font(18));
		l3.setStyle("-fx-text-fill: white;");
		
		Label l4 = new Label("Player #1 Coins :");
		l4.setFont(new Font(18));
		l4.setStyle("-fx-text-fill: white;");
		
		TextField t3= new TextField();
		t3.setStyle("-fx-background-radius:100;\r\n"); // change the radius of the text field
		t3.setPrefSize(350, 10);
		t3.setFont(new Font(15));
		t3.setEditable(false);

		TextField t4= new TextField();
		t4.setStyle("-fx-background-radius:100;\r\n"); // change the radius of the text field
		t4.setPrefSize(350, 10);
		t4.setFont(new Font(15));
		t4.setEditable(false);

		
		GridPane gp2 = new GridPane();
		gp2.add(l3, 0, 0);
		gp2.add(t3, 1,0);
		gp2.add(l4, 0, 1);
		gp2.add(t4, 1, 1);
		gp2.setAlignment(Pos.CENTER);
		gp2.setVgap(10);
		gp2.setHgap(10);
		vb.getChildren().addAll(gp2);
		
        VBox vb2 = new VBox(10);
        Label dpL = new Label("The DP Table : ");
        dpL.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-padding: 4px;");
        dpL.setFont(new Font(18));
        
        TextArea ta = new TextArea();
        ta.setPrefSize(50, 200);
		ta.setFont(new Font(15));
        ta.setEditable(false);
		vb2.getChildren().addAll(dpL , ta);
		vb2.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(vb2);

        
		Label line1 = new Label(
				"-----------------------------------------------------------------------------------");
		line1.setFont(new Font(20));
		line1.setStyle("-fx-text-fill: white;");
		vb.getChildren().add(line1);
		
		
		randomB.setOnAction(e -> {
		    Random random = new Random();

		    // check if TextField t1 is empty and set the number of coins
		    if (t1.getText().isEmpty()) {
		        int coinsNum = (random.nextInt(5) + 1) * 2; // Random even number of coins
		        t1.setText(String.valueOf(coinsNum));
		    }

		    int coinsNum = Integer.parseInt(t1.getText());
		    
		    // if the number is odd or <= 0, show an error message
		    if (coinsNum % 2 != 0 || coinsNum <= 0) {
		        Stage stage = new Stage();
		        Label l = new Label("The number is odd, enter an even number");
		        l.setFont(new Font(15));
		        Button b1 = new Button("   OK   ");
		        b1.setOnAction(s -> {
		            stage.close();
		        });

		        BorderPane bp1 = new BorderPane();
		        bp1.setCenter(l);
		        bp1.setAlignment(b1, Pos.CENTER);
		        bp1.setBottom(b1);
		        Scene s = new Scene(bp1, 300, 100);
		        stage.setScene(s);
		        stage.setTitle("Error");
		        stage.show();
		        t1.clear();
		        return;
		    }

		    // create a small dialog for the user to select the range
		    Stage rangeStage = new Stage();
		    rangeStage.setTitle("Select Range");

		    // Layout for the range selection
		    VBox vbox = new VBox(10);
		    vbox.setAlignment(Pos.CENTER);
		    vbox.setPadding(new Insets(20));

		    // Create input fields for "From" and "To"
		    TextField fromField = new TextField();
		    fromField.setPromptText("From (e.g., 0)");
		    
		    TextField toField = new TextField();
		    toField.setPromptText("To (e.g., 20)");

		    Button confirmButton = new Button("Confirm");

		    // Confirm button action to use the selected range
		    confirmButton.setOnAction(a -> {
		        try {
		            int fromRange = Integer.parseInt(fromField.getText());
		            int toRange = Integer.parseInt(toField.getText());

		            // Check if the range is valid (from < to)
		            if (fromRange >= toRange) {
		                showError("Invalid range: 'From' should be less than 'To'.");
		                return;
		            }

		            // generate random coins based on the selected range
		            StringBuilder coins = new StringBuilder();
		            for (int i = 0; i < coinsNum; i++) {
		                coins.append(random.nextInt(toRange - fromRange + 1) + fromRange); // Random number within range
		                if (i < coinsNum - 1) {
		                    coins.append(" ");
		                }
		            }

		            t2.setText(coins.toString());
		            rangeStage.close(); // Close the range selection dialog
		        } catch (NumberFormatException ex) {
		            showError("Please enter valid numeric values for the range.");
		        }
		    });

		    // Add components to the VBox
		    vbox.getChildren().addAll(new Label("Enter range:"), fromField, toField, confirmButton);

		    // Create the scene and set it to the range stage
		    Scene rangeScene = new Scene(vbox, 300, 200);
		    rangeStage.setScene(rangeScene);
		    rangeStage.show();
		});

	



		
		// here if i need to read data from file
		fileB.setOnAction(e -> {
		    FileChooser fileChooser = new FileChooser();
		    fileChooser.setTitle("Open Coin File");

		    File file = fileChooser.showOpenDialog(primaryStage);
		    if (file != null) {
		        try (Scanner scanner = new Scanner(file)) {
		            if (scanner.hasNextInt()) {
		                int coinsNum = scanner.nextInt(); 
		                t1.setText(String.valueOf(coinsNum)); 

		                StringBuilder coins = new StringBuilder();
		                while (scanner.hasNextInt()) {
		                    coins.append(scanner.nextInt()).append(" "); 
		                }
		                
		                t2.setText(coins.toString().trim());
		            }
		        } catch (FileNotFoundException ex) {
		            ex.printStackTrace();
		        }
		    }
		});


        restartB.setOnAction(e ->  {
        	
        	t1.clear();
        	t2.clear();
        	t3.clear();
        	t4.clear();
        	ta.clear();
        	bottomHbox.getChildren().clear();
        	
        });
        
        solveB.setOnAction(e -> {
            if (t1.getText().isEmpty() || t2.getText().isEmpty()) {
                errorStage("You missed something");
                t1.clear();
                t2.clear();
            } else {
                if (Integer.parseInt(t1.getText()) % 2 != 0 || Integer.parseInt(t1.getText()) <= 0) {
                    errorStage("The number is odd; enter an even number");
                    t1.clear();
                    t2.clear();
                } else {
                    String[] s = t2.getText().trim().split(" ");
                    if (s.length != Integer.parseInt(t1.getText())) {
                        errorStage("Enter the same number of coins as you chose");
                        t1.clear();
                        t2.clear();
                    } else {
                        int[] arr = new int[s.length];
                        for (int i = 0; i < arr.length; i++) {
                            if (s[i].isEmpty()) break;
                            arr[i] = Integer.parseInt(s[i]);
                        }

                        Object[][] dp = getTable(arr);

                        t3.setText(String.valueOf(dp[0][arr.length - 1].first));

                        // Building DP table display with indices
                        StringBuilder tableBuilder = new StringBuilder();
                        
                        // Add index row
                        tableBuilder.append("\t"); // First cell empty
                        for (int i = 0; i < arr.length; i++) {
                            tableBuilder.append(i).append("\t");
                        }
                        tableBuilder.append("\n");

                        // Add table with row indices
                        for (int i = 0; i < dp.length; i++) {
                            tableBuilder.append(i).append(" |\t"); // Row index
                            for (int j = 0; j < dp[i].length; j++) {
                                if (dp[i][j] != null) {
                                    tableBuilder.append(dp[i][j].first).append("\t");
                                } else {
                                    tableBuilder.append("-\t"); // Empty cell placeholder
                                }
                            }
                            tableBuilder.append("\n");
                        }
                        ta.setText(tableBuilder.toString());

                        int[] playerCoins = getCoins(arr, dp);
                        StringBuilder coinsBuilder = new StringBuilder();
                        for (int i = 0; i < playerCoins.length; i += 2) {
                            coinsBuilder.append("(").append(playerCoins[i]).append(") ");
                        }

                        t4.setText(coinsBuilder.toString());

                        VBox player1Box = new VBox(10);
                        VBox player2Box = new VBox(10);
                        HBox hbox = new HBox(10);
                        SequentialTransition seqT = new SequentialTransition();
    
                    	explainB.setOnAction(a -> {
                    		
                    		bottomHbox.getChildren().clear();
                    		seqT.getChildren().clear();
                    		player2Box.getChildren().clear();
                    		player1Box.getChildren().clear();
                    		hbox.getChildren().clear();
                    		
                    		if (t1.getText().isEmpty() || t2.getText().isEmpty()) {
                    			errorStage("You Don't Solve Yet !");
                    			return;
                    		}
                    		
                    	    // Create new labels and elements as usual
                    	    Label player1 = new Label("Player #1");
                    	    player1.setFont(new Font(30));
                    	    player1.setStyle("-fx-text-fill: red;");

                    	    Label player2 = new Label("Player #2");
                    	    player2.setFont(new Font(30));
                    	    player2.setStyle("-fx-text-fill: blue;");

                    	    player1Box.setAlignment(Pos.CENTER);
                    	    player1Box.getChildren().add(player1);

                    	    player2Box.setAlignment(Pos.CENTER);
                    	    player2Box.getChildren().add(player2);

                    	    StackPane[] stack = new StackPane[arr.length];
                    	    hbox.setAlignment(Pos.CENTER);

                    	    for (int i = 0; i < stack.length; i++) {
                    	        stack[i] = new StackPane();
                    	        Circle circle = new Circle(20);
                    	        circle.setFill(Color.WHITE);
                    	        circle.setStroke(Color.BLACK);
                    	        Label label = new Label(String.valueOf(arr[i]));
                    	        stack[i].getChildren().addAll(circle, label);
                    	        hbox.getChildren().add(stack[i]);
                    	    }

                    	    bottomHbox.getChildren().addAll(player1Box, hbox, player2Box);
                    	    bottomHbox.setAlignment(Pos.CENTER);

                    	    // Ensure bottomHbox is added to vb only once to avoid duplicates
                    	    if (!vb.getChildren().contains(bottomHbox)) {
                    	        vb.getChildren().add(bottomHbox);
                    	    }


                            int[] array = getSeq(arr, dp);
                            int[] player1Indexes = new int[arr.length / 2];
                            int[] player2Indexes = new int[arr.length / 2];

                            // Distribute indexes for player choices
                            for (int i = 0, j = 0, k = 0; i < playerCoins.length; i++) {
                                if (i % 2 == 0) {
                                    player1Indexes[j] = array[i];
                                    j++;
                                } else {
                                    player2Indexes[k] = array[i];
                                    k++;
                                }
                            }

                            // Create the transitions for each player's chosen coins

                            TranslateTransition[] tt = new TranslateTransition[arr.length];
                            for (int i = 0, j = 0, k = 0; i < stack.length; i++) {
                                if (i % 2 == 0) { // Player #1's turn
                                    tt[i] = new TranslateTransition(Duration.seconds(2.3), stack[player1Indexes[j]]);
                                    tt[i].setCycleCount(1);
                                    int chosenValue = arr[player1Indexes[j]]; // Get the chosen coin value
                                    int chosenIndex = player1Indexes[j]; // Store the index of the coin

                                    tt[i].setOnFinished(event -> {
                                        // Create StackPane for the selected coin with a red circle and label
                                        StackPane player1Choice = new StackPane();
                                        Circle player1Circle = new Circle(10, Color.RED);
                                        Label player1Label = new Label(String.valueOf(chosenValue));
                                        player1Label.setTextFill(Color.WHITE); // White text for readability
                                        player1Choice.getChildren().addAll(player1Circle, player1Label);
                                        player1Box.getChildren().add(player1Choice);

                                        // Remove the chosen coin from the sequence in hbox
                                        hbox.getChildren().remove(stack[chosenIndex]);
                                    });

                                    seqT.getChildren().add(tt[i]);
                                    j++;
                                } else { // Player #2's turn
                                    tt[i] = new TranslateTransition(Duration.seconds(1.5), stack[player2Indexes[k]]);
                                    tt[i].setCycleCount(1);
                                    int chosenValue = arr[player2Indexes[k]]; // Get the chosen coin value
                                    int chosenIndex = player2Indexes[k]; // Store the index of the coin

                                    tt[i].setOnFinished(event -> {
                                        // Create StackPane for the selected coin with a blue circle and label
                                        StackPane player2Choice = new StackPane();
                                        Circle player2Circle = new Circle(10, Color.BLUE);
                                        Label player2Label = new Label(String.valueOf(chosenValue));
                                        player2Label.setTextFill(Color.WHITE); // White text for readability
                                        player2Choice.getChildren().addAll(player2Circle, player2Label);
                                        player2Box.getChildren().add(player2Choice);

                                        // Remove the chosen coin from the sequence in hbox
                                        hbox.getChildren().remove(stack[chosenIndex]);
                                    });

                                    seqT.getChildren().add(tt[i]);
                                    k++;
                                }
                            }


                            seqT.play();
                        });

                    }
                }
            }
        });
        
        borderPane.setTop(vb);
        Background background = new Background(backgroundImage);
        borderPane.setBackground(background);
        Scene scene = new Scene(borderPane, 850, 800);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Game");
        primaryStage.show();
	}

	// Helper method to show an error message in case of invalid input
	private void showError(String message) {
	    Stage stage = new Stage();
	    Label l = new Label(message);
	    l.setFont(new Font(15));
	    Button b1 = new Button("   OK   ");
	    b1.setOnAction(s -> {
	        stage.close();
	    });

	    BorderPane bp1 = new BorderPane();
	    bp1.setCenter(l);
	    bp1.setAlignment(b1, Pos.CENTER);
	    bp1.setBottom(b1);
	    Scene s = new Scene(bp1, 300, 100);
	    stage.setScene(s);
	    stage.setTitle("Error");
	    stage.show();
	}
	

	private void errorStage(String message) {
		
		Stage stage = new Stage();
		Label label = new Label(message);
		label.setFont(new Font(15));
		Button button = new Button("   OK   ");
		button.setOnAction(s -> stage.close());
		
		BorderPane pane = new BorderPane();
		pane.setCenter(label);
		pane.setBottom(button);
		BorderPane.setAlignment(label, Pos.CENTER);
		BorderPane.setAlignment(button, Pos.CENTER);
		
		Scene scene = new Scene(pane, 350, 100);
		stage.setScene(scene);
		stage.setTitle("Error");
		stage.show();
	}

        

	public static Object[][] getTable(int[] arr) {
		int n = arr.length;

		Object[][] dp = new Object[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dp[i][j] = new Object(0, 0);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j)
					dp[i][j] = new Object(arr[i], 0);
			}
		}

		for (int i = 0; i < arr.length; i++) {
			// to track the sequence of moves
			dp[i][i].pick = i;
		}
		
		
		if (n == 1) {
			dp[0][0].first =  arr[0];
			return dp;
		 }
		
		
		if ( n==2 ) {
			dp[0][0].first =  Math.max(arr[0], arr[1]);
			dp[0][0].second =  Math.min(arr[0], arr[1]);
			return dp;
		}

		for (int i = n; i >= 0; i--) {
			for (int j = 0; j < n; j++) {
				if (j > i) {
					if (dp[i + 1][j].second + arr[i] > dp[i][j - 1].second + arr[j]) {
						dp[i][j].first = dp[i + 1][j].second + arr[i];
						dp[i][j].second = dp[i + 1][j].first;
						dp[i][j].pick = i;
					} else {
						dp[i][j].first = dp[i][j - 1].second + arr[j];
						dp[i][j].second = dp[i][j - 1].first;
						dp[i][j].pick = j;
					}

				}
			}
		}
		return dp;
	}
	
	
	// this for the coins that choosin by the first player
	public static int[] getCoins(int arr[], Object dp[][]) {
		int i = 0;
		int j = arr.length - 1;
		int step;
		int[] coins = new int[arr.length];
		for (int k = 0; k < arr.length; k++) {
			step = dp[i][j].pick;
			// this is the value of pick and its index
			coins[k] = arr[step];
			if (step <= i) {
				i = i + 1;
			} else {
				j = j - 1;
			}
		}
		return coins;
	}

	
	// this for get the indices of the selected coins.
	public static int[] getSeq(int arr[], Object dp[][]) {
		int i = 0;
		int j = arr.length - 1;
		int step;
		int[] coins = new int[arr.length];
		for (int k = 0; k < arr.length; k++) {
			step = dp[i][j].pick;
			// this is the value of pick and its index
			coins[k] = step;
			if (step <= i) {
				i = i + 1;
			} else {
				j = j - 1;
			}
		}
		return coins;
	}
}
