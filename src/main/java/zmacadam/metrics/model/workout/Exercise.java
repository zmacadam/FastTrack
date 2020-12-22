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
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private int id;

    @Column(name = "exercise_name")
    private String exerciseName;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<ExerciseDescription> descriptions = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="workout_id", nullable = false)
    private Workout workout;

    public void addExerciseDescription(ExerciseDescription exerciseDescription) {
        this.descriptions.add(exerciseDescription);
        exerciseDescription.setExercise(this);
    }
}
