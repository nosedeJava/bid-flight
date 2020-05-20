$(document).ready(function () {

    $("#btnSignUp").click(function(){
        let airline={
            name:$("#name").val(),
            phone:$("#phone").val(),
            web:$("#web").val(),
            information:$("#info").val()
        };
        apiclientLogin.createAirline(airline,(err,res)=>{
            if(err){
                alert(err);
            }else{
                alert("Your account has been created succesfully, we will contact and provide you a password");
                window.location.href="/index.html";
            }
        });
    });
});