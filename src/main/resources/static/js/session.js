$(document).ready(function () {
    let contentWithoutLogin="<div class='btn-group' role='group' aria-label='Basic example'> <a href='login.html' class='btn btn-info stretched-link'>Log in</a><a href='register.html' class='btn btn-secondary stretched-link'>Sign up</a></div>"
    let contentWithLogin="<li class='navbar-item'><p style='color: white;font-size:160%;'>$<span id='userbalance'></span> USD - <span class='username'></span></p></li><li class='nav-item dropdown'><a class='nav-link dropdown-toggle' href='#' id='navbarDropdownMenuLink' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>    <i class='material-icons' style='color:white;'>settings</i></a><div class='dropdown-menu dropdown-menu-right dropdown-default' aria-labelledby='navbarDropdownMenuLink-333'> <a class='dropdown-item' href='auctions.html'>Auctions</a> <a class='dropdown-item' href='profile.html'>Profile</a><a class='dropdown-item' href='javascript:void(0)' id='btnLogout'>Logout</a></div></li>"
    $.ajax({
        url: "/accounts/sessionID",
        type: "GET",
        contentType: "application/json",
        success: function (data) {
            if(data.username){
                $("#contentForSession").append(contentWithLogin)
                $("#userbalance").text(data.balance)
                $(".username").text(data.username)
                localStorage.setItem('username',data.username )
                $("#btnLogout").on("click",function(){
                    $.ajax({
                        url: "/perform-logout",
                        type: "POST",
                        success: function (data) {
                            callback(null, "deslogeado")
                        },
                        error: function (data) {
                            callback("error", "Not created")
                        }
                    });
                    window.location.href = "/index.html"
                })

            }else{
                $("#contentForSession").append(contentWithoutLogin)
            }
        },
        error: function (data) {
            console.log(data)
        }
    });
})