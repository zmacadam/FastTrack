package zmacadam.metrics.model.nutrition;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food_desc")
public class FoodDescription {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "description_id")
    private int id;
    @SerializedName(value = "food_name")
    @Column(name = "food_name")
    private String foodName;
    @SerializedName(value = "brand_name")
    @Column(name = "brand_name")
    private String brandName;
    @SerializedName(value = "nf_calories")
    @Column(name = "calories")
    private String calories;
    @SerializedName(value = "nf_total_fat")
    @Column(name = "total_fat")
    private String totalFat;
    @SerializedName(value = "nf_saturated_fat")
    @Column(name = "saturated_fat")
    private String saturatedFat;
    @SerializedName(value = "nf_cholesterol")
    @Column(name = "cholesterol")
    private String cholesterol;
    @SerializedName(value = "nf_sodium")
    @Column(name = "sodium")
    private String sodium;
    @SerializedName(value = "nf_total_carbohydrate")
    @Column(name = "total_carbohydrate")
    private String totalCarbohydrate;
    @SerializedName(value = "nf_dietary_fiber")
    @Column(name = "dietary_fiber")
    private String dietaryFiber;
    @SerializedName(value = "nf_sugars")
    @Column(name = "sugars")
    private String sugars;
    @SerializedName(value = "nf_protein")
    @Column(name = "protein")
    private String protein;

    @OneToMany(mappedBy = "foodDescription", cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();



    public void addFood(Food food) {
        this.foods.add(food);
        food.setFoodDescription(this);
    }

    @Override public String toString() {
        return calories + " , " + totalFat + " , " + saturatedFat + " , "
                + cholesterol+ " , " + sodium + " , " + totalCarbohydrate + " , " + dietaryFiber+ " , " + sugars + " , " + protein;
    }
}
