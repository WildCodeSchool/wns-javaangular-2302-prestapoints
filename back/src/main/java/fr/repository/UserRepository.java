package fr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
import fr.model.User;

// @Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
