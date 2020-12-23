package zmacadam.metrics.model.activity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zmacadam.metrics.model.Day;

import javax.persistence.*;
import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private int id;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "activity_desc")
    private String activityDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "day_id")
    private Day day;

}
