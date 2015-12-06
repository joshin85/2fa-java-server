import java.net.*;


public class Server {

    public static void main(String[] args) {

        int nreq = 1;
        System.out.println("Starting server");
        try
        {
        	System.out.println("Opening Socket");
            ServerSocket sock = new ServerSocket (5000);
            for (;;)
            {
                Socket newsock = sock.accept();
                System.out.println("Creating thread ...");
                Thread t = new ThreadHandler(newsock,nreq);
                t.start();
            }
        }
        catch (Exception e)
        {
            System.out.println("IO error " + e);
        }
        System.out.println("End!");
    }
}

