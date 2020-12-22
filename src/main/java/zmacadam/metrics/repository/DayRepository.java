package zmacadam.metrics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zmacadam.metrics.model.Day;

import java.sql.Date;
import java.util.List;

public interface DayRepository extends JpaRepository<Day, Integer> {
    List<Day> findByDateAndPhoneNumber(Date date, String phoneNumber);
}
