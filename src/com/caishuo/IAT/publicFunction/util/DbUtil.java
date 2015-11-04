package com.caishuo.IAT.publicFunction.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.caishuo.IAT.main.Constant;

public class DbUtil {
	Connection conn_CaseDB 		= null;
	Connection conn_BusinessDB 	= null;
	Connection conn = null;
	
	
	String jdbcName 	= null;
	String dbUrl		= null;
	String dbUserName 	= null;
	String dbPassword 	= null;
	
	public void set_Conn(Connection new_Conn){
		this.conn = new_Conn;	
	}
	
	public Connection get_Conn(){
		return this.conn;	
	}
	
	/**
	 * @author zhengyi
	 * @return	
	 * @Target	数据库连�?
	 * @throws ClassNotFoundException
	 * @throws SQLException 
	 */
//	public Connection caseDB_connect(String conn_Type) throws Exception {
	public Connection connect(String conn_Type) throws Exception {	
		if (conn == null) {
			if (conn_Type == "CaseDB"){
				jdbcName 	= Constant.CaseDB_jdbcName;
				dbUrl		= Constant.CaseDB_HOST+"??useUnicode=true&characterEncoding=UTF-8";
				dbUserName 	= Constant.CaseDB_DBUser;
				dbPassword 	= Constant.CaseDB_DBPWD;
			}else if  (conn_Type == "BusinessDB"){
				jdbcName 	= Constant.BusinessDB_jdbcName;
				dbUrl		= Constant.BusinessDB_HOST+"??useUnicode=true&characterEncoding=UTF-8";
				dbUserName 	= Constant.BusinessDB_DBUser;
				dbPassword 	= Constant.BusinessDB_DBPWD;
			}else {
				System.err.println("不存在的数据库类�?");
			}
			
			Class.forName(jdbcName);
			Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
			this.set_Conn(conn);
		}
		
		return conn;
	}

	public void release() throws Exception {
		if (conn != null) {
			this.conn.close();
			
		}
	}
	

		
	/**
	 * @author zhengyi
	 * @param	rs 
	 * @return
	 * @Target 查询数据�?
	 * @throws Exception
	 */
	public ResultSet select(String conn_Type,String sql_string) throws Exception {
		//连接数据�?
		conn = this.connect(conn_Type);
		
		ResultSet rs=null;

		Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(sql_string);

		return rs;
	}
	
