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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="exercise_id", nullable = false)
    private Exercise exercise;
}
