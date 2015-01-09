<%-- 
    Document   : listaTesisti
    Created on : 2-dic-2014, 15.42.08
    Author     : Damiano
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Tesisti</title>

        <!-- Imported styles on this page -->
        <link rel="stylesheet" href="assets/js/datatables/dataTables.bootstrap.css">

        <script>
            var codice_fiscale = '${person.ssn}';
            $(document).ready(function () {
                $.ajax({
                    url: 'RecuperaTesisti',
                    type: 'POST',
                    data: {id_docente: codice_fiscale},
                    success: function (msg) {

                        var jarray = $.parseJSON(msg);
                        var jarray_size = jarray.mainOb[0].size;
                        var i = 0;
                        var count = 0;
                        var elenco_richieste = 1;
                        //costruzione delle richieste e della lista
                        for (i = 0; i < jarray_size; i++) {

                            var id_tesi = jarray.mainOb[i].id_tesi;
                            var a = jarray.mainOb[i].stato_tesi;
                            var b = jarray.mainOb[i].id_studente;
                            var c = jarray.mainOb[i].descrizione;
                            var d = jarray.mainOb[i].data_inizio;
                            var e = jarray.mainOb[i].data_fine;
                            var f = jarray.mainOb[i].data_fine_prevista;
                            var g = jarray.mainOb[i].titolo;
                            var h = jarray.mainOb[i].abstract;
                            //mostra le richieste delle tesi nello stato '0' '2'
                            if (a == 0 || a == 2) {

                                var row_richieste = document.createElement("tr");
                                //row.setAttribute("class", "row");
                                var corpo_richieste = "<td>" + elenco_richieste + "</td>" +
                                        "<td id=\"richiesta_tipo" + i + "\"></td>" +
                                        "<td id=\"richiesta_nome" + i + "\"></td>" +
                                        "<td id=\"richiesta_messaggio" + i + "\"></td>" +
                                        "<td><button class=\"btn btn-icon btn-success\" name=\"accetta\" value=\"" + id_tesi + "\"><i class=\"fa-thumbs-o-up\"></i></button></td>" +
                                        "<td><button class=\"btn btn-icon btn-red\" name=\"rifiuta\" value=\"" + id_tesi + "\"><i class=\"fa-remove\"></i></button></td>";



                                row_richieste.innerHTML = corpo_richieste;
                                $("#body_table_richieste").append(row_richieste);
                                if (a == 0) {
                                    $("#richiesta_tipo" + i).html("Richiesta Tesi");
                                    count++;
                                }
                                if (a == 2) {
                                    $("#richiesta_tipo" + i).html("Completamento Tesi");
                                    count++;
                                }


                                $("#richiesta_nome" + i).html(b);
                                $("#richiesta_messaggio" + i).html(c);
                                elenco_richieste++;
                            }
                            

                            //mostra l'elenco dei tesisti con tesi allo stato '1'  
                            if (a == 1) {
                                var row_lista_tesi = document.createElement("tr");
                                var corpo_lista_tesi = "<td id=\"lista_stato_tesi" + i + "\"></td>" +
                                        "<td id=\"lista_nome" + i + "\"></td>" +
                                        "<td id=\"lista_titolo" + i + "\"></td>" +
                                        "<td id=\"lista_argomento" + i + "\"></td>" +
                                        "<td id=\"lista_data_inizio" + i + "\"></td>" +
                                        "<td id=\"lista_data_fine" + i + "\"></td>";
                                row_lista_tesi.innerHTML = corpo_lista_tesi;
                                $("#body_table_lista_tesi").append(row_lista_tesi);
                                $("#lista_stato_tesi" + i).html("Tesi in corso");
                                $("#lista_nome" + i).html(b);
                                $("#lista_titolo" + i).html(g);
                                $("#lista_argomento" + i).html("");
                                $("#lista_data_inizio" + i).html(d);
                                $("#lista_data_fine" + i).html(e);
                            }
                            
                            
                            //mostra l'elenco dei tesisti con tesi allo stato '2' 
                            if (a == 2) {
                                var row_lista_tesi = document.createElement("tr");
                                var corpo_lista_tesi = "<td id=\"lista_stato_tesi" + i + "\"></td>" +
                                        "<td id=\"lista_nome" + i + "\"></td>" +
                                        "<td id=\"lista_titolo" + i + "\"></td>" +
                                        "<td id=\"lista_argomento" + i + "\"></td>" +
                                        "<td id=\"lista_data_inizio" + i + "\"></td>" +
                                        "<td id=\"lista_data_fine" + i + "\"></td>";
                                row_lista_tesi.innerHTML = corpo_lista_tesi;
                                $("#body_table_lista_tesi").append(row_lista_tesi);
                                $("#lista_stato_tesi" + i).html("Tesi in attesa");
                                $("#lista_nome" + i).html(b);
                                $("#lista_titolo" + i).html(g);
                                $("#lista_argomento" + i).html("");
                                $("#lista_data_inizio" + i).html(d);
                                $("#lista_data_fine" + i).html(e);
                            }
                            

                            //mostra l'elenco dei tesisti con tesi allo stato '3'
                            if (a == 3) {
                                var row_lista_tesi = document.createElement("tr");
                                var corpo_lista_tesi = "<td id=\"lista_stato_tesi" + i + "\"></td>" +
                                        "<td id=\"lista_nome" + i + "\"></td>" +
                                        "<td id=\"lista_titolo" + i + "\"></td>" +
                                        "<td id=\"lista_argomento" + i + "\"></td>" +
                                        "<td id=\"lista_data_inizio" + i + "\"></td>" +
                                        "<td id=\"lista_data_fine" + i + "\"></td>";
                                row_lista_tesi.innerHTML = corpo_lista_tesi;
                                $("#body_table_lista_tesi").append(row_lista_tesi);
                                $("#lista_stato_tesi" + i).html("Tesi completata");
                                $("#lista_nome" + i).html(b);
                                $("#lista_titolo" + i).html(g);
                                $("#lista_argomento" + i).html("");
                                $("#lista_data_inizio" + i).html(d);
                                $("#lista_data_fine" + i).html(e);
                            }


                        }

                        //questo if deve far scomparire il panel richieste se non ci sono tesi allo stato '0' e '2'
                        if (count == 0) {
                            document.getElementById("panel_richieste").style.display = "none";
                        }


                        //Funzione che preleva i dati dalla tabella
                        jQuery(document).ready(function ($)
                        {
                            $("#example-3").dataTable().yadcf([
                                {column_number: 0},
                                {column_number: 1, filter_type: 'text'},
                                {column_number: 2, filter_type: 'text'},
                                {column_number: 3, filter_type: 'text'},
                                {column_number: 4},
                                {column_number: 5},
                            ]);
                        });
                    }
                });
            });
        </script>
        
    </head>
    <body>

        <!-- Colored panel, remember to add "panel-color" before applying the color -->
        <div class="panel panel-color panel-danger" id="panel_richieste"><!-- Add class "collapsed" to minimize the panel -->
            <div class="panel-heading">
                <h3 class="panel-title">Elenco Richieste</h3>

                <div class="panel-options">
                    <a href="#" data-toggle="panel">
                        <span class="collapse-icon">&ndash;</span>
                        <span class="expand-icon">+</span>
                    </a>                  
                </div>
            </div>

            <div class="panel-body" id="body_richieste">  
                <form action="${pageContext.request.contextPath}/accettaTesi" method="post">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th class="col-2">Tipo Richiesta</th>
                                <th class="col-2">Nome Studente</th>
                                <th class="col-sm-6">Messaggio</th>
                                <th class="col-1">Accetta</th>
                                <th class="col-1">Rifiuta</th>
                            </tr>
                        </thead>
                        <tbody id="body_table_richieste">

                        </tbody>
                    </table>
                </form>

            </div>

        </div>

        <!-- Colored panel, remember to add "panel-color" before applying the color -->
        <div class="panel panel-color panel-danger"><!-- Add class "collapsed" to minimize the panel -->
            <div class="panel-heading">
                <h3 class="panel-title">Lista Tesisti</h3>

                <div class="panel-options">
                    <a href="#" data-toggle="panel">
                        <span class="collapse-icon">&ndash;</span>
                        <span class="expand-icon">+</span>
                    </a>                  
                </div>
            </div>

            <div class="panel-body">               


                <table class="table table-striped table-bordered" id="example-3">
                    <thead>
                        <tr class="replace-inputs">
                            <th>Stato Tesi</th>
                            <th>Nome Studente</th>
                            <th>Titolo Tesi</th>
                            <th>Argomento</th>
                            <th>Data Inizio</th>
                            <th>Data Fine</th>
                        </tr>
                    </thead>
                    <tbody id="body_table_lista_tesi">


                    </tbody>
                </table>


            </div>
        </div>

        <!-- Bottom Scripts -->
        <script src="assets/js/datatables/js/jquery.dataTables.min.js"></script>

        <!-- Imported scripts on this page -->
        <script src="assets/js/datatables/dataTables.bootstrap.js"></script>
        <script src="assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
        <script src="assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>


        <!-- JavaScripts initializations and stuff -->
        <script src="assets/js/xenon-custom.js"></script>


    </body>
</html>
