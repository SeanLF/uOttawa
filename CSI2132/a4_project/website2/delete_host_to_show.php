<?php 
include('config.php');
session_start();
error_reporting(E_ALL);
if(!isset($_SESSION['username'])) {
    header("Location: login.php");
}
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
		
	   $query="delete from project.hostsshow HS where empnum=".$_POST['showhost'].";"; #and shownum="shownum" (from php value annoyance)
       $result=pg_query($dbconn, $query);

       if(!$result){
   		if(strpos(pg_last_error(),'some erorr') !== false)
   			die("error in sql query: ".pg_last_error());
       else {
   		echo "Deletion success!". "<br><a href='delete_hosts_to_show.php'>Delete another showhost.</a><br><a href='index.php'>Take me back home.</a>";
   		pg_free_result($result);
   		pg_close($dbconn); 
   	}
		}
	}else {
	?>  

	<h1>Remove a showhost from a show!</h1>

	    <form action="<?= $_SERVER['PHP_SELF'] ?>" method="post">
			
	          Showhost - showname <br>
	          <select name="showhost">
				  <option>Select a showhost and showname</option>
			           	 <?php 
			           	  $show_query = "SET search_path = project; 
						  SELECT H.firstname, H.lastname, S.showname, HS.empnum, HS.shownum
						  FROM show S, host H, hostsshow HS
						  WHERE HS.empnum = H.enumb and HS.shownum=S.shownumber;";
			          	  $show_query_execute = pg_query($dbconn, $show_query);
     	  
			     	  while($row = pg_fetch_assoc($show_query_execute)) {
			                echo "<option value=\"".$row['empnum']." and shownum=".$row['shownum']."\">".
								$row['firstname'].' '.$row['lastname']." hosting \"".$row['showname'].
									"\"</option>";
			           	  }
			             ?></select>
		
			<br><br>
        
	        <input type="submit" name="submit">
			<br><br><a href='index.php'>Take me back home</a>
	    </form>
	    <?php
	}?>

    
</body>
</html>