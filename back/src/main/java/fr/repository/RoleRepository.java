package fr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    
}
