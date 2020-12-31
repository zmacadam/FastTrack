package zmacadam.metrics.model.workout;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "sets")
    private int sets;

    @Column(name = "reps")
    private int reps;

    @Column(name = "exercise_number")
    private int exerciseNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id", nullable = false)
    private ExerciseDescription exerciseDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="workout_id", nullable = false)
    private Workout workout;

    @Override
    public String toString() {
        return sets + " " + reps;
    }

}
