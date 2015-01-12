<%@page import="it.unisa.tirocinio.manager.concrete.ConcreteMessageForServlet"%>
<%@page import="it.unisa.tirocinio.beans.TrainingOffer"%>
<%@page import="java.util.ArrayList"%>
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
        <script src="${pageContext.request.contextPath}/assets/js/tpLibrary.js"></script>

        <c:choose>
            <c:when test="${sessionScope.person.account.email == null}">
                <c:redirect url="/login.jsp" />
            </c:when>
        </c:choose>

        <%
            pageContext.setAttribute("path", pageContext.getServletContext().getContextPath());
        %>
        <c:if test="${sessionScope.person.account.email != null}">
            <c:choose>
                <c:when test="${sessionScope.person.account.typeOfAccount == 'professor' }">
                    <c:redirect url="/tirocinio/professore/tpprofessore.jsp" />
                </c:when>
                <c:when test="${sessionScope.person.account.typeOfAccount == 'company' }">
                    <c:redirect url="/tirocinio/organizzazione/tporganizzazione.jsp" />
                </c:when>
                <c:when test="${sessionScope.person.account.typeOfAccount == 'Bstudent' }">
                    <c:redirect url="/tirocinio/studente/tphome.jsp" />
                </c:when>
                <c:when test="${sessionScope.person.account.typeOfAccount == 'Mstudent' }">
                    <c:redirect url="/tirocinio/studente/tphome.jsp" />
                </c:when>
                <c:when test="${sessionScope.person.account.typeOfAccount == 'phd' }">
                    <c:redirect url="/tirocinio/studente/tphome.jsp" />
                </c:when>
                <c:when test="${sessionScope.person.account.typeOfAccount == 'admin' }">
                    <c:redirect url="/tirocinio/amministratore/tpamministratore.jsp" />
                </c:when>
                <c:otherwise>
                    <c:redirect url="/pageError.html" />
                </c:otherwise>
            </c:choose>
        </c:if>
    </head>
    <body class="page-body">

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

    </body>
</html>