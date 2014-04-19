<?php
/*
In order to create new users
*/

//Get UserName&Password
$UserName = $_POST["UserName"];
$Password = $_POST["Password"];
//UserName Validation
if ("User" == $UserName)
	die ("Cannot use "User" as UserName!");
//Password Validation
if (strlen($Password) > 15 || strlen($Password) < 7)
	die ("The Password is too Long or too Short!");
//SQL Connection
$SQL_Connection = mysql_connect("localhost","root","CH3CH2OH");
if(!$SQL_Connection)
	die ("Connection failed! (SQL Server is not available)");
//Select Database
mysql_select_db("purchaselist",SQL_Connection);
//Query the UserName is existed or not
$Existance_result = mysql_query("SELECT * FROM User WHERE UserName = ".$UserName, $SQL_Connection);
//UserName Existance
if (mysql_fetch_array($Existance_result))
{
	mysql_close($SQL_Connection);
	die ("This UserName is alreadly existed");
}
//Create a new User
$Create_result = mysql_query("INSERT INTO User (UserName, Password) 
VALUES (\"".$UserName."\" ,\"".$Password."\");");
if (!$Create_result)
{
	mysql_close($SQL_Connection);
	die ("Connection failed! (SQL Server is not available)");
}
//Create a new Table
mysql_select_db($UserName,$SQL_Connection);
$sql_statement = "CREATE TABLE ".$UserName."
(ID BIGINT,
Seconds BIGINT,
Currency TINYTEXT,
TrackingNumber TINYTEXT,
ExpressCompany TINYTEXT,
Expressage DOUBLE,
Title TEXT,
Category TEXT,
Producer TEXT,
Price DOUBLE,
AdditionalFee DOUBLE,
Amount INT,
Importance TINYINT,
Conditions TINYINT,
Bought TINYINT,
URL LONGTEXT,
Info LONGTEXT
);
";
$Create_result = mysql_query($sql_statement, $SQL_Connection);
if (!$Create_result)
{
	mysql_close($SQL_Connection);
	die ("Connection failed! (SQL Server is not available)");
}
//Successful
echo "The new user is created!";
?>