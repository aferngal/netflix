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

import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.repositories.ChapterRepository;

@RunWith(MockitoJUnitRunner.class)
public class ChapterServiceImplTest {
	@Mock
	ChapterRepository chapterRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	ChapterServiceImpl chapterService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getChaptersByTvShowIdAndSeasonNumber() throws NetflixException {
		// given
		final long tvShowId = 1L;
		final short seasonNumber = 1;
		// final TvShow tvShow = new TvShow();
		// final Season season = new Season();
		final Chapter chapter1 = new Chapter();
		final Chapter chapter2 = new Chapter();
		final ChapterRest chapterRest1 = new ChapterRest();
		final ChapterRest chapterRest2 = new ChapterRest();
		final List<Chapter> chapterList = new ArrayList<>();
		chapterList.add(chapter1);
		chapterList.add(chapter2);

		Mockito.when(chapterRepository.findBySeasonTvShowIdAndSeasonNumber(tvShowId, seasonNumber))
				.thenReturn(chapterList);
		Mockito.when(modelMapper.map(chapter1, ChapterRest.class)).thenReturn(chapterRest1);
		Mockito.when(modelMapper.map(chapter2, ChapterRest.class)).thenReturn(chapterRest2);

		// when
		final List<ChapterRest> chapterRestList = chapterService.getChaptersByTvShowIdAndSeasonNumber(tvShowId,
				seasonNumber);

		// then
		assertThat(chapterRestList).contains(chapterRest1, chapterRest2);

	}

	@Test
	public void getChapterByTvShowIdAndSeasonNumberAndChapterNumber() throws NetflixException {
		// given
		final long tvShowId = 1L;
		final short seasonNumber = 1;
		final short chapterNumber = 1;
		final Chapter chapter = new Chapter();
		final ChapterRest chapterRest = new ChapterRest();

		Mockito.when(
				chapterRepository.findBySeasonTvShowIdAndSeasonNumberAndNumber(tvShowId, seasonNumber, chapterNumber))
				.thenReturn(Optional.of(chapter));
		Mockito.when(modelMapper.map(chapter, ChapterRest.class)).thenReturn(chapterRest);
		// when
		final ChapterRest chapterRest2 = chapterService.getChapterByTvShowIdAndSeasonNumberAndChapterNumber(tvShowId,
				seasonNumber, chapterNumber);

		// then
		assertThat(chapterRest2).isSameAs(chapterRest);

	}

	@Test(expected = NotFoundException.class)
	public void getChapterByTvShowIdAndSeasonNumberAndChapterNumberNotFoundException() throws NetflixException {
		// given
		final long tvShowId = 1L;
		final short seasonNumber = 1;
		final short chapterNumber = 1;

		Mockito.when(
				chapterRepository.findBySeasonTvShowIdAndSeasonNumberAndNumber(tvShowId, seasonNumber, chapterNumber))
				.thenReturn(Optional.empty());

		// when
		chapterService.getChapterByTvShowIdAndSeasonNumberAndChapterNumber(tvShowId, seasonNumber, chapterNumber);

		// then

	}
}
