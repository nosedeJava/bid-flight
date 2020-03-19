$(document).ready(function () {
    $("#auctionsButton").on("click",function(){
        alert("auctionsButton")
        return false;
    })
    $("#activeAuctionsButton").on("click",function(){
        alert("activeAuctionsButton")
        return false;
    })
    $(".particularAuction").click(function(){
        alert(this.id)
    })
});