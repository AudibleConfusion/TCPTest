package transmission;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DataConnector extends Thread implements IDataConnection
{
    private int port;
    private String address;
    private Socket socket;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    /**
     * Create client side - open connection to address / port
     * @param address
     */
    public DataConnector(String address, int port) throws IOException
    {
        this.address = address;
        this.port = port;
        this.start();
    }

    /**
     * Create server side - open port on this port and wait for one client
     * @param port
     */
    public DataConnector(int port) throws IOException
    {
        this.port = port;
        this.start();
    }

    @Override
    public DataInputStream getDataInputStream() throws IOException
    {
        return dis;
    }

    @Override
    public DataOutputStream getDataOutputStream() throws IOException
    {
        return dos;
    }

    @Override
    public void run()
    {
        if(address == null)
        {
            try
            {
                ServerSocket server = new ServerSocket(port);
                socket = server.accept();
            }
            catch(Exception e)
            {
                //Nothing for now
            }
        }
        else
        {
            try
            {
                socket = new Socket(address, port);
            }
            catch(Exception e)
            {
                //Nothing for now
            }
        }

        try
        {
            InputStream is = socket.getInputStream();
            dis = new DataInputStream(is);
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
        }
        catch(Exception e)
        {
            //Nothing for now
        }
    }
}