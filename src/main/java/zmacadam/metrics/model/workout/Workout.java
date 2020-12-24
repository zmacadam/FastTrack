package zmacadam.metrics.model.workout;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zmacadam.metrics.model.Day;
import zmacadam.metrics.model.nutrition.Food;
import zmacadam.metrics.model.nutrition.FoodDescription;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "workout")
public class Workout {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private int id;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name = "workout_notes")
    private String notes;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<ExerciseDescription> exerciseDescriptions = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "day_id")
    private Day day;

    public void addExerciseDescription(ExerciseDescription exerciseDescription) {
        this.exerciseDescriptions.add(exerciseDescription);
        exerciseDescription.setWorkout(this);
    }

}
