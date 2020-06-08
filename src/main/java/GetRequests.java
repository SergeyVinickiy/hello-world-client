import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.HttpClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetRequests {

private static int numberOfClient = 0;

public static void main(String[] args) throws IOException{

    //Getting parameters from User

    BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("Please choose number of HTTP clients to simulate: ");
    for(int numberOfTries = 0; numberOfTries <3; numberOfTries++){
        String tempString = obj.readLine();
        if(Helper.isInteger(tempString)) {
            numberOfClient = Integer.parseInt(tempString);
            break;
        }
        else{
            if(numberOfTries < 2){
                System.out.println("Should be an Integer. Please try again");}
            else{
                System.out.println("Sorry, it was last try");
            }
        }
    }


    //Creating the loop to start the threads

    HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
    CreateThread[] threads = new CreateThread[numberOfClient];

    for (int i = 0; i < threads.length; i++) {
    threads[i] = new CreateThread(httpClient, i);
    threads[i].start();}

    }
}