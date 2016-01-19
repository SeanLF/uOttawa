<?php 
		include('config.php');
		error_reporting(E_ALL);	
		session_start();
		if(!isset($_SESSION['username'])){
			header("Location: ../");
			exit();
		}
		if(isset($_POST['logout_x'])){
			unset($_SESSION['username']);
			header("Location: ../");
			exit;
		}
		if(array_key_exists('radioStation_x',$_POST)){
			$dow_text = date('w');
			$dowMap = array('Saturday','Sunday','Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday');
			$resulta = $dowMap[$dow_text];
			if($resulta=='Friday'){
				if($_POST['radio']=='2'){
					$_SESSION['radiostation']='28450306';
				}
				if($_POST['radio']=='5'){
					$_SESSION['radiostation']='28449618';
				}
				if($_POST['radio']=='0'){
					$_SESSION['radiostation']='28444266';
				}
			}else{$_SESSION['radiostation']='28444266';}
		}
		if(isset($_SESSION['radiostation'])){
			$text = '<iframe width="90%" height="300px" scrolling="yes" frameborder="no" id="player" src="https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/playlists/'.$_SESSION['radiostation'].'&auto_play=true&hide_related=false&visual=true"></iframe>';
			echo "<script type=\"text/javascript\"> window.onload = function() { document.getElementById(\"playerContainer\").innerHTML = '". $text . "'; }</script>";
		}	
?>
<!DOCTYPE html>
<html>
	<head>
		<title> IRDb | the Internet Radio Database </title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
		<script type="text/javascript" src="../../js/jquery.jplayer.min.js"></script> 
		<link type="text/css" href="../../skin/jplayer.blue.monday.css" rel="stylesheet" />
		<link href="../website.css" rel="stylesheet" media="all" type="text/css" >
	</head>
	
	<body class="body" style="" >
		<div id="header">
			<div style="width:15%;float:left"><br></div>
			<div style="float:left">
				Internet radio database!
			</div>
			<div style="float:right; width=5%"><br></div>
			<div style="float:right">
				<a href="javascript:void(0)" onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">
					<img src="../pics/info.png" style="width:36px; float:right; right:15px;top:7px; position:relative " >
				</a>
				<form action="<?= $_SERVER['PHP_SELF'] ?>" method="post" style="float:right;right:80px;top:5px; position:relative ">
					<input type="image" name="logout" src="../pics/logout.png" style="width:50px;" >
				</form>
			</div>
		</div>
		<div id="wrapper">
			<div id="leftcolumn" >	
				<h5 id="left" > Table of contents </h5> 			
				<div id="contentWrapper" > 
					<div id="topLeft" >
						<dl id="left" >
							<dt><strong><font size="2" >Logged users:</font></strong></dt>
							<dd> <font size="2" > <a href="add_guest.php">Add a guest.</a>  </font> </dd>
							<dd> <font size="2" > <a href="delete_guest.php">Delete a guest.</a> </font> </dd>
							<dd> <font size="2" > <a href="add_host_to_show.php">Add a host to a show.</a>  </font> </dd>
							<dd> <font size="2" > <a href="delete_host_to_show.php">Remove a host from a show.</a> </font> </dd>
							<dd> <font size="2" > <a href="add_song.php">Add a song.</a> </font> </dd>
							<dd> <font size="2" > <a href="delete_song.php">Delete a song.</a> </font> </dd>
						</dl>
					</div>
					<div id="topRight" >
						<dl id="left" >
							<dt><strong>Show &amp; playsheets:</strong></dt>
							<dd> <font size="2" > <a href="../query1.php" >Get show information.</a> </font> </dd>
							<dd> <font size="2" > <a href="../query2.php" >What's playing on what?</a> </font> </dd>
							<dd> <font size="2" > <a href="../query3.php" >Hosts per category.</a> </font> </dd>
							<dd> <font size="2" > <a href="../query5.php" >Avg host rating per categories.</a> </font> </dd>
						</dl>
					</div>	
					<div id="bottomLeft" >
						<dl id="left" >
							<dt><strong>Songs, Artists, Bands and Albums:</strong></dt>
							<dd> <font size="2" > <a href="../queryF.php" >Album count per Music Label.</a> </font> </dd>
							<dd> <font size="2" > <a href="../queryG.php" >Find songs that weren't played during...</a> </font> </dd>
							<dd> <font size="2" > <a href="../queryI.php" >Most played CanCon artists...</a> </font> </dd>
							<dd> <font size="2" > Currently,  
									<?php 				# WOW! QUERY J IS BUILT IN --- HOLY SMOKES!
										include('config.php');
										$instrumental = "SELECT count(instrumental), 
											(select count(*) from project.song where instrumental=false) 
											from project.song S where instrumental=true";
										$instrB=pg_query($dbconn, $instrumental);
										if(!$instrB){
											die("error in sql query: ".pg_last_error());
										}
											if(pg_num_rows($instrB)>0){
												$rowI = pg_fetch_array($instrB);
												if($rowI[0]>=$rowI[1])
													echo (" instrumental ");
												else echo (" non-instrumental ");
											} else echo(" we don't have enough info to determine which ");
									?> songs are more popular.
								</font> </dd>
							
						</dl>
					</div>
					<div id="bottomRight" >
						<dl id="left" >
							<dt><strong>Guests and Hosts:</strong></dt>
							<dd> <font size="2" > <a href="../queryK.php" >Most featured guests.</a> </font> </dd>
							<dd> <font size="2" > <a href="../queryM.php" >Greatest host salary.</a> </font> </dd>
						</dl>
					</div> 
				</div>
			</div>
			<!-- START CONTENT...NOT MUCH :( -->
			<div id="content" style="margin-top:70px; " >
				<?php 
				include('config.php');
				$q = "set search_path=project;
					select s.shownumber,S.showname
					from timeslot T,playsheet P,show S
					where T.playsheetnumber = P.playsheetnum and P.dayofweek='".date('l')."' and S.shownumber = t.shownumber
					order by t.shownumber, p.dayofweek asc";
				echo ('I would like to listen to:<font size="3">');
				$q = pg_query($dbconn,$q);
				if(!$q){
					die("error in sql query: ".pg_last_error());
				}
				echo('<form action="" method="post">');
				while($row = pg_fetch_array($q)){
					echo ('<input type="radio" name="radio" value="'.$row[0].'">&nbsp'.$row[1].'</input>&nbsp&nbsp	');
				}echo('<input type="radio" name="radio" value="0" checked="checked" >&nbsp All songs</input></font><br><input type="image" src="../pics/play.png" style="width:80px;" name="radioStation"></form>');
				?>
				<div id="playerContainer"> </div>
				
			</div> <!-- end of content -->
		<div id="footer"  >
			<div style="padding:5px">
				<font size="2" >
					<a href="#top" > <font color="grey" > Back to top </font></a> <br>
					Copyright 2013-2014 Sean Floyd. All rights reserved. </font> </div>
		</div> <!-- End of Footer -->
	</body>
</html>