package com.everis.d4i.tutorial.services;

import com.everis.d4i.tutorial.exceptions.NetflixException;

public interface TvShowInfoFeignService {

	double getTvShowUserRate(final String name) throws NetflixException;
}
