package zmacadam.metrics.model.nutrition;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zmacadam.metrics.service.SMSService;

import java.util.Iterator;
import java.util.Set;

public class FoodWrapper {

    private Object[] foods;
    private Food food;
    private FoodDescription foodDescription;

    private static Logger logger = LoggerFactory.getLogger(FoodWrapper.class);

    public Food getFood() {
        return food;
    }

    public FoodDescription getFoodDescription() { return foodDescription; }


    public void createFood(String searchQuery) {
        JsonObject foodObj = new Gson().toJsonTree(foods[0]).getAsJsonObject();
        divideByQty(foodObj);
        JsonObject descObj = createFoodDescription(foodObj);
        descObj.addProperty("search_query", searchQuery);
        logger.info(descObj.toString());
        food = new Gson().fromJson(foodObj, Food.class);
        foodDescription = new Gson().fromJson(descObj, FoodDescription.class);
        logger.info(foodDescription.getSearchQuery());
    }

    public void divideByQty(JsonObject obj) {
        double grams = obj.get("serving_weight_grams").getAsDouble();
        double gram = 28.35;
        double ounces = grams/gram;
        obj.addProperty("serving_qty", ounces);
        obj.addProperty("serving_unit", "ounce");
        Set<String> keys = obj.keySet();
        for (Iterator<String> it = keys.iterator(); it.hasNext(); ) {
            String cur = it.next();
            if (cur.startsWith("nf")) {
                double val = obj.get(cur).getAsDouble();
                obj.addProperty(cur, val/ounces);
            }
        }
    }

    public JsonObject createFoodDescription(JsonObject obj) {
        double servingQty = obj.remove("serving_qty").getAsDouble();
        String servingUnit = obj.remove("serving_unit").getAsString();

        JsonObject foodDesc = new JsonObject();
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
