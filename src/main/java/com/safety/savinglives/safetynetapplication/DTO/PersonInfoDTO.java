package com.safety.savinglives.safetynetapplication.DTO;

import java.util.List;

public class PersonInfoDTO {

	private String lastName;
	private String name;
	private String address;
	private String age;
	private String eMail;
	private List<String> medRecords;

	public PersonInfoDTO() {
		super();
	}

	public PersonInfoDTO(String lastName, String name, String address, String age, String eMail,
			List<String> medRecords) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.age = age;
		this.eMail = eMail;
		this.medRecords = medRecords;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public List<String> getMedRecords() {
		return medRecords;
	}

	public void setMedRecords(List<String> medRecords) {
		this.medRecords = medRecords;
	}

	@Override
	public int hashCode() {

		int finalHash = 0;

		for (String s : medRecords) {

			finalHash += s.hashCode();
		}

		return lastName.hashCode() + name.hashCode() + address.hashCode() + age.hashCode() + eMail.hashCode()
				+ finalHash;
	}

}
