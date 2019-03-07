package ua.kharkov.khpi.database.beans;

import ua.kharkov.khpi.database.enums.Assignment;
import ua.kharkov.khpi.database.enums.Status;


// назначение
public class PatientAssignment extends Entity {

	private static final long serialVersionUID = 8467257860808346237L;

	private int patient_id;
	private int assignment_id;
	private int assignment_status_id;
	
	//util fields
	private String assignmentName;
	private String assignmentStatusName;
	

	public String getAssignmentName() {
		if(assignmentName == null) {
			assignmentName = Assignment.getAssignment(this).getName();
		}
		return assignmentName;
	}

	public String getAssignmentStatusName() {
		if(assignmentStatusName == null) {
			assignmentStatusName = Status.getStatus(this).getName();
		}
		return assignmentStatusName;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public int getAssignment_id() {
		return assignment_id;
	}

	public void setAssignment_id(int assignment_id) {
		this.assignment_id = assignment_id;
	}

	public int getAssignment_status_id() {
		return assignment_status_id;
	}

	public void setAssignment_status_id(int assignment_status_id) {
		this.assignment_status_id = assignment_status_id;
	}
}
