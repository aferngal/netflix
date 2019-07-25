package com.everis.d4i.tutorial.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriesTvShowsRest implements Serializable{

	private static final long serialVersionUID = 1141196643679066263L;
	private Long id;
	private Long categoryId;
	private Long tvShowId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getTvShowId() {
		return tvShowId;
	}
	public void setTvShowId(Long tvShowId) {
		this.tvShowId = tvShowId;
	}
	
	
}
