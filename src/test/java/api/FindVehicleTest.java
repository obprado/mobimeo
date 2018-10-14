package api;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FindVehicleTest extends AcceptanceTest {

    @Test
    public void endpointShouldWork() throws IOException {
        whenWeMakeAGetRequestTo("/find");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(responseBody).isEqualTo("Find a vehicle!");
    }

}
