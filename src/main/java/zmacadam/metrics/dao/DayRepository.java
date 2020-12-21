package zmacadam.metrics.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zmacadam.metrics.model.Day;

import java.sql.Date;

public interface DayRepository extends JpaRepository<Day, Integer> {
    Day findByDate(Date day);
}
