<%-- 
    Document   : InsertDegree
    Author     : Davide
--%>
<<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="it.unisa.model.Degree"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%! public Degree degree = null;
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
                                        Inserisci Corso di Laurea 
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
                                                    degree = (Degree) request.getAttribute("degree");
                                            %>
                                            <p class="bg-danger">Matricola del Corso di laurea già esistente inserirne un'altra</p>
                                            <% }%>
                                            <div class="row"> <br> </div>
                                            <form id="form1" role="form">
                                                <div class="form-group col-sm-3">
                                                    <label for="matricula" style="color: black; font-weight: bold">Matricola:</label>
                                                    <input type="text" id="matricula_text" maxlength="5" class="form-control" <% if (degree != null) { %> value="<% out.print(degree.getMatricula());
                                                        } %>" name="matricula" placeholder="Inserisci matricola">
                                                </div>

                                                <div class="col-sm-5">
                                                    <label for="title" style="color: black; font-weight: bold" >Titolo:</label>
                                                    <input type="text" id="title_text" maxlength="50" name="titolo" <% if (degree != null) { %> value="<% out.print(degree.getTitle());
                                                        } %>" class="form-control" placeholder="Inserisci titolo"> 
                                                </div>
                                            </form>
                                            <div class="form col-sm-1"></div>

                                            <label for="title" style="color: black; font-weight: bold">Stato:</label>
                                            <form role="form col-sm-5">
                                                <div class="form col-sm-1"></div>
                                                <label class="radio-inline">
                                                    <input type="radio" name="optradio" id="status_active" checked><p style="color: black">Attivo</p>
                                                </label>
                                                <label class="radio-inline">

                                                    <input type="radio" name="optradio" id="status_disable" ><p style="color: black" >Disattivo</p>

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
                                                <input type="text" maxlength="500" id="link_text" class="form-control" name="link" <% if (degree != null) { %> value="<% out.print(degree.getLink());
                                                    }%>" placeholder="Inserisci link">
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
                                                <button type="button" style=" height: 32px; width: 90px; color: black" onclick="InsertDegree()" id="button_confirm" >Conferma</button>
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


                    </div>
                </c:when>
            </c:choose>
        </div>
        <!-- Bottom Scripts -->
        <script type="text/javascript">
            function loadDepAndCycles() {
                $.get('GetCycleServlet', function (responseJson) {
                    var $select = $('#cycles');
                    $select.find('option').remove();
                    $('<option>').val("nocyc").text("Seleziona Ciclo").appendTo($select);
                    $.each(responseJson, function (key, value) {
                        $('<option>').val(value.cycle_number).text(value.title).appendTo($select);
                    });
            <% if (request.getAttribute("exist") != null) {%>
                    $('#cycles').val('<%= degree.getCycle()%>');
            <% } %>
                    $("#cycles").select2({allowClear: true}).on('select2-open', function () {
                        $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                    });

                });
                $.get('GetDepartmentServlet', function (responseJson) {
                    var $select = $('#department');
                    $select.find('option').remove();
                    $('<option>').val("nodep").text("Seleziona Dipartimento").appendTo($select);
                    $.each(responseJson, function (key, value) {
                        $('<option>').val(value.departmentAbbreviation).text(value.title).appendTo($select);
                    });
            <% if (request.getAttribute("exist") != null) {%>
                    $('#department').val('<%= degree.getDepartmentAbbreviation()%>');
            <% }%>
                    $("#department").select2({allowClear: true}).on('select2-open', function () {
                        $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                    });
                });

            }

            function RevertModify() {
                document.location.href = '${pageContext.request.contextPath}/ShowDegreeServlet';
            }
            function InsertDegree() {
                var cycle = $("#cycles option:selected").val();
                var departmentAbb = $("#department option:selected").val();
                var degree_matricula = $("#matricula_text").val();
                var link = $("#link_text").val();
                var title = $("#title_text").val();
                var status = "true";

                if ((validate(degree_matricula)) && (validate(link)) && (validate(title)) && (cycle != "nocyc") && (departmentAbb != "nodep")) {
                    if (document.getElementById('status_active').checked) {
                        status = "true";
                    } else if (document.getElementById('status_disable').checked) {
                        status = "false";
                    }

                    var r = confirm("Sei sicuro di voler Inserire il corso di Laurea: " + title);
                    if (r == true) {
                        document.location.href = '${pageContext.request.contextPath}/InsertDegreeServlet?degree_matricula=' + degree_matricula + '&cycle=' + cycle + '&departmentAbb=' + departmentAbb + '&link=' + link + '&title=' + title + '&status=' + status;
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