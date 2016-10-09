package sample;/**
 * Created by Rajat on 10/8/2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NewProject extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("NewProject.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("NewProject.css").toExternalForm());
        primaryStage.setTitle("New Project");

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
