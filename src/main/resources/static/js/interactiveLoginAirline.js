$(document).ready(function() {
    let airlineInfo = () => {
        localStorage.setItem('airline', $("#nameAirline").val());
        window.location.href = "/flights.html";
    }
    $("#btnLogin").click(function() {
        apiclientFlights.getAirline($("#nameAirline").val(),(err,res) =>{
            if(err){
                alert("Airline doesn't exist");
            }else{
                airlineInfo($("#nameAirline").val());
            }
        })
    })
})