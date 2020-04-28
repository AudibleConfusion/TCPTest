package sensorData;

import transmission.IDataConnection;

import java.io.DataOutputStream;
import java.io.IOException;

public class SensorDataSender
{
    private final IDataConnection connection;
    public SensorDataSender(IDataConnection connection)
    {
        this.connection = connection;
    }

    public void sendData(String name, long time, float[] values) throws IOException
    {
        DataOutputStream dos = connection.getDataOutputStream();

        try
        {
            dos.writeUTF(name);
            dos.writeLong(time);
            dos.writeInt(values.length);
            for(int i = 0; i < values.length; i++)
                dos.writeFloat(values[i]);
        }
        catch (Exception e)
        {
            System.err.println("Data transmission failed - fatal");
        }
    }
}