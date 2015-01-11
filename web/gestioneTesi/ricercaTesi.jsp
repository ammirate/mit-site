<%-- 
    Document   : ricercaTesi
    Created on : 5-dic-2014, 12.37.11
    Author     : Daniele
--%>

<!DOCTYPE html>
<html>
    <head>

        <link rel="stylesheet" href="../assets/js/select2/select2.css">
        <link rel="stylesheet" href="../assets/js/select2/select2-bootstrap.css">



        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tesi Studente</title>
	
	<script src="../assets/js/select2/select2.min.js"></script>
        
	<script type="text/javascript">
            /**
             * Serve per memorizzare i dati selezionati dall'utente. 
             * @type Array I dati devono essere memorizzati come una coppia "chiave:valore" 
             * (e.g., values.push("department:DISTRA")).
             */
            var values = new Array("", "", "", "", "", "", ""); 
                        
            /**
             * Ottiene solo il testo dell'elemento su cui viene chiamato la funzione.
             * @returns {Sizzle.getText|jQuery} Il testo dell'elemento
             */
            $.fn.justText = function() {
                return $(this)
                        .clone() //clona l'elemento
                        .children() //seleziona tutti i figli
                        .remove() //rimuove tutti i figli
                        .end() //ritorna all'elemento selezionato
                        .text(); //ottiene il testo dell'elemento
            };
	    
            /**
             * Modifica lo stile della select associata all'id "element_id".
             * @param {type} element_id L'id della select.
             * @param {type} message Il messaggio da visualizzare nella select.
             * @returns {undefined}
             */
            $.fn.initialize = function(element_id, message) {
                $("#" + element_id).select2({
                    placeholder: message,
                    allowClear: true
                }).on('select2-open', function ()
                {
                    // Adding Custom Scrollbar
                    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
                });
            };
            
            /**
             * Modifica lo stile delle select specificate, presenti nella pagina.
             * @returns {undefined}
             */
            $.fn.initializeAll = function() {
                $.fn.populateThesisData("RecuperaDatiDepartment", "department", "ALL");
                $.fn.populateThesisData("RecuperaDatiDegree", "degree", "ALL");
                $.fn.populateThesisData("RecuperaDatiPerson", "professor", "ALL_Professors");
                $.fn.populateThesisData("RecuperaDatiPerson", "student", "ALL_Students");
                $.fn.populateThesisData("RecuperaDatiStoricoTesi", "title", "ALL");
                $.fn.populateThesisData("RecuperaTag", "tag", "ALL");
                $.fn.initialize("department", "Seleziona il dipartimento...");
                $.fn.initialize("degree", "Seleziona il corso di laurea...");
                $.fn.initialize("professor", "Seleziona il professore...");
                $.fn.initialize("student", "Seleziona lo studente...");
                $.fn.initialize("title", "Seleziona il titolo...");
                $.fn.initialize("tag", "Seleziona l'argomento della tesi...");
            };
            
            /**
             * Popola la select associata all'id "element_id", con i dati ottenuti dall'esecuzione
             * della servlet specificata.
             * @param {type} servlet La servlet da eseguire per ottenere i dati.
             * @param {type} element_id L'id della select da popolare.
             * @param (type) value Il valore da utilizzare per il popolamento della select.
             * @returns {undefined}
             */
            $.fn.populateThesisData = function(servlet, element_id, value) {
                if(servlet === "RecuperaDatiPerson") {
		    $.ajax({
			url: "${pageContext.request.contextPath}/RecuperaDatiPerson",
			type: 'POST',
			data: { type: element_id, data: value },
			success: function (data) {
			    var empty_option = "<option></option>\n";

			    var person_select = empty_option;

			    var person_data = $.parseJSON(data);
			    
			    $.each(person_data, function() {
				if(typeof this[element_id + '_ssn'] !== "undefined" &&
				    $("#" + element_id + "_ssn-" + this[element_id + '_ssn']).length === 0) {
				    person_select = "<option id='" + element_id + "_ssn-" + this[element_id + '_ssn'] + "' value='" + 
					this[element_id + '_ssn'] + "'>" + this[element_id + '_name'] + " " + 
					this[element_id + '_surname'] + "</option>\n";
				    $("#" + element_id).append(person_select);
				}
			    });
			}
		    });
		} else {
		    $.ajax({
			url: "${pageContext.request.contextPath}/" + servlet,
			type: 'POST',
			data: { data: value },
			success: function (data) {
			    var tesi = $.parseJSON(data);
			    var select = "<option></option>\n";
			    
			    if(element_id === "department") {		    
				$.each(tesi, function() {
				    select += "<option value='" + this['department_abbrevation'] + 
					    "'>" + this['department_title'] + "</option>\n";
				});
			    } else if(element_id === "degree") {		    
				$.each(tesi, function() {
				    select += "<option value='" + this['degree_matricula'] + 
					    "'>[" + this['degree_matricula'] + "] " + this['degree_title'] + "</option>\n";
				});
			    } else if(element_id === "title") {		    
				$.each(tesi, function() {
				    select += "<option value='" + this['thesis_id'] + 
					    "'>" + this['thesis_title'] + "</option>\n";
				});
			    } else if(element_id === "tag") {		    
				$.each(tesi, function() {
				    select += "<option value='" + this['tag_id'] + 
					    "'>" + this['tag_name'] + "</option>\n";
				});
			    } 
			    //console.log(element_id + ": " + select);
			    $("#" + element_id).html(select);
			}
		    });
		}
            };
            
            /**
            * Popola le select e riempie le riga della tabella con i dati delle 
	    * tesi, ottenute come risultato della ricerca dell'utente (i.e., in 
	    * seguito alla selezione dei criteri di ricerca tramite le select).
            * @returns {undefined}             
            */
            $.fn.fillThesisSelectsAndTable = function() {
		if(values[0] === "" && values[1] === "" && values[2] === "" && 
			values[3] === "" && values[4] === "" && values[5] === "" && values[6] === "") {
		    $.fn.initializeAll();
		    $("#tbody-tesi td").remove();
		} else {
		    $.ajax({
			url: "${pageContext.request.contextPath}/RecuperaDatiStoricoTesi",
			type: 'POST',
			data: { researchCriteria : values },
			success: function (data) {
			    var empty_option = "<option></option>\n";

			    var department_select = "";
			    var degree_select = empty_option; 
			    var professor_select = empty_option;
			    var student_select = empty_option; 
			    var title_select = empty_option; 
			    var tag_select = empty_option; 

			    var thesis_table = "";

			    //$("form option").remove();
			    $("select").each(function(){
				$(this).html(empty_option);
			    });

			    var thesis_data = $.parseJSON(data);
			    var i = 1;
			    $.each(thesis_data, function() {
				if(typeof this['department_abbreviation'] !== "undefined" &&
				    $("#department_abbreviation-" + this['department_abbreviation']).length === 0) {
				    department_select = "<option id='department_abbreviation-" + 
					    this['department_abbreviation'] + "' value='" + this['department_abbreviation'] + 
					    "'>" + this['department_title'] + "</option>\n";
				    $("#department").append(department_select);
				}

				if(typeof this['degree_matricula'] !== "undefined" &&
				    $("#degree_matricula-" + this['degree_matricula']).length === 0) {
				    degree_select = "<option id='degree_matricula-" + 
					    this['degree_matricula'] + "' value='" + this['degree_matricula'] + "'>[" + 
					this['degree_matricula'] + "] " + this['degree_title'] + "</option>\n";
				    $("#degree").append(degree_select);
				}

				if(typeof this['professor_ssn'] !== "undefined" &&
				    $("#professor_ssn-" + this['professor_ssn']).length === 0) {
				    professor_select = "<option id='professor_ssn-" + this['professor_ssn'] + "' value='" + 
					this['professor_ssn'] + "'>" + this['professor_name'] + " " + 
					this['professor_surname'] + "</option>\n";
				    $("#professor").append(professor_select);
				}

				student_select = "<option id='student_ssn-" + this['student_ssn'] + "' value='" + this['student_ssn'] + "'>" + 
				    this['student_name'] + " " + this['student_surname'] + "</option>\n";
				$("#student").append(student_select);

				title_select = "<option value='" + this['thesis_id'] + "'>" + 
				    this['thesis_title'] + "</option>\n";
				$("#title").append(title_select);

				tag_select = "<option value='" + this['tag_id'] + "'>" + 
				    this['tag_name'] + "</option>\n";
				$("#tag").append(tag_select);

				thesis_table += "<tr id ='" + this['thesis_title'] + "'>\n";
				thesis_table += "<td>" + i++ + "</td>\n";
				thesis_table += "<td>" + this['thesis_title'] + "</td>\n";
				thesis_table += "<td>" + this['student_name'] + " " + this['student_surname'] + "</td>\n";
				thesis_table += "<td>" + this['professor_name'] + " " + this['professor_surname'] + "</td>\n";
				thesis_table += "<td>" + this['degree_date'] + "</td>\n";
				thesis_table += "<td>" + this['thesis_abstract']+ "</td>\n";
				thesis_table += "<td>" + this['thesis_description'] + "</td>\n";
				thesis_table += "</tr>\n";
			    });

			    $("#tbody-tesi td").remove();
			    $("#tbody-tesi").append(thesis_table);
			},
			error: function() {
			    alert("Errore");
			}
		    });
		}
            };
            
            $(document).ready(function ($)
            {
                $.fn.initializeAll();
                
                $("#department").click(function ()
                {
                    values[0] = $(this).val();
                    $.fn.fillThesisSelectsAndTable();
                });
                
                $("#degree").click(function ()
                {
                    values[1] = $(this).val();
                    $.fn.fillThesisSelectsAndTable();
                });
                
                $("#professor").click(function ()
                {
                    values[2] = $(this).val();
                    $.fn.fillThesisSelectsAndTable();
                    
                });
                
		$("#student").click(function ()
                {
                    values[3] = $(this).val();
                    $.fn.fillThesisSelectsAndTable();
                    
                });
                
		$("#title").click(function ()
                {
                    values[4] = $(this).val();
                    $.fn.fillThesisSelectsAndTable();
                    
                });
                
		$("#tag").click(function ()
                {
                    values[5] = $(this).val();
                    $.fn.fillThesisSelectsAndTable();
                    
                });
                
		$("#degree-date").change(function ()
                {
                    values[6] = $(this).val();
                    $.fn.fillThesisSelectsAndTable();
                    
                });
            });

        </script>
    </head>
    <body>
        <!-- Form ricerca tesi -->
        <div class="panel panel-color panel-danger"><!-- Add class "collapsed" to minimize the panel -->
            <div class="panel-heading">
                <h3 class="panel-title">Criteri di ricerca</h3>
		
		<div class="panel-options">
                    <a href="#" data-toggle="panel" style="text-decoration:none;">
                        <span class="collapse-icon">&ndash;</span>
                        <span class="expand-icon">+</span>
                    </a>                  
                </div>
            </div>

            <div class="panel-body">

                <form role="form" action="${pageContext.request.contextPath}/richiestaTesi" method="POST" class="form-horizontal">

                    <div class="form-group">
                        <label class="col-sm-2 control-label text-primary" for="dipartimenti">Dipartimento</label>

                        <div class="col-sm-10">
                            <select class="form-control" name="dipartimenti" id="department">
                                <option id="department-options"> </option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group-separator"></div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label text-primary" for="corso_laurea">Corso di laurea</label>

                        <div class="col-sm-10">
                            <select class="form-control" name="corso_laurea" id="degree">
                                <option id="degree-options"> </option>
                            </select>
                        </div>
                    </div>
                
                    <div class="form-group-separator"></div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label text-primary" for="professore">Professore</label>

                        <div class="col-sm-10 ">
                            <select class="form-control" name="professore" id="professor">
                                <option id="professor-options"> </option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group-separator"></div>
      
                    <div class="form-group">
                        <label class="col-sm-2 control-label text-primary" for="studente">Studente</label>

                        <div class="col-sm-10 ">
                            <select class="form-control" name="studente" id="student">
                                <option id="student-options"> </option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group-separator"></div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label text-primary" for="titolo">Titolo Tesi</label>

                        <div class="col-sm-10 ">
                            <select class="form-control" name="titolo" id="title">
                                <option id="title-options"> </option>   
                            </select>
                        </div>
                    </div>

                    <div class="form-group-separator"></div>
                    
		    <div class="form-group">
                        <label class="col-sm-2 control-label text-primary" for="argomento">Argomento</label>

                        <div class="col-sm-10 ">
                            <select class="form-control" name="argomento" id="tag">
                                <option id="tag-options"> </option>   
                            </select>
                        </div>
                    </div>

                    <div class="form-group-separator"></div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label text-primary" for="data">Data di laurea</label>
			<div class="col-sm-10 ">
			    <input type="month" class="form-control" id="degree-date">
			</div>
                    </div>

                </form>
            </div>
        </div>
		
	<div class="panel panel-color panel-danger"><!-- Add class "collapsed" to minimize the panel -->
	    <div class="panel-heading">
                <h3 class="panel-title">Risultati ricerca</h3>
		
		<div class="panel-options">
                    <a href="#" data-toggle="panel" style="text-decoration:none;">
                        <span class="collapse-icon">&ndash;</span>
                        <span class="expand-icon">+</span>
                    </a>                  
                </div>
            </div>

            <div class="panel-body">

                <div class="row">
                    <div class="col-sm-12">
                            <table class="table table-hover">
                                    <thead>
                                            <tr>
                                                    <th>#</th>
                                                    <th>Titolo</th>
                                                    <th>Studente</th>
                                                    <th>Relatore</th>
                                                    <th>Data Laurea</th>
                                                    <th>Abstract</th>
                                                    <th>Descrizione</th>
                                            </tr>
                                    </thead>

                                    <tbody id="tbody-tesi">
                                    </tbody>
                            </table>

                    </div>
                </div>

                <div class="vspacer v3"></div>
            </div>
	</div>
    </body>
</html>
