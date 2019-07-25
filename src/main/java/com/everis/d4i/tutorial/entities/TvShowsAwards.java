package com.everis.d4i.tutorial.entities;

import java.io.Serializable;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TV_SHOWS_AWARDS")
public class TvShowsAwards implements Serializable{
	
	private static final long serialVersionUID = 275896128995513156L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TV_SHOWS_ID", nullable = false)
	private TvShow tvShow;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AWARDS_ID", nullable = false)
	private Award award;
	
	@Column(name = "YEAR")
	private Year year;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TvShow getTvShow() {
		return tvShow;
	}

	public void setTvShow(TvShow tvShow) {
		this.tvShow = tvShow;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}		
	
}
