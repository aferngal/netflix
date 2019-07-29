package com.everis.d4i.tutorial.json;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TvShowRest implements Serializable {

	private static final long serialVersionUID = 4916713904971425156L;

	private Long id;
	private String name;
	private String shortDescription;
	private String longDescription;
	private Year year;
	private byte recommendedAge;
	private double rate;
	// private CategoryRest category;
	private String advertising;
	private List<SeasonRest> seasons = new ArrayList<>();
	private List<AwardRest> tvShowsAwards = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(final String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(final String longDescription) {
		this.longDescription = longDescription;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(final Year year) {
		this.year = year;
	}

	public byte getRecommendedAge() {
		return recommendedAge;
	}

	public void setRecommendedAge(final byte recommendedAge) {
		this.recommendedAge = recommendedAge;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(final double rate) {
		this.rate = rate;
	}

	/*
	 * public CategoryRest getCategory() { return category; }
	 * 
	 * public void setCategory(final CategoryRest category) { this.category =
	 * category; }
	 */
	public String getAdvertising() {
		return advertising;
	}

	public void setAdvertising(final String advertising) {
		this.advertising = advertising;
	}

	public List<SeasonRest> getSeasons() {
		return seasons;
	}

	public void setSeasons(final List<SeasonRest> seasons) {
		this.seasons = seasons;
	}

	public List<AwardRest> getTvShowsAwards() {
		return tvShowsAwards;
	}

	public void setTvShowsAwards(final List<AwardRest> tvShowsAwards) {
		this.tvShowsAwards = tvShowsAwards;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TvShowRest other = (TvShowRest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
