public class ShutdownProcessThread extends Thread {

    private HttpClientThread[] threads;

    public ShutdownProcessThread(HttpClientThread[] threads){
        this.threads = threads;
    }

    @Override
    public void run()
    {
        for(HttpClientThread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
