package zmacadam.metrics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zmacadam.metrics.repository.DayRepository;
import zmacadam.metrics.repository.MealRepository;
import zmacadam.metrics.model.Day;
import zmacadam.metrics.model.nutrition.Meal;

import java.sql.Date;
import java.util.List;

@Service
public class DayDetailsService {

    private DayRepository dayRepository;
    private MealRepository mealRepository;

    @Autowired
    public DayDetailsService(DayRepository dayRepository,
                             MealRepository mealRepository) {
        this.dayRepository = dayRepository;
        this.mealRepository = mealRepository;
    }

    public List<Day> findByDateAndPhoneNumber(Date date, String phoneNumber) {
        return dayRepository.findByDateAndPhoneNumber(date, phoneNumber);
    }

    public Meal findByMealId(int mealId) {
        return mealRepository.findById(mealId);
    }

    public Day saveDay(Day day) {
        return dayRepository.save(day);
    }
}
