<?php
 

 // array for JSON response
$response = array();

// check for required fields
if (isset($_POST['stud_name']) && isset($_POST['initials']) && isset($_POST['IDNo']) && isset($_POST['Gender']) && isset($_POST['studNum']) && isset($_POST['studMac'])) {
    
    
$stud_name = $_POST["surname"];
$stud_init = $_POST["initials"];
$stud_id = $_POST["IDNo"];
$stud_Gender = $_POST["gender"];
$stud_number = $_POST["studNum"];
$stud_address = $_POST["studMac"];

// include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

// mysql inserting a new row
$mysql_qry = "INSERT INTO tblstudent (StudName, Initials, IDNo, Gender, StudNum, MacAddress) VALUES ('$stud_name', '$stud_init', '$stud_id', '$stud_Gender','$stud_number', '$stud_address');";

    
    // check if row inserted or not
    if ($mysql_qry) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Student successfully added.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
    $conn->close();
}
?>