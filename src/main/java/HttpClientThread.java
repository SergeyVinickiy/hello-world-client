import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;


public class HttpClientThread extends Thread{

    private HttpClient httpClient;
    private volatile GetMethod get;
    private int id;
    private String urisToGet = "http://localhost:8080/hello-world?clientId=";

    HttpClientThread(HttpClient httpClient, int id) {
        this.id = id;
        this.httpClient = httpClient;
    }



    public void run() {

        while (true) {

            try {
                int sleepTime = Helper.getRandomNumber(0, 1000);
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
                get.releaseConnection();
            }
        }
    }
}

