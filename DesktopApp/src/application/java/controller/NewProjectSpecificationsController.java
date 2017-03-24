
package application.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;



/**
 * Created by Rajat on 10/8/2016.
 */


public class NewProjectSpecificationsController implements Initializable {

    private static final String TAG = NewProjectSpecificationsController.class.getSimpleName();


    @FXML
    private Label label;

    @FXML
    private Button newProjectBack;

    @FXML
    private Button newProjectNext;

    @FXML
    private TextField projectTitleTextField;


    @FXML
    private ComboBox newProjectSelectTemplate;

    @FXML
    private ComboBox newProjectSelectSdk;

    @FXML
    private TextField projectLocationTextField;

    @FXML Button browseDirectoryButton;



    // What happens when you click on Back button
    @FXML
    private void newProjectBack(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/scene_main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/FirstScreen.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    // What happens when you click on Next button
    @FXML
    private void newProjectNext(ActionEvent event) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/simple_template_project_create.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/create.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        ////////// this method needs to be called before the show method
        setProjectLocation(fxmlLoader);
        setProjectTitle(fxmlLoader);
        ///////////

        stage.show();
    }

    private void setProjectTitle(FXMLLoader fxmlLoader) {
        //Get access to the next screen controller and set the project location attribute of it
        String projectTitle = projectTitleTextField.getText();
        System.out.println(TAG+":"+projectTitle);

        SimpleTemplateController controller = fxmlLoader.<SimpleTemplateController>getController();
        controller.setProjectTitle(projectTitle);

    }

    @FXML
    private void browseDirectory(ActionEvent event) throws IOException{
        //allows one to choose project workspace
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Project Location");
        //Show open file dialog
        File file = directoryChooser.showDialog(null);
        if(file!=null){
            projectLocationTextField.setText(file.getAbsolutePath());
        }

    }

    private void setProjectLocation(FXMLLoader fxmlLoader) {
    //Get access to the next screen controller and set the project location attribute of it
        String projectLocation = projectLocationTextField.getText();
        System.out.println(TAG+":"+projectLocation);

        SimpleTemplateController controller = fxmlLoader.<SimpleTemplateController>getController();
        controller.setProjectLocation(projectLocation);


    }

    // This is the combo box item list for the Select Template box
    ObservableList<String> selectTemplateOptions = FXCollections.observableArrayList(
            "Simple (Recommended)",
            "Advanced"
    );

    ObservableList<String> selectSdkOptions = FXCollections.observableArrayList(
            "Vuforia"
    );


    /*
    // What happens when you click on Back button
    @FXML
    private void newProjectCancel(ActionEvent event) throws IOException{
        Stage stage = (Stage) newProjectCancel.getScene().getWindow();
        stage.close();
    }
    */



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
        newProjectSelectTemplate.setItems(selectTemplateOptions);
        newProjectSelectSdk.setItems(selectSdkOptions);


    }

}
