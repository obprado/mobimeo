package org.obprado.mobimeo;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Times {

    private static final String fileName = "src/main/resources/times.csv";
    private List<List<String>> content;

    public Times(List<List<String>> content) {
        this.content = content;
    }

    public static Times loadFile(CsvReader csvReader) throws IOException {
        return new Times(csvReader.read(fileName));
    }

    public List<Time> timesForStop(Stop stop) {
        return this.content
                .stream()
                .map(Time::fromRow)
                .filter(time -> time.forStopId(stop.getId()))
                .collect(Collectors.toList());
    }
}
