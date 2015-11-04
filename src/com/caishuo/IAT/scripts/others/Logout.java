package com.caishuo.IAT.scripts.others;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;

import com.caishuo.IAT.publicFunction.util.ATString;
import com.caishuo.IAT.publicFunction.util.HttpclientUtil;

/** 
 * @Target 	普�?�接口�??�??
 * @param 	c_DataInput
 * @return 
 * @throws 	Exception 
 * @author 	zhengyi
 */  
public class Logout {

	@SuppressWarnings("unused")
//	public String Logout(String c_DataInput, String str_module) throws Exception {  
	public String logout(String c_DataInput) throws Exception {  	
				
		String str_Response = null;	
	
		//分割输入字符�??
		String[] input_array = ATString.splitInputData(c_DataInput); 
		int num_ExpectInput = 4;

		//【需要修改�??1、接口输入个数；2、接口赋�??
		if (input_array.length != num_ExpectInput){
			str_Response = ATString.checkInputNumErr(num_ExpectInput,input_array.length);
		}else{
			String a_token 			= String.valueOf(input_array[0]);
			String a_SNCode 		= String.valueOf(input_array[1]).substring(3);
			String a_Key 			= String.valueOf(input_array[2]).substring(3);
			String a_Version		= String.valueOf(input_array[3]).substring(3);
			
			//设置网络连接
			HttpClient httpclient = ATString.func_SetNetwork();
			
			//拼接访问的URL并设置API名称:		<模块�??>/接口�??      or   <股票代码>/<模块�??>/接口�??
			String str_APIName = "logout";
			String str_URL = HttpclientUtil.setAPIurl(str_APIName);
			
			//【需要修改�?�若是Delete请求，则选择执行    "setAPIHead_Del"   方法,只需�??"_"之后�??
			//设置头文�??/拼接访问的URL/设置API名称:		<模块�??>/接口�??      or   <股票代码>/<模块�??>/接口�??
			HttpDelete obj_HttpRequest = ATString.setAPIHead_Del(a_token, a_SNCode, a_Key, a_Version, str_URL);
			
			//设置参数�??--（若input_array[3]之后有�?�）
		    List<NameValuePair> params = new ArrayList<NameValuePair>();
		    
		    //【需要修改�?�若是Post请求，则选择执行"execute_HttpClient_Del"方法,只需�??"_"之后�??
		    //		【说明�?�执行httpclient请求并将请求返回成String
		    str_Response = ATString.execute_HttpClient_Del(httpclient, obj_HttpRequest);

	    }
		return str_Response;
	}



	
	
	
	
	
	
	
	
	
	
	
	
}
