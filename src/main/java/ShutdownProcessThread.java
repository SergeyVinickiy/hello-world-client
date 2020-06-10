
/**
 * Graceful shutdown
 */

public class ShutdownProcessThread extends Thread {

    private HttpClientThread[] threads;

    public ShutdownProcessThread(HttpClientThread[] threads){
        this.threads = threads;
    }


    @Override
    public void run()
    {
        System.out.println("Start to shut down the process");
        for(HttpClientThread thread : threads){
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
