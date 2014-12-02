<%@page import="it.unisa.offerta_formativa.beans.Degree"%>
<%@page import="it.unisa.offerta_formativa.beans.Department"%>
<%@page import="it.unisa.offerta_formativa.beans.Cycle"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%! public ArrayList<Cycle> cycles; %>
<%! public ArrayList<Department> departments; 
	public ArrayList<Degree> degrees;
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
<script>
	jQuery(document).ready(
			function(){
			    $.ajax({url:"GetDegreeServlet?idCycle=1",success:function(result){
			    	$("#degree").html(result);
			    }});
			},
			
			function($) {
				/*bisogna metterla in ogni pagina*/
				$(window).on('beforeunload', function(e) {
					if (localStorage.getItem("rememberMeForLogin") == "no") {
						localStorage.removeItem("username");
						localStorage.removeItem("typology");
						localStorage.removeItem("primaryKey");

						localStorage.removeItem("offertaFormativa");
						localStorage.removeItem("gestioneTesi");
						localStorage.removeItem("gestioneTirocinio");
						localStorage.removeItem("dottorato");
						localStorage.removeItem("superAmministratore");
						window.location.href = "index.html";
					}
				});

				//qu� ci vanno gli ID delle funzionalit� che verranno messe all interno del men� laterale...basta copiare una riga e incollarla,
				//facendo attenzione a cambiare l'ID
				//Es: $("pippopaperino_"+localStorage.getItem("offertaFormativa")).empty();
				//ovviamente la localStorage cambia a seconda se si sta nella pagina di offerta formativa, gestione tesi, ecc...
				$(
						"#funzionalita1Permission_"
								+ localStorage.getItem("offertaFormativa"))
						.empty();
				$(
						"#funzionalita3Permission_"
								+ localStorage.getItem("offertaFormativa"))
						.empty();

				//if(localStorage.getItem("username")==null){
				//	window.location.replace("pageError.html");
				//}

				$("#spaceForUsername").html(
						localStorage.getItem("username") + ", "
								+ localStorage.getItem("primaryKey")
								+ ' <i class="fa-angle-down"></i>');

				$("#logout").click(function() {
					localStorage.removeItem("username");
					localStorage.removeItem("typology");
					localStorage.removeItem("primaryKey");
					window.location.href = "index.html";

					localStorage.removeItem("offertaFormativa");
					localStorage.removeItem("gestioneTesi");
					localStorage.removeItem("gestioneTirocinio");
					localStorage.removeItem("dottorato");
					localStorage.removeItem("superAmministratore");

				});
			});
</script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->


