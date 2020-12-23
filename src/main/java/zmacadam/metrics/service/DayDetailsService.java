package zmacadam.metrics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zmacadam.metrics.repository.DayRepository;
import zmacadam.metrics.model.Day;

import java.sql.Date;
import java.util.List;

@Service
public class DayDetailsService {

    private DayRepository dayRepository;

    @Autowired
    public DayDetailsService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    public List<Day> findByDateAndPhoneNumber(Date date, String phoneNumber) {
        return dayRepository.findByDateAndPhoneNumber(date, phoneNumber);
    }


    public Day saveDay(Day day) {
        return dayRepository.save(day);
    }
}
