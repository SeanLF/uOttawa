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
				$query="INSERT INTO project.song VALUES(
						(SELECT S.songid+1 FROM project.song S WHERE S.songid >= ALL (SELECT S1.songid FROM project.song S1)), '"
						.$_POST['title']."','".$_POST['cancon']."','".
						$_POST['instrumental']."',".$_POST['albumid'].",".
						$_POST['artistid'].",'".$_SESSION['username']."', '00:00:00', '".
						$_POST['hour'].":".$_POST['minute'].":".$_POST['second']."', '".$_POST['type']."');";
				$result=pg_query($dbconn, $query);
				if(!$result){
					die("error in sql query: ".pg_last_error());
				}
				echo "Data Successfully Entered! ". "<br><a href='add_song.php'>Insert another</a><br><a href='index.php'>Back to home</a>";
				pg_free_result($result);
				pg_close($dbconn);
			}
			else {
		?>  
		<h1>Add a song to the database!</h1>
		<form action="<?= $_SERVER['PHP_SELF'] ?>" method="post">
        	Title <input type="text" name="title"><br>
			Canadian Content 
			<select name="cancon">
				<option value="false">false</option>
				<option value="true">true</option>            
			</select><br>
        	Instrumental 
			<select name="instrumental">
            	<option value="false">false</option>
				<option value="true">true</option>           
			</select><br>
			Album <br>
			<select name="albumid">
				<option>Select an album</option>
				<?php 
					$show_query = "
									select distinct A.name, AR.firstname, AR.lastname, A.albumid 
									from project.album A, project.artist AR, project.song S
									where S.albumid = A.albumid and S.artistid = AR.artistid";
					$show_query_execute = pg_query($dbconn, $show_query);
					while($row = pg_fetch_assoc($show_query_execute)) {
						echo '<option value="'.($row['albumid']).'">'.$row['name']." by ".$row['firstname']." ".$row['lastname'].'</option>';
					}
				?>
			</select><br>
			Artist <br>
			<select name="artistid">
				<option>Select an artist</option>
				<?php 
					$show_query = "select distinct A.firstname, A.lastname, A.artistid from project.artist A;";
					$show_query_execute = pg_query($dbconn, $show_query);
					while($row = pg_fetch_assoc($show_query_execute)) {
						echo '<option value="'.($row['artistid']).'">'.$row['firstname']." ".$row['lastname'].'</option>';
					}
				?>
			</select><br>
			Length <br>
			<select name="hour">
				<option>Hours</option>
				<?php for ($i = 00; $i <= 24; $i++) : ?>
					<option value="<?php echo $i; ?>"><?php echo $i; ?></option>
				<?php endfor; ?>
			</select>
			<select name="minute">
				<option>Minutes</option>
				<?php for ($i = 00; $i <= 59; $i++) : ?>
					<option value="<?php echo $i; ?>"><?php echo $i; ?></option>
			    <?php endfor; ?>
			</select>
			<select name="second">
				<option>Seconds</option>
				<?php for ($i = 00; $i <= 59; $i++) : ?>
					<option value="<?php echo $i; ?>"><?php echo $i; ?></option>
				<?php endfor; ?>
			</select><br>
			Type <br>
			<select name="type">
				<option>Select a type</option>
				<option value="African">African</option>
				<option value="Asian">Asian</option>
				<option value="Avant-Garde">Avant-Garde</option>
				<option value="Blues">Blues</option>
				<option value="Caribbean and Latin American">Caribbean and Latin American</option>
				<option value="Comedy">Comedy</option>
				<option value="Country">Country</option>
				<option value="Easy Listening">Easy Listening</option>
				<option value="Electronic">Electronic</option>
				<option value="Folk">Folk</option>
				<option value="Hip Hop">Hip Hop</option>
				<option value="Jazz">Jazz</option>
				<option value="Pop">Pop</option>
				<option value="R & B">R & B</option>
				<option value="Rock">Rock</option>
				<option value="Show Tune">Show Tune</option>
				<option value="Unknown">Unknown</option>
			</select><br><br>
			<input type="submit" name="submit"><br><br>
			<a href='index.php'>Don't add a song</a>
		</form>
		<?php } ?>
	</body>
</html>