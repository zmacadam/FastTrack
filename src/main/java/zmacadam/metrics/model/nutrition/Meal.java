package zmacadam.metrics.model.nutrition;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zmacadam.metrics.model.Day;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    public String getTime() {
        String pattern = "hh:mm a";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(time);
    }

    public double getTotal(String nutrient) throws NoSuchFieldException, IllegalAccessException {
        double total = 0;
        for (Food food : foods) {
            FoodDescription foodDescription = food.getFoodDescription();
            Field field = foodDescription.getClass().getDeclaredField(nutrient);
            field.setAccessible(true);
            String value = (String) field.get(foodDescription);
            field.setAccessible(false);
            total += Math.round(Double.parseDouble(food.getServingQty()) * Double.parseDouble(value));
        }
        return total;
    }
}
