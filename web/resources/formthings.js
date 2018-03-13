$(document).ready(function() {
    $('#addform').submit(function() {
        $.ajax({
            type: "POST",
            url: "Outgoes.jsp",
            data: $(this).serialize()
        }).done(function() {
            $(this).find('select').val('0');
            $('#addform').trigger('reset');
        });
        return false;
    });
});