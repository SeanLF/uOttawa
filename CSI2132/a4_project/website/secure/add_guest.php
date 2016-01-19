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
				$query="INSERT INTO project.guest VALUES(
					(SELECT G.guestnumber+1 FROM project.guest G WHERE G.guestnumber >= ALL (SELECT G1.guestnumber FROM project.guest G1)), '".
					$_POST['firstname']."', '". $_POST['lastname']."', '".
						$_POST['description']."', '".$_POST['topic']."', 5, null,'".$_SESSION['username']."');";
				$result=pg_query($dbconn, $query);
				if(!$result){
					if(strpos(pg_last_error(),'duplicate key value violates unique constraint "guestname_unique"') !== false)
						echo "<h1>Error: guest already exists!</h1><br>
						<a href='add_guest.php'>Insert another guest.</a><br><a href='index.php'>Take me back home.</a>";
					else die("error in sql query: ".pg_last_error());
		    	} else {
					echo "Data Successfully Entered! ". "<br><a href='add_guest.php'>Insert another guest.</a><br><a href='index.php'>Take me back home.</a>";
					pg_free_result($result);
					pg_close($dbconn); 
				}
			}
			else {
		?>  
		<h1>Add a guest!</h1>
		<form action="<?= $_SERVER['PHP_SELF'] ?>" method="post">
			First Name <input type="text" name="firstname"><br>
			Last Name <input type="text" name="lastname"><br>
			Description <input type="text" cols="40" rows="2" name="description"> 	<br>
			Topic <input type="text" name="topic">									<br><br><br>
			<input type="submit" name="submit"><br><br>
			<a href='index.php'>Don't add a guest</a>
		</form>
		<?php 
			}	
		?>
	</body>
</html>