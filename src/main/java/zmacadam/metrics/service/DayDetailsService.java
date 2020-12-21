package zmacadam.metrics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zmacadam.metrics.dao.DayRepository;
import zmacadam.metrics.dao.MealRepository;
import zmacadam.metrics.model.Day;
import zmacadam.metrics.model.Meal;

import java.sql.Date;

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

    public Day findByDate(Date day) {
        return dayRepository.findByDate(day);
    }

    public Meal findByMealId(int mealId) {
        return mealRepository.findById(mealId);
    }

    public Day saveDay(Day day) {
        return dayRepository.save(day);
    }
}
