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
            console.log(data)
        }
    });
    $("#btnBalance").click(function () {
        alert("HAHA")
        alert($("#amount").val())
        putBalance($("#amount").val())
    })
    let putBalance = (balance) => {
        apiclientLogin.putMoreBalance(localStorage.getItem("username"), balance, (err, res) => {
            if (err) {
                alert("There was an error increasing your balance")
            } else {
                $("#balanceText").text(res)
            }
        })
    }
})