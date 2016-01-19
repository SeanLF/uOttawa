<?php
error_reporting(E_ALL);
session_start();
if(!isset($_SESSION['username'])) {
    header("Location: login.php");
}
include('config.php');
if(!isset($_GET['page'])) {
    $_GET['page'] = 'home';
}
?>
<!DOCTYPE HTML>
<html>
    <head>
        <title>IRDB | Modify data</title>
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
                <h1>Modify data</h1>
                <p>
                    <ul>
                        <li><a href="query_addguest.php?page=modify">Add Guest</a></li>
                        <li><a href="query_addhostshow.php?page=modify">Add Host to Show</a></li>
                        <li><a href="query_addsong.php?page=modify">Add song</a></li>
                        <li><a href="query_deleteguest.php?page=modify">Delete Guest</a></li>
                        <li><a href="query_deletehostshow.php?page=modify">Delete host to show</a></li>
                        <li><a href="query_deletesong.php?page=modify">Delete song</a></li>                                                
                    </ul>                    
                </p>
            </div>            
        </div>
        
    </body>
</html>