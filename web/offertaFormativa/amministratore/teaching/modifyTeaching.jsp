<%-- 
    Document   : insertTeaching
    Author     : Alessandro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="it.unisa.offerta_formativa.beans.Teaching"%>
<%@page import="it.unisa.offerta_formativa.beans.Curriculum"%>
<%@page import="it.unisa.integrazione.model.Degree"%>
<%@page import="it.unisa.integrazione.model.Department"%>
<%@page import="it.unisa.integrazione.model.Cycle"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%! public ArrayList<Cycle> cycles; %>
<%! public ArrayList<Department> departments;
    public Department dept;
    public Degree degree;
    public Curriculum curr;
    public Teaching teaching;
    public Cycle cycle;
%>
<% 	cycles = (ArrayList<Cycle>)request.getAttribute("cycles");
                departments = (ArrayList<Department>)request.getAttribute("departments");
                teaching = (Teaching)request.getAttribute("teaching");
                curr = (Curriculum) request.getAttribute("curriculum");
                degree = (Degree) request.getAttribute("degree");
                dept = (Department) request.getAttribute("department");
                cycle = (Cycle)request.getAttribute("cycle");
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

        <script type="text/javascript">
            $(window).load(function () {
                $("#active").val("<% out.print((teaching.isActive())?"1":"0"); %>");
                $("#year").val("<% out.print(teaching.getYear()); %>");
                //changeSelect();
            });
        </script>

        <style>
            html .select2-drop .select2-results li.select2-highlighted{ //per la selection di select2
                border-color: #cc3f44;
                background-color:#3875d7;
            }
            form .error{ //per validation
                border-color: #cc3f44;
                color:#cc3f44;
            }

            form input.error{
                border-color: #cc3f44;
            }
        </style>
    </head>
    <body class="page-body">
        
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
                        
                        jQuery(document).ready(function ($) {
                        if(<% out.print(request.getAttribute("error"));%>){
                            $("#error").show();
                            $("#error").fadeOut(3500);
                        }
                        });
                    
                </script>
                <div class="row">
                        <p class="bg-danger" id="error" style="display:none;"><% out.print(request.getAttribute("errorMessage"));%></p>
                </div>
                <div class="row">
                        <p class="bg-success" id="success" style="display:none;"><% out.print(request.getAttribute("successMessage"));%></p>
                </div>
                <div class="row">
                    <div class="col-sm-1"></div>
                    <div class="panel panel-default col-sm-10">
                        <div class="panel-heading" style="text-align: center; ">Modifica Insegnamento</div>
                        <form action="ModifyTeachingServlet" method="post" role="form" id="form" class="form-horizontal">
                            <div class="row"><br></div>
                            <div class="row">
                                <div class='col-sm-1'></div>
                                <div class="form-group col-sm-4">
                                    <label for="department" style="color: black; font-weight: bold">Dipartimento:</label> 
                                    <select name="department" id="department" class="form-control" onchange="loadDegree();" onclick="loadDepartment();" readonly>
                                        <option selected="selected" value="<% out.print(dept.getAbbreviation()); %>"><% out.print(dept.getTitle()); %></option>
                                    </select>
                                </div>
                                <div class="col-sm-2"></div>
                                <div class="form-group col-sm-4">
                                    <label for="cycle" style="color: black; font-weight: bold">Ciclo:</label> 
                                    <select name="cycle" id="cycle" class="form-control" onchange="loadDegree();" onclick="loadCycle();" readonly >
                                        <option selected="selected" value="<% out.print(cycle.getCycleNumber()); %>"><% out.print(cycle.getTitle()); %></option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class='col-sm-1'></div>
                                <div class="form-group col-sm-4">
                                    <label for="degree" style="color: black; font-weight: bold">Corso di Laurea:</label> 
                                    <select name="degree" class="form-control" id="degree" onchange="loadCurriculum(this.value);" onclick="loadDegree();" readonly >
                                        <option selected="selected" value="<% out.print(degree.getMatricula()); %>"><% out.print(degree.getTitle()); %></option>
                                    </select>
                                </div>
                                <div class="col-sm-2"></div>

                                <div class="form-group col-sm-4">
                                    <label for="curriculum" style="color: black; font-weight: bold">Curriculum:</label> 
                                    <select name="curriculum" class="form-control" id="curriculum" onclick="loadCurriculum();" readonly >
                                        <option selected="selected" value="<% out.print(curr.getMatricula()); %>"><% out.print(curr.getTitle()); %></option>
                                    </select>
                                    <input hidden="true" type="text" value="<%out.print(curr.getMatricula());%>" name="oldCurriculumMatricula">
                                </div>

                            </div>
                            <div class="row">
                                <div class='col-sm-1'></div>
                                <div class="form-group col-sm-4">
                                    <label for="matricula" style="color: black; font-weight: bold">Vecchia Matricola:</label>
                                    <input type="text" class="form-control" name="oldMatricula" id="oldMatricula" placeholder="Matricola" value="<%out.println(teaching.getMatricula());%>" readonly>
                                </div>
                                <div class="col-sm-2"></div>
                                <div class="form-group col-sm-4">
                                    <label for="matricula" style="color: black; font-weight: bold">Nuova Matricola:</label>
                                    <input type="text" minlength="10" maxlength="10" class="form-control" name="newMatricula" id="newMatricula" placeholder="Matricola" value="<%out.println(teaching.getMatricula());%>" required >
                                </div>
                                <div class="col-sm-1"></div>

                            </div>

                            <div class="row">
                                <div class='col-sm-1'></div>
                                <div class="form-group col-sm-4">
                                    <label for="title" style="color: black; font-weight: bold">Nome:</label>
                                    <input type="text" minlength="5" class="form-control" name="title" id="title" placeholder="Nome dell'insegnamento" value="<% out.println(teaching.getTitle()); %>">
                                </div>

                                <div class="col-sm-2"></div>
                                <div class="form-group col-sm-4">
                                    <label for="abbreviazione" style="color: black; font-weight: bold">Abbreviazione:</label>
                                    <input type="text" minlength="2" class="form-control" name="abbreviation" id="abbreviation" placeholder="Abbreviazione" value="<%out.println(teaching.getAbbreviation());%>">
                                </div>
                                <div class="col-sm-1"></div>
                            </div>
                            <div class="row">
                                <div class='col-sm-1'></div>
                                <div class="form-group col-sm-2">
                                    <label for="year" style="color: black; font-weight: bold">Anno:</label> 
                                    
                                        <select name="year" id="year" class="form-control">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                        </select>
                                    
                                </div>

                                <div class="col-sm-1"></div>
                                <div class="form-group col-sm-2">
                                    <label for="semester" style="color: black; font-weight: bold">Semestre:</label> 
                                    <select name="semester" id="semester" class="form-control">
                                        <%out.println("<option value="+teaching.getSemester()+">"+teaching.getSemester()+"</option>");%>
                                    </select>
                                </div>
                                <div class="col-sm-2"></div>
                                <div class="form-group col-sm-2">
                                    <label for="active" style="color: black; font-weight: bold">Attivo:</label> 
                                    <select name="active" id="active" class="form-control">
                                        <option value="1">SI</option>
                                        <option value="0">NO</option>
                                        
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class='col-sm-1'></div>
                                <div class="form-group col-sm-10">
                                    <label for="link" style="color: black; font-weight: bold">Link al sillabus:</label>
                                    <input type="text" name="link" id="link" class="form-control" placeholder="link" value="<%out.println(teaching.getLink());%>" >
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-1">
                                    <input type="submit" id="submit" class="btn btn-default" onclick="$('#form').validate();">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- Main Footer -->
                <!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
                <!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
                <!-- Or class "fixed" to  always fix the footer to the end of page -->
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
            </div>
            </c:when>
            </c:choose>                      
        </div>


        <div class="page-loading-overlay">
            <div class="loader-2"></div>
        </div>
        <!-- Bottom Scripts -->
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/TweenMax.min.js"></script>
        <script src="assets/js/resizeable.js"></script>
        <script src="assets/js/joinable.js"></script>
        <script src="assets/js/xenon-api.js"></script>
        <script src="assets/js/xenon-toggles.js"></script>
        <link rel="stylesheet" href="assets/js/select2/select2.css">
        <link href="assets/js/select2/select2-bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="assets/js/jquery-validate/jquery.validate.min.js" id="script-resource-7"></script>
        <script src="assets/js/select2/select2.min.js"></script>
        <script src="assets/js/FunzioniOffertaFormativa.js" type="text/javascript"></script>
        <!-- JavaScripts initializations and stuff -->
        <script src="assets/js/xenon-custom.js"></script>

    </body>
</html>