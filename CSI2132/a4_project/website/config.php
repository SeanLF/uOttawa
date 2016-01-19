<?php
error_reporting(E_ALL);

$conn_string    = "host=web0.site.uottawa.ca port=15432 dbname=sfloy029 user=sfloy029 password=xxxxxxxxx";
$dbconn         = pg_connect($conn_string) or die('Connection failed');
?>
