-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 30, 2024 at 02:31 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kost_finder`
--

-- --------------------------------------------------------

--
-- Table structure for table `kost_images`
--

CREATE TABLE `kost_images` (
  `kost_id` bigint(20) NOT NULL,
  `image_path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kost_images`
--

INSERT INTO `kost_images` (`kost_id`, `image_path`) VALUES
(1, '/images/kost1_1.jpg'),
(1, '/images/kost1_2.jpg'),
(1, '/images/kost1_3.jpg'),
(1, '/images/kost1_4.jpg'),
(2, '/images/kost2_1.jpg'),
(2, '/images/kost2_2.jpg'),
(2, '/images/kost2_3.jpg'),
(2, '/images/kost2_4.jpg'),
(3, '/images/kost3_1.jpg'),
(3, '/images/kost3_2.jpg'),
(3, '/images/kost3_3.jpg'),
(3, '/images/kost3_4.jpg'),
(4, '/images/kost4_1.jpg'),
(4, '/images/kost4_2.jpg'),
(4, '/images/kost4_3.jpg'),
(4, '/images/kost4_4.jpg'),
(5, '/images/kost5_1.jpg'),
(5, '/images/kost5_2.jpg'),
(5, '/images/kost5_3.jpg'),
(5, '/images/kost5_4.jpg'),
(6, '/images/kost6_1.jpg'),
(6, '/images/kost6_2.jpg'),
(6, '/images/kost6_3.jpg'),
(6, '/images/kost6_4.jpg'),
(7, '/images/kost7_1.jpg'),
(7, '/images/kost7_2.jpg'),
(7, '/images/kost7_3.jpg'),
(7, '/images/kost7_4.jpg'),
(8, '/images/kost8_1.jpg'),
(8, '/images/kost8_2.jpg'),
(8, '/images/kost8_3.jpg'),
(8, '/images/kost8_4.jpg'),
(9, '/images/kost9_1.jpg'),
(9, '/images/kost9_2.jpg'),
(9, '/images/kost9_3.jpg'),
(9, '/images/kost9_4.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `kost_new`
--

CREATE TABLE `kost_new` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `facilities` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `owner_contact` varchar(15) DEFAULT NULL,
  `image_paths` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kost_new`
--

