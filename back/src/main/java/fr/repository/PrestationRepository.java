package fr.repository;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import fr.model.Prestation;

// @Repository
public interface PrestationRepository extends JpaRepository<Prestation, Long> {
    
}