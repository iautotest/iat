package com.caishuo.IAT.main;
import java.lang.reflect.Method;
import java.sql.ResultSet;

import com.caishuo.IAT.publicFunction.util.ATString;
import com.caishuo.IAT.publicFunction.util.DbUtil;
import com.caishuo.IAT.publicFunction.util.ExecuteSql;
/**
 * @author yvan
 *1
 */
public class Dispatch_CaishuoIAT {

	public void dispatch_IAT() {
		DbUtil sel_Table = new DbUtil();

		try {
			//驱动层，case-table列表，第�?行的case-table就是�?个case�?
			String sql_String = "select * from dispatch;";
			
			ResultSet rs_dispatch = sel_Table.select("CaseDB",sql_String);

			//迭代
			while (rs_dispatch.next()) {
				
				String d_IsRun 	= rs_dispatch.getString("is_running");
				String d_Id 	= rs_dispatch.getString("id");
				String dispatch_result = "PASS";
				
				//确定当前case-table是否�?要执�?
				if (d_IsRun.equals("1")){
					int execute_all  = 0; 
					int execute_pass = 0;
					int execute_fail = 0;
					String str_FailGroup = "";
					String rc		 	 = null;
					
					//获取case-table名称
					String case_tablename = rs_dispatch.getString("case_tablename");
					//读取case-table�?
					String sql_Case = "select * from "+case_tablename;
					ResultSet rs_case = sel_Table.select("CaseDB",sql_Case);
					
					while (rs_case.next()) {
						long beginTime = System.currentTimeMillis();

						String c_IsRun		= rs_case.getString("run");				//是否运行本条case
						String c_CaseGroup 	= rs_case.getString("group");			//case 组号
						
						//确定本条case是否执行,条件1：Run字段值为1（执行）；条�?2：已失败的groupid在str_FailGroup不存�?
						if(c_IsRun.equals("1")){
							
							if (!str_FailGroup.contains(c_CaseGroup+"!@#$%") || (str_FailGroup != "")){
								//执行case�?,包括反射，传�?
								rc = executeCaseTable(sel_Table,rs_case,case_tablename);
								//统计执行结果
								if (rc != null){
									if(!rc.equals("PASS")){
										//统计执行失败的接口数
										execute_fail += 1;
										dispatch_result = "FAIL";
										str_FailGroup += (c_CaseGroup+"!@#$%");
									}else {
										//统计执行成功的接口数
										execute_pass += 1;
									}
								}
							}
							//统计�?要执行的case行数（列"run"值为1�?
							execute_all += 1;
							String time1 = System.currentTimeMillis() - beginTime + "ms";
							System.out.print(time1+"\r\n");
						}
						//统计case页�?�行�?
						//execute_all = execute_all +1;				
					}
					//在调度表（dispatch）中统计接口自动化运行概�?
					DbUtil.recordStatisticalResults(sel_Table, d_Id, dispatch_result,execute_all, execute_pass, execute_fail);
				}
			}
		sel_Table.release();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("e.getMessage:----"+e.getMessage());
		}
	}


	/**
	 * @Target 将执行结果更新至case�?
	 * @param  case_tablename
	 * @param  rs_case
	 * @return 
	 * @throws Exception
	 */
	//	String c_CaseName 			= rs_case.getString("case");			//用例名称(用例说明)
	//	String c_ExecuteResult 		= rs_case.getString("result");			//执行结果
	//	String c_ExecuteErrorMSG 	= rs.getString("errormsg");				//错误的结�?
	//	String c_ExecuteTime 		= rs.getString("time");					//用例执行时间		
	public String executeCaseTable(DbUtil obj_Mysql,ResultSet rs_case,	String c_tablename) throws Exception {
		String c_Id 				= rs_case.getString("id");				//case id�?
		String c_CaseApiName 		= rs_case.getString("script");			//接口接口名（短名�?
		String c_DataInput 			= rs_case.getString("input");			//输入�?
		String c_ExpectValue 		= rs_case.getString("expect");			//预期�?
		System.err.println("---------------------------------------------------------------------------------------------------------------");
		
		String str_ClassName 		= null;
		String str_classFullName 	= null;
		//System.out.println(c_DataInput);
		
		String str_CheckResult=null;
		String str_Response = null;
		//通过在case中的接口�? 获取类路径与类名
		ResultSet rs_ClassList = DbUtil.getClassList(c_CaseApiName);
		if (rs_ClassList.getRow() == 1){
			//System.out.println(rs_ClassList.getRow());
			str_ClassName = rs_ClassList.getString("className");
			str_classFullName = rs_ClassList.getString("classFullName");
					
			//System.err.println(str_ClassName+"			"+str_classFullName);
			//反射操作
			Class<?> api_Script = Class.forName(str_classFullName);
			Method method = api_Script.getMethod(str_ClassName, String.class);

			//向接口发送请�?,并将结果返回至str_Response
			//String str_module = rs_ClassList.getString("module");
			if (!str_ClassName.contains("ExecuteSql")){
				
				str_Response = (String) method.invoke(api_Script.newInstance(), c_DataInput);					//根据Case表中的接口名反射调用对应脚本�?
				str_CheckResult = ATString.checkExpectValue(c_ExpectValue, str_Response); 						//获取返回并验证预期�??				
			}else{
//				Method method = api_Script.getMethod(str_ClassName, String.class);
//				str_CheckResult = (String) method.invoke(api_Script.newInstance(), c_DataInput,c_ExpectValue);
				ResultSet str_Response1 = (ResultSet) method.invoke(api_Script.newInstance(), c_DataInput);		//根据Case表中的接口名反射调用对应脚本�?
				str_CheckResult = ExecuteSql.check_DBExpectValue(c_ExpectValue, str_Response1);					//获取返回并验证预期�??
			}
			
			//日志级别,保留
			//可将str_Response根据 json解析，格式化输出，或将str_Response 置空
			str_Response = "";							//str_Response = "\r\n返回报文:\r\n" + str_Response;
		}else{
			str_Response = "脚本短名�?"+c_CaseApiName+"不存在，请检查case表或�?查\"public_contrast_class_list\"�?";
		}
		
		//获取返回并验证预期�??
//		if (!str_ClassName.contains("ExecuteSql")){
//			str_CheckResult = ATString.checkExpectValue(c_ExpectValue, str_Response); 
//		}
//		else {
////			ResultSet str_Response = null; 
////			str_CheckResult = ExecuteSql.check_DBExpectValue(c_ExpectValue, str_Response);
//		}
//		System.err.println(c_tablename);
//		System.err.println(c_Id);
//		System.err.println(c_CaseApiName);
//		System.err.println(str_CheckResult);
//		System.err.println(str_Response);
//		System.out.println(""+str_Response);
		//System.err.println(""+str_Response);
		

		
		//将结果记录至数据�?(并记录至日志�?)
		DbUtil.func_UpdateTableResult(obj_Mysql,c_tablename, c_Id, c_CaseApiName, str_CheckResult,str_Response);

		return str_CheckResult;
	}

}
