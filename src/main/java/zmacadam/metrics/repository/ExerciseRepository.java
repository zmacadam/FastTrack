package zmacadam.metrics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zmacadam.metrics.model.workout.ExerciseDescription;

public interface ExerciseRepository extends JpaRepository<ExerciseDescription, Integer> {
    ExerciseDescription findByExerciseName(String exerciseName);
}
