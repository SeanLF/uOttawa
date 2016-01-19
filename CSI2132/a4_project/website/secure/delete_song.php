<?php 
		include('config.php');
		error_reporting(E_ALL);	
		session_start();
		if(!isset($_SESSION['username'])){
			header("Location: ../");
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
				$query="delete from project.song S where S.songid=".$_POST['songid'].";";
				$result=pg_query($dbconn, $query);
				if(!$result){
					die("error in sql query: ".pg_last_error());
				} else {
					echo "Deletion success!". "<br><a href='delete_song.php'>Delete another song.</a><br><a href='index.php'>Take me back home.</a>";
					pg_free_result($result);
					pg_close($dbconn);
				}
			} else {
		?>  
		<h1>Delete a song!</h1>
		<form action="<?= $_SERVER['PHP_SELF'] ?>" method="post">	
			Song <br>
			<select name="songid">
				<option>Select a song</option>
				<?php 
					$show_query = "SET search_path = project; 
						  			SELECT S.title, A.firstname, A.lastname, AL.name, S.songid 
									FROM song S, album AL, artist A
									WHERE S.albumid = AL.albumid and S.artistid=A.artistid;";
					$show_query_execute = pg_query($dbconn, $show_query);
					while($row = pg_fetch_assoc($show_query_execute)) {
						echo '<option value="'.$row['songid'].'">'.
							$row['title']." - ".$row['firstname'].' '.$row['lastname']." - ".$row['name'].
							'</option>';
					}
				?>
			</select><br><br>
			<input type="submit" name="submit"><br><br>
			<a href='index.php'>Take me back home</a>
		</form>
		<?php	} ?>
	</body>
</html>