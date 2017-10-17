<?php

/*
 * Following code will list all the products
 */

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// get all products from products table
$result = mysql_query("SELECT * FROM tblLecturer") or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["tblLecturer"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        // temp user array
       $lecturer = array();
       $lecturer["id"] = $row["id"];
       $lecturer["surname"] = $row["surname"];
       $lecturer["initials"] = $row["initials"];
        $lecturer["email"] = $row["email"];
         $lecturer["password"] = $row["password"];
       $lecturer["stuffNum"] = $row["stuffNum"];
        

        // push single geofence into final response array
        array_push($response["tblLecturer"], $lecturer);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No result found";

    // echo no users JSON
    echo json_encode($response);
}
?>
