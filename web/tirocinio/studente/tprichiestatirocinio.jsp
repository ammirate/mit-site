<%@page import="it.unisa.tirocinio.beans.TrainingOffer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.tirocinio.manager.concrete.ConcreteMessageForServlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts/linecons/css/linecons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap-mod.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-core.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-forms.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-components.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-skins.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jquery.fileupload.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/datatables/dataTables.bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts/meteocons/css/meteocons.css">



        <script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/tpLibrary.js"></script>
        
        <c:choose>
            <c:when test="${sessionScope.person.account.typeOfAccount == 'professor'}">
                <c:redirect url="/indexLog.jsp" />
            </c:when>
            <c:when test="${sessionScope.person.account.typeOfAccount == 'admin'}">
                <c:redirect url="/indexLog.jsp" />
            </c:when>
            <c:when test="${sessionScope.person.account.typeOfAccount == 'company'}">
                <c:redirect url="/indexLog.jsp" />
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${sessionScope.person.account.email == null}">
                <c:redirect url="/login.jsp" />
            </c:when>
        </c:choose>

        <jsp:include page="/getStudentTrainingStatus" />
        <c:set var="statusMessage" value="${sessionScope.message }"></c:set>
        <%
            ConcreteMessageForServlet _message = (ConcreteMessageForServlet) pageContext.getAttribute("statusMessage");
            int requestStatus = (Integer) _message.getMessage("status");
            String description = (String) _message.getMessage("description");
            int studentStatus = (Integer) _message.getMessage("idStudentStatus");
            pageContext.setAttribute("status", requestStatus);
            pageContext.setAttribute("description", description);
            pageContext.setAttribute("idStudentStatus", studentStatus);
        %>

        <%
            pageContext.setAttribute("path", pageContext.getServletContext().getContextPath());
        %>
        <c:if test="${status == 1}">
            <c:choose>
                <c:when test="${idStudentStatus == 3}">
                    <script>
                        jQuery(document).ready(function ($) {
                            $("#ID_modulistica_0").empty();
                            $("#ID_questionario_0").empty();
                            $("#motivationReject").empty();
                            $("#motivationReject").attr("hidden");

                            tpFunction.createPendingStudentPanel('#panelBody');
                        });
                    </script>
                </c:when>
                <c:when test="${idStudentStatus == 2}">
                    <script>
                        jQuery(document).ready(function ($) {
                            $("#motivationReject").empty();
                            $("#motivationReject").attr("hidden");
                            tpFunction.createAcceptedStudentPanel('#panelBody');
                            tpFunction.populateTable('#tableNewsTrainingOrganization', '#tableContainer', '${path}');
                        });
                    </script>
                </c:when>
                <c:when test="${idStudentStatus == 1}">
                    <script>
                        jQuery(document).ready(function ($) {
                            $("#ID_modulistica_0").empty();
                            $("#ID_questionario_0").empty();
                            tpFunction.createRejectedStudentPanel('#motivationReject', '${path}');
                        });
                    </script>
                </c:when>
                <c:otherwise>
                    <script>
                        jQuery(document).ready(function ($) {
                            $("#ID_modulistica_0").empty();
                            $("#ID_questionario_0").empty();
                            $("#motivationReject").empty();
                            $("#motivationReject").attr("hidden");
                        });
                    </script>
                </c:otherwise>
            </c:choose>
        </c:if>
        
    </head>
    <body class="page-body">

        <nav class="navbar horizontal-menu navbar-fixed-top"><!-- set fixed position by adding class "navbar-fixed-top" -->

            <div class="navbar-inner">

                <!-- Navbar Brand -->
                <div class="navbar-brand">
                    <a href="${pageContext.request.contextPath}/tirocinio/studente/tphome.jsp" class="logo">
                        <img src="${pageContext.request.contextPath}/assets/images/mitforsite.png" width="80" alt="" class="hidden-xs" />
                        <img src="${pageContext.request.contextPath}/assets/images/mitforsitemini.png" width="80" alt="" class="visible-xs" />
                    </a>
                </div>

                <!-- Mobile Toggles Links -->
                <div class="nav navbar-mobile">

                    <!-- This will toggle the mobile menu and will be visible only on mobile devices -->
                    <div class="mobile-menu-toggle">

                        <a href="#" data-toggle="user-info-menu-horizontal">
                            <i class="fa-key"></i>
                        </a>

                        <a href="#" data-toggle="mobile-menu-horizontal">
                            <i class="fa-bars"></i>
                        </a>
                    </div>
                </div>

                <div class="navbar-mobile-clear"></div>

                <!-- main menu -->

                <ul class="navbar-nav">
                    <li>
                        <a href="${pageContext.request.contextPath}/ShowTeachingOfferServlet">
                            <i class="linecons-desktop"></i>
                            <span class="title">Offerta Formativa</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/gestioneTesi/index.jsp">
                            <i class="linecons-graduation-cap"></i>
                            <span class="title">Gestione Tesi</span>
                        </a>
                    </li>
                    <li class="opened active">
                        <a href="${pageContext.request.contextPath}/tirocinio/studente/tphome.jsp">
                            <i class="linecons-megaphone"></i>
                            <span class="title">Gestione Tirocinio</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dottorato/index.jsp">
                            <i class="linecons-lightbulb"></i>
                            <span class="title">Dottorato</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="linecons-globe"></i>
                            <span class="title">Links</span>
                        </a>
                        <ul>
                            <li>
                                <a href="http://www.magistralemit.unisa.it/" target="_blank">
                                    <span class="title">DISTRA-MIT</span>
                                </a>
                            </li>
                            <li>
                                <a href="https://esse3web.unisa.it/unisa/Start.do" target="_blank">
                                    <span class="title">Esse3</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- notifications and other links -->
                <ul class="nav nav-userinfo navbar-right">
                    <li class="dropdown user-profile">
                        <a href="#" data-toggle="dropdown">
                            <img src="${pageContext.request.contextPath}/assets/images/user-1.png" alt="user-image" class="img-circle img-inline userpic-32" width="28" />
                            <span id="spaceForUsername">
                                ${sessionScope.person.account.email}
                            </span>
                        </a>

                        <ul class="dropdown-menu user-profile-menu list-unstyled">
                            <li>
                                <a href="${pageContext.request.contextPath}/tirocinio/studente/gestioneTirocinio&PlacementStudenteProfiloPersonale.html">
                                    <i class="fa-edit"></i>
                                    Profilo
                                </a>
                            </li>
                            <li class="last">
                                <a href='${path }/logout' id="logout">
                                    <i class="fa-lock"></i>
                                    Logout
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>

            </div>

        </nav>
        <div class="page-container"><!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->

            <!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
            <!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
            <!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
            <div class="sidebar-menu toggle-others">
                <div class="sidebar-menu-inner">	
                    <ul id="main-menu" class="main-menu">
                        <!-- add class "multiple-expanded" to allow multiple submenus to open -->
                        <!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
                        <li>
                            <a href="${pageContext.request.contextPath}/tirocinio/studente/tphome.jsp">
                                <i class="fa-home"></i>
                                <span class="title">Home</span>
                            </a>
                        </li>
                        <li class="opened active">
                            <a href="${pageContext.request.contextPath}/tirocinio/studente/tprichiestatirocinio.jsp">
                                <i class="fa-bullhorn"></i>
                                <span class="title">Richiesta tirocinio</span>
                            </a>
                        </li>
                        <li id="ID_modulistica_0">
                            <a href="${pageContext.request.contextPath}/tirocinio/studente/tpmodulistica.jsp">
                                <i class="linecons-note"></i>
                                <span class="title">Modulistica</span>
                            </a>
                        </li>
                        <li id="ID_questionario_0">
                            <a href="${pageContext.request.contextPath}/tirocinio/studente/tpquestionario.jsp">
                                <i class="fa-file-o"></i>
                                <span class="title">Questionario</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="main-content">

                <div class="row">

                    <div class="col-md-12">

                        <!-- Default panel -->
                        <div class="panel panel-default" id="panelDefault">
                            <div class="panel-heading">
                                Richiesta Tirocinio
                            </div>

                            <div class="panel-body" id="panelBody"> 
                                <div class="row" id="motivationReject" hidden></div>

                                <p>Questa sezione del sito ti permetterà di inoltrare una richiesta di tirocinio. Per inviare la tua richiesta, è necessario tu possieda un Curriculum Vitae e un documento sul quale vengono descritti esami effettuati, data di convalida e cfu ad essi associati.</p>
                                <p>Entrambi i files devono essere in formato PDF e inviati all'Ufficio Amministrativo tramite i moduli sottostanti. L'ufficio amministrativo, in seguito, provvederà ad esaminare la tua richiesta e ti comunicherà su questa pagina l'esito della stessa.</p>
                                <br><br>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <!--<div class="form-group">-->
                                        <form role="form" class="validate" action="${path}/studentUploadFiles" method="POST" enctype="multipart/form-data">
                                            <div class="form-group">
                                                <!--<label class="col-sm-2 control-label" for="field-4" >Curriculum Vitae</label>-->
                                                <label class="control-label">Studente</label>
                                                <!--<div class="col-sm-8">-->
                                                <input type="text" value="${sessionScope.person.account.email}" name="account" disabled>
                                                <!--</div>-->
                                                <!--<div class="col-sm-2"></div>-->
                                            </div>
                                            <br>
                                            <div class="form-group">
                                                <!--<label class="col-sm-2 control-label" for="field-4" >Curriculum Vitae</label>-->
                                                <label class="control-label">Curriculum Vitae</label>
                                                <!--<div class="col-sm-8">-->
                                                <input type="file" class="form-control" data-validate="required" data-message-required="Per favore, inserisci il file del CV." id="field-4" name="cv" accept="application/pdf">
                                                <!--</div>-->
                                                <!--<div class="col-sm-2"></div>-->
                                            </div>
                                            <br>
                                            <div class="form-group">
                                                <!--<label class="col-sm-2 control-label" for="field-5">Esami svolti</label>-->
                                                <label class="control-label">Esami svolti</label>
                                                <!--<div class="col-sm-8">-->
                                                <input type="file" class="form-control" data-validate="required" id="field-5" name="doc" data-message-required="Per favore, inserisci il file degli esami svolti." accept="application/pdf">
                                                <!--</div>-->
                                                <!--<div class="col-sm-2"></div>-->
                                            </div>
                                            <br><br><br>
                                            <div id="filesControl" align="center"></div>
                                            <div class="row">
                                                <center><button type="submit" class="btn btn-success fileinput-button"><i class="fa-arrow-up"></i>
                                                        <span>Invia Richiesta</span></button></center>
                                            </div>
                                        </form>
                                        <!--</div>-->
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
                <div class="row" hidden id="tableContainer">
                    <div class="col-md-12">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Tirocini Disponibili</h3>
                            </div>

                            <div class="panel-body">

                                <table id="tableNewsTrainingOrganization" class="table table-striped table-hover table-bordered" cellspacing="0" width="100%">
                                    <!--<thead>
                                            <tr>
                                                    <th>Nome Azienda</th>
                                                    <th>Professore Associato</th>
                                                    <th>Descrizione</th>
                                                    <th>Contatti</th>
                                            </tr>
                                    </thead>-->
                                    <tbody align='center'>

                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>
                
            </div>

        </div>

        <div class="page-loading-overlay">
            <div class="loader-2"></div>
        </div>

        <!-- Bottom Scripts -->
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/TweenMax.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/resizeable.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/joinable.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/xenon-api.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/xenon-toggles.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/datatables/js/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/datatables/dataTables.bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>



        <script src="${pageContext.request.contextPath}/assets/js/jquery-validate/jquery.validate.min.js"></script>

        <script src="${pageContext.request.contextPath}/assets/js/xenon-widgets.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jvectormap/regions/jquery-jvectormap-world-mill-en.js"></script>

        <!-- JavaScripts initializations and stuff -->
        <script src="${pageContext.request.contextPath}/assets/js/xenon-custom.js"></script>

    </body>
</html>