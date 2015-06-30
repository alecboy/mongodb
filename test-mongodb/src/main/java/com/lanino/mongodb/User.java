package com.lanino.mongodb;

import org.bson.types.ObjectId;

public class User {

	private ObjectId _id;
	private String name;
	private double age;

	public ObjectId get_Id() {
		return _id;
	}

	public void set_Id(ObjectId _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "{Name: " + getName() + ", Age:" + getAge() + "}";
	}

}
