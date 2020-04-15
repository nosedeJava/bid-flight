var appAuction = (function (persistenceAuction, persistenceFlights) {
    const months = ["Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"]
    let renderAuction = (auction) => {
        let id = auction.id
        let idFlight = auction.ticket.flight.id
        console.log("render auction")
        console.log(auction)
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
                $(".particularAuction").click(function () {
                    localStorage.setItem('auctionId', this.id.split("-")[1])
                    window.location.href = "/particular-auction.html"
                })
            }
        });

    }
    let renderSearch = () => {
        let strSearch = "<div class='container-fluid col-lg-10 mx-auto p-4 without-corner-div' id='search' ><div class='row'>    <div class='col-10' style='border-right: 3px solid rgb(41, 128, 185);'><div class='row'><label for='source' class='col-sm-1 col-form-label'>From:</label><div class='col-sm-5'>    <input type='text' class='form-control' id='source'></div><label for='destiny' class='col-sm-1 col-form-label'>To:</label><div class='col-sm-5'>    <input type='text' class='form-control' id='destiny'></div></div><div class='row' style='margin-top:1rem;'><div class='col'>    <label for='inputFlightType'>Flight class:</label>    <select id='inputFlightType' class='form-control'><option>...</option><option>Economy class</option><option>Bussiness class</option><option>First class</option>    </select></div><div class='col'>    <label for='inputBagType'>Bag type:</label>    <select id='inputBagType' class='form-control'><option>...</option><option>Backpack</option><option>Hand luggage</option><option>Checked baggage</option>    </select></div><div class='col'>    <label for='inputOrder'>Order by:</label>    <select id='inputOrder' class='form-control'><option>...</option><option>Price</option><option>Duration</option><option>Take off date</option>    </select></div></div>    </div>    <div class='col-2' style='padding-top: 2rem;'><button type='button' class='btn btn-primary btn-block' id='btnSearch' style='font-size: 20px;'><i class='material-icons' >near_me</i></button>    </div></div></div>"
        $("#searching").append(strSearch)
        $("#btnSearch").click(function () {
            let query = "?"
            let source = $("#source").val()
            let destiny = $("#destiny").val()
            let inputOrder = $("#inputOrder option:selected").text();
            let inputBagType = $("#inputBagType option:selected").text();
            let inputFlightType = $("#inputFlightType option:selected").text();
            if (!source && !destiny && inputOrder === "..." && inputBagType === "..." && inputFlightType === "...") alert("There is no options setted")
            if(source) query=query+"source="+source+"&&"
            if(destiny) query=query+"destiny="+destiny+"&&"
            if(inputBagType!=="...") query=query+"bagtype="+inputBagType+"&&"
            if(inputFlightType!=="...")query=query+"flighttype="+inputFlightType+"&&"
            if(inputOrder!=="...") query=query+"orderby="+inputOrder+"&&"
            getAndTransformAllAuctions(query)
        })
    }

    let getAndTransformAllAuctions = (filters) => {
        $("#list-flights").html("");
        persistenceAuction.getAuctions(filters, (err, res) => {
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
    let getAndTransformActiveAuctions = (username) => {
        persistenceAuction.getActiveAuctions(username, (err, res) => {
            if (err) {
                alert("no se pudo traer las subastas activas")
            } else {
                let listAuctions = res
                listAuctions.forEach(auction => {
                    renderAuction(auction)
                });
            }
        })
    }
    return {
        showAuctions: () => {
            renderSearch()
            getAndTransformAllAuctions("")
        },
        showActiveAuctions: (username) => getAndTransformActiveAuctions(username)
    }


})(apiclientAuction, apiclientFlights);