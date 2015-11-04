package com.caishuo.IAT.scripts.ademo;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.message.BasicNameValuePair;
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

public class Put {
	
	public String put(String c_DataInput) throws Exception {  
			
		String str_Response = null;
			
		//设置网络连接
		HttpClient httpclient = ATString.func_SetNetwork();
		
		//分割输入字符�?
		String[] input_array = ATString.splitInputData(c_DataInput); 
		
		//【需要修改�??1、预期接口输入个数；2、接口赋值（else的内容）
		int num_ExpectInput = 10;
		if (input_array.length != num_ExpectInput){
			str_Response = ATString.checkInputNumErr(num_ExpectInput,input_array.length);
		}else{
			String a_token 			= String.valueOf(input_array[0]);						//用户token
			String a_SNCode 		= String.valueOf(input_array[1]).substring(3);			//设备唯一识别�?
			String a_Key 			= String.valueOf(input_array[2]).substring(3);			//访问权限
			String a_Version		= String.valueOf(input_array[3]).substring(3);			//应用版本
			String username 		= String.valueOf(input_array[4]).substring(3);
			String headline 		= String.valueOf(input_array[5]).substring(3);
			String intro 			= String.valueOf(input_array[6]).substring(3);
			String province 		= String.valueOf(input_array[7]).substring(3);
			String city 			= String.valueOf(input_array[8]).substring(3);
			String gender 			= String.valueOf(input_array[9]).substring(3);
			
			//【需要修改�?? 设置API名称:	<模块�?>/接口�?      or   <股票代码>/<模块�?>/接口�?     String str_APIName = str_module+"login";
			//修改个人信息
			String str_APIName = "profile";
			
			//拼接访问的URL
			String str_URL = HttpclientUtil.setAPIurl(str_APIName);
			
			//设置头文�?
			HttpPut obj_HttpRequest = ATString.setAPIHead_Put(a_token, a_SNCode, a_Key, a_Version, str_URL);
//			//登录的头文件中，token是空�?
//			obj_HttpRequest.setHeader("Authorization"		, 	""); 
			
			//【需要修改�?�设置参数�?�，input_array[4]及之后的值需要手动赋（例：账号�?�密码）
			//		【说明�?? 参数赋�??
		    List<NameValuePair> params = new ArrayList<NameValuePair>();
		    params.add(new BasicNameValuePair("username", 			username));
		    params.add(new BasicNameValuePair("headline",			headline));
		    params.add(new BasicNameValuePair("intro",				intro));
		    params.add(new BasicNameValuePair("province",			province));
		    params.add(new BasicNameValuePair("city",				city));
		    params.add(new BasicNameValuePair("gender",				gender));
		    
		    //请求并将请求返回成String
		    str_Response = ATString.execute_HttpClient_Put(httpclient, obj_HttpRequest, params);

//			//【视情况修改】若存在�?要取值对象，可�?�过json对象进行取�?�，并放入全�?变量�?"GlobalVariables"�?
//			//		【说明�?�获�?"access_token"�?,若json对象中不存在"token"值，则将全局变量置为"",反之则赋�?
//			JSONObject jsonObject = new JSONObject().fromObject(str_Response.toString()); 	
//			GlobalVariables.token = (String) jsonObject.getJSONObject("data").get("access_token");
			
	    }
		return str_Response;
	}
}	


