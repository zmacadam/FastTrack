package zmacadam.metrics.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food_item")
public class Food {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "food_id")
    private int id;
    @SerializedName(value = "nf_calories")
    @Column(name = "calories")
    private String Calories;
    @SerializedName(value = "nf_total_fat")
    @Column(name = "total_fat")
    private String TotalFat;
    @SerializedName(value = "nf_saturated_fat")
    @Column(name = "saturated_fat")
    private String SaturatedFat;
    @SerializedName(value = "nf_cholesterol")
    @Column(name = "cholesterol")
    private String Cholesterol;
    @SerializedName(value = "nf_sodium")
    @Column(name = "sodium")
    private String Sodium;
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
    @Column(name = "unit")
    private String unit;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="meal_id", nullable = false)
    private Meal meal;


    @Override public String toString() {
        return Calories + " , " + TotalFat + " , " + SaturatedFat + " , "
                + Cholesterol+ " , " + Sodium + " , " + totalCarbohydrate + " , " + dietaryFiber+ " , " + sugars + " , " + protein + " , " + unit;
    }
}
