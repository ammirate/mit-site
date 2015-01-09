<%-- 
    Document   : tesiStudente
    Created on : 2-dic-2014, 12.07.51
    Author     : CosimoAlessandro
--%>

<!DOCTYPE html>
<html>
    <head>

        <link rel="stylesheet" href="assets/js/select2/select2.css">
        <link rel="stylesheet" href="assets/js/select2/select2-bootstrap.css">
        <link rel="stylesheet" href="assets/js/multiselect/css/multi-select.css">
        <link rel="stylesheet" href="assets/js/magicsuggest/css/magicsuggest-1.3.1-min.css">


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tesi Studente</title>

        <script>
            var codice_fiscale = '${person.ssn}';
            var stato_tesi = null;
            var id = null;

            $(document).ready(function () {

                $.ajax({
                    url: 'RecuperaDatiTesi',
                    type: 'POST',
                    data: {id_studente: codice_fiscale},
                    success: function (msg) {
                        var dati_tesi = null;

                        var titolo = null;
                        var abstract = null;
                        var data_inizio = null;
                        var data_fine = null;
                        var data_fine_prevista = null;
                        var messaggio_richiesta = null;
                        if (msg !== "") {
                            var dati_tesi = $.parseJSON(msg);
                        }

                        if (dati_tesi !== null) {
                            id = dati_tesi.id_tesi;
                            titolo = dati_tesi.titolo;
                            abstract = dati_tesi.abstract_tesi;
                            data_inizio = dati_tesi.data_inizio;
                            data_fine = dati_tesi.data_fine;
                            data_fine_prevista = dati_tesi.data_fine_prevista;
                            messaggio_richiesta = dati_tesi.messaggio_richiesta;
                            stato_tesi = dati_tesi.stato_tesi;

                        }

                        if (stato_tesi == null) {
                            popolaSelectDipartimenti();
                            $("#richiesta").show();
                            $("#attesa").hide();
                            $("#inCorso").hide();
                        } else if (stato_tesi == 0) {
                            var relatore_richiesto = dati_tesi.relatori[0].nome_docente + " " + dati_tesi.relatori[0].cognome_docente;
                            $("#professore_richiesta").html(relatore_richiesto);
                            $("#messaggio_richiesta").html(messaggio_richiesta);

                            $("#attesa").show();
                            $("#richiesta").hide();
                            $("#inCorso").hide();

                        } else if (stato_tesi == 1 || stato_tesi == 2) {
                            $("#attesa").hide();
                            $("#richiesta").hide();
                            $("#inCorso").show();
                            /*
                             // Use jQuery's each to iterate over the opts value
                             var professors = [];
                             for (i = 0; i < dati_tesi.relatori.length; i++) {
                             professors.push({value: dati_tesi.relatori[i].id_docente,
                             label: dati_tesi.relatori[i].nome_docente + " " + dati_tesi.relatori[i].cognome_docente});
                             // You will need to alter the below to get the right values from your json object.  Guessing that d.id / d.modelName are columns in your carModels data
                             // $('#multi-select').append('<option value="' + dati_tesi.relatori[i].id_docente + '">' + dati_tesi.relatori[i].nome_docente + " " + dati_tesi.relatori[i].cognome_docente + '</option>');
                             }
                             
                             $("#multi-select").multiselect('dataprovider', professors);
                             */

                            var ms = $('#argument_tag').magicSuggest({
                                data: "",
                                displayField: '',
                                useCommaKey: true,
                                width: 800
                            });


                            $(ms).on('selectionchange', function (v) {

                                $("#tag_selected").val(JSON.stringify(this.getValue(v.id)));

                            });
                        }
                        
                        if (stato_tesi == 2) {
                            $("#button_completa").prop("disabled", true);
                            $("#button_modifica").prop("disabled", true);
                            $("#titolo_panel_tesi_in_corso").html("Richiesta di completamento tesi in attesa")
                        }


                    }
                });
            });

            function popolaSelectDipartimenti()
            {

                $.ajax({
                    type: "POST",
                    url: "PopolaSelectDipartimenti",
                    success: function (data) {
                        // Parse the returned json data
                        if (data != "") {
                            var dipartimenti = $.parseJSON(data);

                            // Use jQuery's each to iterate over the opts value
                            for (i = 0; i < dipartimenti.mainOb.length; i++) {

                                // You will need to alter the below to get the right values from your json object.  Guessing that d.id / d.modelName are columns in your carModels data
                                $('#dipartimenti').append('<option value="' + dipartimenti.mainOb[i].abbreviazione + '">' + dipartimenti.mainOb[i].titolo + '</option>');
                            }
                        }

                    }
                });
            }


            function popolaSelectCorsoLaurea(dipartimento)
            {

                $("#corso_laurea").select2("val", "");
                $("#professore").select2("val", "");

                $.ajax({
                    type: "POST",
                    url: "PopolaSelectCorsoLaurea",
                    data: {abbr_dipartimento: dipartimento.value},
                    success: function (data) {
                        // Parse the returned json data
                        var corso_laurea = $.parseJSON(data);

                        $('#corso_laurea option[value!=""]').remove();
                        $('#professore option[value!=""]').remove();
                        // Use jQuery's each to iterate over the opts value
                        for (i = 0; i < corso_laurea.mainOb.length; i++) {

                            // You will need to alter the below to get the right values from your json object.  Guessing that d.id / d.modelName are columns in your carModels data
                            $('#corso_laurea').append('<option value="' + corso_laurea.mainOb[i].matricola + '">' + corso_laurea.mainOb[i].titolo + '</option>');
                        }


                    }
                });
            }

            function popolaSelectProfessori(corsoLaurea)
            {
                $("#professore").select2("val", "");

                $.ajax({
                    type: "POST",
                    url: "PopolaSelectProfessori",
                    data: {posizione: "professor", corso_laurea: corsoLaurea.value},
                    success: function (data) {
                        // Parse the returned json data

                        $('#professore option[value!=""]').remove();

                        var professori = $.parseJSON(data);

                        // Use jQuery's each to iterate over the opts value
                        for (i = 0; i < professori.mainOb.length; i++) {
                            // You will need to alter the below to get the right values from your json object.  Guessing that d.id / d.modelName are columns in your carModels data
                            $('#professore').append('<option value="' + professori.mainOb[i].codice_fiscale + '">' + professori.mainOb[i].nome + " " + professori.mainOb[i].cognome + '</option>');
                        }
                    }
                });
            }
        </script>

        <script type="text/javascript">




            jQuery(document).ready(function ($)
            {
                $("#dipartimenti").select2({
                    placeholder: 'Seleziona il tuo dipartimento...',
                    allowClear: true
                }).on('select2-open', function ()
                {
                    // Adding Custom Scrollbar
                    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                });

            });

            jQuery(document).ready(function ($)
            {
                $("#corso_laurea").select2({
                    placeholder: 'Seleziona il tuo corso di laurea...',
                    allowClear: true
                }).on('select2-open', function ()
                {
                    // Adding Custom Scrollbar
                    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                });

            });




            jQuery(document).ready(function ($)
            {
                $("#professore").select2({
                    placeholder: 'Seleziona il professore...',
                    allowClear: true
                }).on('select2-open', function ()
                {
                    // Adding Custom Scrollbar
                    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                });

            });
        </script>

    </head>
    <body>

        <!-- Form richiesta tesi -->

        <%
           // if (session.getAttribute("stato_tesi")==null) {

        %>

        <div id="richiesta" class="panel panel-color panel-danger"><!-- Add class "collapsed" to minimize the panel -->
            <div class="panel-heading">
                <h3 class="panel-title">Richiesta Tesi</h3>
            </div>


            <div class="panel-body">

                <form  role="form" action="${pageContext.request.contextPath}/richiestaTesi" method="POST" class="form-horizontal validate">

                    <div class="form-group">
                        <label class="col-sm-2 control-label text-primary" for="dipartimenti">Dipartimenti</label>

                        <div class="col-sm-10">
                            <select onchange="popolaSelectCorsoLaurea(this);"  data-validate="required" data-message-required="Fai una scelta" class="form-control" name="dipartimenti" id="dipartimenti">
                                <option > </option> 

                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label text-primary" for="corso_laurea">Corsi di laurea</label>

                        <div class="col-sm-10">
                            <select onchange="popolaSelectProfessori(this);" data-validate="required" data-message-required="Fai una scelta"  class="form-control" name="corso_laurea" id="corso_laurea">
                                <option value=""> </option> 
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label text-primary" for="professore">Professori</label>

                        <div class="col-sm-10 ">
                            <select data-validate="required" data-message-required="Fai una scelta"  class="form-control" name="professore" id="professore">
                                <option value=""> </option>   
                            </select>
                        </div>
                    </div>

                    <div class="form-group-separator"></div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label text-primary" for="field-5">Messaggio richiesta</label>

                        <div class="col-sm-10">
                            <textarea class="form-control"  data-validate="required,minlength[50]" data-message-required="Questo campo è obbligatorio" cols="5" id="messaggio" name="messaggio"></textarea>
                        </div>
                    </div>

                    <div class="form-group-separator"></div>

                    <div class="form-group">
                        <div class="col-sm-12 text-right">
                            <button type="submit" class="btn btn-danger btn-icon btn-icon-standalone btn-lg">
                                <i class="fa-graduation-cap"></i>
                                <span>Invia Richiesta</span>
                            </button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
        <!-- Riepilogo richiesta tesi in attesa-->

        <%// } else if (session.getAttribute("stato_tesi").equals("0")) {

        %>


        <div id="attesa" class="panel panel-color panel-danger"><!-- Add class "collapsed" to minimize the panel -->
            <div class="panel-heading">
                <h3 class="panel-title">Richiesta Tesi in attesa di conferma</h3>
            </div>


            <div class="panel-body">
                <h3>Riepilogo</h3>

                <b>Professore</b><p id="professore_richiesta">Professore</p>
                <br>
                <b>Messaggio</b><p id="messaggio_richiesta"></p>

            </div>
        </div>

        <!-- Tesi in corso-->

        <%        //     } else if (session.getAttribute("stato_tesi").equals("1")) {

        %>

        <div id="inCorso">

            <%@ include file="tesiInCorso.jsp" %>  


        </div>

        <%              //  }
%>

        <!--Bottom Scripts-->
        <script src="assets/js/select2/select2.min.js"></script>
        <script src="assets/js/jquery-validate/jquery.validate.min.js"></script>
        <script src="assets/js/multiselect/js/jquery.multi-select.js"></script>
        <script src="assets/js/magicsuggest/magicsuggest-1.3.1-min.js"></script>

    </body>
</html>
