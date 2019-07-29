package com.everis.d4i.tutorial.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.ChapterActor;
import com.everis.d4i.tutorial.entities.Season;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.SeasonRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;

@RunWith(MockitoJUnitRunner.class)
public class ActorServiceImplTest {

	@Mock
	ActorRepository actorRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	ActorServiceImpl actorService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getActorById() throws NetflixException {
		// given
		final long id = 1L;
		final Actor actor = new Actor();
		final TvShow tvShow = new TvShow();
		final TvShowRest tvShowRest = new TvShowRest();
		final Season season = new Season();
		final SeasonRest seasonRest = new SeasonRest();
		final Chapter chapter = new Chapter();
		final ChapterRest chapterRest = new ChapterRest();
		final ChapterActor chapterActor1 = new ChapterActor();
		final List<ChapterActor> chapterActorList = new ArrayList<>();
		chapterActorList.add(chapterActor1);
		actor.setChapterActor(chapterActorList);

		actor.setId(id);

		chapter.setSeason(season);
		season.setTvShow(tvShow);
		chapterActor1.setChapter(chapter);

		Mockito.when(actorRepository.findById(id)).thenReturn(Optional.of(actor));
		Mockito.when(modelMapper.map(tvShow, TvShowRest.class)).thenReturn(tvShowRest);
		Mockito.when(modelMapper.map(season, SeasonRest.class)).thenReturn(seasonRest);
		Mockito.when(modelMapper.map(chapter, ChapterRest.class)).thenReturn(chapterRest);

		seasonRest.getChapters().add(chapterRest);
		tvShowRest.getSeasons().add(seasonRest);

		// when
		final ActorRest actorRest = actorService.getActorById(id);

		// then
		assertThat(actorRest.getId()).isEqualTo(id);
		assertThat(actor.getChapterActor()).contains(chapterActor1);
	}

	@Test(expected = NotFoundException.class)
	public void getActorByIdNotFoundException() throws NetflixException {
		// given
		final ActorRest actorRest = new ActorRest();

		Mockito.when(actorRepository.findById(actorRest.getId())).thenReturn(Optional.empty());

		// when
		actorService.getActorById(actorRest.getId());

		// then

	}

	@Test
	public void getActors() throws NetflixException {
		// given
		final List<Actor> actorList = new ArrayList<>();
		final Actor actor = new Actor();
		final ActorRest actorRest = new ActorRest();
		actorList.add(actor);

		Mockito.when(actorRepository.findAll()).thenReturn(actorList);
		Mockito.when(modelMapper.map(actor, ActorRest.class)).thenReturn(actorRest);

		// when
		final List<ActorRest> actorRestList = actorService.getActors();

		// then
		assertThat(actorRestList).contains(actorRest);
	}

	@Test
	public void getActorsByChapter() throws NetflixException {
		// given
		final long tvShowId = 1L;
		final short seasonNumber = 1;
		final short chapterNumber = 1;
		final Actor actor = new Actor();
		final ActorRest actorRest = new ActorRest();
		final List<Actor> actorList = new ArrayList<>();

		actorList.add(actor);

		Mockito.when(modelMapper.map(actor, ActorRest.class)).thenReturn(actorRest);
		Mockito.when(actorRepository
				.findByChapterActorChapterSeasonTvShowIdAndChapterActorChapterSeasonNumberAndChapterActorChapterNumber(
						tvShowId, seasonNumber, chapterNumber))
				.thenReturn(actorList);

		// when
		final List<ActorRest> actorRestList = actorService.getActorsByChapter(tvShowId, seasonNumber, chapterNumber);

		// then
		assertThat(actorRestList).contains(actorRest);
	}

	@Test
	public void createActor() throws NetflixException {
		final ActorRest actorRest = new ActorRest();
		final Actor actorInput = new Actor();
		final Actor actorOutput = new Actor();
		final ActorRest actorRestOut = new ActorRest();

		Mockito.when(modelMapper.map(actorRest, Actor.class)).thenReturn(actorInput);
		Mockito.when(actorRepository.save(actorInput)).thenReturn(actorOutput);
		Mockito.when(modelMapper.map(actorOutput, ActorRest.class)).thenReturn(actorRestOut);

		// when
		final ActorRest actorRestOutput = actorService.createActor(actorRest);

		// then
		assertThat(actorRestOutput).isSameAs(actorRestOut);

	}

	@Test
	public void alterActor() throws NetflixException {
		// given
		final long id = 1L;
		final Actor actor = new Actor();
		final ActorRest actorRest1 = new ActorRest();
		actorRest1.setId(id);
		actorRest1.setName("pepe");
		actorRest1.setSurname("ape");
		actorRest1.setDayOfBirth("1987-05-01");

		Mockito.when(actorRepository.findById(id)).thenReturn(Optional.of(actor));
		Mockito.when(actorRepository.save(actor)).thenReturn(actor);
		Mockito.when(modelMapper.map(actor, ActorRest.class)).thenReturn(actorRest1);

		// when
		final ActorRest actorRest2 = actorService.alterActor(actorRest1);

		// then
		assertThat(actorRest2.getId()).isEqualTo(id);
	}

	@Test
	public void deleteActor() throws NetflixException {
		// given
		final long id = 1L;
		final Actor actor = new Actor();
		final ActorRest actorRest = new ActorRest();
		actor.setId(id);

		Mockito.when(actorRepository.findById(id)).thenReturn(Optional.of(actor));
		actorRepository.delete(actor);
		Mockito.when(modelMapper.map(actor, ActorRest.class)).thenReturn(actorRest);

		// when
		actorService.deleteActor(id);

		// then
		assertThat(!actorRepository.existsById(id));
	}

	@Test(expected = NotFoundException.class)
	public void alterActorNotFoundException() throws NetflixException {
		// given

		final ActorRest actorRest = new ActorRest();

		Mockito.when(actorRepository.findById(actorRest.getId())).thenReturn(Optional.empty());

		// when
		actorService.alterActor(actorRest);

		// then

	}

	@Test(expected = NotFoundException.class)
	public void deleteActorNotFoundException() throws NetflixException {
		// given
		final long id = 8L;

		Mockito.when(actorRepository.findById(id)).thenReturn(Optional.empty());

		// when
		actorService.deleteActor(id);

		// then

	}
}
