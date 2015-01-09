<%-- 
    Document   : tesiInCorso
    Created on : 12-dic-2014, 10.59.06
    Author     : CosimoAlessandro
--%>

<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script>

            $(document).ready(function () {
                $(document).on("click", "#button_completa", function (e) {
                    e.preventDefault();
                    bootbox.confirm("Questa operazione sarà sottoposta a verifica del professore. Continuare?", function (result) {
                        if (result) {
                            $.ajax({
                                url: 'richiestaCompletaTesi',
                                type: 'POST',
                                data: {id_tesi: id},
                                success: function (msg) {
                                    $("#button_completa").prop("disabled", true);
                                    $("#button_modifica").prop("disabled", true);
                                }
                            });
                        }
                    });
                });
            });

            $(document).on("click", "#button_modifica", function (e) {
                e.preventDefault();

               var titolo = $('#titolo').val();
               var abstract = $('#abstract').val();
               var data_inizio = $('#data_inizio').val();
               var data_fine_prevista = $('#data_fine_prevista').val();
               var data_fine = $('#data_fine').val();
              //  $('#send_memory').attr("disabled", true);
              //  $('#load_spinning').show();
                $.ajax({
                    url: 'modificaTesi',
                    type: 'POST',
                    data: {id_tesi:id, titolo:titolo, abstract:abstract, data_inizio:data_inizio, data_fine:data_fine, data_fine_prevista:data_fine_prevista},
                    success: function (msg) {
                        alert("modifica riuscita");
                    }
                });

            });

        </script>
    </head>
    <body>

        <div id="inCorso" class="panel panel-color panel-danger"><!-- Add class "collapsed" to minimize the panel -->
            <div class="panel-heading">
                <h3 id="titolo_panel_tesi_in_corso" class="panel-title">Tesi In Corso</h3>
            </div>


            <div class="panel-body">


                <form  role="form" id="modificaTesi" action="${pageContext.request.contextPath}/modificaTesi" method="POST" class="form-horizontal validate">

                    <br>

                    <div class="form-group">
                        <label class="col-sm-2 text-primary " for="titolo">Titolo</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="titolo" name="titolo" data-validate="required" data-message-required="Campo obbligatorio." placeholder="Inserisci il titolo" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2  text-primary text-left" for="abstract">Abstract</label>

                        <div class="col-sm-10">
                            <textarea type="text" class="form-control" id="abstract" name="abstract" rows="5" data-validate="required" data-message-required="Campo obbligatorio." placeholder="Inserisci l'abstract" ></textarea>
                        </div>
                    </div>

                    <br>

                    <div class="form-group-separator"></div>

                    <br>

                    <div class="form-group">
                        <div class="col-sm-4">

                            <label class="text-primary " >Data Inizio</label>
                            <div class="input-group">
                                <input placeholder="Data Inizio" id="data_inizio" name="data_inizio" type="text" class="form-control datepicker" data-format="yyyy-mm-dd">

                                <div class="input-group-addon">
                                    <a href="#"><i class="linecons-calendar"></i></a>
                                </div>
                            </div>

                            <br>

                            <label class="text-primary" >Data Fine Prevista</label>
                            <div class="input-group">
                                <input placeholder="Data Fine Prevista" id="data_fine_prevista" name="data_fine_prevista" type="text" class="form-control datepicker" data-format="yyyy-mm-dd">

                                <div class="input-group-addon">
                                    <a href="#"><i class="linecons-calendar"></i></a>
                                </div>
                            </div>

                            <br>

                            <label class="text-primary" >Data Fine</label>
                            <div class="input-group">
                                <input placeholder="Data Fine" id="data_fine" name="data_fine" type="text" class="form-control datepicker" data-format="yyyy-mm-dd">

                                <div class="input-group-addon">
                                    <a href="#"><i class="linecons-calendar"></i></a>
                                </div>
                            </div>


                        </div>

                        <label class="col-sm-2 text-primary text-right" for="tagsinput-1">Seleziona Relatori</label>

                        <div class="col-sm-6 pull-right">

                            <script type="text/javascript">
                                jQuery(document).ready(function ($)
                                {
                                    $("#multi-select").multiSelect({
                                        afterInit: function ()
                                        {
                                            // Add alternative scrollbar to list
                                            this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar();
                                        },
                                        afterSelect: function ()
                                        {
                                            // Update scrollbar size
                                            this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar('update');
                                        }
                                    });
                                });
                            </script>
                            <select class="form-control" multiple="multiple" id="multi-select"  name="professors[]">
                                <option>Ciao</option>
                            </select>

                        </div>
                    </div>
                    <br>

                    <div class="form-group-separator"></div>

                    <br>

                    <div class="form-group">
                        <label class="col-sm-2  text-primary " for="tagsinput-1">Argomenti (Tag)</label>

                        <div class="col-sm-10">

                            <div class="input-group">
                                <input type="text"  id="argument_tag"  />

                                <input name="tag_selected[]" type="hidden" id="tag_selected" />

                            </div>

                        </div>
                    </div>

                    <br>

                    <div class="form-group-separator"></div>

                    <script type="text/javascript">
                        jQuery(document).ready(function ($)
                        {
                            var i = 1,
                                    $example_dropzone_filetable = $("#example-dropzone-filetable"),
                                    example_dropzone = $("#advancedDropzone").dropzone({
                                url: 'data/upload-file.php',
                                // Events
                                addedfile: function (file)
                                {
                                    if (i == 1)
                                    {
                                        $example_dropzone_filetable.find('tbody').html('');
                                    }

                                    var size = parseInt(file.size / 1024, 10);
                                    size = size < 1024 ? (size + " KB") : (parseInt(size / 1024, 10) + " MB");

                                    var $el = $('<tr>\
                                                                                                        <td class="text-center">' + (i++) + '</td>\
                                                                                                        <td>' + file.name + '</td>\
                                                                                                        <td><div class="progress progress-striped"><div class="progress-bar progress-bar-warning"></div></div></td>\
                                                                                                        <td>' + size + '</td>\
                                                                                                        <td>Uploading...</td>\
                                                                                                </tr>');

                                    $example_dropzone_filetable.find('tbody').append($el);
                                    file.fileEntryTd = $el;
                                    file.progressBar = $el.find('.progress-bar');
                                },
                                uploadprogress: function (file, progress, bytesSent)
                                {
                                    file.progressBar.width(progress + '%');
                                },
                                success: function (file)
                                {
                                    file.fileEntryTd.find('td:last').html('<span class="text-success">Uploaded</span>');
                                    file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-success');
                                },
                                error: function (file)
                                {
                                    file.fileEntryTd.find('td:last').html('<span class="text-danger">Failed</span>');
                                    file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-red');
                                }
                            });

                            $("#advancedDropzone").css({
                                minHeight: 200
                            });

                        });
                    </script>

                    <br />

                    <label class=" text-primary " for="tagsinput-1">Allegati</label>

                    <div class="row">
                        <div class="col-sm-3 text-center">

                            <div id="advancedDropzone" class="droppable-area">
                                Drop Files Here
                            </div>

                        </div>
                        <div class="col-sm-9">

                            <table class="table table-bordered table-striped" id="example-dropzone-filetable">
                                <thead>
                                    <tr>
                                        <th width="1%" class="text-center">#</th>
                                        <th width="50%">Name</th>
                                        <th width="20%">Upload Progress</th>
                                        <th>Size</th>
                                        <th>Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="5">Files list will appear here</td>
                                    </tr>
                                </tbody>
                            </table>

                        </div>
                    </div>

                    <br>

                    <div class="form-group-separator"></div>

                    <br>

                    <input name="idtesi" id="idtesi" type="hidden" />

                    <div class="col-sm-3">
                        <button id="button_modifica"  class="btn btn-danger btn-icon btn-icon-standalone btn-lg">
                            <i class="fa-save"></i>
                            <span>Salva Modifiche</span>
                        </button>
                    </div>

                </form>

                <div class="col-sm-3">
                    <button id="button_completa" class="btn btn-secondary btn-icon btn-icon-standalone btn-lg">
                        <i class="fa-graduation-cap"></i>
                        <span>Tesi Completata</span>
                    </button>
                </div>

            </div>
        </div>

        <script src="assets/js/datepicker/bootstrap-datepicker.js"></script>
        <script src="assets/js/tagsinput/bootstrap-tagsinput.min.js"></script>
        <script src="assets/js/bootbox/bootbox.js"></script>
        <script src="assets/js/dropzone/dropzone.min.js"></script>

    </body>
</html>
