<?php
 

 // array for JSON response
$response = array();

// check for required fields
if (isset($_POST['surname']) && isset($_POST['initials']) && isset($_POST['studNum']) && isset($_POST['IDNo']) && isset($_POST['gender']) && isset($_POST['email'])) {
    
    
$surname = $_POST["surname"];
$initials = $_POST["initials"];
$IDNo = $_POST["IDNo"];
$gender = $_POST["gender"];
$studNum = $_POST["studNum"];
$email = $_POST["email"];

// include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

// mysql inserting a new row
$mysql_qry = "INSERT INTO tblstudent (surname, initials, studNum,, IDNo, gender, email) VALUES ('$surname', '$initials', '$studNum', '$IDNo','$gender', '$email');";

    
    // check if row inserted or not
    if ($mysql_qry) {
        // successfully inserted into database
        $response["error"] = FALSE;
        $response["success"] = 1;
        $response["message"] = "Student successfully added.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["error"] = TRUE;
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
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