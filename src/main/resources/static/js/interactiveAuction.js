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
        appAuction.showActiveAuctions(localStorage.getItem('username'))
        return false;
    })
    $(".particularAuction").click(function(){
        localStorage.setItem('auctionId', this.id.split("-")[1])
        window.location.href="/particular-auction.html"
    })
    
});