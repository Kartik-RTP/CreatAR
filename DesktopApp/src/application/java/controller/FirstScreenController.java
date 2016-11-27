package application.java.controller;

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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



/**
 * Created by Rajat on 10/8/2016.
 */
public class FirstScreenController implements Initializable{

    @FXML
    private Label label;

    @FXML
    private Button firstScreenNewProject;

    @FXML
    private Button firstScreenOpenProject;

    @FXML
    private ListView listView;


    @FXML
    private void firstScreenNewProject(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/scene_project_specifications_dialog.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/NewProject.css").toExternalForm());
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(scene);
        app.show();
    }

    @FXML
    private void firstScreenOpenProject(ActionEvent event) throws IOException{
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
    /*
        if (selectedFile != null){
            listView.getItems().add(selectedFile.getName());
        }
        else{
            System.out.println("Invalid\n");
        }
*/
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }
}
