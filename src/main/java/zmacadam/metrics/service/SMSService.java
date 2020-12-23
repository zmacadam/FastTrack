package zmacadam.metrics.service;


import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zmacadam.metrics.model.user.User;
import zmacadam.metrics.repository.UserRepository;
import zmacadam.metrics.util.text.FunctionContext;
import zmacadam.metrics.util.text.RegisterFunctions;
import zmacadam.metrics.util.text.WorkoutFunctionImpl;

@Service
public class SMSService {

    private UserRepository userRepository;
    private FunctionContext functionContext;
    private static Logger logger = LoggerFactory.getLogger(SMSService.class);


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
        if (command.length > 1) {
            if (command[0].equals("begin") || command[0].equals("end")) {
                if (command[1].equals("workout")) {
                    return functionContext.call(command[1], command[0], commandRemoved, user);
                }
                return functionContext.call("activity", command[1], command[0], commandRemoved, user);
            }
            return functionContext.call(command[0], command[1], commandRemoved, user);
        } else {
            return functionContext.call(command[0], commandRemoved, user);
        }
    }
}
