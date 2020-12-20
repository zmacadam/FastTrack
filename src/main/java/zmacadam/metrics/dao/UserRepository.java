package zmacadam.metrics.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zmacadam.metrics.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);
    User findByPhoneNumber(String phoneNumber);
}
