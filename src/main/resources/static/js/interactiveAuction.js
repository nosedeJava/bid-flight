$(document).ready(function () {
    appAuction.showAuctions();
    $("#auctionsButton").on("click",function(){
        $("#searching").html("");
        $("#list-flights").html("");
        $("#activeAuctionsButton").removeClass("active");
        $("#auctionsButton").addClass("active");
        appAuction.showAuctions();
        return false;
    })
    $("#activeAuctionsButton").on("click",function(){
        $("#searching").html("");
        $("#list-flights").html("");
        $("#auctionsButton").removeClass("active");
        $("#activeAuctionsButton").addClass("active");
        console.log(localStorage.getItem('username'));
        appAuction.showActiveAuctions(localStorage.getItem('username'));
        return false;
    })
});