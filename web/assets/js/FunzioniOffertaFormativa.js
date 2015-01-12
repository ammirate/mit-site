function changeSelect(){
    
    $("#department").select2({placeholder:'Seleziona un dipartimento',allowClear: true}).on('select2-open', function(){
    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();}); 
    $("#cycle").select2({placeholder:'Seleziona un ciclo',allowClear: true}).on('select2-open', function(){
    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();});
    $("#degree").select2({placeholder:'Seleziona un degree',allowClear: true}).on('select2-open', function(){
    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();});    
    $("#curriculum").select2({placeholder:'Seleziona un curriculum',allowClear: true}).on('select2-open', function(){
    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();});
    $("#teaching").select2({placeholder:'Seleziona un insegnamento',allowClear: true}).on('select2-open', function(){
    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();});
}

function loadDegree() {
    $.get('GetDegreeServlet?cycle=' + $('#cycle').val() + "&department=" + $('#department').val(), function (responseJson) {
        var $select = $('#degree');
        $select.select2('val', '');
        //$('#curriculum').find('option').remove();
        $('#curriculum').select2('val', '');
        $select.removeAttr("onclick");
        $select.find('option').remove();
        $('<option>').text("").appendTo($select);
        $.each(responseJson, function (key, value) {
            $('<option>').val(value.matricula).text(value.title).appendTo($select);
        });
    });
}
function loadCycle(setdefault) {
    $.get('GetCycleServlet', function (responseJson) {
        var $select = $('#cycle');
        $select.removeAttr("onclick");
        $select.find('option').remove();
        $('<option>').text("").appendTo($select);
        $.each(responseJson, function (key, value) {
            $('<option>').val(value.cycle_number).text(value.title).appendTo($select);
        });
        if(setdefault)$("#cycle").select2("val",setdefault);
    });
}
function loadDepartment(setdefault) {
    $.get('GetDepartmentServlet', function (responseJson) {
        var $select = $('#department');
        $select.removeAttr("onclick");
        $select.find('option').remove();
        $('<option>').text("").appendTo($select);
        $.each(responseJson, function (key, value) {
            
            $('<option>').val(value.departmentAbbreviation).text(value.title).attr("selected",true).appendTo($select);
        });
        if(setdefault)$("#department").select2("val",setdefault);
    });
}
function loadCurriculum(i) {
    $.get("GetCurriculumServlet?degree=" + i, function (responseJson) {
        var $select = $('#curriculum');
        $select.removeAttr("onclick");
        $select.find('option').remove();
        $('<option>').text("").appendTo($select);
        $.each(responseJson, function (key, value) {
            $('<option>').val(value.matricula).text(value.title).appendTo($select);
        });
    });
}
function loadTeaching(i){
    $.get("GetTeachingServlet?curriculum="+i, function(responseJson){
        var $select = $('#teaching');
        $select.find('option').remove();
        $('<option>').text("").appendTo($select);
        $.each(responseJson, function (key, value) {
            $('<option>').val(value.matricula).text(value.title).appendTo($select);
        });
    });
}