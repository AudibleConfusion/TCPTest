package filepersistence;

import java.io.*;

/*
    I decided to rewrite the writeDataSet and writeToFile methods I was using to proof that i understood the topic.
    I'll do the same to the rest of the project later on as practice.
*/

public interface ISensorDataStorage
{
    /**
     * This method can be called by a sensor to save a data set.
     * @param sensorName name of sensor
     * @param date UNIX time when measurement took place
     * @param measurements sensor data
     * @param dos DataOutputStream handed over by writeToFile method
     * @throws PersistenceException if something unexpected happened. Insufficient right, medium broken, offline..
     */
    void writeDataSet(String sensorName, long date, float[] measurements, DataOutputStream dos) throws PersistenceException;    //Meant to write individual data sets, helper method to writeToFile

    /**
     * This method can be called by a sensor to save a data set.
     * @param sensorName name of sensor
     * @param timeStamps UNIX time when measurements took place
     * @param values sensor data, contains value sets of all data sets that are to be written
     * @throws PersistenceException if something unexpected happened. Insufficient right, medium broken, offline..
     */
    void writeToFile(String sensorName, long[] timeStamps, float[][] values) throws PersistenceException;                       //Writes all given data sets to file

    void readDataSet(DataInputStream dis);

    /**
     *
     */
    void readFromFile();
}