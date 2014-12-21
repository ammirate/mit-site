<%-- 
    Document   : ModifyDegree
    Author     : Davide
--%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.HashMap"%>
<%@page import="it.unisa.offerta_formativa.beans.Degree"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%! public Degree degree;
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
    <body class="page-body" onload="loadDepAndCycles()">
        <%  degree = (Degree) request.getAttribute("degree");
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
                <div class="row">
                    <div class="col-sm-1"></div>
                    <div class="col-sm-10">
                        <div class="panel panel-default">
                            <div class="panel-heading" style="text-align: center; ">
                                Modifica Corso di Laurea - <% out.print(degree.getTitle()); %>
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

                                    <%  if (request.getAttribute("exist") != null) {

                                    %>
                                    <p class="bg-danger">Matricola del Corso di laurea già esistente inserirne un'altra</p>
                                    <% }%>
                                    <div class="row"> <br> </div>
                                    <div class="form-group col-sm-2">
                                        <label for="title" style="color: black; font-weight: bold">Matricola:</label>
                                        <input type="text" class="form-control" id="matricula_text" name="matricula" maxlength="5" value="<% out.print(degree.getMatricula()); %>" >
                                    </div>

                                    <div class="col-sm-5">
                                        <label for="title" style="color: black; font-weight: bold" >Titolo:</label>
                                        <input type="text" id="title_text" maxlength="50" name="titolo" class="form-control" value="<% out.print(degree.getTitle()); %>" > 
                                    </div>

                                    <div class="form col-sm-1"></div>

                                    <label for="title" style="color: black; font-weight: bold">Stato:</label>
                                    <form role="form col-sm-5">
                                        <div class="form col-sm-1"></div>
                                        <label class="radio-inline">
                                            <input type="radio" name="optradio" id="status_active" checked><p style="color: black">Attivo</p>
                                        </label>
                                        <label class="radio-inline">
                                            <% if (!degree.isActive()) { %>
                                            <input type="radio" name="optradio" id="status_disable" checked ><p style="color: black">Disattivo</p>
                                            <% } else if (degree.isActive()) { %>
                                            <input type="radio" name="optradio"  id="status_disable"><p style="color: black">Disattivo</p>
                                            <% } %>
                                        </label>
                                    </form>

                                </div>
                                <div> <br> </div>  


                                <div class="row">
                                    <div class="form-group col-sm-8">
                                        <label style="color: black; font-weight: bold">Dipartimento:</label><select name="department" class="form-control" id="department" >
                                        </select>
                                    </div>

                                    <div class="col-sm-4">
                                        <label style="color: black; font-weight: bold">Ciclo:</label><select name="cycle" class="form-control" id="cycles" >
                                        </select> 
                                    </div>
                                    <div> <br> </div>

                                </div>

                                <div> <br> </div>

                                <div class="row">
                                    <div class="form-group col-sm-6">
                                        <label for="title" style="color: black; font-weight: bold" >Link:</label>
                                        <input type="text" id="link_text" maxlength="500" class="form-control" name="link" value="<% out.print(degree.getLink());%>" >
                                    </div>

                                </div>

                                <div> <br> </div>
                                <div> <br> </div>
                                <div> <br> </div>

                                <div class="row">

                                    <div class="form-group col-sm-10">
                                        <button type="button" style=" height: 32px; width: 90px; color: black" onclick="RevertModify()">Annulla</button>
                                    </div>

                                    <div class="form-group col-sm-1">
                                        <button type="button" style=" height: 32px; width: 90px; color: black" onclick="UpdateDegree()" id="button_confirm">Conferma</button>
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

                <div> <br> </div>
                <div> <br> </div>
                <div> <br> </div>
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
                function loadDepAndCycles() {
                    $.get('GetCycleServlet', function (responseJson) {
                        var $select = $('#cycles');
                        $select.find('option').remove();
                        $.each(responseJson, function (key, value) {
                            $('<option>').val(value.cycle_number).text(value.title).appendTo($select);
                        });
                        $('#cycles').val('<%= degree.getCycle()%>');
                        $("#cycles").select2({allowClear: true}).on('select2-open', function () {
                            $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                        });

                    });
                    $.get('GetDepartmentServlet', function (responseJson) {
                        var $select_dep = $('#department');
                        $select_dep.find('option').remove();
                        $.each(responseJson, function (key, value) {
                            $('<option>').val(value.departmentAbbreviation).text(value.title).appendTo($select_dep);
                        });
                        $('#department').val('<%= degree.getDepartmentAbbreviation()%>');
                        $('#department').select2({allowClear: true}).on('select2-open', function () {
                            $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                        });
                    });
                }

                function RevertModify() {
                    document.location.href = '${pageContext.request.contextPath}/ShowDegreeServlet';
                }
                function UpdateDegree() {
                    var cycle = $("#cycles option:selected").val();
                    var departmentAbb = $("#department option:selected").val();
                    var degree_matricula = $("#matricula_text").val();
                    var old_matricula = '<%= degree.getMatricula()%>';
                    var link = $("#link_text").val();
                    var title = $("#title_text").val();
                    if ((validate(degree_matricula)) && (validate(link)) && (validate(title)) && (cycle != "nocyc") && (departmentAbb != "nodep")) {
                        if (document.getElementById('status_active').checked) {
                            var status = "true";
                        } else if (document.getElementById('status_disable').checked) {
                            var status = "false";
                        }

                        var r = confirm("Sei sicuro di voler modificare il corso di Laurea: " + title);
                        if (r == true) {
                            document.location.href = '${pageContext.request.contextPath}/UpdateDegreeServlet?old_matricula=' + old_matricula + '&degree_matricula=' + degree_matricula + '&cycle=' + cycle + '&departmentAbb=' + departmentAbb + '&link=' + link + '&title=' + title + '&status=' + status;
                        }
                    } else {
                        var msg;
                        if (!validate(degree_matricula)) {
                            msg = "Inserisci una matricola";
                        } else if (!validate(title)) {
                            msg = "Inserisci un titolo";
                        } else if (!validate(link)) {
                            msg = "Inserisci un link";
                        } else if (cycle === "nocyc") {
                            msg = "Seleziona un ciclo";
                        } else if (departmentAbb === "nodep") {
                            msg = "Seleziona un dipartimento";
                        }
                        var a = alert(msg);
                    }
                }
                function validate(toValidate) {
                    if (toValidate.length > 0)
                        return true;
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
            <!-- JavaScripts initializations and stuff -->
            <script src="assets/js/xenon-custom.js"></script>

    </body>
</html>