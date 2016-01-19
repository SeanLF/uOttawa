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
				echo "<h4>Songs the ".$_POST['show']." show played on ".$_POST['day'].".</h4>";
				echo ("<table><tr><th>Song Title</th><th>Artist Name</th><th>Album Title</th></tr>");
				while($row = pg_fetch_array($result)) {
					echo ("<tr><td>".$row[0]."</td><td>".$row[1]." ".$row[2]."</td><td>".$row[3]."</td></tr>");
				}
				echo("</table>");  
			}else{
				echo "The \"".$_POST['show']."\" show didn't play any songs on ".$_POST['day'].".";
			}
			echo '<br><br> <a href="query2.php">Perform another seach.</a>';
			pg_free_result($result);
			pg_close($dbconn);
		}
		
		else {
	 ?>  
	<form action="<?= $_SERVER['PHP_SELF'] ?>" method="post">       
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
		<input type="submit" name="submit">
	</form>
	<?php 
		} echo ('<br><br><a href="web.php">Home Page.</a> ');
	?>
	</body>
</html>