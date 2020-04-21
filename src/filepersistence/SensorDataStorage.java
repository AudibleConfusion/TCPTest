package filepersistence;

import java.io.*;

public class SensorDataStorage implements ISensorDataStorage
{
    @Override
    public void writeDataSet(String sensorName, long date, float[] measurements, DataOutputStream dos) throws PersistenceException
    {
        try
        {
            dos.writeUTF(sensorName);           //Write the name of the sensor to file | not sure how that can go wrong aside from any way that isn't already handled by the catch clause
            if(date > System.currentTimeMillis())   //Check if the time stamp is in the future, throw exception if so
                throw new PersistenceException();
            dos.writeLong(date);
            dos.writeInt(measurements.length);  //Write length of array to file | see above
            for(float f : measurements)
            {
                if(f < -273.15)                 //Check if a measurement is below absolute zero, throw an exception if so
                    throw new PersistenceException();
                dos.writeFloat(f);
            }
        }
        catch (IOException ex)
        {
            System.err.println("Couldn't write to file - fatal");
        }
    }

    @Override
    public void writeToFile(String sensorName, long[] timeStamps, float[][] values) throws PersistenceException
    {
        if(timeStamps.length == 0)  // check if the timeStamp array, actually contains timeStamps
            throw new PersistenceException();
        /*
            then check if the timeStamps are in order, throw exception should there be an instance where that is not the case
            it is assumed that the sensor messed the dates up, rather than try to write them out of order
            ~this exists simply to give me another thing to check for during testing
        */
        for(int i = 0; i < timeStamps.length - 1; i++)
        {
            if(timeStamps[i] > timeStamps[i+1])
                throw new PersistenceException();
        }

        if(timeStamps.length != values.length) //check if there are equal amounts of time stamps and value stats, throw exception if not
            throw new PersistenceException();

        //try setting up the output streams, throw exception if failure
        OutputStream os;
        DataOutputStream dos = null;

        try
        {
            os = new FileOutputStream("testfile.txt");
            dos = new DataOutputStream(os);
        }
        catch(FileNotFoundException ex)
        {
            System.err.println("Couldn't open file - fatal");
            System.exit(0);
        }

        //Try write data to file, throw exception if failure
        try
        {
            dos.writeInt(timeStamps.length); //Number of Timestamps to track how many dataset have been written to the file
            for(int i = 0; i < timeStamps.length; i++)
                writeDataSet(sensorName, timeStamps[i], values[i], dos); //write every individual data set to file, see write data set
        }
        catch(IOException ex)
        {
            System.err.println("Couldn't write to file - fatal");
            System.exit(0);
        }
    }
}
