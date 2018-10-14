package org.obprado.mobimeo;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {

    public List<List<String>> read(String fileLocation) throws IOException {
        try {
            List<String[]> rows = new CSVReader(new FileReader(fileLocation)).readAll();
            rows.remove(0); // Discard header
            return rows
                    .stream()
                    .map(Arrays::stream)
                    .map(stringStream -> stringStream.collect(Collectors.toList()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException("Could not read file: " + fileLocation, e);
        }
    }
}
