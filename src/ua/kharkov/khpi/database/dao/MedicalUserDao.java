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
	
    // ------------------------------------------------------------------------------------------------	
	private static final String SQL_FIND_MEDICAL_USER_BY_LOGIN = 
	    "SELECT * FROM medical_user " +
	    "WHERE login = ?";
	private static final String SQL_FIND_MEDICAL_USER =     
		"SELECT * FROM medical_user " + 
	    "WHERE role_id = 0 OR role_id = 1 OR role_id = 2";
	private static final String SQL_NUMBUR_OF_PATIENTS =
		"SELECT COUNT(patient.doctor_id) AS Count FROM patient " +
		"WHERE doctor_id = ?";
	private static final String SQL_ADD_NEW_MEDICAL_USER = 
		"INSERT INTO medical_user(login, password, first_name, last_name, first_name_ru, last_name_ru, profession_id, role_id) " + 
	    "                 VALUES (?,     ?,        ?,          ?,         ?,             ?,            ?,             ?)";
	private static final String SQL_FIND_MEDICAL_USER_BY_ID = 
		"SELECT * " +
		"FROM medical_user WHERE id=?";
	// ------------------------------------------------------------------------------------------------
	public MedicalUser findMedicalUserByLogin (String login)
	{
		MedicalUser user = null;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
			con = DBManager.getInstance().getConnection();
			MadicalUserMapper mapper = new MadicalUserMapper();
			pstmt = con.prepareStatement(SQL_FIND_MEDICAL_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next())
     			user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
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
            rs.close();
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
			pstmt = con.prepareStatement(SQL_NUMBUR_OF_PATIENTS);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
			    count = Integer.parseInt(rs.getString("Count"));
            rs.close();
            pstmt.close();
		} catch (Exception ex) {
			DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
		return count;
	}
	
	public void addMedicalUser(MedicalUser user) {
		PreparedStatement pstmt = null;
        Connection con = null;
		try {
			int k = 1;
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_ADD_NEW_MEDICAL_USER);
			pstmt.setString(k++,user.getLogin());
			pstmt.setString(k++,user.getPassword());
			pstmt.setString(k++,user.getFirstName());
			pstmt.setString(k++,user.getLastName());
			pstmt.setString(k++,user.getFirstNameRu());
			pstmt.setString(k++,user.getLastNameRu());
			pstmt.setInt(k++,user.getProfessionId());
			pstmt.setInt(k++,user.getRoleId());
			pstmt.executeUpdate();
	        pstmt.close();  
		} catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
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
	
	public MedicalUser getDoctorById(long id) {
		MedicalUser med = null;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
			con = DBManager.getInstance().getConnection();
			MadicalUserMapper mapper = new MadicalUserMapper();
			pstmt = con.prepareStatement(SQL_FIND_MEDICAL_USER_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
			    med = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
		return med;
	}	
}
