package ua.kharkov.khpi.database.beans;

public class Profession extends Entity{
	private static final long serialVersionUID = 7456257860808346236L;
	private String professionName;
	
	public String getProfessionName() {
		return professionName;
	}
	
	public void setProfessionName(String categoryName) {
		this.professionName = categoryName;
	}
	
	@Override
	public String toString() {
		return "Profession ID: " + getId() + ", Category name: " + professionName;
	}
	
}
