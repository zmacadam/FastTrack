package zmacadam.metrics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import zmacadam.metrics.model.Day;
import zmacadam.metrics.model.Food;
import zmacadam.metrics.model.FoodDescription;
import zmacadam.metrics.model.Meal;
import zmacadam.metrics.service.DayDetailsService;
import zmacadam.metrics.service.MyUserDetailsService;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class MetricsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetricsApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(DayDetailsService dayDetailsService) {
        return (args) -> {
            Day day = new Day();
            Meal meal = new Meal();
            Food food = new Food();
            FoodDescription foodDescription = new FoodDescription();
            foodDescription.setId(food.getId());

            day.addMeal(meal);

            meal.addFoodDescription(foodDescription);
            meal.addFood(food);

            dayDetailsService.saveDay(day);
        };
    }
}
