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
                <h1>Delete Host to Show</h1>
                <a href="modify.php?page=modify" class="btn backbutton"><i class="fa fa-arrow-circle-left"></i>Back</a>
                <p>
                <form action="<?= $_SERVER['PHP_SELF'] ?>?page=modify" method="post">                    
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
                    <button type="submit" name="submit" class="btn btn-danger">Delete Guest</button><br><br>
		</form>
                
                <div class="result">
                    <?php
                    if(isset($_POST['submit'])) {
		
                    $query="delete from project.hostsshow HS where empnum=".$_POST['showhost'].";"; #and shownum="shownum" (from php value annoyance)
                    $result=pg_query($dbconn, $query);

                    if(!$result){
                             if(strpos(pg_last_error(),'some erorr') !== false)
                                     die("error in sql query: ".pg_last_error());
                    }
                    else {
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