<?php
$con=mysqli_connect("localhost","host","kangaroo45","swusers");

$username=$_POST["Username"];
$password=$_POST["Password"];

$statement - mysqli_prepare($con, "SELECT * FROM Users WHERE username = ? AND password = ?");
mysqli_stmt_bind_param($statement, "ss", $username, $password);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement, $user_id, $username, $password, $name, $ptname, $admin);

$user=array();

while(mysqli_stmt_fetch($statement)){
$user[name]=$name;
$user[username]=$username;
$user[password]=$password;
$user[ptname]=$ptname;
$user[admin]=$admin;
}

echo json_encode($user);

mysqli_stmt_close($statement);

mysqli_close($con);
?>