-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 04, 2023 at 09:54 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chatroom`
--

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `owner_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`id`, `name`, `owner_id`) VALUES
(32, 'web', 38),
(34, 'programing', 39);

-- --------------------------------------------------------

--
-- Table structure for table `room_users_join`
--

CREATE TABLE `room_users_join` (
  `id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `joinied_date` text NOT NULL,
  `name_room` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room_users_join`
--

INSERT INTO `room_users_join` (`id`, `room_id`, `user_id`, `joinied_date`, `name_room`) VALUES
(45, 32, 44, 'Wed Jan 04 18:33:54 AST 2023', 'web'),
(47, 34, 44, 'Wed Jan 04 18:35:26 AST 2023', 'programing'),
(48, 32, 45, 'Wed Jan 04 18:44:07 AST 2023', 'web'),
(49, 32, 46, 'Wed Jan 04 18:44:16 AST 2023', 'web'),
(50, 32, 47, 'Wed Jan 04 18:46:53 AST 2023', 'web'),
(51, 32, 38, 'Wed Jan 04 19:45:39 AST 2023', 'web'),
(52, 32, 48, 'Wed Jan 04 19:49:12 AST 2023', 'web'),
(55, 32, 49, 'Wed Jan 04 21:38:54 AST 2023', 'web'),
(56, 32, 50, 'Wed Jan 04 22:54:54 AST 2023', 'web'),
(57, 32, 51, 'Wed Jan 04 22:55:26 AST 2023', 'web');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `firstName` text NOT NULL,
  `lastName` text NOT NULL,
  `userName` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `firstName`, `lastName`, `userName`, `password`) VALUES
(38, 'ali', 'samer', 'ali503', '123'),
(39, 'somar', 'ali', 'somar123', '123'),
(41, 'somar', 'somar', 'somar503', '123'),
(42, 'ali', 'ahmad', 'ali123', '123'),
(44, 'asdasd', 'dasdas', 'dd', 'dd'),
(45, 'f', 'f', 'f', 'f'),
(46, 'e', 'e', 'e', 'e'),
(47, 'ee', 'ee', 'ee', 'ee'),
(48, 'eee', 'eee', 'eee', 'eee'),
(49, 'صص', 'صص', 'صص', 'صص'),
(50, 'ww', 'ww', 'ww', 'ww'),
(51, 'www', 'www', 'www', 'www');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `room_users_join`
--
ALTER TABLE `room_users_join`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `room_users_join`
--
ALTER TABLE `room_users_join`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
