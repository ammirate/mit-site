/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

tpOrganizationFunction = {
    modifyTraining: function (idModify) {
        $("#descriptionTrainingForModify").attr("placeholder", $('#paragraphDescriptionListTraining_' + idModify).text());
        $("#idForModifyTrainingOrganization").attr("value", idModify);
        jQuery('#modal').modal('show', {backdrop: 'static'});
    },
    removeTraining: function (idRemove, path) {
        if (confirm("Sei sicuro di voler eliminare questo tirocinio?")) {
            this.deleteTraining(idRemove, path);
            setTimeout(function () {
                location.reload();
            }, 1000);
        }
    },
    appendTraining: function (idPanel, account, path) {
        $.ajax({
            url: path + '/organizationGetAllTrainingOffers',
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
                        var stringToAppend_tmp = '<a href="#" class="list-group-item" onclick="return false;" style="cursor:default;">';
                        stringToAppend_tmp = stringToAppend_tmp + '<p id="paragraphDescriptionListTraining_' + tmpIDTraining + '" class="list-group-item-text">' + tmpDescriptionTraining + '</p><div class="vertical-top" align="right">';
                        stringToAppend_tmp = stringToAppend_tmp + '<button id="modifyListTraining_' + tmpIDTraining + '" class="btn btn-orange btn-sm" onclick=tpOrganizationFunction.modifyTraining(' + tmpIDTraining + ')><i class="fa fa-pencil"></i><span> Modifica</span></button>';
                        stringToAppend_tmp = stringToAppend_tmp + '<button id="removeListTraining_' + tmpIDTraining + '" class="btn btn-red btn-sm" onclick=tpOrganizationFunction.removeTraining(' + tmpIDTraining + ',"'+path+'")><i class="fa fa-trash"></i><span> Elimina</span></button>';
                        stringToAppend_tmp = stringToAppend_tmp + '</div></a>';

                        $(idPanel).append(stringToAppend_tmp);
                    }
                } else {
                    $(idPanel).append("<center><strong>I dati caricati non correttamente o non sono presenti nel database.</strong></center>");
                }
            }, error: function (e) {
                $(idPanel).append("<center><strong>I dati non sono stati caricati correttamente.</strong></center>");
            }
        });

    },
    deleteTraining: function (idTraining, path) {
        $.post(path + "/organizationDeleteTrainingOffer", {idRemove: idTraining}).fail(function (e) {
            alert("Non è stato possibile eseguire la richiesta.");
        });
    },
    updateTraining: function (idTraining, description, path) {
        $.post(path + "/organizationUpdateTrainingOffer", {idModify: idTraining, description: description}).fail(function (e) {
            alert("Non è stato possibile eseguire la richiesta.");
        });
    }
};		