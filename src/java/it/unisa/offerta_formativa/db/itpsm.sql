-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2014 alle 12:22
-- Versione del server: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `itpsm`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `class`
--

CREATE TABLE IF NOT EXISTS `class` (
`idClass` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `idTeaching` varchar(10) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dump dei dati per la tabella `class`
--

INSERT INTO `class` (`idClass`, `title`, `idTeaching`) VALUES
(1, 'ITPSM', '00000');

-- --------------------------------------------------------

--
-- Struttura della tabella `curriculum`
--

CREATE TABLE IF NOT EXISTS `curriculum` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `idDegree` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `curriculum`
--

INSERT INTO `curriculum` (`id`, `title`, `idDegree`) VALUES
(1, 'MIT', '12345');

-- --------------------------------------------------------

--
-- Struttura della tabella `curriculum_teaching`
--

CREATE TABLE IF NOT EXISTS `curriculum_teaching` (
  `idCurriculum` int(11) NOT NULL,
  `idTeaching` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `cycle`
--

CREATE TABLE IF NOT EXISTS `cycle` (
`id` int(11) NOT NULL,
  `title` varchar(20) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dump dei dati per la tabella `cycle`
--

INSERT INTO `cycle` (`id`, `title`) VALUES
(1, 'Triennale'),
(2, 'Magistrale'),
(3, 'Dottorato');

-- --------------------------------------------------------

--
-- Struttura della tabella `degree`
--

CREATE TABLE IF NOT EXISTS `degree` (
  `title` varchar(50) NOT NULL,
  `matricula` varchar(5) NOT NULL,
  `link` varchar(500) NOT NULL,
  `cycle` int(11) NOT NULL,
  `idDepartment` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `degree`
--

INSERT INTO `degree` (`title`, `matricula`, `link`, `cycle`, `idDepartment`) VALUES
('MIT', '12345', 'AAAAAAAAAAAAAA', 2, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `department`
--

CREATE TABLE IF NOT EXISTS `department` (
`id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `urlMoodle` varchar(200) NOT NULL,
  `token` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `module`
--

CREATE TABLE IF NOT EXISTS `module` (
`idModule` int(11) NOT NULL,
  `idTeaching` varchar(10) NOT NULL,
  `title` varchar(50) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dump dei dati per la tabella `module`
--

INSERT INTO `module` (`idModule`, `idTeaching`, `title`) VALUES
(1, '00000', 'ITSM'),
(2, '00000', 'DE');

-- --------------------------------------------------------

--
-- Struttura della tabella `prof_module_class`
--

CREATE TABLE IF NOT EXISTS `prof_module_class` (
  `idProfessor` int(11) NOT NULL,
  `idClass` int(11) NOT NULL,
  `idModule` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `prof_module_class`
--

INSERT INTO `prof_module_class` (`idProfessor`, `idClass`, `idModule`) VALUES
(1, 1, 1),
(2, 1, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `teaching`
--

CREATE TABLE IF NOT EXISTS `teaching` (
  `matricula` varchar(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  `abbreviation` varchar(10) NOT NULL,
  `link` varchar(500) NOT NULL,
  `year` int(11) NOT NULL,
  `semester` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `teaching`
--

INSERT INTO `teaching` (`matricula`, `title`, `abbreviation`, `link`, `year`, `semester`, `active`) VALUES
('00000', 'ITPSM', 'ITPSM', '', 1, 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `class`
--
ALTER TABLE `class`
 ADD PRIMARY KEY (`idClass`);

--
-- Indexes for table `curriculum`
--
ALTER TABLE `curriculum`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `curriculum_teaching`
--
ALTER TABLE `curriculum_teaching`
 ADD PRIMARY KEY (`idCurriculum`,`idTeaching`);

--
-- Indexes for table `cycle`
--
ALTER TABLE `cycle`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `degree`
--
ALTER TABLE `degree`
 ADD PRIMARY KEY (`matricula`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
 ADD PRIMARY KEY (`idModule`);

--
-- Indexes for table `prof_module_class`
--
ALTER TABLE `prof_module_class`
 ADD PRIMARY KEY (`idProfessor`,`idClass`,`idModule`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
MODIFY `idClass` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `cycle`
--
ALTER TABLE `cycle`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `module`
--
ALTER TABLE `module`
MODIFY `idModule` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
