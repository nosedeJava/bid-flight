$(document).ready(function () {
    appParticularAuction.init();
    $("#btnBid").click(function(){
        console.log("ME HICIERON CLIC CHAVALADA - "+$("#amount").val());
        //appParticularAuction.publishBid($("#amount").val());
    });
});