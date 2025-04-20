package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.beans.property.SimpleStringProperty;



public class Main extends Application {

    private Processor pro;
    private final List<Button> compressButtons = new ArrayList<>(); // this for store compress-related buttons
    private File file = null;
    private Label l = new Label();

    @Override
    public void start(Stage primaryStage) {
        try {
            Pane pane = new Pane();
            Scene scene = new Scene(pane, 550, 500);

            // header Label
            Label hd = new Label("Compress / Decompress File");
            hd.setFont(new Font(20));
            hd.setStyle("-fx-font-weight: bold;");

            // Compress Button
            Button comp = new Button("   Compress   ");
            comp.setPrefSize(150, 60);
            comp.setStyle("-fx-background-color: #b197c3; -fx-background-radius: 100; -fx-font-weight: bold;");

            // Decompress Button
            Button deComp = new Button(" Decompress ");
            deComp.setPrefSize(150, 60);
            deComp.setStyle("-fx-background-color: #b197c3; -fx-background-radius: 100; -fx-font-weight: bold;");

            // manage the components in pane
            pane.getChildren().addAll(hd, comp, deComp);
            hd.setTranslateX(140);
            hd.setTranslateY(50);
            comp.setTranslateX(100);
            comp.setTranslateY(120);
            deComp.setTranslateX(300);
            deComp.setTranslateY(120);

            // compress button action
            comp.setOnAction(a -> Compress(pane, primaryStage));

            // decompress button action
            deComp.setOnAction(t -> handleDecompress(pane, primaryStage));

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method to handle Compress button actions
    private void Compress(Pane pane, Stage primaryStage) {
        l.setText(" ");
        clearCompressButtons(pane);					// this for a restart the pane

        TextField tf = new TextField();
        tf.setPrefWidth(250);
        pane.getChildren().add(tf);
        tf.setTranslateX(120);
        tf.setTranslateY(220);

        Button br = new Button(" Browse ");				// the button that i choose the file i need 
        br.setStyle("-fx-background-radius:100;");
        pane.getChildren().add(br);
        br.setTranslateX(380);
        br.setTranslateY(220);

        Button start = new Button("  Start  ");			// to start the compress 
        start.setPrefSize(150, 25);
        start.setStyle("-fx-background-color: red; -fx-background-radius:100; -fx-font-weight: bold;");
        pane.getChildren().add(start);
        start.setTranslateX(200);
        start.setTranslateY(280);

        br.setOnAction(b -> {							// set the action on browse button
            FileChooser fc = new FileChooser();

            // set up the FileChooser to filter out .huff files
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files (except .huff)", "*.*"));
            fc.setInitialDirectory(new File(System.getProperty("user.home"))); //set default directory
            
            // add a custom extension filter to exclude .huff files
            fc.setInitialFileName(""); // default name hint
            fc.setTitle("Select a File (except .huff)");
           
            file = fc.showOpenDialog(primaryStage);

            if (file == null) {
                showErrorStage("Choose Any File!");
            } else if (file.getName().endsWith(".huff")) {
                showErrorStage("'.huff' files are not allowed!");
            } else {
                tf.setText(file.getPath());
            }
        });

        start.setOnAction(c -> {
            if (tf.getText().isEmpty()) {
                showErrorStage("Choose Any File!");
            } else {
                processCompression(pane);
            }
        });
    }

    private void processCompression(Pane pane) {
        try {
            CreateHuffman create = new CreateHuffman();
            create.readFile(file);
            create.buildHuffManTree();

            pro = new Processor(create.getTable(), create.getRoot());
            pro.compress(file);

            compressButtons(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void compressButtons(Pane pane) {
        Button stat = createButton("Statistic", 100, 350);
        pane.getChildren().add(stat);
        compressButtons.add(stat);

        Button huff = createButton(" Huffman ", 220, 350);
        pane.getChildren().add(huff);
        compressButtons.add(huff);

        Button head = createButton(" Header ", 340, 350);
        pane.getChildren().add(head);
        compressButtons.add(head);

        stat.setOnAction(s -> showStatistics(pane));
        huff.setOnAction(h -> showHuffmanTable());
        head.setOnAction(h -> headerInfo().show());
    }

    private void showStatistics(Pane pane) {
        l = new Label();
        l.setFont(new Font(12));
        pane.getChildren().add(l);
        l.setTranslateX(80);
        l.setTranslateY(395);
        l.setText(pro.stat.toString());
    }
    
    private void showHuffmanTable() {
    	BorderPane borderPane= new BorderPane();
        Stage tableStage = new Stage();
        tableStage.setTitle("Huffman Encoding Table");

        ArrayList<Huffman> data = new ArrayList<>();
		ObservableList<Huffman> dataList;

		for (int i = 0; i < pro.table.length; i++) {
			data.add(pro.table[i]);
		}
		dataList = FXCollections.observableArrayList(data);
		TableView<Huffman> myDataTable = new TableView<>();
		myDataTable.setEditable(true);
		// name of column for display
		TableColumn<Huffman, Integer> ascii = new TableColumn<>("ASCII");
		ascii.setMinWidth(50);
		ascii.setCellValueFactory(new PropertyValueFactory<>("ascii"));
		ascii.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		
		TableColumn<Huffman, String> character = new TableColumn<>("Character");
		character.setMinWidth(50);
		character.setCellValueFactory(cellData -> {
		    int asciiCode = cellData.getValue().getAscii(); // Fetch the ASCII code
		    char characterValue = (char) asciiCode; // Convert ASCII code to character
		    return new SimpleStringProperty(String.valueOf(characterValue)); // Wrap it in a SimpleStringProperty
		});


		TableColumn<Huffman, String> huffman = new TableColumn<>("Huffman code");
		huffman.setMinWidth(50);
		huffman.setCellValueFactory(new PropertyValueFactory<>("huffman"));

		TableColumn<Huffman, Integer> frequency = new TableColumn<>("Frequency");
		frequency.setMinWidth(50);
		frequency.setCellValueFactory(new PropertyValueFactory<>("frequency"));
		frequency.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

		TableColumn<Huffman, Integer> length = new TableColumn<>("Length of Code");
		length.setMinWidth(50);
		length.setCellValueFactory(new PropertyValueFactory<>("length"));
		length.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

		myDataTable.setItems(dataList);

		myDataTable.getColumns().addAll(ascii,character ,frequency,huffman, length);
		myDataTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		VBox vBox = new VBox(5);
		vBox.getChildren().add(myDataTable);
		borderPane.setBottom(vBox);
		BorderPane.setAlignment(vBox, Pos.BOTTOM_CENTER);

        Scene scene = new Scene(borderPane, 600, 400);
        tableStage.setScene(scene);
        tableStage.show();
    }

    
    private Stage headerInfo() {
        Stage headerStage = new Stage();
        headerStage.setTitle("Header Information");

        // Main container for header information
        BorderPane bp = new BorderPane();

        // Header label
        Label headerLabel = new Label("Header Details:");
        headerLabel.setFont(Font.font("Arial", 16));
        headerLabel.setStyle("-fx-font-weight: bold; -fx-padding: 10;");
        bp.setTop(headerLabel);
        BorderPane.setAlignment(headerLabel, Pos.TOP_CENTER);

        // Gather header information
        StringBuilder headerDetails = new StringBuilder();
        headerDetails.append("File Header Information (in Binary):\n");

        if (file != null) {
            String fileName = file.getName();
            String fileExtension = "";
            int extensionIndex = fileName.lastIndexOf('.');
            if (extensionIndex > 0) {
                fileExtension = fileName.substring(extensionIndex + 1).toUpperCase(); // Extract extension
            }

            headerDetails.append("File Name: ").append(fileName).append("\n");
            headerDetails.append("File Extension: ").append(fileExtension.isEmpty() ? "Unknown" : fileExtension).append("\n");
        } else {
            headerDetails.append("No file selected.\n");
        }

        if (pro != null && pro.list != null) {
            headerDetails.append("Additional Compression Details (in Binary):\n");
            for (int i = 0; i < pro.list.size(); i++) {
                if (i == 0) {
                    // Append the binary representation without line breaks
                    headerDetails.append(Integer.toBinaryString(pro.list.get(i) + 1));  // Extension length
                } else {
                    // Append each frequency in binary without space between them
                    headerDetails.append(Integer.toBinaryString(pro.list.get(i)));
                }
            }
        } else {
            headerDetails.append("No compression details available.\n");
        }

        // Output label
        Label outputLabel = new Label(headerDetails.toString());
        outputLabel.setFont(Font.font("Arial", 14));
        outputLabel.setWrapText(true);

        // Add scrollable content
        ScrollPane scrollPane = new ScrollPane(outputLabel);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));

        bp.setCenter(scrollPane);

        // Create scene and return stage
        Scene scene = new Scene(bp, 400, 300);
        headerStage.setScene(scene);
        return headerStage;
    }
    
    
    private void handleDecompress(Pane pane, Stage primaryStage) {
        l.setText(" ");
        clearCompressButtons(pane);

        TextField tf = new TextField();
        tf.setPrefWidth(250);
        pane.getChildren().add(tf);
        tf.setTranslateX(120);
        tf.setTranslateY(220);

        Button br = new Button(" Browse ");
        br.setStyle("-fx-background-radius:100;");
        pane.getChildren().add(br);
        br.setTranslateX(380);
        br.setTranslateY(220);

        Button start = new Button("  Start  ");
        start.setPrefSize(150, 25);
        start.setStyle("-fx-background-color: black; -fx-background-radius:100; -fx-font-weight: bold; -fx-text-fill: red;");
        pane.getChildren().add(start);
        start.setTranslateX(200);
        start.setTranslateY(280);

        br.setOnAction(e -> {
            FileChooser fc = new FileChooser();

            // Set a filter to only show .huff files
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Huff Files (*.huff)", "*.huff"));

            // Show the FileChooser dialog
            file = fc.showOpenDialog(primaryStage);

            if (file == null) {
                showErrorStage("Choose Any File!");
            } else {
                tf.setText(file.getPath());
            }
        });


        start.setOnAction(s -> {
            if (tf.getText().isEmpty()) {
                showErrorStage("Choose Any File!");
            } else {
                try {
                    if (pro == null) {
                        pro = new Processor(); // Replace with appropriate constructor if needed
                    }
                    pro.decompress(this.file);
                    l = new Label();
                    l.setText("Decompression is done");
                    l.setFont(new Font(15));
                    pane.getChildren().add(l);
                    l.setTranslateX(80);
                    l.setTranslateY(440);
                    tf.clear();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    private void clearCompressButtons(Pane pane) {
        for (Button button : compressButtons) {
            pane.getChildren().remove(button);
            l.setText(" ");
        }
        compressButtons.clear();
    }

   

    private Button createButton(String text, double x, double y) {
        Button button = new Button(text);
        button.setPrefSize(100, 25);
        button.setStyle(
                "-fx-background-color: linear-gradient(#6a1b9a, #8e24aa); " +
                        "-fx-background-radius: 10; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; ");
        button.setTranslateX(x);
        button.setTranslateY(y);
        return button;
    }

   

    private void showErrorStage(String message) {
        Stage errorStage = new Stage();
        Label label = new Label(message);
        label.setTextFill(javafx.scene.paint.Color.RED);
        label.setFont(Font.font("Arial", 14));
        VBox vbox = new VBox(label);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        Scene scene = new Scene(vbox, 300, 100);
        errorStage.setScene(scene);
        errorStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}