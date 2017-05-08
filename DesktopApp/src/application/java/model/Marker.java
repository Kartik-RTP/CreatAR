package application.java.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by canopy on 23-10-2016.
 */

@XmlRootElement
@XmlType(propOrder = {"name","address","informationList"})
public class Marker {


    private String mName;

    private String mAddress;

    private List<Information> mInformationList;

    @XmlElement(name = "name")
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @XmlElement(name = "address")
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    @XmlElement(name = "information")
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

    public Marker(){

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
