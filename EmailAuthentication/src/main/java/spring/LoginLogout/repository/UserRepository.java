package spring.LoginLogout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.LoginLogout.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
