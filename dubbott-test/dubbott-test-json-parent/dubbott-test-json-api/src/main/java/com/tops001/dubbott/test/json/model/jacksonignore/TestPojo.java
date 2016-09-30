package com.tops001.dubbott.test.json.model.jacksonignore;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TestPojo {

	String fish = "fish";

	public String getFish() {
		return fish;
	}

	public void setFish(String fish) {
		this.fish = fish;
	}

	@JsonIgnore
	boolean nod = true;

	@JsonIgnore
	public boolean getNod() {
		return nod;
	}

	@JsonIgnore
	public boolean getNew() {
		return true;
	}

}
