package application.java.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by canopy on 23-10-2016.
 */


public class Marker {

    private String mName;
    private String mAddress;

    private List<Information> mInformationList;


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }


    public List<Information> getInformationList() {
        return mInformationList;
    }

    public void setInformationList(List<Information> informationList) {
        mInformationList = informationList;
    }


    public Marker(String address){
        //TODO:initalize name
        mAddress = address;
        mInformationList = new ArrayList<Information>();
    }

    Marker(){

    }

    public Marker(String address, String name){
        mName=name;
        mAddress = address;
        mInformationList = new ArrayList<Information>();
    }

    public void addInformation(Information information){
        mInformationList.add(information);
    }

    public void removeInfromation(int index ){
        mInformationList.remove(index);
    }

    public Information getInformation(int index){

        return mInformationList.get(index);
    }

}
