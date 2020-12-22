package zmacadam.metrics.util.text;

import org.springframework.stereotype.Component;
import zmacadam.metrics.model.user.User;

import java.util.HashMap;

@Component
public class FunctionContext {
    HashMap<String, TextFunctionExecutor> context = new HashMap<>();

    public void register(String name, TextFunctionExecutor function) {
        context.put(name, function);
    }

    public String call(String name, String[] body) { return context.get(name).execute(body); }

    public String call(String name, String[] body, User user) {
        return context.get(name).execute(body, user);
    }

    public String call(String name, String identifier, String[] body, User user) { return context.get(name).execute(identifier, body, user); }

    public TextFunctionExecutor get (String name) {
        return context.get(name);
    }
}
