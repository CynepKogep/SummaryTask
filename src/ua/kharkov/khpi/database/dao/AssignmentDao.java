package ua.kharkov.khpi.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.kharkov.khpi.database.beans.PatientAssignment;
import ua.kharkov.khpi.database.constants.Fields;
import ua.kharkov.khpi.database.manager.DBManager;

public class AssignmentDao {

	private static final String SQL_FIND_ALL_ASSIGNMENT = 
		"SELECT * " +
		"FROM patient_assignment";
	private static final String SQL_FIND_PATIENT_ASSIGNMENTS =
		"SELECT * " +
		"FROM patient_assignment WHERE patient_id=?";
	private static final String SQL_ADD_ASSIGNMENT = 
		"INSERT INTO patient_assignment(patient_id, assignment_id, assignment_status_id)" +
		                        "VALUES(?,          ?,             ?)";
	private static final String SQL_COMPLETE_ASSIGNMENT_BY_ID = 
		"UPDATE patient_assignment SET assignment_status_id = 1 WHERE id = ?";
	private static final String SQL_FINND_ALL_ASSIGNMENT_FOR_NURCE = 
		"SELECT * " +
		"FROM patient_assignment WHERE assignment_id = 0 OR assignment_id = 1";
	
	public void CompleteAssignmentById (int id) {
		PreparedStatement pstmt = null;
        Connection con = null;
        try {
        	con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_COMPLETE_ASSIGNMENT_BY_ID);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
	        pstmt.close();
		} catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
	}
	
	public void AddAssignment (long patient_id, int assignment_id) {
		PreparedStatement pstmt = null;
        Connection con = null;
		try {
			int k = 1;
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_ADD_ASSIGNMENT);
			pstmt.setLong(k++,patient_id);
			pstmt.setInt(k++, assignment_id);
			pstmt.setInt(k++, 0);
			pstmt.executeUpdate();
	        pstmt.close();
		} catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
	}
	
	public List<PatientAssignment> getPatientAssignments(long id){
		List<PatientAssignment> assignmentList = new ArrayList<>();
		PreparedStatement pstmt = null;
        Connection con = null;
        ResultSet rs = null;
        try {
        	con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_FIND_PATIENT_ASSIGNMENTS);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			AssignmentMapper mapper = new AssignmentMapper();
			while (rs.next()) {
	            	assignmentList.add(mapper.mapRow(rs));
			}
		} catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return assignmentList;
	}
	
	public List<PatientAssignment> getAssignments() {
		List<PatientAssignment> assignmentList = new ArrayList<>();
		Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;
        try {
			con = DBManager.getInstance().getConnection();
			stmt = con.createStatement();
			AssignmentMapper mapper = new AssignmentMapper();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_ASSIGNMENT);
			while (rs.next()) {
				assignmentList.add(mapper.mapRow(rs));}
			return assignmentList;
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
        return assignmentList;
	}
	
	public List<PatientAssignment> getAssignmentsforNurce() {
		List<PatientAssignment> assignmentList = new ArrayList<>();
		Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;
        try {
			con = DBManager.getInstance().getConnection();
			stmt = con.createStatement();
			AssignmentMapper mapper = new AssignmentMapper();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FINND_ALL_ASSIGNMENT_FOR_NURCE);
			while (rs.next()) {
				assignmentList.add(mapper.mapRow(rs));}
			return assignmentList;
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
        return assignmentList;
	}

	private static class AssignmentMapper implements EntityMapper<PatientAssignment> {
		@Override
		public PatientAssignment mapRow(ResultSet rs) {
			try {
				PatientAssignment assignment = new PatientAssignment();
				assignment.setId(rs.getLong(Fields.ENTITY__ID));
				assignment.setPatient_id(rs.getInt(Fields.PATIENT_ID));
				assignment.setAssignment_id(rs.getInt(Fields.ASSIGNMENT_ID));
				assignment.setAssignment_status_id(rs.getInt(Fields.ASSIGNMENT_STATUS_ID));
				return assignment;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}
