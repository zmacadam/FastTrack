package zmacadam.metrics.util.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zmacadam.metrics.controller.SMSController;
import zmacadam.metrics.model.Day;
import zmacadam.metrics.model.User;
import zmacadam.metrics.service.DayDetailsService;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Component
public class SleepFunctionImpl extends AbstractFunctionExecutor {

    private final DayDetailsService dayDetailsService;

    private static Logger logger = LoggerFactory.getLogger(SMSController.class);

    @Autowired
    public SleepFunctionImpl(DayDetailsService dayDetailsService) {
        super(dayDetailsService);
        this.dayDetailsService = dayDetailsService;
    }

    @Override
    public String execute(String[] body, User user) {
        logger.info("Sleep execute");
        logger.info(user.getPhoneNumber());
        List<Day> result = findDayByUser(user);
        logger.info(result.toString());
        logger.info(String.valueOf(result.size()));
        Day day = result.get(0);
        day.setSleepTime(new Time(System.currentTimeMillis()));
        saveDay(day);
        return "Sleep successfully executed";
    }
}
