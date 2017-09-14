<?php
 include("include/connection.php");
 //include("include/functions.php");
 session_start();

$geo_name = $_["geo_name"];
$geo_lati = $_["geo_lati"];
$geo_longi = $_["geo_longi"];
$geo_radius = $_["geo_radius"];

$mysql_qry = "INSERT INTO tblgeofencing (GeoName, GeoLatitude, GeoLongitude, GeoRadius) VALUES ('$geo_name',  '$geo_lati', '$geo_longi', '$geo_radius');";

if ($conn->query($mysql_qry) === TRUE)
{
    echo "Successfully Added";
}
else{
    echo "Error: " . $mysql_qry . "<br>" . $conn->error;
}
$conn->close();
?>