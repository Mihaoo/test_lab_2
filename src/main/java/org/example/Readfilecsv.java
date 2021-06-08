package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Readfilecsv {
    private static final String SAMPLE_CSV_FILE_PATH = "fiserdata.csv";

        public static List<String> parserCSV () throws IOException {
            List<String> arrayarstring=new ArrayList<>();
            try (Reader reader=new BufferedReader(new FileReader(SAMPLE_CSV_FILE_PATH))) {
                try (CSVParser csvParser=new CSVParser(reader, CSVFormat.DEFAULT)) {
                    for (CSVRecord csvRecord : csvParser) {


                        String date=csvRecord.get(0);
                        arrayarstring.add(date);


                        System.out.println("No - " + csvRecord.getRecordNumber());

                        System.out.println("Data : " + date);

                    }
                }
            }
            return arrayarstring;
        }
    }

