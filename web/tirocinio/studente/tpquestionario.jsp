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
        <jsp:include page="/getStudentQuestionnaireCompletion">
            <jsp:param name="account" value="${sessionScope.person.account.email}" />
        </jsp:include>
        <c:set var="questionnaireCompletion" value="${sessionScope.questionnaireCompletion }"></c:set>
        <%
            int qCompletion = (Integer) pageContext.getAttribute("questionnaireCompletion");
            pageContext.setAttribute("qCompletion", qCompletion);
            pageContext.setAttribute("path", pageContext.getServletContext().getContextPath());
        %>

        <c:choose>
            <c:when test="${qCompletion == 1}">
                <script>
                    jQuery(document).ready(function ($) {
                        $("#questPanel").empty();
                        tpFunction.createQuestionnaireAlreadySendPanel('#questPanel');
                    });
                </script>
            </c:when>
        </c:choose>

        <script>
            jQuery(document).ready(function ($) {
                $("#logout").click(function (e) {
                    $.get("${path}/logout", {}).fail(function (e) {
                        alert("Si sono verificati dei problemi col server!");
                    });
                });
            });
        </script>
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
                            <a href="${pageContext.request.contextPath}/tirocinio/studente/tphome.jsp">
                                <i class="fa-home"></i>
                                <span class="title">Home</span>
                            </a>
                        </li>
                        <li>
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
                        <li id="ID_questionario_0"  class="opened active">
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
                    <div class="col-sm-12">
                        <div class="panel panel-default" id="questPanel">
                            <div class="panel-heading">
                                <h3 class="panel-title">Questionario valutativo</h3>
                            </div>
                            <div class="panel-body" id='panel-body'>
                                <p align="justify">Esprimi il tuo grado di giudizio di accordo sulle affermazioni che seguono. Seleziona le caselle utilizzando la scala progressova da 1 a 5; per effettuare tale giudizio, i criteri da utilizzare sono: </p> 
                                <br>
                                <center>
                                    <p>
                                        <label class="checkbox-inline">
                                            <input type="checkbox" checked disabled>
                                            1
                                        </label>
                                        <label class="checkbox-inline">
                                            <input type="checkbox" checked disabled>
                                            2
                                        </label>
                                        <label class="checkbox-inline">
                                            <input type="checkbox" checked disabled>
                                            3
                                        </label>
                                        <label class="checkbox-inline">
                                            <input type="checkbox" checked disabled>
                                            4
                                        </label>
                                        <label class="checkbox-inline">
                                            <input type="checkbox" checked disabled>
                                            5
                                        </label>
                                    </p>
                                </center>
                                <br>
                                <p align="justify">dove i valori da attribuire ad ogni casella sono: 1: insufficiente; 2: mediocre; 3: sufficiente; 4:buono; 5:ottimo.</p>
                                <br>
                                <form role="form" class="validate" action="${path}/insertTrainingQuestionnaire" method="POST">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="matricolaStudente">Matricola: </label>
                                        <div class="col-sm-9">
                                            <input required type="text" class="form-control" name="matricolaStudente" id="matricolaStudente" value='${sessionScope.person.matricula }' disabled>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="nomeAzienda">Azienda Ospitante: </label>
                                        <div class="col-sm-9">
                                            <input required type="text" class="form-control" name="nomeAzienda" id="nomeAzienda">
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="tipologiaAzienda">Tipologia Struttura: </label>
                                        <div class="col-sm-9">
                                            <select name="tipologiaAzienda" id="pippo" class="form-control" required>
                                                <option value>--Select--</option>
                                                <option value="Azienda_pubblica">   Azienda Pubblica</option>
                                                <option value="Azieda_privata">     Azienda Privata</option>
                                                <option value="distra">    Tirocinio Interno</option>
                                                <option value="Impresa_NO_PROFIT">  Impresa no Profit</option>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="col-sm-11">
                                        <table class="table responsive" >
                                            <thead>
                                                <tr>
                                                    <th>Valutazione</th><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th>
                                                </tr>
                                            </thead>
                                            <tbody >
                                                <tr>
                                                    <td align="justify">Grado di soddisfazione per il ruolo di mansione svolta</td>
                                                    <td><input type="radio" name="firstAnswer" value="1"></td>
                                                    <td><input type="radio" name="firstAnswer" value="2"></td>
                                                    <td><input type="radio" name="firstAnswer" value="3"></td>
                                                    <td><input type="radio" name="firstAnswer" value="4"></td>
                                                    <td><input type="radio" name="firstAnswer" value="5"></td>
                                                </tr>
                                                <tr>
                                                    <td align="justify">Adeguatezza delle competenze acquisite nel persorso di studi rispetto alle attività di tirocinio svolte</td>
                                                    <td><input type="radio" name="secondAnswer" value="1"></td>
                                                    <td><input type="radio" name="secondAnswer" value="2"></td>
                                                    <td><input type="radio" name="secondAnswer" value="3"></td>
                                                    <td><input type="radio" name="secondAnswer" value="4"></td>
                                                    <td><input type="radio" name="secondAnswer" value="5"></td>
                                                </tr>
                                                <tr>
                                                    <td align="justify">Valutazione del tutor universitario, durante le attività di tirocinio</td>
                                                    <td><input type="radio" name="thirdAnswer" value="1"></td>
                                                    <td><input type="radio" name="thirdAnswer" value="2"></td>
                                                    <td><input type="radio" name="thirdAnswer" value="3"></td>
                                                    <td><input type="radio" name="thirdAnswer" value="4"></td>
                                                    <td><input type="radio" name="thirdAnswer" value="5"></td>
                                                </tr>
                                                <tr>
                                                    <td align="justify">Valutazione del tutor aziendale, durante le attività di tirocinio</td>
                                                    <td><input type="radio" name="fourthAnswer" value="1"></td>
                                                    <td><input type="radio" name="fourthAnswer" value="2"></td>
                                                    <td><input type="radio" name="fourthAnswer" value="3"></td>
                                                    <td><input type="radio" name="fourthAnswer" value="4"></td>
                                                    <td><input type="radio" name="fourthAnswer" value="5"></td>
                                                </tr>
                                                <tr>
                                                    <td align="justify">Valutazione del supporto ricevuto nelle informazioni e attività di tirocinio preliminari e successive allo svolgimento del tirocinio</td>
                                                    <td><input type="radio" name="fifthAnswer" value="1"></td>
                                                    <td><input type="radio" name="fifthAnswer" value="2"></td>
                                                    <td><input type="radio" name="fifthAnswer" value="3"></td>
                                                    <td><input type="radio" name="fifthAnswer" value="4"></td>
                                                    <td><input type="radio" name="fifthAnswer" value="5"></td>
                                                </tr>
                                                <tr>
                                                    <td align="justify">Valutazione acquisizione di competenze nello svolgimento delle attività di tirocinio</td>
                                                    <td><input type="radio" name="sixthAnswer" value="1"></td>
                                                    <td><input type="radio" name="sixthAnswer" value="2"></td>
                                                    <td><input type="radio" name="sixthAnswer" value="3"></td>
                                                    <td><input type="radio" name="sixthAnswer" value="4"></td>
                                                    <td><input type="radio" name="sixthAnswer" value="5"></td>
                                                </tr>
                                                <tr>
                                                    <td align="justify">Valutazione complessiva dell'esperienza di tirocinio</td>
                                                    <td><input type="radio" name="seventhAnswer" value="1"></td>
                                                    <td><input type="radio" name="seventhAnswer" value="2"></td>
                                                    <td><input type="radio" name="seventhAnswer" value="3"></td>
                                                    <td><input type="radio" name="seventhAnswer" value="4"></td>
                                                    <td><input type="radio" name="seventhAnswer" value="5"></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <script>
                                        jQuery(document).ready(function ($) {
                                            function controlRadio() {
                                                var firstAnswer = $("input[name='firstAnswer']:checked").val();
                                                var secondAnswer = $("input[name='secondAnswer']:checked").val();
                                                var thirdAnswer = $("input[name='thirdAnswer']:checked").val();
                                                var fourthAnswer = $("input[name='fourthAnswer']:checked").val();
                                                var fifthAnswer = $("input[name='fifthAnswer']:checked").val();
                                                var sixthAnswer = $("input[name='sixthAnswer']:checked").val();
                                                var seventhAnswer = $("input[name='seventhAnswer']:checked").val();
                                                if ((firstAnswer != null) && (secondAnswer != null) && (thirdAnswer != null) && (fourthAnswer != null) && (fifthAnswer != null) && (sixthAnswer != null) && (seventhAnswer != null)) {
                                                    $("#submit").removeAttr("disabled");
                                                }
                                            }

                                            $("input[type=radio]").change(function () {
                                                controlRadio();
                                            });
                                        });
                                    </script>

                                    <div class="row">
                                        <div class="col-sm-12">
                                            <center>
                                                <button id="submit" type="submit" class="btn btn-blue fileinput-button" disabled>
                                                    <i class="fa-pencil"></i>
                                                    <span>Invia questionario</span>
                                                </button>    
                                            </center>
                                        </div>
                                    </div>
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

        <!-- JavaScripts initializations and stuff -->
        <script src="${pageContext.request.contextPath}/assets/js/xenon-custom.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery-validate/jquery.validate.min.js"></script>

    </body>
</html> 
