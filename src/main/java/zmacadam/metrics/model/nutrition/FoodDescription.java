package zmacadam.metrics.model.nutrition;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "foodDescription", cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();

    public void addFood(Food food) {
        this.foods.add(food);
        food.setFoodDescription(this);
    }

    @Override
    public String toString() {
        return servingQty + " , " + servingUnit;
    }
}
