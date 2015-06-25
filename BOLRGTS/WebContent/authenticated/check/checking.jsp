<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<s:set name="theme" value="'simple'" scope="page" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/BOLRGTS/css/ui-lightness/jquery-ui-1.10.4.css" rel="stylesheet"/>
<script type="text/javascript" src="/BOLRGTS/lib/js/jquery-1.11.0.min.js"></script>
<script src="/BOLRGTS/lib/js/jquery-ui-1.10.4.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#date').datepicker();
	$('#check').on('click',function(){
		var date = $('#date').val().trim();
		var url = '/BOLRGTS/authenticated/check/checkResult.action';
		var param = {'date':date};
		$.post(url,param,function(data){
			$('#data-table').html(data);
		});
	});
});
</script>
<title>checking</title>
</head>
<body>
	<h3>
		<a href="/BOLRGTS/authenticated/welcome.jsp">Home</a>
	</h3>
	<h2> check settlement response</h2>
	<div id="checking-form">
		<label for="date">
			<strong>SETTLE DATE :</strong>
		</label>
		<s:textfield name="date" id="date"/>
		<button id="check">check</button>
	</div>
	<p/>
	<div id="data-table"></div>
</body>
</html>