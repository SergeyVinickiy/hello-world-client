import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class InputReaderThread extends Thread{

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(
                System.in ) );

            @Override
            public void run()
            {
                while( !Thread.interrupted() )
                {
                    try
                    {
                        String line = bufferedReader.readLine();
                        if( line != null )
                            System.exit(0);
                    }
                    catch( IOException e )
                    {
                        System.out.println( getClass().getName() + ": " + e );
                    }
                }
            }

}
