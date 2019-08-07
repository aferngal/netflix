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
import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.NetflixException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = H2JpaConfig.class)
@Sql(scripts = "classpath:actor.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
public class ActorRepositoryTest {

	@Autowired
	private ActorRepository actorRepository;

	@Test
	public void findById() throws NetflixException {
		// when
		final Optional<Actor> actorFound = actorRepository.findById(1L);

		// then
		assertThat(actorFound).isNotEmpty();
	}

	@Test
	public void findByChapterActorChapterSeasonTvShowIdAndChapterActorChapterSeasonNumberAndChapterActorChapterNumber()
			throws NetflixException {
		// given

		// when
		final List<Actor> actorList = actorRepository
				.findByChapterActorChapterSeasonTvShowIdAndChapterActorChapterSeasonNumberAndChapterActorChapterNumber(
						1L, (short) 1, (short) 1);
		// then
		assertThat(actorList).isNotEmpty();
	}

	@Test
	public void findByChapterActorChapter_Id() throws NetflixException {
		// given

		// when
		final List<Actor> actorList = actorRepository.findByChapterActorChapter_Id(1L);

		// then
		assertThat(actorList).isNotEmpty();

	}

	@Test
	public void saveActor() throws NetflixException {
		// given
		final Actor newActor = new Actor();
		// newActor.setId(2L);
		newActor.setName("Nombre");
		newActor.setSurname("Apellido");
		newActor.setDayOfBirth("1980-01-01");

		// when
		final Actor actor = actorRepository.save(newActor);

		// then
		assertThat(actor).isEqualTo(newActor);
	}

	@Test
	public void deleteActor() throws NetflixException {
		// given
		final long id = 1L;
		final Optional<Actor> actor = actorRepository.findById(id);

		// when
		actorRepository.deleteById(1L);

		// then
		assertThat(actor).isNotEmpty();
		assertThat(actorRepository.findById(id)).isEmpty();
	}
}
