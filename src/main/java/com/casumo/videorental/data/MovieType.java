package com.casumo.videorental.data;

import java.util.Arrays;

public enum MovieType {
	NEW("new"),
	REGULAR("regular"),
	OLD("old"),
	UNKNOWN("n/a");

	private String name;

	MovieType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static MovieType getByName(String name) {
		return Arrays.asList(MovieType.values()).stream()
			.filter(d -> d.getName().equals(name))
			.findFirst().orElse(UNKNOWN);
	}

}
