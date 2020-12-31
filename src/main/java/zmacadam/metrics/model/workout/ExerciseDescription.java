package zmacadam.metrics.model.workout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exercise_desc")
public class ExerciseDescription {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "description_id")
    private int id;

    @Column(name = "exercise_name")
    private String exerciseName;

    @OneToMany(mappedBy = "exerciseDescription", cascade = CascadeType.ALL)
    private List<Exercise> exercises = new ArrayList<>();

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
        exercise.setExerciseDescription(this);
    }

}
