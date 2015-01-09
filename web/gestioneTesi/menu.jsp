<%-- 
    Document   : menu
    Created on : 10-dic-2014, 15.31.53
    Author     : CosimoAlessandro
--%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
                <style>

            #main_list li > a:hover {
                text-decoration: underline;
            }

            #main_list li > a{
                background-color: white;

            }

            #main_list li.active > a {
                background-color: #3F61A3;

            }

        </style>

        <script>
            function reload_bacheca() {
                location.reload();
                //$('#bacheca').reload("gestioneTesi/bacheca.jsp");
            }
            ;

            function reload_cronologia() {
                $('#cronologia').load("gestioneTesi/cronologia.jsp");
            }
            ;

            function reload_storico() {
                $('#storico').load("gestioneTesi/storico.jsp");
            }
            ;

        </script>
        
    </head>
    <body style="margin-top:80px">
       
        <div class="page-container">
            <div class="main-content">
                <div class="row">
                    <div  class="col-lg-2">
                        <ul id="main_list" class="nav nav-pills nav-stacked">
                            <li class="active"><a href="#bacheca" data-toggle="pill" onclick="reload_bacheca()"><i class="fa-user"></i> Bacheca</a></li>
                            <li><a href="#cronologia" data-toggle="pill" onclick="reload_cronologia()"><i class="fa-history"></i> Cronologia</a></li>
                            <li><a href="#storico" data-toggle="pill" onclick="reload_storico()"><i class="fa-search"></i> Storico Tesi</a></li>

                        </ul>
                    </div>

                    <div id="tab-content"  class="tab-content col-lg-10">
                        <div class="tab-pane active" id="bacheca">

                            <%@ include file="bacheca.jsp" %>

                        </div>
                        <div class="tab-pane" id="cronologia">

                  

                        </div>
                        <div class="tab-pane" id="storico">

                       

                        </div>

                    </div>
                    
                </div>
            </div>
        </div>
        
    </body>
</html>
