package zmacadam.metrics.model.nutrition;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zmacadam.metrics.model.Day;
import zmacadam.metrics.model.nutrition.Food;
import zmacadam.metrics.model.nutrition.FoodDescription;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "meal")
public class Meal {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private int id;

    @Column(name = "meal_number")
    private int mealNumber;

    @Column(name = "meal_time")
    private Time time;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "day_id")
    private Day day;

    public void addFood(Food food) {
        this.foods.add(food);
        food.setMeal(this);
    }
}
