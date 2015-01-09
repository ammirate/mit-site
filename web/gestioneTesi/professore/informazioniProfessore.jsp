<%-- 
    Document   : informazioniProfessore
    Created on : 2-dic-2014, 15.16.26
    Author     : Damiano
--%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informazioni Professore</title>

        <script>
            var codice_fiscale = '${person.ssn}';
            var tipo_account = '${person.account.typeOfAccount}';

            $(document).ready(function () {
                $.ajax({
                    url: 'RecuperaDatiUtente',
                    type: 'POST',
                    data: {ssn: codice_fiscale, tipo: tipo_account},
                    success: function (msg) {
                        var data_user = $.parseJSON(msg);
                        var nome = data_user.nome;
                        var cognome = data_user.cognome;
                        var dipartimento = data_user.dipartimento;
                        var università = data_user.università;


                        $("#università").html(università);
                        $("#fullname").html(nome + " " + cognome);
                        $("#dip").html(dipartimento);

                    }
                });
            });
        </script>   
    </head>
    <body>

        <div class="panel panel-color panel-danger"><!-- Add class "collapsed" to minimize the panel -->
            <div class="panel-heading">
                <h3 class="panel-title">I Tuoi Dati</h3>

                <div class="panel-options">
                    <a href="#" data-toggle="panel">
                        <span class="collapse-icon">&ndash;</span>
                        <span class="expand-icon">+</span>
                    </a>                  
                </div>
            </div>

            <div class="panel-body">
                <div class="row">
                    <div class="col-sm-12">
                        <b>Nome e Cognome</b><p id="fullname"></p>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-6">
                        <b>Dipartimento</b><p id="dip"></p>
                    </div>

                </div>
            </div>
        </div>        

    </body>
</html>
