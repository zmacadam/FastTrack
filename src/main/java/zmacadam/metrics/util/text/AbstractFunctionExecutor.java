package zmacadam.metrics.util.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zmacadam.metrics.model.Day;
import zmacadam.metrics.model.user.User;
import zmacadam.metrics.service.DayDetailsService;

import java.sql.Date;
import java.util.List;

public abstract class AbstractFunctionExecutor implements TextFunctionExecutor {

    private DayDetailsService dayDetailsService;
    private static Logger logger = LoggerFactory.getLogger(AbstractFunctionExecutor.class);

    public AbstractFunctionExecutor(DayDetailsService dayDetailsService) {
        this.dayDetailsService = dayDetailsService;
    }

    @Override
    public String execute(String[] body) { return null; }
    public String execute(String[] body, User user) { return null; }
    public String execute(String identifier, String[] body, User user) { return null; }
    public String execute(String type, String identifier, String[] body, User user) { return null; }

    public List<Day> findDayByUser(User user) {
       return dayDetailsService.findByDateAndPhoneNumber(new Date(System.currentTimeMillis()), user.getPhoneNumber());
    }

    public Day saveDay(Day day) {
        return dayDetailsService.saveDay(day);
    }

    public Day retrieveDay(User user) {
        List<Day> result = findDayByUser(user);
        Day day;
        if (result.size() == 0) {
            day = new Day();
            day.setDate(new Date(System.currentTimeMillis()));
            day.setPhoneNumber(user.getPhoneNumber());
            saveDay(day);
        } else {
            day = result.get(0);
        }
        return day;
    }
}
