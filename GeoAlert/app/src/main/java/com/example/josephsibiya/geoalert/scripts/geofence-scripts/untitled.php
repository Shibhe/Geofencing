<?php
 include("include/connection.php");
 include("include/functions.php");
 session_start();



 // array for JSON response
$response = array();

// check for required fields
if (isset($_POST['stud_name']) && isset($_POST['initials']) && isset($_POST['IDNo']) && isset($_POST['Gender']) && isset($_POST['studNum']) && isset($_POST['studMac'])) {
    
    
$stud_name = cleanData($_["stud_name"]);
$stud_init = cleanData($_["initials"]);
$stud_id = cleanData($_["IDNo"]);
$stud_Gender = cleanData($_["Gender"]);
$stud_number = cleanData($_["studNum"]);
$stud_address = cleanData($_["studMac"]);


//else 
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