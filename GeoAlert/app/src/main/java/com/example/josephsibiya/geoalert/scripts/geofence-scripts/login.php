<?php
   
   
   //$response = array();

   if($_SERVER["REQUEST_METHOD"] == "POST") {
      // username and password sent from form 
      
      $email = $_POST['email'];
      $password = $_POST['password'];
      

       require_once __DIR__ . '/db_connect.php';

       // connecting to db
       $db = new DB_CONNECT();

      

      $sql = "SELECT * FROM tblLecturer WHERE email = '$email' and password = '$password';";

      $result = mysqli_query($db,$sql);
      $row = mysql_num_rows($result ,MYSQLI_ASSOC);
      
      
      $count = mysqli_num_rows($result);
      
      // If result matched $username and $password, table row must be 1 row
		
      if($count == 1) {
      
         $response["success"] = 1;
         $response["message"] = "successfully.";
         echo json_encode($response);
         
      }else {
      
         $response["success"] = 0;
         $response["message"] =  "Your Login Name or Password is invalid";
          echo json_encode($response);
      }
   } else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>