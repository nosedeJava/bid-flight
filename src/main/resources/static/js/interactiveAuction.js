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
        appAuction.showActiveAuctions()
        return false;
    })
    $(".particularAuction").click(function(){
        alert(this.id)
    })
});