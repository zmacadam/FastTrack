package zmacadam.metrics.util.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zmacadam.metrics.controller.SMSController;
import zmacadam.metrics.model.Day;
import zmacadam.metrics.model.user.User;
import zmacadam.metrics.service.DayDetailsService;

import java.sql.Date;
import java.sql.Time;

@Component
public class AwakeFunctionImpl extends AbstractFunctionExecutor {

    private final DayDetailsService dayDetailsService;

    private static Logger logger = LoggerFactory.getLogger(SMSController.class);

    @Autowired
    public AwakeFunctionImpl(DayDetailsService dayDetailsService) {
        super(dayDetailsService);
        this.dayDetailsService = dayDetailsService;
    }

    @Override
    public String execute(String[] body, User user) {
        logger.info("Awake execute");
        logger.info(user.getPhoneNumber());
        Day day = new Day();
        day.setDate(new Date(System.currentTimeMillis()));
        day.setPhoneNumber(user.getPhoneNumber());
        day.setWakeTime(new Time(System.currentTimeMillis()));
        user.addDay(day);
        saveDay(day);
        return "Awake successfully executed";
    }
}
