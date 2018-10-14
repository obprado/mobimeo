package org.obprado.mobimeo;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Fail.fail;

public class CsvReaderTest {

    @Test
    public void shouldReadACsvFileAndDiscardTheHeader() throws IOException {
        String fileLocation = "src/test/resources/lines.csv";
        List<List<String>> data = new CsvReader().read(fileLocation);

        assertRow(data, 0, "0", "M4");
        assertRow(data, 1, "1", "200");
        assertRow(data, 2, "2", "S75");
    }

    @Test
    public void shouldFailIfFileIsNotThere() {
        String fileLocation = "src/test/resources/nonexistent.csv";
        try {
            new CsvReader().read(fileLocation);
            fail("The CSV reader should have thrown an exception!");
        } catch (IOException exception) {
            assertThat(exception.getMessage()).isEqualTo("Could not read file: src/test/resources/nonexistent.csv");
        }
    }

    private void assertRow(List<List<String>> data, int index, String first, String second) {
        assertThat(data.get(index).get(0)).isEqualTo(first);
        assertThat(data.get(index).get(1)).isEqualTo(second);
    }

}