package org.obprado.mobimeo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lines {

    private static final String fileName = "src/main/resources/lines.csv";
    private Delays delays;
    private List<List<String>> content;

    private Lines(List<List<String>> content, Delays delays) {
        this.content = content;
        this.delays = delays;
    }

    public static Lines loadFile(CsvReader csvReader, Delays delays) throws IOException {
        return new Lines(csvReader.read(fileName), delays);
    }

    public List<TimeLine> forTimes(List<Time> arrivals) {
        return arrivals
                .stream()
                .map(arrival -> new TimeLine(arrival, lineForTime(arrival)))
                .collect(Collectors.toList());
    }

    private Line lineForTime(Time time) {
        return this.content
                .stream()
                .map(row -> Line.fromRow(row, delays))
                .filter(line -> line.getId() == time.getLineId())
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("TODO: handle sad path"));
    }
}
