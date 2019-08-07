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
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.exceptions.NetflixException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = H2JpaConfig.class)
@Sql(scripts = "classpath:chapter.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(scripts = "classpath:cleanTables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Transactional
public class ChapterRepositoryTest {

	@Autowired
	private ChapterRepository chapterRepository;

	@Test
	public void findBySeasonTvShowIdAndSeasonNumber() throws NetflixException {
		// given

		// when
		final List<Chapter> chapterList = chapterRepository.findBySeasonTvShowIdAndSeasonNumber(1L, (short) 1);
		// then
		assertThat(chapterList).isNotEmpty();
	}

	@Test
	public void findBySeasonTvShowIdAndSeasonNumberAndNumber() throws NetflixException {
		// given

		// when
		final Optional<Chapter> chapter = chapterRepository.findBySeasonTvShowIdAndSeasonNumberAndNumber(1L, (short) 1,
				(short) 1);
		// then
		assertThat(chapter).isNotEmpty();
	}
}
