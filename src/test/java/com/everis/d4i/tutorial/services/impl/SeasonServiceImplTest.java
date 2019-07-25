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

import com.everis.d4i.tutorial.entities.Season;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.SeasonRest;
import com.everis.d4i.tutorial.repositories.SeasonRepository;

@RunWith(MockitoJUnitRunner.class)
public class SeasonServiceImplTest {
	@Mock
	SeasonRepository seasonRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	SeasonServiceImpl seasonService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getSeasonsByTvShow() throws NetflixException {
		// given
		long tvShowId = 1;
		final Season season1 = new Season();
		final Season season2 = new Season();
		final List<Season> seasonList = new ArrayList<>();
		final SeasonRest seasonRest1 = new SeasonRest();
		final SeasonRest seasonRest2 = new SeasonRest();
		seasonList.add(season1);
		seasonList.add(season2);

		Mockito.when(seasonRepository.findByTvShowId(tvShowId)).thenReturn(seasonList);
		Mockito.when(modelMapper.map(season1, SeasonRest.class)).thenReturn(seasonRest1);
		Mockito.when(modelMapper.map(season2, SeasonRest.class)).thenReturn(seasonRest2);

		// when
		final List<SeasonRest> seasonRestList = seasonService.getSeasonsByTvShow(tvShowId);

		// then
		assertThat(seasonRestList).contains(seasonRest1, seasonRest2);

	}

	@Test
	public void getSeasonByTvShowIdAndSeasonNumber() throws NetflixException {
		// given
		final long tvShowId = 1L;
		final short seasonNumber = 1;
		final TvShow tvShow = new TvShow();
		final Season season = new Season();
		final SeasonRest seasonRest = new SeasonRest();

		tvShow.setId(tvShowId);
		season.setNumber(seasonNumber);

		Mockito.when(seasonRepository.findByTvShowIdAndNumber(tvShowId, seasonNumber)).thenReturn(Optional.of(season));
		Mockito.when(modelMapper.map(season, SeasonRest.class)).thenReturn(seasonRest);

		// when
		final SeasonRest seasonRest2 = seasonService.getSeasonByTvShowIdAndSeasonNumber(tvShowId, seasonNumber);

		// then
		assertThat(seasonRest2).isSameAs(seasonRest);

	}

	@Test(expected = NotFoundException.class)
	public void getSeasonByTvShowIdAndSeasonNumberNotFoundException() throws NetflixException {
		// given
		final long tvShowId = 1L;
		final short seasonNumber = 1;

		Mockito.when(seasonRepository.findByTvShowIdAndNumber(tvShowId, seasonNumber)).thenReturn(Optional.empty());

		// when
		seasonService.getSeasonByTvShowIdAndSeasonNumber(tvShowId, seasonNumber);

		// then

	}
}
