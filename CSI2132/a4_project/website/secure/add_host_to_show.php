<?php 
	include('config.php');
	error_reporting(E_ALL);	
	session_start();
	if(!isset($_SESSION['username'])){
		header('Location: ../');
		exit();
	}
?>
<!DOCTYPE html><html>
	<head>
    	<title>Team Assignment</title>
	</head>
	<body>	
		<?php
			if(isset($_POST['submit'])) {
				$query="INSERT INTO project.hostsshow VALUES(".
						$_POST['hostid'].", ".$_POST['hostid'].", ".
						$_POST['showid'].", ".
						date("Y").", ".date('m').",'".$_SESSION['username']."');";
				$result=pg_query($dbconn, $query);
				if(!$result){
					if(strpos(pg_last_error(),'duplicate key value violates unique constraint "hostshow_pk"') !== false)
						echo "<h1>Error: can not add duplicate showhost to show.</h1><br>
						<a href='add_host_to_show.php'>Insert another host.</a><br><a href='index.php'>Take me back home.</a>";
					else die("error in sql query: ".pg_last_error());
				} else {
					echo ("Data Successfully Entered! "."<br>
						<a href='add_host_to_show.php'>Insert another host.</a><br>
						<a href='index.php'>Take me back home.</a>");
					pg_free_result($result);
					pg_close($dbconn); 
				}
			}
			else {
		?>  
		<h1>Add a showhost to a show!</h1>
	    <form action="<?= $_SERVER['PHP_SELF'] ?>" method="post">
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
			</select><br><br><br><br>        
			<input type="submit" name="submit"><br><br>
			<a href='index.php'>Don't add a host</a>
		</form>
		<?php
			}
		?>
	</body>
</html>