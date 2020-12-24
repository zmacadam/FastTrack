package zmacadam.metrics.util.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zmacadam.metrics.repository.ExerciseRepository;
import zmacadam.metrics.repository.FoodRepository;
import zmacadam.metrics.repository.MealRepository;
import zmacadam.metrics.service.DayDetailsService;
import zmacadam.metrics.util.search.SearchBuilder;

import javax.annotation.PostConstruct;

@Component
public class RegisterFunctions {

    @Autowired
    private FunctionContext functionContext;

    @Autowired
    private DayDetailsService dayDetailsService;

    @Autowired
    private SearchBuilder searchBuilder;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private MealRepository mealRepository;

    @PostConstruct
    private void initFunctions() {
        functionContext.register("meal", new MealFunctionImpl(dayDetailsService, searchBuilder, foodRepository, mealRepository));
        functionContext.register("awake", new AwakeFunctionImpl(dayDetailsService));
        functionContext.register("sleep", new SleepFunctionImpl(dayDetailsService));
        functionContext.register("workout", new WorkoutFunctionImpl(dayDetailsService));
        functionContext.register("activity", new ActivityFunctionImpl(dayDetailsService));
        functionContext.register("exercise", new ExerciseFunctionImpl(dayDetailsService, exerciseRepository));
    }

    public FunctionContext getFunctionContext() {
        return functionContext;
    }
}
