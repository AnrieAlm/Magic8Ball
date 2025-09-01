//Magic8Ball HGP Assignment 01
//Anriel Maire Almeida - 3168178

package application;


//Standard JavaFX imports
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
//import for alerts added
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//imports for controls
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
//imports for layouts
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
//imports for file i/o
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//imports for arrayList
import java.util.ArrayList;
import java.util.List;

//imports for images
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// imports for extra features
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;


public class Magic8Ball extends Application {
	// create compoments which will be added to sublayout
	// sublayout is added to main layout and main layout is added to scene 
	// scene is added to stage 
	//Declare components that require class scope
	private BorderPane bpMain; // <-- Declaring it here so it can be used by other classes and is private(encapsulation) !!
	Label promptLabel; //Label to prompt user action
	Label answerLabel; // Label to display prediction

	Image img; //stores default image 
	ImageView imv; // displays image 
	Button btnChoose; // button for file selection (unused)

	MenuBar mbMain; //main menu bar
	Menu mnuFile, mnuAbout; // menu for about and file
	MenuItem miFileChange, miAboutHelp; // menu items for file change and about 

	// extra feature 
	Button btnFeelingLucky;
	// Extra feature: Leprechaun image and click counter for easter egg
	ImageView leprechaunView;
	int luckyClickCount = 0; // adding a click counter for Easter egg declaring it here so it can be used by other classes



	//Student Details
	String yourName = "Anriel Maire Almeida"; 
	String yourStudentNum= "3168178"; 

	//Instantiate components
	public Magic8Ball() {
		// Instantiate components using keyword 'new'
		// Instantiate Labels
		promptLabel = new Label("Click the Magic 8 Ball!"); //prompt for users to click 
		answerLabel = new Label(""); // initially empty label

		// Instantiate Button// button to choose file 
		btnChoose = new Button("Choose File");


		// Instantiate Image and ImageView
		// volatile actions in try/catch / image is volatile
		try {
			img = new Image("file:Assets/magic8ball.png"); // Ensure the image exists in the Assets folder
			imv = new ImageView(img);  //the image should be inside ImageView like a picture inside a frame

		}
		catch (Exception oops) { // exception named oops 
			System.err.println("Error loading default image!"); // custom message for us to catch error
			oops.printStackTrace(); //print stack trace 
		}

		//Instatiate Menu items  using word new
		mbMain = new MenuBar();
		//File menu and menu items 
		mnuFile = new Menu("File");
		miFileChange = new MenuItem("Change File");
		//About menu and menu items 
		mnuAbout = new Menu("About");
		miAboutHelp = new MenuItem("Help");

		// extra feature 
		// Declare the "Feeling Lucky" button
		btnFeelingLucky = new Button("Feeling Lucky");

		// Read predictions from CSV file -using conditional statements to see that it exists 
		File answersFile = new File("Assets/magicAnswers.csv"); //check for magic answers
		if (answersFile.exists()) {//if there read deafult predictions
			readMagicAnswers("Assets/magicAnswers.csv"); 
		} else {
			System.err.println("magicAnswers.csv file does not exist!"); // custom error message printed 
		}

		readIrishAnswers("Assets/irishAnswers.csv"); // method call of method readIrishAnswers , parameter is extra csv file

		// Extra feature- Load leprechaun image
		try {// try catch because image is a volatile // 
			Image leprechaunImg = new Image("file:Assets/leprechaun.png");
			leprechaunView = new ImageView(leprechaunImg);
			//  Resize
			leprechaunView.setFitWidth(500); //to appropriate size
			leprechaunView.setPreserveRatio(true); // preserving ratio - user responsive


			// Position the leprechaun image off-screen initially
			leprechaunView.setTranslateX(-500); // out off site 
			leprechaunView.setTranslateY(200); // left corner
		} catch (Exception e) {
			System.err.println("Error loading leprechaun image!");
			e.printStackTrace(); // custom error message and print stack trace if image isnt there in the file 
		}
	}



	// creating an array list 
	private List<String> predictions1 = new ArrayList<>();
	//read a file, populate listview with names 

