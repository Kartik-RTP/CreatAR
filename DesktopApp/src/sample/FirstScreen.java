package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class FirstScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FirstScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("New Project");

        primaryStage.setScene(scene);

        primaryStage.show();
    }



}
