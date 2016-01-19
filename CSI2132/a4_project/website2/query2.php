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
                <h1>What's playing on what?</h1>
                <a href="showsplaysheets.php?page=shows" class="btn btn-default backbutton"><i class="fa fa-arrow-circle-left"></i> Back</a>
                <p>
                <form action="<?= $_SERVER['PHP_SELF'] ?>?page=shows" method="post">       
                    Day:
                    <select name="day">
                        <option>Select a day</option>
                        <option value="Monday">Monday</option><option value="Tuesday">Tuesday</option><option value="Wednesday">Wednesday</option>
                        <option value="Thursday">Thursday</option><option value="Friday">Friday</option><option value="Saturday">Saturday</option>
                        <option value="Sunday">Sunday</option>
                    </select><br>
                    Show:
                    <select name="show">
                        <option>Select a show</option>
                        <?php 
                        $show_query = "select showname from project.show";
                        $show_query_execute = pg_query($dbconn, $show_query);
                        while($row = pg_fetch_assoc($show_query_execute)) {
                            echo '<option value="'.($row['showname']).'">'.$row['showname'].'</option>';
                        }
                        ?>
                    </select><br><br>
                    <button type="submit" name="submit" class="btn btn-info">Get information</button>
                </form><br>
                <div class="result">
                    <?php
                    if(isset($_POST['submit'])) {
			$query="set search_path=project;Select S.title, A.firstname, A.lastname, AL.name
				from song S, songsplayed SP, playsheet P, timeslot T, show SH, artist A, album AL
				where P.playsheetnum = T.playsheetnumber and SP.playsheet = P.playsheetnum and SP.song = S.songid and 
	   			SH.shownumber = T.shownumber and S.artistid=A.artistid and AL.albumid=S.albumid and P.dayofweek='"
				. $_POST['day']. "' and SH.showname='".$_POST['show']."'order by a.lastname,a.firstname,al.name,s.title";
			$result=pg_query($dbconn, $query);
			if(!$result){
                            die("error in sql query: ".pg_last_error());
			}
			if(pg_num_rows($result)>0){
                            echo "<h4>Songs the ".$_POST['show']." show played on ".$_POST['day'].".</h4><br>";
                            echo ("<table><tr><td width='33%'><strong>Song Title</strong></th><td width='33%'><strong>Artist Name</strong></td><td width='33%'><strong>Album Title</strong></td></tr>");
                            while($row = pg_fetch_array($result)) {
                                echo ("<tr><td>".$row[0]."</td><td>".$row[1]." ".$row[2]."</td><td>".$row[3]."</td></tr>");
                            }
                            echo("</table>");  
			} 
                        else{
                            echo "The \"".$_POST['show']."\" show didn't play any songs on ".$_POST['day'].".";
			}                            
                            
			pg_free_result($result);
			pg_close($dbconn);
		}
                    ?>
                </p>
                </div>
            </div>            
        </div>
        
    </body>
</html>