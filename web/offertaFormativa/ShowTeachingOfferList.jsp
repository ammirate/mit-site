<%-- 
    Document   : ShowTeachingOfferList
    Author     : Davide
--%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="it.unisa.offerta_formativa.beans.Cycle"%>
<%@page import="java.util.HashMap"%>
<%@page import="it.unisa.offerta_formativa.beans.Curriculum"%>
<%@page import="it.unisa.offerta_formativa.beans.Degree"%>
<%@page import="it.unisa.offerta_formativa.beans.Teaching"%>

<%@page import="it.unisa.offerta_formativa.beans.Department"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%! public HashMap< Department, HashMap<Degree, HashMap<Curriculum, ArrayList<Teaching>>>> map;
    public ArrayList<Cycle> cycle;
    public String cycleTitle;
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

        </script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
                        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
                        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
                <![endif]-->


    </head>
    <body class="page-body" onload="loadDep()">
        <% map = (HashMap< Department, HashMap<Degree, HashMap<Curriculum, ArrayList<Teaching>>>>) request.getAttribute("map");
            cycle = (ArrayList<Cycle>) request.getAttribute("cycles");
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

            <%@include file="/offertaFormativa/amministratore/lateralMenuAdmin.jsp" %>

            <div class="main-content">
                <div class="row">
                    <div class="col-sm-1"></div>
                    <div class="col-sm-10">
                        <div class="panel panel-default">
                            <div class="panel-heading" style="text-align: center; ">
                                Offerta Formativa
                            </div>
                            <div class="panel-body">



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
                                    <div class="col-md-12">
                                        <div class="panel-group panel-group-joined" id="accordion-teaching-offer">
                                           
                                        </div>
                                    </div>
                                </div>

                                <!-- Main Footer -->
                                <!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
                                <!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
                                <!-- Or class "fixed" to  always fix the footer to the end of page -->





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
                function loadDep() {
                    var div = document.getElementById("accordion-teaching-offer");
                   $.get('GetDepartmentServlet', function (responseJson) {
                        var stringa= "";
                        $.each(responseJson, function (key, value) {
                            stringa += "<div class='panel panel-default'>";
                            stringa += "<div class='panel-heading'>";
                            stringa += "<h4 class='panel-title'>";
                            stringa += "<a data-toggle='collapse' href='#" + value.departmentAbbreviation + "' class='collapsed'>";
                            stringa += value.title + "</a></h4></div>";
                            stringa += "<div id='" + value.departmentAbbreviation + "' class='panel-collapse collapse'>";
                            stringa += "<div class='panel-body'>";
                            stringa += "blabla";
                            stringa += "</div></div></div>";
                        });
                     
                        div.innerHTML = stringa;
                    });
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