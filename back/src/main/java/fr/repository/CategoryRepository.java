package fr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}