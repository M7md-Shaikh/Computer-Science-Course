package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Mem extends Application {

    private ComboBox<String> colorComboBox;
    private TextArea feedbackTextArea;
    private TextField firstMartyrField;
    private TextField secondMartyrField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Memory Test Game");

        // Create layout components
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));

        HBox martyrsBox = createMartyrsBox();
        HBox inputBox = createInputBox();
        HBox colorBox = createColorBox();

        feedbackTextArea = new TextArea();
        feedbackTextArea.setEditable(false);

        // Add components to the layout
        mainLayout.getChildren().addAll(martyrsBox, inputBox, colorBox, feedbackTextArea);

        Scene scene = new Scene(mainLayout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createMartyrsBox() {
        HBox martyrsBox = new HBox(10);

        // Add buttons for martyrs
        Button martyr1 = createMartyrButton("Martyr 1");
        Button martyr2 = createMartyrButton("Martyr 2");
        // Add more buttons for other martyrs

        martyrsBox.getChildren().addAll(martyr1, martyr2);

        return martyrsBox;
    }

    private Button createMartyrButton(String martyrName) {
        Button button = new Button(martyrName);
        button.setOnAction(e -> handleMartyrButtonClick(martyrName));
        return button;
    }

    private void handleMartyrButtonClick(String martyrName) {
        // Logic to handle the selection of a martyr button
        // You may want to store the selected martyrs and compare them later
    }

    private HBox createInputBox() {
        HBox inputBox = new HBox(10);

        // Add text fields for entering martyred names
        firstMartyrField = new TextField();
        firstMartyrField.setPromptText("Enter first martyr");

        secondMartyrField = new TextField();
        secondMartyrField.setPromptText("Enter second martyr");

        // Add buttons for submitting and clearing input
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> checkOrder());

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clearInput());

        inputBox.getChildren().addAll(firstMartyrField, secondMartyrField, submitButton, clearButton);

        return inputBox;
    }

    private void checkOrder() {
        // Logic to check if the entered martyrs are in the correct order
        // Display feedback in the feedbackTextArea
    }

    private void clearInput() {
        // Logic to clear the input fields
        firstMartyrField.clear();
        secondMartyrField.clear();
        feedbackTextArea.clear();
    }

    private HBox createColorBox() {
        HBox colorBox = new HBox(10);

        // Add ComboBox for selecting a color
        colorComboBox = new ComboBox<>();
        colorComboBox.getItems().addAll("Red", "Blue", "Green", "Yellow");
        colorComboBox.setValue("Red");

        // Add button for changing background color
        Button changeColorButton = new Button("Change Color");
        changeColorButton.setOnAction(e -> changeBackgroundColor());

        colorBox.getChildren().addAll(colorComboBox, changeColorButton);

        return colorBox;
    }

    private void changeBackgroundColor() {
        // Logic to change the background color based on the selected color in the ComboBox
        String selectedColor = colorComboBox.getValue().toLowerCase();

        switch (selectedColor) {
            case "red":
                feedbackTextArea.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case "blue":
                feedbackTextArea.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case "green":
                feedbackTextArea.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case "yellow":
                feedbackTextArea.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            default:
                // Handle other cases or set a default color
                break;
        }
    }
}
