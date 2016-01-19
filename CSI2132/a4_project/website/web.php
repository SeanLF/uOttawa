<?php
	include ('config.php');
	header('Access-Control-Allow-Origin: *');
	session_start();
	if(array_key_exists('Login',$_POST)){
		$username=$_POST['username'];
		$password=$_POST['password'];
		$query="SELECT * FROM project.user WHERE username=$1 AND password=$2";
		$stmt=pg_prepare($dbconn,"ps",$query);
		$result=pg_execute($dbconn,"ps",array($username	,$password));
		if(!$result){
			die("error in sql query:" .pg_last_error());
		}
		$row_count=pg_num_rows($result);
		if($row_count>0){
			$_SESSION['username']=$username;
			header("location: secure/index.php");
			exit;
		}
		pg_free_result($result);
		pg_close($dbconn);
	}
	if(array_key_exists('radioStation_x',$_POST)){
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
	if(isset($_SESSION['username'])){
		header('Location: secure/index.php');
		exit();
	}
?>	
<!DOCTYPE html>
<html>
	<head>
		<title> IRDb | the Internet Radio Database </title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" > 
		<link href="website.css" rel="stylesheet" media="all" type="text/css" >
	</head>
	
	<body class="body" >
		<div id="header">
			<div style="width:15%;float:left"><br></div>
			<div style="float:left">
				Internet radio database!
			</div>
			<div style="float:right; width=5%"><br></div>
			<div style="float:right">
				<a href="javascript:void(0)" onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">
					<img src="pics/info.png" style="width:36px; float:right; right:15px;top:7px; position:relative " >
				</a>
				<a href="javascript:void(0)" onclick="document.getElementById('light2').style.display='block';document.getElementById('fade').style.display='block'">
					<img src="pics/login.png" style="width:50px; float:right;right:80px;top:5px; position:relative " >
				</a>
			</div>
		</div>
		<div id="wrapper">
			<div id="leftcolumn" >	
				<h5 id="left" > Table of contents </h5> 			<br>
				<div id="contentWrapper" > 
					<div id="topLeft" >
						<dl id="left" >
							<dt><strong>Show &amp; playsheets:</strong></dt>
							<dd> <font size="4" > <a href="query1.php" >Get show information.</a> </font> </dd>
							<dd> <font size="4" > <a href="query2.php" >What's playing on what?</a> </font> </dd>
							<dd> <font size="4" > <a href="query3.php" >Hosts per category.</a> </font> </dd>
							<dd> <font size="4" > <a href="query5.php" >Avg host rating per categories.</a> </font> </dd>
						</dl>
					</div>
					<div id="topRight" >
						<dl id="left" >
							<dt><strong>Songs, Artists, Bands and Albums:</strong></dt>
							<dd> <font size="4" > <a href="queryF.php" >Album count per Music Label.</a> </font> </dd>
							<dd> <font size="4" > <a href="queryG.php" >Find songs that weren't played during...</a> </font> </dd>
							<dd> <font size="4" > <a href="queryI.php" >Most played CanCon artists...</a> </font> </dd>
							<dd> <font size="4" > Currently,  
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
					<div id="bottomLeft" >
						<dl id="left" >
							<dt><strong>Guests and Hosts:</strong></dt>
							<dd> <font size="4" > <a href="queryK.php" >Most featured guests.</a> </font> </dd>
							<dd> <font size="4" > <a href="queryM.php" >Greatest host salary.</a> </font> </dd>
						</dl>
					</div>
					<!--			<div id="bottomRight" >
						<dl id="left" >
							<dt><strong>D:</strong></dt>
							<dd> <font size="4" > <a href="" >Something as.</a> </font> </dd>
						</dl>
					</div> -->
				</div>
			</div>
			<!-- START CONTENT...NOT MUCH :( -->
			<div id="content" style="margin-top:70px; " >
				<?php 
					include('config.php');
					$day =  date('l');//'Monday';
					$q = "set search_path=project;
						select s.shownumber,S.showname
						from timeslot T,playsheet P,show S
						where T.playsheetnumber = P.playsheetnum and P.dayofweek='".$day."' and S.shownumber = t.shownumber
						order by t.shownumber, p.dayofweek asc";
					echo ('I would like to listen to:<font size="3">');
					$q = pg_query($dbconn,$q);
					if(!$q){
						die("error in sql query: ".pg_last_error());
					}
					echo('<form action="" method="post">');
					while($row = pg_fetch_array($q)){
						echo ('<input type="radio" name="radio" value="'.$row[0].'">&nbsp'.$row[1].'</input>&nbsp&nbsp	');
					}echo('<input type="radio" name="radio" value="0" checked="checked" >&nbsp All songs</input></font><br><input type="image" src="pics/play.png" style="width:80px;" name="radioStation"></form>');
				?>
				<div id="playerContainer"> </div>
				
			</div> <!-- end of content -->
			
			<div id="light" class="white_content" >
				<img src="pics/radio1.png" style="left:0; top:0; position:relative; width:10%" >
				<font size="3" > <br>
					The Internet Radio Databse was created in March of 2014 by Steven and Sean for their CSI2132 Database1 project.<br> <br>
					Thank you to <strong>Soundcloud</strong> for their embedded player and all the support forums for tips and snippits of code.<br><br>
					Photo of NAC taken from: <a href="http://www.newmusicnetwork.ca/en/wp-content/uploads/2013/05/NAC_Ottawa.jpg">New Music Network</a><br><br>
					Icons from: <a href="https://itunes.apple.com/ca/app/2800-icons-for-facebook-twitter/id786188363?mt=12">2800 Icons for Developpers</a>
					<a href="javascript:void(0)" onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'" >
						<img src="pics/wrong.png" style="width:22px; position:absolute; top:0; right:0; " >
					</a>
				</font>
			</div> <!-- popup info end -->
			<div id="light2" class="white_content2" ><br>
				<div  id="loginT">
					<div id="login_info">Sign in!</div>
				</div>
				<form method="POST" action="" >			
					<div style="font-size:80%; padding:5px">Username:  					<br>
						<input type="text" name="username" id="username" autocapitalize="off" autocorrect="off" style="width:80%;text-size:100%;"/>	<br>
						Password:						<br>
						<input type="password" name="password" id="password" style="width:80%;"/></div>
						<input type="submit" value="Login" name="Login" autocapitalize="off" autocorrect="off" id="loginB" />	
						<div style="text-align:left;font-size:45%; color:black">  <br>
							Don't have an account? <a href="register.php">Sign up!</a> 
						</div>	
					</form>	
					<a href="javascript:void(0)" onclick="document.getElementById('light2').style.display='none';document.getElementById('fade').style.display='none'" >
						<img src="pics/wrong.png" style="width:22px; position:absolute; top:0; right:0; " >
					</a>
				</div> <!-- popup info end -->
			<div id="fade" class="black_overlay" ></div>
			<!-- <div id="rightcolumn" ></div>  -->
			
		</div> <!-- End of wrapper div -->
		<div id="footer"  >
			<div style="padding:5px">
				<font size="2" >
					<a href="#top" > <font color="grey" > Back to top </font></a> <br>
					Copyright 2013-2014 Sean Floyd &amp; Steven Deenstra. All rights reserved. </font> </div>
		</div> <!-- End of Footer -->
	</body>
</html>