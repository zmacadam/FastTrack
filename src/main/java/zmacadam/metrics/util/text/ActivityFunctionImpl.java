package zmacadam.metrics.util.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zmacadam.metrics.model.Day;
import zmacadam.metrics.model.activity.Activity;
import zmacadam.metrics.model.user.User;
import zmacadam.metrics.service.DayDetailsService;

import java.sql.Time;
import java.util.List;

@Component
public class ActivityFunctionImpl extends AbstractFunctionExecutor {

    private DayDetailsService dayDetailsService;
    private static Logger logger = LoggerFactory.getLogger(ActivityFunctionImpl.class);


    @Autowired
    public ActivityFunctionImpl(DayDetailsService dayDetailsService) {
        super(dayDetailsService);
        this.dayDetailsService = dayDetailsService;
    }

    public String execute(String type, String identifier, String[] body, User user) {
        Day day = retrieveDay(user);
        if (identifier.equals("begin")) {
            Activity activity = new Activity();
            activity.setActivityName(type);
            activity.setActivityDescription(String.join("\n", body));
            activity.setStartTime(new Time(System.currentTimeMillis()));
            day.addActivity(activity);
            saveDay(day);
        }
        if (identifier.equals("end")) {
            List<Activity> activities = day.getActivities();
            for (Activity activity : activities) {
                if (activity.getActivityName().equals(type) && activity.getEndTime() == null) {
                    activity.setEndTime(new Time(System.currentTimeMillis()));
                    saveDay(day);
                    return "Activity " + type + " ended";
                }
            }
        }
        return "Activity " + type + " started";
    }
}
