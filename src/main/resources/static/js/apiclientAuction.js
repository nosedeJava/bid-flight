var apiclientAuction = (function () {
    let getAllAuctions = (filtros, callback) => {
        console.log(filtros)
        $.ajax({
            url: "/auctions",
            type: "GET",
            success: function(data){ 
                callback(null,data)
            },
            error: function(data) {
                callback("error",[])
            }
        });
    }
    let createNewAuctions = (flight, callback) => {
        console.log(flight)
        $.ajax({
            url: "/auctions",
            type: "POST",
            data: JSON.stringify(flight),
            contentType: "application/json",
            success: function(data){ 
                callback(null,data)
            },
            error: function(data) {
                callback("error",[])
            }
        });
    }
    let getAuction = (id, callback) => {
        console.log(id)
        $.ajax({
            url: "/auctions/"+id,
            type: "GET",
            success: function(data){ 
                callback(null,data)
            },
            error: function(data) {
                callback("error",{})
            }
        });
    }
    let getAuctionsPerUser = (username, callback) => {
        console.log(username)
        $.ajax({
            url: "/accounts/"+username+"/auctions",
            type: "GET",
            success: function(data){ 
                callback(null,data)
            },
            error: function(data) {
                callback("error",[])
            }
        });
    }

    return {
        getAuctions: (filtros, callback) => getAllAuctions(filtros, callback),
        getActiveAuctions: (username, callback) => getAuctionsPerUser( username, callback),
        getAuctionByID: (id, callback) => getAuction(id, callback),
        createAuctions: (airline, flight, callback) => createNewAuctions(flight, callback)
    }
})(); 