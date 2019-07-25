package com.everis.d4i.tutorial.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.d4i.tutorial.entities.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

	Optional<Actor> findById(Long id);

	List<Actor> findByChapterActorChapterSeasonTvShowIdAndChapterActorChapterSeasonNumberAndChapterActorChapterNumber(
			long tvShowId, short seasonNumber, short chapterNumber);

	List<Actor> findByChapterActorChapter_Id(short chapterId);

}
