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
@Table(name="set_description")
public class SetDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="set_description_id")
    private int id;

    @Column(name = "set_number")
    private int setNumber;

    @Column
    private int reps;

    @Column
    private int weight;

    @ManyToOne
    @JoinColumn(name ="exercise_id", nullable = false)
    private Exercise exercise;

}
