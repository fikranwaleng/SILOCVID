
<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data

   $latitude = $_POST['latitude'];
   $longitude = $_POST['longitude'];
   $email = $_POST['email'];
   $instansi = $_POST['instansi'];
   $alamat = $_POST['alamat'];
   $informasi = $_POST['informasi'];

   require_once('koneksi.php');

 
     
     $sql = "INSERT INTO data_informasi (latitude,longitude,email,instansi,alamat,informasi) VALUES('$latitude','$longitude','$email','$instansi','$alamat','$informasi')";
     if(mysqli_query($con,$sql)) {
        $result["success"] = "1";
        $result["message"] = "success";

        echo json_encode($result);
        mysqli_close($conn);
     } else {
 
       $response = "Informasi Gagal di Tambahkan";
       echo json_encode($response);
     }
   
   // tutup database
   mysqli_close($con);
} else {
 
  $response="Pendaftaran Gagal";
  echo json_encode($response);
}
