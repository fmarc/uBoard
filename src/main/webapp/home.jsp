<%-- 
    Document   : home
    Created on : Mar 2, 2014, 8:17:21 PM
    Author     : Maylem Gonzalez
                 Cory McAn
                 Francisco Marcano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="uBoardApp">
    <head ng-controller="uBoardCtrl">
        <title>{{page}} - uBoard</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="icon" type="image/png" href="/favicon.png">
<<<<<<< HEAD
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
=======
        <link href='http://fonts.googleapis.com/css?family=Concert+One' rel='stylesheet' type='text/css'>
>>>>>>> e19efb47ed8f8151759131ea23c58ee723d3d877
        <link rel="stylesheet" type="text/css" href="styles/master.css">
        <script src="scripts/angular.min.js"></script>
        <script src="scripts/home.js"></script>
    </head>
    <body ng-controller="uBoardCtrl">
        <div id="container">
        
            <div id="top-banner">
                <div id="top">
                    <div id="search-box">
                        <form>
                            <input type="text" id="content-search" val="" placeholder="Search...">
                            <img id="mag-glass" />
                        </form>
                    </div>
                    
                    <a class="logo" title="Take me to the Home Page!" href="/"">
                        <img src="/images/logos/uboard_logo_side.png" />
                    </a>
                    
                    <div id="user-auth">
                        <div id="login">
                            <p>LOG IN</p>
                            <div></div>
                        </div>
                    </div>
                </div>
                <div id="bottom"></div>
            </div>
        
            <div id="content">
                <span class="message" style="padding-top: 100px; padding-bottom: 50px;"><img src="images/logos/uboard_logo.png" alt="images/logos/uboard_logo.png" /></span><span class="message">IN DEVELOPMENT</span> 
            </div>
        </div>
    </body>
</html>