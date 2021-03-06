var apiclientFlights = (function () {
    let pushFlight = (airline, flight, callback) => {
        $.ajax({
            url: "/airlines/" + airline + "/flights",
            type: "POST",
            data: JSON.stringify(flight),
            contentType: "application/json",
            success: function (data) {
                callback(null, data);
            },
            error: function (data) {
                callback("error al crear vuelos", []);
            }
        });
    };
    let getAllFlights = (airline, callback) => {
        $.ajax({
            url: "/airlines/" + airline + "/auctions",
            type: "GET",
            success: function (data) {
                callback(null, data);
            },
            error: function (data) {
                callback("error al conseguir todos los vuelos", []);
            }
        });
    };
    let getFlight = (id, callback) => {
        $.ajax({
            url: "/flights/" + id,
            type: "GET",
            success: function (data) {
                callback(null, data);
            },
            error: function (data) {
                callback("error al conseguir un vuelo en particular", {});
            }
        });
    };
    let getAirlineInfo = (airline, callback) => {
        $.ajax({
            url: "/airlines/" + airline,
            type: "GET",
            success: function (data) {
                callback(null, data);
            },
            error: function (data) {
                callback("error al conseguir un vuelo en particular", {});
            }
        });
    };
    return {
        getFlightByID: (id, callback) => getFlight(id, callback),
        createFlight: (airline, flight, callback) => pushFlight(airline, flight, callback),
        getFlights: (airline, callback) => getAllFlights(airline, callback),
        getAirline: (airline, callback) => getAirlineInfo(airline, callback)
    }
})();