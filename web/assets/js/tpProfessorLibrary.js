/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

tpProfessorFunction = {
    appendTraining: function (idPanel, account, path) {
        $.ajax({
            url: path + '/professorGetAllTrainingOffers',
            dataType: 'text',
            data: {accountEmail: account},
            type: 'POST',
            success: function (e) {
                var parsed = jQuery.parseJSON(e);
                if (parsed.status) {
                    var myArrayForListTraining = parsed.message;
                    for (var i = 0; i < myArrayForListTraining.length; i++) {
                        var tmpIDTraining = myArrayForListTraining[i].id;
                        var tmpDescriptionTraining = myArrayForListTraining[i].description;
                        var splitted = tmpDescriptionTraining.split(" - ");
                        if (splitted[0] === "distra") {
                            var stringToAppend_tmp = '<a href="#" class="list-group-item" onclick="return false;" style="cursor:default;">';
                            stringToAppend_tmp = stringToAppend_tmp + '<p id="paragraphDescriptionListTraining_' + tmpIDTraining + '" class="list-group-item-text">' + tmpDescriptionTraining + '</p><div class="vertical-top" align="right">';
                            stringToAppend_tmp = stringToAppend_tmp + '<button id="modifyListTraining_' + tmpIDTraining + '" class="btn btn-orange btn-sm" onclick=tpProfessorFunction.modifyTraining(' + tmpIDTraining + ')><i class="fa fa-pencil"></i><span> Modifica</span></button>';
                            stringToAppend_tmp = stringToAppend_tmp + '<button id="removeListTraining_' + tmpIDTraining + '" class="btn btn-red btn-sm" onclick=tpProfessorFunction.removeTraining(' + tmpIDTraining + ',"' + path + '")><i class="fa fa-trash"></i><span> Elimina</span></button>';
                            stringToAppend_tmp = stringToAppend_tmp + '</div></a>';
                            $(idPanel).append(stringToAppend_tmp);
                        } else {
                            var stringToAppend_tmp = '<a href="#" class="list-group-item" onclick="return false;" style="cursor:default;">';
                            stringToAppend_tmp = stringToAppend_tmp + '<p id="paragraphDescriptionListTraining_' + tmpIDTraining + '" class="list-group-item-text">' + tmpDescriptionTraining + '</p>';
                            stringToAppend_tmp = stringToAppend_tmp + '</a>';
                            $(idPanel).append(stringToAppend_tmp);
                        }
                    }
                } else {
                    $(idPanel).append("<center><strong>I dati non sono stati caricati correttamente o non sono presenti nel database.</strong></center>");
                }
            }, error: function (e) {
                $(idPanel).append("<center><strong>I dati non sono stati caricati correttamente.</strong></center>");
            }
        });

    },
    deleteTraining: function (idTraining, path) {
        $.post(path + "/professorDeleteTrainingOffer", {idRemove: idTraining}).fail(function (e) {
            alert("Si sono verificati dei problemi nell\'elaborazione della richiesta.");
        });
    },
    updateTraining: function (idTraining, description, path) {
        $.post(path + "/deleteTrainingOfferByOrganizationServlet", {idModify: idTraining, description: description}).fail(function (e) {
            alert("Si sono verificati dei problemi nell\'elaborazione della richiesta.");
        });
    },
    modifyTraining: function (idModify) {
        $("#descriptionTrainingForModify").attr("placeholder", $('#paragraphDescriptionListTraining_' + idModify).text());
        $("#idForModifyTrainingProfessor").attr("value", idModify);
        jQuery('#modal').modal('show', {backdrop: 'static'});
    },
    removeTraining: function (idRemove, path) {
        if (confirm("Sei sicuro di voler eliminare questo tirocinio?")) {
            alert("Il toricinio è stato eliminato.");
            this.deleteTraining(idRemove, path);
            setTimeout(function () {
                location.reload();
            }, 1000);
        }
    },
    populateProfessorPanel: function (idTable, idContainer, path) {
        $.ajax({
            url: path + '/getProfessorStudentsViews',
            dataType: 'text',
            type: 'POST',
            success: function (e) {
                $(idTable).append('<thead><tr><th>Nome</th><th>Cognome</th><th>Email</th><th>Status</th></tr></thead>');
                var parsed = JSON.parse(e);
                if (!parsed.status)
                    return;
                var jsonObj = parsed.message;
                var tmp = '';
                for (var i = 0; i < jsonObj.length; i++) {
                    tmp += '<tr><td>' + jsonObj[i].name + '</td><td>' + jsonObj[i].surname + '</td><td>' + jsonObj[i].email + '</td><td>' + jsonObj[i].trainingstatus + '</td></tr>';
                }
                $(idTable).append(tmp);
                $(idTable).dataTable({
                    aLengthMenu: [
                        [5, 10, 20, -1], [5, 10, 20, "Tutti"]
                    ]});
                $(tableContainer).attr("hidden", false);
            },
            error: function (e) {
                alert(JSON.stringify(e));
            }
        });
    }
};		