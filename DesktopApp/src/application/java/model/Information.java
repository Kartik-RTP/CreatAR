package application.java.model;

import java.lang.String;

/**
 * Created by Rajat on 10/21/2016.
 */
abstract class Information  {

    private  String mName;
    private  String mAddress;
    private String mType;
    //private final String action;

    public Information(String address){
        //initialize name
        mAddress=address;
        //intialise type
    }

    public Information(String address,String name){
        mName=name;
        mAddress=address;
        //intialise type
    }

    /* customizeInformation allows a user to add
     * how the information will be displayed
     * on screen
     */
    public void customizeInformaion(){
        //TODO: to be implemented
   }

    public String getName(){
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

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
