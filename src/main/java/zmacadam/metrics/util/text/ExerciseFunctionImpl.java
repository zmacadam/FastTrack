package zmacadam.metrics.util.text;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zmacadam.metrics.model.Day;
import zmacadam.metrics.model.user.User;
import zmacadam.metrics.model.workout.Exercise;
import zmacadam.metrics.model.workout.ExerciseDescription;
import zmacadam.metrics.model.workout.Workout;
import zmacadam.metrics.repository.ExerciseRepository;
import zmacadam.metrics.service.DayDetailsService;

import java.util.List;

@Component
public class ExerciseFunctionImpl extends AbstractFunctionExecutor {

    private final DayDetailsService dayDetailsService;
    private final ExerciseRepository exerciseRepository;

    private static Logger logger = LoggerFactory.getLogger(ExerciseFunctionImpl.class);

    @Autowired
    public ExerciseFunctionImpl(DayDetailsService dayDetailsService,
                                ExerciseRepository exerciseRepository) {
        super(dayDetailsService);
        this.dayDetailsService = dayDetailsService;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public String execute(String identifier, String[] body, User user) {
        logger.info(String.valueOf(body.length));
        Day day = retrieveDay(user);
        List<Workout> workouts = day.getWorkouts();
        Workout currentWorkout = null;
        for (Workout workout : workouts) {
            if (workout.getEndTime() == null) {
                currentWorkout = workout;
                break;
            }
        }
        if (currentWorkout == null) {
            currentWorkout = createWorkout();
            day.addWorkout(currentWorkout);
        }
        if (body.length > 1) {
            addExerciseAndDescription(currentWorkout, body, identifier);
            saveDay(day);
            return "Superset added";
        }
        addExerciseAndDescription(currentWorkout, body, identifier);
        saveDay(day);
        return "Exercise added";
    }

    public void addExerciseAndDescription(Workout workout, String[] body, String identifier) {
        for (String line : body) {
            String[] exerciseValues = line.split(" ");
            int valuesLength = exerciseValues.length;
            String exerciseName = String.join(" ", ArrayUtils.removeAll(exerciseValues, valuesLength -1, valuesLength -2));
            Exercise exercise = exerciseRepository.findByExerciseName(exerciseName);
            if (exercise == null) {
                exercise = new Exercise();
                exercise.setExerciseName(exerciseName);
            }
            ExerciseDescription exerciseDescription = new ExerciseDescription();
            exerciseDescription.setSets(Integer.parseInt(exerciseValues[valuesLength - 2]));
            exerciseDescription.setReps(Integer.parseInt(exerciseValues[valuesLength - 1]));
            exerciseDescription.setExerciseNumber(Integer.parseInt(identifier));
            exerciseDescription.addExercise(exercise);
            workout.addExerciseDescription(exerciseDescription);
            return;
        }
    }
}
