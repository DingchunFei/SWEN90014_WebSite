package com.fei.domain;

import java.util.Date;
import java.util.List;

public class User {

	private String id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private Integer status;

	private String full_name;

	private String institution;
	
	private Integer gender;

	private List<Favourite> favourites;

	private String img_src;


	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", status=" + status +
				", full_name='" + full_name + '\'' +
				", institution='" + institution + '\'' +
				", gender=" + gender +
				'}';
	}

	public User() {
	}

	public User(String username, String password, String email, Integer gender, Integer status) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}

	public String getImg_src() {
		return img_src;
	}

	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public List<Favourite> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<Favourite> favourites) {
		this.favourites = favourites;
	}
}
