package zmacadam.metrics.model.nutrition;


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
    @Column(name = "description_id")
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
    @SerializedName(value = "search_query")
    @Column(name = "search_query")
    private String searchQuery;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @Override
    public String toString() {
        return foodName + " , " + brandName + " , " + servingQty + " , " + servingUnit;
    }
}
