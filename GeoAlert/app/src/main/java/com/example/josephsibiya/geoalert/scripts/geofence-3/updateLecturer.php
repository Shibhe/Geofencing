<?Php

require 'conn.php';
session_start();

$id = $_GET['id'];
$geo_name = $_["geo_name"];
$geo_lati = $_["geo_lati"];
$geo_longi = $_["geo_longi"];
$geo_radius = $_["geo_radius"];


$sql_update = "UPDATE lecturer SET GeoName= '$geo_name' WHERE GeoID=$id;
               UPDATE tblgeofencing SET GeoLatitude= '$geo_lati'' WHERE GeoID=$id;
               UPDATE tblgeofencing SET GeoLongitude= '$geo_longi'' WHERE GeoID=$id;
               UPDATE tblgeofencing SET GeoRadius= '$geo_radius'' WHERE GeoID=$id;";
            
$query_update = mysqli_multi_query($conn, $sql_update) or die(mysql_error());
            
echo "updated";
?>