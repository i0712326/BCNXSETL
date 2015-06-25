<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="/BOLRGTS/lib/js/jshashset-3.0.js"></script>
<script type="text/javascript" src="/BOLRGTS/lib/js/jshashtable-3.0.js"></script>
<script type="text/javascript" src="/BOLRGTS/lib/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/BOLRGTS/lib/js/jquery.numberformatter-1.2.4.min.js"></script>
<script type="text/javascript">
$(function(){
	var inp = $('input[type=text]');
	for(var i=0;i<inp.length;i++){
		var el = $(inp[i]);
		el.blur(function(){
			$(this).formatNumber({format:"#,###.00", locale:"us"});
		});
	}
});
</script>
<title>settlement</title>
</head>
<body>
	<h3>
		<a href="/BOLRGTS/authenticated/welcome.jsp">Home</a>
	</h3>
	<h2>Settlement Detail</h2>
	<s:div class="settle-form">
		<s:form action="/authenticated/settlement/settle.action" method="POST">
			<s:iterator value="accounts" status="stat">
				<s:hidden name="accounts[%{#stat.index}].bic" value="%{bic}"/>
				<s:hidden name="accounts[%{#stat.index}].name" value="%{name}"/>
				<s:hidden name="accounts[%{#stat.index}].account" value="%{account}"/>
				<s:hidden name="accounts[%{#stat.index}].shortName" value="%{shortName}"/>
				<s:textfield name="accounts[%{#stat.index}].amt" label="%{shortName}" value="0.00"/>
		</s:iterator>
		<s:submit value="submit"/>
	</s:form>
	</s:div>
</body>
</html>