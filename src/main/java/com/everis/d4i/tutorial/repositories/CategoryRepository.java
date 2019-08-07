package com.everis.d4i.tutorial.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.d4i.tutorial.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Override
	Optional<Category> findById(Long id);

	Optional<Category> findByName(String name);
}
