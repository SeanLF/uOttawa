<?php 
		include('config.php');
		error_reporting(E_ALL);	
		session_start();
		if(!isset($_SESSION['username'])){
			header("Location: ../");
			exit();
		}
?>
<!DOCTYPE html>
<html>
<head>
    <title>Team Assignment</title>
</head>

<body>
	
	<?php

	if(isset($_POST['submit'])) {
		
	   $query="delete from project.guest G where G.guestnumber=".$_POST['guestid'].";";
       $result=pg_query($dbconn, $query);

       if(!$result){
   		if(strpos(pg_last_error(),'some erorr') !== false)
   			echo "<h1>Error: cannot delete non-existing guest!</h1><br>
   				<a href='delete_guest.php'>Delete another guest.</a><br><a href='index.php'>Take me back home.</a>";
              else die("error in sql query: ".pg_last_error());
       } else {
   		echo "Deletion success!". "<br><a href='delete_guest.php'>Delete another guest.</a><br><a href='index.php'>Take me back home.</a>";
   		pg_free_result($result);
   		pg_close($dbconn); 
   	}
		}
		else {
	?>  

	<h1>Delete a guest!</h1>

	    <form action="<?= $_SERVER['PHP_SELF'] ?>" method="post">
			
	          Guest name <br>
	          <select name="guestid">
			           	 <?php 
			           	  $show_query = "select distinct G.firstname, G.lastname, G.guestnumber from project.guest G;";
			          	  $show_query_execute = pg_query($dbconn, $show_query);
     	  
			     	  while($row = pg_fetch_assoc($show_query_execute)) {
			                echo '<option value="'.$row['guestnumber'].'">'.$row['firstname'].' '.$row['lastname'].'</option>';
			           	  }
			             ?></select><br>
		
			<br><br>
        
	        <input type="submit" name="submit">
			<br><br><a href='index.php'>Take me back home</a>
	    </form>
	    <?php
	}?>

    
</body>
</html>