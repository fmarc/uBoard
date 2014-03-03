<%-- 
    Document   : home
    Created on : Mar 2, 2014, 8:17:21 PM
    Author     : Franco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="uBoardApp">
    <head ng-controller="uBoardCtrl">
        <title>{{page}} - uBoard</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link href='http://fonts.googleapis.com/css?family=Concert+One' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="styles/master.css">
        <script src="scripts/angular.min.js"></script>
        <script src="scripts/home.js"></script>
    </head>
    <body ng-controller="uBoardCtrl">
        <div id="container">
        
            <div id="banner">
                <div id="top_banner">
                    <img class="logo" src="images/logos/uboard_logo_full.png" alt="images/logos/uboard_logo.png" />
                </div>
                <div id="top_banner_bottom"></div>
            </div>
        
            <div id="content">
                <span class="message" style="padding-top: 100px; padding-bottom: 50px;"><img src="images/logos/uboard_logo.png" alt="images/logos/uboard_logo.png" /></span><span class="message">IN DEVELOPMENT</span> 
            </div>
        </div>
    </body>
</html>