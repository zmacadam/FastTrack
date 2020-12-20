package zmacadam.metrics.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "meal")
public class Meal {

    @Id
    @Column(name = "meal_id")
    private int id;

    @Column(name = "day_id")
    private int dayId;

    @OneToMany(mappedBy = "meal")
    private List<Food> foods;

    @ManyToOne
    @JoinColumn(name = "day_id", nullable = false)
    private Day day;
}
