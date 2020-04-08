$(document).ready(function () {

    $("#btnSignUp").click(function(){
        let bidder={
            mail:$("#names").val(),
            password:$("#pass").val(),
            names:$("#names").val(),
            lastnames:$("#lastnames").val(),
            username:$("#username").val(),
            documenttype:"C.C",
            document:$("#doc").val()
        }
        apiclientLogin.createBidder(bidder,(err,res)=>{
            if(err){
                alert(err)
            }else{
                alert("Your account has been created succesfully")
                window.location.href="/login.html"
            }
        })
    })
});