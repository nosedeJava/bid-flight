var apiclientAuction = (function () {
    let getAllAuctions = (filtros, callback) => {
        $.ajax({
            url: "/auctions"+filtros,
            type: "GET",
            success: function(data){
                console.log(data)
                callback(null,data)
            },
            error: function(data) {
                callback("error al traer todos los auction",[])
            }
        });
    }
    let getAuction = (id, callback) => {
        $.ajax({
            url: "/auctions/"+id,
            type: "GET",
            success: function(data){ 
                callback(null,data)
            },
            error: function(data) {
                callback("error al conseguir un auction en especifico",{})
            }
        });
    }
    let getAuctionsPerUser = (username, callback) => {
        $.ajax({
            url: "/accounts/"+username+"/auctions",
            type: "GET",
            success: function(data){ 
                callback(null,data)
            },
            error: function(data) {
                callback("error al conseguir las subastas activas",[])
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