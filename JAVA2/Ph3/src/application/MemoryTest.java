package application;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class MemoryTest extends Application {

    private ArrayList<Martyr> nameList;
    private Label [] nameLabels;
    private TextField first;
    private TextField second;
    private Button submit;
    private Button clear;
    private Label response = new Label();; 
    private ComboBox<String> colorComboBox ;
    
    
    public MemoryTest() {
    	nameList = new ArrayList<>();
    }


    @Override
    public void start(Stage primaryStage) {
    	
    	//here we use Stack Pane , and decliration a boxing and button
    	StackPane sp = new StackPane();
    	Scene scene = new Scene(sp,300,200);
    	Button b1 = new Button("Create Martyr List Window");
    	Button b2 = new Button("Memory Test Window");
    	VBox vb = new VBox(15);
    	
    	// this is lambda expression to set an action handler for the button b1,b2(called another windows)
    	b1.setOnAction(e -> addMartyrWindow());    // here we called adding martyr window  
    	b2.setOnAction(e -> memoryWindow());       // here we called memory martyr window
    	
    	// VBoxing until sort the button  
    	vb.getChildren().addAll(b1,b2);
    	vb.setAlignment(Pos.CENTER);
    	sp.getChildren().add(vb);
    	
    	// here we adding the "scene" to "stage" until it showing 
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("The War on Gaza");
    	primaryStage.show();
    	
    }


    // here we put adding martyr  window in a method then we can called it in Start method (start window).
    public Stage addMartyrWindow() {
    	try {   
    	//here we use Stack Pane and declarition another button and Boxing	
    	Stage stage = new Stage();
    	StackPane sp = new StackPane();
    	Scene scene = new Scene(sp,600,100);
    	Label l ;
    	TextField tf = new TextField();
    	Button b = new Button("Add to File");
    	HBox hb = new HBox(10);
    	
    	l=new Label("Add Martyt : (Name   date of martyrdom)");
    	hb.getChildren().addAll(l,tf,b);
    	hb.setAlignment(Pos.CENTER);    // here until put it in the center of window
    	sp.getChildren().add(hb);
    	
    	// we here use a lambda expression adding a martyr in Binary File 
    	b.setOnAction(e -> {writeMartyrToFile(tf.getText()); tf.clear();});   	
    	
    	stage.setScene(scene);
    	stage.setTitle("Add a Martyr to the File");  // the title of the window
    	stage.show();
    	
    	return stage;
    	}catch(ArrayIndexOutOfBoundsException e) {
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
    
    
    // here we create a memory window to method until we can called it in another windows
    public Stage memoryWindow() {
    
    	// here use Border Stage to put each things in a suitable place
    	Stage stage = new Stage();
    	BorderPane  root= new BorderPane();
    	Scene scene = new Scene(root,800,500);
    	
    	//VBoxing use to sorted عمودي
    	VBox vb = new VBox(20);
    	VBox vb2 = new VBox(10);
    	
    	// HBox use to sorted HORZANTLE افقي
    	HBox hb = new HBox(20); 
    	HBox hbBotton = new HBox(10);
    	HBox colorSelector = new HBox(20);
    	
    	first = new TextField();        // Text Field for first Martyr
    	second = new TextField();       // Text Field for second Martyr
    	submit=new Button("submit");    // Button for submit(RUN THE WINDOW)
    	clear = new Button("clear");    // Button for clearing the Text Fields
    	
    	// Lambda for cleaning the Test Fields
    	clear.setOnAction(e -> {first.clear(); second.clear();});
    	// Lambda for Calling the method of submit we create it under 
    	submit.setOnAction(e -> {submitButton(first,second);});
    	
    	
    	// Combo Box for change the color as u need
    	colorComboBox = new ComboBox<>();
        colorComboBox.getItems().addAll("Red", "Blue", "Green", "Yellow");
        colorComboBox.setValue("Red");
        colorComboBox.setOnAction(e -> changeBackgroundColor()); // lambda called method to change the scene
    	
    	Text title = new Text("Test your Memory");
    	title.setFont(new Font(25));
    	TextFlow tf = new TextFlow();
    	Label txt = new Label("\n\t\t\t\t\t\tHey, my friend! Test your memory to see if you remember who was martyred before.\r\n"
				+ "\r\n"+ "\tPick two Martyr names from the following list, enter them in the boxes in the correct order (date of death), and then press the Submit button.");
    	tf.getChildren().add(txt);
    	vb.getChildren().addAll(title,tf);
    	vb.setAlignment(Pos.CENTER);
    	
    	
    	hb.getChildren().addAll(first,new Label("martyrd before"),second);
    	hb.setAlignment(Pos.CENTER);
    	hbBotton.getChildren().addAll(submit,clear,colorComboBox);
    	hbBotton.setAlignment(Pos.CENTER);
    	
        vb2.getChildren().addAll(hb,hbBotton,response);
        vb2.setAlignment(Pos.CENTER);
    	
    	readMartyrFromFile();
    	FlowPane flowPane = new FlowPane(40, 40); // horizontal and vertical gaps
        flowPane.getChildren().addAll(nameLabels);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setPadding(new Insets(10)); // Add padding for better aesthetics
        root.setCenter(flowPane);
    	
        
		stage.setTitle("Memory Test");
		root.setTop(vb);
		root.setBottom(vb2);
		root.setPadding(new Insets(50 , 10 , 50 , 10));
		stage.setScene(scene);
		stage.show();
		
    	return stage;  // return all a stage
    	
    	
    }
    
    
    // method for combo box button , for the colors , we called it in memory window
    private void changeBackgroundColor() {
    	
    	 String selectedColor = colorComboBox.getValue().toLowerCase();
         Scene scene = colorComboBox.getScene();  // to change the Scene

         switch (selectedColor) {
             case "red":
                 scene.getRoot().setStyle("-fx-background-color: red;");
                 break;
             case "blue":
                 scene.getRoot().setStyle("-fx-background-color: blue;");
                 break;
             case "green":
                 scene.getRoot().setStyle("-fx-background-color: green;");
                 break;
             case "yellow":
                 scene.getRoot().setStyle("-fx-background-color: yellow;");
                 break;
        }
    }
    
    
    // method to write a martyr to binary file , we called it in add martyr window
    public Label writeMartyrToFile(String martyrN) {
    	try {
    	
        try (FileOutputStream fos = new FileOutputStream("MartyrsList.dat", true);
                DataOutputStream dos = new DataOutputStream(fos)) {
        	
        	String[] parts1 = martyrN.split(" ");
      
        	// should have name and date 
        	if (parts1.length != 2) {
                response.setText("Invalid date format. Please use the format: day/month/year");
                return response;
            }
        	
        	
        	String[] part = martyrN.split(" ");
        	String name = part[0]; // first part of the String is name
        	String date = part[1]; // Second part of the String is date
        	
        	Martyr martyr = new Martyr(name,date);
        	nameList.add(martyr);
        	
        	dos.writeUTF(martyr.getMartyrName()+","+martyr.getDateOfMartyrdom());
        	dos.writeUTF("\n");
        	
        } catch (FileNotFoundException e) {
           response.setText(e.getMessage());
           return response;
        } catch (IOException e) {
        	  response.setText(e.getMessage());		}
        return response;
    	}catch(ArrayIndexOutOfBoundsException e) {
    		  response.setText(e.getMessage());   
    	}
		return response;
    }
    

    
    // method for reading a martyr from the binary file.
    public  Martyr readMartyrFromFile() {
    	
    	String filePath = "MartyrsList.dat";
        try (DataInputStream dis = new DataInputStream(new FileInputStream(filePath))) {
            nameList.clear(); // Clear the existing names

            while (dis.available() > 0) {
                String nameAndDate = dis.readUTF();
              
                String[] parts = nameAndDate.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    String date = parts[1];

                    Martyr martyr = new Martyr(name, date);
                    nameList.add(martyr);
                } else {
                    System.out.println("Invalid line format: " + nameAndDate);
                }
            }

            // update the nameLabels array
            nameLabels = new Label[nameList.size()];

            for (int i = 0; i < nameList.size(); i++) {
                Martyr martyr = nameList.get(i);
                nameLabels[i] = new Label(martyr.getMartyrName());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
		return null;
   }
   
    
    // here method for a submit button , and return the label (response) under the button
    public Label submitButton(TextField first , TextField second) {
    	
    	String firstName = first.getText();
 	    String secondName = second.getText();
 	    Label r = new Label();
 	    r.setAlignment(Pos.CENTER);
 	    
 	    	// if texts is empty
 	    if (firstName.isEmpty() || secondName.isEmpty()) {
 	        response.setText("Enter names in both boxes. Then press Submit.");
 	        return response;
 	  
 	        // if this names is not in the list
 	    } else if (!inList(firstName) && !inList(secondName)) {
 	    	response.setText("Neither entry is in the name list.");
 	       return response;
 	   
 	       // the first name is not in the list
 	    } else if (!inList(firstName)) {
 	       response.setText("First entry not in name list – check spelling.");
 	       return response;
 	    
 	      // the second name is not in the list
 	    } else if (!inList(secondName)) {
 	       response.setText("Second entry not in name list – check spelling.");
 	       return response;
 	    
 	       //  Both names are from the list but are the same
 	    } else if (firstName.equals(secondName)) {
 	       response.setText("You entered the same names. Try again.");
 	       return response;
 	    
 	    } else {
 	        //  Names are in the correct order
 	         Label test =(checkDate(firstName, secondName));
 	         r.setText(test.getText());
 	    }
    return r;	
    }
    
    
    // method to check whether the given name is in the name list.
    private boolean inList(String name) {
        
        for (Martyr martyr : nameList) {
            if (martyr.getMartyrName().equalsIgnoreCase(name)) {
                return true; // name is found in the list
            }
        }
        return false; // name is not found in the list
    }
    
    
    
    // method to check if first martyr was martyred before
    public Label checkDate(String firstName, String secondName) {
    
    	try {
    	
    		boolean firstMartyr = inList(firstName); // called inList method
    		boolean secondMartyr = inList(secondName);
    		
    		Martyr FMartyr = null, SMartyr = null;

    		// Check if both martyrs are found
    		if (firstMartyr == false || secondMartyr == false) {
    			response.setText("Martyr not found in the list. Check spelling.");
      	        return response;
      	    }

    		// Search for martyrs in the nameList by name
    		for (Martyr martyr : nameList) {
    			if (martyr.getMartyrName().equalsIgnoreCase(firstName)) {
    				FMartyr = martyr;
    	      } else if (martyr.getMartyrName().equalsIgnoreCase(secondName)) {
    	          	SMartyr = martyr;
    	      }
    		}
    	// Check if both martyrs are found
    	if (FMartyr == null || SMartyr == null) {
   	        response.setText("Martyr not found in the list. Check spelling.");
   	        return response;
   	    }

    	// split the String thats user entered 
    	String[] dateParts1 = FMartyr.getDateOfMartyrdom().split("/");
    	String[] dateParts2 = SMartyr.getDateOfMartyrdom().split("/");

    	int m1=Integer.parseInt(dateParts1[2]);
    	int m2=Integer.parseInt(dateParts2[2]);
    			
    	// sublit to days,months,and years
    	int day1 = Integer.parseInt(dateParts1[0]);
    	int month1 = Integer.parseInt(dateParts1[1]);
    	int year1 = m1;

    	int day2 = Integer.parseInt(dateParts2[0]);
    	int month2 = Integer.parseInt(dateParts2[1]);
    	int year2 = m2;

    	// to enter a date in future
        if (year1 > 2024 || year2 > 2024) {
        	response.setText("Wrong. We cannot add that the martyr was martyred in the future.");
            return response;
        }
        
        // to enter a wrong date
        if (month1 >12 || month2 > 12) {
            response.setText("Wrong. The large Number of months is 12");
            return response;
        }
        
        // to enter a wrong date
        if (day1 >31 || day2 > 31) {
            response.setText("Wrong. The large days Number of months is 31");
            return response;
        }
        // to enter a wrong date , date in future
        if (year1 == 2024 || month1 > 1) {
            response.setText("Wrong. That date wasn't come until this moment(FUTURE)");
            return response;
        }
        
        if (year2 == 2024 || month2 > 1) {
            response.setText("Wrong. That date wasn't come until this moment (FUTURE)");
            return response;
        }
        
        if (year1 != year2) {
            if (year1 < year2) {
                response.setText("You Are Correct!");
                return response;
            } else {
                response.setText("Wrong. Try again");
                return response;
            }
        } else if (month1 != month2) {
            if (month1 < month2) {
                response.setText("You Are Correct!");
                return response;
            } else {
                response.setText("Wrong. Try again");
                return response;
            }
        } else if (day1 != day2) {
            if (day1 < day2) {
                response.setText("You Are Correct!");
                return response;
            } else {
                response.setText("Wrong. Try again");
                return response;
            }
        } else {
            response.setText("You entered the same names. Try again");
            return response;
            
        }
    	} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
    		response.setText(e.getMessage());
    	    return response;
    	}
    }
    
   
   // main method 
    public static void main(String[] args) {
    	
    	launch(args);
        
    }
}