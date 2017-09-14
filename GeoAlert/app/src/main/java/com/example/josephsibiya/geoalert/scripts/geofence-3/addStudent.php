<?php
 include("include/connection.php");
 include("include/functions.php");
 session_start();

$stud_name = cleanData($_["stud_name"]);
$stud_init = cleanData($_["initials"]);
$stud_id = cleanData($_["IDNo"]);
$stud_Gender = cleanData($_["Gender"]);
$stud_number = cleanData($_["studNum"]);
$stud_address = cleanData($_["studMac"]);

 
//check the values in the database
$sql_check_id = "SELECT COUNT(StudNum) FROM tblstudent WHERE(StudNum='$stud_number')";
$query_check_id = mysql_query($sql_check_id) or die(mysql_error());
    
$check_id_rlt = mysql_fetch_array($query_check_id);

//else 
$mysql_qry = "INSERT INTO tblstudent (StudName, Initials, IDNo, Gender, StudNum, MacAddress) VALUES ('$stud_name', '$stud_init', '$stud_id', '$stud_Gender','$stud_number', '$stud_address');";

    
    if($check_id_rlt[0] == 1)
    {
        die("Student already exist");
    }
    else if ($conn->query($mysql_qry) === TRUE)
    {
        echo "Successfully Added";
    }
    else{
        echo "Error: " . $mysql_qry . "<br>" . $conn->error;
    }
    $conn->close();
?>