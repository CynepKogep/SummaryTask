package ua.kharkov.khpi.database.beans;

public class Diagnosis extends Entity{
	
	private static final long serialVersionUID = 8466257860808349874L;
	
	private String diagnosisName;
	private String diagnosisNameRu;

	public String getDiagnosisNameRu() {
		return diagnosisNameRu;
	}

	public void setDiagnosisNameRu(String diagnosisNameRu) {
		this.diagnosisNameRu = diagnosisNameRu;
	}

	public String getDiagnosisName() {
		return diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}

	@Override
	public String toString() {
		return "Diagnosis [diagnosisName=" + diagnosisName + ", diagnosisNameRu=" + diagnosisNameRu + "]";
	}
	
	
}