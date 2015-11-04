/**
 * 
 */
package com.caishuo.IAT.main;
import java.io.IOException;

//import com.caishuo.IAT.publicFunction.util.HttpClientTest;
//import com.caishuo.IAT.publicFunction.util.HttpURLConnectionTest;
import com.caishuo.IAT.publicFunction.util.ReadXML;

/**
 * @author yvan
 *
 */
public class Main_TestHttpClient {
	String globalAccessToken = null;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		
		
		
		//读取XML
		new ReadXML(Constant.FILENAME_CONFIG);
		//long beginTime = System.currentTimeMillis();
		
		
//		  System.out.println(Constant.HOST);
//		  System.out.println(Constant.ClientKey);
//		  System.out.println(Constant.Loglevel);
//		  System.out.println(Constant.THINKTIME_SHORT);
//		  System.out.println(Constant.THINKTIME_LONG);
//		  
//		  System.out.println(Constant.proxyHost);
//		  System.out.println(Constant.proxyPort);
//		  System.out.println(Constant.proxyUserName);
//		  System.out.println(Constant.proxyPassWord);
//		  
//		  System.out.println(Constant.CaseDB_HOST);
//		  System.out.println(Constant.CaseDB_jdbcName);
//		  System.out.println(Constant.CaseDB_DBUser);
//		  System.out.println(Constant.CaseDB_DBPWD);
//		  
//		  System.out.println(Constant.BusinessDB_HOST);
//		  System.out.println(Constant.BusinessDB_jdbcName);
//		  System.out.println(Constant.BusinessDB_DBUser);
//		  System.out.println(Constant.BusinessDB_DBPWD);
		  

		long beginTime = System.currentTimeMillis();
		
		Dispatch_CaishuoIAT asd3 = new Dispatch_CaishuoIAT();
		asd3.dispatch_IAT();
		  
		String time1 = System.currentTimeMillis() - beginTime + "ms";
		System.out.print("Total Time:" + time1);  
	}



}
