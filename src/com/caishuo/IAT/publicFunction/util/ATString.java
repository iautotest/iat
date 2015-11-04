package com.caishuo.IAT.publicFunction.util;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.caishuo.IAT.main.Constant;
import com.caishuo.IAT.main.GlobalVariables;

@SuppressWarnings("deprecation")
public class ATString {
	/**
	 * @param c_DataInput
	 * @return
	 */
	public ATString (){	}
	
	/**
	 * @Target 	分隔输入�?
	 * @author 	zhengyi
	 * @param 	c_ExpectValue
	 * @param 	str_ResPonse
	 * @return 	ErrorMSG
	 * @throws 	Exception
	 */
	public static String[] splitInputData(String c_DataInput) throws Exception {
//		System.out.println(c_DataInput);
		String str = c_DataInput.replace("|", "|---");
		
		String[] strarray = str.split("\\|");
		return strarray;
	}
	
	/**
	 * @Target 	验证str_Full是否包含了str_SearchChars
	 * @author 	zhengyi
	 * @param 	str_Full
	 * @param 	str_SearchChars
	 * @return 	boolean(存在:true;不存�?:false)
	 * @throws 	Exception
	 */
	public static boolean containsAny(String str_Full, String str_SearchChars) throws Exception {
		return str_Full.contains(str_SearchChars);
	}
	
	/**
	 * @Target 	在返回的报文验证预期是否存在
	 * @author 	zhengyi
	 * @param 	c_ExpectValue
	 * @param 	str_ResPonse
	 * @return 	ErrorMSG
	 * @throws 	Exception
	 */
	public static  String checkExpectValue(String c_ExpectValue, String str_ResPonse) throws Exception {
		String ErrorMSG = "预期值检验失败：\r\n";
		String str_Result = "PASS";
		//HttpEntity entity = response.getEntity();
		if (c_ExpectValue == null){
			boolean rb = ATString.containsAny(str_ResPonse ,"\"status\":1");
			if (! rb) {
				ErrorMSG += "接口执行过程中产生未预期的错�?:\r\n"+"预期为：" + c_ExpectValue + "\r\n实际值为�?"+str_ResPonse + "\r\n";
				return ErrorMSG;
			}else	{	
				return str_Result;	
			}
		}
		if (str_ResPonse != null) {
			if (ATString.containsAny(str_ResPonse,"输入个数不符合预�?")){
				return str_ResPonse;
				}else {				 
				String[] expect_array = c_ExpectValue.split("\\|");
	
				for (int i = 0; i < expect_array.length; i++) {
					if (! ATString.containsAny(expect_array[i],"\"msg\":")){
						expect_array[i] = expect_array[i]+",";
//						boolean rb = ATString.containsAny(str_ResPonse ,expect_array[i]+",");
					}
					//在返回的报文验证预期是否存在
					boolean rb = ATString.containsAny(str_ResPonse ,expect_array[i]);
					if (! rb) {
						ErrorMSG += "�?" + i + "个预期�??:"+expect_array[i]+"	未找到\r\n";
					}
				}
				if (! ErrorMSG.equals("预期值检验失败：\r\n")){
					ErrorMSG +="实际值为�?" + str_ResPonse;
					return ErrorMSG;
				}
//				System.out.println("\r\n预期值验证成功\r\n");
				return str_Result;
			}
		}	
		return ErrorMSG;
	}
	
	/** 
	 * @Target 	调试结果输出
	 * @param 	预期输入值个�?
	 * @param 	实际输入值个�?
	 * @author 	zhengyi
	 * @return 
	 * @date 	20150921
	 */  
	public static String checkInputNumErr(int numExpectInput,int numRealInput) {
		System.err.println("[Error]：输入个数不符合预期，预期个数为�?" + numExpectInput + "，实际输入个数为�?"	+numRealInput);
		String str_Response = "[Error]：输入个数不符合预期，预期个数为�?" + numExpectInput + "，实际输入个数为�?"+numRealInput;
		return str_Response;
	}

