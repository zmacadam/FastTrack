package zmacadam.metrics.util.text;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zmacadam.metrics.model.Day;
import zmacadam.metrics.model.user.User;
import zmacadam.metrics.model.workout.ExerciseDescription;
import zmacadam.metrics.model.workout.Exercise;
import zmacadam.metrics.model.workout.SetDescription;
import zmacadam.metrics.model.workout.Workout;
import zmacadam.metrics.repository.ExerciseRepository;
import zmacadam.metrics.service.DayDetailsService;

import java.util.ArrayList;
import java.util.Arrays;
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
            String exerciseName = String.join(" ", ArrayUtils.removeAll(exerciseValues, valuesLength -1, valuesLength -2, valuesLength -3));
            ExerciseDescription exerciseDescription = exerciseRepository.findByExerciseName(exerciseName);
            if (exerciseDescription == null) {
                exerciseDescription = new ExerciseDescription();
                exerciseDescription.setExerciseName(exerciseName);
            }
            logger.info(exerciseDescription.getExerciseName());
            Exercise exercise = new Exercise();
            exercise.setSets(Integer.parseInt(exerciseValues[valuesLength - 3]));
            exercise.setReps(exerciseValues[valuesLength - 2]);
            exercise.setWeight(exerciseValues[valuesLength - 1]);
            exercise.setExerciseNumber(Integer.parseInt(identifier));
            List reps;
            List weights;
            if (exercise.getReps().contains(",")) {
                reps = Arrays.asList(exercise.getReps().split(","));
            } else {
                reps = new ArrayList<>();
                for (int i = 0; i < exercise.getSets(); i++) {
                    reps.add(Integer.parseInt(exercise.getReps()));
                }
            }
            if (exercise.getWeight().contains(",")) {
                weights = Arrays.asList(exercise.getWeight().split(","));
            } else {
                weights = new ArrayList<>();
                for (int i = 0; i < exercise.getSets(); i++) {
                    weights.add(Integer.parseInt(exercise.getWeight()));
                }
            }
            for (int i = 0; i < exercise.getSets(); i++) {
                SetDescription setDescription = new SetDescription();
                setDescription.setSetNumber(i);
                setDescription.setReps((int) reps.get(i));
                setDescription.setWeight((int) weights.get(i));
                exercise.addSetDescription(setDescription);
            }
            exerciseDescription.addExercise(exercise);
            workout.addExercise(exercise);
            return;
        }
    }
}
