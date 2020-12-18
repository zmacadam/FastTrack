package zmacadam.metrics.nutrition;

import net.minidev.json.JSONObject;

import java.net.URI;
import java.net.http.HttpRequest;

public class SearchBuilder implements Search {

    @Override
    public HttpRequest createPOSTRequest(String url, JSONObject query) {
        HttpRequest req = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("x-app-id", apiId)
                .header("x-app-key", apiKey)
                .header("x-remote-user-id", userId)
                .uri(URI.create(url))
                .method("POST", HttpRequest.BodyPublishers.ofString(query.toJSONString()))
                .build();
        return req;
    }

    @Override
    public HttpRequest createGETRequest(String url) {
        HttpRequest req = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("x-app-id", apiId)
                .header("x-app-key", apiKey)
                .header("x-remote-user-id", userId)
                .uri(URI.create(url))
                .build();
        return req;
    }
}
