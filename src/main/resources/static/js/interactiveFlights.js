
$(document).ready(function () {
    if (localStorage.getItem("airline")) {
        appFlights.showAirlineAuctions()
        $("#btnPostFlights").click(function () {
            console.log($("#inputTakeOffDate").val())
        })
    }else{
        window.location.href = "/login-airline.html"
    }

})