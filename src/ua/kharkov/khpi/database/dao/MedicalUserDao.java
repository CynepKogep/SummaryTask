package ua.kharkov.khpi.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.kharkov.khpi.database.beans.MedicalUser;
import ua.kharkov.khpi.database.constants.Fields;
import ua.kharkov.khpi.database.manager.DBManager;

public class MedicalUserDao {
	
	private static final String SQL_FIND_ALL_DOCTORS =
            "SELECT * FROM medical_stuff WHERE role_id=1 OR role_id=2";
	private static final String SQL_FIND_MED_STAFF_BY_LOGIN = "SELECT * FROM medical_stuff WHERE login=?";
	private static final String SQL_ADD_NEW_MEDICAL_STAFF = "INSERT INTO medical_stuff(login, password, first_name, last_name, category_id, role_id) "
																					+ "VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_FIND_DOC_BY_ID = "SELECT * FROM medical_stuff WHERE id=?";
	private static final String SQL_NUMB_OF_PATIENTS ="select count(patient.doctor_id) as Count from patient where doctor_id = ?";
	

	private static final String SQL_FIND_MEDICAL_USER_BY_ID = "SELECT * FROM medical_user WHERE id=?";
	
	
    // ------------------------------------------------------------------------------------------------	
	private static final String SQL_FIND_MEDICAL_USER_BY_LOGIN = 
	    "SELECT * FROM medical_user " +
	    "WHERE login = ?";
	private static final String SQL_FIND_MEDICAL_USER =     
		"SELECT * FROM medical_user " + 
	    "WHERE role_id = 0 OR role_id = 1 OR role_id = 2";

	// ------------------------------------------------------------------------------------------------
	
	public MedicalUser findMedicalUserByLogin (String login)
	{
		MedicalUser user = null;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_FIND_MEDICAL_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			rs.next();
			MadicalUserMapper mapper = new MadicalUserMapper();
			user = mapper.mapRow(rs);
			return user;
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
		return user;
	}
	
	public List<MedicalUser> getMedicalUsers() {
		List<MedicalUser> doctorsList = new ArrayList<>();
		Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			stmt = con.createStatement();
			MadicalUserMapper mapper = new MadicalUserMapper();
            rs = stmt.executeQuery(SQL_FIND_MEDICAL_USER);
            while (rs.next())
            	doctorsList.add(mapper.mapRow(rs));
		} catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
		return doctorsList;
	}
	private static class MadicalUserMapper implements EntityMapper<MedicalUser> {
		@Override
		public MedicalUser mapRow(ResultSet rs) {
			try {
				MedicalUser med = new MedicalUser();
				med.setId(rs.getLong(Fields.ENTITY__ID));
				med.setLogin(rs.getString(Fields.MEDICAL_USER_LOGIN));
				med.setPassword(rs.getString(Fields.MEDICAL_USER_PASSWORD));
				med.setFirstName(rs.getString(Fields.MEDICAL_USER_FIRST_NAME));
				med.setLastName(rs.getString(Fields.MEDICAL_USER_LAST_NAME));
				med.setFirstNameRu(rs.getString(Fields.MEDICAL_USER_FIRST_NAME_RU));
				med.setLastNameRu(rs.getString(Fields.MEDICAL_USER_LAST_NAME_RU));
				med.setProfessionId(rs.getInt(Fields.MEDICAL_USER_PROFESSION_ID));
				med.setRoleId(rs.getInt(Fields.MEDICAL_USER_ROLE_ID));
				return med;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}
	// ----------------------------------------------------------------------------------
	
	
	
	
	public List<MedicalUser> getDoctors() {
		List<MedicalUser> doctorsList = new ArrayList<>();
		Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			stmt = con.createStatement();
			MadicalUserMapper mapper = new MadicalUserMapper();
            rs = stmt.executeQuery(SQL_FIND_ALL_DOCTORS);
            while (rs.next())
            	doctorsList.add(mapper.mapRow(rs));
		} catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
		return doctorsList;
	}
	
	public int getCountOfPatientsForDoctorId(long id) {
		int count = 0;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
        	con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_NUMB_OF_PATIENTS);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			count = Integer.parseInt(rs.getString("Count"));
		} catch (Exception ex) {
			DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
		return count;
	}
	
	public MedicalUser getDoctorById(long id) {
		MedicalUser med = null;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_FIND_DOC_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			MadicalUserMapper mapper = new MadicalUserMapper();
			med = mapper.mapRow(rs);
			return med;
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
		return med;
	}
	

	public MedicalUser getMedByLogin (String login) {
		MedicalUser med = null;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_FIND_MED_STAFF_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			rs.next();
			MadicalUserMapper mapper = new MadicalUserMapper();
			med = mapper.mapRow(rs);
			return med;
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
		return med;
	}
	
	public void addMedicalStaff(MedicalUser med) {
		PreparedStatement pstmt = null;
        Connection con = null;
		try {
			int k = 1;
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_ADD_NEW_MEDICAL_STAFF);
			pstmt.setString(k++,med.getLogin());
			pstmt.setString(k++,med.getPassword());
			pstmt.setString(k++,med.getFirstName());
			pstmt.setString(k++,med.getLastName());
			pstmt.setInt(k++,med.getProfessionId());
			pstmt.setInt(k++,med.getRoleId());
			pstmt.executeUpdate();
	        pstmt.close();  
		} catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
	}

}
