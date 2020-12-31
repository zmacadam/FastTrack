package zmacadam.metrics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zmacadam.metrics.model.nutrition.FoodDescription;

public interface FoodRepository extends JpaRepository<FoodDescription, Integer> {
    FoodDescription findByFoodName(String foodName);
}
