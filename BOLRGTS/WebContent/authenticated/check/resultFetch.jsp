<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Result</title>
</head>
<body>
	<table>
		<tr>
			<th>No.</th>
			<th>DATETIME</th>
			<th>SYSDATE</th>
			<th>RRN</th>
			<th>FROM NAME</th>
			<th>FROM</th>
			<th>TO NAME</th>
			<th>TO</th>
			<th>AMOUNT</th>
			<th>RES</th>
			<th>SWIFTNAME</th>
		</tr>
		<s:iterator value="settleTxns" status="stat">
			<tr>
				<td><s:property value="%{#stat.count}"/></td>
				<td>
					<s:date name="dateTime" format="MM/dd/yy HH:mm:ss"/>
				</td>
				<td>
					<s:date name="sysDate" format="MM/dd/yy"/>
				</td>
				<td><s:property value="rrn"/></td>
				<td><s:property value="fromName"/></td>
				<td><s:property value="from"/></td>
				<td><s:property value="toName"/></td>
				<td><s:property value="to"/></td>
				<td>
					<s:text name="format.money">
						<s:param name="value" value="amount"/>
					</s:text>
				</td>
				<td><s:property value="res"/></td>
				<td>
					<s:url action="download.action" var="urlTag">
						<s:param name="name">
							<s:property value="%{swiftName}"/>
						</s:param>
					</s:url>
					<s:a href="%{urlTag}"><s:property value="swiftName"/></s:a>
				</td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>