package zmacadam.metrics.model.nutrition;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "food_id")
    private int id;
    @SerializedName(value = "serving_qty")
    @Column(name = "serving_qty")
    private String servingQty;
    @SerializedName(value = "serving_unit")
    @Column(name = "serving_unit")
    private String servingUnit;
    @SerializedName(value = "search_query")
    @Column(name = "search_query")
    private String searchQuery;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="meal_id", nullable = false)
    private Meal meal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="description_id")
    private FoodDescription foodDescription;

    @Transient
    public double multValue(String nutrient) throws NoSuchFieldException, IllegalAccessException {
        Field field = foodDescription.getClass().getDeclaredField(nutrient);
        field.setAccessible(true);
        String value = (String) field.get(foodDescription);
        field.setAccessible(false);
        return Math.round(Double.parseDouble(servingQty) * Double.parseDouble(value));
    }

    @Override
    public String toString() {
        return servingQty + " , " + servingUnit;
    }
}
