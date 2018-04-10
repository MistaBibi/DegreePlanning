package com.hci.degreeplanningandroid;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVFile {
    private String fileName;
    private InputStream inputStream = null;
    private ArrayList<String> CSVValues = null;

    public CSVFile(InputStream inputStream, String fileName) {
        this.fileName = fileName;
        this.inputStream = inputStream;
        CSVValues = new ArrayList<String>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                if (!row[0].equals("CLASS")) {
                    if (row[1].equals("X")) {
                        CSVValues.add(row[0] + " TAKEN");
                    } else if (row[2].equals("X")) {
                        CSVValues.add(row[0] + " IN PROGRESS");
                    } else if (row[3].equals("X")) {
                        CSVValues.add(row[0] + " NOT COMPLETED");
                    }
                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        } finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
    }

    /*
     * Returns the entire CSV file as a List of String arrays
     */
    public ArrayList<String> read(){
        return CSVValues;
    }

    /*
     * Finds a specific csv line whose first value is equal to key
     *
     * If no line is found, return null
     */
    public String[] findRecord(String key) {
        for(Object line : CSVValues) {
            if(((String[]) line)[1].equals(key)) {
                return (String[]) line;
            }
        }
        return null;
    }

    public void updateRecord(String[] record) {
        for(Object line : CSVValues) {
            if(((String[]) line)[1].equals(record[1])) {
                line = record;
            }
        }
    }

    public void addRecord(String record) {
        CSVValues.add(record);
    }

//    public void write() {
//        FileWriter file;
//        try {
//            file = new FileWriter(fileName);
//
//        } catch (IOException e) {
//
//        }
//    }
}