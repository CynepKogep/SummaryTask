package ua.kharkov.khpi.database.beans;

public class MedicalUser extends Entity {
	
	private static final long serialVersionUID = 8467257860808346237L;
	
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String firstNameRu;
	private String lastNameRu;
	private int professionId;
	private int roleId;
	
	private String professionName;
	private String professionNameRu;
	
	
	private Integer numberOfPatients = null;
	
	public Integer getNumberOfPatients() {
		return numberOfPatients;
	}
	public void setNumberOfPatients(Integer numberOfPatients) {
		this.numberOfPatients = numberOfPatients;
	}
	public String getProfessionNameRu() {
		return professionNameRu;
	}
	public void setProfessionNameRu(String professionNameRu) {
		this.professionNameRu = professionNameRu;
	}
	public String getProfessionName() {
		return professionName;
	}
	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
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
	public int getProfessionId() {
		return professionId;
	}
	public void setProfessionId(int professionId) {
		this.professionId = professionId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "MedicalUser [login=" + login + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", firstNameRu=" + firstNameRu + ", lastNameRu=" + lastNameRu + ", professionId="
				+ professionId + ", roleId=" + roleId + ", professionName=" + professionName + ", professionNameRu="
				+ professionNameRu + "]";
	}

	

}
