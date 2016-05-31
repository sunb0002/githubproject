package com.ufinity.ott.domain.MediaPlatform;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MP_Display {
	@JsonProperty("content-lysis-id") // to map with JSON field
	private String content_lysis_id;
	@JsonProperty("name") // to map with JSON field
	private String name;
	@JsonProperty("short-title") // to map with JSON field
	private String short_title;
	@JsonProperty("display-locale") // to map with JSON field
	private String display_locale;
	@JsonProperty("actors") // to map with JSON field
	private String actors;
	@JsonProperty("description") // to map with JSON field
	private String description;
	@JsonProperty("long-description") // to map with JSON field
	private String long_description;
	@JsonProperty("genre") // to map with JSON field
	private String genre;
	@JsonProperty("subgenre") // to map with JSON field
	private String subgenre;
	
	public MP_Display() {}
	public MP_Display(String content_lysis_id, String name, String short_title, String display_locale, String actors,
			String description, String long_description, String genre, String subgenre) {
		this.content_lysis_id = content_lysis_id;
		this.name = name;
		this.short_title = short_title;
		this.display_locale = display_locale;
		this.actors = actors;
		this.description = description;
		this.long_description = long_description;
		this.genre = genre;
		this.subgenre = subgenre;
	}
	
	public String getContent_lysis_id() {
		return content_lysis_id;
	}
	public void setContent_lysis_id(String content_lysis_id) {
		this.content_lysis_id = content_lysis_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShort_title() {
		return short_title;
	}
	public void setShort_title(String short_title) {
		this.short_title = short_title;
	}
	public String getDisplay_locale() {
		return display_locale;
	}
	public void setDisplay_locale(String display_locale) {
		this.display_locale = display_locale;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLong_description() {
		return long_description;
	}
	public void setLong_description(String long_description) {
		this.long_description = long_description;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getSubgenre() {
		return subgenre;
	}
	public void setSubgenre(String subgenre) {
		this.subgenre = subgenre;
	}
	@Override
	public String toString() {
		return "MP_Display [content_lysis_id=" + content_lysis_id + ", name=" + name + ", short_title=" + short_title
				+ ", display_locale=" + display_locale + ", actors=" + actors + ", description=" + description
				+ ", long_description=" + long_description + ", genre=" + genre + ", subgenre=" + subgenre + "]";
	}
}
