package com.everis.d4i.tutorial.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.repositories.TvShowInfoRepository;
import com.everis.d4i.tutorial.services.TvShowInfoFeignService;

@Service
public class TvShowInfoFeignServiceImpl implements TvShowInfoFeignService {

	@Autowired
	private TvShowInfoRepository tvShowInfoRepository;

	@Override
	public double getTvShowUserRate(final String name) throws NetflixException {

		return tvShowInfoRepository.findByName(name).getRate();

	}

}
