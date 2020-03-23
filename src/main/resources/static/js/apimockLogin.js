var apimockLogin = (function () {
    var airlines = []
    var bidders = []
    airlines["Avianca"] = {
        name: "Avianca",
        phone: "31313131",
        web: "www.avianca.com",
        information: "soy una aerolinea"
    }
    bidders["jhon@arsw.co"] = {
        mail: "jhon@arsw.co",
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
    let incrementBalance=(uri,method,mail,balance,callback)=>{
        console.log(uri + " " + method)
        console.log(mail + " " + balance)
        bidders[mail].balance+=balance 
        callback(null, "incrementado")
    }
    return {
        createAirline: (airline, callback) => createNewAirline("/airlines", "POST", airline, callback),
        createBidder: (bidder, callback) => createNewBidder("/accounts", "POST", bidder, callback),
        loginBidder: (mail, password, callback) => logBidder("login", "POST", mail, password, callback),
        putMoreBalance:(username,mail,balance,callback)=> incrementBalance("/accounts/"+username,"PUT",mail,balance,callback)
    }
})();