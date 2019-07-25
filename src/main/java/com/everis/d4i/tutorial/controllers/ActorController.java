package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface ActorController {

	NetflixResponse<List<ActorRest>> getActors() throws NetflixException;

	NetflixResponse<ActorRest> getActorById(Long id) throws NetflixException;
	
	NetflixResponse<List<ActorRest>> getActorsByChapter(Long tvShowId, short seasonNumber, short chapterNumber) throws NetflixException;
	
	NetflixResponse<ActorRest> createActor(ActorRest actorRest) throws NetflixException;
	
	NetflixResponse<ActorRest> alterActor(ActorRest actorRest) throws NetflixException;
	
	NetflixResponse<ActorRest> deleteActor(Long id) throws NetflixException;
	
}
