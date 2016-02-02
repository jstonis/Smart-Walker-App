<?php
$con=mysqli_connect("localhost","host","kangaroo45","swusers");

$name=$_POST["Name"];
$username=$_POST["Username"];
$password=$_POST["Password"];
$ptname=$_POST["PT Name"];
$admin=$_POST["Admin"];

$statement = mysqli_prepare($con, "INSERT INTO Users(? , ? , ? , ? , ?) VALUES($name, $username, $password, $ptname, $admin)");
mysqli_stmt_bind_param($statement, "ssssb", $name, $username, $password, $ptname, $admin);
mysqli_stmt_execute($statement);
mysqli_stmt_close($statement);


mysqli_close($con);
?>