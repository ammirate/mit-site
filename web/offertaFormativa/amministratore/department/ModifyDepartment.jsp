<%-- 
    Document   : ModifyDepartment
    Author     : Davide
--%>
<<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="it.unisa.model.Department"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%! public Department department;
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
    <body class="page-body" >
        <%  department = (Department) request.getAttribute("department");
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
                        <div class="row">
                            <div class="col-sm-1"></div>
                            <div class="col-sm-10">
                                <div class="panel panel-default">
                                    <div class="panel-heading" style="text-align: center; ">
                                        Modifica Dipartimento - <% out.print(department.getTitle()); %>
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
                                            <p class="bg-danger">Matricola del Dipartimento già esistente inserirne un'altra</p>
                                            <% }%>
                                            <div class="row"> <br> </div>
                                            <div class="form-group col-sm-4">
                                                <label for="title" style="color: black; font-weight: bold">Abbreviazione:</label>
                                                <input type="text" class="form-control" id="abbreviation_text" name="matricula" maxlength="50" value="<% out.print(department.getAbbreviation()); %>" >
                                            </div>

                                            <div class="col-sm-7">
                                                <label for="title" style="color: black; font-weight: bold" >Titolo:</label>
                                                <input type="text" id="title_text" maxlength="500" name="titolo" class="form-control" value="<% out.print(department.getTitle()); %>" > 
                                            </div>

                                            <div class="form col-sm-1"></div>

                                        </div>
                                        <div> <br> </div>  

                                        <div class="row">
                                            <div class="form-group col-sm-11">
                                                <label for="title" style="color: black; font-weight: bold" >Moodle Url:</label>
                                                <input type="text" id="moodle_url_text" maxlength="1000" class="form-control" name="moodle_url" value="<% out.print(department.getUrlMoodle());%>" >
                                            </div>

                                        </div>
                                        <div> <br> </div>  

                                        <div class="row">
                                            <div class="form-group col-sm-11">
                                                <label for="title" style="color: black; font-weight: bold" >Moodle token:</label>
                                                <input type="text" id="token_text" maxlength="500" class="form-control" name="link" value="<% out.print(department.getToken());%>" >
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
                                                <button type="button" style=" height: 32px; width: 90px; color: black" onclick="UpdateDepartment()" id="button_confirm">Conferma</button>
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
                </c:when>
            </c:choose>
        </div>

        <!-- Bottom Scripts -->
        <script type="text/javascript">
            function RevertModify() {
                document.location.href = '${pageContext.request.contextPath}/ShowDepartmentServlet';
            }
            function UpdateDepartment() {
                var title = $("#title_text").val();
                var moodle_url = $("#moodle_url_text").val();
                var token = $("#token_text").val();
                var abbreviation = $("#abbreviation_text").val();
                var old_abbreviation = '<%= department.getAbbreviation()%>';
                if ((validate(abbreviation)) && (validate(token)) && (validate(title)) && (validate(moodle_url))) {

                    var r = confirm("Sei sicuro di voler modificare il Dipartimento: " + title);
                    if (r == true) {
                        document.location.href = '${pageContext.request.contextPath}/UpdateDepartmentServlet?old_abbreviation=' + old_abbreviation + '&abbreviation=' + abbreviation + '&token=' + token + '&title=' + title + '&url_moodle=' + moodle_url;
                    }
                } else {
                    var msg;
                    if (!validate(abbreviation)) {
                        msg = "Inserisci un'abbreviazione";
                    } else if (!validate(title)) {
                        msg = "Inserisci un titolo";
                    } else if (!validate(moodle_url)) {
                        msg = "Inserisci Url di Moodle";
                    } else if (!validate(token)) {
                        msg = "Inserisci il token";
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
        <!-- JavaScripts initializations and stuff -->
        <script src="assets/js/xenon-custom.js"></script>

    </body>
</html>