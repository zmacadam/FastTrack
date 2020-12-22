package zmacadam.metrics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
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

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date")
    private Date date;

    @Column(name = "wake_time")
    private Time wakeTime;

    @Column(name = "sleep_time")
    private Time sleepTime;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    private List<Meal> meals = new ArrayList<>();

    public void addMeal(Meal meal) {
        this.meals.add(meal);
        meal.setDay(this);
    }

}
