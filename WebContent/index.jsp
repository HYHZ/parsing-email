<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<!-- Apple devices fullscreen -->
<meta name="apple-mobile-web-app-capable" content="yes" />
<!-- Apple devices fullscreen -->
<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />

<title>Dashboard</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="plugins/flat-admin/html/css/bootstrap.min.css">
<!-- Bootstrap responsive -->
<link rel="stylesheet" href="plugins/flat-admin/html/css/bootstrap-responsive.min.css">
<!-- jQuery UI -->
<link rel="stylesheet" href="plugins/flat-admin/html/css/plugins/jquery-ui/smoothness/jquery-ui.css">
<link rel="stylesheet" href="plugins/flat-admin/html/css/plugins/jquery-ui/smoothness/jquery.ui.theme.css">
<!-- PageGuide -->
<link rel="stylesheet" href="plugins/flat-admin/html/css/plugins/pageguide/pageguide.css">
<!-- Fullcalendar -->
<link rel="stylesheet" href="plugins/flat-admin/html/css/plugins/fullcalendar/fullcalendar.css">
<link rel="stylesheet" href="plugins/flat-admin/html/css/plugins/fullcalendar/fullcalendar.print.css" media="print">
<!-- chosen -->
<link rel="stylesheet" href="plugins/flat-admin/html/css/plugins/chosen/chosen.css">
<!-- select2 -->
<link rel="stylesheet" href="plugins/flat-admin/html/css/plugins/select2/select2.css">
<!-- icheck -->
<link rel="stylesheet" href="plugins/flat-admin/html/css/plugins/icheck/all.css">
<!-- Theme CSS -->
<link rel="stylesheet" href="plugins/flat-admin/html/css/style.css">
<!-- Color CSS -->
<link rel="stylesheet" href="plugins/flat-admin/html/css/themes.css">
<!--[if lte IE 9]>
		<script src="js/plugins/placeholder/jquery.placeholder.min.js"></script>
		<script>
			$(document).ready(function() {
				$('input, textarea').placeholder();
			});
		</script>
	<![endif]-->

<!-- Favicon -->
<link rel="shortcut icon" href="plugins/flat-admin/html/img/favicon.ico" />
<!-- Apple devices Homescreen icon -->
<link rel="apple-touch-icon-precomposed" href="plugins/flat-admin/html/img/apple-touch-icon-precomposed.png" />

<style type="text/css">
body {
	font-size: 12px;
}

#navigation #brand {
	padding-right: 61px;
}

textarea,input[type="text"],input[type="password"],input[type="datetime"],input[type="datetime-local"],input[type="date"],input[type="month"],input[type="time"],input[type="week"],input[type="number"],input[type="email"],input[type="url"],input[type="search"],input[type="tel"],input[type="color"],.uneditable-input
	{
	background: none;
}

.cke.cke_chrome {
	border-top: 1px solid #CCC;
}

.form-horizontal .control-label {
	text-align: left;
	width: 60px;
}

.form-horizontal .controls {
	margin-left: 80px;
}

.box .box-title {
	margin-top: 5px;
}
</style>

</head>

<body>

	<%
		// 判断当前用户是否登录 
// 		if (session.getAttribute("s_user") == null) {
// 			response.sendRedirect("login.html");
// 		}
	%>


	<!-- Header -->
	<jsp:include page="/WEB-INF/pages/common/header.jsp" />

	<div id="content" class="container-fluid">

		<!-- Sidebar left panel -->
		<jsp:include page="/WEB-INF/pages/common/sidebar.jsp" />

		<!-- Right content start -->
		<div id="main">
			<div class="container-fluid">
				<div id="page" class="row-fluid">
					
				
				</div>
			</div>
		</div>
	</div>


	<!-- jQuery -->
	<script src="plugins/flat-admin/html/js/jquery.min.js"></script>
	<script src="plugins/flat-admin/html/js/bootstrap.min.js"></script>
	
	
	<!-- jQuery UI -->
	<script src="plugins/flat-admin/html/js/plugins/jquery-ui/jquery.ui.core.min.js"></script>
	<script src="plugins/flat-admin/html/js/plugins/jquery-ui/jquery.ui.widget.min.js"></script>
	<script src="plugins/flat-admin/html/js/plugins/jquery-ui/jquery.ui.mouse.min.js"></script>
	<script src="plugins/flat-admin/html/js/plugins/jquery-ui/jquery.ui.draggable.min.js"></script>
	<script src="plugins/flat-admin/html/js/plugins/jquery-ui/jquery.ui.resizable.min.js"></script>
	<script src="plugins/flat-admin/html/js/plugins/jquery-ui/jquery.ui.sortable.min.js"></script>
	
	
	<!-- Theme framework -->
	<script src="plugins/flat-admin/html/js/eakroko.min.js"></script>
	<!-- Theme scripts 
	=====================================================================
		@dependence jquery_ui_sortable.js
		-->
	<script src="plugins/flat-admin/html/js/application.min.js"></script>
	<script type="text/javascript">
	 	$("#page").load("MailAccountServlet");
    </script>
</body>
</html>