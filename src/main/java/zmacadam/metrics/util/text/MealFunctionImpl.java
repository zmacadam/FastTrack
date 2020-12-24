package zmacadam.metrics.util.text;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zmacadam.metrics.model.*;
import zmacadam.metrics.model.nutrition.Food;
import zmacadam.metrics.model.nutrition.FoodDescription;
import zmacadam.metrics.model.nutrition.FoodWrapper;
import zmacadam.metrics.model.nutrition.Meal;
import zmacadam.metrics.model.user.User;
import zmacadam.metrics.repository.FoodRepository;
import zmacadam.metrics.repository.MealRepository;
import zmacadam.metrics.service.DayDetailsService;
import zmacadam.metrics.util.search.SearchBuilder;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Component
public class MealFunctionImpl extends AbstractFunctionExecutor {

    private final DayDetailsService dayDetailsService;
    private final SearchBuilder searchBuilder;
    private final FoodRepository foodRepository;
    private final MealRepository mealRepository;

    private static Logger logger = LoggerFactory.getLogger(MealFunctionImpl.class);

    @Autowired
    public MealFunctionImpl(DayDetailsService dayDetailsService,
                            SearchBuilder searchBuilder,
                            FoodRepository foodRepository,
                            MealRepository mealRepository) {
        super(dayDetailsService);
        this.dayDetailsService = dayDetailsService;
        this.searchBuilder = searchBuilder;
        this.foodRepository = foodRepository;
        this.mealRepository = mealRepository;
    }

    @Override
    public String execute(String identifier, String[] body, User user) {
        logger.info("Meal execute");

        Day day = retrieveDay(user);

        Meal meal = mealRepository.findByMealNumber(Integer.parseInt(identifier));
        if (meal == null) {
            meal = new Meal();
            meal.setDay(day);
            meal.setMealNumber(Integer.parseInt(identifier));
            meal.setTime(new Time(System.currentTimeMillis()));
        }

        for (String line : body) {
            try {
                Object[] foodAndDescription = createFoodAndDescription(line);
                if (foodAndDescription == null) {
                    return "No foods found for entry: " + line;
                }
                FoodDescription foodDescription = (FoodDescription) foodAndDescription[1];
                foodDescription.addFood((Food) foodAndDescription[0]);
                meal.addFoodDescription(foodDescription);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }

        day.addMeal(meal);
        saveDay(day);
        return "Meal successfully added";
    }

    public Object[] createFoodAndDescription(String line) throws IOException, InterruptedException {
        Object[] foodAndDescription  = new Object[2];
        String url = "https://trackapi.nutritionix.com/v2/natural/nutrients";
        JSONObject obj = new JSONObject();
        logger.info(line);
        obj.put("query", line);
        String result = searchBuilder.search(searchBuilder.createPOSTRequest(url, obj));
        JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
        if (jsonObject.has("message")) {
            return null;
        }
        logger.info(result);
        JsonArray foodsArray = jsonObject.getAsJsonArray("foods");
        JsonObject foodsObject = (JsonObject) foodsArray.get(0);
        String foodName = foodsObject.get("food_name").toString();
        Food food = foodRepository.findByFoodName(foodName.replace("\"", ""));
        if (food == null) {
            logger.info("food was null");
            FoodWrapper foodWrapper = (FoodWrapper) searchBuilder.jsonToFood(result, 1);
            foodWrapper.createFood(line);
            foodAndDescription[0] = foodWrapper.getFood();
            foodAndDescription[1] = foodWrapper.getFoodDescription();
            return foodAndDescription;
        }
        foodAndDescription[0] = food;
        FoodDescription foodDescription = new FoodDescription();
        foodDescription.setSearchQuery(line);
        foodDescription.setServingQty(foodsObject.get("serving_qty").toString());
        foodDescription.setServingUnit("ounce");
        foodAndDescription[1] = foodDescription;
        return foodAndDescription;
    }

}
