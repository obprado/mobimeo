package org.obprado.mobimeo;

import java.io.IOException;
import java.util.List;

public class Stops {

    private static final String fileName = "src/main/resources/stops.csv";
    private List<List<String>> content;

    public Stops(List<List<String>> content) {
        this.content = content;
    }

    public static Stops loadFile(CsvReader csvReader) throws IOException {
        return new Stops(csvReader.read(fileName));
    }

    public Stop find(int coordinateX, int coordinateY) {
        return this.content
                .stream()
                .map(Stop::fromRow)
                .filter(stop -> stop.matches(coordinateX, coordinateY))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("TODO: handle sad path"));
    }

}