$(function () {
    $(".btn-decrease-quantity").click(function () {
        let gift_id = $(this).data("gift-id");
        let inputQuantity = $("#quantity-" + gift_id);
        if (inputQuantity.val() > 1) {
            inputQuantity.val(parseInt(inputQuantity.val()) - 1);
        }
    });

    $(".btn-increase-quantity").click(function () {
        let gift_id = $(this).data("gift-id");
        let inputQuantity = $("#quantity-" + gift_id);
        inputQuantity.val(parseInt(inputQuantity.val()) + 1);
    });
});