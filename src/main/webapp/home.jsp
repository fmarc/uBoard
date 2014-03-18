<%-- 
    Document   : home
    Created on : Mar 2, 2014, 8:17:21 PM
    Author     : Maylem Gonzalez
                 Cory McAn
                 Francisco Marcano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>uBoard</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="icon" type="image/png" href="/favicon.png">
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="styles/master.css">
        <script src="scripts/jquery1.11.0.min.js"></script>
        <script src="scripts/home.js"></script>
    </head>
    <body>
        <div id="container">
        
            <div id="top-banner">
                <div id="top">
                    <div id="search-box">
                        <form>
                            <input type="text" id="content-search" val="" placeholder="Search">
                            <img id="mag-glass" />
                        </form>
                    </div>
                    
                    <a class="logo" title="Take me to the Home Page!" href="/"">
                        <img src="/images/logos/uboard_logo_side.png" />
                    </a>
                    
                    <div id="user-auth">
                        <div id="login">
                            <p>LOG IN</p>
                        </div>
                        <div id="login-modal">
                            <form>
                                <input type="text" id="login-user" class="text-input" placeholder="Username">
                                <span id="login-user-pic"></span>
                                <input type="password" id="login-pass" class="text-input" placeholder="Password">
                                <span id="login-pass-pic"></span>
                                <input id="login-button" class="button" type="submit" value="LOG IN">
                                <input id="register-button" class="button" type="button" value="REGISTER">
                            </form>
                        </div>
                    </div>
                </div>
                <div id="bottom"></div>
            </div>
        
            <div id="content">
                <span class="message" style="padding-top: 100px; padding-bottom: 50px;"> <iframe width="560" height="315" src="//www.youtube.com/embed/QHs_2-pB_6c" frameborder="0" allowfullscreen></iframe><img src="images/logos/uboard_logo.png" alt="images/logos/uboard_logo.png" /></span><span class="message">IN DEVELOPMENT</span> 
            </div>
        </div>
    </body>
</html>