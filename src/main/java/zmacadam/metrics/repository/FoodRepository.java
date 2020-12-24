package zmacadam.metrics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zmacadam.metrics.model.nutrition.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    Food findByFoodName(String foodName);
}
