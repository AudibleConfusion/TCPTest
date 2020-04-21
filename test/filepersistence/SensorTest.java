package filepersistence;

import org.junit.Assert;
import org.junit.Test;

public class SensorTest {
    @Test
    public void gutTest1() throws PersistenceException //using a two time stamps and one value each
    {
        ISensorDataStorage sds = new SensorDataStorage();

        String sensorName = "Bob der Sensor";

        long timeStamps[] = new long[2];
        timeStamps[0] = System.currentTimeMillis() - 5000;
        timeStamps[1] = System.currentTimeMillis() - 1000;

        float[][] values = new float[2][];
        float[] valueSet1 = new float[1];
        valueSet1[0] = (float) 1.0;
        float[] valueSet2 = new float[1];
        valueSet2[0] = (float) -12.0;
        values[0] = valueSet1;
        values[1] = valueSet2;

        sds.writeToFile(sensorName, timeStamps, values);
    }

    @Test
    public void gutTest2() throws PersistenceException //using a 3 time stamps and varying amounts of values each
    {
        ISensorDataStorage sds = new SensorDataStorage();

        String sensorName = "Heinz der Sensor";

        long timeStamps[] = new long[3];
        timeStamps[0] = System.currentTimeMillis() - 5000;
        timeStamps[1] = System.currentTimeMillis() - 3000;
        timeStamps[2] = System.currentTimeMillis() - 1000;

        float[][] values = new float[3][];
        float[] valueSet1 = new float[1];
        valueSet1[0] = (float) 1.0;
        float[] valueSet2 = new float[3];
        valueSet2[0] = (float) -55.0;
        valueSet2[1] = (float) 22;
        valueSet2[2] = (float) 1;
        float[] valueSet3 = new float[2];
        valueSet3[0] = (float) 2.3;
        valueSet3[1] = (float) 2.5;
        values[0] = valueSet1;
        values[1] = valueSet2;
        values[2] = valueSet3;

        sds.writeToFile(sensorName, timeStamps, values);
    }

    @Test
    public void randTest() throws PersistenceException //using a time stamp that is equal to current time as well as a measurement at absolute zero
    {
        ISensorDataStorage sds = new SensorDataStorage();

        String sensorName = "Karl der Sensor";

        long timeStamps[] = new long[2];
        timeStamps[0] = System.currentTimeMillis() - 5000;
        timeStamps[1] = System.currentTimeMillis();

        float[][] values = new float[2][];
        float[] valueSet1 = new float[1];
        valueSet1[0] = (float) 1.0;
        float[] valueSet2 = new float[1];
        valueSet2[0] = (float) -273.15;
        values[0] = valueSet1;
        values[1] = valueSet2;

        sds.writeToFile(sensorName, timeStamps, values);
    }

    @Test
    public void schlechtTest1() throws PersistenceException //using time stamps that aren't in ascending order
    {
        ISensorDataStorage sds = new SensorDataStorage();

        String sensorName = "Fred der Sensor";

        long timeStamps[] = new long[2];
        timeStamps[0] = System.currentTimeMillis() - 1000;
        timeStamps[1] = System.currentTimeMillis() - 5000;

        float[][] values = new float[2][];
        float[] valueSet1 = new float[1];
        valueSet1[0] = (float) 1.0;
        float[] valueSet2 = new float[1];
        valueSet2[0] = (float) -12.0;
        values[0] = valueSet1;
        values[1] = valueSet2;

        try {
            sds.writeToFile(sensorName, timeStamps, values);
            Assert.fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void schlechtTest2() throws PersistenceException //using more time stamps than value sets
    {
        ISensorDataStorage sds = new SensorDataStorage();

        String sensorName = "Joe der Sensor";

        long timeStamps[] = new long[3];
        timeStamps[0] = System.currentTimeMillis() - 1000;
        timeStamps[1] = System.currentTimeMillis() - 500;
        timeStamps[2] = System.currentTimeMillis();

        float[][] values = new float[2][];
        float[] valueSet1 = new float[1];
        valueSet1[0] = (float) 1.0;
        float[] valueSet2 = new float[1];
        valueSet2[0] = (float) -12.0;
        values[0] = valueSet1;
        values[1] = valueSet2;

        try {
            sds.writeToFile(sensorName, timeStamps, values);
            Assert.fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void schlechtTest3() throws PersistenceException //using less time stamps than value sets
    {
        ISensorDataStorage sds = new SensorDataStorage();

        String sensorName = "Nancy die Sensorin";

        long timeStamps[] = new long[1];
        timeStamps[0] = System.currentTimeMillis();

        float[][] values = new float[2][];
        float[] valueSet1 = new float[1];
        valueSet1[0] = (float) 1.0;
        float[] valueSet2 = new float[1];
        valueSet2[0] = (float) -12.0;
        values[0] = valueSet1;
        values[1] = valueSet2;

        try {
            sds.writeToFile(sensorName, timeStamps, values);
            Assert.fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void schlechtTest4() throws PersistenceException //using time stamp array with no entries
    {
        ISensorDataStorage sds = new SensorDataStorage();

        String sensorName = "Nancy die Sensorin";

        long timeStamps[] = new long[0];

        float[][] values = new float[2][];
        float[] valueSet1 = new float[1];
        valueSet1[0] = (float) 1.0;
        float[] valueSet2 = new float[1];
        valueSet2[0] = (float) -12.0;
        values[0] = valueSet1;
        values[1] = valueSet2;

        try {
            sds.writeToFile(sensorName, timeStamps, values);
            Assert.fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void schlechtTest5() throws PersistenceException //using time stamp from the future
    {
        ISensorDataStorage sds = new SensorDataStorage();

        String sensorName = "Heidi die Sensorin";

        long timeStamps[] = new long[1];
        timeStamps[0] = System.currentTimeMillis() + 10000;

        float[][] values = new float[1][];
        float[] valueSet1 = new float[1];
        valueSet1[0] = (float) 1.0;

        try
        {
            sds.writeToFile(sensorName, timeStamps, values);
            Assert.fail();
        }
        catch (Exception e){
        }
    }
}