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
                    Select a category to list the host names together with the last date that they hosted a related show.<br><br>
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
				Select H.firstname, H.lastname, S.showname, P.date
				from show S, timeslot T, playsheet P, host H, hostsshow HS
				where s.category='".$_POST['category']."' and s.shownumber=t.shownumber and t.playsheetnumber = p.playsheetnum and HS.shownum = s.shownumber 
					and HS.empnum = H.enumb and p.date >= all 
						(select p1.date 
						from show S1, timeslot T1, playsheet P1 
						where s1.category='".$_POST['category']."' and s1.shownumber=t1.shownumber and t1.playsheetnumber = p1.playsheetnum and
						p1.date<'".gmDate("Y/m/d")."') and p.date<'".gmDate("Y/m/d")."';";
			$result=pg_query($dbconn, $query);
			if(!$result){
                            die("error in sql query: ".pg_last_error());
			}
			if(pg_num_rows($result)>0){
                            echo "The last time a show with the \"".$_POST['category']."\" category aired, it was hosted by <br>";
                            while($row = pg_fetch_array($result)) {
                                echo " - ".$row[0]." ".$row[1]." on the \"".$row[2]."\" show, the ".$row[3].".<br>";
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