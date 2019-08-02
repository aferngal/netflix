package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Award;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.TvShowService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class TvShowServiceImpl implements TvShowService {

	@Autowired
	private TvShowRepository tvShowRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TvShowInfoImpl tvShowInfo;

	@Autowired
	private TvShowInfoFeignServiceImpl tvShowInfoFeign;

	@Override
	public List<TvShowRest> getTvShowsByCategory(final Long categoryId) throws NetflixException {

		final List<TvShowRest> tvShowRestList = tvShowRepository.findByCategoriesTvShowsCategoryId(categoryId).stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRest.class)).collect(Collectors.toList());

		tvShowRestList.forEach(tvShowRest -> {

			try {
				tvShowRest.setRate(tvShowInfo.getTvShowRate(tvShowRest));
				tvShowRest.setUserRate(tvShowInfoFeign.getTvShowUserRate(tvShowRest.getName()));
			} catch (final NetflixException e) {
				e.printStackTrace();
			}

		});

		return tvShowRestList;
	}

	@Override
	public TvShowRest getTvShowById(final Long id) throws NetflixException {

		final TvShow tvShow = tvShowRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_TV_SHOW));

		final Set<Award> award = tvShow.getTvShowsAwards().stream().map(awards -> awards.getAward())
				.collect(Collectors.toSet());

		final List<AwardRest> awardRestList = award.stream()
				.map(awardRest -> modelMapper.map(awardRest, AwardRest.class)).collect(Collectors.toList());

		final TvShowRest tvShowRest = modelMapper.map(tvShow, TvShowRest.class);

		tvShowRest.setTvShowsAwards(awardRestList);
		tvShowRest.setRate(tvShowInfo.getTvShowRate(tvShowRest));

		return tvShowRest;

	}

}