	private void readMagicAnswers (String magicAnswers) {
		try {// try catch for volatile file element
			//Open file for reading 
			BufferedReader buf = new BufferedReader(new FileReader(magicAnswers));
			String line=""; //variable to hold each line of the file

			// Read each line from the file- until the end of file
			while ((line = buf.readLine()) != null) {
				line = line.trim(); // Trim to remove leading/trailing whitespace
				if (!line.isEmpty()) { // Ignore empty lines
					predictions1.add(line); // Add the prediction to the list
				}
			}
		} catch (FileNotFoundException e) { //Handle missing file 
			System.err.println("Error: File not found - " + magicAnswers);
			//Log error message 
			e.printStackTrace(); // print stack trace for debugging
		} catch (IOException e) {// Handle general I/O errors 
			System.err.println("Error reading file - " + magicAnswers);
			//print custom error message and print stack trace for debugging
			e.printStackTrace();
		}
	}
	// extra feature - list to store irish predictions
	private List<String> irishPredictions = new ArrayList<>();
	//Method to read Gaelic themed predictions from irishAnswers.csv file
	private void readIrishAnswers(String irishAnswersFile) {
		//Open file for reading
		try (BufferedReader reader = new BufferedReader(new FileReader(irishAnswersFile))) {
			String line;
			// read each line until the end of file 
			while ((line = reader.readLine()) != null) {
				// ignore empty lines 
				if (!line.isEmpty()) {
					irishPredictions.add(line); // Add the prediction to the list
				}
			}
		} catch (FileNotFoundException e) {// handle missing files
			// custom message for error and prints stack trace for debugging
			System.err.println("Error: File not found - " + irishAnswersFile);
			e.printStackTrace(); 
		} catch (IOException e) { //handle general I/O errors 
			System.err.println("Error reading file - " + irishAnswersFile);
			e.printStackTrace();
		}
	}
	//Event Handling
	@Override
	public void init() {
		//handle events on the components of the UI

		//Event handler for menu item filechoose being clicked 
		miFileChange.setOnAction(event ->{
			System.out.println("miFileChange was clicked!"); // prints to console when miFile was clicked 
			//show a system dialogue to choose a new image
			FileChooser fc = new FileChooser(); // create file chooser dialogue
			fc.setTitle("Change your magic 8 ball image");// set dialogue title
			//set initial directory to Assets - volatile component
			// Set the initial directory to "Assets/"
			try {
				// Attempt to set the initial directory to "Assets/"
				File assetsDir = new File("Assets/");
				if (assetsDir.exists()) {
					fc.setInitialDirectory(assetsDir);
					//set initial directory if exists
				} else {// log error if folder missing 
					System.err.println("Assets folder does not exist!");
				}
			} catch (Exception ex) {
				// Handle unexpected errors (e.g. file system access issues)
				System.err.println("An error occurred while setting the initial directory: " + ex.getMessage());
				ex.printStackTrace(); // print stack trace
			}
			//filter out only image files ( .png)
			ExtensionFilter onlyImages = new ExtensionFilter("PNG  images","*.png");

			//add filter to file chooser 
			fc.getExtensionFilters().add(onlyImages);

			//show dialog and get the chosen file 
			File chosenFile = fc.showOpenDialog(null);

			//make sure the file isnt null 
			if(chosenFile !=null) {


				//show the image in the UI (try/catch!)

				try { // load selected image and update the imageView
					img = new Image (chosenFile.toURI().toURL().toString());
					imv.setImage(img); // we dont create a new image , it just update the image 
				}

				catch  (Exception oopsie) { // print error message and stack trace
					System.out.println("Error reading user selected image.");
					oopsie.printStackTrace();

				}

			}
		});


		// Event handler for "About" MenuItem
		miAboutHelp.setOnAction(evt -> {
			showDialog(); // Call the showDialog() method
		});




		// Event handler for clicking the Magic 8 Ball image
		imv.setOnMouseClicked(event -> {
			//check if prediction list is not empty 
			if (!predictions1.isEmpty()) {
				//generate random index 
				int randomIndex = (int) (Math.random() * predictions1.size());
				//get random prediction
				String randomPrediction = predictions1.get(randomIndex);
				//Display prediction in answer label 
				answerLabel.setText(randomPrediction);
				// Hide the promptLabel after displaying prediction
				promptLabel.setVisible(false); } 
			else { // error handling of empty prediction list 
				answerLabel.setText("No predictions available.");
			}
			shakeImage(imv); // shake method call , to shake image frame

			//if the feeling luck button was clicked only then remove the effects 
			// Check if current image is the Irish-themed ball before resetting
			// so that in case the user chose their picture it should not change
			if (imv.getImage() != null && imv.getImage().getUrl() != null &&
					imv.getImage().getUrl().contains("irishBall.png")) {

				// extra feature - css glow button styling - remove
				btnFeelingLucky.getStyleClass().remove("button-feeling-lucky-glow");
				// Change  the background color to the default
				bpMain.setStyle("-fx-background-color: #168178;"); 

				// Change the image to default

				try {
					imv.setImage(new Image("file:Assets/magic8ball.png")); // Load the new image
				} catch (Exception e) {
					System.err.println("Error loading image: " + e.getMessage());
				}} // error handling , print stack trace and custom message 
		});

		// Extra feature- Feeling luck button , will have a click counter of 5
		btnFeelingLucky.setOnAction(event -> {
			luckyClickCount++;
			if (luckyClickCount == 5) { // when the click = 5 , easter egg generated 
				// answer gives custom message 
				answerLabel.setText("You have caught a sneaky Leprechaun!");
				// leprechaun peek method is called and leprechaun comes into view 
				peekLeprechaun(leprechaunView);
				luckyClickCount = 0; // Reset the click count
			} else { 
				// if prediction list is not empty 
				if (!irishPredictions.isEmpty()) {
					// generate random index of the array 
					int randomIndex = (int) (Math.random() * irishPredictions.size());
					// the string will store that prediction of the generated index number
					String randomIrishPrediction = irishPredictions.get(randomIndex);

					// Display the random generated Irish prediction in the answerLabel
					answerLabel.setText(randomIrishPrediction);

					// Hide the promptLabel
					promptLabel.setVisible(false);
				} else { // if prediction list empty then the answer label shows this message
					answerLabel.setText("No Irish predictions available.");
				}

				// extra feature-glow effect by adding the CSS class
				btnFeelingLucky.getStyleClass().add("button-feeling-lucky-glow");
				// Change the image to the Irish-themed ball
				try {
					imv.setImage(new Image("file:Assets/irishBall.png")); // Load the new image
				} catch (Exception e) {
					// error handling because of volatile image element, 
					System.err.println("Error loading image: " + e.getMessage());
				} //custom message generated 
				// Change the background color to complement green
				bpMain.setStyle("-fx-background-color: 	#381819;"); 
				shakeImage(imv); // shake method to the irish magic 8 ball image frame
			}
		});
	}

