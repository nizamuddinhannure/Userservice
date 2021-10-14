package com.aws.spring.demo.usermodule.model;

public class User {

	private Integer id;
	private String name;
	private long mobileNo;

	public User() {
		super();
	}

	public User(Integer id, String name, long mobileNo) {
		super();
		this.id = id;
		this.name = name;
		this.mobileNo = mobileNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mobileNo=" + mobileNo + "]";
	}

}