	/**
	 * @author zhengyi
	 * @return
	 * @throws Exception 
	 * @Target 插入数据�?
	 */
	public void insert(String conn_Type,String sql_string) throws Exception {
		//连接数据�?
//		conn = get_Conn();
		conn = this.connect(conn_Type);
		
		try {
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sql_string);

			if (rs == 1) {
				//System.out.println("Insert operation is successful!");
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
	public String update(String conn_Type,String sql_string) throws Exception {
		//连接数据�?
//		conn = get_Conn();
		conn = this.connect(conn_Type);
		
		try {	
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sql_string);
//			int rs = stmt.execute
			
			if (rs == 1) {
				//System.out.println("Update operation is successful!");
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
	public String delete(String conn_Type,String sql_string) throws Exception {
		//连接数据�?
//		conn = get_Conn();
		conn = this.connect(conn_Type);
		
		try {	
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sql_string);
			
			if (rs == 1) {
				//System.out.println("Update operation is successful!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "0";
	}
	
	/**
	 * @Target 将执行结果更新至case�?
	 * @param obj_Mysql
	 * @param case_tablename
	 * @param c_Id
	 * @param str_Res
	 * @return 
	 * @throws Exception
	 */
	public static String func_UpdateTableResult(DbUtil obj_Mysql, String case_tablename, String c_Id, String c_CaseApiName, String str_Res, String str_Response)
			throws Exception {
		
		//str_Response = "";
		String str_Result = "PASS";
		String str_ErrorMSG = "";
		
		str_Res = str_Res.replace("'", "\"");
		str_Response = str_Response.replace("'", "\"");
		if (! str_Res.equals("PASS")) {
				//System.err.println(str_Res);
				str_Result = "FAIL";
				str_ErrorMSG = str_Res;
				System.err.println(str_Res);
		}else {
			System.out.println(str_Res);
			//str_Response = "";
		}
		 
		 //拼接更新的SQL�?
		 String sql_String = "UPDATE test." + case_tablename + " set result = '" + str_Result;
		 sql_String += "' ,errormsg = '" + str_ErrorMSG  + str_Response + "' ,time = NOW()";
		 sql_String += " WHERE id = " + c_Id + ";";
		 
		 //执行update语句
		 obj_Mysql.update("CaseDB",sql_String);
		
		//记录执行日志
		func_addPerformLog(obj_Mysql, c_Id, str_Result, str_ErrorMSG,case_tablename,c_CaseApiName);
		
		return sql_String;
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
	public static void func_addPerformLog(DbUtil obj_Mysql, String c_Id, String str_Result,String str_ErrorMSG, String case_tablename, String c_CaseApiName) 
			throws Exception {
		
		String str_BatchNO = "111";
		String table_dispatch = "dispatch";
		
		//拼接日志表insert语句
		String sql_String = "insert into " + "test" + ".log_Perform (";
		sql_String += "batch_no,table_dispatch,table_case,case_Rowid,script_name,time_Response,Time_execute,execute_result,execute_errormsg)";
		sql_String += "values('"+ str_BatchNO + "','" + table_dispatch + "','" + case_tablename + "','" + c_Id + "','" + c_CaseApiName + "',";
		sql_String += "'0'," + "NOW(),'"+ str_Result + "','" + str_ErrorMSG + "');";
		
		//执行insert语句
		DbUtil insert_Table = new DbUtil();
		insert_Table.insert("CaseDB",sql_String);
		 
		//System.err.println(sql_String);
		
	}
	
	/**
	 * @Target 	读取class列表
	 * @param 	脚本名称（c_CaseApiName�?
	 * @throws 	Exception 
	 * @throws 	SQLException 
	 * @return	rs_ClassList
	 */
	public static ResultSet getClassList(String c_CaseApiName) throws Exception,SQLException {
		DbUtil sel_Table = new DbUtil();
		String sql_String = "select * from public_contrast_class_list where inCaseName = '" + c_CaseApiName + "';";
		ResultSet rs_ClassList = sel_Table.select("CaseDB",sql_String);
		rs_ClassList.next();
		return rs_ClassList;
	}
	

	/**
	 * @author zhengyi
	 * @Target 将执行结果更新至case�?
	 * @param 调度表mysql对象	obj_DisTable
	 * @param case_table表行�?	d_Id
	 * @param case_table执行结果	result
	 * @param case_table表�?�行�?	all
	 * @param case_table表�?�过�?	pass
	 * @param case_table表失败数	fail
	 * @throws Exception
	 */
	public static void recordStatisticalResults(DbUtil obj_DisTable,String d_Id, String result, int all, int pass, int fail) 
			throws Exception {
		
		String sql_Update = null;
		sql_Update = "UPDATE test.dispatch SET ";
		sql_Update += "execute_result = '" + result +"' , ";
		sql_Update += "execute_errormsg = '" + " " +"' , ";
		sql_Update += "execute_Time = " + "now()" +" , ";
		sql_Update += "num_pass_case = '" + pass +"' , ";
		sql_Update += "num_fail_case = '" + fail +"' , ";
		sql_Update += "num_run_case = '" + (pass+fail) +"' , ";
		sql_Update += "num_all_case = '" + all +"' ";
		sql_Update += "WHERE id = '" + d_Id +"' ;";
//		System.out.println(sql_Update);
		obj_DisTable.update("CaseDB",sql_Update);
	}
}