	// Show the leprechaun animation method
	private void peekLeprechaun(ImageView leprechaunView) {
		// create slide in animation for image
		TranslateTransition slideIn = new TranslateTransition(Duration.seconds(1), leprechaunView);
		slideIn.setToX(20); // Slide in from the left 20px
		// create pause after the slid in animation for 1 second
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		// slide out to off screen -500 px to left 
		TranslateTransition slideOut = new TranslateTransition(Duration.seconds(1), leprechaunView);
		slideOut.setToX(-500); // Slide out to the left
		// combination of animations into a sequential transition: slide in pause , slide out
		SequentialTransition peek = new SequentialTransition(slideIn, pause, slideOut);
		peek.play();// play the combined animation
	}

	//Alert 
	//shows a built-in dialog (Alert)
	private void showDialog() {
		//use an alert for this dialog type INFORMATION
		Alert myAlert = new Alert(AlertType.INFORMATION);

		//set the Title of alert dialog 
		myAlert.setTitle("About Magic 8 Ball:Key Information");

		//customize header and content
		myAlert.setHeaderText("				         Application Name: Magic8Ball\n"
				+ "				         Student Name: Anriel Almeida\n"
				+ "				         Student Number: 3168178\n"
				+ "				         Programme of Study: Higher Diploma in Computing\n"
				+"				         Favorite Gestalt Principle: Law of similarity\n"
				+ "\n"); // set content
		myAlert.setContentText("		Explanation: I choose this principle because:\n "
				+"		The users can intutively figure out that similar stylings\n"
				+ "		represent similar objects like menu buttons and will perform similar functions\n"
				+ "		As due to similar shape and colour you can make a user to perceive items as\n"
				+ "		belonging to a group\n"
				);

		//load and set the icon of the Alert
		//Inside Assets folder 
		// Load the icon image with error handling
		ImageView imvIcon = null; // Declare the ImageView outside the try-catch


		//- volatile actions in try/catch !
		try { img = new Image("Assets/law-of-similarity.png"); //load image
		imvIcon = new ImageView(img); // its like a frame for the img

		} catch (Exception oops) {
			System.err.println("Error loading default image!"); // custom message gives a better idea of what went wrong 
			oops.printStackTrace(); // print stack trace
		}
		// if image exists, add the styling
		imvIcon.setFitWidth(600); //width 600px
		imvIcon.setPreserveRatio(true); 
		myAlert.setGraphic(imvIcon);   	    // Set the graphic (icon) for the Alert

		//show the alert
		myAlert.showAndWait();
	}
	// extra feature - vibration feature to move ball, called in 2 methods 
	// so i made a separate method 
	private void shakeImage(ImageView imv) {
		TranslateTransition shake = new TranslateTransition(Duration.millis(100), imv);
		shake.setFromX(0); // start position 0 x axis 
		shake.setByX(10); // move by 20 px
		shake.setCycleCount(6);// repeat 6 times 
		shake.setAutoReverse(true); // reverse direction after each cycle 
		shake.play(); // play the animation 
	}



