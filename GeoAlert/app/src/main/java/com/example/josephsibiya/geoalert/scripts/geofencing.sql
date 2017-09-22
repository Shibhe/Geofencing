-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 13, 2017 at 08:43 PM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `geofencing`
--
CREATE DATABASE IF NOT EXISTS `geofencing` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `geofencing`;

-- --------------------------------------------------------

--
-- Table structure for table `lecturer`
--

CREATE TABLE IF NOT EXISTS `lecturer` (
  `LectID` int(11) NOT NULL AUTO_INCREMENT,
  `Initials` varchar(5) DEFAULT NULL,
  `Surname` varchar(25) DEFAULT NULL,
  `Email` varchar(25) DEFAULT NULL,
  `OfficeNum` varchar(10) DEFAULT NULL,
  `stuffNum` varchar(9) DEFAULT NULL,
  `Username` varchar(9) NOT NULL,
  `Password` varchar(12) NOT NULL,
  PRIMARY KEY (`LectID`),
  UNIQUE KEY `stuffNum` (`stuffNum`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `lecturer`
--

INSERT INTO `lecturer` (`LectID`, `Initials`, `Surname`, `Email`, `OfficeNum`, `stuffNum`, `Username`, `Password`) VALUES
(1, 'JV', 'Sibiya', 'sibiyajvt@gmail.com', '123334s', '213213369', '123', '€,\Z)¬ŽmŽ†');

-- --------------------------------------------------------

--
-- Table structure for table `tblgeofencing`
--

CREATE TABLE IF NOT EXISTS `tblgeofencing` (
  `GeoID` int(11) NOT NULL AUTO_INCREMENT,
  `GeoName` varchar(50) NOT NULL,
  `GeoLatitude` varchar(20) NOT NULL,
  `GeoLongitude` varchar(20) NOT NULL,
  `GeoRadius` int(11) DEFAULT NULL,
  PRIMARY KEY (`GeoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tblstudent`
--

CREATE TABLE IF NOT EXISTS `tblstudent` (
  `StudID` int(11) NOT NULL AUTO_INCREMENT,
  `Surname` varchar(40) DEFAULT NULL,
  `Initials` varchar(5) DEFAULT NULL,
  `IDNo` varchar(13) NOT NULL,
  `Gender` tinyint(1) DEFAULT NULL,
  `StudNum` varchar(9) DEFAULT NULL,
  `MacAddress` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`StudID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
