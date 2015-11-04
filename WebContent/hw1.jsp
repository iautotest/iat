

<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page import="com.caishuo.IAT.publicFunction.util.WebDbUtil"%>

<%@ page contentType="text/html; charset=utf-8"%>

<%
	WebDbUtil iat_db = new WebDbUtil();
%>

<br>


<%
	Map<String, Object> result = iat_db.selectDispatch("dispatch");
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
		<td width="50px">	id					</td>
		<td width="150px">	case_tablename		</td>
		<td width="100px">	is_running			</td>
		<td width="120px">	execute_result		</td>
		<td width="150px">	execute_errormsg	</td>
		<td width="100px">	execute_Time		</td>
		<td width="100px">	num_run_case		</td>
		<td width="110px">	num_pass_case		</td>
		<td width="110px">	num_fail_case		</td>
		<td width="110px">	num_all_case		</td>
		<tr>
<%		int i;
		for (i = 1; i < rc3.size(); i++) {
		//id, CASE, GROUP, script, run, input, expect, result, errormsg, COMMENT, TIME
%>

			<%//输出表格
			Map map = rc3.get(i);%>
		
			
			<tr>
				<td width="50px">	<%=map.get("id")				%></td>
				<td width="150px">	<%=map.get("case_tablename")	%></td>
				<td width="100px">	<%=map.get("is_running")		%></td>
				<td width="120px">	<%=map.get("execute_result")	%></td>
				<td width="150px">	<%=map.get("execute_errormsg")	%></td>
				<td width="100px">	<%=map.get("execute_Time")		%></td>
				<td width="100px">	<%=map.get("num_run_case")		%></td>
				<td width="110px">	<%=map.get("num_pass_case")		%></td>
				<td width="110px">	<%=map.get("num_fail_case")		%></td>
				<td width="110px">	<%=map.get("num_all_case")		%></td>
		
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
	