package zmacadam.metrics.nutrition;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import zmacadam.metrics.model.Food;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstantFoodWrapper {

    private Object[] common;
    private Object[] branded;
    private Object[] foods;
    private Food[] food;

    public void createFoods() {
        List list = new ArrayList(Arrays.asList(common));
        list.addAll(Arrays.asList(branded));
        foods = list.toArray();
        food = new Food[foods.length];
        for (int i = 0; i < foods.length; i++) {
            JsonObject obj = new Gson().toJsonTree(foods[i]).getAsJsonObject();
            food[i] = new Gson().fromJson(obj, Food.class);
        }
    }

    public Food[] getFoods() {
        return food;
    }

    @Override public String toString() {
        String result = "";
        for (Object obj : common) {
            result += obj + "\n";
        }
        for (Object obj : branded) {
            result += obj + "\n";
        }
        return result;
    }
}
