<%-- 
    Document   : InsertCurriculum
    Author     : Davide
--%>
<<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="it.unisa.offerta_formativa.beans.Curriculum"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%! public Curriculum curriculum = null;
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
    <body class="page-body" onload="loadDep()">

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
                                        Inserisci Nuovo Curriculum
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
                                                    curriculum = (Curriculum) request.getAttribute("curriculum");
                                            %>
                                            <p class="bg-danger">Matricola del Curriculum già esistente inserirne un'altra</p>
                                            <% }%>
                                            <div class="row"> <br> </div>
                                            <div class="form-group col-sm-3">
                                                <label for="title" style="color: black; font-weight: bold">Matricola:</label>
                                                       <input type="text" maxlength="45" id="matricula_text" class="form-control" <% if (curriculum != null) { %> value="<% out.print(curriculum.getMatricula());
                                                           } %>" name="matricula" placeholder="Inserisci" >
                                            </div>

                                            <div class="col-sm-5">
                                                <label for="title" style="color: black; font-weight: bold" >Titolo:</label>
                                                       <input type="text" maxlength="100" id="title_text" name="titolo" class="form-control" <% if (curriculum != null) { %> value="<% out.print(curriculum.getTitle());
                                                           }%>"   placeholder="Inserisci" > 
                                            </div>

                                            <div class="form col-sm-1"></div>

                                            <label for="title" style="color: black; font-weight: bold">Stato:</label>
                                            <form role="form col-sm-5">
                                                <div class="form col-sm-1"></div>
                                                <label class="radio-inline">
                                                    <input type="radio" name="optradio" id="status_active" checked><p style="color: black">Attivo</p>
                                                </label>
                                                <label class="radio-inline">

                                                    <input type="radio" name="optradio" id="status_disable" ><p style="color: black">Disattivo</p>

                                                </label>
                                            </form>

                                        </div>
                                        <div> <br> </div>  


                                        <div class="row">
                                            <div class="form-group col-sm-6">
                                                <label style="color: black; font-weight: bold">Dipartimento:</label><select name="department" class="form-control" id="department" onchange="loadCycle(this.value);">
                                                </select> 
                                            </div>
                                            <div class="form-group col-sm-5">
                                                <label style="color: black; font-weight: bold">Ciclo:</label><select name="cycle" class="form-control" id="cycles" onchange="loadDegree(this.value);" >
                                                </select> 
                                            </div>
                                            <div class="form-group col-sm-6">
                                                <label style="color: black; font-weight: bold">Corso di laurea:</label><select name="degree" class="form-control" id="degrees" >
                                                </select> 
                                            </div>
                                            <div> <br> </div>

                                        </div>

                                        <div> <br> </div>



                                        <div> <br> </div>
                                        <div> <br> </div>
                                        <div> <br> </div>

                                        <div class="row">

                                            <div class="form-group col-sm-10">
                                                <button type="button" style=" height: 32px; width: 90px; color: black" onclick="RevertModify()">Annulla</button>
                                            </div>

                                            <div class="form-group col-sm-1">
                                                <button type="button" style=" height: 32px; width: 90px; color: black" onclick="InsertCurriculum()" id="button_confirm" >Conferma</button>
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
                </c:when>
            </c:choose>
        </div>

        <!-- Bottom Scripts -->
        <script type="text/javascript">
            jQuery(document).ready(function ($)
            {
                $("#department").select2({allowClear: true}).on('select2-open', function () {
                    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                });
                $("#degrees").select2({allowClear: true}).on('select2-open', function () {
                    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                });
                $("#cycles").select2({allowClear: true}).on('select2-open', function () {
                    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                });
            });
            function loadCycle(i) {
                $('#cycles').val('NoCycle');
                $("#cycles").select2({allowClear: true}).on('select2-open', function () {
                    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                });
                $('#degrees').val('NoDeg');
                $("#degrees").select2({allowClear: true}).on('select2-open', function () {
                    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                });


                $.get('GetCycleServlet?department=' + i, function (responseJson) {
                    var $select = $('#cycles');
                    $select.find('option').remove();
                    $('<option>').val("NoCycle").text("Seleziona il Ciclo").appendTo($select);
                    $.each(responseJson, function (key, value) {
                        $('<option>').val(value.cycle_number).text(value.title).appendTo($select);
                    });
                    $('#cycles').val('NoCycle');
                    $("#cycles").select2({allowClear: true}).on('select2-open', function () {
                        $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                    });
                });
            }
            function loadDep() {
                $.get('GetDepartmentServlet', function (responseJson) {
                    var $select = $('#department');
                    $('<option>').val("noDep").text("Seleziona un Dipartimento").appendTo($select);
                    $.each(responseJson, function (key, value) {
                        $('<option>').val(value.departmentAbbreviation).text(value.title).appendTo($select);
                    });
                    $('#department').val('noDep');
                    $("#department").select2({allowClear: true}).on('select2-open', function () {
                        $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                    });
                });
            }
            function loadDegree(i) {
                var departmentAbb = $("#department option:selected").val();
                $.get('GetDegreeServlet?cycle=' + i + "&department=" + departmentAbb, function (responseJson) {
                    var $select = $('#degrees');
                    $select.find('option').remove();
                    $('<option>').val("NoDeg").text("Seleziona Corso di laurea").appendTo($select);
                    $.each(responseJson, function (key, value) {
                        $('<option>').val(value.matricula).text(value.title).appendTo($select);
                    });
                    $('#degrees').val('NoDeg');
                    $("#degrees").select2({allowClear: true}).on('select2-open', function () {
                        $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                    });
                });
            }
            function RevertModify() {
                document.location.href = '${pageContext.request.contextPath}/ShowDegreeServlet';
            }
            function InsertCurriculum() {
                var degree_matricula = $("#degrees option:selected").val();
                var curriculum_matricula = $("#matricula_text").val();
                var title = $("#title_text").val();
                var cycle = $("#cycles option:selected").val();
                var departmentAbb = $("#department option:selected").val();
                var status = "true";

                if ((validate(curriculum_matricula)) && (validate(title)) && (degree_matricula != "NoDeg") && (cycle != "NoCycle") && (departmentAbb != "noDep")) {

                    if (document.getElementById('status_active').checked) {
                        status = "true";
                    } else if (document.getElementById('status_disable').checked) {
                        status = "false";
                    }

                    var r = confirm("Sei sicuro di voler modificare il corso di Laurea: " + title);
                    if (r == true) {
                        document.location.href = '${pageContext.request.contextPath}/InsertCurriculumServlet?curriculum_matricula=' + curriculum_matricula + '&degree_matricula=' + degree_matricula + '&title=' + title + '&status=' + status;
                    }
                } else {
                    var msg;
                    if (!validate(curriculum_matricula)) {
                        msg = "Inserisci una matricola";
                    } else if (!validate(title)) {
                        msg = "Inserisci un titolo";
                    } else if (departmentAbb === "noDep") {
                        msg = "Seleziona un Dipartimento";
                    } else if (cycle === "NoCycle") {
                        msg = "Seleziona un Ciclo di Studi";
                    } else if (degree_matricula === "NoDeg") {
                        msg = "Seleziona un Corso di laurea";
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
        <!-- JavaScripts initializations and stuff -->
        <script src="assets/js/xenon-custom.js"></script>

    </body>
</html>