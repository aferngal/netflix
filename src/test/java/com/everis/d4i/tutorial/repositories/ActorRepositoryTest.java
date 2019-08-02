package com.everis.d4i.tutorial.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.ChapterActor;
import com.everis.d4i.tutorial.entities.Season;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = H2JpaConfig.class)
//@DataJpaTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ActorRepositoryTest {

	@Autowired
	private ActorRepository actorRepository;

	// @Autowired
	// private JavaSeH2Memory JavaSeH2Mem;

	@Test
	public void findById() throws NetflixException {
		// given
		final Long id = 1L;
		final Actor actor = new Actor();
		actor.setId(id);

		actorRepository.save(actor);

		// entityManager.merge(actor);

		// when
		final Optional<Actor> actorFound = actorRepository.findById(id);

		// then
		assertThat(actorFound.get().getId()).isEqualTo(actor.getId());
	}

	@BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
	@Test
	public void findByChapterActorChapterSeasonTvShowIdAndChapterActorChapterSeasonNumberAndChapterActorChapterNumber()
			throws NetflixException {
		// given
		final Long id = 1L;
		final short number = 1;
		final Actor actor = new Actor();
		final List<Actor> actorList = new ArrayList();
		final List<ChapterActor> chapterActorList = new ArrayList();
		final ChapterActor chapterActor = new ChapterActor();
		final Chapter chapter = new Chapter();
		final Season season = new Season();
		final TvShow tvShow = new TvShow();
		tvShow.setId(id);
		season.setNumber(number);
		chapter.setNumber(number);

		actorList.add(actor);
		chapter.setSeason(season);
		season.setTvShow(tvShow);
		chapterActor.setActor(actor);
		chapterActor.setChapter(chapter);
		chapterActorList.add(chapterActor);
		actor.setChapterActor(chapterActorList);
		actor.setDayOfBirth("1987-05-01");
		actor.setName("pepe");
		actor.setSurname("ape");

		// entityManager.merge(actor);
		// entityManager.flush();

		// when
		final List<Actor> actorListFound = actorRepository
				.findByChapterActorChapterSeasonTvShowIdAndChapterActorChapterSeasonNumberAndChapterActorChapterNumber(
						1L, number, number);

		// then
		assertThat(actorListFound).contains(actor);
	}

}
