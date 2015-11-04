
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page import="com.caishuo.IAT.publicFunction.util.WebDbUtil"%>

<%@ page contentType="text/html; charset=utf-8"%>

<%
WebDbUtil iat_db = new WebDbUtil();
%>

<br>


<%
	Map<String, Object> result = iat_db.selectCase("others_login");
	if (result.get("code").equals("error")) {
%>
		<%=result.get("code")%>	
		<br>	
		<%=result.get("message")%>	
		<br>
<%
	} else {

		List<Map> rc3 = (List<Map>) result.get("obj");
		%>
		
		<table border="1" bordercolor="black" cellspacing="3">
		<tr>
		<td width="50px">	id			</td>
		<td width="150px">	CASE		</td>
		<td width="100px">	GROUP		</td>
		<td width="120px">	script		</td>
		<td width="150px">	run			</td>
		<td width="100px">	input		</td>
		<td width="100px">	expect		</td>
		<td width="110px">	result		</td>
		<td width="110px">	errormsg	</td>
		<td width="110px">	COMMENT		</td>
		<tr>
<%		int i;
		for (i = 1; i < rc3.size(); i++) {
		//id, CASE, GROUP, script, run, input, expect, result, errormsg, COMMENT, TIME
%>

			<%//输出表格
			Map map = rc3.get(i);%>
		
			
			<tr>
				<td width="50px">	<%=map.get("id")				%></td>
				<td width="150px">	<%=map.get("CASE")	%></td>
				<td width="100px">	<%=map.get("GROUP")		%></td>
				<td width="120px">	<%=map.get("script")	%></td>
				<td width="150px">	<%=map.get("run")	%></td>
				<td width="100px">	<%=map.get("input")		%></td>
				<td width="100px">	<%=map.get("expect")		%></td>
				<td width="110px">	<%=map.get("result")		%></td>
				<td width="110px">	<%=map.get("errormsg")		%></td>
				<td width="110px">	<%=map.get("COMMENT")		%></td>
		
			</tr>
		<%
		}
	}
%>



	<%--
		int fontSize = 0;
	--%>
	<%--
		while (fontSize <= 7) {
	--%>
	<!-- font color="green" size="<--%=fontSize%>"-->
	 
	<!--/font-->

	<%--
		fontSize++;
	--%>
	<%--
		}
	--%>


	<%! int serviceVar = 0;	 %>
	
	<%	serviceVar++;
		String content = "响应客户请求次数 : " + serviceVar;
	%>
	<h1><%=content%></h1>


	<%	out.println("Your IP address is " + request.getRemoteAddr());	%>
	<br>
	Today's date:<%=(new java.util.Date()).toLocaleString()%>
	