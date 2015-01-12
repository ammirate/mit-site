<%-- 
    Document   : ShowSyllabus
    Author     : Davide
--%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%! public ArrayList<String> htmlSyllabus;%>

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
        <link rel="stylesheet"
              href="assets/css/tabella-esse3.css">
        <link rel="stylesheet" href="assets/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/xenon-core.css">
        <link rel="stylesheet" href="assets/css/xenon-forms.css">
        <link rel="stylesheet" href="assets/css/xenon-components.css">
        <link rel="stylesheet" href="assets/css/xenon-skins.css">
        <link rel="stylesheet" href="assets/css/custom.css">

        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script>
            jQuery(document).ready(
                    function () {
                        $.ajax({url: "GetDegreeServlet?idCycle=1", success: function (result) {
                                $("#degree").html(result);
                            }});
                    },
                    function ($) {
                        /*bisogna metterla in ogni pagina*/
                        $(window).on('beforeunload', function (e) {
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

                        //quì ci vanno gli ID delle funzionalità che verranno messe all interno del menù laterale...basta copiare una riga e incollarla,
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

                        $("#logout").click(function () {
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
        <% 	htmlSyllabus = (ArrayList<String>) request.getAttribute("htmlSyllabus");
        %>
        <%@include file="/offertaFormativa/topMenu.jsp" %>
        <div class="page-container">
            <!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->

            <!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
            <!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
            <!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
            <c:choose>
                <c:when test="${sessionScope.person.account.typeOfAccount == 'professor'}">
                    <%@include file="/offertaFormativa/lateralMenuProf.jsp" %>
                </c:when>
                <c:when test="${sessionScope.person.account.typeOfAccount == 'Bstudent'}">
                    <%@include file="/offertaFormativa/lateralMenuStud.jsp" %>
                </c:when>
                <c:when test="${sessionScope.person.account.typeOfAccount == 'Mstudent'}">
                    <%@include file="/offertaFormativa/lateralMenuStud.jsp" %>
                </c:when>
                <c:when test="${sessionScope.person.account.typeOfAccount == 'admin'}">
                    <%@include file="/offertaFormativa/amministratore/lateralMenuAdmin.jsp" %>
                </c:when>
            </c:choose>

            <div class="main-content">
                <div class="row">
                    <div class="col-sm-1"></div>
                    <div class="col-sm-10">
                        <div class="panel panel-default">
                            <h6>
                            <div class="panel-heading" style="text-align: center; ">
                                <% 
                                out.print(htmlSyllabus.get(0));
                                %>

                            </div>
                                </h6>
                            <div>
                                <div class="row"> <br> </div>
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

                                <div class="text-justify">


                                    <%  out.print(htmlSyllabus.get(1));%>
                                    <!-- Main Footer -->
                                    <!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
                                    <!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
                                    <!-- Or class "fixed" to  always fix the footer to the end of page -->
                                </div>

                            </div>           


                        </div>


                    </div>

                    <div class="col-sm-1"></div>

                </div>  


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
                                    
                <div class="page-loading-overlay">
                    <div class="loader-2"></div>
                </div>
                                    
            </div>              
            <!-- Bottom Scripts -->
            <script type="text/javascript">
                function loadDegree(i) {
                    $.ajax({url: "GetDegreeServlet?idCycle=" + i, success: function (result) {
                            $("#degree").html(result);
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