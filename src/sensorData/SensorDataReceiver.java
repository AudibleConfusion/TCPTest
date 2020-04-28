package sensorData;

import filepersistence.SensorDataSet;
import filepersistence.SensorDataStorage;
import transmission.IDataConnection;

import java.io.DataInputStream;

public class SensorDataReceiver extends Thread
{
    private final IDataConnection connection;
    private final SensorDataSet storage;
    private DataInputStream dis = null;

    public SensorDataReceiver(IDataConnection connection, SensorDataSet storage)
    {
        this.connection = connection;
        this.storage = storage;
        this.start();
    }

    SensorDataSet getStorage()
    {
        return storage;
    }

    @Override
    public void run()
    {
        try
        {
            dis = connection.getDataInputStream();

            while(true)
            {
                System.out.println(dis.readUTF());
                System.out.println(dis.readLong());
                System.out.println(dis.readInt());
                System.out.println(dis.readInt());
                System.out.println(dis.readInt());
                System.out.println(dis.readInt());
            }
        }
        catch(Exception e)
        {
            //Nothing here yet
        }
    }
}
