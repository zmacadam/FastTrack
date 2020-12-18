package zmacadam.metrics.nutrition;

import net.minidev.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;

public interface Search {

    String apiId = "bcabd749";
    String apiKey = "d3598fbc3a27f958899164f2c4c3ed60";
    String userId = "0";

    public HttpRequest createPOSTRequest(String url, JSONObject query);
    public HttpRequest createGETRequest(String url);
}
