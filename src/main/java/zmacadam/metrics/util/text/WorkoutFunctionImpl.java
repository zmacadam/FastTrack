package zmacadam.metrics.util.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zmacadam.metrics.model.Day;
import zmacadam.metrics.model.activity.Activity;
import zmacadam.metrics.model.user.User;
import zmacadam.metrics.model.workout.Workout;
import zmacadam.metrics.service.DayDetailsService;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Component
public class WorkoutFunctionImpl extends AbstractFunctionExecutor {

    private final DayDetailsService dayDetailsService;
    private static Logger logger = LoggerFactory.getLogger(WorkoutFunctionImpl.class);

    @Autowired
    public WorkoutFunctionImpl(DayDetailsService dayDetailsService) {
        super(dayDetailsService);
        this.dayDetailsService = dayDetailsService;
    }

    public String execute(String identifier, String[] body, User user) {
        Day day = retrieveDay(user);
        if (identifier.equals("begin")) {
            Workout workout = new Workout();
            workout.setStartTime(new Time(System.currentTimeMillis()));
            workout.setNotes(String.join("\n", body));
            day.addWorkout(workout);
            saveDay(day);
        }
        if (identifier.equals("end")) {
            List<Workout> workouts = day.getWorkouts();
            for (Workout workout : workouts) {
                if (workout.getEndTime() == null) {
                    workout.setEndTime(new Time(System.currentTimeMillis()));
                    saveDay(day);
                    return "Workout ended";
                }
            }
        }
        return "Workout started";
    }
}
