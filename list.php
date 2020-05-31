<?php

include_once('koneksi.php');
 
$query = mysqli_query($con, 'select * from data_informasi');
 
if (mysqli_num_rows($query) > 0) {
 # buat array
 $responsistem = array();
 $responsistem["data"] = array();
 while ($row = mysqli_fetch_assoc($query)) {
     
 # kerangka format penampilan data json
 $data['id'] = $row["id"];
 $data['latitude'] = $row["latitude"];
 $data['longitude'] = $row["longitude"];
 $data['email'] = $row["email"];
 $data['no_ktp'] = $row["no_ktp"];
 $data['no_hp'] = $row["no_hp"];
 $data['instansi'] = $row["Instansi"];
 $data['alamat'] = $row["alamat"];
 $data['informasi'] = $row["informasi"];

 array_push($responsistem["data"],$data);
 
 }
 echo json_encode($responsistem);
} else {
 # menmapilkan pesan jika tidak ada data dalam tabel
 $responsistem["message"]="tidak ada data";
 echo json_encode($responsistem);
}
?>