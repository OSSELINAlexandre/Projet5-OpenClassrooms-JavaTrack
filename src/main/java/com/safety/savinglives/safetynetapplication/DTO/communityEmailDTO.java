package com.safety.savinglives.safetynetapplication.DTO;

public class communityEmailDTO {

	private String email;

	public communityEmailDTO() {
		super();
	}

	public communityEmailDTO(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {

		return email.hashCode() + 7789658;
	}

}
