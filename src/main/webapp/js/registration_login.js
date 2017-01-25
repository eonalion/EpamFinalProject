/**
 */

// $(document).on("submit", "#signUpButton", function (event) {
//     var $form = $(this);
//     $.post($form.attr("action"), $form.serialize(), function (response) {
//         $.each(response, function (key, value) {
//             $("<p>").text(key + " " + value).appendTo($("body"));
//         });
//     });
//     event.preventDefault();
// });

$(document).ready(function() {
    // bind 'myForm' and provide a simple callback function
    $('#registration').ajaxForm(function(response) {
        $.each(response, function (key, value) {
            $("<p>").text(key+" "+value).appendTo($("#register-section"));
        });
    });

   /* $('#log-in').ajaxForm(function(response) {
        $.each(response, function (key, value) {
            $("<p>").text(key+" "+value).appendTo($("#log-in-section"));
        });
    });*/
});
