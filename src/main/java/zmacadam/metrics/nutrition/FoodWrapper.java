package zmacadam.metrics.nutrition;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import zmacadam.metrics.model.Food;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FoodWrapper {

    private Object[] foods;
    private Food food;

    public Food getFood() {
        return food;
    }

    public void createFood() {
        JsonObject obj = new Gson().toJsonTree(foods[0]).getAsJsonObject();
        divideByQty(obj);
        System.out.println(obj.toString());
        food = new Gson().fromJson(obj, Food.class);
    }

    public void divideByQty(JsonObject obj) {
        double divisor = obj.get("serving_qty").getAsDouble();
        obj.addProperty("unit", obj.get("serving_unit").getAsString());
        Set<String> keys = obj.keySet();
        for (Iterator<String> it = keys.iterator(); it.hasNext(); ) {
            String cur = it.next();
            if (cur.startsWith("nf")) {
                double val = obj.get(cur).getAsDouble();
                obj.addProperty(cur, val/divisor);
            }
        }
    }
    @Override public String toString() {
        String result = "";
        for (Object obj : foods) {
            result += obj + "\n";
        }
        return result;
    }
}
