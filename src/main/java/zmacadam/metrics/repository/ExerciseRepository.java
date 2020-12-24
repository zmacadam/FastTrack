package zmacadam.metrics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zmacadam.metrics.model.workout.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    Exercise findByExerciseName(String exerciseName);
}
