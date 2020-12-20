package zmacadam.metrics.nutrition;

import com.google.gson.annotations.SerializedName;

public class Food {
    @SerializedName(value = "food_name")
    private String foodName;
    @SerializedName(value = "brand_name")
    private String brandName;
    @SerializedName(value = "serving_qty")
    private String servingQty;
    @SerializedName(value = "serving_unit")
    private String servingUnit;
    @SerializedName(value = "nf_calories")
    private String Calories;
    @SerializedName(value = "nf_total_fat")
    private String TotalFat;
    @SerializedName(value = "nf_saturated_fat")
    private String SaturatedFat;
    @SerializedName(value = "nf_cholesterol")
    private String Cholesterol;
    @SerializedName(value = "nf_sodium")
    private String Sodium;
    @SerializedName(value = "nf_total_carbohydrate")
    private String totalCarbohydrate;
    @SerializedName(value = "nf_dietary_fiber")
    private String dietaryFiber;
    @SerializedName(value = "nf_sugars")
    private String sugars;
    @SerializedName(value = "nf_protein")
    private String protein;

    @Override public String toString() {
        return foodName + " , " + brandName + " , " + servingQty + " , " + servingUnit + " , " + Calories + " , " + TotalFat + " , " + SaturatedFat + " , "
                + Cholesterol+ " , " + Sodium + " , " + totalCarbohydrate + " , " + dietaryFiber+ " , " + sugars + " , " + protein;
    }
}
