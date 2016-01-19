<?php
error_reporting(E_ALL);
session_start();
if(!isset($_SESSION['username'])) {
    header("Location: login.php");
}
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
        <title>IRDB | Shows and Playsheet</title>
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
                <h1>Get show information</h1>
                <a href="showsplaysheets.php?page=shows" class="btn backbutton"><i class="fa fa-arrow-circle-left"></i>Back</a>
                <p>
                <form action="<?= $_SERVER['PHP_SELF'] ?>?page=shows" method="post">
                    Select a show to get all info:<br><br>
                    <select name="show" style="font-size: 22px;">
                        <option>Select a value</option>
                        <?php 
                        $show_query = "select showname from project.show";
                        $show_query_execute = pg_query($dbconn, $show_query);
                        while($row = pg_fetch_assoc($show_query_execute)) {
                            echo '<option value="'.($row['showname']).'">'.$row['showname'].'</option>';
                        }
                        ?>
                    </select><br><br>
                    <button type="submit" name="submit" class="btn btn-info">Show information</button>
		</form><br>
                <div class="result">
                    <?php
                    if(isset($_POST['submit'])) {
                        $query="Select * from project.show S where S.showname="."'".$_POST['show']."'";
                        $result=pg_query($dbconn, $query);
                        if(!$result){
                                die("error in sql query: ".pg_last_error());
                        }
                        $row = pg_fetch_array($result);
                        echo "<h4> Show information </h4>Show Number: ".$row[0]."<br>Show Name: ".$row[1]."<br>Show Description: ".$row[2].'<br>';                    
                        pg_free_result($result);
                        pg_close($dbconn);
                    }
                    ?>
                </p>
                </div>
            </div>            
        </div>
        
    </body>
</html>