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
                <h1>Album count per music label</h1>
                <a href="songsartists.php?page=songs" class="btn backbutton"><i class="fa fa-arrow-circle-left"></i>Back</a>
                <p>
                <?php 
                $query = "set search_path=project;
                        select A.label,  count(A.label) as count
                        from album A, song S
                        where S.albumid = A.albumid
                        group by A.label
                        order by count desc, A.label";
                $result=pg_query($dbconn, $query);
                if(!$result){
                    die("error in sql query: ".pg_last_error());
                }
                if(pg_num_rows($result)>0){
                    echo ("<table>	<tr><td><strong>Label Name</strong></td>	<td><strong>Album Count<strong></td>	</tr>");
                    while($row = pg_fetch_array($result)) {
                        echo ("<tr><td>".$row[0]."</td><td>".$row[1]."</td></tr>");                        
                    }
                    echo ("</table>");
                } else {
                    echo("<h1>Sorry but we don't have any content...</h1>");
                } 
		?>
                </p>
                </div>
            </div>            
        </div>
        
    </body>
</html>