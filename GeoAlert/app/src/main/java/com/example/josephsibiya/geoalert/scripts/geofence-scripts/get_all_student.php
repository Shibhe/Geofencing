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
$result = mysql_query("SELECT * FROM tblStudent") or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["tblStudent"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $student = array();
        $student["id"] = $row["id"];
        $student["surname"] = $row["surname"];
        $student["initials"] = $row["initials"];
        $student["studNum"] = $row["studNum"];
        $student["IDNo"] = $row["IDNo"];
        $student["gender"] = $row["gender"];
        $student["email"] = $row["email"];



        // push single student into final response array
        array_push($response["tblStudent"], $student);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["error"] = TRUE;
    $response["success"] = 0;
    $response["message"] = "No Student found";

    // echo no users JSON
    echo json_encode($response);
}
?>
