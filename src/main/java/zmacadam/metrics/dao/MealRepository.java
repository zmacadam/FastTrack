package zmacadam.metrics.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zmacadam.metrics.model.Meal;

public interface MealRepository extends JpaRepository<Meal, Integer> {
    Meal findById(int id);
}
