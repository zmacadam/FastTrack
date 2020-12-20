package zmacadam.metrics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "day")
public class Day {

    @Id
    @Column(name = "day_id")
    private int id;

    @Column(name = "date")
    private Timestamp date;

    @OneToMany(mappedBy = "day")
    private List<Meal> meals;

    public void addMeal(Meal meal) {
        this.meals
    }

}
