package com.musthafa.springboot.models;


public class UserEntity {

	private int userId;
	private String userName;
	private int userAge;
	private double userSalary;

	public UserEntity(int userId, String userName, int userAge, double userSalary) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userAge = userAge;
		this.userSalary = userSalary;
	}

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public double getUserSalary() {
		return userSalary;
	}

	public void setUserSalary(double userSalary) {
		this.userSalary = userSalary;
	}

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", userName=" + userName + ", userAge=" + userAge + ", userSalary="
				+ userSalary + "]";
	}

}
