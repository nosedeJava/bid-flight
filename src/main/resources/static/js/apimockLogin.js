var apimockLogin = (function () {
    var airlines = []
    var bidders = []
    airlines["Avianca"] = {
        name: "Avianca",
        phone: "31313131",
        web: "www.avianca.com",
        information: "soy una aerolinea"
    }
    bidders["jhon@es.hp"] = {
        mail: "jhon@es.hp",
        password: "1234",
        names: "Jhon",
        lastnames: "Apellido",
        username: "Crk_jhon",
        documenttype: "CC",
        document: "1345314664",
        balance: "1021",
        payments: [],
        bids: [{
            amount:1024
        }]
    }
    let createNewAirline = (uri, method, airline, callback) => {
        console.log(uri + " " + method)
        console.log(airline)
        airlines.push(airline)
        callback(null, "created")
    }
    let createNewBidder = (uri, method, bidder, callback) => {
        console.log(uri + " " + method)
        console.log(bidder)
        bidders.push(bidder)
        callback(null, "created")
    }
    let logBidder = (uri, method, mail, password, callback) => {
        console.log(uri + " " + method)
        console.log(mail + " " + password)
        if (bidders[mail] === undefined) {
            callback("ese usuario no existe", null)
        } else {
            if (bidders[mail].password === password) {
                callback(null, "tokenDe" + mail)
            } else {
                callback("contraseña incorrecta", null)
            }
        }
    }
    return {
        createAirline: (uri, method, airline, callback) => createNewAirline(uri, method, airline, callback),
        createBidder: (uri, method, bidder, callback) => createNewBidder(uri, method, bidder, callback),
        loginBidder: (uri, method, mail, password, callback) => logBidder(uri, method, mail, password, callback)
    }
})();