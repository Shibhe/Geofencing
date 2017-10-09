<?php

/*
 * Following code will update a product information
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['surname']) && isset($_POST['initials']) && isset($_POST['IDNo']) && isset($_POST['Gender']) && isset($_POST['studNum']) && isset($_POST['macAddress'])) {
    
$id = $_POST['id'];
$stud_name = $_POST["surname"];
$stud_init = $_POST["initials"];
$stud_id = $_POST["IDNo"];
$stud_Gender = $_POST["Gender"];
$stud_number = $_POST["studNum"];
$stud_address = $_POST["studMac"];

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql update row with matched pid
    $result = mysql_query("UPDATE tblLecturer SET surname = '$stud_name', initials = '$stud_init', IDNo = '$stud_id', gender = '$stud_Gender',studNum = '$stud_number', macAddress = '$stud_address' WHERE id = $id");

    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["error"] = FALSE;
        $response["success"] = 1;
        $response["message"] = "Student successfully updated.";
        
        // echoing JSON response
        echo json_encode($response);
    } else {
    // required field is missing
    $response["error"] = TRUE;
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
}
?>