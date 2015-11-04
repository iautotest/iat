package com.caishuo.IAT.publicFunction.util;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.caishuo.IAT.main.GlobalVariables;

/** 
 * @Target 	执行SQL语句
 * @param 	String c_DataInput
 * @return 	String str_Response
 * @throws 	Exception 
 * @author 	zhengyi
 * @date 	20150918
 */   

public class ExecuteSql {
//	public int num_Column;
	public ResultSet executeSql(String c_DataInput) throws Exception {

		String conn_Type = "BusinessDB";

		//ResultSetMetaData rs_Sql = null;
//		ResultSet rs_Sql= null;
//		int i = 0;
//		String rc_DBCheck = "";

		DbUtil obj_SqlString = new DbUtil();
		ResultSet rs_Sql = null;
		int num_Column = get_QueryColumnCount(conn_Type,c_DataInput);
		GlobalVariables.num_Column = num_Column;
		//ResultSetMetaData rs=null;

		if (c_DataInput.toLowerCase().contains("select")){
						
			rs_Sql = obj_SqlString.select(conn_Type,c_DataInput);
			rs_Sql.next();
			
			return rs_Sql;
//			rc_DBCheck = ExecuteSql.check_DBExpectValue(c_ExpectValue, rs_Sql);
//			ExecuteSql.check_DBExpectValue(c_DataInput, str_Response1);
			
		}else if (c_DataInput.toLowerCase().contains("update")){
			obj_SqlString.update(conn_Type, c_DataInput);
		}else if (c_DataInput.toLowerCase().contains("insert")){
			obj_SqlString.insert(conn_Type, c_DataInput);
		}else if (c_DataInput.toLowerCase().contains("delete")){
			obj_SqlString.delete(conn_Type, c_DataInput);
		}

		return rs_Sql;  
		
	}

	/**
	 * @param conn_Type
	 * @param c_DataInput
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public int get_QueryColumnCount(String conn_Type, String c_DataInput) throws Exception, SQLException {
		//获取查询出列�?
		DbUtil obj_SqlString = new DbUtil();
		Connection conn = obj_SqlString.connect(conn_Type);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(c_DataInput);
		int num_Column = rs.getMetaData().getColumnCount();
		return num_Column;
	}

	/**
	 * @Target 	将数据库查询结果 �? 预期值进行匹配验�?
	 * @author 	zhengyi
	 * @param 	String c_ExpectValue
	 * @param 	ResultSet obj_DB_Result
	 * @return 	ErrorMSG
	 * @throws 	Exception
	 */
	public static  String check_DBExpectValue(String c_ExpectValue, ResultSet obj_DB_Result) throws Exception {
	
		String ErrorMSG = "预期值检验失败：\r\n";
		String str_Result = "PASS";
	
		if (c_ExpectValue == null) {
			return "ERROR";
		}
		
		String[] expect_array = c_ExpectValue.split("\\|");
		if (expect_array.length != GlobalVariables.num_Column){
			return "ERROR";
		}
		
		for(int j=1;j<GlobalVariables.num_Column+1;j++){
			String Str_QueryValue = obj_DB_Result.getString(j);
			System.err.println(j+":"+Str_QueryValue);
			boolean bool_check = expect_array[j-1].equals(Str_QueryValue);
			if (!bool_check){
				ErrorMSG += "�?" + j + "个匹配失败\r\n预期�?:" + expect_array[j-1] +"		\r\n实际�?:"+ Str_QueryValue + "\r\n\r\n";
			}
		}
		
		if (ErrorMSG.equals("预期值检验失败：\r\n")){
			return str_Result;
		}
	
		return ErrorMSG;
	}

}
