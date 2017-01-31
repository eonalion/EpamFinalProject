$(document).ready(function () {
    var addToCartBtn = '.add-to-cart-button';
    var $addToCartBtn = $(addToCartBtn);
    var removeFromCartBtn = '.remove-from-cart-btn';
    var $removeFromCartBtn = $(removeFromCartBtn);

    $addToCartBtn.click(function () {
        $(this).text('In cart');
    });

    $removeFromCartBtn.click(function () {
        $(this).parent().parent().remove();
    });

    var addToCartForm = '.add-to-cart-form';
    var removeFromCartForm = '.remove-from-cart-form';
    var $addToCartForm = $(addToCartForm);
    var $removeFromCartForm = $(removeFromCartForm);

    var respSelector = '<div class="row response"></div>';
    var $respTag = $(respSelector);
    var failureStyle = {"color": "#83353d", "font-size": "13px"};
    $respTag.css(failureStyle);

    /*$removeFromCartForm.ajaxForm(function (response) {
        switch (response.left) {
            case "OK":
                break;
            case "ERROR":
                $respTag.text(response.right.causeMessage + " " + response.right.toDoMessage);
                $addToCartForm.after($respTag);
                break;
        }
    });*/

    $addToCartForm.ajaxForm(function (response) {
        switch (response.left) {
            case "OK":
                break;
            case "ERROR":
                $respTag.text(response.right.causeMessage + " " + response.right.toDoMessage);
                $addToCartForm.after($respTag);
                break;
        }
    });

});
