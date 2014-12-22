function changeSelect(){
    
    $("#department").select2({allowClear: true}).on('select2-open', function(){
    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();});    
    $("#cycle").select2({allowClear: true}).on('select2-open', function(){
    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();});
    $("#degree").select2({allowClear: true}).on('select2-open', function(){
    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();});    
    $("#curriculum").select2({allowClear: true}).on('select2-open', function(){
    $(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();});
}

function loadDegree() {
    $.get('GetDegreeServlet?cycle=' + $('#cycle').val() + "&department=" + $('#department').val(), function (responseJson) {
        var $select = $('#degree');
        $select.find('option').remove();
        $('<option>').val(0).text("Scegli Corso").appendTo($select);
        $.each(responseJson, function (key, value) {
            $('<option>').val(value.matricula).text(value.title).appendTo($select);
        });
    });
}
function loadCycle() {
    $.get('GetCycleServlet', function (responseJson) {
        var $select = $('#cycle');
        $select.find('option').remove();
        $('<option>').val(0).text("Scegli Ciclo").appendTo($select);
        $.each(responseJson, function (key, value) {
            $('<option>').val(value.cycle_number).text(value.title).appendTo($select);
        });
    });
}
function loadDepartment() {
    $.get('GetDepartmentServlet', function (responseJson) {
        var $select = $('#department');
        $select.find('option').remove();
        $('<option>').val(0).text("Scegli Dipartimento").appendTo($select);
        $.each(responseJson, function (key, value) {
            $('<option>').val(value.departmentAbbreviation).text(value.title).appendTo($select);
        });
    });
}
function loadCurriculum(i) {
    $.get("GetCurriculumServlet?degree=" + i, function (responseJson) {
        var $select = $('#curriculum');
        $select.find('option').remove();
        $('<option>').val(0).text("Scegli Curriculum").appendTo($select);
        $.each(responseJson, function (key, value) {
            $('<option>').val(value.matricula).text(value.title).appendTo($select);
        });
    });
}