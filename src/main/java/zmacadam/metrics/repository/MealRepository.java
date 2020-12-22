package zmacadam.metrics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zmacadam.metrics.model.nutrition.Meal;

public interface MealRepository extends JpaRepository<Meal, Integer> {
    Meal findById(int id);
}
