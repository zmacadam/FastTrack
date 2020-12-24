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
    @Column(name = "exercise_description_id")
    private int id;

    @Column(name = "sets")
    private int sets;

    @Column(name = "reps")
    private int reps;

    @Column(name = "exercise_number")
    private int exerciseNumber;

    @OneToMany(mappedBy = "exerciseDescription", cascade = CascadeType.ALL)
    private List<Exercise> exercises = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="workout_id", nullable = false)
    private Workout workout;


    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
        exercise.setExerciseDescription(this);
    }
}
