package com.fortex.quickRing.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;

import com.fortex.lib.globalservices.GlobalRuntime;
import com.fortex.quickRing.model.FxTableModel;
import com.fortex.quickRing.model.UserModel;
import com.fortex.quickRing.utils.ConfigSetting;

public class DBOperation {
	private static BasicDataSource dataSource;
	static {
		dataSource = new BasicDataSource();		
		dataSource.setDriverClassName(ConfigSetting.getDatabaseProperty("driverClass"));
		dataSource.setUrl(ConfigSetting.getDatabaseProperty("url"));
		dataSource.setUsername(ConfigSetting.getDatabaseProperty("username"));
		dataSource.setPassword(ConfigSetting.getDatabaseProperty("password"));				
		dataSource.setValidationQuery(ConfigSetting.getDatabaseProperty("validationQuery"));		
		dataSource.setInitialSize(Integer.parseInt(ConfigSetting.getDatabaseProperty("initialSize")));
		dataSource.setMaxTotal(Integer.parseInt(ConfigSetting.getDatabaseProperty("maxTotal")));
		dataSource.setMaxWaitMillis(Integer.parseInt(ConfigSetting.getDatabaseProperty("maxWaitMillis")));
		dataSource.setRemoveAbandonedOnBorrow(true);
		dataSource.setRemoveAbandonedTimeout(Integer.parseInt(ConfigSetting.getDatabaseProperty("removeAbandonedTimeout")));
		dataSource.setPoolPreparedStatements(true);
	}
	
	/**
	 * 
	 * <p>Description:Check if the account exists and the password match</p> 
	 *
	 * @author Patrick Chi
	 * @date 2016-08-05 
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public static UserModel getUserInfo(String userName, String password, String targetId, int loginType) throws SQLException {
		Connection conn = dataSource.getConnection();
		String hashUserName= GlobalRuntime.hashPwd(userName);
		String hashPassword= GlobalRuntime.hashPwd(password);
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UserModel user = null;
		try{
			StringBuffer sb = new StringBuffer();			 
			
			sb = new StringBuffer();
			sb.append("SELECT domain,quoteService FROM loginTable lt,systemconfigs sc WHERE ")
			.append(" lt.LoginHash = ? AND lt.PasswordHash = ? AND lt.TargetID=? AND lt.loginType=? AND")
			.append(" lt.domain != sc.DomainClosedAccts");
			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, hashUserName);
			stmt.setString(2, hashPassword);
			stmt.setString(3, targetId);
			stmt.setInt(4, loginType);
			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new UserModel(rs.getInt("domain"), rs.getInt("quoteService"), userName);
			}
		}finally{
			close(rs,stmt,conn);
		}
		return user;
	}
	
	
	public static Boolean isBlocking(String userName) throws SQLException {
		Connection conn = dataSource.getConnection();		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean isBlocking=false;
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id FROM xRing_BlockAccount where account =? ");
			stmt= conn.prepareStatement(sb.toString());
			stmt.setString(1, userName);
			rs = stmt.executeQuery();
			if(rs.next())
				isBlocking=true;						
			
		}finally{
			close(rs,stmt,conn);
			
		}
		return isBlocking;
	}
	
	
	/**
	 * <p>Description:Get domain by account.</p>
	 * @author Ivan Huo
	 * @date 2016-08-25	
	 * @return
	 * @throws SQLException
	 */ 
	public static int getDomainByAccount(String account) throws SQLException {
		Connection conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("SELECT domain FROM LoginTable WHERE login = ?");
			stmt.setString(1, account);
			rs = stmt.executeQuery();
			int domain = 0;
			if (rs.next())
				domain = rs.getInt(1);
			return domain;
		} finally {
			close(rs,stmt,conn);
		}
	}
	
	
	/**
	 * <p>Description:Get all symbols from database.</p>
	 * @author Ivan Huo
	 * @date 2016-08-25	
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, FxTableModel> getSymbols() throws SQLException {
		Map<String, FxTableModel> symbols = new HashMap<String, FxTableModel>();
		Connection conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("SELECT FXSymbol,DECIMAL FROM fxtable");			
			rs = stmt.executeQuery();				 
 			while(rs.next()){
 				FxTableModel model = new FxTableModel();
 				model.setSymbol(rs.getString("FXSymbol"));
 				if(model.getSymbol() != null && model.getSymbol().startsWith("#"))
 					model.setSymbol(model.getSymbol().substring(1));
 				model.setDecimal(rs.getInt("DECIMAL"));
				symbols.put(model.getSymbol(), model);
			}
			return symbols;
		} finally {
			close(rs,stmt,conn);
		}
	}
	
	/**
	 * <p>Description:close ResultSet,Statement and Connection.</p>
	 * @author Ivan Huo
	 * @date 2016-08-25	
	 * @return
	 * @throws SQLException
	 */
	private static void close(ResultSet rs,Statement stmt,Connection conn) {
		if(rs != null)
			try {rs.close();} catch (SQLException e) {}
		if(stmt != null)
			try {stmt.close();} catch (SQLException e) {}
		if(conn != null)
			try {conn.close();} catch (SQLException e) {}
	}
}

