package com.everis.d4i.tutorial.services;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;

public interface TvShowInfoService {

	double getTvShowRate(TvShowRest tvShowRest) throws NetflixException;
}
