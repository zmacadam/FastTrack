package zmacadam.metrics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "day")
public class Day {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "day_id")
    private int id;

    @Column(name = "date")
    private Date date;

    @OneToMany(mappedBy = "day", cascade = CascadeType.PERSIST)
    private List<Meal> meals = new ArrayList<>();

    public void addMeal(Meal meal) {
        this.meals.add(meal);
        meal.setDay(this);
    }

}
