$(document).ready(function () {
    debugger;
    $("#addCategoryform").submit(function () {
        $.ajax({
            type: "POST",
            url: "/changeOptions",
            data: $(this).serialize()
        });
    });
});