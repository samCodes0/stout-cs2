package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Sam Doyle
 * Date: 04/27/2026
 * Description: A graphical application for entering some information about a student, and saving it to a file 
 */
public class Main extends Application {
	private static final int SCENE_WIDTH = 370; // the width of the window
	private static final int SCENE_HEIGHT = 170; // height of the window

	// left column components
	private Label studentNameLabel;
	private Label studentEmailLabel;
	private Label studentNationalityLabel;
	private RadioButton undergraduateButton;
	private CheckBox transferCheckBox;
	private Button resetButton;

	// right column components
	private TextField studentNameTextField;
	private TextField studentEmailTextField;
	private ComboBox<String> studentNationalityComboBox;
	private RadioButton graduateButton;
	private Button saveButton;

	final String RECORDS_FILENAME = "records.txt";  // name of the file to save student info too
	private final Alert EMPTY_BOX_WARNING = new Alert(AlertType.WARNING, "student name or email cannot be empty!",
			ButtonType.OK);
	private final Alert WRITE_ALERT = new Alert(AlertType.INFORMATION, "wrote student information to the file " + RECORDS_FILENAME, ButtonType.OK);

	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

			// create the components and add them to the root
			// initialize the left column
			studentNameLabel = new Label("Student Full Name: ");
			studentEmailLabel = new Label("Student Email Address: ");
			studentNationalityLabel = new Label("Student Nationality: ");
			undergraduateButton = new RadioButton("Undergraduate");
			transferCheckBox = new CheckBox("Transferred Student");
			resetButton = new Button("Reset");
			// add left column components to the root
			root.addColumn(0, studentNameLabel, studentEmailLabel, studentNationalityLabel, undergraduateButton,
					transferCheckBox, resetButton);

			// initialize the right column
			studentNameTextField = new TextField();
			studentEmailTextField = new TextField();
			studentNationalityComboBox = new ComboBox<String>();
			graduateButton = new RadioButton("Graduate");
			Label space = new Label(); // empty label for space between the graduateButton and saveButton
			saveButton = new Button("Save");
			// set the prompt text for the name and email text fields
			studentNameTextField.setPromptText("Student Full Name");
			studentEmailTextField.setPromptText("Student Email");
			// fill the student nationality combobox with the nationalities from the file
			fillComboBoxFromFile(studentNationalityComboBox, "nations-1.txt");
			// add the right column to the root
			root.addColumn(1, studentNameTextField, studentEmailTextField, studentNationalityComboBox, graduateButton,
					space, saveButton);

			// now set up a ToggleGroup for the radio buttons
			ToggleGroup tg = new ToggleGroup();
			tg.getToggles().addAll(undergraduateButton, graduateButton);

			// reset components to their defaults
			reset();

			// now set up the functionality
			resetButton.setOnAction(e -> {
				reset();
			});

			saveButton.setOnAction(e -> {
				// before saving, make sure the student name and email textboxes are not empty:
				if (studentNameTextField.getText().equals("") || studentEmailTextField.getText().equals("")) {
					EMPTY_BOX_WARNING.show();
					return;
				}
				try {
					saveToFile(RECORDS_FILENAME);
					WRITE_ALERT.show();
				} catch (IOException exc) {
					exc.printStackTrace();
				}
			});

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * reset all components to their default values
	 */
	private void reset() {
		transferCheckBox.setSelected(false);
		undergraduateButton.setSelected(true);
		studentNationalityComboBox.getSelectionModel().selectFirst();
		// reset the text fields to have no text in them
		studentNameTextField.setText("");
		studentEmailTextField.setText("");
	}

	private void saveToFile(String fileName) throws IOException {
		File file = new File(fileName);
		FileWriter writer = new FileWriter(file, true);
		writer.write(studentNameTextField.getText() + "\n");
		writer.write(studentEmailTextField.getText() + "\n");
		writer.write(studentNationalityComboBox.getValue() + "\n");
		if (graduateButton.isSelected()) {
			writer.write("Graduate \n");
		} else if (undergraduateButton.isSelected()) {
			writer.write("Undergraduate \n");
		}
		if (transferCheckBox.isSelected()) {
			writer.write("1\n");
		} else {
			writer.write("0\n");
		}
		writer.close();
	}

	/**
	 * Fills the combo box with string entries for each line in the file created
	 * from the filepath
	 * 
	 * @param comboBox the combobox to fill
	 * @param filePath the path to the file containing the lines to fill the
	 *                 combobox with
	 * @throws FileNotFoundException
	 */
	private void fillComboBoxFromFile(ComboBox<String> comboBox, String filePath) throws FileNotFoundException {
		Scanner fileIn = new Scanner(new File(filePath));
		while (fileIn.hasNext()) {
			String currentLine = fileIn.nextLine();
			comboBox.getItems().add(currentLine);
		}
		fileIn.close();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
