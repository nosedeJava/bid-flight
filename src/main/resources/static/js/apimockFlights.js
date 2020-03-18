var apimockFlights = (function () {
    var flights = []
    flights.push({
        airline: {
            name: "Avianca"
        },
        takeoffdate: Date(),
        duration: 12,
        source: "Bogota",
        destiny: "Paris",
        layovers: [],
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
        callback(null,flights[id])
    }
    return {
        getFlightByID: (uri, method, id, callback) => getFlight(uri, method, id, callback),
        createFlight: (uri, method, flight, callback) => pushFlight(uri, method, flight, callback),
        getFlights: (uri, method, callback) => getAllFlights(uri, method, callback)
    }

})();