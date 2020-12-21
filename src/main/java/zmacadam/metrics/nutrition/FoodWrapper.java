package zmacadam.metrics.nutrition;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import zmacadam.metrics.model.Food;
import zmacadam.metrics.model.FoodDescription;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FoodWrapper {

    private Object[] foods;
    private Food food;
    private FoodDescription foodDescription;

    public Food getFood() {
        return food;
    }

    public FoodDescription getFoodDescription() { return foodDescription; }

    public void createFood() {
        JsonObject foodObj = new Gson().toJsonTree(foods[0]).getAsJsonObject();
        divideByQty(foodObj);
        JsonObject descObj = createFoodDescription(foodObj);
        food = new Gson().fromJson(foodObj, Food.class);
        foodDescription = new Gson().fromJson(descObj, FoodDescription.class);
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

    public JsonObject createFoodDescription(JsonObject obj) {
        String foodName = obj.remove("food_name").getAsString();
        String brandName = obj.get("brand_name") == null ? "null" : obj.get("brand_name").getAsString();
        obj.remove("brand_name");
        double servingQty = obj.remove("serving_qty").getAsDouble();
        String servingUnit = obj.remove("serving_unit").getAsString();
        System.out.println(foodName + ", " + brandName + ", " + servingQty + ", " + servingUnit);

        JsonObject foodDesc = new JsonObject();
        foodDesc.addProperty("food_name", foodName);
        foodDesc.addProperty("brand_name", brandName);
        foodDesc.addProperty("serving_qty", servingQty);
        foodDesc.addProperty("serving_unit", servingUnit);

        return foodDesc;
    }

    @Override public String toString() {
        String result = "";
        for (Object obj : foods) {
            result += obj + "\n";
        }
        return result;
    }
}