	//Window setup and layouts
	@Override
	public void start(Stage primaryStage) throws Exception  { 
		//set the title of the window
		primaryStage.setTitle("Assignment01  - " + yourName + " - " + yourStudentNum);

		//Set default width and height for the stage (window)

		primaryStage.setWidth(600);
		primaryStage.setHeight(800);

		// Add Menus to MenuBar
		mbMain.getMenus().addAll(mnuFile, mnuAbout);

		// Add MenuItems to Menus
		mnuFile.getItems().add(miFileChange);
		mnuAbout.getItems().add(miAboutHelp);

		// Bind the width of the ImageView and preserve ratio
		//imview.fitWidthProperty used  to bind with to half the window
		imv.fitWidthProperty ().bind(primaryStage.widthProperty().divide(2));
		imv.setPreserveRatio(true); // to ensure height of image adjusts automatically to 
		//maintain original aspect ratio
		//Stack the image and answer label on top of each other
		// call the children of stack and add them to stack pane
		StackPane imageStack = new StackPane();
		imageStack.getChildren().addAll(imv, answerLabel);


		//Align the answerLabel in the center if the StackPane 
		StackPane.setAlignment(answerLabel, Pos.CENTER);

		//Create sub-layouts
		//Create a VBox to hold the MenuBar and promptLabel
		VBox vbTop = new VBox(); // For the prompt label , menubar 
		vbTop.getChildren().addAll(mbMain, promptLabel); // Add MenuBar and promptLabel
		// Styling sub layouts // Add  and spacing
		vbTop.setSpacing(90);
		vbTop.setAlignment(Pos.CENTER); // Center-align the content
		// sublayouts (s)

		//Create main layout
		bpMain = new BorderPane();  //you're initializing the instance variable


		//Add sub-layouts to main layout
		// adding menu bar and prompt to main  layout 
		bpMain.setTop(vbTop);
		bpMain.setCenter(imageStack); // Add the StackPane to the center of the BorderPane

		

		// Extra feature 
		// Style the button (optional)
		btnFeelingLucky.setStyle("-fx-base: lightgreen; -fx-font-size: 14pt;");

		// Add the button to the layout
		VBox vbBottom = new VBox(10); // Vertical box for bottom section
		vbBottom.getChildren().add(btnFeelingLucky);
		vbBottom.setAlignment(Pos.CENTER); // Center-align the button
		vbBottom.setPadding (new Insets (120)); // button padding 
		// Add the VBox to the main layout
		bpMain.setBottom(vbBottom);


		// Extra feature -Add the leprechaun image to the scene
		if (leprechaunView != null) {
			bpMain.getChildren().add(leprechaunView);
		}


		//Create the scene
		Scene s = new Scene(bpMain); //main layout

		// apply a stylesheet (global style)//  for a button use inlinestlye
		s.getStylesheets().add("Assets/m8b_style.css");

		// Set the scene 
		primaryStage.setScene(s);
		//Show the stage
		primaryStage.show();
	}

	//Launch the application
	public static void main(String[] args) {
		launch(args);
	}
}