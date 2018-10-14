package api;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NextVehicleTest extends AcceptanceTest {

    @Test
    public void shouldFindVehicle() throws IOException {
        // M4 should come to stop 1 (1, 4)
        whenWeMakeAGetRequestTo("/next?coordinateX=1&coordinateY=4&time=10:00:30");

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(responseBody).isEqualTo("{ \n" +
                "  \"line\": \"M4\",\n" +
                "  \"regularTime\": \"10:02:00\",\n" +
                "  \"actualTime\": \"10:03:00\"\n" +
                "}");
    }
}
