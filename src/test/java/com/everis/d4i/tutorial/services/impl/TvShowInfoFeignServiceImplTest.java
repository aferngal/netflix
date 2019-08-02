package com.everis.d4i.tutorial.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowInfoRepository;

@RunWith(MockitoJUnitRunner.class)
public class TvShowInfoFeignServiceImplTest {
	@Mock
	private TvShowInfoRepository tvShowInfoRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	TvShowInfoFeignServiceImpl tvShowInfoFeignService;

	@Mock
	private TvShowInfoImpl tvShowInfo;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getTvShowUserRate() throws NetflixException {
		// given
		final String name = "Juego de tronos";
		final TvShowRest tvShowRest = new TvShowRest();
		Mockito.when(tvShowInfoRepository.findByName(name)).thenReturn(tvShowRest);
		// when
		final double rate = tvShowInfoFeignService.getTvShowUserRate(name);
		// then
		assertThat(tvShowRest.getRate()).isEqualTo(rate);
	}

}
