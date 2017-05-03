package application.java.helper;

/**
 * Created by Rajat on 5/2/2017.
 */
public class Logger {

    public static void log(String TAG, String message){
        System.out.println(TAG+ ":" + message);
    }

    public static void log(String TAG, String message, int importanceLevel){
        // importanceLevel = 1 : Lowest critical level
        // importanceLevel = 1 : Highest critical level

        if (importanceLevel == 1){
            System.out.println(TAG + ":" + message);
        }
        else if(importanceLevel == 2){
            System.out.println("$$$     " + TAG + ":" + message + "     $$$");
        }
        else if (importanceLevel == 3){

            System.out.println("--------------------------");
            System.out.println(TAG + ":" + message);
            System.out.println("--------------------------");
            System.out.println("\n");
        }
        else{
            System.out.println(TAG + ":" + message);
        }
    }
}
