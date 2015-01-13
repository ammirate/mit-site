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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">

        <script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/tpProfessorLibrary.js"></script>
        
        <c:choose>
            <c:when test="${sessionScope.person.account.typeOfAccount == 'Bstudent'}">
                <c:redirect url="/ShowTeachingOfferServlet" />
            </c:when>
            <c:when test="${sessionScope.person.account.typeOfAccount == 'Mstudent'}">
                <c:redirect url="/ShowTeachingOfferServlet" />
            </c:when>
            <c:when test="${sessionScope.person.account.typeOfAccount == 'phd'}">
                <c:redirect url="/ShowTeachingOfferServlet" />
            </c:when>
            <c:when test="${sessionScope.person.account.typeOfAccount == 'phdadmin'}">
                <c:redirect url="/ShowTeachingOfferServlet" />
            </c:when>
            <c:when test="${sessionScope.person.account.typeOfAccount == 'admin'}">
                <c:redirect url="/ShowTeachingOfferServlet" />
            </c:when>
            <c:when test="${sessionScope.person.account.typeOfAccount == 'company'}">
                <c:redirect url="/ShowTeachingOfferServlet" />
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
        <c:set var="deleteTrainingOfferStatus" value="${sessionScope.message.getMessage('deleteTrainingStatus')}"></c:set>
        <c:choose>
            <c:when test="${deleteTrainingOfferStatus == 0}">
                <script>
                    alert("Si sono verificati dei problemi nel completamento della richiesta.");
                </script>
                <%
                    request.getSession().removeAttribute("deleteTrainingStatus");
                %>
                <c:remove var="deleteTrainingOfferStatus"/>
            </c:when>
            <c:when test="${deleteTrainingOfferStatus == 1}">
                <%
                    request.getSession().removeAttribute("deleteTrainingStatus");
                %>
                <c:remove var="deleteTrainingOfferStatus"/>
            </c:when>
        </c:choose>

        <script>
            jQuery(document).ready(function ($) {
                tpProfessorFunction.appendTraining('#listTrainingProfessore', '${sessionScope.person.account.email}', "${path}");
                
            });
        </script>

    </head>
    <body class="page-body" onLoad="">

        <nav class="navbar horizontal-menu navbar-fixed-top"><!-- set fixed position by adding class "navbar-fixed-top" -->

            <div class="navbar-inner">

                <!-- Navbar Brand -->
                <div class="navbar-brand">
                    <a href="${pageContext.request.contextPath}/tirocinio/professore/tpprofessore.jsp" class="logo">
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
                        <a href="${pageContext.request.contextPath}/tirocinio/professore/tpprofessore.jsp">
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
                                <a href="../../tirocinio/professore/gestioneTirocinio&PlacementProfessoreProfiloPersonale.html">
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
                        <li id="offertaTirocini"  class="opened active">
                            <a href="#">
                                <i class="fa-certificate"></i>
                                <span class="title">Offerta Tirocinio</span>
                            </a>
                        </li>
                        <li id="offertaTirocini">
                            <a href="${pageContext.request.contextPath}/tirocinio/professore/tpprofessoretirocinanti.jsp">
                                <i class="fa-group"></i>
                                <span class="title">Tirocinanti</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="main-content">

                <script>
                    jQuery(document).ready(function ($)
                    {
                        $('a[href="#layout-variants"]').on('click', function (ev)
                        {
                            ev.preventDefault();

                            var win = {top: $(window).scrollTop(), toTop: $("#layout-variants").offset().top - 15};

                            TweenLite.to(win, .3, {top: win.toTop, roundProps: ["top"], ease: Sine.easeInOut, onUpdate: function ()
                                {
                                    $(window).scrollTop(win.top);
                                }
                            });
                        });
                    });
                </script>

                <div class="row">
                    <div class="col-sm-12">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Aggiungi Offerta di Tirocinio</h3>
                            </div>
                            <div class="panel-body">
                                <p align="justify">
                                    Questa sezione è adibita all'inserimento di un'offerta di tirocinio. Completa i campi sottostanti per rendere l'offerta visualizzabile agli studenti; al termine di questa operazione, potrai visualizzare tutti i tirocini inseriti nella tabella di riepilogo nel pannello sottostante. Puoi ovviamente inserire più tirocini per gli studenti interessati utilizzando sempre questo modulo.
                                </p>
                                <br>

                                <form role="form" class="validate" action="${path}/professorInsertTrainingOffer" method="POST">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="field-2">Descrizione del Tirocinio</label>
                                        <div class="col-sm-10">
                                            <textarea name="description" id="descriptionTrainingProfessore" data-validate="required" data-message-required="Per favore, inserisci la descrizione." class="form-control" rows="5" cols="5" placeholder="Descrizione..." maxlength="250"></textarea>
                                            <br>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <center>
                                            <button type="submit" class="btn btn-secondary btn-single" id="addTrainingProfessore">
                                                <i class="fa-plus"></i>
                                                <span>Aggiungi Tirocinio</span>
                                            </button>
                                        </center>
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
                                </form>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Lista dei tirocini aggiunti
                            </div>
                            <div class="panel-body">
                                <div class="col-sm-12">
                                    <div id="listTrainingProfessore" class="list-group">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                
            </div>

        </div>
        <div class="modal fade" id="modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <center><h4 class="modal-title">Modifica il Tirocinio.</h4></center>
                    </div>
                    <form role="form" class="validate" action="${path}/professorUpdateTrainingOffer" method="POST">
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label for="descriptionTrainingForModify" class="col-sm-3 control-label">Descrizione del Tirocinio</label>
                                        <div class="col-sm-9">
                                            <input type="hidden" name="idModify" id="idForModifyTrainingProfessor">
                                            <textarea name="description" id="descriptionTrainingForModify" class="form-control" placeholder="" rows="5" cols="5" maxlength="250" data-validate="required" data-message-required="Per favore, inserisci la descrizione."></textarea>
                                        </div>
                                    </div>	
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <center>
                                <button type="button" class="btn btn-white" data-dismiss="modal">Chiudi</button>
                                <button type="submit" id="modifyDataForTrainingIntoDatabase" class="btn btn-orange">Modifica</button>
                            </center>
                        </div>
                    </form>
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

        <script src="${pageContext.request.contextPath}/assets/js/jquery-validate/jquery.validate.min.js"></script>
        <!-- JavaScripts initializations and stuff -->
        <script src="${pageContext.request.contextPath}/assets/js/xenon-custom.js"></script>
    </body>
</html>