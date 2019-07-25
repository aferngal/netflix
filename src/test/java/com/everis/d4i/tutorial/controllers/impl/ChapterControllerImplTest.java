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
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.ChapterService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;

@RunWith(MockitoJUnitRunner.class)
public class ChapterControllerImplTest {
	@Mock
    private ChapterService chapteryService;

    @InjectMocks
    private ChapterControllerImpl chapterControllerImpl;

    @Before
    public void setUp() throws Exception {
    }	
    
    @Test
    public void getChaptersByTvShowIdAndSeasonNumber() throws NetflixException{
    	//given    	
    	final List<ChapterRest> chapterRestList = new ArrayList<>();
        chapterRestList.add(new ChapterRest());
        chapterRestList.add(new ChapterRest());
        chapterRestList.add(new ChapterRest());
    	final long tvShowId=1L;
    	final short seasonNumber=1;
    	Mockito.when(chapteryService.getChaptersByTvShowIdAndSeasonNumber(tvShowId, seasonNumber)).thenReturn(chapterRestList);
    	
    	//when
    	final NetflixResponse<List<ChapterRest>> netflixResponse = chapterControllerImpl.getChaptersByTvShowIdAndSeasonNumber(tvShowId, seasonNumber);
    			
    	//then
    	assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
        assertEquals(netflixResponse.getData(),chapterRestList);
       
    }
    
    @Test
    public void getChapterByTvShowIdAndSeasonNumberAndChapterNumber() throws NetflixException{
    	//given    	
    	final ChapterRest chapterRest = new ChapterRest();    	
    	final long tvShowId=1L;
    	final short seasonNumber=1;
    	final short number=1;
    	chapterRest.setNumber(number);
    	
    	Mockito.when(chapteryService.getChapterByTvShowIdAndSeasonNumberAndChapterNumber(tvShowId, seasonNumber, number)).thenReturn(chapterRest);
    	
    	//when
    	final NetflixResponse<ChapterRest> netflixResponse = chapterControllerImpl.getChapterByTvShowIdAndSeasonNumberAndChapterNumber(tvShowId, seasonNumber, number);
    			
    	//then
    	assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
        assertEquals(netflixResponse.getData(),chapterRest);
       
    }
    
}
