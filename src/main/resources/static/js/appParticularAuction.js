var appParticularAuction = (function (persistenceFlights, persistenceAuctions) {
    let bids
    const months = ["Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"]
    const classWinner = "card text-success border-success mb-3 col-lg-9 mx-auto"
    const classNoWinner = "card text-warning border-warning mb-3 col-lg-8 mx-auto"
    const renderInfo = (flight, auction) => {
        let source = flight.source
        let destiny = flight.destiny
        let take_off_date = flight.takeoffdate
        let airline = flight.airline.name
        let flight_type = auction.ticket.type
        let bag_type = auction.ticket.bagtype
        let duration = flight.duration
        let price = auction.ticket.price
        let layover = flight.layovers.length === 0 ? "There is no layover" : "One layover to: " + flight.layovers[0].airport
        let dateCute = take_off_date.split("-")
        take_off_date = dateCute[0] + " " + months[parseInt(dateCute[1], 10) - 1] + " " + dateCute[2]
        dateCute = auction.dueDate.split("-")
        due_date = dateCute[0] + " " + months[parseInt(dateCute[1], 10) - 1] + " " + dateCute[2].split("T")[0]+" "+dateCute[2].split("T")[1].split(":")[0]+":"+dateCute[2].split("T")[1].split(":")[1]
        $('#source').text(source)
        $('#destiny').text(destiny)
        $('#layover').text(layover)
        $('#take-off-date').text(take_off_date)
        $('#flight-type').text(flight_type)
        $('#bag-type').text(bag_type)
        $('#duration').text(duration)
        $('#price').text(price)
        $('#due-date').text(due_date)
        $('#airline').text(airline)
    }
    const renderBids = () => {
        if (bids.length > 0) {
            let winner = bids[0]
            let stringWinner = "<div class='" + classWinner + "' style='border-width:5px;'><div class='card-body text-center p-0'><h2 class='card-text'><b>" + winner.bidder.username + " has bidding " + winner.amount + " dollars for this flight!</b></h2></div></div>"
            $("#listBids").append(stringWinner)
            bids.slice(1, 6).forEach(bid => {
                let string = "<div class='" + classNoWinner + "'><div class='card-body text-center p-0'><h4 class='card-text'>" + bid.bidder.username + " has bidding " + bid.amount + " dollars for this flight</h4></div></div>"
                $("#listBids").append(string)
            });
        }
    }
    let renderAll = () => {
        persistenceAuctions.getAuctionByID(localStorage.getItem('auctionId'), (err, res) => {
            if (err) {
                alert(err)
                window.location.href = "/auction.html"
            } else {
                let auction = res
                console.log("particular auction")
                console.log(res)
                let id = auction.id
                let idFlight = auction.ticket.flight.id
                persistenceFlights.getFlightByID(idFlight, (err, res) => {
                    if (err) {
                        alert(err)
                        window.location.href = "/auction.html"
                    } else {
                        console.log("particular flight of auction")
                        console.log(res)
                        renderInfo(res, auction)
                    }
                });
                bids = auction.bids
                renderBids()
            }
        });
        connect();
    }


    var stompClient = null;
    var topic = "";

    var connect = function () {
        console.info('Connecting to WS...');
        var socket = new SockJS('/bid-flight');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            subscribe(localStorage.getItem('auctionId'));
        }, function(){
            console.log(error);
        });
        
    };
    let updateBids = (bid)=>{
        $("#listBids").children().attr('class',classNoWinner)
        let stringWinner = "<div class='" + classWinner + "' style='border-width:5px;'><div class='card-body text-center p-0'><h2 class='card-text'><b>" + bid.bidder.username + " has bidding " + bid.amount + " dollars for this flight!</b></h2></div></div>"
        $("#listBids").prepend(stringWinner)
        let i=0
        $('#listBids').children('div').each(function () {
            if(i>5) $(this).remove()
            i++
        });
    }
    var subscribe = (id) => {
        //console.log(stompClient)
        topic = '/app/auctions/' + id
        console.log("pulse aca y me subscribi a" + topic)
        stompClient.subscribe(topic, function (eventbody) {
            let bid = JSON.parse(eventbody.body);
            
            updateBids(bid)
        });
    }
    return {
        init: () => renderAll(),

        publishBid: function () {
            amount = $("#amount").val();
            let bid = {
                bidder : {
                    username : $("#user").text()
                },
                amount : amount,
                auction : {
                    id : localStorage.getItem('auctionId')
                }
            };
            console.info("publishing bid "+JSON.stringify(bid));
            console.info('Enviando bid a ' + topic + '...');
            stompClient.send(topic, {}, JSON.stringify(bid));
        },

        disconnect: function () {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }
    };
    
})(apiclientFlights, apiclientAuction);