<?php
error_reporting(E_ALL);
session_start();
include('config.php');
if(!isset($_GET['page'])) {
    $_GET['page'] = 'home';
}
if(isset($_SESSION['username'])) {
    header("location: index.php");
}
if(isset($_POST['submit'])) {   
   $username = pg_escape_string($_POST['username']);
   $password = pg_escape_string($_POST['password']);
   
   $selectuser = "SELECT username, password FROM project.user WHERE username = '".$username."' AND password = '".$_POST['password']."'";
   $userquery = pg_query($dbconn, $selectuser);
   
   if(!$userquery){
       die("error in sql query: ".pg_last_error());       
   }
   
   $countuser = pg_num_rows($userquery); 
   
   if($countuser == 1) {
       $_SESSION['username'] = $username;
       header("Location: index.php");
   }
   else {
       $message = 1;
   }
   
   
}
?>
<!DOCTYPE HTML>
<html>
    <head>
        <title>IRDB | Login</title>
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
                
        <?php
        if(isset($message) && $message == 1) {                
            echo '<div class="error_message">The username and password seem to be not correct</div>';
        }
        ?>                
        <?php
        if(isset($_GET['page']) && $_GET['page'] == 'logout') {
            echo "<div class=\"success_message\">You're logged out successfully!</div>";
        }
        ?>       
        
        <div class="loginbox">
            <form action="<?= $_SERVER['PHP_SELF'] ?>" method="post">
                <input type="text" name="username" value="irdb"><br>
                <input type="password" name="password" value="admin"><br>
                <button type="submit" name="submit" class="btn btn-info">Login</button>
            </form>
        </div>
        
    </body>
</html>