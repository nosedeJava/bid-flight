var apimockAuction = (function () {
    auctions = []
    auctions.push({
        id: 0,
        ticket: {
            type: "Economic",
            price: 123,
            bagtype: "Medium",
            flight: 0
        },
        duedate: Date(),
        bids: [{
            bidder: {
                username: "Crk_jhon",
                mail: "jhon@arsw.co",
                password: "1234"
            },
            amount: 1024
        }]
    })
    auctions.push({
        id: 1,
        ticket: {
            type: "Economic",
            price: 123.12,
            bagtype: "Medium",
            flight: 0
        },
        duedate: Date(),
        bids: [{
            bidder: {
                username: "Crk_jhon",
                mail: "jhon@arsw.co",
                password: "1234"
            },
            amount: 1024
        }]
    })
    auctions.push({
        id: 2,
        ticket: {
            type: "Economic",
            price: 123.12,
            bagtype: "Medium",
            flight: 0
        },
        duedate: Date(),
        bids: []
    })
    let getAllAuctions = (uri, method, callback) => {
        console.log(uri + " " + method)
        callback(null, auctions)
    }
    let createNewAuctions = (flight, callback) => {
        console.log(flight)
        flight.tickets.forEach(ticket => {
            auctions.push({
                ticket: ticket,
                duedate: Date(),
                bids: []
            })
        });
    }
    let bidOnAuctionImpl = (uri, method, idAuction, bid, callback) => {
        console.log(uri + " " + method)
        console.log(idAuction)
        console.log(bid)
        console.log(auctions[idAuction])
        auctions[idAuction].bids.push(bid)
        callback(null, "executed")
    }
    let getAuction = (uri, method, id, callback) => {
        console.log(uri + " " + method)
        console.log(auctions[id])
        callback(null, auctions[id])
    }
    let getAuctionsPerUser = (uri, method, username, callback) => {
        let x = []
        auctions.forEach(auction => {
            auction.bids.forEach(bid => {
                if (bid.bidder.username === username) {
                    x.push(auction)
                }
            });
        });
        callback(null, x)
    }
    return {
        getAuctions: (callback) => getAllAuctions("/auctions", "GET", callback),
        getActiveAuctions: (username, callback) => getAuctionsPerUser("/accounts/"+username+"/auctions", "GET", username, callback),
        getAuctionByID: (id, callback) => getAuction(uri, "GET", id, callback),
        createAuctions: (airline,flight, callback) => createNewAuctions("/airlines/"+airline+"/flights", "POST", flight, callback),
        bidOnAuction: (idAuction, bid, callback) => bidOnAuctionImpl("/auctions/"+idAuction, "PUT", idAuction, bid, callback)
    }
})(); 