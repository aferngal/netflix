package com.everis.d4i.tutorial.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.everis.d4i.tutorial.config.H2JpaConfig;
import com.everis.d4i.tutorial.entities.Season;
import com.everis.d4i.tutorial.exceptions.NetflixException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = H2JpaConfig.class)
@Sql(scripts = "classpath:season.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(scripts = "classpath:cleanTables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Transactional
public class SeasonRepositoryTest {

	@Autowired
	private SeasonRepository seasonRepository;

	@Test
	public void findByTvShowId() throws NetflixException {
		// given

		// when
		final List<Season> seasonList = seasonRepository.findByTvShowId(2L);

		// then
		assertThat(seasonList).hasSize(3);
	}

	@Test
	public void findByTvShowIdAndNumber() throws NetflixException {
		// given

		// when
		final Optional<Season> season = seasonRepository.findByTvShowIdAndNumber(2L, (short) 2);

		// then
		assertThat(season).isNotEmpty();
	}

}
