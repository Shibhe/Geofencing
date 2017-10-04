<?php
 

 // array for JSON response
$response = array();

// check for required fields
if (isset($_POST['name']) && isset($_POST['latitude']) && isset($_POST['longitude'])) {
    
  
$name = $_POST["name"];  
$latitude = $_POST["latitude"];
$longitude = $_POST["longitude"];

// include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

// mysql inserting a new row
$mysql_qry = "INSERT INTO tblgeofence (name, latitude, longitude) VALUES ('$name', '$latitude', '$longitude')";

    
    // check if row inserted or not
    if ($mysql_qry) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Geofence successfully added.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // required field is missing
  
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Required field(s) is missing";
        
        // echoing JSON response
        echo json_encode($response);  
}
}
?>