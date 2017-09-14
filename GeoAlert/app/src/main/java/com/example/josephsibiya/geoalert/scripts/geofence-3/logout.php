<?Php
    session_start();
    
    session_unset();
    session_destroy();
    
    if(!$_SESSION['Id'])
    {
        header("Location: login.php");
    }
?>