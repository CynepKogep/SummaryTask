package ua.kharkov.khpi.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.kharkov.khpi.database.beans.Patient;
import ua.kharkov.khpi.database.constants.Fields;
import ua.kharkov.khpi.database.manager.DBManager;

public class PatientDao {
	private static final String SQL_FIND_ALL_PATIENTS = "SELECT * FROM patient";
	private static final String SQL_FIND_PATIENT_BY_ID = "SELECT * FROM patient where id=?";
	private static final String SQL_ADD_NEW_PATIENT = 
	    "INSERT INTO patient (first_name, last_name, first_name_ru, last_name_ru, doctor_id, date_of_birth, telephon_number, email) " 
	+                "VALUES (?,          ?,         ?,             ?,            ?,         ?,             ?,               ?)";
	private static final String SQL_FIND_DOCTORS_PATIENTS = "SELECT * FROM patient WHERE doctor_id=?";
	private static final String SQL_SET_DIAGNOSIS_BY_PATIENT_ID = 
		"UPDATE patient SET diagnosis_id = ? WHERE id = ?";
	private static final String SQL_DISCHARGED_PATIENT_BY_ID = "UPDATE patient SET discharged=1 WHERE id=?";
	
	
	public void DischargedPatient(long id) {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_DISCHARGED_PATIENT_BY_ID);
			pstmt.setLong(1,id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
	}
	
	public void setDiagnosis(int diagnosis_id, long id) {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			int k = 1;
			pstmt = con.prepareStatement(SQL_SET_DIAGNOSIS_BY_PATIENT_ID);
			pstmt.setInt(k++, diagnosis_id);
			pstmt.setLong(k++, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
	}
	
	public Patient getPatientById(Long id) {
		Patient patient = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_FIND_PATIENT_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			PatientMapper mapper = new PatientMapper();
			patient = mapper.mapRow(rs);
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return patient;
	}

	public List<Patient> getPatients() {
		List<Patient> patientList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			stmt = con.createStatement();
			PatientMapper mapper = new PatientMapper();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_PATIENTS);
			while (rs.next())
				patientList.add(mapper.mapRow(rs));
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return patientList;
	}

	public List<Patient> getPatientsByDoctorId(Long id) {
		List<Patient> patientList = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_FIND_DOCTORS_PATIENTS);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			PatientMapper mapper = new PatientMapper();
			while (rs.next()) {
				patientList.add(mapper.mapRow(rs));
			}
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return patientList;
	}

	public void addNewPatient(Patient patient) {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			int k = 1;
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_ADD_NEW_PATIENT);
			pstmt.setString(k++, patient.getFirstName());
			pstmt.setString(k++, patient.getLastName());
			pstmt.setString(k++, patient.getFirstNameRu());
			pstmt.setString(k++, patient.getLastNameRu());
			pstmt.setInt(k++, patient.getDoctor_id());
			pstmt.setDate(k++, patient.getDateOfBirth());
			pstmt.setString(k++, patient.getTelephoneNumber());
			pstmt.setString(k++, patient.getEmail());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
	}

	private static class PatientMapper implements EntityMapper<Patient> {
		@Override
		public Patient mapRow(ResultSet rs) {
			try {
				Patient patient = new Patient();
				patient.setId(rs.getLong(Fields.ENTITY__ID));
				patient.setFirstName(rs.getString(Fields.PATIENT_FIRST_NAME));
				patient.setLastName(rs.getString(Fields.PATIENT_LAST_NAME));
				patient.setFirstNameRu(rs.getString(Fields.PATIENT_FIRST_NAME_RU));
				patient.setLastNameRu(rs.getString(Fields.PATIENT_LAST_NAME_RU));
				patient.setDoctor_id(rs.getInt(Fields.PATIENT_DOCTOR_ID));
				patient.setDateOfBirth(rs.getDate(Fields.PATIENT_DATE_OF_BIRTH));
				patient.setTelephoneNumber(rs.getString(Fields.PATIENT_TELEPHON));
				patient.setEmail(rs.getString(Fields.PATIENT_EMAIL));
				patient.setDischarged(rs.getBoolean(Fields.PATIENT_DISCHARGED));
				patient.setDiagnosis_id(rs.getInt(Fields.PATIENT_DIAGNOSIS_ID));
				return patient;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}
