package ua.kharkov.khpi.database.enums;

import ua.kharkov.khpi.database.beans.PatientAssignment;

public enum Assignment {
	PROCEDURE, PILLS, OPERATION;
	
	public static Assignment getAssignment(PatientAssignment patientAssignment) {
		int assignmentID = patientAssignment.getAssignment_id();
		return Assignment.values()[assignmentID];
	}
	
	public static int getIndex(String assignment) {
		for(int i = 0; i < Assignment.values().length; i++) {
			if (assignment.toLowerCase().equals(Assignment.values()[i].getName()))
				return i;
		}
		return -1;
	}
	
	public String getName() {
		return name().toLowerCase();
	}
}