<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>BOL-RGTS</title>
</head>
<body>
	<div id="login-form">
		<s:form action="/authenticated/logon.action" method="POST">
			<s:textfield name="user.usrId" label="User ID"/>
			<s:password name="user.passwd" label="Password"/>
			<s:submit value="submit"/>
		</s:form>
	</div>
</body>
</html>