package org.csv_parsing;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.*;

public class Main {
    static File targetCSV1 = new File("CommonCSV.csv");
    static File targetCSV2 = new File("OpenCSV.csv");
    static List<String[]> datasetRecords = new ArrayList<>();

    public static void main(String[] args) {

        datasetRecords.add(new String[] {"NrCard", "FirstName", "LastName"});
        datasetRecords.add(new String[] {"000001", "Scafaru", "Maxim"});
        datasetRecords.add(new String[] {"000002", "Kamushkin", "Petya"});

        try (FileWriter writer = new FileWriter(targetCSV1)) {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL);
            printer.printRecords(datasetRecords);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}