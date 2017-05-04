package application.java.model;

import sun.misc.InvalidJarIndexException;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kartik on 2/4/17.
 * Contains the various project details ..
 * is an object version of magic.xml
 **/

@XmlRootElement
public class MagicManifest {


    private File mProjectDirectoryFile; //used for accessing project directory
    private List<Marker> mListOfMarkers;
    private List<Information> mListOfInformation;
    private  String mProjectDirectory;
    private String mProjectTitle;




    public MagicManifest(){
        if(mListOfMarkers==null){mListOfMarkers=new ArrayList<Marker>();}
    };

    public File getProjectDirectoryFile() {
        return mProjectDirectoryFile;
    }

    public void setProjectDirectoryFile(File mProjectDirectoryFile) {
        this.mProjectDirectoryFile = mProjectDirectoryFile;
    }

    @XmlList
    public List<Marker> getListOfMarkers() {
        return mListOfMarkers;
    }

    @XmlElement
    public List<Information> getListOfInformation(){return  mListOfInformation;}


    public void addMarker(Marker marker){
        if (mListOfMarkers==null){
            mListOfMarkers = new ArrayList<Marker>();
        }
        mListOfMarkers.add(marker);
    }


    public void addInformation(Information information){
        if(mListOfInformation == null){
            mListOfInformation = new ArrayList<Information>();
        }
        mListOfInformation.add(information);
    }

    public void setListOfMarkers(List<Marker> mListOfMarkers) {
        this.mListOfMarkers = mListOfMarkers;
    }

    public void setListOfInformation (List<Information> mListOfInformation ) {this.mListOfInformation = mListOfInformation;}

    public int noOfMarkers(){
        if (mListOfMarkers==null){return  0;}
        return mListOfMarkers.size();
    }

    public Marker getMarker(int index){
        if(mListOfMarkers==null){
            throw new NullPointerException("List of markers is null...neeeds to be intialized");
        }
        else if (index < 0 && index >= mListOfMarkers.size()){
            throw new IndexOutOfBoundsException("index not found");

        }
        return mListOfMarkers.get(index);
    }

    public String getProjectDirectory() {
        return mProjectDirectory;
    }

    public void setProjectDirectory(String mProjectDirectory) {
        this.mProjectDirectory = mProjectDirectory;
    }

    public String getProjectTitle() {
        return mProjectTitle;
    }

    public void setProjectTitle(String mProjectTitle) {
        this.mProjectTitle = mProjectTitle;
    }
}
