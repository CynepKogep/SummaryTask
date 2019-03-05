package ua.kharkov.khpi.database.beans;

import java.sql.Date;

public class Patient extends Entity {
	private static final long serialVersionUID = 8466257860830246236L;

	private String  firstName;
	private String  lastName;
	private String  firstNameRu;
	private String  lastNameRu;
	private int     doctor_id;
	private Date    dateOfBirth;
	private String  telephoneNumber;
	private String  email;
	private boolean discharged;
	private int     diagnosis_id;

	//util parameter
	private String  diagnosisName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstNameRu() {
		return firstNameRu;
	}

	public void setFirstNameRu(String firstNameRu) {
		this.firstNameRu = firstNameRu;
	}

	public String getLastNameRu() {
		return lastNameRu;
	}

	public void setLastNameRu(String lastNameRu) {
		this.lastNameRu = lastNameRu;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDischarged() {
		return discharged;
	}

	public void setDischarged(boolean discharged) {
		this.discharged = discharged;
	}

	public int getDiagnosis_id() {
		return diagnosis_id;
	}

	public void setDiagnosis_id(int diagnosis_id) {
		this.diagnosis_id = diagnosis_id;
	}

	public String getDiagnosisName() {
		return diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}

	@Override
	public String toString() {
		return "Patient [firstName=" + firstName + ", lastName=" + lastName + ", firstNameRu=" + firstNameRu
				+ ", lastNameRu=" + lastNameRu + ", doctor_id=" + doctor_id + ", dateOfBirth=" + dateOfBirth
				+ ", telephoneNumber=" + telephoneNumber + ", email=" + email + ", discharged=" + discharged
				+ ", diagnosis_id=" + diagnosis_id + ", diagnosisName=" + diagnosisName + "]";
	}
	
	
	
}
