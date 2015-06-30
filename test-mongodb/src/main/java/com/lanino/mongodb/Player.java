package com.lanino.mongodb;

import org.bson.types.ObjectId;

public class Player {

	private ObjectId _id;
	private double id;
	private String name;
	private double age;
	private String birthplace;
	private String birthdate;
	private String position;
	private double weight;
	private String height;
	private String imageUrl;
	private double number;

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

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

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String brithplace) {
		this.birthplace = brithplace;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}

}
