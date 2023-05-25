package fr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.entity.User;

// @Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
