package ua.kharkov.khpi.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.kharkov.khpi.database.beans.Profession;
import ua.kharkov.khpi.database.constants.Fields;
import ua.kharkov.khpi.database.manager.DBManager;

public class ProfessionDao {
	private static final String SQL_FIND_ALL_PROFESSION = "SELECT * FROM profession GROUP BY id;";
	private static final String SQL_GET_PROFESSION_BY_ID = "SELECT * FROM profession WHERE ID = ?";

	public List<Profession> getProfession() {
		List<Profession> professionList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBManager.getInstance().getConnection();
			stmt = con.createStatement();
			ProfessionMapper mapper = new ProfessionMapper();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_PROFESSION);
			while (rs.next())
				professionList.add(mapper.mapRow(rs));
		} catch (SQLException ex) {
			DBManager.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBManager.getInstance().commitAndClose(con);
		}
		return professionList;
	}

	public Profession getProfessionById(long id) {
		Profession profession = null;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
			con = DBManager.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_GET_PROFESSION_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			ProfessionMapper mapper = new ProfessionMapper();
			profession = mapper.mapRow(rs);
			return profession;
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
		return profession;
	}
	
	private static class ProfessionMapper implements EntityMapper<Profession> {
		@Override
		public Profession mapRow(ResultSet rs) {
			try {
				Profession category = new Profession();
				category.setId(rs.getLong(Fields.ENTITY__ID));
				category.setProfessionName(rs.getString(Fields.PROFESSION_NAME));
				category.setProfessionNameRu(rs.getString(Fields.PROFESSION_NAME_RU));
				return category;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}
	
}
