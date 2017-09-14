<?Php

require 'conn.php';
session_start();

 $id = $_GET['id'];

$sql_delete = "DELETE FROM tblgeofencing WHERE(GeoID=$id)";
$query_delete = mysql_query($sql_delete) or die(mysql_error());
    
    echo "Successfull deleted";

?>