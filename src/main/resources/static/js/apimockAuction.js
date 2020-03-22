var apimockAuction = (function () {
    auctions = []
    auctions.push({
        ticket: {
            type: "Economic",
            price: 123,
            bagtype: "Medium",
            flight: 0
        },
        duedate: Date(),
        bids:[{
            bidder: {
                mail: "jhon@arsw.co",
                password: "1234"
            },
            amount: 1024
        }]
    })
    auctions.push({
        ticket: {
            type: "Economic",
            price: 123.12,
            bagtype: "Medium",
            flight: 0
        },
        duedate: Date(),
        bids:[{
            bidder: {
                mail: "jhon@arsw.co",
                password: "1234"
            },
            amount: 1024
        }]
    })
    auctions.push({
        ticket: {
            type: "Economic",
            price: 123.12,
            bagtype: "Medium",
            flight: 0
        },
        duedate: Date(),
        bids:[{
            bidder: {
                mail: "jhon@arsw.co",
                password: "1234"
            },
            amount: 1024
        }]
    })
    let getAllAuctions = (uri, method, callback) => {
        console.log(uri + " " + method)
        callback(null, auctions)
    }
    let createNewAuctions = (flight, callback) => {
        console.log(flight)
        flight.tickets.forEach(ticket => {
            auctions.push({
                ticket:ticket,
                duedate:Date(),
                bids:[]
            })
        });
    }
    let bidOnAuctionImpl = (uri, method, idAuction, bid,callback) => {
        console.log(uri+" "+method)
        console.log(idAuction)
        console.log(bid)
        console.log(auctions[idAuction])
        auctions[idAuction].bids.push(bid)
        callback(null,"executed")
    }
    let getAuction=(uri, method, id, callback)=>{
        console.log(uri+" "+method)
        console.log(auctions[id])
        callback(null,auctions[id])
    }
    return {
        getAuctions: (uri, method, callback) => getAllAuctions(uri, method, callback),
        getAuctionByID: (uri, method, id, callback) => getAuction(uri, method, id, callback),
        createAuctions: (uri, method, flight, callback) => createNewAuctions(uri, method, flight, callback),
        bidOnAuction: (uri, method, idAuction, bid ,callback) => bidOnAuctionImpl(uri, method, idAuction, bid ,callback)
    }
})(); 