</head>
<body class="page-body">
	<% 	cycles = (ArrayList<Cycle>)request.getAttribute("cycles");
		departments = (ArrayList<Department>)request.getAttribute("departments");
		degrees= (ArrayList<Degree>) request.getAttribute("degrees");
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
				<h2>Inserimento Insegnamento</h2>
				<form action="InsertDegreeServlet" method="post" role="form" class="form-horizontal">
					<div class="row">
					<div class="form-group col-sm-4">
						<label for="department">Dipartimento:</label> 
						<select name="idDepartment" id="idDepartment" class="form-control">
							<%
								if (departments.size() != 0)
									for (Department d : departments) {
							%><option value=<%out.print(d.getId());%>>
								<%
									out.print(d.getTitle());
								%>
							</option>
							<%
								}
							%>
						</select>
					</div>
					<div class="col-sm-2"></div>
					<div class="form-group col-sm-4">
						<label for="cycle">Ciclo:</label> 
						<select name="idCycle" class="form-control" onchange="loadDegree(this.value);">
							<%
								if (cycles.size() != 0)
									for (Cycle c : cycles) {
							%><option value=<%out.print(c.getId());%>>
								<%
									out.print(c.getTitle());
								%>
							</option>
							<%
								}
							%>
						</select>
					</div>
					</div>
					<div class="row">
					<div class="form-group col-sm-4">
						<label for="degree">Corso di Laurea:</label> <select name="idDegree"
							class="form-control" id="degree">
							
						</select>
					</div>
					<div class="col-sm-2"></div>
					
					<div class="form-group col-sm-4">
						<label for="curriculum">Curriculum:</label> 
						<select name="idCurriculum" class="form-control" id="curriculum">
							
						</select>
					</div>
					
					</div>
					<div class="row">
					<div class="form-group col-sm-4">
                		<label for="matricula">Matricola:</label>
                		<input type="text" class="form-control" name="matricula" placeholder="Matricola">
                	</div>
                	<div class="col-sm-2"></div>
                	<div class="form-group col-sm-6">
                		<label for="title">Nome:</label>
                		<input type="text" class="form-control" name="title" placeholder="Nome dell'insegnamento">
                	</div>
                	
					</div>
					
					<div class="row">
					<div class="form-group col-sm-4">
                		<label for="abbreviazione">Abbreviazione:</label>
                		<input type="text" class="form-control" name="matricula" placeholder="Abbreviazione">
                	</div>
                	<div class="col-sm-2"></div>
                	<div class="form-group col-sm-2">
						<label for="year">Anno:</label> 
						<select name="year"class="form-control">
							<option value=1>1</option>
							<option value=2>2</option>
							<option value=3>3</option>
							<option value=4>4</option>
							<option value=5>5</option>
						</select>
                	</div>
                	
                	<div class="col-sm-1"></div>
                	<div class="form-group col-sm-2">
						<label for="semester">Semestre:</label> 
						<select name="semester"class="form-control">
							<option value=1>1</option>
							<option value=2>2</option>
						</select>
                	</div>
                	
					</div>
					
					<div class="row">
						<div class="form-group col-sm-12">
                		<label for="link">Link al sillabus:</label>
                		<input type="text" name="link" class="form-control" placeholder="link">
                	</div>
					</div>
                                        <div class="row">
                                            <div class="col-sm-1"></div>
                                            <div class="form-group col-sm-2">
                                                    <label for="classNumber">Classi:</label> 
                                                    <select id="classNumber" name="classNumber" class="form-control" onchange="loadClasses(this.value);">
                                                        <option value=1>1</option>
                                                        <option value=2>2</option>
                                                    </select>
                                            </div>
                                            <div class="form-group col-sm-2"></div>
                                            <div class="form-group col-sm-2">
                                                    <label for="moduleNumber">Moduli:</label> 
                                                    <select id="moduleNumber" name="moduleNumber" class="form-control" onchange="loadModules(this.value);" >
                                                        <option value=1>1</option>
                                                        <option value=2>2</option>
                                                    </select>
                                            </div>
                                        </div>
                                        <div id="modules">
                                        </div>   
					<div id="classes">
                                        </div> 
                                        <div id="lastDiv">

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
	function loadDegree(i){
	    $.ajax({url:"GetDegreeServlet?idCycle="+i,success:function(result){
	    	$("#degree").html(result);
	    }});
	}
        
	</script>
        <script type="text/javascript">
        function loadModules(i){
            var stringa="<h3>Inserisci Moduli</h3>";
            for(j=1;j<=i;j++){
                stringa+="<div class='row'><div class='form-group col-sm-4'><input type='text' name='moduleName"+j+"' id='moduleName"+j+"' placeholder='Inserisci il modulo "+j+"' class='form-control' onkeyup='loadAssociation();'></div></div>";
            }
            $("#modules").html(stringa);
        }
        function loadClasses(i){
            var stringa="<h3>Inserisci Classi</h3>";
            for(j=1;j<=i;j++){
                stringa+="<div class='row'><div class='form-group col-sm-4'><input type='text' name='className"+j+"' id='className"+j+"' placeholder='Inserisci la classe "+j+"' class='form-control' onkeyup='loadAssociation();'></div></div>";
            }
            $("#classes").html(stringa);
        }
        function loadAssociation(){
            var stringa ="<h2>Associa Docente</h2>";
            
            //alert($("#moduleName1").val());
            //$("#lastDiv").html($("#moduleName1").val());
            var moduleNum=$("#moduleNumber option:selected").val();
            var classNum = $("#classNumber option:selected").val();
            
            for(i=1;i<=moduleNum;i++){
                stringa ="<h3>Associa Docenti a Classe "+i+"</h3>";
                for(j=1;j<=classNum;j++){
                    //stringa+="<div class='row'>";
                    //stringa+="<div class='form-group col-sm-2'><label for='year'>"+$("#moduleName"+j+" option:selected").val()+"</label></div>";
                    //stringa+="</div>";
                }
            }
            
            $("#lastDiv").html(stringa);
            
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