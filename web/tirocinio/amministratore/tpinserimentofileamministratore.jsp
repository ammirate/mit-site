<%@page import="it.unisa.integrazione.model.Department"%>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-core.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-forms.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-components.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-skins.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jquery.fileupload.css">
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

        <jsp:include page="/getAllDepartments" />
        <c:set var="allDepartments" value="${sessionScope.departments }"></c:set>
        <%
            pageContext.setAttribute("path", pageContext.getServletContext().getContextPath());
        %>
        
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
                            <img src="${pageContext.request.contextPath}/assets/images/user-1.png" alt="user-image" class="img-circle img-inline userpic-32" width="28" />
                            <span id="spaceForUsername">
                                ${sessionScope.person.account.email}
                            </span>
                        </a>

                        <ul class="dropdown-menu user-profile-menu list-unstyled">
                            <li>
                                <a href="${pageContext.request.contextPath}/tirocinio/amministratore/gestioneTirocinio&PlacementAmministratoreProfiloPersonale.html">
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
                            <a href="${pageContext.request.contextPath}/tirocinio/amministratore/tpamministratore.jsp">
                                <i class="linecons-cog"></i>
                                <span class="title">Offerta Tirocinio</span>
                            </a>
                        </li>
                        <li class="opened active">
                            <a href="#">
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

                        <!-- Default panel -->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Inserimento Modulistica
                            </div>

                            <div class="panel-body">

                                <p>Questa sezione del sito permetterà di inoltrare i moduli inerenti al tirocinio. Per inviare la tua richiesta, è necessario tu possieda i moduli "Piano Formativo", "Registro Ore" e "Questionario".</p>
                                <p>Tutti i files devono essere in formato PDF. Gli Studenti, in seguito, potranno scaricare i suddetti moduli mediante accesso alle proprie aree dedicate e compilarli.</p>
                                <br><br>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <form action="${path}/adminUploadFiles" method="POST" role="form" class="validate" id="sendModulesForm" enctype="multipart/form-data">
                                                <div class="form-group">
                                                    <label class="control-label">Seleziona il dipartimento a cui appartengono i documenti:</label>
                                                    <select class="form-control" id="departments" name="department" data-validate="required" data-message-required="Per favore, inserisci il dipartimento.">
                                                        <!--<option value="" selected>--Seleziona--</option>-->
                                                        <%
                                                            ArrayList<Department> departments = (ArrayList<Department>) pageContext.getAttribute("allDepartments");
                                                            for (Department dep : departments) {
                                                                out.println("<option value=\"" + dep.getAbbreviation() + "\">" + dep.getAbbreviation() + "</option>");
                                                            }
                                                        %>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Modulistica</label>
                                                    <input type="file" class="form-control" id="trainingPlan" name="trainingPlan" accept="application/pdf" data-validate="required" data-message-required="Per favore, inserisci il file della modulistica."/>
                                                </div>

                                                <div class="form-group">
                                                    <label class="control-label">Registro Ore</label>
                                                    <input type="file" class="form-control" id="examsCarriedOut" name="examsCarriedOut" accept="application/pdf" data-validate="required" data-message-required="Per favore, inserisci il file del Registro Ore."/>
                                                </div>

                                                <div class="form-group">
                                                    <label class="control-label">Questionario</label>
                                                    <input type="file" class="form-control" id="questionnaire" name="questionnaire" accept="application/pdf" data-validate="required" data-message-required="Per favore, inserisci il file del Questionario."/>
                                                </div>

                                                <br><br><br>
                                                <div class="row">
                                                    <center><button type="submit" class="btn btn-success fileinput-button" id="sendModules">
                                                            <i class="fa-pencil"></i>
                                                            <span>Invia Modulo</span>
                                                        </button>    
                                                    </center>
                                                </div>
                                                <c:set var="status" value="${sessionScope.message.getMessage('status')}"></c:set>
                                                <c:choose>
                                                    <c:when test="${status == 1}">
                                                        <div class="alert alert-success">
                                                            <center><strong>Successo,</strong> dati caricati correttamente.</center>
                                                        </div>
                                                        <%
                                                            request.getSession().removeAttribute("message");
                                                        %>
                                                        <c:remove var="status"/>
                                                    </c:when>
                                                    <c:when test="${status == 0}">
                                                        <div class="alert alert-danger">
                                                            <center><strong>Errore</strong> nell'elaborazione dei dati. Riprova per favore!</center>
                                                        </div>
                                                        <%
                                                            request.getSession().removeAttribute("message");
                                                        %>
                                                        <c:remove var="status"/>
                                                    </c:when>
                                                </c:choose>
                                            </form>
                                        </div>
                                    </div>
                                </div>
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
        <script src="${pageContext.request.contextPath}/assets/js/rwd-table/js/rwd-table.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery-validate/jquery.validate.min.js"></script>


        <!-- JavaScripts initializations and stuff -->
        <script src="${pageContext.request.contextPath}/assets/js/xenon-custom.js"></script>

    </body>
</html>