
import exceptions.AcceptConnectionException;
import java.io.IOException;
import server.ServerTcp;

public class Main
{

    public Main()
    {
    }

    public static void main(String args[])
    {
        try
        {
            ServerTcp servertcp = new ServerTcp(5777, 10, "127.0.0.1");
            servertcp.startServerTcp();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
