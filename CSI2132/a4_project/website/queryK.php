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
				SELECT S.showname, G.firstname, G.lastname, H.firstname, H.lastname, P.date
				FROM guest G, host H, hostsshow HS, timeslot T, playsheet P, show S
				WHERE G.rating >= (
				SELECT MAX(guest.rating) 
				FROM guest) 
				AND G.timeslotnumber=T.timeslotnumber AND T.shownumber=HS.shownum AND HS.empnum=H.enumb AND P.playsheetnum=T.playsheetnumber 
				AND S.shownumber = HS.shownum
				ORDER BY G.lastname";
			$result=pg_query($dbconn, $query);
			if(!$result){
				die("error in sql query: ".pg_last_error());
			}
			if(pg_num_rows($result)>0){
				echo ("<h3>Guests that have appeared most often.</h3><table><tr><th>Show name</th><th>Guest name</th> <th>Host name</th> <th>Date</th>	</tr>");
				while($row = pg_fetch_array($result)) {
					echo ("<tr><td>".$row[0]."</td><td>".$row[1]." ".$row[2]."</td><td>".$row[3]." ".$row[4]."</td><td>".$row[5]."</td></tr>");
				}
				echo ("</table>");
			} else {
				echo("<h1>We never had guests?! :(</h1>");
			} 
		?>
	<br><br>
	<a href="../">Back</a>
	</body>
</html>