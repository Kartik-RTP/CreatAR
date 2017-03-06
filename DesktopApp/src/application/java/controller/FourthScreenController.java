package application.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Prateek Raina on 28-01-2017.
 */
public class FourthScreenController implements Initializable {

    @FXML
    javafx.scene.control.Button canc_Button;

    @FXML
    ProgressBar buildProgressBar;

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("FourthScreen.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


    @FXML
    private void canc_Button(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/simple_template_project_create.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/create.css").toExternalForm());
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(scene);
        app.show();
    }


//    public static void main(String[] args) {
//        launch(args);
//    }

}
