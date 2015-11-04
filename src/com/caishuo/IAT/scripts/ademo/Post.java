package com.caishuo.IAT.scripts.ademo;

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
 * @Target 	普�?�接口登�?
 * @param 	String c_DataInput
 * @return 	String str_Response
 * @throws 	Exception 
 * @author 	zhengyi
 * @date 	20150918
 */   

public class Post {
	
	public String post(String c_DataInput) throws Exception {  
		String str_Response = null;

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
			String provider 		= String.valueOf(input_array[4]).substring(3);			//第三方类�?
			String provider_id 		= String.valueOf(input_array[5]).substring(3);			//第三方id
			String access_token 	= String.valueOf(input_array[6]).substring(3);			//access_token
			String nick_name 		= String.valueOf(input_array[7]).substring(3);			//昵称
			String avatar 			= String.valueOf(input_array[8]).substring(3);			//头像
			String channel_code 	= String.valueOf(input_array[9]).substring(3);			//渠道�?

			//设置API名称
			String str_APIName = "users/provider";

			//拼接访问的URL
			String str_URL = HttpclientUtil.setAPIurl(str_APIName);
			//【说明�?? 设置头文�?
			HttpPost obj_HttpRequest = ATString.setAPIHead_Post(a_token, a_SNCode, a_Key, a_Version, str_URL);
			
			//【需要修改�?�设置参数�?�，input_array[4]之后的�?�需要手动赋（例：账号�?�密码）
		    List<NameValuePair> params = new ArrayList<NameValuePair>();
		    params.add(new BasicNameValuePair("provider"		, 		provider		));
		    params.add(new BasicNameValuePair("provider_id"		,		provider_id		));
		    params.add(new BasicNameValuePair("access_token"	,		access_token	));
		    params.add(new BasicNameValuePair("nick_name"		,		nick_name		));
		    params.add(new BasicNameValuePair("avatar"			,		avatar			));
		    params.add(new BasicNameValuePair("channel_code"	,		channel_code	));
		    
		    //设置网络连接
			HttpClient httpclient = ATString.func_SetNetwork();
		    //【说明�?�请求并将请求返回成String
		    str_Response = ATString.execute_HttpClient_Post(httpclient, obj_HttpRequest, params);

			new JSONObject();
			//【需要修改�?��?�说明�?�获�?"access_token"�?,若json对象中不存在"token"值，则将全局变量置为"",反之则赋�?
			JSONObject jsonObject = JSONObject.fromObject(str_Response.toString()); 	
			if (jsonObject.get("status").toString().equals("1")){
				GlobalVariables.token = (String) jsonObject.getJSONObject("data").get("access_token");
			}else {
				GlobalVariables.token = "";
			}	
			
	    }
		return str_Response;
	}


}	