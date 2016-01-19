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
        <title>IRDB | Songs, artists, bands, and albums</title>
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
                <h1>Songs, Artists, Bands, and Albums</h1>
                <p class="content-p">
                <ul>
                    <li><a href="queryf.php?page=songs">Album count per music label</a></li>
                    <li><a href="queryg.php?page=songs">Find which songs were played during...</a></li>
                    <li><a href="queryl.php?page=songs">Most played CanCon artists</a></li>                    
                </ul>
                </p>
            </div>            
        </div>
        
    </body>
</html>