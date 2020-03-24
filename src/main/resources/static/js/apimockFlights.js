
var apimockFlights = (function () {
    var flights = []
    flights.push({
        airline: {
            name: "Avianca"
        },
        takeoffdate: "12-01-2020 18:12",
        duration: 12,
        source: "Bogota, Colombia",
        destiny: "Paris, France",
        layovers: [{
            airport:"El dorado, BOG-COL"
        }],
        tickets: [{
            type: "Economic",
            price: 123.12,
            bagtype: "Medium",
            flight: 0
        }]
    })
    let pushFlight = (uri, method, flight, callback) => {
        console.log(uri + " " + method)
        flights.push(flight)
        callback(null, "created")
    }
    let getAllFlights = (uri, method, callback) => {
        console.log(uri + " " + method)
        callback(null, flights)
    }
    let getFlight = (uri, method, id, callback) => {
        console.log(uri + " " + method)
        console.log(id)
        console.log(flights[id])
        callback(null, flights[id])
    }
    return {
        getFlightByID: (id, callback) => getFlight("/flights/" + id, "GET", id, callback),
        createFlight: (airline, flight, callback) => pushFlight("/airlines/" + airline + "/flights", "POST", flight, callback),
        getFlights: (callback) => getAllFlights("/airlines/" + airline + "/flights", "GET", callback)
    }

})();