	/**
	 * @Target 	设置Get头文�?
	 * @param	a_token
	 * @param	a_SNCode
	 * @param	a_Key
	 * @param	a_Version
	 * @param	url
	 * @return	obj_HttpRequest
	 * @throws 	Exception 
	 */
	public static HttpGet setAPIHead_Get(String a_token, String a_SNCode, String a_Key, String a_Version, String url) throws Exception {

		//创建访问的URL对象
		HttpGet obj_HttpRequest = new HttpGet(url);
//		obj_HttpRequest.setHeader(new BasicHeader("Cookie","fddddddd"));
		//默认token赋�??
		if (a_token.equals(""))	{	a_token = GlobalVariables.token;	}
		if (a_Key.	equals(""))	{	a_Key 	= Constant.ClientKey;		}
		
		//设置头文�?
		obj_HttpRequest.setHeader("Authorization"		, 	a_token		); 
		obj_HttpRequest.setHeader("X-Client-Key"		, 	a_Key		);
		obj_HttpRequest.setHeader("X-SN-Code"			, 	a_SNCode	); 
		obj_HttpRequest.setHeader("X-Client-Version"	, 	a_Version	);
		
		return obj_HttpRequest;
	}
	
	/**
	 * @Target 	设置Post头文�?
	 * @param	a_token
	 * @param	a_SNCode
	 * @param	a_Key
	 * @param	a_Version
	 * @param	url
	 * @return	HttpPost obj_HttpRequest
	 * @throws 	Exception 
	 */
	public static HttpPost setAPIHead_Post(String a_token, String a_SNCode, String a_Key, String a_Version, String url) throws Exception {
	
		//创建访问的URL对象
		HttpPost obj_HttpRequest = new HttpPost(url);
		
		//默认token赋�??
		if (a_token.equals("")){	a_token = GlobalVariables.token;	}
		if (a_Key.	equals("")){	a_Key 	= Constant.ClientKey;		}
		
		//设置头文�?
		obj_HttpRequest.setHeader("Authorization"		, 	a_token		); 
		obj_HttpRequest.setHeader("X-Client-Key"		, 	a_Key		);
		obj_HttpRequest.setHeader("X-SN-Code"			, 	a_SNCode	); 
		obj_HttpRequest.setHeader("X-Client-Version"	, 	a_Version	);
		
		return obj_HttpRequest;
	}

	
	/**
	 * @Target 	设置Delete头文�?
	 * @param	a_token
	 * @param	a_SNCode
	 * @param	a_Key
	 * @param	a_Version
	 * @param	url
	 * @return	HttpDelete obj_HttpRequest
	 * @throws 	Exception 
	 */
	public static HttpDelete setAPIHead_Del(String a_token, String a_SNCode, String a_Key, String a_Version, String url) throws Exception {

		//创建访问的URL对象
		HttpDelete obj_HttpRequest = new HttpDelete(url);
		
		//默认token赋�??
		if (a_token.equals(""))	{	a_token = GlobalVariables.token;	}
		if (a_Key.	equals(""))	{	a_Key 	= Constant.ClientKey;		}
		
		//设置头文�?
		obj_HttpRequest.setHeader("Authorization"		, 	a_token		);
		obj_HttpRequest.setHeader("X-Client-Key"		, 	a_Key		);
		obj_HttpRequest.setHeader("X-SN-Code"			, 	a_SNCode	); 
		obj_HttpRequest.setHeader("X-Client-Version"	, 	a_Version	);
		
		return obj_HttpRequest;
	}
	
	/**
	 * @Target 	设置Put头文�?
	 * @param	a_token
	 * @param	a_SNCode
	 * @param	a_Key
	 * @param	a_Version
	 * @param	url
	 * @return	HttpPut obj_HttpRequest
	 * @throws 	Exception 
	 */
	public static HttpPut setAPIHead_Put(String a_token, String a_SNCode, String a_Key, String a_Version, String url) throws Exception {
//		//拼接访问的URL并设置API名称:		<模块�?>/接口�?      or   <股票代码>/<模块�?>/接口�?
//		String url = HttpclientUtil.setAPIurl("logout");		
		//拼接访问的URL
		HttpPut obj_HttpRequest = new HttpPut(url);
		
		//默认token赋�??
		if (a_token.equals(""))	{	a_token = GlobalVariables.token;	}
		if (a_Key.	equals(""))	{	a_Key 	= Constant.ClientKey;		}
		
		//设置头文�?
		obj_HttpRequest.setHeader("Authorization"		, 	a_token		); 
		obj_HttpRequest.setHeader("X-Client-Key"		, 	a_Key	);
		obj_HttpRequest.setHeader("X-SN-Code"			, 	a_SNCode	); 
		obj_HttpRequest.setHeader("X-Client-Version"	, 	a_Version	);
		
		return obj_HttpRequest;
	}

