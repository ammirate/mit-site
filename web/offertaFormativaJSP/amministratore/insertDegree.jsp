<%-- 
    Document   : insertDegree
    Author     : Alessandro
--%>
<%@page import="it.unisa.offerta_formativa.beans.Department"%>
<%@page import="it.unisa.offerta_formativa.beans.Cycle"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
    <%! public ArrayList<Cycle> cycles; %>
    <%! public ArrayList<Department> departments; %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>DISTRA-MIT</title>

	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
	<link rel="stylesheet" href="assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="assets/css/fonts/fontawesome/css/font-awesome.css">
	<link rel="stylesheet" href="assets/css/bootstrap.css">
	<link rel="stylesheet" href="assets/css/xenon-core.css">
	<link rel="stylesheet" href="assets/css/xenon-forms.css">
	<link rel="stylesheet" href="assets/css/xenon-components.css">
	<link rel="stylesheet" href="assets/css/xenon-skins.css">
	<link rel="stylesheet" href="assets/css/custom.css">

	<script src="assets/js/jquery-1.11.1.min.js"></script>
	<script>
		/* jQuery(document).ready(function($){
			if(localStorage.getItem("username")!=null){
				if(localStorage.getItem("typology")=="Professor"){
					alert("Professor");
				}
				if(localStorage.getItem("typology")=="student"){
					window.location.href="offertaFormativaStudente.html";
				}
				if(localStorage.getItem("typology")=="Organization"){
					alert("Organization");
				}
				if(localStorage.getItem("typology")=="Administrator"){
					alert("Administrator");
				}
			};
			$.get('GetDepartmentServlet',{all:""},function(responseText) { 
                $('#idDepartment').append(responseText);         
            });
		}); */
	</script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	
	
</head>
<body class="page-body">
	<% cycles = (ArrayList<Cycle>)request.getAttribute("cycles");
		departments = (ArrayList<Department>)request.getAttribute("departments");
	%>
	
	<nav class="navbar horizontal-menu navbar-fixed-top"><!-- set fixed position by adding class "navbar-fixed-top" -->
		
		<div class="navbar-inner">
		
			<!-- Navbar Brand -->
			<div class="navbar-brand">
				<a href="index.html" class="logo">
					<img src="assets/images/mitforsite.png" width="80" alt="" class="hidden-xs" />
					<img src="assets/images/mitforsitemini.png" width="80" alt="" class="visible-xs" />
				</a>
			</div>
				
			<!-- Mobile Toggles Links -->
			<div class="nav navbar-mobile">
			
				<!-- This will toggle the mobile menu and will be visible only on mobile devices -->
				<div class="mobile-menu-toggle">
					
					<a href="#" data-toggle="user-info-menu-horizontal">
						<i class="fa-key"></i>
					</a>
					
					<a href="#" data-toggle="mobile-menu-horizontal">
						<i class="fa-bars"></i>
					</a>
				</div>
			</div>
			
			<div class="navbar-mobile-clear"></div>
			
			<!-- main menu -->
					
			<ul class="navbar-nav">
				<li class="opened active">
					<a href="index.html">
						<i class="fa fa-home"></i>
						<span class="title">Home</span>
					</a>
				</li>
				<li>
					<a href="offertaFormativa.html">
						<i class="linecons-desktop"></i>
						<span class="title">Offerta Formativa</span>
					</a>
				</li>
				<li>
					<a href="gestioneTesi.html">
						<i class="linecons-graduation-cap"></i>
						<span class="title">Gestione Tesi</span>
					</a>
				</li>
				<li>
					<a href="gestioneTirocinio.html">
						<i class="linecons-megaphone"></i>
						<span class="title">Gestione Tirocinio</span>
					</a>
				</li>
				<li>
					<a href="dottorato.html">
						<i class="linecons-lightbulb"></i>
						<span class="title">Dottorato</span>
					</a>
				</li>
                <li>
				    <a href="#">
				        <i class="linecons-globe"></i>
						<span class="title">Links</span>
					</a>
					<ul>
						<li>
							<a href="http://www.magistralemit.unisa.it/" target="_blank">
								<span class="title">DISTRA-MIT</span>
							</a>
						</li>
						<li>
							<a href="https://esse3web.unisa.it/unisa/Start.do" target="_blank">
								<span class="title">Esse3</span>
							</a>
						</li>
					</ul>
                </li>
			</ul>
			<!-- notifications and other links -->
			<ul class="nav nav-userinfo navbar-right">
                <li>
				    <a href="login.html">
				        <i class="fa-user"></i>
						<span class="title">Login</span>
					</a>
                </li>
			</ul>
	
		</div>
		
	</nav>

    <!--BODY-->
    
    <div class="page-container">
        <div class="main-content">
            <div class="row">
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                
                <form action="InsertDegreeServlet" method="post" role="form" class="form-horizontal">
                	<div class="form-group">
                		<label for="matricula">Matricola:</label>
                		<input type="text" class="form-control" name="matricula" placeholder="matricola">
                	</div>
                	<div class="form-group">
                		<label for="title">Nome:</label>
                		<input type="text" class="form-control" name="title" placeholder="nome">
                	</div>
                	<div class="form-group">
                		<label for="link">link:</label>
                		<input type="text" name="link" class="form-control" placeholder="link">
                	</div>
                	<div class="form-group">
                		<label for="cycle">Ciclo:</label>
                		<select name="idCycle" class="form-control">
                		<% 
                		if(cycles.size()!=0)
                		for(Cycle c : cycles){
                				%><option value=<% out.print(c.getNumber());%>><% out.print(c.getTitle());%></option>
                			<% } %>
                		</select>
                	</div>
                	<div class="form-group">
                		<label for="department">Dipartimento:</label>
                		<select name="idDepartment" id="idDepartment" class="form-control">
                			<%
                			if(departments.size()!=0)
                			for(Department d : departments){
                				%><option value=<% out.print(d.getAbbreviation());%>><% out.print(d.getTitle());%></option>
                			<%}%>
                		</select>
                	</div>
                	<div class="form-group">
                		<input type="submit" id="submit" class="btn btn-default">
                	</div>
                </form>
                
                </div>    
                <div class="col-sm-1"></div>
            </div>
        </div>
    </div>
    

	<!-- Bottom Scripts -->
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/TweenMax.min.js"></script>
	<script src="assets/js/resizeable.js"></script>
	<script src="assets/js/joinable.js"></script>
	<script src="assets/js/xenon-api.js"></script>
	<script src="assets/js/xenon-toggles.js"></script>


	<!-- JavaScripts initializations and stuff -->
	<script src="assets/js/xenon-custom.js"></script>

</body>
</html>
