package com.caishuo.IAT.scripts.others;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import com.caishuo.IAT.main.GlobalVariables;
import com.caishuo.IAT.publicFunction.util.ATString;
import com.caishuo.IAT.publicFunction.util.HttpclientUtil;

import net.sf.json.JSONObject;
/** 
 * @Target 	普�?�接口登�??
 * @param 	c_DataInput
 * @return 
 * @throws 	Exception 
 * 
 * @author 	zhengyi
 * @date 	20150824
 */  

public class Login{
	
	@SuppressWarnings("static-access")
	public String login(String c_DataInput) throws Exception {  
	
		//【需要修改�?? 设置API名称:	<模块�??>/接口�??      or   <股票代码>/<模块�??>/接口�??
//		String str_APIName = str_module+"login";
		String str_APIName = "login";
		
		String str_Response = null;
			
		//设置网络连接
		HttpClient httpclient = ATString.func_SetNetwork();
		
		//分割输入字符�??
		String[] input_array = ATString.splitInputData(c_DataInput); 
		int num_ExpectInput = 6;

		//【需要修改�??1、接口输入个数；2、接口赋�??
		if (input_array.length != num_ExpectInput){
			str_Response = ATString.checkInputNumErr(num_ExpectInput,input_array.length);
		}else{
			String a_token 			= String.valueOf(input_array[0]);						//用户token
			String a_SNCode 		= String.valueOf(input_array[1]).substring(3);			//设备唯一识别�??
			String a_Key 			= String.valueOf(input_array[2]).substring(3);			//访问权限
			String a_Version		= String.valueOf(input_array[3]).substring(3);			//应用版本
			String email_or_mobile 	= String.valueOf(input_array[4]).substring(3);			//账号
			String password 		= String.valueOf(input_array[5]).substring(3);			//密码

			//拼接访问的URL并设置API名称:		<模块�??>/接口�??      or   <股票代码>/<模块�??>/接口�??
			String str_URL = HttpclientUtil.setAPIurl(str_APIName);
			
			//【需要修改�?�若是Delete请求，则选择执行    "setAPIHead_Del"   方法,只需�??"_"之后�??
			//		【说明�?? 设置头文�??/拼接访问的URL
			HttpPost obj_HttpRequest = ATString.setAPIHead_Post(a_token, a_SNCode, a_Key, a_Version, str_URL);
//			//登录的头文件中，token是空�??
//			obj_HttpRequest.setHeader("Authorization"		, 	""); 
			
			//【需要修改�?�设置参数�?�，input_array[4]之后的�?�需要手动赋（例：账号�?�密码）
			//		【说明�?? 参数赋�??
		    List<NameValuePair> params = new ArrayList<NameValuePair>();
		    params.add(new BasicNameValuePair("email_or_mobile", 	email_or_mobile));
		    params.add(new BasicNameValuePair("password",			password));
		    
		    //【需要修改�?�若是Post请求，则选择执行"execute_HttpClient_Post"方法,只需�??"_"之后�??
		    //		【说明�?�请求并将请求返回成String
		    str_Response = ATString.execute_HttpClient_Post(httpclient, obj_HttpRequest, params);

			//【需要修改�?�若存在�??要取值对象，可�?�过json对象进行取�?�，并放入全�??变量�??"GlobalVariables"�??
			//		【说明�?�获�??"access_token"�??,若json对象中不存在"token"值，则将全局变量置为"",反之则赋�??
			JSONObject jsonObject = new JSONObject().fromObject(str_Response.toString()); 	
			if (jsonObject.get("status").toString().equals("1")){
				GlobalVariables.token 	= (String) jsonObject.getJSONObject("data").get("access_token");
				//GlobalVariables.userid	=   (int)  jsonObject.getJSONObject("data").get("id");
				GlobalVariables.userid	=  jsonObject.getJSONObject("data").get("id");
			}else {
				GlobalVariables.token 	= "";
				GlobalVariables.userid 	= "";
			}
				
//			System.err.println("GlobalVariables.token:		"+GlobalVariables.userid);		
			
	    }
		return str_Response;
	}
}	