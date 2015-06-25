<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Settlement Page</title>
</head>
<body>
	<h3>
		<a href="/BOLRGTS/authenticated/welcome.jsp">Home</a>
	</h3>
	<h1>Settlement Detail</h1>
	<table>
	<s:iterator value="accounts" status="stat">
		<tr>
			<td><s:property value="%{#stat.count}"/></td>
			<td><s:property value="name"/></td>
			<td><s:property value="shortName"/></td>
			<td><s:property value="account"/></td>
			<td><s:property value="bic"/></td>
			<td>
				<s:text name="format.money">
					<s:param name="value" value="amount"/>
				</s:text>
			</td>
			<td>
				<s:url action="download.action" var="urlTag">
					<s:param name="name">
						<s:property value="%{shortName}"/>
					</s:param>
				</s:url>
				<s:a href="%{urlTag}">download</s:a>
			</td>
		</tr>
	</s:iterator>
	</table>
	<p/>
	<s:form action="/authenticated/upload/confirm.action" method="POST">
		<s:submit value="confirm"/>
	</s:form>
</body>
</html>