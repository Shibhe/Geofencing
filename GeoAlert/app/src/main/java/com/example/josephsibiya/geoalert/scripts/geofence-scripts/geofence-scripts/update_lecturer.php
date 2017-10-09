<?php

/*
 * Following code will update a product information
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['initials']) && isset($_POST['surname'])  && isset($_POST['password'])) {
    
$id = $_POST['id'];
$initials = $_POST["initials"];
$surname = $_POST["surname"];
$password = = $_POST["password"];


    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql update row with matched pid
    $result = mysql_query("UPDATE tblLecturer SET surname = '$surname', initials = '$initials', password = '$password' WHERE id = $id");

    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["error"] = FALSE;
        $response["success"] = 1;
        $response["message"] = "Lecturer successfully updated.";
        
        // echoing JSON response
        echo json_encode($response);
     
} else {
    // required field is missing
    $response["error"] = TRUE;
    $response["success"] = 0;
    $response["message"] = "Rops! An error occurred.";

    // echoing JSON response
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