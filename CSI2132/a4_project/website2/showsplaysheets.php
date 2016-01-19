<?php
error_reporting(E_ALL);
session_start();
if(!isset($_SESSION['username'])) {
    header("Location: login.php");
}
if(!isset($_GET['page'])) {
    $_GET['page'] = 'home';
}
?>
<!DOCTYPE HTML>
<html>
    <head>
        <title>IRDB | Shows and Playsheet</title>
        <link href="bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="style.css" rel="stylesheet" type="text/css">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    </head>
    <body>
        
        <div class="header-div">
            <font class="header-bigtitle">
                Internet Radio Database
            </font><br>
            <font class="header-subtitle">
                <i>Databases I, CSI2132</i>
            </font>
        </div>
        
        <div class="content-container">
            <div class="content-left">
                <?php include('menu.php'); ?>
            </div>
            <div class="content-right">
                <h1>Shows and playsheets</h1>
                <p class="content-p">
                <ul>
                    <li><a href="query1.php?page=shows">Get show information</a></li>
                    <li><a href="query2.php?page=shows">What's playing on what?</a></li>
                    <li><a href="query3.php?page=shows">Hosts per category</a></li>
                    <li><a href="query5.php?page=shows">Average host rating per category</a></li>
                </ul>
                </p>
            </div>            
        </div>
        
    </body>
</html>