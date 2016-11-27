package application.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by canopy on 23-10-2016.
 */
abstract class Marker {

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

    Marker(String address){
        //TODO:initalize name
        mAddress = address;
        mInformationList = new ArrayList<Information>();
    }

    Marker(String address,String name){
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
