package zmacadam.metrics.model;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import tools.HibernateUtils;
import zmacadam.metrics.nutrition.FoodWrapper;
import zmacadam.metrics.nutrition.InstantFoodWrapper;

import java.sql.Timestamp;

public class StoreFoodTests {

    @Test
    public void storeFood() {
        Day day = new Day();
        day.setId(0);
        day.setDate(new Timestamp(System.currentTimeMillis()));

        Meal meal = new Meal();
        meal.setDay(day);
        meal.setDayId(0);
        meal.setId(0);

        String constant = "{\"foods\":[{\"food_name\":\"tritip\",\"brand_name\":null,\"serving_qty\":8,\"serving_unit\":\"ounces\",\"serving_weight_grams\":226.8,\"nf_calories\":478.55,\"nf_total_fat\":25.11,\"nf_saturated_fat\":null,\"nf_cholesterol\":188.24,\"nf_sodium\":120.2,\"nf_total_carbohydrate\":0,\"nf_dietary_fiber\":0,\"nf_sugars\":0,\"nf_protein\":59.08,\"nf_potassium\":732.56,\"nf_p\":455.87,\"full_nutrients\":[{\"attr_id\":203,\"value\":59.0814},{\"attr_id\":204,\"value\":25.1068},{\"attr_id\":205,\"value\":0},{\"attr_id\":207,\"value\":2.3814},{\"attr_id\":208,\"value\":478.548},{\"attr_id\":221,\"value\":0},{\"attr_id\":255,\"value\":142.0448},{\"attr_id\":262,\"value\":0},{\"attr_id\":263,\"value\":0},{\"attr_id\":268,\"value\":2002.644},{\"attr_id\":269,\"value\":0},{\"attr_id\":291,\"value\":0},{\"attr_id\":301,\"value\":43.092},{\"attr_id\":303,\"value\":3.7649},{\"attr_id\":304,\"value\":49.896},{\"attr_id\":305,\"value\":455.868},{\"attr_id\":306,\"value\":732.564},{\"attr_id\":307,\"value\":120.204},{\"attr_id\":309,\"value\":10.5916},{\"attr_id\":312,\"value\":0.1769},{\"attr_id\":315,\"value\":0.0204},{\"attr_id\":317,\"value\":63.504},{\"attr_id\":318,\"value\":0},{\"attr_id\":319,\"value\":0},{\"attr_id\":320,\"value\":0},{\"attr_id\":321,\"value\":0},{\"attr_id\":322,\"value\":0},{\"attr_id\":323,\"value\":0.9072},{\"attr_id\":334,\"value\":0},{\"attr_id\":337,\"value\":0},{\"attr_id\":338,\"value\":0},{\"attr_id\":341,\"value\":0},{\"attr_id\":342,\"value\":0},{\"attr_id\":343,\"value\":0},{\"attr_id\":401,\"value\":0},{\"attr_id\":404,\"value\":0.1588},{\"attr_id\":405,\"value\":0.2858},{\"attr_id\":406,\"value\":15.724},{\"attr_id\":410,\"value\":1.1658},{\"attr_id\":415,\"value\":1.2361},{\"attr_id\":417,\"value\":18.144},{\"attr_id\":418,\"value\":3.47},{\"attr_id\":421,\"value\":224.9856},{\"attr_id\":430,\"value\":3.1752},{\"attr_id\":431,\"value\":0},{\"attr_id\":432,\"value\":18.144},{\"attr_id\":435,\"value\":18.144},{\"attr_id\":454,\"value\":29.484},{\"attr_id\":501,\"value\":0.3878},{\"attr_id\":502,\"value\":2.361},{\"attr_id\":503,\"value\":2.6876},{\"attr_id\":504,\"value\":4.6993},{\"attr_id\":505,\"value\":4.9919},{\"attr_id\":506,\"value\":1.5377},{\"attr_id\":507,\"value\":0.7621},{\"attr_id\":508,\"value\":2.3338},{\"attr_id\":509,\"value\":1.8824},{\"attr_id\":510,\"value\":2.9303},{\"attr_id\":511,\"value\":3.8193},{\"attr_id\":512,\"value\":1.8847},{\"attr_id\":513,\"value\":3.5925},{\"attr_id\":514,\"value\":5.382},{\"attr_id\":515,\"value\":8.8702},{\"attr_id\":516,\"value\":3.5971},{\"attr_id\":517,\"value\":2.8169},{\"attr_id\":518,\"value\":2.327},{\"attr_id\":521,\"value\":0.6214},{\"attr_id\":601,\"value\":188.244},{\"attr_id\":607,\"value\":0},{\"attr_id\":608,\"value\":0},{\"attr_id\":609,\"value\":0},{\"attr_id\":610,\"value\":0.0113},{\"attr_id\":611,\"value\":0.034},{\"attr_id\":612,\"value\":0.5602},{\"attr_id\":613,\"value\":5.8651},{\"attr_id\":614,\"value\":2.7624},{\"attr_id\":617,\"value\":11.6235},{\"attr_id\":618,\"value\":0.6169},{\"attr_id\":619,\"value\":0.1157},{\"attr_id\":620,\"value\":0.0907},{\"attr_id\":621,\"value\":0},{\"attr_id\":626,\"value\":0.8006},{\"attr_id\":627,\"value\":0},{\"attr_id\":628,\"value\":0.0068},{\"attr_id\":629,\"value\":0},{\"attr_id\":630,\"value\":0},{\"attr_id\":631,\"value\":0}],\"nix_brand_name\":null,\"nix_brand_id\":null,\"nix_item_name\":null,\"nix_item_id\":null,\"upc\":null,\"consumed_at\":\"2020-12-18T21:21:09+00:00\",\"metadata\":{\"is_raw_food\":false},\"source\":1,\"ndb_no\":13953,\"tags\":{\"item\":\"bottom sirloin\",\"measure\":\"ounces\",\"quantity\":\"8.0\",\"food_group\":2,\"tag_id\":2755},\"alt_measures\":[{\"serving_weight\":85,\"measure\":\"oz, cooked\",\"seq\":80,\"qty\":3},{\"serving_weight\":569,\"measure\":\"roast (yield from 690g raw meat)\",\"seq\":1,\"qty\":1},{\"serving_weight\":100,\"measure\":\"g\",\"seq\":null,\"qty\":100},{\"serving_weight\":28.3495,\"measure\":\"wt. oz\",\"seq\":null,\"qty\":1}],\"lat\":null,\"lng\":null,\"meal_type\":3,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"sub_recipe\":null}]}\n";
        FoodWrapper foodWrapper = (FoodWrapper) jsonToFood(constant, 1);
        foodWrapper.createFood();
        Food food = foodWrapper.getFood();

        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(day);
        session.persist(meal);
        session.persist(food);

        transaction.commit();

        session.close();

    }

    public Object jsonToFood(String jsonString, int type) {
        return type == 1 ? new Gson().fromJson(jsonString, FoodWrapper.class) : new Gson().fromJson(jsonString, InstantFoodWrapper.class);
    }
}
