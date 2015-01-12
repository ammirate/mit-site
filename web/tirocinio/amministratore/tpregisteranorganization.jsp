<%@page import="it.unisa.tirocinio.beans.TrainingOffer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.tirocinio.manager.concrete.ConcreteMessageForServlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                tpAdminFunction.appendProfessor('#comboboxProfessor', "${path}");
            });
        </script>
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
                        <li>
                            <a href="${pageContext.request.contextPath}/tirocinio/amministratore/tpamministratore.jsp">
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
                        <li class="opened active">
                            <a href="#">
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
                    <div class="col-sm-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Registrazione Nuova Azienda</h3>
                            </div>
                            <div class="panel-body">
                                <form role="form" class="form-horizontal validate" action="${path}/insertNewOrganization" method="POST">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="vatNumber">P.IVA Azienda</label>
                                        <div class="col-sm-9">
                                            <input data-validate="required" data-message-required="Per favore, inserisci la Partita Iva." type="text" class="form-control" name="vatNumber" id="vatNumber" maxlength="14">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="companyName">Nome Compagnia</label>
                                        <div class="col-sm-9">
                                            <input data-validate="required" data-message-required="Per favore, inserisci il nome della compagnia." type="text" class="form-control" name="companyName" id="companyName">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="city">Citt&agrave;</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="city" name="city" data-validate="required" data-message-required="Per favore, inserisci la città.">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="address">Indirizzo</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="address" name="address" data-validate="required" data-message-required="Per favore, inserisci l\'indirizzo.">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="phone">Telefono Azienda</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="phone" name="phone" data-validate="required" data-message-required="Per favore, inserisci il numero telefonico." placeholder="fisso o mobile" data-mask="9999999999999999">
                                        </div>
                                    </div>
                                    <div class="form-group" id="mail-group">
                                        <label class="col-sm-3 control-label" for="email">Email Azienda</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="email" name="email" data-validate="required" data-message-required="Per favore, inserisci l\'email." placeholder="xxxxxx@xxxxxxx.xxx" data-mask="^([\w\-\.]+)@((\[([0-9]{1,3}\.){3}[0-9]{1,3}\])|(([\w\-]+\.)+)([a-zA-Z]{2,4}))$" data-is-regex="true">
                                        </div>
                                    </div>
                                    <div class="form-group-separator"></div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="emailAccount">Username</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="emailAccount" name="emailAccount" data-validate="required" data-message-required="Per favore, inserisci lo username dell'azienda.">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="password">Password</label>
                                        <div class="col-sm-9">
                                            <input type="password" class="form-control" id="password" name="password" data-validate="required" data-message-required="Per favore, inserisci la password dell'azienda.">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="nameLiable">Nome Responsabile</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="emailAccount" name="nameLiable" data-validate="required" data-message-required="Per favore, inserisci il nome del responsabile.">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="surnameLiable">Cognome Responsabile</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="password" name="surnameLiable" data-validate="required" data-message-required="Per favore, inserisci il cognome del responsabile.">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="ssnLiable">Codice Fiscale</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="cf" name="ssnLiable" data-validate="required" data-message-required="Per favore, inserisci il codice fiscale." data-mask="^[A-Za-z]{6}[0-9LMNPQRSTUV]{2}[A-Za-z]{1}[0-9LMNPQRSTUV]{2}[A-Za-z]{1}[0-9LMNPQRSTUV]{3}[A -Za-z]{1}$" data-is-regex="true">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Sesso</label>

                                        <div class="col-sm-9">
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="gender" checked="" value="M">
                                                    M
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="gender" value="F">
                                                    F
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group-separator"></div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="password">Professore Associato</label>
                                        <div class="col-sm-9">
                                            <select name="professorSSN" id="comboboxProfessor" class="form-control" data-validate="required" data-message-required="Per favore, inserisci il professore di riferimento.">
                                            </select>
                                        </div>
                                    </div>
                                   
                                    <div class="form-group-separator"></div>
                                    <div class="row">
                                        <center>
                                            <button id="submit" type="submit" class="btn btn-blue fileinput-button">
                                                <i class="fa-pencil"></i>
                                                <span>Registra</span>
                                                <!-- The file input field used as target for the file upload widget -->
                                            </button>    
                                        </center>
                                    </div>
                                    <c:set var="status" value="${sessionScope.status}"></c:set>
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
                                        <c:when test="${status == 1}">
                                            <div class="alert alert-success">
                                                <center><strong>Eseguito,</strong> dati caricati correttamente.</center>
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
        <script src="${pageContext.request.contextPath}/assets/js/inputmask/jquery.inputmask.bundle.js"></script>

        <!-- JavaScripts initializations and stuff -->
        <script src="${pageContext.request.contextPath}/assets/js/xenon-custom.js"></script>

    </body>
</html> 
