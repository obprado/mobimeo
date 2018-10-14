package api;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LineDelayTest extends AcceptanceTest {

    @Test
    public void endpointShouldWork() throws IOException {
        whenWeMakeAGetRequestTo("/delays");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(responseBody).isEqualTo("Line delays!");
    }
}
