package com.talentsprint.us.model;

public class Cuisine {
	private String cuisineId;
	private String name;
	private String country;

	public String getCuisineId() {
		return cuisineId;
	}

	public void setCuisineId(String cuisineId) {
		this.cuisineId = cuisineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Cuisine [cuisineId=" + cuisineId + ", name=" + name + ", country=" + country + "]";
	}

}
