package com.everis.d4i.tutorial.json;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorRest implements Serializable {	

	private static final long serialVersionUID = -1412168834980491000L;
	
	private Long id;
	private String name;
	private String surname;
	private String dayOfBirth;
	@JsonProperty(access = Access.READ_ONLY)
	private List<TvShowRest> tvShows;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDayOfBirth() {
		return dayOfBirth;
	}
	public void setDayOfBirth(String dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}
	public List<TvShowRest> getTvShows() {
		return tvShows;
	}
	public void setTvShows(List<TvShowRest> tvShows) {
		this.tvShows = tvShows;
	}	

}
