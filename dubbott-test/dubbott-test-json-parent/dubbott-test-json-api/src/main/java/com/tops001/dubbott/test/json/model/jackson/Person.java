package com.tops001.dubbott.test.json.model.jackson;

/**
 * If a change is made to this class, it must also be updated in
 * lib/appclasses.jar\com\ibm\ws\jaxrs\fat\jackson\
 */
public class Person {

	private String name;
	private int age;
	private Manager m;
	private String random = "randomValue";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String randomProp() {
		return random;
	}

	public void setRandomProp(String s) {
		random = s;
	}

	public Manager getManager() {
		return m;
	}

	public void setManager(Manager m) {
		this.m = m;
	}

}
