<?php

/*
 * Following code will delete a product from table
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['id'])) {
    $id = $_POST['id'];

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql update row with matched pid
    $result = mysql_query("DELETE FROM tblGeofence WHERE id = $id");
    
    // check if row deleted or not
    if (mysql_affected_rows() > 0) {
        // successfully updated
        $response["error"] = FALSE;
        $response["success"] = 1;
        $response["message"] = "Geofence successfully deleted";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // no product found
        $response["error"] = TRUE;
        $response["success"] = 0;
        $response["message"] = "No geofence found";

        // echo no users JSON
        echo json_encode($response);
    }
    } else {
    // required field is missing
    $response["error"] = TRUE;
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}

?>