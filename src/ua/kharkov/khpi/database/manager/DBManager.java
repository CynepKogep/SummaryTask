package ua.kharkov.khpi.database.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * DB manager. Works with MySQL DB. 
 * Only the required DAO methods are defined!
 * 
 */
public class DBManager {

	private static final Logger log = Logger.getLogger(DBManager.class);
	
	////////////////////////////////////////////////////////////
	//                   singleton                            //
	////////////////////////////////////////////////////////////

	private static DBManager instance;
	
	private DBManager() {
	}

	public static synchronized DBManager getInstance() {
		if (instance == null)
			instance = new DBManager();
		return instance;
	}
    
	////////////////////////////////////////////////////////////
	
	/**
	 * Returns a DB connection from the Pool Connections. Before using this
	 * method you must configure the Date Source and the Connections Pool in your
	 * WEB_APP_ROOT/META-INF/context.xml file.
	 * 
	 * @return A DB connection.
	 * 
	 */
	public Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			// clinicDB - the name of data source
			DataSource ds = (DataSource) envContext.lookup("jdbc/hospitalDB");
			con = ds.getConnection();
		} catch (NamingException ex) {
			ex.printStackTrace();
			log.error("Cannot obtain a connection from the pool", ex);
		}
		return con;
	}

	////////////////////////////////////////////////////////////
	//              DB util methods                           //
	////////////////////////////////////////////////////////////

	/**
	 * Commits and close the given connection.
	 * 
	 * @param con - Connection to be committed and closed.
	 * 
	 */
	public void commitAndClose(Connection con) {
		try {
			con.commit();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Rollbacks and close the given connection.
	 * 
	 * @param con - Connection to be rollbacked and closed.
	 * 
	 */
	public void rollbackAndClose(Connection con) {
		try {
			con.rollback();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**************** THIS METHOD IS NOT USED IN THE PROJECT *******/
	
	/**
	 * Returns a DB connection. This method is just for a example how to use the
	 * DriverManager to obtain a DB connection. It does not use a pool
	 * connections and not used in this project. It is preferable to use
	 * {@link #getConnection()} method instead.
	 * 
	 * @return A DB connection.
	 * 
	 */
	
	public Connection getConnectionWithDriverManager() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/hospitaldatabase?useUnicode=true"
						+ "&useJDBCCompliantTimezoneShift=true"
						+ "&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "dima");
		connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		connection.setAutoCommit(false);
		return connection;
	}

	/**************************************************************/
}
