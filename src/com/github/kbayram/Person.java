package com.github.kbayram;

public class Person {
	private int		ID;
	private String	firstname;
	private String	lastname;
	private String	phoneNumber;

	public Person() {
	}

	public Person(String firstname, String lastname, String phoneNumber) {
		this(0,firstname,lastname,phoneNumber);
	}

	public Person(int iD, String firstname, String lastname, String phoneNumber) {
		ID = iD;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
