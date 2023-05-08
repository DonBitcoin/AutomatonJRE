package org.csv_parsing;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSV {
    static final String FOLDER = "src/main/resources/";
    static List<String[]> writerRecords = new ArrayList<>();
    static List<String[]> readerRecord1 = new ArrayList<>();
    static List<String[]> readerRecord2 = new ArrayList<>();

    public static void main(String[] args) {
        writerRecords.add(new String[] {"NrCard", "FirstName", "LastName"});
        writerRecords.add(new String[] {"000001", "Scafaru", "Maxim"});
        writerRecords.add(new String[] {"000002", "Kamushkin", "Petya"});

        var targetCSV1 = new File(FOLDER + "CommonCSV.csv");
        var targetCSV2 = new File(FOLDER + "OpenCSV.csv");
        var targetCSV3 = new File(FOLDER + "deniro.csv");

        //(1.1) Apache Commons CSV serialization routine.
        try (var printer = new CSVPrinter(new FileWriter(targetCSV1), CSVFormat.EXCEL)) {
            printer.printRecords(writerRecords);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        //(1.2) OpenCSV file.CSV serialization routine.
        try (var printer = new CSVWriter(new FileWriter(targetCSV2))) {
            for (var data : writerRecords) {
                printer.writeNext(data, false);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        //(1.3) Parsing a file.CSV with OpenCSV component.
        try (var reader = new CSVReader(new FileReader(targetCSV3))) {
            readerRecord1 = reader.readAll();
        }
        catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

        //(1.4) Parsing a file.CSV with Scanner class.
        try (var reader = new Scanner(new FileReader(targetCSV3))) {
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                readerRecord2.add(data.split(",(\\s)*"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}