var appAuction = (function (persistenceAuction, persistenceFlights) {
    let renderAuction = (auction) => {
        persistenceFlights.getFlightByID("flights/" + auction.ticket.flight, "GET", auction.ticket.flight, (err, res) => {
            if (err) {
                alert(err)
            } else {
                let flight = res
                let string = "<div class='container flight' style='margin-top:20px'><div class='row'><div class='col-md-9 py-2'><div class='card h-100'><div class='card-body'><div class='row'><div class='col-5'><span>"+ auction.source + "</span><i class='material-icons' style='color: rgba(0,0,0,0.60)'>arrow_forward</i><span>"+flight.destiny+"</span><i class='material-icons' title='Direct' style='color: #8F4213'>airplanemode_active</i> <br><i class='material-icons' title='Take off date'>schedule</i><span> "+flight.takeoffdate+ "</span><br></div><div class='col-2'><b>Airline:</b><span>"+flight.airline.name+"</span></div><div class='col-5'>Flight type:<span>"+auction.ticket.type+"</span><i class='material-icons'>people_alt</i><br>Bag type:<span> "+auction.ticket.bagtype+" </span><i class='material-icons'>work_outline</i><br>Duration:<span>"+flight.duration+" hours</span><i class='material-icons'>watch_later</i><br></div></div></div></div></div><div class='col-md-3 py-2'><div class='card border-primary h-100'><div class='card-header  border-primary bg-transparent text-primary'>The max bid is at</div><div class='card-body text-primary'><div class='fluid-container'><div class='row'><div class='col-sm-auto pt-2'><p class='card-text'>$"+auction.ticket.price+" USD</p></div><div class='col-sm-auto'><button type='button' class='btn btn-outline-primary particularAuction' id='auction-0'>Let's go bid!</button></div></div></div></div></div></div></div></div>"
                $("#list-flights").append(string)
            }
        });

    }
    let getAndTransformAllAuctions=()=>{
        persistenceAuction.getAuctions("/auctions","GET",(err,res)=>{
            if(err){
                alert(err)
            }else{
                let listAuctions=res
                listAuctions.forEach(auction => {
                    renderAuction(auction)
                });
            }
        })
    }
    return{
        showAuctions:()=>getAndTransformAllAuctions()
    }


})(apimockAuction, apimockFlights);