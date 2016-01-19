<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title> Sign up | IRDb </title>
	</head>



	<body>
	<h1> Sign up </h1>

	<form id="testform" name="testform" method="post" action="">

		<p> <label for="istnum"> Student #:<br> </label> <input name="istnum" type="text" id="istnum"/> </p>

		<p> <label for="lastn"> Last Name:<br> </label> <input name="lastn" type="text" id="lastn"/> </p>

		<p> <label for="firstn"> First Name #:<br> </label> <input name="firstn" type="text" id="firstn"/> </p>

		<p> <label for="ipass"> Password:<br> </label> <input name="ipass" type="password" id="ipass"/> </p>

		<p> <label for="conf"> Confirm password:<br> </label> <input name="conf" type="password" id="conf"/> </p>

		<p> <label for="street"> Street:<br> </label> <input name="street" type="text" id="street"/> </p>

		<p> <label for="city"> City:<br> </label> <input name="city" type="text" id="city"/> </p>

		<p> <label for="gender"> Gender:<br> </label> <select name="gender"> <option value="male">male</option> <option value="female">female</option> </select> </p>

		<p> <label for="email"> Email:<br> </label> <input name="email" type="text" id="email"/> </p>

		<p> <input type="submit" name="register" value="Register"/> </p>

		<script>
		function hasWhiteSpaceOrEmpty(s)
		{
		  return s == "" || s.indexOf(' ') >= 0;
		}

		function validateInput()
		{
		    var inputVal = $("#istnum").val();
		    if(hasWhiteSpaceOrEmpty(inputVal))
		    {
		        //This has whitespace or is empty, disable the button
		        $("#register").attr("disabled", "disabled");
		    }
		    else
		    {
		        //not empty or whitespace
		        $("#register").removeAttr("disabled");
		    }
		}

		$(document).ready(function() {
		    $("#istnum").keyup(validateInput);
		});
		</script>
	</form>

	</body>

	<?php
		if(array_key_exists('register',$_POST)){

			$stnum=$_POST['istnum'];
			$lname=$_POST['lastn'];
			$fname=$_POST['firstn'];
			$pass=$_POST['ipass'];
			$street=$_POST['street'];
			$city=$_POST['city'];
			$gender=$_POST['gender'];
			$email=$_POST['email'];

			$conn_string="host=web0.site.uottawa.ca port=15432 dbname=sfloy029 user=sfloy029 password=xxxxxxxxx";

			$dbconn=pg_connect($conn_string) or die('Connection failed');

			$query="INSERT INTO php_project.student(student_num, last_name, first_name, student_pass, street, city, gender, email)
			 VALUES ('$stnum', '$lname', '$fname', '$pass', '$street', '$city', '$gender', '$email')";

			$result=pg_query($dbconn, $query);

			if(!$result){
				die("error in sql query: ".pg_last_error());
			}

			echo "Data Successfully Entered! ". "<a href='login.php'>Login now</a>";

			pg_free_result($result);
			pg_close($dbconn);
		}

	?>

</html>
