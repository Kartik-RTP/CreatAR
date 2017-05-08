package application.java.controller;

import application.java.helper.Logger;
import application.java.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.util.Callback;
import javafx.scene.control.Button;
import org.apache.commons.io.FileUtils;
import sun.rmi.runtime.Log;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.soap.Name;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by kartik on 24-10-2016.
 */


public class SimpleTemplateController  implements Initializable{

    private static final String TAG = SimpleTemplateController.class.getSimpleName();
    ///////////////////////////////////////////////////////////////////////////////
    //TODO: check if following 2 fields can be put into MagicManifest object itself
    //TODO: or does it even make sense?
    private Marker mCurrentActiveMarker;
    private  int mCurrentActiveMarkerIndex=0;
    private String markerInfoDir;
    private String markerDirectory;
    private String markerFolder;
    ///////////////////////////////////////////////////////////////////////////////
    private MagicManifest mMagicManifest;//its the project manifest object
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

    Marshaller mMarshallerObj;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //this method is called when all fx:id nodes are available ..
        //I am going to set up the list view at this point
        if(mMagicManifest==null){mMagicManifest=new MagicManifest();
        }
        setupJAXB();

    }

    private void setupJAXB() {
        JAXBContext contextObj = null;
        try {

            contextObj = JAXBContext.newInstance(MagicManifest.class);
            mMarshallerObj = contextObj.createMarshaller();
            mMarshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.println("Marshall is "+ mMarshallerObj.toString());

        } catch (JAXBException e) {
            Logger.log(TAG,"not setting JAXB",3);
            e.printStackTrace();
        }


    }


    public void loadProject() {
        //TODO: may need to intialize MagicManifest
        accessProjectDirectory();
        loadMagicFile();

        //Do all the preprocessing such as building xml files and creating directories
        //TODO : finish this off


    }

    private void loadMagicFile() {
        //TODO: this needs to be implemented from scratch
        File magicFile = new File(mMagicManifest.getProjectDirectory()
                                    +  File.separator+"magic.xml");
        try {
            magicFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(magicFile.getAbsoluteFile().exists()){
            System.out.println(TAG+":"+"magic.xml already exists");
        }
        else{
            //need to use DOM classes and parsers for this
            System.out.println(TAG+":"+"magic.xml has been created");
        }
        //this will create the magic.xml ,if doesn't exist already and then return its handle

        //Probably need to create a anew object for this

        //TODO : incorporate mMagicManifest here


    }

    private void accessProjectDirectory() {

        File projectDirectoryFile = new File(mMagicManifest.getProjectDirectory());
        if (projectDirectoryFile.mkdirs()) {
            System.out.format("\n"+TAG+":"+"Directory %s has been created.", mMagicManifest.
                                                                             getProjectDirectoryFile().
                                                                             getAbsolutePath()
                                                                                                 );

        } else if (projectDirectoryFile.isDirectory()) {
            System.out.format("\n"+TAG+":"+"Directory %s has already been created.",  mMagicManifest.
                                                                                        getProjectDirectoryFile().
                                                                                        getAbsolutePath()
                                                                                                         );

        } else {
            System.out.format("\n"+TAG+":"+"Directory %s could not be created.",  mMagicManifest
                                                                                .getProjectDirectoryFile()
                                                                                .getAbsolutePath()
                                                                                                );

        }
        mMagicManifest.setProjectDirectoryFile(projectDirectoryFile);
    }


    public void addMarker(ActionEvent actionEvent) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Marker Images");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        System.out.print("Got a hit");
        //TODO:change the actual parameter passed as the primaryStage
        List<File> selectedFiles= fileChooser.showOpenMultipleDialog( new Popup());//instead of new stage or popup , I need to give it primary stage
        //otherwise the control on previous windows is not locked

        markerFolder = mMagicManifest.getProjectDirectory() +  File.separator + "marker";
        makeDirectory(markerFolder);

        if(selectedFiles!=null){
            for(File file : selectedFiles){
                mMagicManifest.addMarker(new Marker(file.toURI().toString()
                                                                 ,file.getName()));
                int index = file.getName().indexOf(".");
                String markerDirectoryName = file.getName().substring(0,index);


                markerDirectory = markerFolder + File.separator + markerDirectoryName;

                // Make marker name directory
                makeDirectory(markerFolder + File.separator +markerDirectoryName);

                // Copy images from src to marker name directory
                copyFiles(file.getAbsolutePath(), markerDirectory);

                //Make marker NFT directory
                makeDirectory(markerDirectory + File.separator +"markerNFT");
                copyFiles(file.getAbsolutePath(),markerDirectory + File.separator +"markerNFT");

                // Make marker Info directory
                makeDirectory(markerDirectory + File.separator + "markerInformation");
                markerInfoDir = makeDirectory(markerDirectory + File.separator + "markerInformation");

            }
        }
        if(mMagicManifest.getListOfMarkers()!=null && mMagicManifest.noOfMarkers()>0){

            updateCurrentActiveMarker(mCurrentActiveMarkerIndex);

        }

    }
    public void removeMarker(ActionEvent actionEvent) throws IOException{
        int index = mCurrentActiveMarker.getName().indexOf(".");
        String removedDir = mMagicManifest.getProjectDirectory() + File.separator + mCurrentActiveMarker.getName().substring(0,index);

        FileUtils.deleteDirectory(new File(removedDir));

        if(mMagicManifest.getListOfMarkers()!=null && mMagicManifest.noOfMarkers()>0){

            updateCurrentActiveMarker(mCurrentActiveMarkerIndex);

        }

    }

    private String makeDirectory(String directoryName){
        File dir = new File(directoryName);
        dir.mkdir();

        return directoryName;

    }

    private void copyFiles(String fileSrc, String destination) throws IOException {
        File markerImageSource = new File(fileSrc);
        File markerImageDest = new File(destination);
        FileUtils.copyFileToDirectory(markerImageSource,markerImageDest);
    }

    private void copyFiles(List<File> files,String destination) throws IOException {
        for(File file:files){
            copyFiles(
                    file.getAbsolutePath(),
                    destination
                    );
        }

    }

    private String getNameOfImage(String fileName){
        int index = fileName.indexOf(".");
        String NameOfImage = fileName.substring(0,index);

        return NameOfImage;
    }


    private void updateCurrentActiveMarker(int index) {
            mCurrentActiveMarker = (Marker) mMagicManifest.getMarker(index);
            mCurrentActiveMarkerIndex = index;
            updateMarkerPane();

    }

    /* updateMarkerPane basically just puts out the contents of mCurrentActiveMarker
       on screen in the #put_id_here pane
    */
    private void updateMarkerPane() {
        if(mMagicManifest.noOfMarkers()>0){
            markerBorderPane.setVisible(true); //try to change this
                                               // , calling this function too many times

            //reformat below 2 lines
            gotoNextMarkerButton.setVisible(true);
            gotoPreviousMarkerButton
                    .setVisible(true);
            if (mCurrentActiveMarker != null) {
                markerImageView.setImage(new Image(mCurrentActiveMarker.getAddress()));
                markerNameText.setText(mCurrentActiveMarker.getName());
                updateInformationListView(mCurrentActiveMarker.getInformationList());
                antiEditInformation();

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

       if(mCurrentActiveMarkerIndex<mMagicManifest.noOfMarkers()-1){
           updateCurrentActiveMarker(mCurrentActiveMarkerIndex+1);
       }
    }

    public void editInformation(ActionEvent actionEvent) {
        addInformationButton.setDisable(false);
        doneChangesToInformationButton.setDisable(false);
    }



    // This is the method which is called when we press the "Add Button" next to "Edit Button"
    public void addInformation(ActionEvent actionEvent) throws IOException, JAXBException {
           //TODO: to be filled

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Information media (images/text/video)");
        //fileChooser.setInitialDirectory();

        //TODO: decide on the filters
        fileChooser.getExtensionFilters().addAll(
            //new FileChooser.ExtensionFilter("Text Files","*.txt")
            new FileChooser.ExtensionFilter("Image Files", "*.jpg")


        );


        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        if(selectedFiles.size() > 6){
            selectedFiles = selectedFiles.subList(0,6);
        }

        if(selectedFiles != null){

            //Add the informations to information list of current active marker
            String NameofImage = getNameOfImage(mCurrentActiveMarker.getName());

            String markerInfoImages = makeDirectory(
                    markerFolder + File.separator + NameofImage + File.separator + "markerInformation" + File.separator + "Images");

            makeDirectory(markerInfoImages);
            copyFiles(selectedFiles,markerInfoImages); //TODO : add trycatch to this statement with proper logging

            addInfomationToInformationListOfActiveMarker(selectedFiles);


            //Update the list view
            updateInformationListView(mCurrentActiveMarker.getInformationList());
            Logger.log(TAG, "Directories Created",2);
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
            for(File file : selectedFiles){mCurrentActiveMarker.addInformation(new Information(file.toURI().toString()
                    ,file.getName()));}
        }
*/


        if(mMagicManifest.getListOfMarkers()!=null && mMagicManifest.noOfMarkers()>0){
            updateCurrentActiveMarker(mCurrentActiveMarkerIndex);
        }
    }

    private void addInfomationToInformationListOfActiveMarker(List<File> selectedFiles) throws IOException {
        for (int i=0;i<selectedFiles.size();i++){
            mCurrentActiveMarker.getInformationList().add(
                                                new Information(
                                                        selectedFiles.get(i).getAbsolutePath(),
                                                        selectedFiles.get(i).getName()
                                                                     )
                                                         );
        }
    }

    private void updateInformationListView(List<Information> informationList) {
        //TODO:Note that we are processing entire list even if add a single information ,
        //TODO: Check if this can be optimized somehow
        informationListView.getItems().clear(); //empties the list

        for (int i = 0;i < informationList.size();i++){
            informationListView.getItems().add(
                                                (
                                                         informationList.get(i)
                                                 ).getName()); //TODO:check if this line works properly

            informationListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> list) {
                    return new XCell();
                }
            });

            informationListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            //selectedFiles.remove(informationListView.getSelectionModel().getSelectedItem());
        }
    }

    public void doneChangesToInformation(ActionEvent actionEvent) {

        antiEditInformation();
    }

    //used for partial toggling purposes
    public void antiEditInformation(){
        addInformationButton.setDisable(true);
        doneChangesToInformationButton.setDisable(true);
    }


    // What happens when you click on Build button
    @FXML
    private void simpleProjectBuildButton(ActionEvent event) throws IOException {
        saveProject();


        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FourthScreen.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/FourthScreen.css").toExternalForm());
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(scene);
        app.show();
    }

    private void saveProject() {

        try {
            mMarshallerObj.marshal(mMagicManifest, new FileOutputStream("magic.xml"));
        } catch (JAXBException e) {
            Logger.log(TAG,"unable to save project",3);
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    /////////////////////////////////////////////////////////////////////////////////////////

    // Following methods will be called by previous controller before moving to this controller

    public void setProjectDirectory(String projectDirectory) {
        mMagicManifest.setProjectDirectory(projectDirectory);
        System.out.println(TAG+":"+ projectDirectory);
    }


    public void setProjectTitle(String projectTitle) {
        mMagicManifest.setProjectTitle(projectTitle);
    }

    ///////////////////////////////////////////////////////////////////////////////////////

    class XCell extends ListCell<String>{

        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Pane pane = new Pane();
        Button button = new Button("(X)");
        String lastItem;

        public XCell() {
            super();
            button.getStyleClass().add("deleteInfoButton");
            hbox.getChildren().addAll(label, pane, button);
            HBox.setHgrow(pane, Priority.ALWAYS);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println(lastItem + " : " + event);
                    informationListView.getItems().remove(lastItem);  //This line is to remove a row on button click!

                    //TODO : deleteInformation(); So create this function and
                    //TODO : basically , we need to remove the above rows data as well

                }
            });
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);  // No text in label of super class
            if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
                lastItem = item;
                label.setText(item!=null ? item : "<null>");
                setGraphic(hbox);
            }
        }
    }

    //commenting needs to be done properly and information about functions needs to be added


}