	/**
	 * @Target 	执行 HttpGet 请求
	 * @author 	zhengyi
	 * @param 	httpclient
	 * @param 	HttpGet obj_HttpRequest
	 * @return	str_ResPonse
	 * @throws 	Exception
	 */
	public static String execute_HttpClient_Get(HttpClient httpclient,	HttpGet obj_HttpRequest)	throws Exception {
		
		HttpResponse 	obj_ResPonse 	= httpclient.execute(obj_HttpRequest); 
		HttpEntity 		entity	 		= obj_ResPonse.getEntity();
		String			str_ResPonse 	= EntityUtils.toString(entity);
		
		return str_ResPonse;
	}

	/**
	 * @Target 	执行 HttpDelete 请求
	 * @author 	zhengyi
	 * @param 	HttpClient httpclient
	 * @param 	HttpDelete obj_HttpRequest
	 * @return 	str_ResPonse
	 * @throws 	Exception
	 */
	public static String execute_HttpClient_Del(HttpClient httpclient,	HttpDelete obj_HttpRequest) throws Exception {
		//String str_ResPonse;
		
		HttpResponse 	obj_ResPonse 	= httpclient.execute(obj_HttpRequest); 
		HttpEntity 		entity 			= obj_ResPonse.getEntity();
		String			str_ResPonse 	= EntityUtils.toString(entity);
		
		return str_ResPonse;
	}
	
	/**
	 * @Target 	执行 HttpPost 请求
	 * @author 	zhengyi
	 * @param 	HttpClient httpclient
	 * @param 	HttpPost   obj_HttpRequest
	 * @param 	List	   params
	 * @return	str_ResPonse
	 * @throws 	Exception
	 */
	public static String execute_HttpClient_Post(HttpClient httpclient,	HttpPost obj_HttpRequest, List<NameValuePair> params)	throws Exception {
//		String str_ResPonse;
		
		obj_HttpRequest.setEntity(new UrlEncodedFormEntity(params));  
		
		HttpResponse 	obj_ResPonse 	= httpclient.execute(obj_HttpRequest); 
		HttpEntity 		entity 			= obj_ResPonse.getEntity();
		String			str_ResPonse 	= EntityUtils.toString(entity);
		
		return str_ResPonse;
	}
	
	/**
	 * @Target 	执行 HttpPost 请求
	 * @author 	zhengyi
	 * @param 	HttpClient httpclient
	 * @param 	HttpPost   obj_HttpRequest
	 * @param 	List	   params
	 * @return	str_ResPonse
	 * @throws 	Exception
	 */
	public static String execute_HttpClient_Put(HttpClient httpclient,	HttpPut obj_HttpRequest, List<NameValuePair> params) throws Exception {
//		String str_ResPonse;
		
		obj_HttpRequest.setEntity(new UrlEncodedFormEntity(params));  
		
		HttpResponse 	obj_ResPonse 	= httpclient.execute(obj_HttpRequest); 
		HttpEntity 		entity 			= obj_ResPonse.getEntity();
		String			str_ResPonse 	= EntityUtils.toString(entity);
		
		return str_ResPonse;
	}
	
	/**
	 * @Target	设置网络连接(代理、忽略SSL)
	 * @author	zhengyi
	 * @return	HttpClient	httpclient
	 * @throws 	Exception
	 */
	public static HttpClient func_SetNetwork() throws Exception {
		
		HttpClient 	httpclient	= new DefaultHttpClient();					//创建HttpClient
					httpclient 	= HttpclientUtil.setProxy(httpclient);		//设置代理访问
					httpclient	= HttpclientUtil.setSSL(httpclient);		//设置SSL访问
					
		return httpclient;
	}

//	/** 
//	 * @Target 	调试结果输出
//	 * @param 	预期输入值个�?
//	 * @param 	实际输入值个�?
//	 * @author 	zhengyi
//	 * @return 
//	 * @date 	20150921
//	 */  
//	public static String checkInputNumErr1(int numExpectInput,int numRealInput) {
//		System.err.println("[Error]：输入个数不符合预期，预期个数为�?" + numExpectInput + "，实际输入个数为�?"	+numRealInput);
//		String str_Response = "[Error]：输入个数不符合预期，预期个数为�?" + numExpectInput + "，实际输入个数为�?"+numRealInput;
//		return str_Response;
//	}
	
}
