package com.everis.d4i.tutorial.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.services.TvShowInfoService;
import com.everis.d4i.tutorial.utils.constants.RestConstants;

@Service
public class TvShowInfoImpl implements TvShowInfoService {

	@Autowired
	private RestTemplate restTemplate;

	final String url = RestConstants.EXTERNAL_API + RestConstants.EXTERNAL_INFO + RestConstants.EXTERNAL_FILM;

	@Override
	public double getTvShowRate(final TvShowRest tvShowRest) throws NetflixException {

		return restTemplate.getForObject(url + "/" + tvShowRest.getName(), TvShowRest.class).getRate();

	}

}
