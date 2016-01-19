<?php 
	session_start();
	include('config.php');
?>
<!DOCTYPE html><html>
	<head>
    	<title>Team Assignment</title>
	</head>
	<body>
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
			echo ('<br><br><a href="query5.php">Perform another seach.</a> ');
			pg_free_result($result);
			pg_close($dbconn);
		} else {
		?>  
		<form action="<?= $_SERVER['PHP_SELF'] ?>" method="post">
			<h4>Select a category to list the host names together with the average rating of the hosts for this category of show.</h4>
			Category:
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
			<input type="submit" name="submit">
		</form>
		<?php
		}	echo ('<br><br><a href="web.php">Home Page.</a> ');
		?>
	</body>
</html>