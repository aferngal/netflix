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
import com.everis.d4i.tutorial.json.SeasonRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.SeasonService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;

@RunWith(MockitoJUnitRunner.class)
public class SeasonControllerImplTest {
	@Mock
    private SeasonService seasonService;

    @InjectMocks
    private SeasonControllerImpl seasonControllerImpl;

    @Before
    public void setUp() throws Exception {
    }	
    
    @Test
    public void getSeasonByTvShow() throws NetflixException {
    	//given
    	final TvShowRest tvShowRest = new TvShowRest();
    	final long tvShowId=1;
    	tvShowRest.setId(tvShowId);    	
    	final List<SeasonRest> seasonRestList = new ArrayList<>();
    	seasonRestList.add(new SeasonRest());
    	seasonRestList.add(new SeasonRest());
    	seasonRestList.add(new SeasonRest());
    	tvShowRest.setSeasons(seasonRestList);    	
    	Mockito.when(seasonService.getSeasonsByTvShow(tvShowId)).thenReturn(tvShowRest.getSeasons());
    	
    	//when
    	final NetflixResponse<List<SeasonRest>> netflixResponse = seasonControllerImpl.getSeasonsByTvShow(tvShowId);
    	
    	//then
    	assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
        assertEquals(netflixResponse.getData(),tvShowRest.getSeasons());
    }
    
    @Test
    public void getSeasonByTvShowIdAndSeasonNumber() throws NetflixException{
    	//given
    	final long tvShowId=1;
    	final short seasonNumber=1;
    	final SeasonRest seasonRest = new SeasonRest();
    	Mockito.when(seasonService.getSeasonByTvShowIdAndSeasonNumber(tvShowId, seasonNumber)).thenReturn(seasonRest);
    	
    	//when
    	final NetflixResponse<SeasonRest> netflixResponse = seasonControllerImpl.getSeasonByTvShowIdAndSeasonNumber(tvShowId, seasonNumber);
    	
    	//then
    	assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
        assertEquals(netflixResponse.getData(), seasonRest);
    	
    }
    
}
