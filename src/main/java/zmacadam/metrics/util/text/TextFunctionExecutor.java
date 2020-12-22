package zmacadam.metrics.util.text;

import zmacadam.metrics.model.Day;
import zmacadam.metrics.model.user.User;

import java.util.List;

public interface TextFunctionExecutor {
     String execute(String[] body);
     String execute(String[] body, User user);
     String execute(String identifier, String[] body, User user);
     List<Day> findDayByUser(User user);
     Day saveDay(Day day);
}
