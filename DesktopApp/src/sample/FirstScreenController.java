package sample;

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
import javafx.scene.control.Label;
import javafx.stage.Stage;



/**
 * Created by Rajat on 10/8/2016.
 */
public class FirstScreenController implements Initializable{

    @FXML
    private Label label;

    @FXML
    private void firstScreenNewProject(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("NewProject.fxml"));
        Scene scene = new Scene(root);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(scene);
        app.show();
    }

    @FXML
    private void firstScreenOpenProject(ActionEvent event) throws IOException{

        /* The Action for the Open project button in the First Screen */
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }
}
