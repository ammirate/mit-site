<%-- 
    Document   : ShowTeachingOfferList
    Author     : Davide
--%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <%@include file="/offertaFormativa/topMenu.jsp" %>
        <div class="page-container">
            <c:choose>
                <c:when test="${sessionScope.person.account.typeOfAccount == 'professor'}">
                    <%@include file="/offertaFormativa/lateralMenuProf.jsp" %>
                </c:when>
                <c:when test="${sessionScope.person.account.typeOfAccount == 'student'}">
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
                                    <div class="col-sm-1"></div>

                                    <div class="form-group col-sm-5">
                                        <label style="color: black; font-weight: bold">Dipartimento:</label><select name="department" class="form-control" id="department" onchange="loadCycle(this.value);">
                                            <option> Seleziona il Dipartimento </option>
                                        </select> 
                                    </div>
                                    <div class="form-group col-sm-5">
                                        <label style="color: black; font-weight: bold">Ciclo:</label><select name="cycle" class="form-control" id="cycles" onchange="loadDegree(this.value);" >
                                        </select> 
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-1"></div>

                                    <div class="form-group col-sm-5">
                                        <label style="color: black; font-weight: bold">Corso di laurea:</label><select name="degree" class="form-control" id="degrees" onchange="loadCurr(this.value)">
                                        </select> 
                                    </div>

                                    <div class="form-group col-sm-5">
                                        <label style="color: black; font-weight: bold">Curriculum:</label><select name="curriculum" class="form-control" id="curriculum" onchange="loadTeach(this.value)">
                                        </select> 
                                    </div>




                                </div>
                                <div> <br> </div>
                                <div> <br> </div>


                                <div class="row">
                                    <div class="col-sm-1"></div>

                                    <div class="col-md-10">
                                        <ul class="list-group list-group-minimal" id="offer_list">

                                        </ul>
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
        </div>
            <!-- Bottom Scripts -->
            <script type="text/javascript">
                jQuery(document).ready(function ($)
                {
                    $("#department").select2({allowClear: true}).on('select2-open', function () {
                        $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                    });
                    $("#cycles").select2({allowClear: true}).on('select2-open', function () {
                        $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                    });
                    $("#curriculum").select2({allowClear: true}).on('select2-open', function () {
                        $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                    });
                    $("#degrees").select2({allowClear: true}).on('select2-open', function () {
                        $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                    });
                });
                function loadCycle(i) {
                    $.get('GetCycleServlet?department=' + i, function (responseJson) {
                        var $select = $('#cycles');
                        $select.find('option').remove();
                        $('<option>').val("NoCycle").text("Seleziona il Ciclo").appendTo($select);
                        $.each(responseJson, function (key, value) {
                            $('<option>').val(value.cycle_number).text(value.title).appendTo($select);
                        });
                        $('#cycles').val("NoCycle");
                        $("#cycles").select2({allowClear: true}).on('select2-open', function () {
                            $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                        });
                    });
                }
                function loadDep() {
                    $.get('GetDepartmentServlet', function (responseJson) {
                        var $select = $('#department');
                        $.each(responseJson, function (key, value) {
                            $('<option>').val(value.departmentAbbreviation).text(value.title).appendTo($select);
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
                            if (value.status === "Attivo") {
                                $('<option>').val(value.matricula).text(value.title).appendTo($select);
                            }
                        });
                        $('#degrees').val('NoDeg');
                        $("#degrees").select2({allowClear: true}).on('select2-open', function () {
                            $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                        });
                    });
                }
                function loadCurr(i) {
                    $.get('GetCurriculumServlet?degree=' + i, function (responseJson) {
                        var $select = $('#curriculum');
                        $select.find('option').remove();
                        $('<option>').val("NoCur").text("Seleziona Curriculum").appendTo($select);
                        $.each(responseJson, function (key, value) {
                            if (value.status === "Attivo") {
                                $('<option>').val(value.matricula).text(value.title).appendTo($select);
                            }
                        });
                        $('#curriculum').val("NoCur");
                        $("#curriculum").select2({allowClear: true}).on('select2-open', function () {
                            $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                        });
                    });
                }
                function loadTeach(matricula) {
                    var list = document.getElementById("offer_list");
                    $.get('GetTeachingServlet?curriculum=' + matricula, function (responseJson) {
                        var stringa = "";
                        $.each(responseJson, function (key, value) {
                            if (value.active === "Attivo") {
                                stringa += "<a href='${pageContext.request.contextPath}/GetSyllabusServlet?teaching_matricula=" + value.matricula + "'><li class='list-group-item' style='cursor: pointer; color: #112B62; font-weight: bold;'>" + value.title + "</li></a>";
                            }
                        });
                        list.innerHTML = stringa;
                    });
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