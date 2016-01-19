<?php
error_reporting(E_ALL);
session_start();
if(!isset($_SESSION['username'])) {
    header("Location: login.php");
}
include('config.php');
if(!isset($_GET['page'])) {
    $_GET['page'] = 'home';
}
?>
<!DOCTYPE HTML>
<html>
    <head>
        <title>IRDB | Modify Data</title>
        <link href="bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="style.css" rel="stylesheet" type="text/css">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    </head>
    <body>
        
        <div class="header-div">
            <font class="header-bigtitle">
                Internet Radio Database
            </font><br>
            <font class="header-subtitle">
                <i>Databases I, CSI2132</i>
            </font>
        </div>
        
        <div class="content-container">
            <div class="content-left">
                <?php include('menu.php'); ?>
            </div>
            <div class="content-right">
                <h1>Add Song</h1>
                <a href="modify.php?page=modify" class="btn backbutton"><i class="fa fa-arrow-circle-left"></i>Back</a>
                <p>
                <form action="<?= $_SERVER['PHP_SELF'] ?>?page=modify" method="post">                    
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
                    <button type="submit" name="submit" class="btn btn-danger">Delete song</button><br><br>
		</form>
                
                <div class="result">
                    <?php
                    if(isset($_POST['submit'])) {
				$query="delete from project.song S where S.songid=".$_POST['songid'].";";
				$result=pg_query($dbconn, $query);
				if(!$result){
					die("error in sql query: ".pg_last_error());
				} else {
					echo "Deletion success!";
					pg_free_result($result);
					pg_close($dbconn);
				}
			}
                    ?>
                </div>
                </p>
                </div>
            </div>            
        </div>
        
    </body>
</html>