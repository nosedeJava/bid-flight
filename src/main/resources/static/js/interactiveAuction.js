$(document).ready(function () {
    appAuction.showAuctions()
    $("#auctionsButton").on("click",function(){
        $("#list-flights").html("");
        $("#activeAuctionsButton").removeClass("active")
        $("#auctionsButton").addClass("active")
        appAuction.showAuctions()
        return false;
    })
    $("#activeAuctionsButton").on("click",function(){
        $("#list-flights").html("");
        $("#auctionsButton").removeClass("active")
        $("#activeAuctionsButton").addClass("active")
        //hace falta un username que es el man que se logea
        //let username= ?
        appAuction.showActiveAuctions(/*username*/)
        return false;
    })
    $(".particularAuction").click(function(){
        //hace falta redirigir
        alert(this.id)
    })
});