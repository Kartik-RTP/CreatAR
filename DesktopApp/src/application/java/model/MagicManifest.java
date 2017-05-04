package application.java.model;

import com.sun.org.apache.xerces.internal.impl.dtd.XMLSimpleType;
import sun.misc.InvalidJarIndexException;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kartik on 2/4/17.
 * Contains the various project details ..
 * is an object version of magic.xml
 **/

@XmlRootElement(name="ProjectManifest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"mProjectTitle","mProjectDirectory","mListOfMarkers","mProjectDirectoryFile"})
public class MagicManifest {

    private File mProjectDirectoryFile; //used for accessing project directory


    @XmlElement(name = "marker")
    private List<Marker> mListOfMarkers;

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


    public List<Marker> getListOfMarkers() {
        return mListOfMarkers;
    }




    public void addMarker(Marker marker){
        if (mListOfMarkers==null){
            mListOfMarkers = new ArrayList<Marker>();
        }
        mListOfMarkers.add(marker);
    }



    public void setListOfMarkers(List<Marker> mListOfMarkers) {
        this.mListOfMarkers = mListOfMarkers;
    }



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
