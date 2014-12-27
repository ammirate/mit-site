<%-- 
    Document   : insertTeaching
    Author     : Alessandro
--%>
<%@page import="it.unisa.model.Degree"%>
<%@page import="it.unisa.model.Department"%>
<%@page import="it.unisa.model.Cycle"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%! public ArrayList<Cycle> cycles; %>
<%! public ArrayList<Department> departments;
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
        <script type="text/javascript">
            $(window).load(function () {
                loadDepartment();
                loadCycle();
                loadClasses(1);
                loadModules(1);
                changeSelect();
                
                $.ajax({crossDomain: true,
                    type:"GET",
                    contentType: "application/json; charset=utf-8",
                    async:false,
                    url: "http://localhost/moodle/provalogin.php",
                    data: { username: 'admin',password:'0Pq7-eyf' },
                    dataType: "jsonp",                
                    jsonpCallback: function(content){alert(content);}});

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

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
                        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
                        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
                <![endif]-->


    </head>
    <body class="page-body">
        <% 	cycles = (ArrayList<Cycle>) request.getAttribute("cycles");
            departments = (ArrayList<Department>) request.getAttribute("departments");
        %>
        <nav class="navbar horizontal-menu navbar-fixed-top">
            <!-- set fixed position by adding class "navbar-fixed-top" -->

            <div class="navbar-inner">

                <!-- Navbar Brand -->
                <div class="navbar-brand">
                    <a href="offertaFormativaStudente.html" class="logo"> <img
                            src="assets/images/mitforsite.png" width="80" alt=""
                            class="hidden-xs" /> <img src="assets/images/mitforsitemini.png"
                            width="80" alt="" class="visible-xs" />
                    </a>
                </div>

                <!-- Mobile Toggles Links -->
                <div class="nav navbar-mobile">

                    <!-- This will toggle the mobile menu and will be visible only on mobile devices -->
                    <div class="mobile-menu-toggle">

                        <a href="#" data-toggle="user-info-menu-horizontal"> <i
                                class="fa-key"></i>
                        </a> <a href="#" data-toggle="mobile-menu-horizontal"> <i
                                class="fa-bars"></i>
                        </a>
                    </div>
                </div>

                <div class="navbar-mobile-clear"></div>

                <!-- main menu -->

                <ul class="navbar-nav">
                    <li class="opened active"><a
                            href="offertaFormativaStudente.html"> <i
                                class="linecons-desktop"></i> <span class="title">Offerta
                                Formativa</span>
                        </a></li>
                    <li><a href="#"> <i class="linecons-graduation-cap"></i> <span
                                class="title">Gestione Tesi</span>
                        </a></li>
                    <li><a href="#l"> <i class="linecons-megaphone"></i> <span
                                class="title">Gestione Tirocinio</span>
                        </a></li>
                    <li><a href="#"> <i class="linecons-lightbulb"></i> <span
                                class="title">Dottorato</span>
                        </a></li>
                    <li><a href="#"> <i class="linecons-globe"></i> <span
                                class="title">Links</span>
                        </a>
                        <ul>
                            <li><a href="http://www.magistralemit.unisa.it/"
                                   target="_blank"> <span class="title">DISTRA-MIT</span>
                                </a></li>
                            <li><a href="https://esse3web.unisa.it/unisa/Start.do"
                                   target="_blank"> <span class="title">Esse3</span>
                                </a></li>
                        </ul></li>
                </ul>
                <!-- notifications and other links -->
                <ul class="nav nav-userinfo navbar-right">
                    <li class="dropdown user-profile"><a href="#"
                                                         data-toggle="dropdown"> <img src="assets/images/user-1.png"
                                                     alt="user-image" class="img-circle img-inline userpic-32"
                                                     width="28" /> <span id="spaceForUsername"> </span>
                        </a>

                        <ul class="dropdown-menu user-profile-menu list-unstyled">
                            <li><a href="#"> <i class="fa-edit"></i> Profilo
                                </a></li>
                            <li class="last"><a href="#" id="logout"> <i
                                        class="fa-lock"></i> Logout
                                </a></li>
                        </ul></li>
                </ul>

            </div>

        </nav>
        <div class="page-container">
            <!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->

            <!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
            <!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
            <!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
            <div class="sidebar-menu toggle-others">
                <div class="sidebar-menu-inner">
                    <ul id="main-menu" class="main-menu">
                        <!-- add class "multiple-expanded" to allow multiple submenus to open -->
                        <!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
                        <li class="opened active"><a href="#"> <i
                                    class="linecons-cog"></i> <span class="title">Home</span>
                            </a></li>
                        <li id="funzionalita1Permission_0"><a href="#"> <i
                                    class="linecons-cog"></i> <span class="title">Funzionalit&agrave;
                                    01</span>
                            </a></li>
                        <li><a href="#"> <i class="linecons-note"></i> <span
                                    class="title">Funzionalit&agrave; 02</span>
                            </a></li>
                        <li id="funzionalita3Permission_0"><a href="#"> <i
                                    class="linecons-mail"></i> <span class="title">Funzionalit&agrave;
                                    03</span>
                            </a>
                            <ul>
                                <li><a href="#"> <span class="title">INSERT</span>
                                    </a></li>
                                <li><a href="#"> <span class="title">DELETE</span>
                                    </a></li>
                                <li><a href="#"> <span class="title">VIEW</span>
                                    </a></li>
                            </ul></li>
                    </ul>
                </div>
            </div>

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
                            //$("#error").fadeOut(3500);
                        }
                        if(<% out.print(request.getAttribute("success"));%>){
                            $("#success").show();
                            //$("#error").fadeOut(3500);
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
                        <div class="panel-heading">Inserimento Insegnamento</div>
                        <form action="InsertTeachingServlet" method="post" role="form" id="form" class="form-horizontal">
                            <div class="row">
                                <div class="col-sm-1"></div>
                                <div class="form-group col-sm-4">
                                    <label for="department">Dipartimento:</label> 
                                    <select name="department" id="department" class="form-control" onchange="loadDegree();">

                                    </select>
                                </div>
                                <div class="col-sm-2"></div>
                                <div class="form-group col-sm-4">
                                    <label for="cycle">Ciclo:</label> 
                                    <select name="cycle" class="form-control" id="cycle"  onchange="loadDegree();">

                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-1"></div>
                                <div class="form-group col-sm-4">
                                    <label for="degree">Corso di Laurea:</label> 
                                    <select name="degree" class="form-control" id="degree" onchange="loadCurriculum(this.value);">

                                    </select>
                                </div>
                                <div class="col-sm-2"></div>

                                <div class="form-group col-sm-4">
                                    <label for="curriculum">Curriculum:</label> 
                                    <select name="curriculum" class="form-control" id="curriculum">

                                    </select>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-sm-1"></div>
                                <div class="form-group col-sm-4">
                                    <label for="matricula">Matricola:</label>
                                    <input  minlength="10" maxlength="10" type="text" class="form-control" name="matricula" placeholder="Matricola"  required />
                                </div>
                                <div class="col-sm-2"></div>
                                <div class="form-group col-sm-4">
                                    <label for="title">Nome:</label>
                                    <input minlength="5" type="text" class="form-control" name="title" placeholder="Nome dell'insegnamento" required />
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-1"></div>
                                <div class="form-group col-sm-4">
                                    <label for="abbreviazione">Abbreviazione:</label>
                                    <input minlength="2" type="text" class="form-control" name="abbreviation" placeholder="Abbreviazione" required />
                                </div>
                                <div class="col-sm-2"></div>
                                <div class="form-group col-sm-2">
                                    <label for="year">Anno:</label> 
                                    <select name="year" class="form-control">
                                        <option value=1>1</option>
                                        <option value=2>2</option>
                                        <option value=3>3</option>
                                        <option value=4>4</option>
                                        <option value=5>5</option>
                                    </select>
                                </div>

                                <div class="col-sm-1"></div>
                                <div class="form-group col-sm-2">
                                    <label for="semester">Semestre:</label> 
                                    <select name="semester" class="form-control">
                                        <option value=1>1</option>
                                        <option value=2>2</option>
                                    </select>
                                </div>

                            </div>

                            <div class="row">
                                <div class="col-sm-1"></div>
                                <div class="form-group col-sm-10">
                                    <label for="link">Link al sillabus:</label>
                                    <input  type="url" name="link"   class="form-control" placeholder="link" required />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-1"></div>
                                <div class="form-group col-sm-4">
                                    <label for="classNumber">Classi:</label> 
                                    <select id="classNumber" name="classNumber" class="form-control" onchange="loadClasses(this.value);">
                                        <option value=1>Default</option>
                                        <option value=2>2</option>
                                    </select>
                                </div>
                                <div class="form-group col-sm-2"></div>
                                <div class="form-group col-sm-4">
                                    <label for="moduleNumber">Moduli:</label> 
                                    <select id="moduleNumber" name="moduleNumber" class="form-control" onchange="loadModules(this.value);" >
                                        <option value=1>Default</option>
                                        <option value=2>2</option>
                                        <option value=2>3</option>
                                        <option value=2>4</option>
                                    </select>
                                </div>
                            </div>
                            <div id="modules">
                            </div>   
                            <div id="classes">
                            </div> 
                            <div id="lastDiv">

                            </div>
                            <div class="row">
                                <div class="form-group col-sm-1">
                                    <input type="submit" id="submit" class="btn btn-default" onclick="$('#form').validate();">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-sm-1"></div>
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

        </div>
        <!-- Bottom Scripts -->
        <script src="assets/js/FunzioniOffertaFormativa.js" type="text/javascript"></script>
        <script type="text/javascript">
                                        function loadModules(i) {
                                            var stringa = "<div class='form-group-separator'></div><div class='lead'>Inserisci Moduli</div>";
                                            for (j = 1; j <= i; j++) {
                                                stringa += "<div class='row'><div class='col-sm-1'></div><div class='form-group col-sm-4'><input type='text' name='moduleName" + j + "' id='moduleName" + j + "' placeholder='Inserisci il modulo " + j + "' class='form-control' onkeyup='loadAssociation();' onchange='loadAssociation();'></div></div>";
                                            }
                                            $("#modules").html(stringa);
                                            loadAssociation();
                                        }
                                        function loadClasses(i) {
                                            var stringa = "<div class='form-group-separator'></div><div class='lead'>Inserisci Classi</div>";
                                            for (j = 1; j <= i; j++) {
                                                stringa += "<div class='row'><div class='col-sm-1'></div><div class='form-group col-sm-4'><input type='text' name='className" + j + "' id='className" + j + "' placeholder='Inserisci la classe " + j + "' class='form-control' onkeyup='loadAssociation();' onchange='loadAssociation();'></div></div>";
                                            }
                                            $("#classes").html(stringa);
                                            loadAssociation();
                                        }
                                        function loadAssociation() {
                                            var count = 0;
                                            var stringa = "<div class='form-group-separator'></div>";
                                            var moduleNum = $("#moduleNumber option:selected").val();
                                            var classNum = $("#classNumber option:selected").val();
                                            for (j = 1; j <= classNum; j++) {
                                                stringa += "<div class='lead'>Associa Docenti a Classe " + j + " - " + $("#className" + j).val() + "</div>";
                                                for (i = 1; i <= moduleNum; i++) {
                                                    stringa += "<div class='row'>";
                                                    stringa += "<div class='col-sm-1'></div><div class='form-group col-sm-2'><label for='module'>" + $("#moduleName" + i).val() + "</label><select class='form-control' name='docente" + j + "-" + i + "' id='docente" + j + "-" + i + "'></select></div>";
                                                    stringa += "</div>";
                                                    if( !($("#moduleName" + i).val()=='') && !($("#className" + j).val()=='')){
                                                        count++;
                                                    }
                                                }
                                            }
                                            if(count==classNum*moduleNum){
                                                $("#lastDiv").html(stringa);
                                            }
                                            $.ajax({url: "GetProfessorServlet?department=" + $("#department").val(), success: function (result) {
                                                var stringa = "";
                                                $.each(result, function (key, value) {
                                                    stringa+="<option value="+value.account.email+">"+value.name +" "+value.surname+"</option>";
                                                });
                                                for (j = 1; j <= classNum; j++) {
                                                    for (i = 1; i <= moduleNum; i++) {
                                                        $("#docente"+j+"-"+i).html(stringa);
                                                    }
                                                }
                                            }});
                                        }
                                        function loadProfessor() {
                                            
                                            
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
        <script src="assets/js/jquery-validate/jquery.validate.min.js" id="script-resource-7"></script>
        <script src="assets/js/jquery-validate/localization/messages_it.min.js" type="text/javascript"></script>
        <!-- JavaScripts initializations and stuff -->
        <script src="assets/js/xenon-custom.js"></script>

    </body>
</html>