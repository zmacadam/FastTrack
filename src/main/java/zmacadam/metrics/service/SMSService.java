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
        String function = command[0].toLowerCase();
        String identifier = command[1];
        if (lines[0].equals("new user")) {
            return functionContext.call("user", lines);
        }
        User user = userRepository.findByPhoneNumber(from);
        if (user == null) {
            return "User does not exist!";
        }
        String[] commandRemoved = ArrayUtils.remove(lines, 0);
        if (command.length > 1) {
            if (function.equals("begin") || function.equals("end")) {
                if (identifier.equals("workout")) {
                    return functionContext.call(identifier, function, commandRemoved, user);
                }
                return functionContext.call("activity", identifier, function, commandRemoved, user);
            }
            return functionContext.call(function, identifier, commandRemoved, user);
        } else {
            return functionContext.call(function, commandRemoved, user);
        }
    }
}
