package zmacadam.metrics.service;


import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zmacadam.metrics.model.User;
import zmacadam.metrics.repository.DayRepository;
import zmacadam.metrics.repository.UserRepository;
import zmacadam.metrics.util.search.SearchBuilder;
import zmacadam.metrics.util.text.FunctionContext;
import zmacadam.metrics.util.text.RegisterFunctions;

import java.util.Arrays;

@Service
public class SMSService {

    private UserRepository userRepository;
    private FunctionContext functionContext;

    @Autowired
    public SMSService(UserRepository userRepository,
                      RegisterFunctions registerFunctions) {
        this.userRepository = userRepository;
        this.functionContext = registerFunctions.getFunctionContext();
    }

    public String parseText(String from, String body) {
        String[] lines = body.split("\\r?\\n");
        String[] command = lines[0].split(" ");
        if (lines[0].equals("new user")) {
            return functionContext.call("user", lines);
        }
        User user = userRepository.findByPhoneNumber(from);
        if (user == null) {
            return "User does not exist!";
        }
        String[] commandRemoved = ArrayUtils.remove(lines, 0);
        System.out.println(Arrays.toString(lines));
        if (command.length > 1) {
            return functionContext.call(command[0], command[1], commandRemoved, user);
        } else {
            return functionContext.call(command[0], commandRemoved, user);
        }
    }
}
