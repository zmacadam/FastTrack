package zmacadam.metrics.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Array;
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

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private List<FoodDescription> descriptions = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "day_id")
    private Day day;

    public void addFood(Food food) {
        this.foods.add(food);
        food.setMeal(this);
    }

    public void addFoodDescription(FoodDescription foodDescription) {
        this.descriptions.add(foodDescription);
        foodDescription.setMeal(this);
    }
}
