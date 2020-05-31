
<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
   $email = $_POST['email'];
   $no_ktp = $_POST['no_ktp'];
   $no_hp = $_POST['no_hp'];
   $kata_sandi = $_POST['kata_sandi'];
   
    

   require_once('koneksi.php');
   //Cek npm sudah terdaftar apa belum
   $sql = "SELECT * FROM registrasi_covid WHERE email ='$email'";
   $check = mysqli_fetch_array(mysqli_query($con,$sql));
   if(isset($check)){
     $response = "Maaf! Email yang anda masukkan sudah terdaftar";
     echo json_encode($response);
   } else {
     
     $sql = "INSERT INTO registrasi_covid (email,no_ktp,no_hp,kata_sandi) VALUES('$email','$no_ktp','$no_hp','$kata_sandi')";
     if(mysqli_query($con,$sql)) {
        $result["success"] = "1";
        $result["message"] = "success";

        echo json_encode($result);
        mysqli_close($conn);
     } else {
 
       $response = "Pendaftaran Gagal";
       echo json_encode($response);
     }
   }
   // tutup database
   mysqli_close($con);
} else {
 
  $response="Pendaftaran Gagal";
  echo json_encode($response);
}
