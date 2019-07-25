package com.everis.d4i.tutorial.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.util.Arrays;
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
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
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
		final Map<TvShowRest, Set<SeasonRest>> tvShowSeasonMap = new HashMap<>(); // TvShowRest - Set SeasonRest
		final Map<SeasonRest, List<ChapterRest>> seasonChaptersMap = new HashMap<>();
		final ChapterActor chapterActor1 = new ChapterActor();
		final ChapterActor chapterActor2 = new ChapterActor();
		final List<ChapterActor> chapterActorList = new ArrayList<>();
		chapterActorList.add(chapterActor1);
		chapterActorList.add(chapterActor2);
		actor.setChapterActor(chapterActorList);
		// final ActorRest actorRest = new ActorRest();

		actor.setId(id);
		// chapterActor.setActor(actor);
		// chapterActor.setChapter(chapter);
		chapter.setSeason(season);
		season.setTvShow(tvShow);
		chapterActor1.setChapter(chapter);
		tvShowSeasonMap.computeIfAbsent(tvShowRest, k -> new HashSet<>()).add(seasonRest);
		seasonChaptersMap.computeIfAbsent(seasonRest, k -> new ArrayList<>()).add(chapterRest);

		Mockito.when(actorRepository.findById(id)).thenReturn(Optional.of(actor));
		Mockito.when(modelMapper.map(tvShow, TvShowRest.class)).thenReturn(tvShowRest);
		Mockito.when(modelMapper.map(season, SeasonRest.class)).thenReturn(seasonRest);
		Mockito.when(modelMapper.map(chapter, ChapterRest.class)).thenReturn(chapterRest);
		// Mockito.when(modelMapper.map(actor, ActorRest.class)).thenReturn(actorRest);

		// when
		final ActorRest actorRest = actorService.getActorById(id);

		// then
		// assertThat(actorRest2.getId()).isEqualTo(id);
		assertThat(actorRest.getId()).isEqualTo(id);
		assertThat(actor.getChapterActor()).contains(chapterActor1, chapterActor2);

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
		// assertThat(actorRestList.get(0).getName()).isEqualTo("Buffoonery");
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
		actorRest.setName("Pepe");
		actorRest.setSurname("Colubi");
		final Actor actorInput = new Actor();

		Mockito.when(modelMapper.map(actorRest, Actor.class)).thenReturn(actorInput);
		actorRepository.save(actorInput);

		// when
		final ActorRest actorOutput = actorService.createActor(actorRest);

		// then
		assertThat(actorOutput).isSameAs(actorRest);

	}

	@Test(expected = InternalServerErrorException.class)
	public void createActorThrowsInternalServerErrorException() throws NetflixException {
		final ActorRest actorRest = new ActorRest();
		// final Actor actorInput = new Actor();
		actorRest.setId(1L);
		Mockito.when(actorRepository.existsById(actorRest.getId())).thenReturn(true);

		// when
		actorService.createActor(actorRest);

		// then

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
		//assertThat(actorRest2.getId()).isEqualTo(actor.getId());
		assertThat(!actorRepository.existsById(id));
	}
	
	@Test(expected = NotFoundException.class)
	public void alterActorNotFoundException() throws NetflixException {
		// given
		//final long id = 8L;
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
		// Mockito.when(modelMapper.map(actor, ActorRest.class)).thenThrow(new
		// NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR));

		// when
		actorService.deleteActor(id);

		// then

	}
}
