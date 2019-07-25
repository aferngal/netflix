package com.everis.d4i.tutorial.controllers.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.TvShowService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;

@RunWith(MockitoJUnitRunner.class)
public class TvShowControllerImplTest {
	@Mock
	private TvShowService tvShowService;

	@InjectMocks
	private TvShowControllerImpl tvShowControllerImpl;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getTvShowsByCategory() throws NetflixException {
		// given
		final long categoryId = 1L;
		final CategoryRest categoryRest = new CategoryRest();
		categoryRest.setId(categoryId);
		
		final List<TvShowRest> tvShowRestList = new ArrayList<>();
		tvShowRestList.add(new TvShowRest());
		tvShowRestList.add(new TvShowRest());
		tvShowRestList.add(new TvShowRest());		
		
		Mockito.when(tvShowService.getTvShowsByCategory(categoryId)).thenReturn(tvShowRestList);

		// when
		final NetflixResponse<List<TvShowRest>> netflixResponse = tvShowControllerImpl.getTvShowsByCategory(categoryId);
		
		// then
		assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);        
        assertEquals(netflixResponse.getData(), tvShowRestList);
	}
	
	@Test
	public void getTvShowById() throws NetflixException {
		// given
		final long id = 1;
		final TvShowRest tvShowRest = new TvShowRest();
		tvShowRest.setId(id);
		
		Mockito.when(tvShowService.getTvShowById(id)).thenReturn(tvShowRest);

		// when
		final NetflixResponse<TvShowRest> netflixResponse = tvShowControllerImpl.getTvShowById(id);
		
		// then
		assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
        assertEquals(netflixResponse.getData(), tvShowRest);        
	}
}
