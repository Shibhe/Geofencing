<?php
 require "conn.php";
     
$username = $_POST['Username'];   
$password = $_POST['Password'];
                   
                
$mysql_qry = "SELECT * FROM lecturer WHERE Username LIKE '$username' AND Password LIKE '$password'";
$result = mysqli_query($conn,$mysql_qry) or die(mysqli_error($conn));;

 $id = (int)$rlt['Id'];

   if(mysqli_num_rows($result) > 0)
    {
         session_start();
         $_SESSION['lect_id'] = $id;
         echo "   Welcome to Geofence Application. Please Enjoy!!!!" . $username;
     }
        else
     {
            echo "Login failed. Incorrect username or password.";
      }

?>