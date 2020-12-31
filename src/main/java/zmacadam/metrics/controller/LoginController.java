package zmacadam.metrics.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import zmacadam.metrics.model.Day;
import zmacadam.metrics.model.user.User;
import zmacadam.metrics.service.DayDetailsService;
import zmacadam.metrics.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private DayDetailsService dayDetailsService;

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        session.setAttribute("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        userExists = userService.findUserByPhoneNumber(user.getPhoneNumber());
        if (userExists != null) {
            bindingResult
                    .rejectValue("phoneNumber", "error.user",
                            "There is a already a user registered with the phone number provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            user.setPhoneNumber("+1" + user.getPhoneNumber());
            userService.saveUser(user);
            session.setAttribute("successMessage", "User has been registered successfully");
            session.setAttribute("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/user/home", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) throws ParseException {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        session.setAttribute("user", user);
        if (session.getAttribute("date") == null) {
            session.setAttribute("date", new Date(System.currentTimeMillis()));
        }
        session.setAttribute("days", user.getDays());
        session.setAttribute("userName", "Welcome back " + user.getFirst() + " " + user.getLast());
        java.sql.Date date = (Date) session.getAttribute("date");
        String phoneNumber = user.getPhoneNumber();
        List<Day> days = dayDetailsService.findByDateAndPhoneNumber(date, phoneNumber);
        if (days.size() > 0) {
            Day day = days.get(0);
            session.setAttribute("meals", day.getMeals());
            logger.info(String.valueOf(day.getMeals().size()));
            session.setAttribute("workouts", day.getWorkouts());
            session.setAttribute("activities", day.getActivities());
            session.setAttribute("wake", day.getWakeTime());
            session.setAttribute("sleep", day.getSleepTime());
        } else {
            session.removeAttribute("meals");
            session.removeAttribute("workouts");
            session.removeAttribute("activities");
            session.removeAttribute("wake");
            session.removeAttribute("sleep");
        }
        logger.info("home called");
        modelAndView.setViewName("user/home");
        return modelAndView;
    }

    @RequestMapping(value="/user/updateDate", method = RequestMethod.POST)
    public String updateDate(HttpSession session, @RequestBody String body) {
        session.setAttribute("date", java.sql.Date.valueOf(body));
        return "user/home";
    }

}
