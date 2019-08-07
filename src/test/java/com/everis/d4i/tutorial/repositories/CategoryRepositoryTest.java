package com.everis.d4i.tutorial.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.everis.d4i.tutorial.config.H2JpaConfig;
import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.exceptions.NetflixException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = H2JpaConfig.class)
@Sql(scripts = "classpath:category.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void findById() throws NetflixException {
		// given

		// when
		final Optional<Category> category = categoryRepository.findById(1L);
		// then
		assertThat(category).isNotEmpty();
	}

	@Test
	public void findByName() throws NetflixException {
		// given

		// when
		final Optional<Category> category = categoryRepository.findByName("ACCIÓN");
		// then
		assertThat(category).isNotEmpty();
	}

	@Test
	public void saveCategory() throws NetflixException {
		// given
		final Category newCategory = new Category();
		// newCategory.setId(4L);
		newCategory.setName("SCI-FI");

		// when
		final Category category = categoryRepository.save(newCategory);

		// then
		assertThat(category).isEqualTo(newCategory);
	}

	@Test
	public void deleteCategory() throws NetflixException {
		// given
		final long id = 1L;
		final Optional<Category> category = categoryRepository.findById(id);

		// when
		categoryRepository.deleteById(1L);

		// then
		assertThat(category).isNotEmpty();
		assertThat(categoryRepository.findById(id)).isEmpty();
	}

}
