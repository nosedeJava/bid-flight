var apiclientLogin = (function () {
    let createNewAirline = (airline, callback) => {
        console.log(airline)
        $.ajax({
            url: "/airlines",
            type: "POST",
            data: JSON.stringify(airline),
            contentType: "application/json",
            success: function (data) {
                callback(null, "Created")
            },
            error: function (data) {
                callback("error", "Not created")
            }
        });
    }
    let createNewBidder = (bidder, callback) => {
        console.log(bidder)
        $.ajax({
            url: "/accounts",
            type: "POST",
            data: JSON.stringify(bidder),
            contentType: "application/json",
            success: function (data) {
                callback(null, "created")
            },
            error: function (data) {
                callback("error", "not created")
            }
        });
    }
    let incrementBalance = (username, balance, callback) => {
        console.log(username + " " + balance)
        $.ajax({
            url: "/accounts/"+username,
            type: "PUT",
            data: JSON.stringify({balance:balance}),
            contentType: "application/json",
            success: function (data) {
                callback(null, "modified")
            },
            error: function (data) {
                callback("error", "no modified")
            }
        });
    }
    return {
        createAirline: (airline, callback) => createNewAirline("/airlines", "POST", airline, callback),
        createBidder: (bidder, callback) => createNewBidder("/accounts", "POST", bidder, callback),
        putMoreBalance: (username, balance, callback) => incrementBalance(username, balance, callback)
    }
})();