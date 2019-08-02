package com.everis.d4i.tutorial.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
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

import com.everis.d4i.tutorial.entities.Award;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.entities.TvShowsAwards;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowRepository;

@RunWith(MockitoJUnitRunner.class)
public class TvShowServiceImplTest {

	@Mock
	private TvShowRepository tvShowRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	TvShowServiceImpl tvShowService;

	@Mock
	private TvShowInfoImpl tvShowInfo;

	@Mock
	private TvShowInfoFeignServiceImpl tvShowInfoFeign;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getTvShowsByCategory() throws NetflixException {

		// given
		final long categoryId = 1L;

		final TvShow tvShow1 = new TvShow();
		final TvShow tvShow2 = new TvShow();

		final List<TvShow> tvShowList = Arrays.asList(tvShow1, tvShow2);

		final TvShowRest tvShowRest1 = new TvShowRest();
		final TvShowRest tvShowRest2 = new TvShowRest();

		Mockito.when(tvShowRepository.findByCategoriesTvShowsCategoryId(categoryId)).thenReturn(tvShowList);
		Mockito.when(modelMapper.map(tvShow1, TvShowRest.class)).thenReturn(tvShowRest1);
		Mockito.when(modelMapper.map(tvShow2, TvShowRest.class)).thenReturn(tvShowRest2);
		tvShowRest1.setRate(tvShowInfo.getTvShowRate(tvShowRest1));
		tvShowRest1.setUserRate(tvShowInfoFeign.getTvShowUserRate(tvShowRest1.getName()));

		// when
		final List<TvShowRest> tvShowRestList = tvShowService.getTvShowsByCategory(categoryId);

		// then
		assertThat(tvShowRestList).contains(tvShowRest1, tvShowRest2);

	}

	@Test
	public void getTvShowById() throws NetflixException {

		// given
		final long id = 1L;

		final TvShow tvShow = new TvShow();
		final Award award1 = new Award();
		final Award award2 = new Award();
		final TvShowsAwards tvShowsAwards1 = new TvShowsAwards();
		final TvShowsAwards tvShowsAwards2 = new TvShowsAwards();

		tvShowsAwards1.setAward(award1);
		tvShowsAwards2.setAward(award2);
		tvShow.setTvShowsAwards(Arrays.asList(tvShowsAwards1, tvShowsAwards2));

		final TvShowRest tvShowRest = new TvShowRest();
		final AwardRest awardRest1 = new AwardRest();
		final AwardRest awardRest2 = new AwardRest();
		// final List<AwardRest> awardRestList = Arrays.asList(awardRest1, awardRest2);

		Mockito.when(tvShowRepository.findById(id)).thenReturn(Optional.of(tvShow));
		Mockito.when(modelMapper.map(award1, AwardRest.class)).thenReturn(awardRest1);
		Mockito.when(modelMapper.map(award2, AwardRest.class)).thenReturn(awardRest2);
		Mockito.when(modelMapper.map(tvShow, TvShowRest.class)).thenReturn(tvShowRest);

		// when
		final TvShowRest tvShowRestFinal = tvShowService.getTvShowById(id);

		// then
		assertThat(tvShowRestFinal).isSameAs(tvShowRest);

	}

	@Test(expected = NotFoundException.class)
	public void getTvShowByIdKO() throws NetflixException {
		// given
		final long id = 1L;
		// final Category category = new Category();
		// final CategoryRest categoryRest = new CategoryRest();
		// categoryRest.setId(id);
		Mockito.when(tvShowRepository.findById(id)).thenReturn(Optional.empty());
		// Mockito.when(modelMapper.map(category,
		// CategoryRest.class)).thenReturn(categoryRest);

		// when
		tvShowService.getTvShowById(id);

		// then
		// assertThat(categoryRest2.getId()).isEqualTo(id);

	}

	@Test
	public void getTvShowsByCategoryRateNotFound() throws NetflixException {

		// given
		final TvShowRest tvShowRest1 = new TvShowRest();
		tvShowRest1.setName(null);

		// Mockito.when(tvShowInfo.getTvShowRate(Mockito.any(TvShowRest.class)))
		// .thenThrow(Mockito.mock(NetflixException.class));

		// when
		tvShowService.getTvShowsByCategory(1L);
		// assertThatThrownBy(() -> tvShowInfo.getTvShowRate(null)).isEqualTo(ex);
		// Assertions.assertThatThrownBy(() ->
		// tvShowInfo.getTvShowRate(null)).isInstanceOf(NetflixException.class);

	}
}
