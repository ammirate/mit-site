<%-- 
    Document   : listClassModule
    Author     : Alessandro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="it.unisa.integrazione.model.*"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="it.unisa.offerta_formativa.beans.ProfModuleClass"%>
<%@page import="it.unisa.offerta_formativa.beans.Module"%>
<%@page import="it.unisa.offerta_formativa.beans.ClassPartition"%>
<%@page import="it.unisa.offerta_formativa.beans.Teaching"%>
<%@page import="it.unisa.offerta_formativa.beans.Curriculum"%>
<%@page import="java.util.ArrayList"%>
<%! ArrayList<ClassPartition> classes;%>
<%! ArrayList<Module> modules;%>
<%! ArrayList<Person> professors;%>
<%! HashMap<ProfModuleClass, String> profmoduleclass;
    Teaching teaching;
%>
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


    </head>
    <body class="page-body">
        <% 	classes = (ArrayList<ClassPartition>) request.getAttribute("classes");
            modules = (ArrayList<Module>) request.getAttribute("modules");
            profmoduleclass = (HashMap<ProfModuleClass, String>) request.getAttribute("profmoduleclass");
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
                    jQuery(document).ready(function ($) {
                        if(<% out.print(request.getAttribute("error"));%>){
                            $("#error").show();
                            //$("#error").fadeOut(3500);
                        }
                        if(<% out.print(request.getAttribute("success"));%>){
                            $("#success").show();
                            //$("#error").fadeOut(3500);
                        }
                    });
                    </script>
                    <div class="row">
                        <p class="bg-danger" id="error" style="display:none;"><% out.print(request.getAttribute("errorMessage"));%></p>
                    </div>
                    <div class="row">
                        <p class="bg-success" id="success" style="display:none;"><% out.print(request.getAttribute("successMessage"));%></p>
                    </div>

                    <div class="row "><div class="col-sm-1"></div>
                        <div class="panel col-sm-10"><h2><% out.print(teaching.getMatricula() + " - " + teaching.getTitle() + "");%></h2></div>
                        <div class="col-sm-1"></div>
                    </div>        
                
                <div class="row ">
                    <div class="col-sm-1"></div>
                    <div class=" col-sm-10">
                        
                        
                        <div class="panel panel-default clearfix">
                            <!-- Default panel contents -->
                            <div class="panel-heading" style="text-align: center; ">Classi associate all'insegnamento</div>
                            <div class="panel-body ">
                                <!-- Table -->
                                <div class="row table-responsive">
                                    <table class="table table-striped" id="tableClasses" >
                                        <thead>
                                            <tr>
                                                <th class="col-sm-8">Titolo</th>
                                                <th class="col-sm-2"></th>
                                                <th class="col-sm-2"></th>
                                            </tr>
                                        </thead>
                                        <tbody id="tablebodyClasses">
                                            <%
                                                for (int i = 0; i < classes.size(); i++) {
                                                    out.println("<tr><td>" + classes.get(i).getTitle() + "</td><td><a href='ShowModifyClassServlet?matricula=" + classes.get(i).getTeachingMatricula() + "&classTitle=" + classes.get(i).getTitle() + "'>Modifica</a></td><td>");
                                                    if (classes.size() == 1) {
                                                        out.print("Elimina</td></tr>");
                                                    } else {
                                                        out.print("<a href='DeleteClassServlet?matricula=" + teaching.getMatricula() + "&title=" + classes.get(i).getTitle() + "'>Elimina</a></td></tr>");
                                                    }
                                                }
                                            %>
                                        </tbody>            
                                    </table>

                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="col-sm-1"></div>
                </div>
                <div class="row clearfix">
                    <div class="col-sm-1"></div>
                    <div class="col-sm-10">
                        <div class="panel panel-default">
                            <!-- Default panel contents -->
                            <div class="panel-heading" style="text-align: center;">Moduli associati all'insegnamento</div>
                            <!-- Table -->
                            <div class="panel-body">


                                <div class="row table-responsive">

                                    <table class="table table-striped" id="teaching" >
                                        <thead>
                                            <tr>
                                                <th class="col-sm-8">Titolo</th>
                                                <th class="col-sm-2"></th>
                                                <th class="col-sm-2"></th>
                                            </tr>
                                        </thead>
                                        <tbody id="tablebodyModules">
                                            <%
                                                for (int i = 0; i < modules.size(); i++) {
                                                    out.println("<tr><td>" + modules.get(i).getTitle() + "</td><td><a href='ShowModifyModuleServlet?matricula=" + modules.get(i).getTeachingMatricula() + "&moduleTitle=" + modules.get(i).getTitle() + "'>Modifica</a></td><td>");
                                                    if (modules.size() == 1) {
                                                        out.print("Elimina</td></tr>");
                                                    } else {
                                                        out.print("<a href='DeleteModuleServlet?matricula=" + teaching.getMatricula() + "&title=" + modules.get(i).getTitle() + "'>Elimina</a></td></tr>");
                                                    }
                                                }
                                            %>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-1"></div>
                </div>

                <div class="row clearfix">
                    <div class="col-sm-1"></div>
                    <div class="col-sm-10">
                        <div class="panel panel-default">
                            <!-- Default panel contents -->
                            <div class="panel-heading" style="text-align: center;">Associazione dei Docenti</div>
                            <!-- Table -->
                            <div class="panel-body">
                                <div class="row table-responsive">
                                <table class="table table-striped responsive" id="tableModules">
                                    <thead>
                                        <tr>
                                            <th>Classe</th>
                                            <th>Modulo</th>
                                            <th>Professore</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody id="tablebodyAssociation">
                                        <%
                                            for (Entry<ProfModuleClass, String> e : profmoduleclass.entrySet()) {
                                                out.print("<tr><td>" + e.getKey().getClassTitle() + "</td><td>" + e.getKey().getModuleTitle() + "</td><td>" + e.getValue() + "</td>");
                                                out.print("<td><a href='ShowModifyProfAssociationServlet?classTitle=" + e.getKey().getClassTitle());
                                                out.print("&moduleTitle=" + e.getKey().getModuleTitle() + "&matricula=" + e.getKey().getTeachingMatricula());
                                                out.print("&mail=" + e.getKey().getProfEmail() + "'>Modifica</a></td></tr>");
                                            }
                                        %>

                                    </tbody>
                                </table>
                                </div>        
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