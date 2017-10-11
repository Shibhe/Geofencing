-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 11, 2017 at 03:22 PM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `geoalert`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblGeofence`
--

CREATE TABLE `tblGeofence` (
  `id` int(11) NOT NULL,
  `name` varchar(35) NOT NULL,
  `latitude` varchar(25) NOT NULL,
  `longitude` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblGeofence`
--

INSERT INTO `tblGeofence` (`id`, `name`, `latitude`, `longitude`) VALUES
(1, 'dfgfgf', 'dfgfgfdgfg', 'dfgfdgfd');

-- --------------------------------------------------------

--
-- Table structure for table `tblLecturer`
--

CREATE TABLE `tblLecturer` (
  `id` int(11) NOT NULL,
  `surname` varchar(25) NOT NULL,
  `initials` varchar(5) NOT NULL,
  `stuffNum` varchar(9) NOT NULL,
  `email` varchar(35) NOT NULL,
  `password` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblLecturer`
--

INSERT INTO `tblLecturer` (`id`, `surname`, `initials`, `stuffNum`, `email`, `password`) VALUES
(1, 'sin', 'df', 'sfsfdfsd', 'sfsd', 'sdfsdf'),
(2, 'JDHKHDD', 'JB', '232324', 'sibiyajvt@gmail.com', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `tblStudent`
--

CREATE TABLE `tblStudent` (
  `id` int(11) NOT NULL,
  `surname` varchar(25) NOT NULL,
  `initials` varchar(5) NOT NULL,
  `studNum` varchar(9) NOT NULL,
  `IDNo` varchar(13) NOT NULL,
  `gender` varchar(7) NOT NULL,
  `email` varchar(35) NOT NULL,
  `macAddress` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblStudent`
--

INSERT INTO `tblStudent` (`id`, `surname`, `initials`, `studNum`, `IDNo`, `gender`, `email`, `macAddress`) VALUES
(1, 'sibiya', 'jv', '123456789', '1234567890987', 'male', 'sibiyajvt@gmail.com', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblGeofence`
--
ALTER TABLE `tblGeofence`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tblLecturer`
--
ALTER TABLE `tblLecturer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tblStudent`
--
ALTER TABLE `tblStudent`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblGeofence`
--
ALTER TABLE `tblGeofence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tblLecturer`
--
ALTER TABLE `tblLecturer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tblStudent`
--
ALTER TABLE `tblStudent`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
