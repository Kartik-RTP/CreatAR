
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


public class NewProjectController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private javafx.scene.control.Button newProjectCancel;

    @FXML
    private void newProjectBack(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("FirstScreen.fxml"));
        Scene scene = new Scene(root);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(scene);
        app.show();
    }


    @FXML
    private void newProjectCancel(ActionEvent event) throws IOException{
        Stage stage = (Stage) newProjectCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

}
