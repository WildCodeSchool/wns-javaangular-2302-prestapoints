package fr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import fr.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    
    @Query("SELECT r FROM Registration r WHERE r.prestation.id = :id")
    Iterable<Registration> findAllByPrestationId(@Param("id") Integer id);

    @Query("SELECT r, p FROM Registration r JOIN Prestation p")
    Iterable<Registration> findAllPrestationWithComment();
}
