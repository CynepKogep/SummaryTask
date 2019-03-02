package ua.kharkov.khpi.database.enums;

import ua.kharkov.khpi.database.beans.PatientAssignment;

//Assignment status
public enum Status {
	IN_PROGRESS, COMPLETE;
	
	public static Status getStatus(PatientAssignment patientAssignment) {
		int statusId = patientAssignment.getAssignment_status_id();
		return Status.values()[statusId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
}
