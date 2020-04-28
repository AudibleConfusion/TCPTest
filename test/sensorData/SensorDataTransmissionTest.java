package sensorData;

import filepersistence.SensorDataSet;
import org.junit.Assert;
import org.junit.Test;
import transmission.IDataConnection;
import transmission.DataConnector;

import java.io.IOException;

public class SensorDataTransmissionTest
{
    private static final int PORTNUMBER = 9999;

    @Test
    public void gutTransmissionTest() throws IOException, InterruptedException
    {
        // create example data set
        String sensorName = "MyGoodOldSensor"; // does not change
        long timeStamp = System.currentTimeMillis();
        float[] valueSet = new float[3];
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;
        valueSet[2] = (float) 2.1;

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                              receiver side                                        //
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        // create storage
        SensorDataSet dataStorage = new SensorDataSet(sensorName, timeStamp, valueSet);
        // create connections
        IDataConnection receiverConnection = new DataConnector(PORTNUMBER);
        // create receiver
        SensorDataReceiver sensorDataReceiver = new SensorDataReceiver(receiverConnection, dataStorage);

        Thread.sleep(1000);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                              sender side                                          //
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        // create connections
        IDataConnection senderConnection = new DataConnector("localhost", PORTNUMBER);
        // create sender
        SensorDataSender sensorDataSender = new SensorDataSender(senderConnection);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //                               execute communication and test                                      //
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        // send data with TCP
        sensorDataSender.sendData(sensorName, timeStamp, valueSet);

        // test if stored
        SensorDataSet dataStorageReceived = sensorDataReceiver.getStorage();

        // just dummy values
        String sensorNameReceived = dataStorageReceived.getName();
        long timeStampReceived = dataStorageReceived.getTimeStamp();
        float[] valueSetReceived = dataStorageReceived.getValues();

        // test
        Assert.assertEquals(sensorName, sensorNameReceived);
        Assert.assertEquals(timeStamp, timeStampReceived);
        // TODO: test values
    }
}