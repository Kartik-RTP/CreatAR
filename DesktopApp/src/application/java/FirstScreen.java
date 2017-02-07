package application.java;

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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/scene_main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/FirstScreen.css").toExternalForm());
        primaryStage.setTitle("CreatAR");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        primaryStage.show();
    }



}
