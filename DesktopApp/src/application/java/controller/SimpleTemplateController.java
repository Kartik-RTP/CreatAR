package application.java.controller;

import application.java.model.BasicInformation;
import application.java.model.BasicMarker;
import application.java.model.BasicInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by canopy on 24-10-2016.
 */
public class SimpleTemplateController  implements Initializable{

    private  BasicMarker mCurrentActiveMarker;
    private  int mCurrentActiveMarkerIndex=0;

    @FXML
    ImageView markerImageView;
    @FXML
    Text markerNameText;
    @FXML
    BorderPane markerBorderPane;
    @FXML
    Button addInformationButton;
    @FXML
    Button doneChangesToInformationButton;

    @FXML
    Button gotoPreviousMarkerButton;

    @FXML
    Button gotoNextMarkerButton;

    @FXML
    ListView informationListView;

    @FXML
    Button simpleProjectBuildButton;





    private List<BasicMarker> mListOfMarkers;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //this method is called when all fx:id nodes are available ..
        //I am going to set up the list view at this point
        if(mListOfMarkers==null){mListOfMarkers=new ArrayList<BasicMarker>();}

    }


    public void addMarker(ActionEvent actionEvent) {

        if(mListOfMarkers==null){mListOfMarkers=new ArrayList<BasicMarker>();System.out.println("got called here");}//for first call
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Marker Images");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        System.out.print("Got a hit");
        //TODO:change the actual paramter passed as the primaryStage
        List<File> selectedFiles= fileChooser.showOpenMultipleDialog( new Popup());//instead of new stage or popup , I need to give it primary stage
        //otherwise the control on previous windows is not locked
        if(selectedFiles!=null){
            for(File file : selectedFiles){mListOfMarkers.add(new BasicMarker(file.toURI().toString()
                                                                 ,file.getName()));}
        }
        if(mListOfMarkers!=null && mListOfMarkers.size()>0){

            updateCurrentActiveMarker(mCurrentActiveMarkerIndex);

        }

    }

    private void updateCurrentActiveMarker(int index) {
            mCurrentActiveMarker = mListOfMarkers.get(index);
            mCurrentActiveMarkerIndex = index;
            updateMarkerPane();

    }

    private void updateMarkerPane() {
        if(mListOfMarkers.size()>0){
            markerBorderPane.setVisible(true); //try to change this , calling this function too many times
            //reformat below 2 lines
            gotoNextMarkerButton.setVisible(true);
            gotoPreviousMarkerButton
                    .setVisible(true);
            if (mCurrentActiveMarker != null) {
                markerImageView.setImage(new Image(mCurrentActiveMarker.getAddress()));
                markerNameText.setText(mCurrentActiveMarker.getName());
                //TODO:see if marker index needs to be updated here ..or something
                //informationListView.getItems().add(selectedFiles.get(i).getName());


            }else{
                //no marker has been added yet , so we display default stuff
                //TODO:fill this portion
                //for now I am doing nothing


            }
        }else{
            //no markers to display
            markerBorderPane.setVisible(false);
            //reformat below 2 lines\
            gotoNextMarkerButton.setVisible(false);
            gotoPreviousMarkerButton.setVisible(false);
        }
    }



    public void gotoPreviousMarker(ActionEvent actionEvent) {

      if(mCurrentActiveMarkerIndex>0){
          updateCurrentActiveMarker(mCurrentActiveMarkerIndex-1);
      }
    }


    public void gotoNextMarker(ActionEvent actionEvent) {

       if(mCurrentActiveMarkerIndex<mListOfMarkers.size()-1){
           updateCurrentActiveMarker(mCurrentActiveMarkerIndex+1);
       }
    }

    public void editInformation(ActionEvent actionEvent) {
        addInformationButton.setDisable(false);
        doneChangesToInformationButton.setDisable(false);
    }


    public void addInformation(ActionEvent actionEvent) {
           //TODO: to be filled

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Information media (images/text/video)");
        //fileChooser.setInitialDirectory();

        //TODO: decide on the filters
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Text Files","*.txt"),
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);

        if(selectedFiles != null){
            for (int i = 0;i < selectedFiles.size();i++){
                informationListView.getItems().add(selectedFiles.get(i).getName());
                informationListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                //selectedFiles.remove(informationListView.getSelectionModel().getSelectedItem());
            }
        }
        else{
            System.out.println("Files not chosen");
        }
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        //System.out.print("Got a hit");
        //TODO:change the actual parameter passed as the primaryStage
        /*
        List<File> selectedFiles= fileChooser.showOpenMultipleDialog( new Popup());//instead of new stage or popup , I need to give it primary stage
        //otherwise the control on previous windows is not locked
        if(selectedFiles!=null){
            for(File file : selectedFiles){mCurrentActiveMarker.addInformation(new BasicInformation(file.toURI().toString()
                    ,file.getName()));}
        }
*/


        if(mListOfMarkers!=null && mListOfMarkers.size()>0){
            updateCurrentActiveMarker(mCurrentActiveMarkerIndex);
        }
    }

    public void doneChangesToInformation(ActionEvent actionEvent) {
        addInformationButton.setDisable(true);
        doneChangesToInformationButton.setDisable(true);
    }

    // What happens when you click on Build button
    @FXML
    private void simpleProjectBuildButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FourthScreen.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("/css/FirstScreen.css").toExternalForm());
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(scene);
        app.show();
    }



    //commenting needs to be done properly and information about functions needs to be added

}
