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
			$query = "set search_path=project; select A.firstname, A.lastname, S.title, count(*) from song S, songsplayed SP, playsheet P, artist A where 					cancon=true and SP.song = S.songid and SP.playsheet = P.playsheetnum and S.artistid= A.artistid group by A.firstname, A.lastname,S.title having 					count(*) >= all (select count(*)-2 from song S1, songsplayed SP1, playsheet P1, artist A1 where cancon=true and SP1.song = S1.songid and 						SP1.playsheet = P1.playsheetnum and S1.artistid= A1.artistid group by A1.firstname, A1.lastname) order by count(*) desc,A.lastname, A.firstname";
			$result=pg_query($dbconn, $query);
			if(!$result){
				die("error in sql query: ".pg_last_error());
			}
			if(pg_num_rows($result)>0){
				echo ("<h3>Here are the Canadian Content artists with the most played songs.</h3><table><tr><th>Artist name</th><th>Song title</th> <th>Times played</th> 	</tr>");
				while($row = pg_fetch_array($result)) {
					echo ("<tr><td>".$row[0]." ".$row[1]."</td><td>".$row[2]."</td><td>".$row[3]."</td></tr>");
				}
				echo ("</table>");
			} else {
				echo("<h1>No CanCon song was ever played?!</h1>");
			} 
		?>
	<br><br>
	<a href="../">Back</a>
	</body>
</html>