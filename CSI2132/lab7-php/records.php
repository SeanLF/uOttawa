
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Student Information</title>
	</head>

<?php
		error_reporting(E_ALL);
		session_start();
		if(!isset($_SESSION['studentnum'])){
			echo("Please "."<a href='login.php'>login</a>");
			exit();
		}
		$dbconn=pg_connect("host=web0.site.uottawa.ca port=15432 dbname=sfloy029 user=sfloy029 password=xxxxxxxxx");
		if(!$dbconn){
			die("Error in connection ".pg_last_error());
		}
		$studentnum=$_SESSION['studentnum'];
$query= "Select c.course, g.year, g.sec, g.grade FROM php_project.student s, php_project.grades g, php_project.courses c WHERE s.student_num=g.student_num AND g.course_num=c.course_num AND s.student_num=$1";

			$stmt=pg_prepare($dbconn,"ps", $query);
			$result=pg_execute($dbconn, "ps", array($studentnum));
			if(!$result){
				die(pg_last_error());
			}

?>

	<body>
		<h1>Student Record Details </h1><br>

		Student number: <?php echo($studentnum); ?><br><br>

		<table>
			<tr>
				<th>Course</th>
				<th>Year</th>
				<th>Session</th>
				<th>Grade</th>
			</tr>
<?php while($row=pg_fetch_array($result)) { ?>

			<tr>
				<td><?php echo $row[0]; ?></td>
				<td><?php echo $row[1]; ?></td>
				<td><?php echo $row[2]; ?></td>
				<td><?php echo $row[3]; ?></td>
			</tr>
<?php } ?>
		</table>

		<br>
		<br>
		<a href="update_profile.php?studentnum=<?php $_SESSION['studentnum']; ?> ">Update profile</a>
	</body>

</html>
