package com.everis.d4i.tutorial.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.SeasonRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class ActorServiceImpl implements ActorService {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private ActorRepository actorRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<ActorRest> getActors() throws NetflixException {
		return actorRepository.findAll().stream().map(actors -> modelMapper.map(actors, ActorRest.class))
				.collect(Collectors.toList());
	}

	@Override
	public ActorRest getActorById(final Long id) throws NetflixException {
		final Actor actor = getActor(id);

		final Map<TvShowRest, Set<SeasonRest>> tvShowSeasonMap = new HashMap<>(); // TvShowRest - Set SeasonRest
		final Map<SeasonRest, List<ChapterRest>> seasonChaptersMap = new HashMap<>(); // SeasonRest - List ChapterRest

		actor.getChapterActor().forEach(chapterActor -> {
			final TvShowRest tvShowRest = modelMapper.map(chapterActor.getChapter().getSeason().getTvShow(),
					TvShowRest.class);
			final SeasonRest seasonRest = modelMapper.map(chapterActor.getChapter().getSeason(), SeasonRest.class);
			final ChapterRest chapterRest = modelMapper.map(chapterActor.getChapter(), ChapterRest.class);

			tvShowSeasonMap.computeIfAbsent(tvShowRest, k -> new HashSet<>()).add(seasonRest);
			seasonChaptersMap.computeIfAbsent(seasonRest, k -> new ArrayList<>()).add(chapterRest);
		});

		final ActorRest actorRest = new ActorRest();
		actorRest.setId(actor.getId());
		actorRest.setName(actor.getName());
		actorRest.setSurname(actor.getSurname());
		actorRest.setDayOfBirth(actor.getDayOfBirth());
		final List<TvShowRest> tvShowList = new ArrayList<>();

		// recorro las series del map
		for (final TvShowRest tvShow : tvShowSeasonMap.keySet()) {
			// recorro las temporadas de las series del map
			for (final SeasonRest season : tvShow.getSeasons()) {
				// utilizo la temporada como clave para buscar en el otro map los chapters
				season.setChapters(seasonChaptersMap.get(season));
			}
			tvShowList.add(tvShow);
		}
		actorRest.setTvShows(tvShowList);
		return actorRest;
	}

	@Override
	public List<ActorRest> getActorsByChapter(final long tvShowId, final short seasonNumber,
			final short chapterNumber) {

		return actorRepository
				.findByChapterActorChapterSeasonTvShowIdAndChapterActorChapterSeasonNumberAndChapterActorChapterNumber(
						tvShowId, seasonNumber, chapterNumber)
				.stream().map(actor -> modelMapper.map(actor, ActorRest.class)).collect(Collectors.toList());
	}

	@Override
	public ActorRest createActor(final ActorRest actorRest) throws NetflixException {
		final Actor actor = modelMapper.map(actorRest, Actor.class);
		return modelMapper.map(actorRepository.save(actor), ActorRest.class);
	}

	@Override
	public ActorRest alterActor(final ActorRest actorRest) throws NetflixException {
		final Actor actor = actorRepository.findById(actorRest.getId())
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR));

		actor.setId(actorRest.getId());
		actor.setName(actorRest.getName());
		actor.setSurname(actorRest.getSurname());
		actor.setDayOfBirth(actorRest.getDayOfBirth());

		return modelMapper.map(actorRepository.save(actor), ActorRest.class);
	}

	@Override
	public ActorRest deleteActor(final Long id) throws NetflixException {
		final Actor actor = getActor(id);

		actorRepository.delete(actor);
		return modelMapper.map(actor, ActorRest.class);

	}

	private Actor getActor(final Long id) throws NotFoundException {
		return actorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_ACTOR));
	}
}
