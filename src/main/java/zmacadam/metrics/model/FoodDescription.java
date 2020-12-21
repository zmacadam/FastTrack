package zmacadam.metrics.model;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food_desc")
public class FoodDescription {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "food_id")
    private int id;
    @SerializedName(value = "food_name")
    @Column(name = "food_name")
    private String foodName;
    @SerializedName(value = "brand_name")
    @Column(name = "brand_name")
    private String brandName;
    @SerializedName(value = "serving_qty")
    @Column(name = "serving_qty")
    private String servingQty;
    @SerializedName(value = "serving_unit")
    @Column(name = "serving_unit")
    private String servingUnit;
    @ManyToOne
    @JoinColumn(name ="meal_id", nullable = false)
    private Meal meal;

    @Override
    public String toString() {
        return foodName + " , " + brandName + " , " + servingQty + " , " + servingUnit;
    }
}
