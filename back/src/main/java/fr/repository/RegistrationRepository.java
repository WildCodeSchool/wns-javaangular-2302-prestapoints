package fr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import fr.model.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    
    @Query("SELECT r FROM Registration r WHERE r.prestation.id = :id")
    Iterable<Registration> findAllByPrestationId(@Param("id") Long id);

    @Query("SELECT r, p FROM Registration r JOIN Prestation p")
    Iterable<Registration> findAllPrestationWithComment();
}
