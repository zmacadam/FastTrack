package zmacadam.metrics.util.text;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zmacadam.metrics.controller.SMSController;
import zmacadam.metrics.model.*;
import zmacadam.metrics.service.DayDetailsService;
import zmacadam.metrics.util.search.SearchBuilder;

import java.io.IOException;
import java.sql.Time;
import java.util.List;

@Component
public class MealFunctionImpl extends AbstractFunctionExecutor {

    private final DayDetailsService dayDetailsService;
    private final SearchBuilder searchBuilder;

    private static Logger logger = LoggerFactory.getLogger(SMSController.class);

    @Autowired
    public MealFunctionImpl(DayDetailsService dayDetailsService,
                            SearchBuilder searchBuilder) {
        super(dayDetailsService);
        this.dayDetailsService = dayDetailsService;
        this.searchBuilder = searchBuilder;
    }

    @Override
    public String execute(String identifier, String[] body, User user) {
        logger.info("Meal execute");
        List<Day> result = findDayByUser(user);
        Day day = result.get(0);

        Meal meal = new Meal();
        meal.setDay(day);
        meal.setMealNumber(Integer.parseInt(identifier));
        meal.setTime(new Time(System.currentTimeMillis()));

        for (String line : body) {
            try {
                Object[] foodAndDescription = createFoodAndDescription(line);
                if (foodAndDescription == null) {
                    return "No foods found for entry: " + line;
                }
                meal.addFood((Food) foodAndDescription[0]);
                meal.addFoodDescription((FoodDescription) foodAndDescription[1]);
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
        if (jsonObject.get("result").getAsString().equals("We couldn't match any of your foods")) {
            return null;
        }
        FoodWrapper foodWrapper = (FoodWrapper) searchBuilder.jsonToFood(result, 1);
        foodWrapper.createFood();
        foodAndDescription[0] = foodWrapper.getFood();
        foodAndDescription[1] = foodWrapper.getFoodDescription();
        return foodAndDescription;
    }

}
