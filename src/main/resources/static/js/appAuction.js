var appAuction = (function (persistenceAuction, persistenceFlights) {
    const months = ["Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"]
    let renderAuction = (auction) => {
        let id=auction.id
        let idFlight = auction.ticket.flight
        persistenceFlights.getFlightByID(idFlight, (err, res) => {
            if (err) {
                alert(err)
            } else {
                let flight = res
                let source = flight.source
                let destiny = flight.destiny
                let take_off_date = flight.takeoffdate
                let airline = flight.airline.name
                let flight_type = auction.ticket.type
                let bag_type = auction.ticket.bagtype
                let duration = flight.duration
                let price = auction.ticket.price
                let layover = flight.layovers.length === 0 ? "airplanemode_active" : "flight_takeoff"
                let layoverT = flight.layovers.length === 0 ? "Direct" : flight.layovers.length + " Layovers"
                let dateCute = take_off_date.split("-")
                take_off_date = dateCute[0] + " " + months[parseInt(dateCute[1], 10) - 1] + " " + dateCute[2]
                let string = "<div class='container flight' style='margin-top:20px'><div class='row'><div class='col-md-9 py-2'><div class='card h-100'><div class='card-body'><div class='row'><div class='col-5'><span>" + source + " </span><i class='material-icons' style='color: rgba(0,0,0,0.60)'>arrow_forward</i><span>" + destiny + " </span><i class='material-icons' title='" + layoverT + "' style='color: #8F4213'>" + layover + "</i> <br><br><i class='material-icons' title='Take off date'>schedule</i><span> " + take_off_date + " </span><br></div><div class='col-2'><b>Airline: </b><span>" + airline + "</span></div><div class='col-5'>Flight type: <span>" + flight_type + "</span><i class='material-icons'>people_alt</i><br>Bag type: <span>" + bag_type + "</span><i class='material-icons'>work_outline</i><br>Duration: <span>" + duration + " hours</span><i class='material-icons'>watch_later</i><br></div></div></div></div></div><div class='col-md-3 py-2'><div class='card border-primary h-100'><div class='card-header  border-primary bg-transparent text-primary'>The max bid is at</div><div class='card-body text-primary'><div class='fluid-container'><div class='row'><div class='col-6 pl-3 pt-1'><p class='card-text'>$" + price + " USD</p></div><div class='col-6 px-sm-1'> <button type='button' class='btn btn-outline-primary particularAuction' id='auction-" + id + "'>Let's go bid!</button> </div> </div> </div> </div> </div> </div> </div> </div>"
                $("#list-flights").append(string)
            }
        });

    }
    let getAndTransformAllAuctions = () => {
        persistenceAuction.getAuctions([],(err, res) => {
            if (err) {
                alert(err)
            } else {
                let listAuctions = res
                listAuctions.forEach(auction => {
                    renderAuction(auction)
                });
            }
        })
    }
    let getAndTransformActiveAuctions=(username)=>{
        persistenceAuction.getActiveAuctions(username,(err,res)=>{
            if(err){
                alert("no se pudo traer las subastas activas")
            }else{
                let listAuctions = res
                listAuctions.forEach(auction => {
                    renderAuction(auction)
                });
            }
        })
    }
    return {
        showAuctions: () => getAndTransformAllAuctions(),
        showActiveAuctions: (username) => getAndTransformActiveAuctions(username)
    }


})(apimockAuction, apimockFlights);