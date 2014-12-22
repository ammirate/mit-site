<%-- 
    Document   : insertTeaching
    Author     : Alessandro
--%>
<%@page import="it.unisa.offerta_formativa.beans.Teaching"%>
<%@page import="it.unisa.offerta_formativa.beans.Curriculum"%>
<%@page import="it.unisa.offerta_formativa.beans.Degree"%>
<%@page import="it.unisa.offerta_formativa.beans.Department"%>
<%@page import="it.unisa.offerta_formativa.beans.Cycle"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%! public ArrayList<Cycle> cycles; %>
<%! public ArrayList<Department> departments;
    public Department dept;
    public Degree degree;
    public Curriculum curr;
    public Teaching teaching;
    public Cycle cycle;
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Xenon Boostrap Admin Panel" />
<meta name="author" content="" />

<title>DISTRA-MIT</title>

<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
<link rel="stylesheet" href="assets/css/fonts/linecons/css/linecons.css">
<link rel="stylesheet"
	href="assets/css/fonts/fontawesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/bootstrap.css">
<link rel="stylesheet" href="assets/css/xenon-core.css">
<link rel="stylesheet" href="assets/css/xenon-forms.css">
<link rel="stylesheet" href="assets/css/xenon-components.css">
<link rel="stylesheet" href="assets/css/xenon-skins.css">
<link rel="stylesheet" href="assets/css/custom.css">

<script src="assets/js/jquery-1.11.1.min.js"></script>


<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->


