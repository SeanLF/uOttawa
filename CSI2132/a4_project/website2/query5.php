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
                <h1>Hosts per category</h1>
                <a href="showsplaysheets.php?page=shows" class="btn btn-default backbutton"><i class="fa fa-arrow-circle-left"></i> Back</a>
                <p>
                <form action="<?= $_SERVER['PHP_SELF'] ?>?page=shows" method="post">
                    Select a category to list the host names together with the average rating of the hosts for this category of show.<br><br>
                    Category:<br>
                    <select name="category">
                        <option>Select a category</option>
                        <?php 
                        $show_query = "select distinct category from project.show";
                        $show_query_execute = pg_query($dbconn, $show_query);
                        while($row = pg_fetch_assoc($show_query_execute)) {
                            echo '<option value="'.($row['category']).'">'.$row['category'].'</option>';
                        }
                        ?>
                    </select><br><br>
                    <button type="submit" name="submit" class="btn btn-info">Show information</button>
		</form><br>
                <div class="result">
                    <?php
                    if(isset($_POST['submit'])) {
			$query="set search_path=project;
				Select distinct s.showname, H.firstname, H.lastname, H.rating 
				from show S, timeslot T, playsheet P, host H, hostsshow HS
				where s.shownumber=t.shownumber and t.playsheetnumber = p.playsheetnum 
				and HS.shownum = s.shownumber and HS.empnum = H.enumb and s.category='".$_POST['category']."'";
			$result=pg_query($dbconn, $query);
			if(!$result){
                            die("error in sql query: ".pg_last_error());
			}
			if(pg_num_rows($result)>0){
                            echo "The following are hosts and their shows relating to the \"".$_POST['category']."\" category. <br><br>";
                            
                            while($row = pg_fetch_array($result)) {
                                echo " - <strong>".$row[1]." ".$row[2]."</strong> on the 
                                        \"<strong>".$row[0]."</strong>\" show, has a rating of 
                                        <strong>".$row[3]."</strong> on a scale of 1 to 10.<br>";
                            }  
			} else {
                            echo "There are either no hosts for the \"".$_POST['category']."\" category or the show for that category hasn't aired yet.";
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