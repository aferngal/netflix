package com.everis.d4i.tutorial.json;

import java.io.Serializable;
import java.time.Year;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TvShowAwardRest implements Serializable{

	private static final long serialVersionUID = -1439104274367758510L;
	
	private Long id;
	private Long tvShowsId;	
	private Long awardsId;	
	private Year year;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTvShowsId() {
		return tvShowsId;
	}
	public void setTvShowsId(Long tvShowsId) {
		this.tvShowsId = tvShowsId;
	}
	public Long getAwardsId() {
		return awardsId;
	}
	public void setAwardsId(Long awardsId) {
		this.awardsId = awardsId;
	}
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
	
	
}
