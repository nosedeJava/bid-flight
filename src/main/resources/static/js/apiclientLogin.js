var apiclientLogin = (function () {
    let createNewAirline = (airline, callback) => {
        $.ajax({
            url: "/airlines",
            type: "POST",
            data: JSON.stringify(airline),
            contentType: "application/json",
            success: function (data) {
                callback(null, "Created");
            },
            error: function (data) {
                callback("error", "Not created");
            }
        });
    }
    let createNewBidder = (bidder, callback) => {
        $.ajax({
            url: "/accounts",
            type: "POST",
            data: JSON.stringify(bidder),
            contentType: "application/json",
            success: function (data) {
                callback(null, "created");
            },
            error: function (data) {
                callback("error", "not created");
            }
        });
    }
    let incrementBalance = (username, balance, callback) => {
        $.ajax({
            url: "/accounts/" + username,
            type: "PUT",
            data: JSON.stringify(balance),
            contentType: "application/json",
            success: function (data) {
                callback(null, data);
            },
            error: function (data) {
                callback("error", "no modified");
            }
        });
    }
    return {
        createAirline: (airline, callback) => createNewAirline(airline, callback),
        createBidder: (bidder, callback) => createNewBidder(bidder, callback),
        putMoreBalance: (username, balance, callback) => incrementBalance(username, balance, callback)
    }
})();