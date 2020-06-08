import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CreateThread extends Thread{

    private HttpClient httpClient;
    private volatile GetMethod get;
    private int id;
    int w = 0;
    public CreateThread(HttpClient httpClient, int id) {


        this.id = id;
        this.httpClient = httpClient;
    }
    String urisToGet = "http://localhost:8080/hello-world?clientId=";

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public void run() {

        while(true){
            String line = null;
            try {
                line = reader.readLine();
                if (line == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) break;

            try {
                int sleepTime = Helper.getRandomNumber(1000, 5000);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                 get = new GetMethod(urisToGet + id);
                System.out.println(id +
                        " - about to get something from " +
                        get.getURI());
                httpClient.executeMethod(get);

                System.out.println(id + " - get executed");


                byte[] bytes = get.getResponseBody();

                String s = new String(bytes);
                System.out.println(id + " - " + s);

            } catch (Exception e) {
                System.out.println(id + " - error: " + e);
            } finally {
                // always release the connection after we're done
                get.releaseConnection();
                System.out.println(id + " - connection released");
            };
        }}
}

