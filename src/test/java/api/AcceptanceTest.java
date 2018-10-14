package api;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.obprado.mobimeo.MobilityApp;

import java.io.IOException;

public abstract class AcceptanceTest {
    public static final int PORT = 8081;
    public static final String HOST = "http://localhost:" + PORT;
    protected HttpResponse response;
    protected String responseBody;
    private MobilityApp mobilityApp = new MobilityApp();

    @Before
    public void setUp() throws Exception {
        mobilityApp.run();
    }

    @After
    public void tearDown() throws Exception {
        mobilityApp.stop();
    }

    protected void whenWeMakeAGetRequestTo(final String uri) throws IOException {
        HttpUriRequest httpUriRequest = new HttpGet(HOST + uri);
        response = HttpClientBuilder.create().build().execute(httpUriRequest);
        responseBody = EntityUtils.toString(response.getEntity());
    }
}