</head>
<body class="page-body">
	<% 	cycles = (ArrayList<Cycle>)request.getAttribute("cycles");
		departments = (ArrayList<Department>)request.getAttribute("departments");
		teaching = (Teaching)request.getAttribute("teaching");
                curr = (Curriculum) request.getAttribute("curriculum");
                degree = (Degree) request.getAttribute("degree");
                dept = (Department) request.getAttribute("department");
                cycle = (Cycle)request.getAttribute("cycle");
	%>
	<nav class="navbar horizontal-menu navbar-fixed-top">
		<!-- set fixed position by adding class "navbar-fixed-top" -->

		<div class="navbar-inner">

			<!-- Navbar Brand -->
			<div class="navbar-brand">
				<a href="offertaFormativaStudente.html" class="logo"> <img
					src="assets/images/mitforsite.png" width="80" alt=""
					class="hidden-xs" /> <img src="assets/images/mitforsitemini.png"
					width="80" alt="" class="visible-xs" />
				</a>
			</div>

			<!-- Mobile Toggles Links -->
			<div class="nav navbar-mobile">

				<!-- This will toggle the mobile menu and will be visible only on mobile devices -->
				<div class="mobile-menu-toggle">

					<a href="#" data-toggle="user-info-menu-horizontal"> <i
						class="fa-key"></i>
					</a> <a href="#" data-toggle="mobile-menu-horizontal"> <i
						class="fa-bars"></i>
					</a>
				</div>
			</div>

			<div class="navbar-mobile-clear"></div>

			<!-- main menu -->

			<ul class="navbar-nav">
				<li class="opened active"><a
					href="offertaFormativaStudente.html"> <i
						class="linecons-desktop"></i> <span class="title">Offerta
							Formativa</span>
				</a></li>
				<li><a href="#"> <i class="linecons-graduation-cap"></i> <span
						class="title">Gestione Tesi</span>
				</a></li>
				<li><a href="#l"> <i class="linecons-megaphone"></i> <span
						class="title">Gestione Tirocinio</span>
				</a></li>
				<li><a href="#"> <i class="linecons-lightbulb"></i> <span
						class="title">Dottorato</span>
				</a></li>
				<li><a href="#"> <i class="linecons-globe"></i> <span
						class="title">Links</span>
				</a>
					<ul>
						<li><a href="http://www.magistralemit.unisa.it/"
							target="_blank"> <span class="title">DISTRA-MIT</span>
						</a></li>
						<li><a href="https://esse3web.unisa.it/unisa/Start.do"
							target="_blank"> <span class="title">Esse3</span>
						</a></li>
					</ul></li>
			</ul>
			<!-- notifications and other links -->
			<ul class="nav nav-userinfo navbar-right">
				<li class="dropdown user-profile"><a href="#"
					data-toggle="dropdown"> <img src="assets/images/user-1.png"
						alt="user-image" class="img-circle img-inline userpic-32"
						width="28" /> <span id="spaceForUsername"> </span>
				</a>

					<ul class="dropdown-menu user-profile-menu list-unstyled">
						<li><a href="#"> <i class="fa-edit"></i> Profilo
						</a></li>
						<li class="last"><a href="#" id="logout"> <i
								class="fa-lock"></i> Logout
						</a></li>
					</ul></li>
			</ul>

		</div>

	</nav>
	<div class="page-container">
		<!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->

		<!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
		<!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
		<!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
		<div class="sidebar-menu toggle-others">
			<div class="sidebar-menu-inner">
				<ul id="main-menu" class="main-menu">
					<!-- add class "multiple-expanded" to allow multiple submenus to open -->
					<!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
					<li class="opened active"><a href="#"> <i
							class="linecons-cog"></i> <span class="title">Home</span>
					</a></li>
					<li id="funzionalita1Permission_0"><a href="#"> <i
							class="linecons-cog"></i> <span class="title">Funzionalit&agrave;
								01</span>
					</a></li>
					<li><a href="#"> <i class="linecons-note"></i> <span
							class="title">Funzionalit&agrave; 02</span>
					</a></li>
					<li id="funzionalita3Permission_0"><a href="#"> <i
							class="linecons-mail"></i> <span class="title">Funzionalit&agrave;
								03</span>
					</a>
						<ul>
							<li><a href="#"> <span class="title">INSERT</span>
							</a></li>
							<li><a href="#"> <span class="title">DELETE</span>
							</a></li>
							<li><a href="#"> <span class="title">VIEW</span>
							</a></li>
						</ul></li>
				</ul>
			</div>
		</div>

		<div class="main-content">

			<script>
				jQuery(document).ready(function($) {
					$('a[href="#layout-variants"]').on('click', function(ev) {
						ev.preventDefault();

						var win = {
							top : $(window).scrollTop(),
							toTop : $("#layout-variants").offset().top - 15
						};

						TweenLite.to(win, .3, {
							top : win.toTop,
							roundProps : [ "top" ],
							ease : Sine.easeInOut,
							onUpdate : function() {
								$(window).scrollTop(win.top);
							}
						});
					});
				});
			</script>

			<div class="jumbotron">
				<h2>Modifica Insegnamento</h2>
                                <form action="ModifyTeachingServlet" method="post" role="form" class="form-horizontal">
                                    <div class="row">
                                        <div class="form-group col-sm-4">
                                            <label for="department">Dipartimento:</label> 
                                            <select name="department" id="department" class="form-control" readonly onclick="enablePlacingFields()">
                                                <% out.print("<option value="+dept.getAbbreviation()+">"+dept.getTitle()
                                                        +"</option>");
                                                %>
                                            </select>
                                        </div>
                                        <div class="col-sm-2"></div>
                                        <div class="form-group col-sm-4">
                                            <label for="cycle">Ciclo:</label> 
                                            <select name="cycle" id="cycle" class="form-control" onchange="loadDegree(this.value);" readonly onclick="enablePlacingFields()">
                                                <% out.print("<option value="+cycle.getNumber()+">"+cycle.getTitle()
                                                        +"</option>");
                                                %>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-sm-4">
                                            <label for="degree">Corso di Laurea:</label> 
                                            <select name="degree" class="form-control" id="degree" onchange="loadCurriculum(this.value);" readonly onclick="enablePlacingFields()">
                                                <% out.print("<option value="+degree.getMatricula()+">"+degree.getTitle()
                                                        +"</option>");
                                                %>
                                            </select>
                                        </div>
                                        <div class="col-sm-2"></div>

                                        <div class="form-group col-sm-4">
                                            <label for="curriculum">Curriculum:</label> 
                                            <select name="curriculum" class="form-control" id="curriculum" readonly onclick="enablePlacingFields()">
                                                <% out.print("<option value="+curr.getMatricula()+">"+curr.getTitle()
                                                        +"</option>");
                                                %>
                                            </select>
                                            <input hidden="true" type="text" value="<%out.print(curr.getMatricula());%>" name="oldCurriculumMatricula">
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="form-group col-sm-4">
                                            <label for="matricula">Vecchia Matricola:</label>
                                            <input type="text" class="form-control" name="oldMatricula" id="oldMatricula" placeholder="Matricola" value="<%out.println(teaching.getMatricula());%>" readonly>
                                        </div>
                                        <div class="col-sm-2"></div>
                                        <div class="form-group col-sm-4">
                                            <label for="matricula">Nuova Matricola:</label>
                                            <input type="text" class="form-control" name="newMatricula" id="newMatricula" placeholder="Matricola" value="<%out.println(teaching.getMatricula());%>" readonly onclick="check(this);">
                                        </div>
                                        <div class="col-sm-2"></div>

                                    </div>

                                    <div class="row">
                                        <div class="form-group col-sm-4">
                                            <label for="title">Nome:</label>
                                            <input type="text" class="form-control" name="title" id="title" placeholder="Nome dell'insegnamento" value="<% out.println(teaching.getTitle()); %>" readonly onclick="check(this)">
                                        </div>
                                        
                                        <div class="col-sm-2"></div>
                                        <div class="form-group col-sm-4">
                                            <label for="abbreviazione">Abbreviazione:</label>
                                            <input type="text" class="form-control" name="abbreviation" id="abbreviation" placeholder="Abbreviazione" value="<%out.println(teaching.getAbbreviation());%>" readonly onclick="check(this)">
                                        </div>
                                        <div class="col-sm-2"></div>
                                    </div>
                                        <div class="row">
                                            <div class="form-group col-sm-2">
                                                <label for="year">Anno:</label> 
                                                <select name="year" id="year" class="form-control" readonly onclick="check(this)">
                                                    <%out.println("<option value="+teaching.getYear()+">"+teaching.getYear()+"</option>");%>
                                                </select>
                                            </div>

                                            <div class="col-sm-2"></div>
                                            <div class="form-group col-sm-2">
                                                <label for="semester">Semestre:</label> 
                                                <select name="semester" id="semester" class="form-control" readonly onclick="check(this)">
                                                    <%out.println("<option value="+teaching.getSemester()+">"+teaching.getSemester()+"</option>");%>
                                                </select>
                                            </div>
                                            <div class="col-sm-2"></div>
                                            <div class="form-group col-sm-2">
                                                <label for="active">Attivo:</label> 
                                                <select name="active" id="active" class="form-control" readonly onclick="check(this)">
                                                    <%out.println("<option value="+((teaching.isActive())?1:0)+">"+((teaching.isActive())?"SI":"NO")+"</option>");%>
                                                </select>
                                            </div>
                                        </div>
                                   
                                    <div class="row">
                                        <div class="form-group col-sm-12">
                                            <label for="link">Link al sillabus:</label>
                                            <input type="text" name="link" id="link" class="form-control" placeholder="link" value="<%out.println(teaching.getLink());%>" readonly onclick="check(this)">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-sm-1">
                                            <input type="submit" id="submit" class="btn btn-default">
                                        </div>
                                    </div>
                                </form>
			</div>
			<!-- Main Footer -->
			<!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
			<!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
			<!-- Or class "fixed" to  always fix the footer to the end of page -->
			<footer class="main-footer sticky footer-type-1">

				<div class="footer-inner">

					<!-- Add your copyright text here -->
					<div class="footer-text">
						&copy; 2014 <strong>Unisa</strong> <a href="http://www.unisa.it"
							target="_blank"></a>
					</div>


					<!-- Go to Top Link, just add rel="go-top" to any link to add this functionality -->
					<div class="go-up">

						<a href="#" rel="go-top"> <i class="fa-angle-up"></i>
						</a>

					</div>

				</div>

			</footer>
		</div>

	</div>
	

	<div class="page-loading-overlay">
		<div class="loader-2"></div>
	</div>




	<!-- Bottom Scripts -->
	<script type="text/javascript">
        function enablePlacingFields(){ //makes modificable the fields related to where a teaching is placed
            $("#department").removeAttr('readonly');
            $("#department").removeAttr('onclick');
            $("#cycle").removeAttr('readonly');
            $("#cycle").removeAttr('onclick');
            $("#degree").removeAttr('readonly');
            $("#degree").removeAttr('onclick');
            $("#curriculum").removeAttr('readonly');
            $("#curriculum").removeAttr('onclick');
            getCycle();
            getDepartment();
            $.ajax({url:"GetDegreeServlet",success:function(result){
	    	$("#degree").html(result);
	    }});
            loadCurriculum(0);
        } 
        function check(i){ //makes modificable the single field clicked
            $("#"+i.attributes["id"].value).removeAttr('readonly');
        }
        function getCycle(){
            $.ajax({url:"GetCycleServlet",success:function(result){
                    $("#cycle").html(result);
            }});
        }
        function getDepartment(){
            $.ajax({url:"GetDepartmentServlet",success:function(result){
                    $("#department").html(result);
            }});
        }
	function loadDegree(i){
            $.get('GetDegreeServlet?cycle='+i,function(responseJson) {   
            var $select = $('#degree');                           
               $select.find('option').remove(); 
               $.each(responseJson, function(key, value) {               
                   $('<option>').val(value.departmentAbbreviation).text(value.title).appendTo($select);      
                });
            });
	}
        function loadCurriculum(i){
	    $.ajax({url:"GetCurriculumServlet?degreeMatricula="+i,success:function(result){
	    	$("#curriculum").html(result);
                
	    }});
	}
	</script>
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