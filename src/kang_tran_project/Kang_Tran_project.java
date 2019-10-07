package kang_tran_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.DatePicker;
import java.io.*;
import java.util.ArrayList;

/*
 * Runs the main class
 * Authors: Ray & Thanh
 */
/**
 * main class extends application
 *
 * @author Ray Kang, Thanh Tran
 */
public class Kang_Tran_project extends Application {

    Stage window;   // models the window of the application
    VBox root;      // vbox layout
    Scene home;     // scene for the home window   
    Scene appointment;  // scene for the appointment window
    Scene register;     // scene for the register window
    Button home_button; // models the home button 
    Button register_button; // models the register button
    Button appointment_button;  // models the appointment button
    HBox hbTop, hbMid, hbBot;   //  hBox layout
    StackPane stack1, stack2, stack3;   // stackpanes
    Text banner;    // application banner
    Image welcome;  // image of banner
    Pet pet;
    Vet vet;

    // set pref. size of label to 100
    private final double LABEL_PREF_SIZE = 100.0;

    /**
     * start method that runs the stage
     *
     * @author Thanh Tran
     */
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;  // set primary stage to window
        hbTop = new HBox();
        hbTop.setAlignment(Pos.CENTER);
        hbTop.setPrefSize(1000, 100);
        banner = new Text("Welcome to Pet Med-cord");   // display welcome message
        banner.setFont(Font.font("Impact", 45));
        banner.setFill(Color.BLUE);
        hbTop.getChildren().add(banner);    // add banner to the hBox

        hbMid = new HBox();
        hbMid.setPrefSize(1000, 400);
        welcome = new Image("pet.jpg");
        hbMid.getChildren().add(new ImageView(welcome));    //display the image

        hbBot = new HBox();
        hbBot.setPrefSize(1000, 100);

        stack1 = new StackPane();
        stack1.setPrefSize(333, 100);

        stack2 = new StackPane();
        stack2.setPrefSize(333, 100);

        stack3 = new StackPane();
        stack3.setPrefSize(333, 100);

        //home
        home_button = new Button("Home");
        home_button.setPrefSize(200, 50);
        home_button.setOnAction(event -> showHome());

        //register 
        register_button = new Button("Register");
        register_button.setPrefSize(200, 50);
        register_button.setOnAction(event -> showRegister());

        //appointment 
        appointment_button = new Button("Book an Appointment");
        appointment_button.setPrefSize(200, 50);
        appointment_button.setOnAction(event -> showAppointment());

        // add buttons to stack pane
        stack1.getChildren().add(home_button);
        stack2.getChildren().add(register_button);
        stack3.getChildren().add(appointment_button);

        //horizontally align stackpanes
        hbBot.setPadding(new Insets(15));
        hbBot.getChildren().addAll(stack1, stack2, stack3);

        // vertically align the welcome message, the image and the buttons
        root = new VBox();
        root.getChildren().add(hbTop);
        root.getChildren().add(hbMid);
        root.getChildren().add(hbBot);

        home = new Scene(root, 1000, 600);

