package ua.kharkov.khpi.database.constants;

/**
 * Holder for fields names of DB tables and beans.
 * 
 */
public final class Fields {
	// entities
	public static final String ENTITY__ID = "id";                           // 1
	// TABLE <<MEDICAL_USER>>
	public static final String MEDICAL_USER_LOGIN         = "login";        // 2
	public static final String MEDICAL_USER_PASSWORD      = "password";     // 3
	public static final String MEDICAL_USER_FIRST_NAME    = "first_name";   // 4
	public static final String MEDICAL_USER_LAST_NAME     = "last_name";    // 5
	public static final String MEDICAL_USER_FIRST_NAME_RU = "first_name_ru";// 6 
	public static final String MEDICAL_USER_LAST_NAME_RU  = "last_name_ru"; // 7
	public static final String MEDICAL_USER_PROFESSION_ID = "profession_id";// 8 
	public static final String MEDICAL_USER_ROLE_ID       = "role_id";      // 9 
	// TABLE <<PATIENT>>
	public static final String PATIENT_FIRST_NAME    = "first_name";        // 2
	public static final String PATIENT_LAST_NAME     = "last_name";         // 3
	public static final String PATIENT_FIRST_NAME_RU = "first_name_ru";     // 4
	public static final String PATIENT_LAST_NAME_RU  = "last_name_ru";      // 5
	public static final String PATIENT_DOCTOR_ID     = "doctor_id";         // 6
	public static final String PATIENT_DATE_OF_BIRTH = "date_of_birth";     // 7 
	public static final String PATIENT_TELEPHON      = "telephon_number";   // 8 
	public static final String PATIENT_EMAIL         = "email";             // 9 
	public static final String PATIENT_DISCHARGED    = "discharged";        // 10
	public static final String PATIENT_DIAGNOSIS_ID  = "diagnosis_id";      // 11
	// TABLE <<ROLE>>
	public static final String ROLE_NAME         = "role_name";
	// TABLE <<PROFESSION>>
	public static final String PROFESSION_NAME   = "profession_name";
	// TABLE <<DIAGNOSIS>>
	public static final String DIAGNOSIS_NAME    = "diagnosis_name";
	// TABLE <<ASSIGNMENT_STATUS>>
	public static final String ASSIGNMENT_STATUS = "status_name";
	// TABLE <<ASSIGNMENT_TYPE>>
	public static final String ASSIGNMENT_TYPE   = "type_name";
	// TABLE <<PATIENT_ASSIGNMENT>>
	public static final String PATIENT_ID           = "patient_id";
	public static final String ASSIGNMENT_ID        = "assignment_id";
	public static final String ASSIGNMENT_STATUS_ID = "assignment_status_id";
}
