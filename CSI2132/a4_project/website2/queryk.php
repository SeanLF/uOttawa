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
        <title>IRDB | Guests and Hosts</title>
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
                <h1>Most featured guests</h1>
                <a href="guestshosts.php?page=guests" class="btn backbutton"><i class="fa fa-arrow-circle-left"></i>Back</a>
                <p>
                <?php 
                $query = "set search_path=project;
                        SELECT S.showname, G.firstname, G.lastname, H.firstname, H.lastname, P.date
                        FROM guest G, host H, hostsshow HS, timeslot T, playsheet P, show S
                        WHERE G.rating >= (
                        SELECT MAX(guest.rating) 
                        FROM guest) 
                        AND G.timeslotnumber=T.timeslotnumber AND T.shownumber=HS.shownum AND HS.empnum=H.enumb AND P.playsheetnum=T.playsheetnumber 
                        AND S.shownumber = HS.shownum
                        ORDER BY G.lastname";
                $result=pg_query($dbconn, $query);
                if(!$result){
                    die("error in sql query: ".pg_last_error());
                }
                if(pg_num_rows($result)>0){
                    echo ("<table><tr><th>Show name</th><th>Guest name</th> <th>Host name</th> <th>Date</th>	</tr>");
                    while($row = pg_fetch_array($result)) {
                            echo ("<tr><td>".$row[0]."</td><td>".$row[1]." ".$row[2]."</td><td>".$row[3]." ".$row[4]."</td><td>".$row[5]."</td></tr>");
                    }
                    echo ("</table>");
                } else {
                    echo("<h1>We never had guests?! :(</h1>");
                } 
		?>
                </p>
                </div>
            </div>            
        </div>
        
    </body>
</html>