        primaryStage.setTitle("Pet Medcord");   // set title
        primaryStage.setScene(home);    // set scene
        primaryStage.show();    // show the stage
    }

    /**
     * launches the application
     *
     * @param args command line argument(s)
     * @author Thanh Tran
     */
    public static void main(String[] args) {
        launch(args);   // launch application
    }
    
    /**
     * show home screen
     * @author Thanh Tran
     */
    private void showHome() {
        banner.setText("Welcome to Pet Med-cord");
        hbMid.getChildren().clear();    // clear the previous screen
        hbMid.getChildren().add(new ImageView(welcome)); // re-display the image
    }

    /**
     * show registration screen
     * @author Thanh Tran
     */
    private void showRegister() {
        banner.setText("Registration");
        hbMid.getChildren().clear();    // clear the previous screen
        hbMid.setSpacing(50);   // set a spacing of 50

        //set array of fields, labels and text fields
        String[] fields = {"Pet Name: ", "Pet Type: ", "Breed: ", "Age: ", "Weight(kg): "};
        HBox[] hb = new HBox[fields.length];
        Label[] lb = new Label[fields.length];
        TextField[] tf = new TextField[fields.length];
        VBox vb_register = new VBox(15);
        vb_register.setPadding(new Insets(30));
        vb_register.setPrefWidth(670);
        // create labels for each fields and format each textfield and label
        for (int i = 0; i < fields.length; i++) {
            lb[i] = new Label(fields[i]);
            lb[i].setMinWidth(LABEL_PREF_SIZE);
            tf[i] = new TextField();
            hb[i] = new HBox(30);
            hb[i].getChildren().addAll(lb[i], tf[i]);
            vb_register.getChildren().add(hb[i]);
        }

        HBox hb_neuter = new HBox(30);
        Label lb_neuter = new Label("Neutered: ");  // create a label for neutered
        RadioButton rb_neutered = new RadioButton("Yes");   // set radio button for yes
        RadioButton rb_not_neutered = new RadioButton("No"); // set radio button for no
        ToggleGroup neuter_group = new ToggleGroup();   // create a toggle group
        rb_neutered.setToggleGroup(neuter_group);   // set "Yes" to toggle group
        rb_not_neutered.setToggleGroup(neuter_group);   // set "No" to toggle group
        rb_neutered.setSelected(true);  // set "Yes" as the default selection
        lb_neuter.setMinWidth(LABEL_PREF_SIZE); // set the min label size to 100
        // add label and radio buttons to the HBox for horizontal alignment
        hb_neuter.getChildren().addAll(lb_neuter, rb_neutered, rb_not_neutered);

        // textbox for extra information to be included
        HBox hb_other = new HBox(85);
        Label lb_other = new Label("Other: ");  // create label other
        TextArea ta_other = new TextArea(); // create a textbox
        ta_other.setPrefColumnCount(30);    // set the preferred width for the text box
        ta_other.setPrefRowCount(50); // set the preferred height of the text box
        // add label and textbox to the HBox to align horizontally
        hb_other.getChildren().addAll(lb_other, ta_other);

        //set neuter option and other textbox to a vertical alignment
        vb_register.getChildren().addAll(hb_neuter, hb_other);

        VBox vb_registerbuttons = new VBox(50);
        //set alignment of save button and clear button
        vb_registerbuttons.setAlignment(Pos.CENTER_LEFT);
        //set the preferred width of register button
        vb_registerbuttons.setPrefWidth(200);
        // set the the padding of save button and clear button
        vb_registerbuttons.setPadding(new Insets(20));
        // create a button to confirm and save
        Button save_registration = new Button("Confirm and Save");
        // call an event handler
        save_registration.setOnAction(event -> {

            boolean ok = true;  // set default value of ok to true
            try {
                // fetch text and convert to integer
                int age = Integer.parseInt(tf[3].getText());
                // fetch weight and convert to double
                double weight = Double.parseDouble(tf[4].getText());
                // for loop checks if data has been entered
                for (int i = 0; i < tf.length - 1; i++) {
                    if (tf[i].getText().trim() == "") {
                        throw new Exception("Missing data");
                    }
                }
            } catch (Exception e) {
                ok = false; // set ok to false
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog"); // set Title for alert box
                alert.setHeaderText("Data Missing");    // set HeaderText for alertBox
                //set message to alert box
                alert.setContentText("Must fill in all fields and enter numbers for age and weight. Please retry.");
                Optional<ButtonType> result = alert.showAndWait();  //show alert
            }

            //fetch input from text fields including radio buttons and other text area
            if (ok) {
                String[] inputText = new String[tf.length + 2];
                for (int i = 0; i < fields.length; i++) {
                    inputText[i] = tf[i].getText();
                }

                // store the input from the radio buttons and text area into the input[] array
                // then write to file
                RadioButton selectedRadioButton = (RadioButton) neuter_group.getSelectedToggle();
                inputText[fields.length] = selectedRadioButton.getText();
                inputText[fields.length + 1] = ta_other.getText();
                writeToFile(inputText);
            }
        });

        // create clear button
        Button clear_registration = new Button("Clear Fields");
        clear_registration.setOnAction(event -> {
            for (int i = 0; i < fields.length; i++) {
                tf[i].clear();  // clear all text fields
            }
            rb_neutered.setSelected(true);  // reset radio button back to default
            ta_other.clear();   // clear textbox field
        });

        // add buttons to vbox
        vb_registerbuttons.getChildren().addAll(save_registration, clear_registration);

        // align radio buttons and other field to the confirm and save along with the clear button
        hbMid.getChildren().addAll(vb_register, vb_registerbuttons);
    }

    /**
     * A method to show the appointment screen\
     *
     * @Author: Ray Kang
     */
    private void showAppointment() {
        banner.setText("Book an Appointment");  // set text title
        hbMid.getChildren().clear();    // clear any nodes in the hbox
        hbMid.setSpacing(50);   // set spacing to 50

        //array of fields and labels 
        String[] fields = {"Pet Name ", "Pet Type ", "Breed ", "Age ", "Weight(kg) ", "Neutered ", "Other "};
        Label[] lb = new Label[fields.length];
        Label[] info = new Label[fields.length];
        HBox hb_info[] = new HBox[fields.length];
        VBox vb_info = new VBox(25);
        vb_info.setPadding(new Insets(30)); //set padding
        vb_info.setPrefWidth(700);  // set pref width
        // set format of all labels
        for (int i = 0; i < fields.length; i++) {
            lb[i] = new Label(fields[i]);
            lb[i].setMinWidth(LABEL_PREF_SIZE);
            info[i] = new Label("");
            hb_info[i] = new HBox(30);
            hb_info[i].getChildren().addAll(lb[i], info[i]);
            vb_info.getChildren().addAll(hb_info[i]);
        }

        VBox vb_button = new VBox(50);
        vb_button.setPrefWidth(200);    // set preferred width
        vb_button.setAlignment(Pos.CENTER_LEFT);
        Button load_profile = new Button("Load Profile");
        load_profile.setOnAction(event -> {
            String[] loaded_info = new String[7]; // put input into a string
            loaded_info = readFromFile();   // read from file
            for (int i = 0; i < loaded_info.length; i++) {
                info[i].setText(loaded_info[i]);
            }
        });

        // book an appointment
        Button booking = new Button("Book Appointment");    // book appointment button
        booking.setOnAction(event -> {
            boolean ok = true;  // set ok to true as the default value
            String missing = "";    // declare a missing field
            for (int i = 0; i < info.length - 1; i++) {
                // check if data are filled
                if (info[i].getText().trim().equals("")) {
                    ok = false;
                    missing += fields[i] + " ";
                }
            }

            if (ok) {
                String name = info[0].getText();    // fetch name
                String type = info[1].getText();    // fetch type
                String breed = info[2].getText();   // fetch breed
                int age = Integer.parseInt(info[3].getText());  // fetch age and convert
                double weight = Double.parseDouble(info[4].getText());  // fetch weight and convert
                boolean neuter;
                // get answer if pet is neutered or not and set the value to either true or false
                if (info[5].getText() == "Yes") {
                    neuter = true;
                } else {
                    neuter = false;
                }
                String other = info[6].getText();   //fetch any additional data entered
                pet = new Pet(name, type, breed, age, weight, neuter, other);
                openBooking();  // call method 
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog"); // set title of alert
                alert.setHeaderText("Data Missing");    // set header text of alert
                alert.setContentText(missing + " Data Missing.");   // set alert message
                Optional<ButtonType> result = alert.showAndWait();  // show alert
            }
        });

        vb_button.getChildren().addAll(load_profile, booking);
        hbMid.getChildren().addAll(vb_info, vb_button);

    }

    /**
     * A method to write to a file
     *
     * @param input user input being written to the file
     * @Author Thanh Tran
     */
    private void writeToFile(String input[]) {

        //create an instance of file chooser
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save File Chooser");  // set title
        //filter files to be written to
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        // open file explorer and save to a directory
        File file = chooser.showSaveDialog(window);

        if (file == null) {
            return;
        }   // write to file
        try (FileWriter fileWriter = new FileWriter(file, false);
                PrintWriter writer = new PrintWriter(fileWriter)) {
            for (int i = 0; i < input.length; i++) {
                writer.print(input[i] + "\n");
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * A method that reads from a file
     *
     * @return output data read from a file
     * @Author: Ray Kang
     */
    public String[] readFromFile() {
        String[] output = new String[7];    // string output
        // create an instance of file chooser
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File Chooser");  // set title
        //set to a working directory
        File file = new File(".");
        chooser.setInitialDirectory(file);
        // add extension filters
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        // select a file
        File selectedFile = chooser.showOpenDialog(window);

        // if a file is selected and is found do the following
        if (selectedFile != null) {
            try {
                Scanner input = new Scanner(selectedFile);
                StringBuilder builder = new StringBuilder();
                int i = 0;
                //checks if file has a next line then set to output
                while (input.hasNextLine()) {
                    output[i] = input.nextLine();
                    i++;
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex.toString());
            }
        }
        return output;
    }

    /**
     * A method for booking appointments
     *
     * @Author: Ray Kang
     */
    public void openBooking() {

        //window for booking appointments
        Label lbVet = new Label("Vet: ");   // create a label called vet
        ComboBox<String> cmbVet = new ComboBox<>(); // create a combo box
        String[] vetNames = {"Ray", "Thanh", "Paul"};  // an array of vetNames
        ObservableList vetList = FXCollections.observableArrayList(vetNames);
        cmbVet.setItems(vetList);   // set vetList to the comboBox
        cmbVet.getSelectionModel().select(0);   //set the first element as the default on the drop down 
        HBox hbVet = new HBox(30);
        hbVet.getChildren().addAll(lbVet, cmbVet);  // add label and combo box to hbox

        Label lbDate = new Label("Date: "); // create a label called date
        DatePicker bookingDatePicker = new DatePicker();   //create an instance of DatePicker 
        HBox hbDate = new HBox(30);
        hbDate.getChildren().addAll(lbDate, bookingDatePicker); // add label and datepicker to hbox

        Label lbTime = new Label("Time: "); // create a label called Time
        ComboBox<String> cmbTime = new ComboBox<>();    // combo box
        // array of times available
        String[] time = {"9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
        ObservableList timeList = FXCollections.observableArrayList(time);
        cmbTime.setItems(timeList); // set timeList to comboBox
        cmbTime.getSelectionModel().select(0);  // set first element as the default
        HBox hbTime = new HBox(30);
        hbTime.getChildren().addAll(lbTime, cmbTime);   // set label and comboBox to hBox

        VBox vbBookInfo = new VBox(30);
        vbBookInfo.setPrefHeight(500);  // set a preferred height
        vbBookInfo.setPadding(new Insets(20));  // set padding
        vbBookInfo.getChildren().addAll(hbVet, hbDate, hbTime);

        HBox hbBookButton = new HBox(30);
        hbBookButton.setAlignment(Pos.CENTER);// set alignment
        hbBookButton.setPadding(new Insets(20)); // set padding

        //Cancel Button
        Button buttonCancel = new Button("Cancel");
        buttonCancel.setOnAction(event -> {
            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();  // closes stage
        });

        //Create confirm button
        Button buttonConfirm = new Button("Confirm");
        buttonConfirm.setOnAction(event -> {
            boolean ok = true;
            String output = "";
            // if bookingDatePicker has not been picked send an alert to the user
            if (bookingDatePicker.getValue() == null) {
                ok = false;
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog"); // set title
                alert.setHeaderText("Please pick a date");  // set header text
                alert.setContentText("Please select a date.");  // set message
                Optional<ButtonType> result = alert.showAndWait();  // show alert
            }
            //read the correct pet file
            if (ok) {
                String vetName = cmbVet.getValue(); // get the vet that was chosen
                File file;
                if (vetName.equals("Ray")) {
                    file = new File("src/PetMedcord/VetProfile/Ray.txt");   // write to this txtfile if chosen
                } else if (vetName.equals("Thanh")) {
                    file = new File("src/PetMedcord/VetProfile/Thanh.txt"); // write to this textfile if chosen
                } else {
                    file = new File("src/PetMedcord/VetProfile/Paul.txt"); // write to this textfile if chosen
                }

                // read file and output to console
                try {
                    Scanner input = new Scanner(file);
                    vet = new Vet(vetName);
                    String date = bookingDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Appointment newAppointment = new Appointment(date, cmbTime.getValue(), pet.getName());
                    //if the file is empty write the new appointment info into the file
                    if (input.hasNextLine() == false) {
                        output += (newAppointment);
                        try (
                                FileWriter fileWriter = new FileWriter(file, true);
                                PrintWriter writer = new PrintWriter(file)) {
                            writer.print(output);
                        } catch (FileNotFoundException ex) {
                            System.out.println("File not found.");
                        } catch (IOException ex) {
                            System.out.println("IO Exception.");
                        }
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Appointment Dialog");   // set title
                        alert.setHeaderText("Booking Success"); // set header text
                        alert.setContentText("You appointment for " + newAppointment.getPet() + " is booked on " + newAppointment.getDate() + " at " + newAppointment.getTime());
                        Optional<ButtonType> result = alert.showAndWait();
                    } else {
                        //if there are existing appointments in the file, compare the new appointment with all the existing appointments in the file
                        boolean booked = false;
                        while (input.hasNextLine() && !booked) {
                            String lineIn = input.nextLine();
                            Appointment existAppointment = new Appointment(lineIn);
                            vet.getAppointment().add(existAppointment);
                            //if there is equivalent appointment in the file, show an error dialog
                            if (newAppointment.equals(existAppointment)) {
                                Alert alert = new Alert(AlertType.ERROR);
                                alert.setTitle("Error Dialog"); // set title
                                alert.setHeaderText(vet.getName() + " is not available on " + newAppointment.getDate() + " at " + newAppointment.getTime());
                                alert.setContentText("Please choose another time slot.");
                                Optional<ButtonType> result = alert.showAndWait();
                                vet.getAppointment().clear();
                                booked = true;
                            }
                        }
                        //add the new appointment into vet's appointment arraylist then do sort(). Once sorting is done, write the sorted arraylist of appointments to the file
                        if (!booked) {
                            vet.getAppointment().add(newAppointment);
                            Collections.sort(vet.getAppointment());
                            for (int i = 0; i < vet.getAppointment().size(); i++) {
                                output += vet.getAppointment().get(i) + "\n";
                            }
                            try (
                                    FileWriter fileWriter = new FileWriter(file, true);
                                    PrintWriter writer = new PrintWriter(file)) {
                                writer.print(output);
                            } catch (FileNotFoundException ex) {
                                System.out.println("File not found.");
                            } catch (IOException ex) {
                                System.out.println("IO Exception.");
                            }
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Appointment Dialog");
                            alert.setHeaderText("Booking Success");
                            alert.setContentText("You appointment for " + newAppointment.getPet() + " is booked on " + newAppointment.getDate() + " at " + newAppointment.getTime());
                            Optional<ButtonType> result = alert.showAndWait();
                        }
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found.");
                }
            }
        });

        //View Schedule Button
        Button buttonSchedule = new Button("Vet Schedule");
        buttonSchedule.setOnAction(event -> {
            VBox vb_schedule = new VBox();
            String vetName = cmbVet.getValue();
            Label lb_schedule = new Label("");

            //load the correct vet file
            File file;
            if (vetName == "Ray") {
                file = new File("src/PetMedcord/VetProfile/Ray.txt");
            } else if (vetName == "Thanh") {
                file = new File("src/PetMedcord/VetProfile/Thanh.txt");
            } else {
                file = new File("src/PetMedcord/VetProfile/Paul.txt");
            }
            try {
                Scanner input = new Scanner(file);
                String output = "";
                while (input.hasNextLine()) {
                    output += input.nextLine();
                    output += "\n";
                }
                lb_schedule.setText(output);
            } catch (FileNotFoundException ex) {
                System.out.println("File Not Found");
            }

            vb_schedule.getChildren().addAll(lb_schedule);
            Scene scheduleScene = new Scene(vb_schedule, 400, 300);
            Stage scheduleWindow = new Stage();
            scheduleWindow.setTitle("Book an Appointment");
            scheduleWindow.setScene(scheduleScene);
            scheduleWindow.setX(window.getX() + 600);
            scheduleWindow.setY(window.getY() + 100);
            scheduleWindow.show();
        });

        hbBookButton.getChildren().addAll(buttonCancel, buttonConfirm, buttonSchedule);

        VBox vb_book = new VBox(20);
        vb_book.setPadding(new Insets(20));
        vb_book.getChildren().addAll(vbBookInfo, hbBookButton);

        Scene bookingScene = new Scene(vb_book, 400, 300);
        Stage bookingWindow = new Stage();
        bookingWindow.setTitle("Book an Appointment");
        bookingWindow.setScene(bookingScene);
        bookingWindow.setX(window.getX() + 200);
        bookingWindow.setY(window.getY() + 100);
        bookingWindow.show();
    }
}
