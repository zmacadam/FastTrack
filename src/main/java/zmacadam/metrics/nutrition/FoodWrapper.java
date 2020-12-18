package zmacadam.metrics.nutrition;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class FoodWrapper {

    private Object[] foods;
    private Food food;

    public Food getFood() {
        return food;
    }

    public void createFood() {
        JsonObject obj = new Gson().toJsonTree(foods[0]).getAsJsonObject();
        food = new Gson().fromJson(obj, Food.class);
    }

    @Override public String toString() {
        String result = "";
        for (Object obj : foods) {
            result += obj + "\n";
        }
        return result;
    }
}
