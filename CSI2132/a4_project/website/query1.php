<?php 
session_start();
include('config.php');
?>

<!DOCTYPE html>	<html>
	<head>
		<title>Team Assignment</title>
	</head>
	<body>
		<?php
			if(isset($_POST['submit'])) {
				$query="Select * from project.show S where S.showname="."'".$_POST['show']."'";
				$result=pg_query($dbconn, $query);
				if(!$result){
					die("error in sql query: ".pg_last_error());
				}
				$row = pg_fetch_array($result);
				echo ("<h4> Show information </h4>Show Number: ".$row[0]."<br>Show Name: ".$row[1]."<br>Show Description: ".$row[2].'<br>');
				echo ("Show Type: ".$row[3].'<br><br><a href="query1.php">Perform another seach.</a>');
				pg_free_result($result);
				pg_close($dbconn);
			}
			else {
		?>
		<form action="<?= $_SERVER['PHP_SELF'] ?>" method="post">
			Select a show to get all info:
			<select name="show">
				<option>Select a value</option>
				<?php 
					$show_query = "select showname from project.show";
					$show_query_execute = pg_query($dbconn, $show_query);
					while($row = pg_fetch_assoc($show_query_execute)) {
						echo '<option value="'.($row['showname']).'">'.$row['showname'].'</option>';
					}
				?>
			</select><br><br>
			<input type="submit" name="submit">
		</form><br><br>
		<a href="web.php">Home Page.</a>
		<?php } ?>
	</body>
</html>