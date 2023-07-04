package fr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.entity.Prestation;

public interface PrestationRepository extends JpaRepository<Prestation, Integer> {

    
}
