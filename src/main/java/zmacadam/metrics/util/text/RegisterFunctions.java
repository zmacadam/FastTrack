package zmacadam.metrics.util.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

    @PostConstruct
    private void initFunctions() {
        functionContext.register("meal", new MealFunctionImpl(dayDetailsService, searchBuilder));
        functionContext.register("awake", new AwakeFunctionImpl(dayDetailsService));
        functionContext.register("sleep", new SleepFunctionImpl(dayDetailsService));
    }

    public FunctionContext getFunctionContext() {
        return functionContext;
    }
}
