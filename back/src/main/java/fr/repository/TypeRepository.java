package fr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.entity.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

}
