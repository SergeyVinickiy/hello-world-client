import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * Run method of client threads
 */

public class HttpClientThread extends Thread {

    private HttpClient httpClient;
    private volatile GetMethod get;
    private int id;
    private boolean isRunning = true;
    private String urisToGet = "http://localhost:8080/hello-world?clientId=";

    HttpClientThread(HttpClient httpClient, int id) {
        this.id = id;
        this.httpClient = httpClient;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void run() {

        while (isRunning) {

            try {
                int sleepTime = getRandomNumber(0, 1000);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                get = new GetMethod(urisToGet + id);
                System.out.println(id +
                        " client about to send request to " +
                        get.getURI());
                httpClient.executeMethod(get);

                byte[] bytes = get.getResponseBody();

                String s = new String(bytes);
                System.out.println(id + " client got the response: " + s);

            } catch (Exception e) {
                System.out.println(id + " - error: " + e);
            } finally {
                get.releaseConnection();
            }
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}

