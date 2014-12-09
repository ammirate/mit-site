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
    <body class="page-body">
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

            <%@include file="/offertaFormativaJSP/amministratore/lateralMenuAdmin.jsp" %>

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

                                <%
                                    if (!map.isEmpty()) { %> <ul class="list-group" id="list" class="col-sm-12" name="list_all" style=" padding:0; "> <%
                                    // for (Department d : map.keySet()) {
                                    List<Department> deps = new ArrayList<Department>(map.keySet());
                                    Collections.sort(deps);
                                    for (int i = 0; i < deps.size(); i++) {
                                        Department d = deps.get(i);
                                    %><li class="list-group-item" style=" color: black; list-style-type: none; "><span style="cursor: pointer; font-weight: bold;"> <i class="glyphicon glyphicon-plus" style=" float: left;" onclick="setIcon(this)" ></i> &nbsp;&nbsp; <%out.print(d.getTitle());%></span><ul style=" padding:0;">
                                            <% if (map.get(d).keySet().size() != 0) {
//                                                    for (Degree de : map.get(d).keySet()) {
                                                    List<Degree> degs = new ArrayList<Degree>(map.get(d).keySet());
                                                    Collections.sort(degs);
                                                    for (int j = 0; j < degs.size(); j++) {
                                                        Degree de = degs.get(j);
                                                        if (de.isActive()) {
                                                            for (Cycle cy : cycle) {
                                                                if (cy.getNumber() == de.getCycle()) {
                                                                    cycleTitle = cy.getTitle();
                                                                }
                                                            }
                                            %>
                                            <li style=" color: #212121; margin: 10px; list-style-type: none; "><span style="cursor: pointer;"><i class="glyphicon glyphicon-plus" style=" float: left;" onclick="setIcon(this)" ></i> &nbsp;&nbsp; <%out.print(cycleTitle + " - " + de.getTitle());%> <a href="${pageContext.request.contextPath}/GetSyllabusServlet?teaching_matricula=1"> <i class="glyphicon glyphicon-info-sign" style=" font-size: medium; float: right;"  ></i></a></span><ul style=" padding:0;">
                                                        <%  if (map.get(d).get(de).keySet().size() != 0) {
                                                                List<Curriculum> currs = new ArrayList<Curriculum>(map.get(d).get(de).keySet());
                                                                Collections.sort(currs);
                                                                //for (Curriculum cu : map.get(d).get(de).keySet()) {
                                                                for (int k = 0; k < currs.size(); k++) {
                                                                    Curriculum cu = currs.get(k);
                                                                    if (cu.isActive()) {
                                                        %>
                                                    <li style=" color: #D84315; margin: 10px; list-style-type: none; "><span style="cursor: pointer;"><i class="glyphicon glyphicon-plus" style=" float: left;" onclick="setIcon(this)" ></i> &nbsp;&nbsp; <%out.print("Curriculum - " + cu.getTitle());%></span><ul style=" padding:0;">
                                                            <%
                                                                if (map.get(d).get(de).get(cu).size() != 0) {
                                                                    List<Teaching> teachs = new ArrayList<Teaching>(map.get(d).get(de).get(cu));
                                                                    Collections.sort(teachs);
                                                                    //for (Teaching te : map.get(d).get(de).get(cu)) {
                                                                    for (int y = 0; y < teachs.size(); y++) {
                                                                        Teaching te = teachs.get(y);
                                                                        if (te.isActive()) {
                                                            %>
                                                            <li style="  margin: 10px; list-style-type: none; "><span style="cursor: pointer; color: #112B62;"> &nbsp;&nbsp; <a style="color: #112B62; font-weight: bold;" href="${pageContext.request.contextPath}/GetSyllabusServlet?teaching_matricula=<%out.print(te.getMatricula());%>"><%out.print("Corso di " + te.getTitle());%></a></span></li>
                                                                    <%}
                                                                            }
                                                                        }

                                                                    %>
                                                        </ul></li>  
                                                        <%                                                       }
                                                                }
                                                            }  %>      
                                                </ul></li>
                                                <%
                                                            }
                                                        }
                                                    }
                                                %>

                                        </ul></li>
                                        <%
                                                }
                                            }
                                        %>  </ul>

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
                function setIcon(obj) {
                    if(obj.className == "glyphicon glyphicon-plus"){
                    obj.className = "glyphicon glyphicon-minus";}
                    else obj.className = "glyphicon glyphicon-plus";
                }
            </script>
            <script src="assets/js/bootstrap.min.js"></script>
            <script src="assets/js/TweenMax.min.js"></script>
            <script src="assets/js/resizeable.js"></script>
            <script src="assets/js/joinable.js"></script>
            <script src="assets/js/xenon-api.js"></script>
            <script src="assets/js/xenon-toggles.js"></script>
            <script>
                $(function () {
                    $('#list').find('SPAN').click(function (e) {
                        $(this).parent().children('UL').toggle();
                    });
                });
                $(function () {
                    //hide or collapsed initially.
                    $('#list').find('UL').hide();

                });
            </script>

            <!-- JavaScripts initializations and stuff -->
            <script src="assets/js/xenon-custom.js"></script>

    </body>
</html>