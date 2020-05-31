<?php

 require_once('koneksi.php');
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values
 $email = $_POST['email'];
 $kata_sandi = $_POST['kata_sandi'];
 
 
  

 //Creating sql query
 $sql = "SELECT * FROM registrasi_covid WHERE email='$email' AND kata_sandi='$kata_sandi'";

 //executing query
 $result = mysqli_query($con,$sql);

 //fetching result
 $check = mysqli_fetch_array($result);

 //if we got some result
 if(isset($check)){
 //displaying success
 echo "success";
 }else{
 //displaying failure
 echo "failure";
 }
 mysqli_close($con);
 }
?>