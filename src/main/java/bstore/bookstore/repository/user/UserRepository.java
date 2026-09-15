package bstore.bookstore.repository.user;

import bstore.bookstore.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles "
            + "WHERE u.email = :email AND u.isDeleted = false")
    Optional<User> findByEmail(String email);
}
