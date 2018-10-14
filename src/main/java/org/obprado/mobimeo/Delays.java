package org.obprado.mobimeo;

import java.io.IOException;
import java.util.List;

public class Delays {

    private static final String fileName = "src/main/resources/delays.csv";
    public static final int NAME_INDEX = 0;
    public static final int DELAY_INDEX = 1;
    private List<List<String>> content;

    public Delays(List<List<String>> content) {
        this.content = content;
    }

    public static Delays loadFile(CsvReader csvReader) throws IOException {
        return new Delays(csvReader.read(fileName));
    }

    public int findDelayFor(String name) {
        return this.content
                .stream()
                .filter(row -> row.get(NAME_INDEX).contentEquals(name))
                .map(row -> row.get(DELAY_INDEX))
                .map(Integer::valueOf)
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("TODO: handle sad path"));
    }
}
