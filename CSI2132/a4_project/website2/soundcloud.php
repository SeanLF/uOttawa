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
if(array_key_exists('radioStation',$_POST)){
	if(date('l')=='Friday'){
		if($_POST['radio']=='2'){
			$_SESSION['radiostation']='28450306';
		}
		if($_POST['radio']=='5'){
			$_SESSION['radiostation']='28449618';
		}
		if($_POST['radio']=='0'){
			$_SESSION['radiostation']='28444266';
		}
	} else if(date('l')=='Monday'){
		if($_POST['radio']=='1'){
			$_SESSION['radiostation']='28537895';
		}
		if($_POST['radio']=='3'){
			$_SESSION['radiostation']='28537936';
		}
		if($_POST['radio']=='4'){
			$_SESSION['radiostation']='28537848';
		}
		if($_POST['radio']=='0'){
			$_SESSION['radiostation']='28444266';
		}
	} else{
		$_SESSION['radiostation']='28444266';
	}
}
if(isset($_SESSION['radiostation'])){
	$text = '<iframe width="90%" height="300px" scrolling="yes" frameborder="no" id="player" src="https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/playlists/'.$_SESSION['radiostation'].'&auto_play=true&hide_related=false&visual=true"></iframe>';
		echo "<script type=\"text/javascript\"> window.onload = function() { document.getElementById(\"playerContainer\").innerHTML = '". $text . "'; }</script>";
	}
	?>
	<!DOCTYPE HTML>
	<html>
	<head>
		<title>IRDB | Soundcloud</title>
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
				<h1>Soundcloud plugin</h1>
				<p>
					<?php
					//                    $day = date('l');//'Monday';
					$q = "set search_path=project;
					select s.shownumber,S.showname
					from timeslot T,playsheet P,show S
					where T.playsheetnumber = P.playsheetnum and P.dayofweek='Monday' and S.shownumber = t.shownumber
					order by t.shownumber, p.dayofweek asc";
					echo ('I would like to listen to:<font size="3">');
					$q = pg_query($dbconn,$q);
                    
					if(!$q){
						die("error in sql query: ".pg_last_error());
					}
                    
					echo('<form action="" method="post">');
					while($row = pg_fetch_array($q)){
						echo ('<input type="radio" name="radio" value="'.$row[0].'">&nbsp'.$row[1].'</input>&nbsp&nbsp');
					}
                    
					echo('<input type="radio" name="radio" value="0" checked="checked" >&nbsp All songs</input></font><br><br><button type="submit" class="btn btn-success" name="radioStation">Play <i class="fa fa-play"></i></button></form>');
					?>
					<div id="playerContainer"></div>                    
				</p>
			</div>            
		</div>
        
	</body>
	</html>