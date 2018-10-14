package api;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NextVehicleTest extends AcceptanceTest {

    @Test
    public void endpointShouldWork() throws IOException {
        whenWeMakeAGetRequestTo("/next");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(responseBody).isEqualTo("Next vehicle for stop X!");
    }
}
