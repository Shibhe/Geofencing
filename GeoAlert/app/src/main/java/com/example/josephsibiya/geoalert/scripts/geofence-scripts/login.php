<?php
   
   
   if($_SERVER["REQUEST_METHOD"] == "POST") {
      // username and password sent from form 
      
      $username = $_POST['username'];
      $password = $_POST['password']; 
      
       require_once __DIR__ . '/db_connect.php';

       // connecting to db
       $db = new DB_CONNECT();

      $sql = "SELECT id FROM tblLecturer WHERE username = '$username' and password = '$mypassword'";

      $result = mysqli_query($db,$sql);
      $row = mysqli_fetch_array($result,MYSQLI_ASSOC);
      
      
      $count = mysqli_num_rows($result);
      
      // If result matched $myusername and $mypassword, table row must be 1 row
		
      if($count == 1) {
      
         $response["success"] = 1;
         $response["message"] = "successfully.";
         echo json_encode($response);
         
      }else {
         $response["success"] = 0;
         $response["message"] =  "Your Login Name or Password is invalid";
          echo json_encode($response);
         $conn->close();
      }
   }
?>