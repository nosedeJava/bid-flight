var apiclientFlights = (function () {
    let pushFlight = (airline, flight, callback) => {
        console.log(airline)
        console.log(flight)
        $.ajax({
            url: "/airlines/" + airline + "/flights",
            type: "POST",
            data: JSON.stringify(flight),
            contentType: "application/json",
            success: function (data) {
                callback(null, data)
            },
            error: function (data) {
                callback("error", [])
            }
        });
    }
    let getAllFlights = (airline, callback) => {
        $.ajax({
            url: "/airlines/" + airline + "/flights",
            type: "GET",
            success: function (data) {
                callback(null, data)
            },
            error: function (data) {
                callback("error", [])
            }
        });
    }
    let getFlight = (id, callback) => {
        console.log(id)
        $.ajax({
            url: "/flights/" + id,
            type: "GET",
            success: function (data) {
                callback(null, data)
            },
            error: function (data) {
                callback("error", {})
            }
        });
    }
    return {
        getFlightByID: (id, callback) => getFlight(id, callback),
        createFlight: (airline, flight, callback) => pushFlight(airline, flight, callback),
        getFlights: (airline, callback) => getAllFlights(airline, callback)
    }
})();