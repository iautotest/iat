package com.caishuo.IAT.scripts.ademo;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.caishuo.IAT.publicFunction.util.ATString;
import com.caishuo.IAT.publicFunction.util.HttpclientUtil;
 
/** 
 * @Target 	普�?�接口登�?
 * @param 	String c_DataInput
 * @return 	String str_Response
 * @throws 	Exception 
 * @author 	zhengyi
 * @date 	20150918
 */  

public class Get {
	
	public String get(String c_DataInput) throws Exception {  
		
		String str_Response = null;
	
		//分割输入字符�?
		String[] input_array = ATString.splitInputData(c_DataInput); 
		
		//【需要修改�??1、预期接口输入个数；2、接口赋值（else的内容）
		int num_ExpectInput = 4;
		if (input_array.length != num_ExpectInput){
			str_Response = ATString.checkInputNumErr(num_ExpectInput,input_array.length);
		}else{
			String a_token 			= String.valueOf(input_array[0]);						//用户token
			String a_SNCode 		= String.valueOf(input_array[1]).substring(3);			//设备唯一识别�?
			String a_Key 			= String.valueOf(input_array[2]).substring(3);			//访问权限
			String a_Version		= String.valueOf(input_array[3]).substring(3);			//应用版本
//			String provider 		= String.valueOf(input_array[4]).substring(3);			//第三方类�?
//			String provider_id 		= String.valueOf(input_array[5]).substring(3);			//第三方id
//			String access_token 	= String.valueOf(input_array[6]).substring(3);			//access_token

			//【需要修改�?? 设置API名称:	<模块�?>/接口�?      or   <股票代码>/<模块�?>/接口�?
			String str_APIName = "users/me";
			
			//拼接访问的URL并设置API名称:	str_URL += "?Par1=Var1&Par2=Var2";
			String str_URL = HttpclientUtil.setAPIurl(str_APIName);
//			if (!per_page	.equals(""))	{str_URL 	+= "?stock_ids="		+	per_page;	}
//			if (!page		.equals(""))	{str_URL 	+= "&basket_ids="		+	page;		}
//			if (!market		.equals(""))	{str_URL 	+= "&basket_ids="		+	market;		}
			
			//设置头文�?
			HttpGet obj_HttpRequest = ATString.setAPIHead_Get(a_token, a_SNCode, a_Key, a_Version, str_URL);
					
			//设置网络连接
			HttpClient httpclient = ATString.func_SetNetwork();
		    //请求并将请求返回成String
		    str_Response = ATString.execute_HttpClient_Get(httpclient, obj_HttpRequest);
		    
//			//【视情况修改】若存在�?要取值对象，可�?�过json对象进行取�?�，并放入全�?变量�?"GlobalVariables"�?
//			//		【说明�?�获�?"access_token"�?,若json对象中不存在"token"值，则将全局变量置为"",反之则赋�?
//			JSONObject jsonObject = new JSONObject().fromObject(str_Response.toString()); 	
//			GlobalVariables.token = (String) jsonObject.getJSONObject("data").get("access_token");
	    }
		return str_Response;
	}
}	


