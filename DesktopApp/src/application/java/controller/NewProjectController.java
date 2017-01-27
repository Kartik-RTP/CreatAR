
package application.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;



/**
 * Created by Rajat on 10/8/2016.
 */


public class NewProjectController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Button newProjectBack;

    @FXML
    private Button newProjectNext;


    @FXML
    private ComboBox newProjectSelectTemplate;

    @FXML
    private ComboBox newProjectSelectSdk;




    // What happens when you click on Back button
    @FXML
    private void newProjectBack(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/scene_main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/FirstScreen.css").toExternalForm());
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(scene);
        app.show();
    }

    // What happens when you click on Next button
    @FXML
    private void newProjectNext(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/simple_template_project_create.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/create.css").toExternalForm());
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(scene);
        app.show();
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
