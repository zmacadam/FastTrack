package zmacadam.metrics.nutrition;

import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.stereotype.Component;
import zmacadam.metrics.model.Food;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class SearchTests {

    private SearchBuilder searchBuilder = new SearchBuilder();

    @Test
    public void naturalTest() throws IOException, InterruptedException {
//        String url = "https://trackapi.nutritionix.com/v2/natural/nutrients";
//        JSONObject obj = new JSONObject();
//        obj.put("query", "8 ounces of tritip");
//        String result = search(searchBuilder.createPOSTRequest(url, obj));
        String constant = "{\"foods\":[{\"food_name\":\"tritip\",\"brand_name\":null,\"serving_qty\":8,\"serving_unit\":\"ounces\",\"serving_weight_grams\":226.8,\"nf_calories\":478.55,\"nf_total_fat\":25.11,\"nf_saturated_fat\":null,\"nf_cholesterol\":188.24,\"nf_sodium\":120.2,\"nf_total_carbohydrate\":0,\"nf_dietary_fiber\":0,\"nf_sugars\":0,\"nf_protein\":59.08,\"nf_potassium\":732.56,\"nf_p\":455.87,\"full_nutrients\":[{\"attr_id\":203,\"value\":59.0814},{\"attr_id\":204,\"value\":25.1068},{\"attr_id\":205,\"value\":0},{\"attr_id\":207,\"value\":2.3814},{\"attr_id\":208,\"value\":478.548},{\"attr_id\":221,\"value\":0},{\"attr_id\":255,\"value\":142.0448},{\"attr_id\":262,\"value\":0},{\"attr_id\":263,\"value\":0},{\"attr_id\":268,\"value\":2002.644},{\"attr_id\":269,\"value\":0},{\"attr_id\":291,\"value\":0},{\"attr_id\":301,\"value\":43.092},{\"attr_id\":303,\"value\":3.7649},{\"attr_id\":304,\"value\":49.896},{\"attr_id\":305,\"value\":455.868},{\"attr_id\":306,\"value\":732.564},{\"attr_id\":307,\"value\":120.204},{\"attr_id\":309,\"value\":10.5916},{\"attr_id\":312,\"value\":0.1769},{\"attr_id\":315,\"value\":0.0204},{\"attr_id\":317,\"value\":63.504},{\"attr_id\":318,\"value\":0},{\"attr_id\":319,\"value\":0},{\"attr_id\":320,\"value\":0},{\"attr_id\":321,\"value\":0},{\"attr_id\":322,\"value\":0},{\"attr_id\":323,\"value\":0.9072},{\"attr_id\":334,\"value\":0},{\"attr_id\":337,\"value\":0},{\"attr_id\":338,\"value\":0},{\"attr_id\":341,\"value\":0},{\"attr_id\":342,\"value\":0},{\"attr_id\":343,\"value\":0},{\"attr_id\":401,\"value\":0},{\"attr_id\":404,\"value\":0.1588},{\"attr_id\":405,\"value\":0.2858},{\"attr_id\":406,\"value\":15.724},{\"attr_id\":410,\"value\":1.1658},{\"attr_id\":415,\"value\":1.2361},{\"attr_id\":417,\"value\":18.144},{\"attr_id\":418,\"value\":3.47},{\"attr_id\":421,\"value\":224.9856},{\"attr_id\":430,\"value\":3.1752},{\"attr_id\":431,\"value\":0},{\"attr_id\":432,\"value\":18.144},{\"attr_id\":435,\"value\":18.144},{\"attr_id\":454,\"value\":29.484},{\"attr_id\":501,\"value\":0.3878},{\"attr_id\":502,\"value\":2.361},{\"attr_id\":503,\"value\":2.6876},{\"attr_id\":504,\"value\":4.6993},{\"attr_id\":505,\"value\":4.9919},{\"attr_id\":506,\"value\":1.5377},{\"attr_id\":507,\"value\":0.7621},{\"attr_id\":508,\"value\":2.3338},{\"attr_id\":509,\"value\":1.8824},{\"attr_id\":510,\"value\":2.9303},{\"attr_id\":511,\"value\":3.8193},{\"attr_id\":512,\"value\":1.8847},{\"attr_id\":513,\"value\":3.5925},{\"attr_id\":514,\"value\":5.382},{\"attr_id\":515,\"value\":8.8702},{\"attr_id\":516,\"value\":3.5971},{\"attr_id\":517,\"value\":2.8169},{\"attr_id\":518,\"value\":2.327},{\"attr_id\":521,\"value\":0.6214},{\"attr_id\":601,\"value\":188.244},{\"attr_id\":607,\"value\":0},{\"attr_id\":608,\"value\":0},{\"attr_id\":609,\"value\":0},{\"attr_id\":610,\"value\":0.0113},{\"attr_id\":611,\"value\":0.034},{\"attr_id\":612,\"value\":0.5602},{\"attr_id\":613,\"value\":5.8651},{\"attr_id\":614,\"value\":2.7624},{\"attr_id\":617,\"value\":11.6235},{\"attr_id\":618,\"value\":0.6169},{\"attr_id\":619,\"value\":0.1157},{\"attr_id\":620,\"value\":0.0907},{\"attr_id\":621,\"value\":0},{\"attr_id\":626,\"value\":0.8006},{\"attr_id\":627,\"value\":0},{\"attr_id\":628,\"value\":0.0068},{\"attr_id\":629,\"value\":0},{\"attr_id\":630,\"value\":0},{\"attr_id\":631,\"value\":0}],\"nix_brand_name\":null,\"nix_brand_id\":null,\"nix_item_name\":null,\"nix_item_id\":null,\"upc\":null,\"consumed_at\":\"2020-12-18T21:21:09+00:00\",\"metadata\":{\"is_raw_food\":false},\"source\":1,\"ndb_no\":13953,\"tags\":{\"item\":\"bottom sirloin\",\"measure\":\"ounces\",\"quantity\":\"8.0\",\"food_group\":2,\"tag_id\":2755},\"alt_measures\":[{\"serving_weight\":85,\"measure\":\"oz, cooked\",\"seq\":80,\"qty\":3},{\"serving_weight\":569,\"measure\":\"roast (yield from 690g raw meat)\",\"seq\":1,\"qty\":1},{\"serving_weight\":100,\"measure\":\"g\",\"seq\":null,\"qty\":100},{\"serving_weight\":28.3495,\"measure\":\"wt. oz\",\"seq\":null,\"qty\":1}],\"lat\":null,\"lng\":null,\"meal_type\":3,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"sub_recipe\":null}]}\n";
        FoodWrapper food = (FoodWrapper) jsonToFood(constant, 1);
        System.out.println(food.toString());
        food.createFood();
        System.out.println(food.getFood());
    }

    @Test
    public void instantTest() throws IOException, InterruptedException {
//        String url = "https://trackapi.nutritionix.com/v2/search/instant";
//        JSONObject obj = new JSONObject();
//        obj.put("query", "big");
//        String result = search(searchBuilder.createPOSTRequest(url, obj));
        String constant = "{\"common\":[{\"food_name\":\"bigilla\",\"serving_unit\":\"tablespoon\",\"tag_name\":\"bigilla\",\"serving_qty\":1,\"common_type\":null,\"tag_id\":\"15319\",\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"locale\":\"en_US\"},{\"food_name\":\"big red\",\"serving_unit\":\"stick\",\"tag_name\":\"big red gum\",\"serving_qty\":1,\"common_type\":null,\"tag_id\":\"5092\",\"photo\":{\"thumb\":\"https://nix-tag-images.s3.amazonaws.com/5092_thumb.jpg\"},\"locale\":\"en_US\"},{\"food_name\":\"big kat\",\"serving_unit\":\"bar 1.94 oz\",\"tag_name\":\"hershey's kit kat big kat bar\",\"serving_qty\":1,\"common_type\":2,\"tag_id\":\"6741\",\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"locale\":\"en_US\"},{\"food_name\":\"big mac\",\"serving_unit\":\"burger\",\"tag_name\":\"Mcdonald's big mac\",\"serving_qty\":1,\"common_type\":1,\"tag_id\":\"2583\",\"photo\":{\"thumb\":\"https://nix-tag-images.s3.amazonaws.com/2583_thumb.jpg\"},\"locale\":\"en_US\"},{\"food_name\":\"big gulp\",\"serving_unit\":\"Big Gulp (30 oz)\",\"tag_name\":\"big gulp\",\"serving_qty\":1,\"common_type\":null,\"tag_id\":\"6209\",\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"locale\":\"en_US\"},{\"food_name\":\"big tasty\",\"serving_unit\":\"burger\",\"tag_name\":\"mcdonald's big n tasty\",\"serving_qty\":1,\"common_type\":null,\"tag_id\":\"3671\",\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"locale\":\"en_US\"},{\"food_name\":\"big bagel\",\"serving_unit\":\"large bagel (4-1/2\\\")\",\"tag_name\":\"restaurant bagel\",\"serving_qty\":1,\"common_type\":null,\"tag_id\":\"3018\",\"photo\":{\"thumb\":\"https://nix-tag-images.s3.amazonaws.com/3018_thumb.jpg\"},\"locale\":\"en_US\"},{\"food_name\":\"big kat bar\",\"serving_unit\":\"bar 1.94 oz\",\"tag_name\":\"hershey's kit kat big kat bar\",\"serving_qty\":1,\"common_type\":2,\"tag_id\":\"6741\",\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"locale\":\"en_US\"},{\"food_name\":\"big n tasty\",\"serving_unit\":\"burger\",\"tag_name\":\"mcdonald's big n tasty\",\"serving_qty\":1,\"common_type\":null,\"tag_id\":\"3671\",\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"locale\":\"en_US\"},{\"food_name\":\"big red cola\",\"serving_unit\":\"can or bottle (12 fl oz)\",\"tag_name\":\"big red soda\",\"serving_qty\":1,\"common_type\":null,\"tag_id\":\"5091\",\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"locale\":\"en_US\"},{\"food_name\":\"big mac meal\",\"serving_unit\":\"meal\",\"tag_name\":\"big mac meal\",\"serving_qty\":1,\"common_type\":null,\"tag_id\":\"3361\",\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"locale\":\"en_US\"},{\"food_name\":\"big mac sauce\",\"serving_unit\":\"tbsp\",\"tag_name\":\"big mac sauce\",\"serving_qty\":1,\"common_type\":null,\"tag_id\":\"7210\",\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"locale\":\"en_US\"},{\"food_name\":\"reese big cup\",\"serving_unit\":\"cup\",\"tag_name\":\"reeses big cup\",\"serving_qty\":1,\"common_type\":2,\"tag_id\":\"4572\",\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"locale\":\"en_US\"},{\"food_name\":\"reeses big cup\",\"serving_unit\":\"cup\",\"tag_name\":\"reeses big cup\",\"serving_qty\":1,\"common_type\":2,\"tag_id\":\"4572\",\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"locale\":\"en_US\"},{\"food_name\":\"kit kat big kat\",\"serving_unit\":\"bar 1.94 oz\",\"tag_name\":\"hershey's kit kat big kat bar\",\"serving_qty\":1,\"common_type\":2,\"tag_id\":\"6741\",\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"locale\":\"en_US\"},{\"food_name\":\"peanut butter big cup\",\"serving_unit\":\"cup\",\"tag_name\":\"reeses big cup\",\"serving_qty\":1,\"common_type\":2,\"tag_id\":\"4572\",\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"locale\":\"en_US\"},{\"food_name\":\"mcdonalds big mac meal\",\"serving_unit\":\"meal\",\"tag_name\":\"big mac meal\",\"serving_qty\":1,\"common_type\":null,\"tag_id\":\"3361\",\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"locale\":\"en_US\"},{\"food_name\":\"mcdonald big breakfast\",\"serving_unit\":\"item 9.5 oz\",\"tag_name\":\"mcdonald's big breakfast\",\"serving_qty\":1,\"common_type\":null,\"tag_id\":\"6230\",\"photo\":{\"thumb\":\"https://nix-tag-images.s3.amazonaws.com/6230_thumb.jpg\"},\"locale\":\"en_US\"},{\"food_name\":\"mcdonalds big breakfast\",\"serving_unit\":\"item 9.5 oz\",\"tag_name\":\"mcdonald's big breakfast\",\"serving_qty\":1,\"common_type\":null,\"tag_id\":\"6230\",\"photo\":{\"thumb\":\"https://nix-tag-images.s3.amazonaws.com/6230_thumb.jpg\"},\"locale\":\"en_US\"},{\"food_name\":\"big breakfast mcdonalds\",\"serving_unit\":\"item 9.5 oz\",\"tag_name\":\"mcdonald's big breakfast\",\"serving_qty\":1,\"common_type\":null,\"tag_id\":\"6230\",\"photo\":{\"thumb\":\"https://nix-tag-images.s3.amazonaws.com/6230_thumb.jpg\"},\"locale\":\"en_US\"}],\"branded\":[{\"food_name\":\"Bigger Spud Burger\",\"serving_unit\":\"Serving\",\"nix_brand_id\":\"59c569200730b969529d56fe\",\"brand_name_item_name\":\"Teddy's Bigger Burgers Bigger Spud Burger\",\"serving_qty\":1,\"nf_calories\":1080,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Teddy's Bigger Burgers\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"c6406218cc2b161f16da3300\",\"locale\":\"en_US\"},{\"food_name\":\"Bigger Teri Burger\",\"serving_unit\":\"Serving\",\"nix_brand_id\":\"59c569200730b969529d56fe\",\"brand_name_item_name\":\"Teddy's Bigger Burgers Bigger Teri Burger\",\"serving_qty\":1,\"nf_calories\":840,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Teddy's Bigger Burgers\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"c6406218ef5b2103e19a76df\",\"locale\":\"en_US\"},{\"food_name\":\"Biggest Spud Burger\",\"serving_unit\":\"Serving\",\"nix_brand_id\":\"59c569200730b969529d56fe\",\"brand_name_item_name\":\"Teddy's Bigger Burgers Biggest Spud Burger\",\"serving_qty\":1,\"nf_calories\":1500,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Teddy's Bigger Burgers\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"c64062180dc1bd28003a194c\",\"locale\":\"en_US\"},{\"food_name\":\"Burger, Big Boy\",\"serving_unit\":\"Serving\",\"nix_brand_id\":\"5a13469ba9c7a62a5c9042b9\",\"brand_name_item_name\":\"Frisch's Big Boy Burger, Big Boy\",\"serving_qty\":1,\"nf_calories\":720,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Frisch's Big Boy\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"c6405087b4ed850abfe6993a\",\"locale\":\"en_US\"},{\"food_name\":\"Original Bigger Burger\",\"serving_unit\":\"Serving\",\"nix_brand_id\":\"59c569200730b969529d56fe\",\"brand_name_item_name\":\"Teddy's Bigger Burgers Original Bigger Burger\",\"serving_qty\":1,\"nf_calories\":850,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Teddy's Bigger Burgers\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"c640621866678d09d81c2b7f\",\"locale\":\"en_US\"},{\"food_name\":\"Big Big Gummy Bear\",\"serving_unit\":\"pc\",\"nix_brand_id\":\"5e1ac57cc1c9dbfd5649597f\",\"brand_name_item_name\":\"Novelty Specialties Big Big Gummy Bear\",\"serving_qty\":0.20000000298023224,\"nf_calories\":240,\"photo\":{\"thumb\":\"https://nutritionix-api.s3.amazonaws.com/5e1ac57ec1c9dbfd56495980.jpeg\"},\"brand_name\":\"Novelty Specialties\",\"region\":1,\"brand_type\":2,\"nix_item_id\":\"5e1ac57cc1c9dbfd5649597e\",\"locale\":\"en_US\"},{\"food_name\":\"Big Spud Burger\",\"serving_unit\":\"Serving\",\"nix_brand_id\":\"59c569200730b969529d56fe\",\"brand_name_item_name\":\"Teddy's Bigger Burgers Big Spud Burger\",\"serving_qty\":1,\"nf_calories\":920,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Teddy's Bigger Burgers\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"c640621802d3a86cf754695f\",\"locale\":\"en_US\"},{\"food_name\":\"Big Teri Burger\",\"serving_unit\":\"Serving\",\"nix_brand_id\":\"59c569200730b969529d56fe\",\"brand_name_item_name\":\"Teddy's Bigger Burgers Big Teri Burger\",\"serving_qty\":1,\"nf_calories\":680,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Teddy's Bigger Burgers\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"c6406218cbc1d63b43b9ba3d\",\"locale\":\"en_US\"},{\"food_name\":\"BigFoot on the Beach\",\"serving_unit\":\"oz\",\"nix_brand_id\":\"521b95474a56d006cae2a0c7\",\"brand_name_item_name\":\"Bigfoot Java BigFoot on the Beach\",\"serving_qty\":12,\"nf_calories\":330,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Bigfoot Java\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"53444fa7cd5bb303a94c4bba\",\"locale\":\"en_US\"},{\"food_name\":\"Biggest Teri Burger\",\"serving_unit\":\"Serving\",\"nix_brand_id\":\"59c569200730b969529d56fe\",\"brand_name_item_name\":\"Teddy's Bigger Burgers Biggest Teri Burger\",\"serving_qty\":1,\"nf_calories\":1250,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Teddy's Bigger Burgers\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"c640621899f2efd65cef4b15\",\"locale\":\"en_US\"},{\"food_name\":\"Original Biggest Burger\",\"serving_unit\":\"Serving\",\"nix_brand_id\":\"59c569200730b969529d56fe\",\"brand_name_item_name\":\"Teddy's Bigger Burgers Original Biggest Burger\",\"serving_qty\":1,\"nf_calories\":1270,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Teddy's Bigger Burgers\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"c6406218055632e4925a7c7a\",\"locale\":\"en_US\"},{\"food_name\":\"Burger, Super Big Boy\",\"serving_unit\":\"Serving\",\"nix_brand_id\":\"5a13469ba9c7a62a5c9042b9\",\"brand_name_item_name\":\"Frisch's Big Boy Burger, Super Big Boy\",\"serving_qty\":1,\"nf_calories\":1260,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Frisch's Big Boy\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"c64050870d262ee070e9ee2d\",\"locale\":\"en_US\"},{\"food_name\":\"Big Red, Big Q\",\"serving_unit\":\"fl oz\",\"nix_brand_id\":\"5c0eea7b7ab00c11149d0c8e\",\"brand_name_item_name\":\"QuikTrip Big Red, Big Q\",\"serving_qty\":32,\"nf_calories\":430,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"QuikTrip\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"15218721acf1c58a4564342d\",\"locale\":\"en_US\"},{\"food_name\":\"Bigfoot Extreme Chocolate\",\"serving_unit\":\"oz\",\"nix_brand_id\":\"521b95474a56d006cae2a0c7\",\"brand_name_item_name\":\"Bigfoot Java Bigfoot Extreme Chocolate\",\"serving_qty\":12,\"nf_calories\":230,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Bigfoot Java\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"53444fa7bbe33514459809f5\",\"locale\":\"en_US\"},{\"food_name\":\"Original Big Burger\",\"serving_unit\":\"Serving\",\"nix_brand_id\":\"59c569200730b969529d56fe\",\"brand_name_item_name\":\"Teddy's Bigger Burgers Original Big Burger\",\"serving_qty\":1,\"nf_calories\":690,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Teddy's Bigger Burgers\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"c6406218d798249b62a33211\",\"locale\":\"en_US\"},{\"food_name\":\"Mini Big Boy Sundae Chocolate\",\"serving_unit\":\"Serving\",\"nix_brand_id\":\"5a13469ba9c7a62a5c9042b9\",\"brand_name_item_name\":\"Frisch's Big Boy Mini Big Boy Sundae Chocolate\",\"serving_qty\":1,\"nf_calories\":240,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Frisch's Big Boy\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"15219505ab76bf96bb0c0daa\",\"locale\":\"en_US\"},{\"food_name\":\"Big Apple Club, 1 order\",\"serving_unit\":\"Serving\",\"nix_brand_id\":\"521b95434a56d006cae29808\",\"brand_name_item_name\":\"Big Apple Bagels Big Apple Club, 1 order\",\"serving_qty\":1,\"nf_calories\":270,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Big Apple Bagels\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"53444d047ba4ca15456f568b\",\"locale\":\"en_US\"},{\"food_name\":\"Mini Big Boy Sundae Strawberry\",\"serving_unit\":\"Serving\",\"nix_brand_id\":\"5a13469ba9c7a62a5c9042b9\",\"brand_name_item_name\":\"Frisch's Big Boy Mini Big Boy Sundae Strawberry\",\"serving_qty\":1,\"nf_calories\":260,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Frisch's Big Boy\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"1521950534dbbc04f30ecabf\",\"locale\":\"en_US\"},{\"food_name\":\"Mini Big Boy Sundae Hot Fudge\",\"serving_unit\":\"Serving\",\"nix_brand_id\":\"5a13469ba9c7a62a5c9042b9\",\"brand_name_item_name\":\"Frisch's Big Boy Mini Big Boy Sundae Hot Fudge\",\"serving_qty\":1,\"nf_calories\":300,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Frisch's Big Boy\",\"region\":1,\"brand_type\":1,\"nix_item_id\":\"15219505a4d806160713f5f4\",\"locale\":\"en_US\"},{\"food_name\":\"Biscuits, Big, Homestyle Buttermilk\",\"serving_unit\":\"biscuit\",\"nix_brand_id\":\"51db37b2176fe9790a898532\",\"brand_name_item_name\":\"Pillsbury Biscuits, Big, Homestyle Buttermilk\",\"serving_qty\":1,\"nf_calories\":180,\"photo\":{\"thumb\":\"https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png\",\"highres\":null,\"is_user_uploaded\":false},\"brand_name\":\"Pillsbury\",\"region\":1,\"brand_type\":2,\"nix_item_id\":\"51c3633c97c3e69de4b0377c\",\"locale\":\"en_US\"}]}\n";
        InstantFoodWrapper food = (InstantFoodWrapper) jsonToFood(constant, 2);
        System.out.println(food.toString());
        food.createFoods();
        Food[] foods = food.getFoods();
        for (Food f : foods) {
            System.out.println(f.toString());
        }
    }

    @Test
    public void UPCTest() throws IOException, InterruptedException {
//        String upcString = "https://trackapi.nutritionix.com/v2/search/item?upc=851045005013";
//        String result = search(searchBuilder.createGETRequest(upcString));
        String constant = "{\"foods\":[{\"food_name\":\"Beef Jerky, Chipotle Adobo\",\"brand_name\":\"Three Jerks\",\"serving_qty\":1,\"serving_unit\":\"oz\",\"serving_weight_grams\":28,\"nf_calories\":100,\"nf_total_fat\":3,\"nf_saturated_fat\":1,\"nf_cholesterol\":30,\"nf_sodium\":350,\"nf_total_carbohydrate\":8,\"nf_dietary_fiber\":1,\"nf_sugars\":6,\"nf_protein\":10,\"nf_potassium\":null,\"nf_p\":null,\"full_nutrients\":[{\"attr_id\":203,\"value\":10},{\"attr_id\":204,\"value\":3},{\"attr_id\":205,\"value\":8},{\"attr_id\":208,\"value\":100},{\"attr_id\":269,\"value\":6},{\"attr_id\":291,\"value\":1},{\"attr_id\":301,\"value\":26},{\"attr_id\":303,\"value\":1.44},{\"attr_id\":307,\"value\":350},{\"attr_id\":318,\"value\":300},{\"attr_id\":401,\"value\":0},{\"attr_id\":601,\"value\":30},{\"attr_id\":605,\"value\":0},{\"attr_id\":606,\"value\":1}],\"nix_brand_name\":\"Three Jerks\",\"nix_brand_id\":\"551af50449bbebc5780a61b0\",\"nix_item_name\":\"Beef Jerky, Chipotle Adobo\",\"nix_item_id\":\"5556515436f95593518aa94e\",\"metadata\":{},\"source\":8,\"ndb_no\":null,\"tags\":null,\"alt_measures\":null,\"lat\":null,\"lng\":null,\"photo\":{\"thumb\":\"https://nutritionix-api.s3.amazonaws.com/555652c40761f2ce5d7e076d.jpeg\",\"highres\":null,\"is_user_uploaded\":false},\"note\":null,\"updated_at\":\"2019-01-27T10:39:22+00:00\",\"nf_ingredient_statement\":\"Beef, Water, Sugar, Less than 2% Salt, Corn Syrup Solids, Dried Soy Sauce (Soybeans, Salt, Wheat), Hydrolyzed Corn and Soy Protein, Monosodium Glutamate, Maltodextrin, Flavorings, Sodium Erythorbate, Sodium Nitrite.\"}]}";
        FoodWrapper food = (FoodWrapper) jsonToFood(constant, 1);
        System.out.println(food.toString());
        food.createFood();
        System.out.println(food.getFood());
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
