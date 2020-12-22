package zmacadam.metrics.util.search;

import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;
import zmacadam.metrics.model.nutrition.FoodWrapper;
import zmacadam.metrics.model.nutrition.InstantFoodWrapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class SearchBuilder implements Search {

    @Override
    public HttpRequest createPOSTRequest(String url, JSONObject query) {
        return HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("x-app-id", apiId)
                .header("x-app-key", apiKey)
                .header("x-remote-user-id", userId)
                .uri(URI.create(url))
                .method("POST", HttpRequest.BodyPublishers.ofString(query.toJSONString()))
                .build();
    }

    @Override
    public HttpRequest createGETRequest(String url) {
        return HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("x-app-id", apiId)
                .header("x-app-key", apiKey)
                .header("x-remote-user-id", userId)
                .uri(URI.create(url))
                .build();
    }

    public String search(HttpRequest req) throws IOException, InterruptedException {
        HttpResponse<String> response = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }

    public Object jsonToFood(String jsonString, int type) {
        return type == 1 ? new Gson().fromJson(jsonString, FoodWrapper.class) : new Gson().fromJson(jsonString, InstantFoodWrapper.class);
    }
}
