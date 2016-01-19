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
				select A.label,  count(A.label) as count
				from album A, song S
				where S.albumid = A.albumid
				group by A.label
				order by count desc, A.label";
			$result=pg_query($dbconn, $query);
			if(!$result){
				die("error in sql query: ".pg_last_error());
			}
			if(pg_num_rows($result)>0){
				echo ("<h3>For each music label, we have X albums.</h3><table>	<tr><th>Label Name</th>	<th>Album Count</th>	</tr>");
				while($row = pg_fetch_array($result)) {
					echo ("<tr><td>".$row[0]."</td><td>".$row[1]."</td></tr>");
					echo ("");
				}
				echo ("</table>");
			} else {
				echo("<h1>Sorry but we don't have any content...</h1>");
			} 
		?>
	<br><br>
	<a href="../">Back</a>
	</body>
</html>