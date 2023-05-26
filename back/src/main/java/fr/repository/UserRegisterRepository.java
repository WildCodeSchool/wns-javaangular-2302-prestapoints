package fr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import fr.entity.UserRegister;

public interface UserRegisterRepository extends JpaRepository<UserRegister, Integer> {
    
    @Query("SELECT r FROM User_register r WHERE r.prestation.id = :id")
    List<UserRegister> findAllByPrestationId(@Param("id") Integer id);

    @Query("SELECT r, p FROM User_register r JOIN Prestation p")
    Iterable<UserRegister> findAllPrestationWithComment();
}