INSERT INTO `kost_new` (`id`, `name`, `description`, `facilities`, `price`, `owner_contact`, `image_paths`) VALUES
(1, 'Kost Putri Bu Rara', 'Kost Putri Bu Rara dekat dengan Universitas Negeri Surabaya Kampus 5 Magetan, hanya 5 menit berjalan kaki. Ukuran kamar 3x3, cocok untuk mahasiswa yang membutuhkan kenyamanan dengan harga terjangkau.', 'Kamar mandi dalam, WiFi tersedia, Dapur umum', 700000, '081234567890', '[\"/images/kost1_1.jpg\", \"/images/kost1_2.jpg\", \"/images/kost1_3.jpg\", \"/images/kost1_4.jpg\"]'),
(2, 'Kost Hijau Bu Wiwik', 'Kost Hijau Bu Wiwik terletak 10 menit dari Universitas Negeri Surabaya Kampus 5 Magetan. Ukuran kamar 3x4, memberikan akses mudah ke kampus dengan suasana tenang dan nyaman untuk belajar.', 'Kamar mandi luar, Tidak ada WiFi, Dapur umum', 700000, '081234567891', '[\"/images/kost2_1.jpg\", \"/images/kost2_2.jpg\", \"/images/kost2_3.jpg\", \"/images/kost2_4.jpg\"]'),
(3, 'Kost Sasna Putri', 'Kost Sasna Putri berjarak 8 menit dari Universitas Negeri Surabaya Kampus 5 Magetan. Ukuran kamar 4x3, dilengkapi dengan fasilitas kamar mandi dalam dan WiFi, membuatnya nyaman untuk mahasiswa.', 'Kamar mandi dalam, WiFi tersedia, Tidak ada dapur umum', 750000, '081234567892', '[\"/images/kost3_1.jpg\", \"/images/kost3_2.jpg\", \"/images/kost3_3.jpg\", \"/images/kost3_4.jpg\"]'),
(4, 'Kost Maasyuna Bulusari', 'Kost Maasyuna Bulusari terletak di lokasi strategis, hanya 7 menit dari Universitas Negeri Surabaya Kampus 5 Magetan. Ukuran kamar 4x4, dengan kamar yang luas dan fasilitas lengkap.', 'Kamar mandi dalam, WiFi tersedia, Dapur umum', 600000, '081234567893', '[\"/images/kost4_1.jpg\", \"/images/kost4_2.jpg\", \"/images/kost4_3.jpg\", \"/images/kost4_4.jpg\"]'),
(5, 'Kost Rasyifa', 'Kost Rasyifa hanya 6 menit dari Universitas Negeri Surabaya Kampus 5 Magetan. Ukuran kamar 3x3, dengan suasana yang nyaman dan fasilitas WiFi di setiap kamar.', 'Kamar mandi luar, WiFi tersedia, Tidak ada dapur umum', 650000, '081234567894', '[\"/images/kost5_1.jpg\", \"/images/kost5_2.jpg\", \"/images/kost5_3.jpg\", \"/images/kost5_4.jpg\"]'),
(6, 'Kost Kafafi', 'Kost Kafafi berlokasi strategis, 9 menit dari Universitas Negeri Surabaya Kampus 5 Magetan. Ukuran kamar 3x4, dilengkapi dengan fasilitas lengkap dan kamar yang cukup besar.', 'Kamar mandi dalam, WiFi tersedia, Dapur umum', 710000, '081234567895', '[\"/images/kost6_1.jpg\", \"/images/kost6_2.jpg\", \"/images/kost6_3.jpg\", \"/images/kost6_4.jpg\"]'),
(7, 'Kost Griyo Utomo', 'Kost Griyo Utomo berada di area yang sangat dekat dengan Universitas Negeri Surabaya Kampus 5 Magetan, hanya 5 menit berjalan kaki. Ukuran kamar 4x3, harga terjangkau dengan fasilitas lengkap.', 'Kamar mandi dalam, Tidak ada WiFi, Dapur umum', 670000, '081234567896', '[\"/images/kost7_1.jpg\", \"/images/kost7_2.jpg\", \"/images/kost7_3.jpg\", \"/images/kost7_4.jpg\"]'),
(8, 'Kost Dhinasty', 'Kost Dhinasty berlokasi strategis hanya 7 menit dari Universitas Negeri Surabaya Kampus 5 Magetan. Ukuran kamar 4x3, dengan fasilitas WiFi gratis dan kamar mandi dalam.', 'Kamar mandi dalam, WiFi tersedia, Tidak ada dapur umum', 500000, '081234567897', '[\"/images/kost8_1.jpg\", \"/images/kost8_2.jpg\", \"/images/kost8_3.jpg\", \"/images/kost8_4.jpg\"]'),
(9, 'Kost Bilqis', 'Kost Bilqis terletak hanya 10 menit dari Universitas Negeri Surabaya Kampus 5 Magetan. Ukuran kamar 3x4, menyediakan kamar yang nyaman dengan WiFi dan dapur umum.', 'Kamar mandi dalam, WiFi tersedia, Dapur umum', 800000, '081234567898', '[\"/images/kost9_1.jpg\", \"/images/kost9_2.jpg\", \"/images/kost9_3.jpg\", \"/images/kost9_4.jpg\"]');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kost_images`
--
ALTER TABLE `kost_images`
  ADD KEY `FKrws3hlh8po9qv72v78pai68ph` (`kost_id`);

--
-- Indexes for table `kost_new`
--
ALTER TABLE `kost_new`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kost_new`
--
ALTER TABLE `kost_new`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `kost_images`
--
ALTER TABLE `kost_images`
  ADD CONSTRAINT `FKrws3hlh8po9qv72v78pai68ph` FOREIGN KEY (`kost_id`) REFERENCES `kost_new` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
