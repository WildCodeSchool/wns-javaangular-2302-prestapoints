package fr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.entity.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
    
}
