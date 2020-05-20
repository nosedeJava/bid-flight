$(document).ready(function () {
    $.ajax({
        url: "/accounts/sessionID",
        type: "GET",
        contentType: "application/json",
        success: function (data) {
            $("#names").text(data.names)
            $("#lastnames").text(data.lastnames)
            $("#username").text(data.username)
            $("#document").text(data.document)
            $("#balanceText").text(data.balance)
        },
        error: function (data) {

        }
    });
    let putBalance = (balance) => {
        apiclientLogin.putMoreBalance(localStorage.getItem("username"), balance, (err, res) => {
            if (err) {
                alert("There was an error increasing your balance");
            } else {
                $("#balanceText").text(res);
                $("#userbalance").text(res);
            }
        });
    };
    $("#btnBalance").click(function () {
        putBalance($("#amount").val());
    });

})