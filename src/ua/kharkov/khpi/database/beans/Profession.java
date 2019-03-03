package ua.kharkov.khpi.database.beans;

public class Profession extends Entity{
	private static final long serialVersionUID = 8467257860808346237L;
	
	private String professionName;
	private String professionNameRu;
	
	public String getProfessionNameRu() {
		return professionNameRu;
	}

	public void setProfessionNameRu(String professionNameRu) {
		this.professionNameRu = professionNameRu;
	}

	public String getProfessionName() {
		return professionName;
	}
	
	public void setProfessionName(String categoryName) {
		this.professionName = categoryName;
	}

	@Override
	public String toString() {
		return "Profession [professionName=" + professionName + ", professionNameRu=" + professionNameRu + "]";
	}
	
}
