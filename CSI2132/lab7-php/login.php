<?php
		session_start();
		if(array_key_exists('Login',$_POST)){
			$studentnum=$_POST['studentnum'];
			$password=$_POST['userPassword'];
			$dbconn=pg_connect("host=web0.site.uottawa.ca port=15432 dbname=sfloy029 user=sfloy029 password=xxxxxxxxx") or die('Connection failed');
			$query="SELECT * FROM php_project.student WHERE student_num=$1 AND student_pass=$2";
			$stmt=pg_prepare($dbconn,"ps",$query);
			$result=pg_execute($dbconn,"ps",array($studentnum,$password));
			if(!$result){
				die("error in sql query:" .pg_last_error());
			}
			$row_count=pg_num_rows($result);
			if($row_count>0){
$_SESSION['studentnum']=$studentnum;
header("location: records.php");
				exit;
			}
			pg_free_result($result);
			pg_close($dbconn);
		}

?>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<title>Student Database</title>
</head>


<body style="left:185px; top:4px; " >
	<div id="header" background-color:'blue'="" style="position:relative; left:128px; top:1px; background-color:#50A7E6; color:#FFFFFF; -webkit-border-top-left-radius:25px; border-top-left-radius:25px; -o-border-top-left-radius:25px; -ms-border-top-left-radius:25px; -moz-border-radius-topleft:25px; -webkit-border-top-right-radius:25px; border-top-right-radius:25px; -o-border-top-right-radius:25px; -ms-border-top-right-radius:25px; -moz-border-radius-topright:25px; -webkit-border-bottom-right-radius:25px; border-bottom-right-radius:25px; -o-border-bottom-right-radius:25px; -ms-border-bottom-right-radius:25px; -moz-border-radius-bottomright:25px; -webkit-border-bottom-left-radius:25px; border-bottom-left-radius:25px; -o-border-bottom-left-radius:25px; -ms-border-bottom-left-radius:25px; -moz-border-radius-bottomleft:25px; width:873px; margin-right:1px; height:75px; padding:6px 10px 6px 10px; " ><h1>&nbsp;Sign in to see your grades! <div style="width:116px; position:relative; left:750px; top:-40px; " ><a href="register.php" style="background-color:#FFE200; border-image-source:initial; border-image-slice:initial; border-image-width:initial; border-image-outset:initial; border-image-repeat:initial; border:1px solid #000000; padding:1px 4px 1px 4px; " >Sign up</a><div></div></div></h1></div>

<div style="width:321px; position:relative; left:448px; top:24px; " ><h1 style="width:446px; " >

	<form method="POST" action="" style="position:relative; left:-319px; top:-14px; width:888px; " >
		<p style="width:352px; " >Student number:  </p><div><input type="text" name="studentnum" id="studentnum" autocapitalize="off" autocorrect="off" style="width:887px; position:relative; left:0; top:-25px; height:36px; font-size:25px; " /></div><p></p>
		<p style="position:relative; left:0; top:-12px; " >Password: </p><div><input type="password" name="userPassword" id="userPassword" style="font-size:25px; width:887px; position:relative; left:0; top:-37px; height:36px; " /></div>
		<div style="" ><input type="submit" value="Login" name="Login" autocapitalize="off" autocorrect="off" style="background-color:#FFE200; width:887px; height:40px; font-size:20pt; border-image-source:initial; border-image-slice:initial; border-image-width:initial; border-image-outset:initial; border-image-repeat:initial; border:1px solid #FFC700; padding:1px 4px 1px 4px; " /></div>
	</form>

	<br><br>



</h1></div>

</body></html>
