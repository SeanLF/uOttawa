<?php 
		include('config.php');
		error_reporting(E_ALL);	
		session_start();
		if(!isset($_SESSION['username'])){
			header("Location: ../");
			exit();
		}
		if(isset($_POST['logout'])){
			unset($_SESSION['username']);
			header("Location: ../");
			exit;
		}
			
?>

<!DOCTYPE html>
<html>
<head>
    <title>Database access</title>
</head>

<body>
	<?php
		if(isset($_POST['logout'])) {		
			
		}
	?>
	<h1>As a registered user, you may modify the database!</h1>
	
	<a href="add_guest.php">Add a guest.</a> <br><br>
	<a href="delete_guest.php">Delete a guest.</a> <br><br>
	<a href="add_host_to_show.php">Add a host to a show.</a> <br><br>
	<a href="delete_host_to_show.php">Remove a host from a show.</a> <br><br>
	<a href="add_song.php">Add a song.</a> <br><br>
	<a href="delete_song.php">Delete a song.</a> <br><br>
		
		<form action="<?= $_SERVER['PHP_SELF'] ?>" method="post">
		
			<input type="submit" name="logout" value="Log out">
		
		</form>
</body>
</html>