<?Php
    //Functions

    //Sanitize data.
    function cleanData($data)
    {
        $data = addslashes($data);
        $data = trim($data);
        $data = strip_tags($data);
        $data = mysql_escape_string($data);
        return $data;
    }
?>