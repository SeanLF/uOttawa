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
        <title>IRDB | Modify Data</title>
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
                <h1>Add Host to Show</h1>
                <a href="modify.php?page=modify" class="btn backbutton"><i class="fa fa-arrow-circle-left"></i>Back</a>
                <p>
                 <form action="<?= $_SERVER['PHP_SELF'] ?>?page=modify" method="post">
			Show <br>
			<select name="showid">
				<option>Select an show</option>
				<?php 
					$show_query = " SELECT DISTINCT S.shownumber, S.showname FROM project.show S";
					$show_query_execute = pg_query($dbconn, $show_query);
					while($row = pg_fetch_assoc($show_query_execute)) {
						echo '<option value="'.($row['shownumber']).'">'.$row['showname'].'</option>';
					}
				?>
			</select><br>
	        Host <br>
			<select name="hostid">
				<option>Select a host</option>
				<?php 
					$show_query = "SELECT H.enumb, H.firstname, H.lastname FROM project.host H;";
					$show_query_execute = pg_query($dbconn, $show_query);
					while($row = pg_fetch_assoc($show_query_execute)) {
						echo '<option value="'.($row['enumb']).'">'.$row['firstname']." ".$row['lastname'].'</option>';
					}
				?>
			</select><br><br>      
                        <button type="submit" name="submit" class="btn btn-info">Add host</button><br><br>
                        </form>
                
                <div class="result">
                    <?php
                    if(isset($_POST['submit'])) {
				$query="INSERT INTO project.hostsshow VALUES(".
						$_POST['hostid'].", ".$_POST['hostid'].", ".
						$_POST['showid'].", ".
						date("Y").", ".date('m').",'irdb');";
				$result=pg_query($dbconn, $query);
				if(!$result){
					if(strpos(pg_last_error(),'duplicate key value violates unique constraint "hostshow_pk"') !== false)
						echo "<h1>Error: can not add duplicate showhost to show.</h1><br>";						
					else die("error in sql query: ".pg_last_error());
				} else {
					echo ("Data Successfully Entered!");
					pg_free_result($result);
					pg_close($dbconn); 
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