<%-- 
    Document   : listTeaching
    Created on : 6-dic-2014, 13.25.32
    Author     : Alessandro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="it.unisa.integrazione.model.Cycle"%>
<%@page import="it.unisa.integrazione.model.Department"%>
<%@page import="it.unisa.offerta_formativa.beans.Teaching"%>
<%@page import="java.util.ArrayList"%>
<%! ArrayList<Cycle> cycles;%>
<%! ArrayList<Department> departments;%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script type="text/javascript">
            $(window).load(function () {
                loadDepartment();
                loadCycle();
                changeSelect();
            });


        </script>
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
        <% 	cycles = (ArrayList<Cycle>) request.getAttribute("cycles");
            departments = (ArrayList<Department>) request.getAttribute("departments");

        %>
        <%@include file="/offertaFormativa/topMenu.jsp" %>
        <div class="page-container">

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
                            jQuery(document).ready(function ($) {
                                $('a[href="#layout-variants"]').on('click', function (ev) {
                                    ev.preventDefault();

                                    var win = {
                                        top: $(window).scrollTop(),
                                        toTop: $("#layout-variants").offset().top - 15
                                    };

                                    TweenLite.to(win, .3, {
                                        top: win.toTop,
                                        roundProps: ["top"],
                                        ease: Sine.easeInOut,
                                        onUpdate: function () {
                                            $(window).scrollTop(win.top);
                                        }
                                    });
                                });
                            });
                        </script>

                        <div class="row">
                            <div class="col-sm-1"></div>
                            <div class="panel panel-default col-sm-10 clearfix">
                                <!-- Default panel contents -->
                                <div class="panel-heading" style="text-align: center; ">Visualizza Insegnamenti</div>
                                <div class="panel-body ">
                                    <form action="" method="post" role="form" class="form-horizontal">
                                        <div class="row">
                                            <div class="col-sm-1"></div>
                                            <div class="form-group col-sm-4">
                                                <label for="department" style="color: black; font-weight: bold">Dipartimento:</label> 
                                                <select name="department" id="department" class="form-control" onchange="loadDegree();">

                                                </select>
                                            </div>
                                            <div class="col-sm-1"></div>
                                            <div class="form-group col-sm-4">
                                                <label for="cycle" style="color: black; font-weight: bold">Ciclo:</label> 
                                                <select name="cycle" class="form-control" id="cycle" onchange="loadDegree();">

                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-1"></div>
                                            <div class="form-group col-sm-4">
                                                <label for="degree" style="color: black; font-weight: bold">Corso di Laurea:</label> 
                                                <select name="degree" class="form-control" id="degree" onchange="loadCurriculum(this.value);">

                                                </select>
                                            </div>
                                            <div class="col-sm-1"></div>

                                            <div class="form-group col-sm-4">
                                                <label for="curriculum" style="color: black; font-weight: bold">Curriculum:</label> 
                                                <select name="curriculum" class="form-control" id="curriculum" onchange="loadTeachingTable(this.value);">

                                                </select>
                                            </div>
                                        </div>
                                        
                                    </form>
                                    <div class="row table-responsive">

                                            <table class="table table-striped" id="teaching" >

                                            </table>

                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-1"></div>
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
        <link rel="stylesheet" href="assets/js/select2/select2.css">
        <link href="assets/js/select2/select2-bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="assets/js/select2/select2.min.js"></script>
        <script type="text/javascript">
                                                    function loadTeachingTable(i) {
                                                        $.get('GetTeachingServlet?curriculum=' + i, function (responseJson) {
                                                            var stringa = "<thead><tr><th>Matricola</th><th>Nome</th>";
                                                            stringa += "<th>Abbreviazione</th><th>Anno</th>";
                                                            stringa += "<th>Semestre</th><th></th><th></th><th></th></tr></thead><tbody>";
                                                            $.each(responseJson, function (key, value) {
                                                                stringa += "<tr><td>" + value.matricula + "</td>";
                                                                stringa += "<td>" + value.title + "</td>";
                                                                stringa += "<td>" + value.abbreviation + "</td>";
                                                                stringa += "<td>" + value.year + "</td>";
                                                                stringa += "<td>" + value.semester + "</td>";
                                                                stringa += "<td>" + value.active + "</td>";
                                                                stringa += "<td><a href='ShowModifyTeachingServlet?matricula=" + value.matricula + "&curriculum=" + i + "'>Modifica</a></td>";
                                                                stringa += "<td><a href='ShowAssociationServlet?matricula=" + value.matricula + "'>Dettagli</a></td></tr>";
                                                            });
                                                            stringa += "</tbody>";
                                                            $("#teaching").html(stringa);
                                                        });
                                                    }
        </script>
        <!-- Bottom Scripts -->
        <script src="assets/js/FunzioniOffertaFormativa.js" type="text/javascript"></script>
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