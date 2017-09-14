<?Php

require 'conn.php';
session_start();

$id = $_GET['id'];
$stud_name = $_["stud_name"];
$stud_init = $_["initials"];
$stud_id = $_["IDNo"];
$stud_Gender = $_["Gender"];
$stud_number = $_["studNum"];
$stud_address = $_["studMac"];


 $sql_update = "UPDATE tblstudent SET Surname='$stud_name' WHERE StudID=$id;
                UPDATE tblstudent SET Initials='$stud_init' WHERE StudID=$id;
                UPDATE tblstudent SET IDNo='$stud_id' WHERE StudID=$id;
                UPDATE tblstudent SET Gender='$stud_Gender' WHERE StudID=$id;
                UPDATE tblstudent SET StudNum='$stud_number' WHERE StudID=$id;
                UPDATE tblstudent SET MacAddress='$stud_address' WHERE StudID=$id;";
            
$query_update = mysqli_multi_query($conn, $sql_update) or die(mysql_error());
            
echo "Student  updated";

?>