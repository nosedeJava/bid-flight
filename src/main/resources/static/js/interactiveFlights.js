
$(document).ready(function () {
    if (localStorage.getItem("airline")) {
        appFlights.showAirlineAuctions();
        $("#btnPostFlights").click(function () {
            //YYYY-MM-DDTHH:MM
            let formatTOD = document.getElementById("inputTakeOffDate").value;
            let inputBagType = $("#inputBagType option:selected").text();
            let inputFlightType = $("#inputFlightType option:selected").text();
            let source = $("#source").val();
            let destiny = $("#destiny").val();
            let duration = $("#inputDuration").val();
            let cantTickets = $("#inputTickets").val();
            let price = $("#inputPrice").val();
            if (formatTOD === "" || !inputBagType || !inputFlightType || !source || !destiny || !duration || !cantTickets || !price) {
                alert("There are missing values");
            } else {
                // transform date
                let datePlusHours = formatTOD.split("T");
                let dateTOD = datePlusHours[0].split("-");
                let correctTOD = dateTOD.join("-") + " " + datePlusHours[1];
                let intTickets = parseInt(cantTickets, 10);
                let flight = {
                    airline: {
                        name: localStorage.getItem("airline")
                    },
                    takeoffdate: correctTOD,
                    duration: duration,
                    source: source,
                    destiny: destiny,
                    layovers: [],
                    tickets: []
                };
                if ($("#inputLayover").val()) {
                    flight.layovers.push({
                        airport: $("#inputLayover").val()
                    });
                }
                for (let i = 0; i < intTickets; i++) {
                    flight.tickets.push({
                        type: inputFlightType,
                        price: price,
                        bagtype: inputBagType
                    });
                }
                apiclientFlights.createFlight(localStorage.getItem("airline"), flight, (err, res) => {
                    if (err) {
                        alert("There was an error posting your flight");
                    } else {
                        location.reload();
                    }
                });
            }
        });
    } else {
        window.location.href = "/login-airline.html";
    }
})