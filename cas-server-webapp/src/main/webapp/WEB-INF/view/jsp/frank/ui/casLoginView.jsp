<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.include file="includes/top.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 
	这是用于cas的认证登陆界面
	采用metro风格的bootstrap完成.
 -->
<html lang="zh-cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>平台统一认证登陆系统</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/resources/common/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/common/css/style-metro.css"/>" rel="stylesheet" type="text/css" />
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="<c:url value="/resources/common/js/ltie/html5shiv.min.js"/>"></script>
      <script src="<c:url value="/resources/common/js/ltie/respond.min.js"/>"></script>
    <![endif]-->
    <link href="<c:url value="/resources/sso/css/css.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/common/css/font-awesome.min.css"/>">
    <!--[if IE 7]>
	<link rel="stylesheet" href="<c:url value="/resources/common/js/css/ltie/font-awesome-ie7.min.css"/>">
		<![endif]-->
</head>
<body>
<!--[if lt IE 11]>
<div class="alert alert-danger">
	<strong>
	您的浏览器版本有点低，为保证您能顺利使用，请使用以下浏览器：
	<a href="http://www.baidu.com/link?url=8Bg0KxLIYinE7QJPJJfcEYwvpAYySKHb7w2RLEPmGJ6ko3gezzDXH1sfzZqdoWPtXjBT6rUfyopQtIvTw5-Ir_1hAxLpywdO-dqBxpiiUD_" target="_blank">猎豹浏览器</a>、
	<a href="http://www.baidu.com/link?url=vaNf_GcWlAeOaKirTXrL7db_od-3zZlPrad8JYelYMFZj1OpGqWsJ-_jwUeEUrzJh4U7Z2kYQVzML1gNvmdzAv1JCBWxF3y_J2ufGmIQNn3" target="_blank">Chrome浏览器</a>、
	<a href="http://www.baidu.com/link?url=b_Z0hoKToXU59BT2z1sY_tt2nHEhsMX7YQ-a49c_mxL1ogjNBFEZJC-rQc8HYF9Aczn4LsZo7M0CscsTkTNu2wAHeeAIQiDBVybrtjl4rfi" target="_blank">火狐浏览器</a>
	</strong>
</div>
<![endif]--> 
<!-- 错误提示信息 -->
<c:if test="${not pageContext.request.secure}">
<!-- 错误提示信息  BEGIN-->
<div class="container">
	<div class="alert alert-warning alert-dismissible" role="alert">
  	<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
 			<strong>不安全的链接。</strong></br>
 	    	您正在以非安全链接访问认证系统，可能导致认证系统无法正常工作。
	</div>
</div>
<!-- 错误提示 END -->
</c:if>

<!-- 提示信息 --> 
<div class="login_logo">
    <div class="container" style="float: left: ;" >
	    <div class="row" >
	      <div class="col-md-16" >
	        <img src="<c:url value="/resources/sso/images/logo.png"/>" class="logo">
	        <img src="<c:url value="/resources/sso/images/logo_title.png"/>" class="logo_title"> 
	       </div>
	    </div>
  	</div>
</div>
<div class="login_xinxi">
 <form:form method="post" id="fm1" cssClass="fm-v clearfix" commandName="${commandName}" htmlEscape="true">
  <div class="login_info">

   	<!-- 错误提示信息  BEGIN-->
 	<div class="alert alert-warning alert-dismissible" role="alert" >
 			<form:errors path="*" id="msg" cssClass="errors" element="div" />
		   	<c:if test="${empty sessionScope.openIdLocalId}">
				<spring:message code="screen.welcome.label.netid.accesskey" var="userNameAccessKey" />
			</c:if>
	</div>
	<!-- 错误提示 END -->
     <div class="login_ico">
       	 <span class="hidden-xs">
     		<img src="<c:url value="/resources/sso/images/login_icon.png"/>" >
     	 </span>
     </div>
     <div class="login_dl">
      
        <div class="col-md-16">
              <div class="row">
	            	<div class="col-md-16 login_down">
	            	  <c:if test="${not empty sessionScope.openIdLocalId}">
		                  <input  class="form-control" id="username" name="username" type="text" placeholder="请输入用户名" value="${sessionScope.openIdLocalId}">
             		   </c:if>
             		   <c:if test="${empty sessionScope.openIdLocalId}">
						 <input  class="form-control" id="username" name="username" type="text" placeholder="请输入用户名" >
						</c:if>
	                </div>
	                
	            	<div class="col-md-16 login_down">
	                  <input  class="form-control" id="password"  name="password"  type="password"  placeholder="请输入密码" > 
	                </div>
	                	
	            	<div class="col-md-16 login_down text-left">
	            		<input type="hidden" name="lt" value="${loginTicket}" />
						<input type="hidden" name="execution" value="${flowExecutionKey}" />
						<input type="hidden" name="_eventId" value="submit" />
	            		
	                  	<button type="submit" class="btn green pull-right">
								<spring:message code="screen.welcome.button.login" /> 
								<i class="m-icon-swapright m-icon-white"></i>
						</button>
	                </div>
              </div>
        </div>
      
     </div>
  </div>
</div>
</form:form>
<!-- 底部菜单 --> 
<div class="footer">
  <div class="container" >
    <div class="row " >
      <div class="col-md-16" > Copyright © 2014 Powered by Jasig Central Authentication Service</div>
    </div>
  </div>
</div>
<!-- 底部菜单 -->
<script type="text/javascript" src="<c:url value="/resources/common/js/jquery-1.10.1.min.js"/>" ></script>
<script type="text/javascript" src="<c:url value="/resources/common/js/bootstrap.min.js"/>"></script>
</body>
</html>
