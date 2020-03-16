var apimockFlights= (function () {
    var flights=[]
    flights.push({
        airline:{
            name: "Avianca"
        },
        takeoffdate:Date(),
        duration:12,
        source:"Bogota",
        destiny:"Paris",
        layovers:[],
        tickets:[{
            type:"Economic",
            price:123.12,
            bagtype:"Medium"
        }]
    })
    pushFlight =(uri,method,flight,callback)=>{
        console.log(uri+" "+method)
        flights.push(flight)
        callback(null,"created")
    }
    getAllFlights=(uri,method,callback)=>{
        console.log(uri+" "+method)
        callback(null,flights)
    }
    return{
        createFlight:(uri,method,flight,callback)=>pushFlight(uri,method,flight,callback),
        getFlights:(uri,method,callback)=>getAllFlights(uri,method,callback)
    }

})();