package com.everis.d4i.tutorial.services;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;

public interface ActorService {

	List<ActorRest> getActors() throws NetflixException;

	ActorRest getActorById(Long id) throws NetflixException;
	
	List<ActorRest> getActorsByChapter(long tvShowId, short seasonNumber, short chapterNumber);	
	
	ActorRest createActor(ActorRest actorRest) throws NetflixException;
	
	ActorRest alterActor(ActorRest actorRest) throws NetflixException;
	
	ActorRest deleteActor(Long id) throws NetflixException;
	
	
}
