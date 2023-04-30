package fr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
