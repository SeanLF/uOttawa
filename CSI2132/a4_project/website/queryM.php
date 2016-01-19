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
				$query = "set search_path=project; 
					SELECT H.firstname, H.lastname, H.salary
					FROM host H
					WHERE salary > (
					SELECT H2.salary
					FROM host H2
					WHERE H2.stagename = '".$_POST['host']."' OR H2.firstname = '".$_POST['host']."')";
				$result=pg_query($dbconn, $query);
				if(!$result){
					die("error in sql query: ".pg_last_error());
				}
				if(pg_num_rows($result)>0){
					echo ("<table><tr><th>Host name</th><th>Salary</th></tr>");
					while($row = pg_fetch_array($result)) {
						echo ("<tr><td>".$row[0]." ".$row[1]."</td><td>".$row[2]."</td></tr>");
					}
					echo ("</table>");
				} else {
					echo("<h1>Selected host has the highest salary?!</h1>");
				}
				echo ('<br><br><a href="queryM.php">Perform another seach.</a> ');
				pg_free_result($result);
				pg_close($dbconn);
			} else { 
		?>
	
		<form action="<?= $_SERVER['PHP_SELF'] ?>" method="post">
			<h3>Hosts with salaries greater or equal than:</h3>
			<select name="host">
				<option>Select a host</option>
				<?php 
				$show_query = "select distinct host.firstname, host.lastname from project.host order by host.lastname ";
				$show_query_execute = pg_query($dbconn, $show_query);
				while($row = pg_fetch_assoc($show_query_execute)) {
					echo '<option value="'.($row['firstname']).'">'.$row['firstname']." ".$row['lastname'].'</option>';
				}
				?>
			</select><br><br>
			<input type="submit" name="submit">
		</form>
	<?php
	}	echo ('<br><br><a href="../">Back.</a> ');	?>
	</body>
</html>

