package zmacadam.metrics.util.text;

import zmacadam.metrics.model.Day;
import zmacadam.metrics.model.user.User;
import zmacadam.metrics.service.DayDetailsService;

import java.sql.Date;
import java.util.List;

public abstract class AbstractFunctionExecutor implements TextFunctionExecutor {

    private DayDetailsService dayDetailsService;

    public AbstractFunctionExecutor(DayDetailsService dayDetailsService) {
        this.dayDetailsService = dayDetailsService;
    }

    @Override
    public String execute(String[] body) { return null; }
    public String execute(String[] body, User user) { return null; }
    public String execute(String identifier, String[] body, User user) { return null; }

    public List<Day> findDayByUser(User user) {
       return dayDetailsService.findByDateAndPhoneNumber(new Date(System.currentTimeMillis()), user.getPhoneNumber());
    }

    public Day saveDay(Day day) {
        return dayDetailsService.saveDay(day);
    }
}
