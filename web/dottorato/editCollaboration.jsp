<%-- 
    Document   : editCollaboration
    Created on : 26-dic-2014, 17.24.17
    Author     : gemmacatolino
--%>

<%@page import="it.unisa.dottorato.phdProfile.collaborations.CollaborationManager"%>
<%@page import="it.unisa.dottorato.bean.Collaboration"%>
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

        <title>DISTRA-MIT Dottorato</title>

        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
        <link rel="stylesheet" href="../assets/css/fonts/linecons/css/linecons.css">
        <link rel="stylesheet" href="../assets/css/fonts/fontawesome/css/font-awesome.css">
        <link rel="stylesheet" href="../assets/css/bootstrap.css">
        <link rel="stylesheet" href="../assets/css/xenon-core.css">
        <link rel="stylesheet" href="../assets/css/xenon-forms.css">
        <link rel="stylesheet" href="../assets/css/xenon-components.css">
        <link rel="stylesheet" href="../assets/css/xenon-skins.css">
        <link rel="stylesheet" href="../assets/css/custom.css">
        <link rel="stylesheet" href="style/dottorato.css">

        <script src="../assets/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="script/index.js"></script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
                <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
                <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
    <body class="page-body">

        <!-- Inclusione della pagina contenente il menù superiore -->
        <jsp:include page="topMenu.jsp" flush="true"/>
        <div class="page-container">

            <!-- Inclusione della pagina contenente il menù laterale -->
            <jsp:include page="lateralMenu.jsp"/>

            <!-- Contenuto della pagina -->
            <%
                int collaborationID = (Integer.parseInt("" + session.getAttribute("idCollaboration")));
                Collaboration collaboration = CollaborationManager.getInstance().getCollaborationById(collaborationID);%>

            <div class="main-content" id="content">

                <div class="row">

                    <div class="col-sm-1"></div>

                    <div class="col-sm-10">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h1>Modifica Collaborazione</h1>
                            </div>
                            <div class="panel-body">
                                <form class="form-horizontal" method="POST" action="UpdateCollaboration">
                                    <div class="form-group">
                                        <table width="90%" align="center">
                                            <tr><td>
                                                    <p>Istituzione:</p>
                                                    <div class="input-group">
                                                        <span class="input-group-addon"></span>
                                                        <input class="form-control" name="istitution" type="text" value="<%= collaboration.getIstitution()%>">
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <p>Descrizione:</p>
                                                    <div class="input-group">
                                                        <span class="input-group-addon"></span>
                                                        <textarea class="form-control" name="description" rows="5" cols="40"><%= collaboration.getDescription()%></textarea>
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <p>Data Di Inizio:</p>
                                                    <div class="input-group">
                                                        <span class="input-group-addon"></span>
                                                        <input class="form-control" name="startDate" type="text" value="<%= collaboration.getStartDate()%>">
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <p>Data Di Fine:</p>
                                                    <div class="input-group">
                                                        <span class="input-group-addon"></span>
                                                        <input class="form-control" name="endDate" type="text" value="<%= collaboration.getEndDate()%>" >
                                                    </div>
                                                    <br>
                                                    <br>

                                                    <div>
                                                        <input type="submit" class="btn btn-blue" value="Modifica"> 
                                                        <br>
                                                        <br>

                                                    </div>
                                                </td></tr>
                                        </table>

                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-1"></div>

                </div>
            </div>

    </body>
</html>

