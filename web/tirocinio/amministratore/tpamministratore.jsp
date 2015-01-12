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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap-mod.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-core.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-forms.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-components.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-skins.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">

        <script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/tpAdministratorLibrary.js"></script>
        
        <c:choose>
            <c:when test="${sessionScope.person.account.typeOfAccount == 'Bstudent'}">
                <c:redirect url="/indexLog.jsp" />
            </c:when>
            <c:when test="${sessionScope.person.account.typeOfAccount == 'Mstudent'}">
                <c:redirect url="/indexLog.jsp" />
            </c:when>
            <c:when test="${sessionScope.person.account.typeOfAccount == 'phd'}">
                <c:redirect url="/indexLog.jsp" />
            </c:when>
            <c:when test="${sessionScope.person.account.typeOfAccount == 'phdadmin'}">
                <c:redirect url="/indexLog.jsp" />
            </c:when>
            <c:when test="${sessionScope.person.account.typeOfAccount == 'professor'}">
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

        <%
            pageContext.setAttribute("path", pageContext.getServletContext().getContextPath());
        %>
        
        <script>
            jQuery(document).ready(function ($) {
                tpAdminFunction.appendStudentInformation("#tableNotifications",'${path }');
            });
        </script>
        
        <c:set var="trainingComplete" value="${sessionScope.message.getMessage('trainingComplete')}"></c:set>
        <c:choose>
            <c:when test="${trainingComplete == 0}">
                <script>
                    alert("Si sono verificati dei problemi nel completamento della richiesta.");
                </script>
                <%
                    request.getSession().removeAttribute("trainingComplete");
                %>
                <c:remove var="trainingComplete"/>
            </c:when>
        </c:choose>
    </head>
    <body class="page-body">

        <nav class="navbar horizontal-menu navbar-fixed-top"><!-- set fixed position by adding class "navbar-fixed-top" -->

            <div class="navbar-inner">

                <!-- Navbar Brand -->
                <div class="navbar-brand">
                    <a href="${pageContext.request.contextPath}/tirocinio/amministratore/tpamministratore.jsp" class="logo">
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
                        <a href="${pageContext.request.contextPath}/tirocinio/amministratore/tpamministratore.jsp">
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
                            <img src="../../assets/images/user-1.png" alt="user-image" class="img-circle img-inline userpic-32" width="28" />
                            <span id="spaceForUsername">
                                ${sessionScope.person.account.email}
                            </span>
                        </a>

                        <ul class="dropdown-menu user-profile-menu list-unstyled">
                            <li>
                                <a href="../../tirocinio/amministratore/gestioneTirocinio&PlacementAmministratoreProfiloPersonale.html">
                                    <i class="fa-edit"></i>
                                    Profilo
                                </a>
                            </li>
                            <li class="last">
                                <a href="${path }/logout" id="logout">
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
                        <li  class="opened active">
                            <a href="#">
                                <i class="linecons-cog"></i>
                                <span class="title">Offerta Tirocinio</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/tirocinio/amministratore/tpinserimentofileamministratore.jsp">
                                <i class="linecons-cog"></i>
                                <span class="title">Inserimento Moduli</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/tirocinio/amministratore/tpassociazioneprofessoreazienda.jsp">
                                <i class="linecons-cog"></i>
                                <span class="title">Associa Professore</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/tirocinio/amministratore/tpregisteranorganization.jsp">
                                <i class="linecons-cog"></i>
                                <span class="title">Registra Azienda</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/tirocinio/amministratore/tpaggiungistudentetraining.jsp">
                                <i class="linecons-cog"></i>
                                <span class="title">Aggiungi Tirocinio</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/tirocinio/amministratore/tpvisionetirocini.jsp">
                                <i class="linecons-cog"></i>
                                <span class="title">Visione Tirocini</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/tirocinio/amministratore/tpamministratoretirocinanti.jsp">
                                <i class="linecons-cog"></i>
                                <span class="title">Tirocinanti</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="main-content">
                <div class="row">
                    <div class="col-md-12">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Notifiche</h3>
                            </div>

                            <div class="panel-body">
                                <table id="tableNotifications" class="table table-striped table-hover table-bordered" cellspacing="0" width="100%">

                                    <thead>
                                        <tr>
                                            <th>Matricola</th>
                                            <th>Credenziali</th>
                                            <th>CV</th>
                                            <th>Esami</th>
                                            <th>Azioni</th>
                                        </tr>
                                    </thead>

                                    <tbody align="center">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <c:set var="status" value="${sessionScope.message.getMessage('status')}"></c:set>
                        <c:choose>
                            <c:when test="${status == 0}">
                                <div class="alert alert-danger">
                                    <center><strong>Errore,</strong> dati caricati non correttamente. Per favore, riprova.</center>
                                </div>
                                <%
                                    request.getSession().removeAttribute("message");
                                %>
                                <c:remove var="status"/>
                            </c:when>
                        </c:choose>
                    </div>
                </div>

                
            </div>

        </div>

        <div class="modal fade" id="modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <center><h4 class="modal-title">Rifiutare la domanda di tirocinio.</h4></center>
                    </div>

                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6">

                                <div class="form-group">
                                    <label for="serialNumberModalForReject" class="control-label">Matricola</label>

                                    <input type="text" id="serialNumberModalForReject" class="form-control" disabled>
                                </div>	

                            </div>

                            <div class="col-md-6">

                                <div class="form-group">
                                    <label for="nameANDsurnameModalForReject" class="control-label">Email dello studente</label>

                                    <input type="text" id="nameANDsurnameModalForReject" class="form-control" disabled>
                                </div>	

                            </div>
                        </div>
                        <form role="form" class="validate" action="${path}/addErrorToStudentInformation" method="POST">
                            <input type="hidden" name="errors" id="errors"/>
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="xe-widget xe-todo-list">
                                        <div class="xe-header">
                                            <center><h4 class="title"><strong>Errori</strong> riscontrati.</h4></center>
                                        </div>
                                        <div class="xe-body">
                                            <ul class="list-unstyled">
                                                <li>
                                                    <label>
                                                        <input name="curriculumDescription" value="Curriculum Vitae mancante: il file inviato al personale amministrativo presenta degli errori." type="checkbox" id="cvDocumentError" class="cbr"/>
                                                        Curriculum Vitae mancante: il file inviato dallo studente presenta degli errori.
                                                    </label>
                                                </li>
                                                <li>
                                                    <label>
                                                        <input name="accademicTrainscriptDescription" value="Libretto Universitario: il file inviato al personale amministrativo presenta delle incongruenze con i dati del sistema." type="checkbox" id="esDocumentError" class="cbr"/>
                                                        Libretto Universitario: il file inviato dallo studente presenta delle incongruenze con i dati del sistema.
                                                    </label>
                                                </li>
                                                <li>
                                                    <label>
                                                        <input name="cfuDescription" value="Non possiedi un numero di CFU sufficienti per poter proseguire la pratica." type="checkbox" id="cfuNotFoundError" class="cbr"/>
                                                        CFU mancanti: Lo studente non possiede un numero di CFU sufficienti per poter proseguire la pratica.
                                                    </label>
                                                </li>
                                                <li>
                                                    <label>
                                                        <input name="otherDescription" value="Sono stati riscontrati degli errori nell'elaborazione della tua pratica. Contatta l'ufficio amministrativo." type="checkbox" id="otherError" class="cbr" checked />
                                                        Altro: il motivo del rifiuto della richiesta non è elencato. Lo studente provvederà a contattare l'ufficio amministrativo.
                                                    </label>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </div>

                    <div class="modal-footer">
                        <center>
                            <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
                            <button type="submit" id="invio" class="btn btn-info">Invio Errori</button>
                        </center>
                    </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="page-loading-overlay">
            <div class="loader-2"></div>
        </div>


        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/datatables/dataTables.bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts/meteocons/css/meteocons.css">

        <!-- Bottom Scripts -->
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/TweenMax.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/resizeable.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/joinable.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/xenon-api.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/xenon-toggles.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/datatables/js/jquery.dataTables.js"></script>

        <!-- Imported scripts on this page -->
        <script src="${pageContext.request.contextPath}/assets/js/datatables/dataTables.bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>

        <!-- Imported scripts on this page -->
        <script src="${pageContext.request.contextPath}/assets/js/xenon-widgets.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jvectormap/regions/jquery-jvectormap-world-mill-en.js"></script>

        <script src="${pageContext.request.contextPath}/assets/js/jquery-validate/jquery.validate.min.js"></script>

        <!-- JavaScripts initializations and stuff -->
        <script src="${pageContext.request.contextPath}/assets/js/xenon-custom.js"></script>

    </body>
</html>