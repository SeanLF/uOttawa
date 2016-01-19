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
                <h1>Add Guest</h1>
                <a href="modify.php?page=modify" class="btn backbutton"><i class="fa fa-arrow-circle-left"></i>Back</a>
                <p>
                <form action="<?= $_SERVER['PHP_SELF'] ?>?page=modify" method="post">                    
                    First Name <br><input type="text" name="firstname"><br>
                    Last Name <br><input type="text" name="lastname"><br>
                    Description <br><input type="text" cols="40" rows="2" name="description"> 	<br>
                    Topic <br><input type="text" name="topic">									<br><br><br>
                    <button type="submit" name="submit" class="btn btn-info">Insert guest</button><br><br>			
		</form>
                
                <div class="result">
                    <?php
                    if(isset($_POST['submit'])) {
                        $query="INSERT INTO project.guest VALUES(
                                (SELECT G.guestnumber+1 FROM project.guest G WHERE G.guestnumber >= ALL (SELECT G1.guestnumber FROM project.guest G1)), '".
                                $_POST['firstname']."', '". $_POST['lastname']."', '".
                                        $_POST['description']."', '".$_POST['topic']."', 5, null,'irdb');";
                        $result=pg_query($dbconn, $query);
                        if(!$result){
                                if(strpos(pg_last_error(),'duplicate key value violates unique constraint "guestname_unique"') !== false)
                                        echo "<h1>Error: guest already exists!</h1><br>
                                        <a href='add_guest.php'>Insert another guest.</a><br><a href='index.php'>Take me back home.</a>";
                                else die("error in sql query: ".pg_last_error());
                    } else {
                        echo "Data Successfully Entered!";
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