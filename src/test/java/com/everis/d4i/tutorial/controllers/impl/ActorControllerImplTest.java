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
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;

@RunWith(MockitoJUnitRunner.class)
public class ActorControllerImplTest {

	@Mock
	private ActorService actorService;

	@InjectMocks
	private ActorControllerImpl actorControllerImpl;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getActors() throws NetflixException {
		// given
		final List<ActorRest> actorRestList = new ArrayList<>();
		actorRestList.add(new ActorRest());
		actorRestList.add(new ActorRest());
		actorRestList.add(new ActorRest());
		Mockito.when(actorService.getActors()).thenReturn(actorRestList);

		// when
		final NetflixResponse<List<ActorRest>> netflixResponse = actorControllerImpl.getActors();

		// then
		assertNotNull(netflixResponse);
		assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
		assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
		assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
		assertEquals(netflixResponse.getData(), actorRestList);
	}

	@Test
	public void getActorById() throws NetflixException {
		// given
		final long id = 5;
		final ActorRest actorRest = new ActorRest();
		actorRest.setId(id);
		Mockito.when(actorService.getActorById(id)).thenReturn(actorRest);

		// when
		final NetflixResponse<ActorRest> netflixResponse = actorControllerImpl.getActorById(id);

		// then
		assertNotNull(netflixResponse);
		assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
		assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
		assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
		assertEquals(netflixResponse.getData(), actorRest);

	}

	@Test
	public void getActorsByChapter() throws NetflixException {
		// given
		final long tvShowId = 1;
		final short seasonNumber = 1;
		final short chapterNumber = 1;
		final List<ActorRest> actorRestList = new ArrayList<>();
		actorRestList.add(new ActorRest());
		actorRestList.add(new ActorRest());
		actorRestList.add(new ActorRest());

		Mockito.when(actorService.getActorsByChapter(tvShowId, seasonNumber, chapterNumber)).thenReturn(actorRestList);

		// when
		final NetflixResponse<List<ActorRest>> netflixResponse = actorControllerImpl.getActorsByChapter(tvShowId,
				seasonNumber, chapterNumber);

		// then
		assertNotNull(netflixResponse);
		assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
		assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
		assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
		assertEquals(netflixResponse.getData(), actorRestList);
	}

	@Test
	public void createActor() throws NetflixException {
		// given
		final ActorRest actorRest = new ActorRest();

		Mockito.when(actorService.createActor(actorRest)).thenReturn(actorRest);

		// when
		final NetflixResponse<ActorRest> netflixResponse = actorControllerImpl.createActor(actorRest);

		// then
		assertNotNull(netflixResponse);
		assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
		assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.CREATED));
		assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
		assertEquals(netflixResponse.getData(), actorRest);

	}

	@Test
	public void alterActor() throws NetflixException {
		// given
		final ActorRest actorRest = new ActorRest();

		Mockito.when(actorService.alterActor(actorRest)).thenReturn(actorRest);

		// when
		final NetflixResponse<ActorRest> netflixResponse = actorControllerImpl.alterActor(actorRest);

		// then
		assertNotNull(netflixResponse);
		assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
		assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
		assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
		assertEquals(netflixResponse.getData(), actorRest);
	}

	@Test
	public void deleteActor() throws NetflixException {
		// given
		final ActorRest actorRest = new ActorRest();

		Mockito.when(actorService.deleteActor(actorRest.getId())).thenReturn(actorRest);

		// when
		final NetflixResponse<ActorRest> netflixResponse = actorControllerImpl.deleteActor(actorRest.getId());

		// then
		assertNotNull(netflixResponse);
		assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
		assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.NO_CONTENT));
		assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
		assertEquals(netflixResponse.getData(), actorRest);

	}

}
