package com.caishuo.IAT.publicFunction.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebDbUtil implements selectDispatch{
	Connection conn_CaseDB = null;
	Connection conn_BusinessDB = null;
	Connection conn = null;

	String jdbcName = null;
	String dbUrl = null;
	String dbUserName = null;
	String dbPassword = null;

	public void set_Conn(Connection new_Conn) {
		this.conn = new_Conn;
	}

	public Connection get_Conn() {
		return this.conn;
	}

	/**
	 * @author zhengyi
	 * @return
	 * @Target 数据库连�?
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection connect() throws Exception {
		// public Connection connect(String conn_Type) throws Exception {
		//ReadXML.readXML("config_InterfaceAutoTest.xml");
//		System.out.println(Constant.CaseDB_jdbcName);

		// if (conn == null) {
		// if (conn_Type == "CaseDB"){
		// jdbcName = Constant.CaseDB_jdbcName;
		// dbUrl =
		// Constant.CaseDB_HOST+"??useUnicode=true&characterEncoding=UTF-8";
		// dbUserName = Constant.CaseDB_DBUser;
		// dbPassword = Constant.CaseDB_DBPWD;
		// }else if (conn_Type == "BusinessDB"){
		// jdbcName = Constant.BusinessDB_jdbcName;
		// dbUrl =
		// Constant.BusinessDB_HOST+"??useUnicode=true&characterEncoding=UTF-8";
		// dbUserName = Constant.BusinessDB_DBUser;
		// dbPassword = Constant.BusinessDB_DBPWD;
		// }else {
		// System.err.println("不存在的数据库类�?");
		// }

		// <CaseDB_HOST>jdbc:mysql://192.168.1.59:3306/test</CaseDB_HOST>
		// <CaseDB_jdbcName>com.mysql.jdbc.Driver</CaseDB_jdbcName>
		// <CaseDB_DBUser>test</CaseDB_DBUser>
		// <CaseDB_DBPWD>123456</CaseDB_DBPWD>

		jdbcName = "com.mysql.jdbc.Driver";
		dbUrl = "jdbc:mysql://192.168.1.59:3306/test??useUnicode=true&characterEncoding=UTF-8";
		dbUserName = "test";
		dbPassword = "123456";
		Class.forName(jdbcName);
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		this.set_Conn(conn);
		// }
		// System.out.println("111111111111"+conn.toString());
		return conn;
	}

	public void release() throws Exception {
		if (conn != null) {
			this.conn.close();

		}
	}

	/**
	 * @author zhengyi
	 * @param rs
	 * @return
	 * @Target 查询数据�?
	 * @throws Exception
	 */
	public Map<String, String> select(String sql_string) throws Exception {
		ResultSet rs;

		Connection conn = connect();

		Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(sql_string);

		// @SuppressWarnings("unused")
		// List<Map> rc_List = resultSetToList(rs);
		// System.out.println(rc_List);

		rs.next();
		Map<String, String> rc = getResultMap(rs);

		conn.close();
		return rc;
	}

	@SuppressWarnings("rawtypes")
	public List<Map> selectToList(String sql_string) throws Exception {
		ResultSet rs;
		Connection conn = connect();

		Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(sql_string);

		List<Map> rc_List = resultSetToList(rs);

		conn.close();
		return rc_List;
	}

	// ResultSet �? MAP
	private static Map<String, String> getResultMap(ResultSet rs) throws SQLException {
		Map<String, String> hm = new HashMap<String, String>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		for (int i = 1; i <= count; i++) {
			String key = rsmd.getColumnLabel(i);
			String value = rs.getString(i);
			hm.put(key, value);
		}
		return hm;
	}

	@SuppressWarnings("rawtypes")
	public static List<Map> resultSetToList(ResultSet rs) throws java.sql.SQLException {

		if (rs == null)
			return Collections.emptyList();

		ResultSetMetaData md = rs.getMetaData(); // 得到结果�?(rs)的结构信息，比如字段数�?�字段名�?
		int columnCount = md.getColumnCount(); // 返回�? ResultSet 对象中的列数
		List<Map> list = new ArrayList<Map>();
		Map<String, Object> rowData = new HashMap<String, Object>();
		while (rs.next()) {
			rowData = new HashMap<String, Object>(columnCount);
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(rowData);
			// System.out.println("list:" + list.toString());
		}

		return list;

	}

	/**
	 * @author zhengyi
	 * @Target 查询数据库并将结果（ResultSet格式）转换成List<Map>
	 * @param String		sql_string �?要查询的SQL语句
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> resultSetToListMap(String sql_string) throws Exception {

		ResultSet rs;

		Connection conn = connect();
		// if (rs == null)
		// return Collections.emptyList();

		// System.out.println("conn:"+conn.hashCode());
		Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(sql_string);

		ResultSetMetaData md = rs.getMetaData(); // 得到结果�?(rs)的结构信息，比如字段数�?�字段名�?
		int columnCount = md.getColumnCount(); // 返回�? ResultSet 对象中的列数
		List<Map> list = new ArrayList<Map>();
		Map<String, Object> rowData = new HashMap<String, Object>();
		while (rs.next()) {
			// System.out.println("list2:");
			rowData = new HashMap<String, Object>(columnCount);
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(rowData);
			// System.out.println("list:" + list.toString());
		}
		conn.close();
		return list;
	}

	/**
	 * @author zhengyi
	 * @Target 查询数据库dispatch�?
	 * @param String		table_Name �?要查询的表名
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> selectDispatch(String table_Name) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		if (table_Name.toLowerCase().equals("dispatch")) {
			table_Name = "SELECT id, case_tablename, is_running, execute_result, execute_errormsg, "
					+ "execute_Time, num_pass_case, num_fail_case, num_run_case, num_all_case FROM test.dispatch;";
		} else {
			result.put("code", "error");
			result.put("message", "table no exise");
			return result;
		}

		List<Map> rc = this.resultSetToListMap(table_Name);
		result.put("code", "success");
		result.put("obj", rc);
		return result;
	}
	
	/**
	 * @author 	zhengyi
	 * @Target 	查询数据库Case�?
	 * @param	String		sql_string �?要查询的SQL语句
	 * @throws 	Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> selectCase(String table_Name) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		if (table_Name.toLowerCase().equals("others_login")) {
			table_Name = "SELECT 'id', 'case', 'group', script, run, input, expect, result, "+
								"errormsg, 'comment', 'time' FROM test.others_login;";

		} else {
			result.put("code", "error");
			result.put("message", "table no exise");
			return result;
		}

		List<Map> rc = this.resultSetToListMap(table_Name);
		result.put("code", "success");
		result.put("obj", rc);
		return result;
	}

	/**
	 * @author zhengyi
	 * @return
	 * @throws Exception
	 * @Target 插入数据�?
	 */
	public void insert(String conn_Type, String sql_string) throws Exception {
		// 连接数据�?
		// conn = get_Conn();
		// conn = this.connect(conn_Type);
		Connection conn = connect();

		try {
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sql_string);

			if (rs == 1) {
				// System.out.println("Insert operation is successful!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author zhengyi
	 * @return
	 * @throws Exception
	 * @Target 更新数据�?
	 */
	public String update(String conn_Type, String sql_string) throws Exception {
		// 连接数据�?
		// conn = get_Conn();
		// conn = this.connect(conn_Type);
		Connection conn = connect();

		try {
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sql_string);
			// int rs = stmt.execute

			if (rs == 1) {
				// System.out.println("Update operation is successful!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "0";
	}

	/**
	 * @author zhengyi
	 * @return
	 * @throws Exception
	 * @Target 更新数据�?
	 */
	public String delete(String conn_Type, String sql_string) throws Exception {
		// 连接数据�?
		// conn = get_Conn();
		// conn = this.connect(conn_Type);
		Connection conn = connect();

		try {
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sql_string);

			if (rs == 1) {
				// System.out.println("Update operation is successful!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "0";
	}


	/**
	 * @Target 将执行结果更新至日志�?
	 * @param obj_Mysql
	 * @param c_Id
	 * @param str_Result
	 * @param str_ErrorMSG
	 * @param case_tablename
	 * @param c_CaseApiName
	 * @throws Exception
	 */
	public static void func_addPerformLog(selectDispatch obj_Mysql, String c_Id, String str_Result, String str_ErrorMSG,
			String case_tablename, String c_CaseApiName) throws Exception {

		String str_BatchNO = "111";
		String table_dispatch = "dispatch";

		// 拼接日志表insert语句
		String sql_String = "insert into " + "test" + ".log_Perform (";
		sql_String += "batch_no,table_dispatch,table_case,case_Rowid,script_name,time_Response,Time_execute,execute_result,execute_errormsg)";
		sql_String += "values('" + str_BatchNO + "','" + table_dispatch + "','" + case_tablename + "','" + c_Id + "','"
				+ c_CaseApiName + "',";
		sql_String += "'0'," + "NOW(),'" + str_Result + "','" + str_ErrorMSG + "');";

		// 执行insert语句
		DbUtil insert_Table = new DbUtil();
		insert_Table.insert("CaseDB", sql_String);

		// System.err.println(sql_String);

	}

	/**
	 * @author zhengyi
	 * @Target 将执行结果更新至case�?
	 * @param 调度表mysql对象
	 *            obj_DisTable
	 * @param case_table表行�?
	 *            d_Id
	 * @param case_table执行结果
	 *            result
	 * @param case_table表�?�行�?
	 *            all
	 * @param case_table表�?�过�?
	 *            pass
	 * @param case_table表失败数
	 *            fail
	 * @throws Exception
	 */
	public static void recordStatisticalResults(DbUtil obj_DisTable, String d_Id, String result, int all, int pass,
			int fail) throws Exception {

		String sql_Update = null;
		sql_Update = "UPDATE test.dispatch SET ";
		sql_Update += "execute_result = '" + result + "' , ";
		sql_Update += "execute_errormsg = '" + " " + "' , ";
		sql_Update += "execute_Time = " + "now()" + " , ";
		sql_Update += "num_pass_case = '" + pass + "' , ";
		sql_Update += "num_fail_case = '" + fail + "' , ";
		sql_Update += "num_run_case = '" + (pass + fail) + "' , ";
		sql_Update += "num_all_case = '" + all + "' ";
		sql_Update += "WHERE id = '" + d_Id + "' ;";
		// System.out.println(sql_Update);
		obj_DisTable.update("CaseDB", sql_Update);
	}
}
