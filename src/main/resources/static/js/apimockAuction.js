var apimockAuction = (function () {
    auctions = []
    auctions.push({
        id: 0,
        ticket: {
            type: "Economic",
            price: 1024,
            bagtype: "Medium",
            flight: 0
        },
        duedate: "12-01-2020 8:12",
        bids: [{
            bidder: {
                username: "Crk_jhon",
                mail: "jhon@arsw.co",
                password: "1234"
            },
            amount: 1024
        },{
            bidder: {
                username: "Crk_jhon",
                mail: "jhon@arsw.co",
                password: "1234"
            },
            amount: 100
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
        duedate: "12-01-2020 8:12",
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
        duedate: "12-01-2020 8:12",
        bids: []
    })
    let getAllAuctions = (filtros, callback) => {
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
        callback(null,"created")
    }
    let getAuction = (id, callback) => {
        callback(null, auctions[id])
    }
    let getAuctionsPerUser = (username, callback) => {
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
        getAuctions: (filtros, callback) => getAllAuctions([],callback),
        getActiveAuctions: (username, callback) => getAuctionsPerUser(username, callback),
        getAuctionByID: (id, callback) => getAuction( id, callback),
        createAuctions: (airline, flight, callback) => createNewAuctions(flight, callback)
    }
})(); 