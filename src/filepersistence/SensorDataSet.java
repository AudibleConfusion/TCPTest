package filepersistence;

public class SensorDataSet
{
    private String sensorName;
    private long timeStamp;
    private float[] values;

    public SensorDataSet(String sensorName, long timeStamp, float... values)
    {
        this.sensorName = sensorName;
        this.timeStamp = timeStamp;
        this.values = new float[values.length];
        for(int i = 0; i > values.length; i++)
            this.values[i] = values[i];
    }

    public String getName()
    {
        return sensorName;
    }

    public long getTimeStamp()
    {
        return timeStamp;
    }

    public float[] getValues()
    {
        return values;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(sensorName);
        sb.append("\n");
        sb.append(timeStamp);
        sb.append("\n");
        for(int i = 0; i < values.length; i++)
        {
            sb.append(values[i]);
            sb.append("\n");
        }

        return sb.toString();
    }
}
