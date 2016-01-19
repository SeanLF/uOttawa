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
        <title>IRDB | Guests and Hosts</title>
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
                <h1>Host salaries</h1>
                <a href="guestshosts.php?page=guests" class="btn backbutton"><i class="fa fa-arrow-circle-left"></i>Back</a>
                <p>
                <form action="<?= $_SERVER['PHP_SELF'] ?>?page=hosts" method="post">
                    Hosts with salaries greater or equal than:<br><br>
                    <select name="host">
                        <option>Select a host</option>
                        <?php 
                        $show_query = "select distinct host.firstname, host.lastname from project.host order by host.lastname ";
                        $show_query_execute = pg_query($dbconn, $show_query);
                        while($row = pg_fetch_assoc($show_query_execute)) {
                            echo '<option value="'.($row['firstname']).'">'.$row['firstname']." ".$row['lastname'].'</option>';
                        }
                        ?>
                    </select><br><br>
                    <button type="submit" name="submit" class="btn btn-info">Show information</button><br><br>
		</form>
                
                <div class="result">
                    <?php
                    if(isset($_POST['submit'])) {
                        $query = "set search_path=project; 
                                SELECT H.firstname, H.lastname, H.salary
                                FROM host H
                                WHERE salary > (
                                SELECT H2.salary
                                FROM host H2
                                WHERE H2.stagename = '".$_POST['host']."' OR H2.firstname = '".$_POST['host']."')";
                        $result=pg_query($dbconn, $query);
                        if(!$result){
                            die("error in sql query: ".pg_last_error());
                        }
                        if(pg_num_rows($result)>0){
                            echo ("<table><tr><th>Host name</th><th>Salary</th></tr>");
                            while($row = pg_fetch_array($result)) {
                                    echo ("<tr><td>".$row[0]." ".$row[1]."</td><td>".$row[2]."</td></tr>");
                            }
                            echo ("</table>");
                        } else {
                            echo("<h1>Selected host has the highest salary?!</h1>");
                        }                        
                        pg_free_result($result);
                        pg_close($dbconn);
                    } 
                    ?>
                </div>
                </p>
                </div>
            </div>            
        </div>
        
    </body>
</html>