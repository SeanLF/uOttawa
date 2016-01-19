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
                <h1>Delete Guest</h1>
                <a href="modify.php?page=modify" class="btn backbutton"><i class="fa fa-arrow-circle-left"></i>Back</a>
                <p>
                <form action="<?= $_SERVER['PHP_SELF'] ?>?page=modify" method="post">                    
                Guest name <br>
	          <select name="guestid">
                    <?php 
                        $show_query = "select distinct G.firstname, G.lastname, G.guestnumber from project.guest G;";
                        $show_query_execute = pg_query($dbconn, $show_query);

                        while($row = pg_fetch_assoc($show_query_execute)) {
                            echo '<option value="'.$row['guestnumber'].'">'.$row['firstname'].' '.$row['lastname'].'</option>';
                        }
                    ?>
                  </select><br>
                    <button type="submit" name="submit" class="btn btn-danger">Delete Guest</button><br><br>
		</form>
                
                <div class="result">
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