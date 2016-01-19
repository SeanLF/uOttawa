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
                <h1>Here are the Canadian Content artists with the most played songs.</h1>
                <a href="songsartists.php?page=songs" class="btn backbutton"><i class="fa fa-arrow-circle-left"></i>Back</a>
                <p>
                <?php 
                $query = "set search_path=project; select A.firstname, A.lastname, S.title, count(*) from song S, songsplayed SP, playsheet P, artist A where 					cancon=true and SP.song = S.songid and SP.playsheet = P.playsheetnum and S.artistid= A.artistid group by A.firstname, A.lastname,S.title having 					count(*) >= all (select count(*)-2 from song S1, songsplayed SP1, playsheet P1, artist A1 where cancon=true and SP1.song = S1.songid and 						SP1.playsheet = P1.playsheetnum and S1.artistid= A1.artistid group by A1.firstname, A1.lastname) order by count(*) desc,A.lastname, A.firstname";
                $result=pg_query($dbconn, $query);
                if(!$result){
                    die("error in sql query: ".pg_last_error());
                }
                if(pg_num_rows($result)>0){
                    echo ("<table><tr><td><strong>Artist name</strong></td><td><strong>Song title</strong></td> <td><strong>Times played</strong></td> 	</tr>");
                    while($row = pg_fetch_array($result)) {
                        echo ("<tr><td>".$row[0]." ".$row[1]."</td><td>".$row[2]."</td><td>".$row[3]."</td></tr>");
                    }
                    echo ("</table>");
                } else {
                    echo("<h1>No CanCon song was ever played?!</h1>");
                } 
		?>
                </p>
                </div>
            </div>            
        </div>
        
    </body>
</html>