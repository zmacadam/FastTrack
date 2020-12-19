package zmacadam.metrics.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zmacadam.metrics.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
