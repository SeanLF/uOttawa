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
			$query = "set search_path=project;
				select distinct S.title, A.firstname, A.lastname, AL.name, AL.albumrecordingdate from timeslot T, playsheet P, song S, artist A, album AL, 					songsPlayed SP	where T.playsheetnumber = P.playsheetnum and SP.playsheet = P.playsheetnum and S.songid = SP.song and S.artistid=A.artistid and	 					S.albumid=AL.albumid and P.date >= '2014/01/01' and P.date <='2014/04/01' order by S.title";
			$result=pg_query($dbconn, $query);
			if(!$result){
				die("error in sql query: ".pg_last_error());
			}
			if(pg_num_rows($result)>0){
				echo ("<h3>Select a month and find which songs weren't played.</h3><table>	<tr><th>Title</th>	<th>Artist name</th> <th>Album name</th> 						<th>Recording date</th>	</tr>");
				while($row = pg_fetch_array($result)) {
					echo ("<tr><td>".$row[0]."</td><td>".$row[1]." ".$row[2]."</td><td>".$row[3]."</td><td>".$row[4]."</td></tr>");
				}
				echo ("</table>");
			} else {
				echo("<h1>Every one of our songs were played during your selected interval...</h1>");
			} 
		?>
	<br><br>
	<a href="../">Back</a>
	</body>
</html>