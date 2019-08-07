package com.everis.d4i.tutorial.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.everis.d4i.tutorial.config.H2JpaConfig;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = H2JpaConfig.class)
@Sql(scripts = "classpath:tvShow.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
public class TvShowRepositoryTest {

	@Autowired
	private TvShowRepository tvShowRepository;

	@Test
	public void findByCategoriesTvShowsCategoryId() throws NetflixException {
		// given

		// when
		final List<TvShow> tvShowList = tvShowRepository.findByCategoriesTvShowsCategoryId(3L);
		// then
		assertThat(tvShowList).isNotEmpty();
	}

	@Test
	public void findBySeasonsChaptersChapterActorActorId() throws NetflixException {
		// given

		// when
		final List<TvShow> tvShowList = tvShowRepository.findBySeasonsChaptersChapterActorActorId(1L);
		// then
		assertThat(tvShowList).isNotEmpty();
	}
}
