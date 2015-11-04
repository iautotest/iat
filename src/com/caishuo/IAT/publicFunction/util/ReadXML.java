package com.caishuo.IAT.publicFunction.util;

	import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.caishuo.IAT.main.Constant;
	
	/**
	 * @author yvan
	 *
	 */
public class ReadXML{
	
	 public ReadXML(String fileName) throws Exception{
		 DocumentBuilderFactory domfac=DocumentBuilderFactory.newInstance();
		 try {
			 DocumentBuilder dombuilder=domfac.newDocumentBuilder();
			 InputStream is=new FileInputStream(fileName);
			 Document doc=dombuilder.parse(is);
			 Element root=doc.getDocumentElement();	
			 NodeList dbinfo=root.getChildNodes();
			 if(dbinfo!=null){
				 for(int i=0;i<dbinfo.getLength();i++){
					 Node db=dbinfo.item(i);
					 
					 if(db.getNodeName().equals("ClientKey")){
						//setClientKey(db.getFirstChild().getNodeValue());
						 Constant.ClientKey = db.getFirstChild().getNodeValue();
					 }
					 if(db.getNodeName().equals("HOST")){
						 //setHost(db.getFirstChild().getNodeValue());
						 Constant.HOST = db.getFirstChild().getNodeValue();
					 }
					 if(db.getNodeName().equals("Loglevel")){
						 //setLoglevel(db.getFirstChild().getNodeValue());
						 Constant.Loglevel = db.getFirstChild().getNodeValue();
					 }
					 
					 //二级节点
					 for(Node node=db.getFirstChild();node!=null;node=node.getNextSibling()){
						 if(node.hasChildNodes()){
//							 if(node.getNodeName().equals("THINKTIME_SHORT")){
//								 Constant.THINKTIME_SHORT = node.getFirstChild().getNodeValue();
//							 }
//							 if(node.getNodeName().equals("THINKTIME_LONG")){
//								 Constant.THINKTIME_LONG = node.getFirstChild().getNodeValue();
//							 }
							 
							 
							 //proxy
							 if(node.getNodeName().equals("ignoreproxy")){
								 Constant.ignoreproxy = node.getFirstChild().getNodeValue();
							 }
							 if(node.getNodeName().equals("proxyHost")){
								 Constant.proxyHost = node.getFirstChild().getNodeValue();
							 }
							 if(node.getNodeName().equals("proxyPort")){
								 Constant.proxyPort = node.getFirstChild().getNodeValue();
							 }
							 if(node.getNodeName().equals("userName")){
								 Constant.proxyUserName = node.getFirstChild().getNodeValue();						 
							 }
							 if(node.getNodeName().equals("password")){
								 Constant.proxyPassWord = node.getFirstChild().getNodeValue();
							 }
							 
							 //CaseDB
							 if(node.getNodeName().equals("CaseDB_HOST")){
								 Constant.CaseDB_HOST = node.getFirstChild().getNodeValue();
							 }
							 if(node.getNodeName().equals("CaseDB_jdbcName")){
								 Constant.CaseDB_jdbcName = node.getFirstChild().getNodeValue();
							 }
							 if(node.getNodeName().equals("CaseDB_DBUser")){
								 Constant.CaseDB_DBUser = node.getFirstChild().getNodeValue();						 
							 }
							 if(node.getNodeName().equals("CaseDB_DBPWD")){
								 Constant.CaseDB_DBPWD = node.getFirstChild().getNodeValue();
							 }
							 
							 //BusinessDB
							 if(node.getNodeName().equals("BusinessDB_HOST")){
								 Constant.BusinessDB_HOST = node.getFirstChild().getNodeValue();
							 }
							 if(node.getNodeName().equals("BusinessDB_jdbcName")){
								 Constant.BusinessDB_jdbcName = node.getFirstChild().getNodeValue();
							 }
							 if(node.getNodeName().equals("BusinessDB_DBUser")){
								 Constant.BusinessDB_DBUser = node.getFirstChild().getNodeValue();						 
							 }
							 if(node.getNodeName().equals("BusinessDB_DBPWD")){
								 Constant.BusinessDB_DBPWD = node.getFirstChild().getNodeValue();
							 }

						 }
					 }
				 } 
			 } 
		 }finally{
			 
		 }
	 }
}