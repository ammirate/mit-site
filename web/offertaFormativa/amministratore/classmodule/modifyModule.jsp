<%-- 
    Document   : modifyClass
    Author     : Alessandro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="it.unisa.offerta_formativa.beans.Teaching"%>
<%@page import="it.unisa.offerta_formativa.beans.Curriculum"%>
<%@page import="it.unisa.integrazione.model.*"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%! 
public String title;
public Teaching teaching;
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

<style>
            html .select2-drop .select2-results li.select2-highlighted{ //per la selection di select2
                                                                        border-color: #cc3f44;
                                                                        background-color:#3875d7;
            }
            form .error{ //per validation
                         border-color: #cc3f44;
                         color:#cc3f44;
            }

            form input.error{
                border-color: #cc3f44;
            }
        </style>
</head>
<body class="page-body">
        <%
                title = (String)request.getAttribute("title");
                teaching = (Teaching) request.getAttribute("teaching");
	%>
	<%@include file="/offertaFormativa/topMenu.jsp" %>
	<div class="page-container">
		<!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->

		<!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
		<!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
		<!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
		<c:choose>
                <c:when test="${sessionScope.person.account.typeOfAccount != 'admin'}">
                    <script type="text/javascript">
                        $(function () {
                            $("#ErrorDialog").modal();
                        });
                    </script>
                    <%@include file="/offertaFormativa/errorPermission.jsp" %>
                </c:when>
                <c:when test="${sessionScope.person.account.typeOfAccount == 'admin'}">
                    <%@include file="/offertaFormativa/amministratore/lateralMenuAdmin.jsp" %>

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
                        <div class="row">
                            <form class="form" action="ModifyModuleServlet" method="post">
                                <div class="col-sm-1"></div>
                                <div class="panel panel-default col-sm-10 clearfix">
                                    <!-- Default panel contents -->
                                    <div class="panel-heading" style="text-align: center;"><%out.print(teaching.getMatricula() + " - " + teaching.getTitle());%></div>
                                    <div class="panel-body ">
                                        <form action="InsertTeachingServlet" method="post" role="form" >
                                            <div class="row">
                                                <div class="form-group col-sm-9">
                                                    <label for="title" style="color: black; font-weight: bold">Nome Modulo:</label>
                                                    <input type="text" minlength="2" class="form-control" name="moduleTitle" value="<%out.print(title);%>">
                                                </div>
                                                <input type="text" hidden="true" name="oldModuleTitle" value="<%out.print(title);%>">
                                                <input type="text" hidden="true" name="matricula" value="<%out.print(teaching.getMatricula());%>">
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-sm-2">
                                                    <input type="submit" class="form-control" value="Modifica" onclick="$('#form').validate();">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="col-sm-1"></div>
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
            </c:when>
            </c:choose>                                 
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
        function loadCurriculum(i){
	    $.ajax({url:"GetCurriculumServlet?degreeMatricula="+i,success:function(result){
	    	$("#curriculum").html(result);
                
	    }});
	}
        function loadTeaching(i){
            $.ajax({url:"GetTeachingServlet?curriculum="+i,success:function(result){
	    	$("#tablebody").html(result);
                $("#table").css("display","table");
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
            loadAssociation();
        }
        function loadClasses(i){
            var stringa="<h3>Inserisci Classi</h3>";
            for(j=1;j<=i;j++){
                stringa+="<div class='row'><div class='form-group col-sm-4'><input type='text' name='className"+j+"' id='className"+j+"' placeholder='Inserisci la classe "+j+"' class='form-control' onkeyup='loadAssociation();'></div></div>";
            }
            $("#classes").html(stringa);
            loadAssociation();
        }
        function loadAssociation(){
            var stringa ="<h2>Associa Docente</h2>";
            
            //alert($("#moduleName1").val());
            //$("#lastDiv").html($("#moduleName1").val());
            var moduleNum=$("#moduleNumber option:selected").val();
            var classNum = $("#classNumber option:selected").val();
                for(j=1;j<=classNum;j++){
                    stringa+="<h3>Associa Docenti a Classe "+j+" - "+ $("#className"+j).val() +"</h3>";
                    for(i=1;i<=moduleNum;i++){
                        stringa+="<div class='row'>";
                        stringa+="<div class='form-group col-sm-2'><label for='module'>"+$("#moduleName"+i).val()+"</label><select class='form-control' name='docente"+j+"-"+i+"' id='docente"+j+"-"+i+"'></select></div>";
                        stringa+="</div>";
                    }
                }
            $("#lastDiv").html(stringa);
            //$.ajax({url:"GetProfessorServlet?abbreviation="+$("#idDepartment").val(),success:function(result){
            $.ajax({url:"GetProfessorServlet?abbreviation=1",success:function(result){
                for(j=1;j<=classNum;j++){
                    for(i=1;i<=moduleNum;i++){
                        $("#docente"+j+"-"+i).html(result);
                    }
                }
            }});
        }
        function loadProfessor(){
            var string="";
            $.ajax({url:"GetProfessorServlet?abbreviation="+$("#idDepartment").val(),success:function(result){
                string=result;
            }});
            var moduleNum=$("#moduleNumber option:selected").val();
            var classNum = $("#classNumber option:selected").val();
            for(j=1;j<=classNum;j++){
                for(i=1;i<=moduleNum;i++){
                    //$("#docente"+i+"-"+j+).html(string);
                }
            }
        }
        
        </script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/TweenMax.min.js"></script>
	<script src="assets/js/resizeable.js"></script>
	<script src="assets/js/joinable.js"></script>
	<script src="assets/js/xenon-api.js"></script>
	<script src="assets/js/xenon-toggles.js"></script>
        <link rel="stylesheet" href="assets/js/select2/select2.css">
        <link href="assets/js/select2/select2-bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="assets/js/select2/select2.min.js"></script>
        <script src="assets/js/jquery-validate/jquery.validate.min.js" id="script-resource-7"></script>
        <script src="assets/js/jquery-validate/localization/messages_it.min.js" type="text/javascript"></script>
        <script src="assets/js/FunzioniOffertaFormativa.js" type="text/javascript"></script>
	<!-- JavaScripts initializations and stuff -->
	<script src="assets/js/xenon-custom.js"></script>

</body>
</html>