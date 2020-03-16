var apimockLogin=(function(){
    var airlines=[]
    var bidders=[]
    airlines["Avianca"]={
        name:"Avianca",
        phone:"31313131",
        web:"www.avianca.com",
        information:"soy una aerolinea"
    }
    bidders["Jhon"]={
        mail:"jhon@es.hp",
        password:"1234",
        names:"Jhon",
        lastnames:"Apellido",
        username:"Crk_jhon",
        documenttype:"CC",
        document:"1345314664",
        balance:"1021",
        payments:[],
        bids:[]
    }
    let createNewAirline=(uri,method,airline,callback)=>{

    }
    let createNewBidder=(uri,method,bidder,callback)=>{

    }
    let logBidder=(uri,method,name,password,callback)=>{

    }
    return{
        createAirline:(uri,method,airline,callback)=>createNewAirline(uri,method,airline,callback),
        createBidder:(uri,method,bidder,callback)=>createNewBidder(uri,method,bidder,callback),
        loginBidder:(uri,method,name,password,callback)=>logBidder(uri,method,name,password,callback)
    }
})();