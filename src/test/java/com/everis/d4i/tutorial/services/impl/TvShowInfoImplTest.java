package com.everis.d4i.tutorial.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;

@RunWith(MockitoJUnitRunner.class)
public class TvShowInfoImplTest {

	@Mock
	TvShowInfoImpl tvShowInfoImpl;

	@Mock
	private ModelMapper modelMapper;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getTvShowRate() throws NetflixException {
		// given
		final TvShowRest tvShowRest = new TvShowRest();

		// when
		final double rate = tvShowInfoImpl.getTvShowRate(tvShowRest);

		// then
		assertThat(rate).isBetween(0d, 5d);
	}
}
