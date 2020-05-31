-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 31 Bulan Mei 2020 pada 00.28
-- Versi server: 10.3.16-MariaDB
-- Versi PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id13843834_silcovid`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_informasi`
--

CREATE TABLE `data_informasi` (
  `id` int(10) NOT NULL,
  `latitude` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `longitude` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Instansi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `alamat` text COLLATE utf8_unicode_ci NOT NULL,
  `informasi` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data untuk tabel `data_informasi`
--

INSERT INTO `data_informasi` (`id`, `latitude`, `longitude`, `email`, `Instansi`, `alamat`, `informasi`) VALUES
(5, '-0.858129', '119.884030', 'fikranmawal@gmail.com', 'Rumah Sakit Undata Palu', 'jalan Martadinata', 'di rumah sakit undata terdapat pemeriksaan gratis mengenai covid 19, dan ada pembagian masker'),
(7, ' -0.8997674', '119.8490717', 'fikranwaleng@gmail.com', 'RS Anutapura', 'Palu Barat', 'Bagi yang terdapat keluhan silahkan ke rumah sakit anutapura, karena ada pemeriksaan terkait covid   19 dan ada pmebagian sembako gratis'),
(8, '-0.901676', '119.878101', 'fikran@gmail.com', 'PMI', 'jalan R.A. Kartini 18', 'Akan di adakan pemeriksaan terkait covid 19, dan akan di lakukan donor darah gratis'),
(10, '-0.691013', '119.838705', 'megawati@gmail.com', 'Puskesmas Wani', 'Jalan HM Thalib no 30', 'Disini ada pmebagian masker sekaligus pemeriksaan kesehatan terkait covid 19');

-- --------------------------------------------------------

--
-- Struktur dari tabel `informasi`
--

CREATE TABLE `informasi` (
  `id_data` int(10) NOT NULL,
  `nama_data` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data untuk tabel `informasi`
--

INSERT INTO `informasi` (`id_data`, `nama_data`) VALUES
(1, 'data_1'),
(2, 'data_2'),
(3, 'data_3'),
(4, 'data_4');

-- --------------------------------------------------------

--
-- Struktur dari tabel `registrasi_covid`
--

CREATE TABLE `registrasi_covid` (
  `id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `no_ktp` varchar(50) NOT NULL,
  `no_hp` varchar(20) NOT NULL,
  `kata_sandi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `registrasi_covid`
--

INSERT INTO `registrasi_covid` (`id`, `email`, `no_ktp`, `no_hp`, `kata_sandi`) VALUES
(42, 'fikran@gmail.com', '132645887934510790', '082348575930', 'qwerty'),
(43, 'megawati@gmail.com', '2566628666666699699885', '082266480394', 'f55117002'),
(44, 'fikranwaleng@gmail.com', '6466488484654646464616', '316434619', '88543169456494455'),
(45, 'fikranmawal@gmail.com', '24694368436948434', '082348575930', 'qwerty'),
(46, 'audyruslan7@gmail.com', '646494', '87672484', 'sasuke');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `data_informasi`
--
ALTER TABLE `data_informasi`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `informasi`
--
ALTER TABLE `informasi`
  ADD PRIMARY KEY (`id_data`);

--
-- Indeks untuk tabel `registrasi_covid`
--
ALTER TABLE `registrasi_covid`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `data_informasi`
--
ALTER TABLE `data_informasi`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT untuk tabel `informasi`
--
ALTER TABLE `informasi`
  MODIFY `id_data` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `registrasi_covid`
--
ALTER TABLE `registrasi_covid`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
