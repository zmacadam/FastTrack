package zmacadam.metrics.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zmacadam.metrics.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByPhonenumber(String phoneNumber);
}
