package filepersistence;
import java.io.*;
import java.util.Date;

// Should be able to write and read variable amounts of datasets with variable amounts of measurements

public class sensor
{
    public static void main(String[] args) throws PersistenceException, IOException
    {
        // three example data sets
        String sensorName = "MyGoodOldSensor"; // does not change

        long timeStamps[] = new long[3];
        timeStamps[0] = System.currentTimeMillis() - 10000;
        timeStamps[1] = timeStamps[0] + 1000;
        timeStamps[2] = timeStamps[1] + 3000;

        float[][] values = new float[3][];
        // 1st measure .. just one value
        float[] valueSet = new float[1];
        values[0] = valueSet;
        valueSet[0] = (float) 1.5; // example value 1.5 degrees

        // 2nd measure .. just three values
        valueSet = new float[3];
        values[1] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;
        valueSet[2] = (float) 2.1;

        // 3rd measure .. two values
        valueSet = new float[2];
        values[2] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;

        //implementation
        ISensorDataStorage sds = new SensorDataStorage();

        sds.writeToFile(sensorName, timeStamps, values);
        sds.readFromFile();
    }
}