package com.caishuo.IAT.publicFunction.util;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import com.caishuo.IAT.main.Constant;

@SuppressWarnings("deprecation")
public class HttpclientUtil {
	/*
	 *  @Target 设置SSL
	 * */
	/**
	 * @author zhengyi
	 * @Target 设置SSL(绕过SSL访问)
	 * */
	public static HttpClient setSSL(HttpClient httpclient) throws NoSuchAlgorithmException,	KeyManagementException {
		X509TrustManager tm = new X509TrustManager() {

			public void checkClientTrusted(X509Certificate[] xcs, String string)
					throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] xcs, String string)
					throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		// 跳过SSL访问
		SSLContext ctx = SSLContext.getInstance("SSL");
		ctx.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory ssf = new SSLSocketFactory(ctx);
		ClientConnectionManager ccm = httpclient.getConnectionManager();
		SchemeRegistry sr = ccm.getSchemeRegistry();
		sr.register(new Scheme("https", 443, ssf));
		return httpclient;
	}
	
	/**
	 * @author zhengyi
	 * @Target 设置Proxy
	 * */
	public static HttpClient setProxy(HttpClient httpclient) throws Exception {
		
		  DefaultHttpClient httpClient = new DefaultHttpClient();
		  if (!Constant.ignoreproxy.equals("1")){	  
			  String proxyHost = Constant.proxyHost;
			  int proxyPort = Integer.parseInt(Constant.proxyPort);
			  String userName = Constant.proxyUserName;
			  String password = Constant.proxyPassWord;
			  httpClient.getCredentialsProvider().setCredentials(
					    new AuthScope(proxyHost, proxyPort),
					    new UsernamePasswordCredentials(userName, password));
			  HttpHost proxy = new HttpHost(proxyHost,proxyPort);
			  httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
		  }
		  return httpClient;
	}

	/**
	 *	@author zhengyi
	 *  @Target 读取并拼接URL
	 * HttpPost httppost = new HttpPost("https://office.caishuo.com/api/v1/login.json");
	 * */
	public static String setAPIurl(String url_API) throws Exception {
		@SuppressWarnings({ "unused", "resource" })
		HttpClient httpClient = new DefaultHttpClient();
		  String url_server = Constant.HOST+"/api/v1/";
		  String url_testURL = url_server+url_API+".json";
		  
		  return url_testURL;
	}
	
//	/**
//     * @author zhengyi
//     * @Target 发�?? delete请求 
//	 * @param httpclient
//	 * @param httppost
//	 * @param params
//	 * @return
//	 * @throws Exception
//	 */
//	@SuppressWarnings("null")
//	public static String exec_HttpClient(HttpClient httpclient, HttpPost str_RequestType,List<NameValuePair> params) throws Exception {
//		String str_ResPonse;
//		
//		HttpPost httppost = null;// = new HttpPost();
//		//执行get请求   
//		httppost.setEntity(new UrlEncodedFormEntity(params));  
//		HttpResponse obj_ResPonse = httpclient.execute(httppost); 
//		HttpEntity entity = obj_ResPonse.getEntity();
//		str_ResPonse = EntityUtils.toString(entity);
//		return str_ResPonse;
//	}
	
}
