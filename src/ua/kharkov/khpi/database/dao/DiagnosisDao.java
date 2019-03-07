package ua.kharkov.khpi.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.kharkov.khpi.database.beans.Diagnosis;
import ua.kharkov.khpi.database.constants.Fields;
import ua.kharkov.khpi.database.manager.DBManager;


public class DiagnosisDao {
	private static final String SQL_GET_DIAGNOSIS = "SELECT * FROM diagnosis";
	private static final String SQL_GET_DIAGNOS_BY_ID = "SELECT * FROM diagnosis WHERE id = ?";
	
	public Diagnosis getDiagnosById(long id) {
		Diagnosis diagnos = null;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_GET_DIAGNOS_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			DiagnossMapper mapper = new DiagnossMapper();
			diagnos = mapper.mapRow(rs);
			return diagnos;
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
		return diagnos;
	}
	
	public List<Diagnosis> GetDiagnoses() {
		List<Diagnosis> diagnosList= new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			stmt = con.createStatement();
			DiagnossMapper mapper = new DiagnossMapper();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_GET_DIAGNOSIS);
			while (rs.next())
				diagnosList.add(mapper.mapRow(rs));
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return diagnosList;
	}
	
	private static class DiagnossMapper implements EntityMapper<Diagnosis> {
		@Override
		public Diagnosis mapRow(ResultSet rs) {
			try {
				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setId(rs.getLong(Fields.ENTITY__ID));
				diagnosis.setDiagnosisName(rs.getString(Fields.DIAGNOSIS_NAME));
				diagnosis.setDiagnosisNameRu(rs.getString(Fields.DIAGNOSIS_NAME_RU));
				return diagnosis;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}
