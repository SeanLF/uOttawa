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
                <h1>Find which songs were played during...</h1>
                <a href="songsartists.php?page=songs" class="btn backbutton"><i class="fa fa-arrow-circle-left"></i>Back</a><br>
                <form action="<?= $_SERVER['PHP_SELF'] ?>?page=songs" method="post">
                    Select a month<br>
                    <select name="month">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                    </select><br>
                    Select a year<br>
                    <select name="year">
                        <option value="2014">2014</option>
                        <option value="2013">2013</option>                                                
                    </select><br><br>
                    <button type="submit" name="submit" class="btn btn-info">Show information</button>
                </form>                
                <p>
                <div class="result">
                    <?php 
                if(isset($_POST['submit'])) {
                                    
                    $query = "set search_path=project;
                        select distinct S.title, A.firstname, A.lastname, AL.name, AL.albumrecordingdate from timeslot T, playsheet P, song S, artist A, album AL,songsPlayed SP where T.playsheetnumber = P.playsheetnum and SP.playsheet = P.playsheetnum and S.songid = SP.song and S.artistid=A.artistid and S.albumid=AL.albumid and EXTRACT(MONTH FROM P.date) = ".$_POST['month']." AND EXTRACT(YEAR FROM P.date) = ".$_POST['year']." order by S.title";
                    $result=pg_query($dbconn, $query);
                    if(!$result){
                        die("error in sql query: ".pg_last_error());
                    }
                    if(pg_num_rows($result)>0){
                        echo ("<table>	<tr><td><strong>Title</strong></td>	<td><strong>Artist name</strong></td> <td><strong>Album name</strong></td> <td><strong>Recording date</strong></th>	</tr>");
                        while($row = pg_fetch_array($result)) {
                            echo ("<tr><td>".$row[0]."</td><td>".$row[1]." ".$row[2]."</td><td>".$row[3]."</td><td>".$row[4]."</td></tr>");
                        }
                        echo ("</table>");
                    } else {
                        echo("<h1>None of our songs were played during your selected interval...</h1>");
                    }
                }
		?>
                </div>
                </p>
                </div>
            </div>            
        </div>
        
    </body>
</html>