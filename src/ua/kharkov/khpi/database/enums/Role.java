package ua.kharkov.khpi.database.enums;

import ua.kharkov.khpi.database.beans.MedicalUser;

public enum Role {
	ADMIN, DOCTOR, NURSE;
	
	public static Role getRole(MedicalUser med) {
		int roleID = med.getRoleId();
		return Role.values()[roleID];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
}

