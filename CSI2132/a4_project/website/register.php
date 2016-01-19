<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title> Sign up | IRDb </title>
	<script type='text/javascript' src='http://code.jquery.com/jquery.min.js'></script>
	</head>



	<body>



	<?php
	// define variables and set to empty values
	$email = $user = $ipass = $conf = $country = "";

	if ($_SERVER["REQUEST_METHOD"] == "POST")
	{
	   $email = test_input($_POST["email"]);
	   $user = test_input($_POST["user"]);
	   $ipass = test_input($_POST["ipass"]);
	   $conf = test_input($_POST["conf"]);
	   $country = test_input($_POST["country"]);
	}

	function test_input($data)
	{
	   $data = trim($data);
	   $data = stripslashes($data);
	   $data = htmlspecialchars($data);
	   return $data;
	}
	?>
	<h1> Sign up </h1>

	<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">

		<p> <label for="email"> Email:<br> </label> <input name="email" type="text" id="email"/> </p>

		<p> <label for="user"> Username:<br> </label> <input name="user" type="text" id="user"/> </p>

		<p> <label for="ipass"> Password:<br> </label> <input name="ipass" type="password" id="ipass"/> </p>

		<p> <label for="conf"> Confirm password:<br> </label> <input name="conf" type="password" id="conf"/> </p>

		<p> <label for="country"> Country:<br> </label> <input name="country" type="text" id="country"/> </p>

		<p> <input type="submit" name="register" value="Register" /> </p>
	</form>




	</body>

	<?php
		if(array_key_exists('register',$_POST)){

			$user=$_POST['user'];
			$pass=$_POST['ipass'];
			$country=$_POST['country'];
			$email=$_POST['email'];

			$conn_string="host=web0.site.uottawa.ca port=15432 dbname=sfloy029 user=sfloy029 password=xxxxxxxxx";

			$dbconn=pg_connect($conn_string) or die('Connection failed');

			$query="INSERT INTO project.user(username, email, country, password) VALUES ('$user', '$email', '$country', '$pass')";

			$result=pg_query($dbconn, $query);

			if(!$result){
				die("error in sql query: ".pg_last_error());
			}

			echo "Data Successfully Entered! ". "<a href='web.php'>Login now</a>";

			pg_free_result($result);
			pg_close($dbconn);
		}

	?>

</html>
