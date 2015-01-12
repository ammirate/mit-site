-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Gen 12, 2015 alle 12:04
-- Versione del server: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db-da-integrare`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `email` varchar(100) NOT NULL COMMENT 'Nome con il quale l''utente viene riconosciuto da un',
  `password` varchar(45) NOT NULL,
  `typeOfAccount` varchar(45) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabella adibita alla gestione dei dati principali per l''acesso al sistema';

--
-- Dump dei dati per la tabella `account`
--

INSERT INTO `account` (`email`, `password`, `typeOfAccount`, `active`) VALUES
('adl@unisa.it', 'test', 'professor', 1),
('ballo@hotmail.it', 'ghj', 'Mstudent', 1),
('ciro@gmail.com', 'ciropass', 'Mstudente', 1),
('ciro@unisa.it', '123123', 'Mstudent', 1),
('cosimo@gmail.com', 'cosimo', 'Mstudent', 1),
('d.iannone@gmail.com', 'daniele', 'Mstudent', 1),
('damiano@gmail.com', 'dami', 'Mstudent', 0),
('delucia@gmail.com', 'andrea', 'professor', 1),
('elyx24@hotmail.it', 'ciao', 'phd', 1),
('ferrucci@gmail.com', 'filomena', 'professor', 1),
('ff@unisa.it', 'ciao', 'professor', 1),
('francese@gmail.com', 'francese', 'professor', 1),
('gemma.catolino91@gmail.com', 'CIAO', 'Mstudent', 1),
('m.taddeo@gmail.com', 'taddeo', 'Bstudent', 0),
('mariorossi@gmail.com', 'mario', 'Bstudent', 0),
('mikela_f@hotmail.it', 'ciao', 'Mstudent', 1),
('nuovo@unisa.it', '123123', 'Bstudent', 1),
('nuovoprof@unisa.it', '123123', 'professor', 1),
('polese@gmail.com', 'polese', 'professor', 0),
('prof@unisa.it', '123123', 'professor', 1),
('test@test.it', 'test', 'phd', 1),
('tucci@gmail.com', 'Tucci', 'professor', 0),
('uio@hotmail.it', 'yuio', 'Bstudent', 1),
('vaia@gmail.com', 'vaia', 'professor', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Object` varchar(255) NOT NULL,
  `ID_Thesis` int(11) NOT NULL,
  `Status` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `ID_Thesis` (`ID_Thesis`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `chronology`
--

CREATE TABLE IF NOT EXISTS `chronology` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Text` varchar(1000) NOT NULL,
  `ID_Professor` varchar(16) NOT NULL,
  `ID_Student` varchar(16) NOT NULL,
  `Notice_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Type` varchar(255) DEFAULT NULL COMMENT 'values: richiesta, accetta, rifiuta, modifica',
  PRIMARY KEY (`ID`),
  KEY `fk_person1` (`ID_Student`) USING BTREE,
  KEY `fk_person2` (`ID_Professor`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=53 ;

--
-- Dump dei dati per la tabella `chronology`
--

INSERT INTO `chronology` (`ID`, `Text`, `ID_Professor`, `ID_Student`, `Notice_Date`, `Type`) VALUES
(14, 'lo studente Palumbo Ciro ha inviato una richiesta di tesi al prof. De Lucia Andrea con il seguente messaggio: jokljogie7se5dritfuogyiuhiuggctuftguyiuoihgufyyguhij ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2014-12-22 16:03:54', 'null'),
(15, 'il prof.De Lucia Andrea ha accettato la richiesta di Palumbo Ciro per avviare un lavoro di tesi ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2014-12-22 16:05:01', 'null'),
(16, 'il prof.De Lucia Andrea ha convalidato la richiesta di Palumbo Ciro per confermare il completamento del lavoro di tesi.  ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2014-12-22 16:09:32', 'null'),
(17, 'il prof.De Lucia Andrea ha accettato la richiesta di Palumbo Ciro per avviare un lavoro di tesi. La seduta di laurea è prevista il giorno 2015-01-23 ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2014-12-22 16:10:30', 'null'),
(18, 'il prof.null ha rifiutato la richiesta di null per confermare il completamento del lavoro di tesi. Controlla che tutti i campi siano stati compilati correttamente o prova a contattare il docente. ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2014-12-22 16:33:35', 'null'),
(19, 'il prof.De Lucia Andrea ha rifiutato la richiesta di Palumbo Ciro per confermare il completamento del lavoro di tesi. Controlla che tutti i campi siano stati compilati correttamente o prova a contattare il docente. ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2014-12-22 16:42:51', 'null'),
(20, 'lo studente Utente Nuovo ha inviato una richiesta di tesi al prof. De Lucia Andrea con il seguente messaggio: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaassssssssss ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2014-12-22 16:45:44', 'null'),
(21, 'il prof.De Lucia Andrea ha rifiutato la richiesta di Utente Nuovo per avviare un lavoro di tesi ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2014-12-22 16:46:03', 'null'),
(22, 'lo studente Catolino Golli ha inviato una richiesta di tesi al prof. De Lucia Andrea con il seguente messaggio: asdsacacsascsacscaaccssacascsccacacacasccsacacacacacac ', 'ANDDLC65M0N409C', 'CTLGMM91A71B519A', '2015-01-11 16:29:21', 'richiesta'),
(23, 'il prof.De Lucia Andrea ha accettato la richiesta di Catolino Golli per avviare un lavoro di tesi ', 'ANDDLC65M0N409C', 'CTLGMM91A71B519A', '2015-01-11 16:37:27', 'accetta'),
(24, 'il prof.De Lucia Andrea ha accettato la richiesta di Palumbo Ciro per avviare un lavoro di tesi. La seduta di laurea è prevista il giorno 2008-12-14 ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2015-01-11 16:37:57', 'accetta'),
(25, 'lo studente Catolino Golli ha apportato delle modifiche alla propria Tesi in Corso. ', 'ANDDLC65M0N409C', 'CTLGMM91A71B519A', '2015-01-11 17:32:33', 'modifica'),
(26, 'lo studente Catolino Golli ha apportato delle modifiche alla propria Tesi in Corso. ', 'ANDDLC65M0N409C', 'CTLGMM91A71B519A', '2015-01-11 17:33:19', 'modifica'),
(27, 'lo studente Catolino Golli ha apportato delle modifiche alla propria Tesi in Corso. ', 'ANDDLC65M0N409C', 'CTLGMM91A71B519A', '2015-01-11 17:36:28', 'modifica'),
(28, 'lo studente Catolino Golli ha apportato delle modifiche alla propria Tesi in Corso. ', 'ANDDLC65M0N409C', 'CTLGMM91A71B519A', '2015-01-11 17:38:10', 'modifica'),
(29, 'lo studente Catolino Golli ha apportato delle modifiche alla propria Tesi in Corso. ', 'ANDDLC65M0N409C', 'CTLGMM91A71B519A', '2015-01-11 17:38:31', 'modifica'),
(30, 'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ', 'FLMFRR65M0A509S', 'NNNDNL89M01A509C', '2015-01-11 17:48:55', 'modifica'),
(31, 'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ', 'FLMFRR65M0A509S', 'NNNDNL89M01A509C', '2015-01-11 17:49:20', 'modifica'),
(32, 'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ', 'FLMFRR65M0A509S', 'NNNDNL89M01A509C', '2015-01-11 17:50:25', 'modifica'),
(33, 'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ', 'FLMFRR65M0A509S', 'NNNDNL89M01A509C', '2015-01-11 17:51:22', 'modifica'),
(34, 'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ', 'FLMFRR65M0A509S', 'NNNDNL89M01A509C', '2015-01-11 17:52:41', 'modifica'),
(35, 'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ', 'FLMFRR65M0A509S', 'NNNDNL89M01A509C', '2015-01-11 17:53:43', 'modifica'),
(36, 'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ', 'FLMFRR65M0A509S', 'NNNDNL89M01A509C', '2015-01-11 17:57:34', 'modifica'),
(37, 'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ', 'FLMFRR65M0A509S', 'NNNDNL89M01A509C', '2015-01-11 18:03:35', 'modifica'),
(38, 'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ', 'FLMFRR65M0A509S', 'NNNDNL89M01A509C', '2015-01-11 18:04:14', 'modifica'),
(39, 'lo studente Palumbo Ciro ha inviato una richiesta di tesi al prof. De Lucia Andrea con il seguente messaggio: ssssssssssssssssssssseeempre la steeeeeessa cosa. Messaggio fittizzio, prof si ricorda di me? ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2015-01-11 20:45:54', 'richiesta'),
(40, 'il prof.De Lucia Andrea ha accettato la richiesta di Palumbo Ciro per avviare un lavoro di tesi ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2015-01-11 21:01:25', 'accetta'),
(41, 'lo studente Palumbo Ciro ha inviato una richiesta di tesi al prof. De Lucia Andrea con il seguente messaggio: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2015-01-12 09:52:26', 'richiesta'),
(42, 'il prof.De Lucia Andrea ha rifiutato la richiesta di Palumbo Ciro per avviare un lavoro di tesi ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2015-01-12 09:52:37', 'rifiuta'),
(43, 'lo studente Palumbo Ciro ha inviato una richiesta di tesi al prof. De Lucia Andrea con il seguente messaggio: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2015-01-12 09:53:07', 'richiesta'),
(44, 'il prof.De Lucia Andrea ha accettato la richiesta di Palumbo Ciro per avviare un lavoro di tesi ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2015-01-12 09:53:17', 'accetta'),
(45, 'lo studente Palumbo Ciro ha inviato una richiesta per il completamento della tesi al prof. De Lucia Andrea ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2015-01-12 09:53:59', 'richiesta_completamento'),
(46, 'il prof.De Lucia Andrea ha rifiutato la richiesta di Palumbo Ciro di completamento del lavoro di tesi. Controlla che tutti i campi siano stati compilati correttamente o prova a contattare il docente. ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2015-01-12 09:54:12', 'rifiuta'),
(47, 'lo studente Palumbo Ciro ha inviato una richiesta per il completamento della tesi al prof. De Lucia Andrea ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2015-01-12 09:54:33', 'richiesta_completamento'),
(48, 'il prof.De Lucia Andrea ha convalidato la richiesta di Palumbo Ciro per confermare il completamento del lavoro di tesi.  ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2015-01-12 09:54:43', 'accetta'),
(49, 'lo studente Palumbo Ciro ha inviato una richiesta di tesi al prof. De Lucia Andrea con il seguente messaggio: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2015-01-12 10:10:52', 'richiesta'),
(50, 'il prof.De Lucia Andrea ha accettato la richiesta di Palumbo Ciro per avviare un lavoro di tesi ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2015-01-12 10:11:09', 'accetta'),
(51, 'lo studente Palumbo Ciro ha apportato delle modifiche alla propria Tesi in Corso. ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2015-01-12 10:11:28', 'modifica'),
(52, 'lo studente Palumbo Ciro ha apportato delle modifiche alla propria Tesi in Corso. ', 'ANDDLC65M0N409C', 'CRRPLMC91M0N409C', '2015-01-12 10:13:45', 'modifica');

-- --------------------------------------------------------

--
-- Struttura della tabella `class`
--

CREATE TABLE IF NOT EXISTS `class` (
  `title` varchar(50) NOT NULL,
  `teaching_matricula` varchar(10) NOT NULL,
  PRIMARY KEY (`title`,`teaching_matricula`),
  KEY `title` (`title`),
  KEY `teaching_matricula` (`teaching_matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `class_event`
--

CREATE TABLE IF NOT EXISTS `class_event` (
  `FK_Class` int(11) NOT NULL,
  `FK_Event` int(11) NOT NULL,
  PRIMARY KEY (`FK_Class`,`FK_Event`),
  KEY `FK_Event` (`FK_Event`),
  KEY `FK_Class` (`FK_Class`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `class_notice`
--

CREATE TABLE IF NOT EXISTS `class_notice` (
  `FK_Class` int(11) NOT NULL,
  `FK_Event` int(11) NOT NULL,
  PRIMARY KEY (`FK_Class`,`FK_Event`),
  KEY `FK_Event` (`FK_Event`),
  KEY `FK_Class` (`FK_Class`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `collaboration`
--

CREATE TABLE IF NOT EXISTS `collaboration` (
  `idCollaboration` int(20) NOT NULL AUTO_INCREMENT,
  `istitution` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `FK_Student` varchar(16) NOT NULL,
  PRIMARY KEY (`idCollaboration`),
  KEY `FK_Student` (`FK_Student`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `curriculum`
--

CREATE TABLE IF NOT EXISTS `curriculum` (
  `title` varchar(100) NOT NULL,
  `matricula` varchar(45) NOT NULL,
  `degree_matricula` varchar(5) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`matricula`),
  KEY `fk_curriculum_degree1_idx` (`degree_matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `curriculum_teaching`
--

CREATE TABLE IF NOT EXISTS `curriculum_teaching` (
  `curriculum_matricula` varchar(45) NOT NULL,
  `teaching_matricula` varchar(10) NOT NULL,
  KEY `curriculum_matricula` (`curriculum_matricula`),
  KEY `teaching_matricula` (`teaching_matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `cycle`
--

CREATE TABLE IF NOT EXISTS `cycle` (
  `cycle_number` int(11) NOT NULL,
  `title` varchar(20) NOT NULL,
  PRIMARY KEY (`cycle_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `cycle`
--

INSERT INTO `cycle` (`cycle_number`, `title`) VALUES
(1, 'studente triennale'),
(2, 'studente magistrale'),
(3, 'phd student'),
(4, 'professore'),
(5, 'azienda');

-- --------------------------------------------------------

--
-- Struttura della tabella `degree`
--

CREATE TABLE IF NOT EXISTS `degree` (
  `title` varchar(500) NOT NULL,
  `matricula` varchar(5) NOT NULL,
  `link` varchar(500) DEFAULT NULL,
  `department_abbreviation` varchar(100) NOT NULL,
  `cycle_number` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`matricula`),
  KEY `fk_degree_department1_idx` (`department_abbreviation`),
  KEY `fk_degree_cycle1_idx` (`cycle_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `degree`
--

INSERT INTO `degree` (`title`, `matricula`, `link`, `department_abbreviation`, `cycle_number`, `active`) VALUES
('MIT', '02255', NULL, 'distra', 2, 1),
('EM', '03345', NULL, 'distra', 1, 1),
('CM', '04453', NULL, 'distra', 2, 1),
('DMIT', '88876', NULL, 'distra', 3, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `abbreviation` varchar(50) NOT NULL,
  `title` varchar(500) DEFAULT NULL,
  `url_moodle` varchar(1000) DEFAULT NULL,
  `token` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`abbreviation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `department`
--

INSERT INTO `department` (`abbreviation`, `title`, `url_moodle`, `token`) VALUES
('distra', 'Dipartimento di Studi e Ricerche Aziendali ', NULL, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `event`
--

CREATE TABLE IF NOT EXISTS `event` (
  `idEvent` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `hours` int(1) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`idEvent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `event_professor`
--

CREATE TABLE IF NOT EXISTS `event_professor` (
  `FK_Event` int(11) NOT NULL,
  `FK_Professor` varchar(16) NOT NULL,
  PRIMARY KEY (`FK_Event`,`FK_Professor`),
  KEY `FK_Professor` (`FK_Professor`),
  KEY `FK_Event` (`FK_Event`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `lesson`
--

CREATE TABLE IF NOT EXISTS `lesson` (
  `idLesson` int(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  `speaker` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `place` varchar(45) NOT NULL,
  `FK_Event` int(11) NOT NULL,
  PRIMARY KEY (`idLesson`),
  KEY `FK_Event` (`FK_Event`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `lesson_student`
--

CREATE TABLE IF NOT EXISTS `lesson_student` (
  `FK_Lesson` int(20) NOT NULL,
  `FK_Student` varchar(16) NOT NULL,
  PRIMARY KEY (`FK_Lesson`,`FK_Student`),
  KEY `FK_Student` (`FK_Student`),
  KEY `FK_Lesson` (`FK_Lesson`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `mission`
--

CREATE TABLE IF NOT EXISTS `mission` (
  `idMission` int(20) NOT NULL AUTO_INCREMENT,
  `place` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `FK_Student` varchar(16) NOT NULL,
  PRIMARY KEY (`idMission`),
  KEY `FK_Student` (`FK_Student`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `module`
--

CREATE TABLE IF NOT EXISTS `module` (
  `title` varchar(50) NOT NULL,
  `teaching_matricula` varchar(10) NOT NULL,
  PRIMARY KEY (`title`,`teaching_matricula`),
  KEY `fk_module_teaching1_idx` (`teaching_matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `notice`
--

CREATE TABLE IF NOT EXISTS `notice` (
  `idNotice` int(11) NOT NULL,
  `object` varchar(45) NOT NULL,
  `text` text NOT NULL,
  PRIMARY KEY (`idNotice`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `organization`
--

CREATE TABLE IF NOT EXISTS `organization` (
  `vat_number` varchar(16) NOT NULL DEFAULT '',
  `company_name` varchar(45) NOT NULL DEFAULT '',
  `city` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `fk_account` varchar(100) NOT NULL,
  `fk_external_tutor` varchar(16) DEFAULT NULL,
  `fk_professor` varchar(16) NOT NULL,
  PRIMARY KEY (`vat_number`),
  KEY `fk_acc` (`fk_account`),
  KEY `fk_tutor` (`fk_external_tutor`),
  KEY `fk_prof` (`fk_professor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `pending_acceptance`
--

CREATE TABLE IF NOT EXISTS `pending_acceptance` (
  `id_pending_acceptance` int(11) NOT NULL AUTO_INCREMENT,
  `request_date` date DEFAULT NULL,
  `fk_person` varchar(16) NOT NULL,
  PRIMARY KEY (`id_pending_acceptance`),
  KEY `fk_StudentAttendence_Student1_idx` (`fk_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `SSN` varchar(16) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `zip_code` varchar(45) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `citizenship` varchar(45) DEFAULT NULL,
  `Account_email` varchar(100) NOT NULL,
  `Department_abbreviation` varchar(50) NOT NULL,
  `web_page` varchar(300) DEFAULT NULL,
  `university` varchar(200) DEFAULT NULL,
  `matricula` varchar(10) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `cycle` int(11) DEFAULT NULL,
  `degree_matricula` varchar(5) DEFAULT NULL,
  `cover_letter` text,
  PRIMARY KEY (`SSN`),
  KEY `fk_Person_Account_idx` (`Account_email`),
  KEY `fk_Person_Department1_idx` (`Department_abbreviation`),
  KEY `fk_cycle` (`cycle`),
  KEY `degree_matricula` (`degree_matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `person`
--

INSERT INTO `person` (`SSN`, `name`, `surname`, `phone`, `city`, `address`, `zip_code`, `gender`, `citizenship`, `Account_email`, `Department_abbreviation`, `web_page`, `university`, `matricula`, `position`, `cycle`, `degree_matricula`, `cover_letter`) VALUES
('ANDDLC65M0N409C', 'Andrea', 'De Lucia', '3384673356', 'Salerno', NULL, NULL, 'M', NULL, 'adl@unisa.it', 'DISTRA', 'ww.delucia.it', 'Università di Salerno', '022552585', 'professor', 1, '02255', NULL),
('CRRPLMC91M0N409C', 'Ciro', 'Palumbo', NULL, NULL, NULL, NULL, 'M', NULL, 'ciro@unisa.it', 'DISTRA', NULL, NULL, '022552585', 'student', 1, '02255', NULL),
('CSMGRT89M0A309C', 'Cosimo', 'Grattacaso', '3334536354', 'Paestum', 'via sicilia', '87832', 'M', 'italiana', 'cosimo@gmail.com', 'DISTRA', 'www.cosimo.it', 'Università di Salerno', '022274382', 'student', 1, '02255', NULL),
('CTLGMM91A71B519A', 'Golli', 'Catolino', '3272844649', 'Baranello', 'Contrada Gaudo n. 27', '86011', 'F', 'Italiana', 'gemma.catolino91@gmail.com', 'distra', 'http://www.unisa.it/docenti/filomenaferrucci/index', 'UniversitÃ  degli Studi di Salerno', '022552585', 'null', 1, '02255', '     Nel mio percorso di studente di dottorato del curriculum EDAP, intendo occuparmi del management delle Pubbliche Amministrazioni, in ottica sistemico-relazionale. Lo studio della gestione delle PA sarÃ????Ã???Ã??Ã?Â  sviluppato attraverso lÃ???Ã??Ã?Â¢??analisi di nuovi e piÃ????Ã???Ã??Ã?Â¹ recenti studi che propongono per il management pubblico nuovi approcci come ad esempio il Ã???Ã??Ã?Â¢??Public Service-DominantÃ???Ã??Ã?Â¢??, ovvero un approccio basato sui servizi pubblici e che enfatizza i processi di trasformazione della conoscenza. I miei interessi dunque, includono lo studio di quelle prospettive teoriche che tendono a fornire una lettura relazionale de     '),
('dfgt678ujgyt56yg', 'Mario', 'Rossi', NULL, NULL, NULL, NULL, NULL, NULL, 'test@test.it', 'distra', NULL, NULL, '022552585', NULL, NULL, '02255', NULL),
('DMNSNS90M0N309C', 'Damiano', 'Senese', '3456789098', 'Salerno', 'via casilina', '08768', 'M', 'italiano', 'damiano@gmail.com', 'DISTRA', 'www.dami.it', 'Università degli Studi di Salerno', '022552283', 'student', 1, '02255', NULL),
('FLMFRR65M0A509S', 'Filomena', 'Ferrucci', NULL, NULL, NULL, NULL, 'F', 'italiana', 'ferrucci@gmail.com', 'DISTRA', NULL, 'Università degli Studi di Salerno', '022552585', 'professor', 1, '02255', NULL),
('FRRMHL89M41H501S', 'Michela', 'Ferrante', '3456787917', 'Frosinone', 'via casilina', '03100', 'F', 'Italiana', 'mikela_f@hotmail.it', 'DISTRA', 'www.miki.it', 'Università degli Studi di Salerno', '022554678', 'student', 1, '02255', NULL),
('ghhnui78a56f234e', 'fjhsdkgfsghjfas', 'fbhjsdafhjdsakds', '642386742', 'dfsfdsdsafsd', 'sddsddsdsaf', '34789', 'M', 'Italiana', 'ballo@hotmail.it', 'distra', 'null', 'UniversitÃ  degli Studi di Salerno', '022552585', 'null', 2, '02255', 'null'),
('kfurhdjtirjehdfg', 'Elisa', 'DEugenio', '3334350369', 'Silvi', 'Via abruzzo 48', '64028', 'F', NULL, 'elyx24@hotmail.it', 'distra', NULL, NULL, '022552585', NULL, NULL, '02255', NULL),
('MRRRSS88M0A309C', 'Mario', 'Rossi', '3334536354', 'Napoli', 'via le lame', '83645', 'M', 'italiana', 'mariorossi@gmail.com', 'DISTRA', 'www.rossi.it', 'Università di Salerno', '0222736745', 'student', 1, '02255', NULL),
('MRRTDD85M0A309S', 'Maria', 'Taddeo', '3487654362', 'Matera', 'via roma', '65374', 'F', 'italiana', 'm.taddeo@gmail.com', 'DISTRA', 'taddeo.it', 'Università di Salerno', '0222736453', 'student', 1, '02255', NULL),
('MRZTCC62M0A309C', 'Maurizio', 'Tucci', '3456253625', 'Salerno', 'via verdi', '84536', 'M', 'italiana', 'tucci@gmail.com', 'DISTRA', 'tucci.com', 'Università di Salerno', '022552585', 'professor', 1, '02255', NULL),
('mtfgjl90u56t234d', 'Mario', 'Ciao', '5410973', 'salerno', 'via via', '87356', 'M', 'Italiana', 'uio@hotmail.it', 'distra', 'null', 'UniversitÃ  degli Studi di Salerno', '022552585', 'null', 1, '03345', 'null'),
('NNNDNL89M01A509C', 'Daniele', 'Iannone', '3456786764', 'Montoro', 'via verdi', '87654', 'M', 'italiano', 'd.iannone@gmail.com', 'DISTRA', NULL, 'Università degli Studi di Salerno', '022554736', 'student', 1, '02255', NULL),
('quejdorp506jtugh', 'Filomena', 'Ferrucci', '5556985645', 'Salerno', 'via Salerno', '45678', 'F', NULL, 'ff@unisa.it', 'distra', 'http://www.unisa.it/docenti/filomenaferrucci/index', 'Unisa', '022552585', NULL, NULL, '02255', NULL),
('RTTFRN68M0A309S', 'Rita', 'Francese', '3487652362', 'Fisciano', 'via le lame', '65334', 'F', 'italiana', 'francese@gmail.com', 'DISTRA', 'www.francese.it', 'Università di Salerno', '022552585', 'professor', 1, '02255', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `phdclass`
--

CREATE TABLE IF NOT EXISTS `phdclass` (
  `idClass` int(20) NOT NULL AUTO_INCREMENT,
  `FK_PhdCycle` int(11) NOT NULL,
  `FK_PhdCurriculum` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`idClass`),
  UNIQUE KEY `FK_PhdCycle` (`FK_PhdCycle`,`FK_PhdCurriculum`),
  KEY `FK_PhdCurriculum` (`FK_PhdCurriculum`),
  KEY `FK_cycle` (`FK_PhdCycle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `phdcurriculum`
--

CREATE TABLE IF NOT EXISTS `phdcurriculum` (
  `name` varchar(100) NOT NULL,
  `description` text,
  `FK_Professor` varchar(16) DEFAULT '',
  PRIMARY KEY (`name`),
  KEY `FK_Professor` (`FK_Professor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `phdcycle`
--

CREATE TABLE IF NOT EXISTS `phdcycle` (
  `idPhdCycle` int(11) NOT NULL,
  `description` text,
  `year` year(4) NOT NULL,
  `FK_Professor` varchar(16) DEFAULT '',
  PRIMARY KEY (`idPhdCycle`),
  KEY `FK_Professor` (`FK_Professor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `phdcycle`
--

INSERT INTO `phdcycle` (`idPhdCycle`, `description`, `year`, `FK_Professor`) VALUES
(15, 'Il corso di Dottorato di Ricerca in Management & Information Technology ha come obiettivo la formazione di specialisti della ricerca in ambito economico-aziendale ed informatico. Il corso è strutturato in tre curricula, denominati (i) Economia e Direzione delle Aziende Pubbliche, (i) Marketing e Comunicazione e (iii) Informatica, Sistemi Informativi e Tecnologie del Software. Attraverso i tre curricula, il corso di Dottorato intende formare figure professionali diverse, ma che riescano ad interagire per la soluzione di problemi complessi in ambito economico-aziendale, grazie all''utilizzo di tecnologie dell''informazione innovative e ad un approccio inter-disciplinare che favorisca la reciproca condivisione di idee e competenze. Il primo curriculum mira a formare specialisti nel settore del management di enti, istituzioni ed aziende afferenti al settore pubblico, con conoscenze relative a principi, teorie e modelli di gestione dei processi di innovazione nella Pubblica Amministrazione. Il secondo curriculum mira a formare specialisti in grado di utilizzare le più avanzate, innovative ed affidabili metodologie di ricerca scientifica in campo economico-sociale, con particolare riferimento al marketing ed alla comunicazione. Infine, il terzo curriculum mira a formare specialisti nel settore dell''Informatica, con conoscenza degli aspetti teorici, metodologici e sperimentali dei sistemi informativi, dell''ingegneria del software, dei dati e della conoscenza, dell''elaborazione di immagini e dell''interazione uomo-macchina, con applicazioni in particolare alla economia e alla gestione aziendale.\r\n\r\nIl completamento del Corso di Dottorato ed il superamento dell''esame finale consente per tutti e tre i curricula lo svolgimento di attività di ricerca in ambito accademico, nei settori dell''economia aziendale e dell''informatica, costituendo un titolo legalmente riconosciuto nei concorsi universitari, nonché in enti di ricerca e nelle divisioni ricerca e sviluppo di aziende. Inoltre, la qualità del percorso formativo e le competenze specialistiche acquisite consentono l''inserimento dei dottori di ricerca anche nel mondo del lavoro e delle professioni, nella Pubblica Amministrazione e nelle aziende.', 2014, NULL),
(16, 'Il corso di Dottorato di Ricerca in Management & Information Technology ha come obiettivo la formazione di specialisti della ricerca in ambito economico-aziendale ed informatico. Il corso è strutturato in tre curricula, denominati (i) Economia e Direzione delle Aziende Pubbliche, (i) Marketing e Comunicazione e (iii) Informatica, Sistemi Informativi e Tecnologie del Software. Attraverso i tre curricula, il corso di Dottorato intende formare figure professionali diverse, ma che riescano ad interagire per la soluzione di problemi complessi in ambito economico-aziendale, grazie all''utilizzo di tecnologie dell''informazione innovative e ad un approccio inter-disciplinare che favorisca la reciproca condivisione di idee e competenze. Il primo curriculum mira a formare specialisti nel settore del management di enti, istituzioni ed aziende afferenti al settore pubblico, con conoscenze relative a principi, teorie e modelli di gestione dei processi di innovazione nella Pubblica Amministrazione. Il secondo curriculum mira a formare specialisti in grado di utilizzare le più avanzate, innovative ed affidabili metodologie di ricerca scientifica in campo economico-sociale, con particolare riferimento al marketing ed alla comunicazione. Infine, il terzo curriculum mira a formare specialisti nel settore dell''Informatica, con conoscenza degli aspetti teorici, metodologici e sperimentali dei sistemi informativi, dell''ingegneria del software, dei dati e della conoscenza, dell''elaborazione di immagini e dell''interazione uomo-macchina, con applicazioni in particolare alla economia e alla gestione aziendale.\n\nIl completamento del Corso di Dottorato ed il superamento dell''esame finale consente per tutti e tre i curricula lo svolgimento di attività di ricerca in ambito accademico, nei settori dell''economia aziendale e dell''informatica, costituendo un titolo legalmente riconosciuto nei concorsi universitari, nonché in enti di ricerca e nelle divisioni ricerca e sviluppo di aziende. Inoltre, la qualità del percorso formativo e le competenze specialistiche acquisite consentono l''inserimento dei dottori di ricerca anche nel mondo del lavoro e delle professioni, nella Pubblica Amministrazione e nelle aziende.', 2014, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `professor_phdcurriculum`
--

CREATE TABLE IF NOT EXISTS `professor_phdcurriculum` (
  `FK_Professor` varchar(16) NOT NULL,
  `FK_PhdCurriculum` varchar(100) NOT NULL,
  PRIMARY KEY (`FK_Professor`,`FK_PhdCurriculum`),
  KEY `FK_Professor` (`FK_Professor`),
  KEY `FK_PhdCurriculum` (`FK_PhdCurriculum`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `professor_phdcycle`
--

CREATE TABLE IF NOT EXISTS `professor_phdcycle` (
  `FK_Professor` varchar(16) NOT NULL,
  `FK_PhdCycle` int(11) NOT NULL,
  PRIMARY KEY (`FK_Professor`,`FK_PhdCycle`),
  KEY `FK_PhdCycle` (`FK_PhdCycle`),
  KEY `FK_Professor` (`FK_Professor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `professor_student`
--

CREATE TABLE IF NOT EXISTS `professor_student` (
  `FK_Professor` varchar(16) NOT NULL,
  `FK_Student` varchar(16) NOT NULL,
  PRIMARY KEY (`FK_Professor`,`FK_Student`),
  KEY `FK_Professor` (`FK_Professor`),
  KEY `FK_Student` (`FK_Student`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `prof_module_class`
--

CREATE TABLE IF NOT EXISTS `prof_module_class` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `class_title` varchar(50) NOT NULL,
  `teaching_matricula` varchar(10) NOT NULL,
  `module_title` varchar(50) NOT NULL,
  `email_account` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `class_title` (`class_title`),
  KEY `module_teaching_matricula` (`teaching_matricula`),
  KEY `module_title` (`module_title`),
  KEY `email_account` (`email_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `publication`
--

CREATE TABLE IF NOT EXISTS `publication` (
  `idPublication` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `authors` varchar(45) NOT NULL,
  `abstract` text NOT NULL,
  `file` longblob,
  `year` varchar(4) NOT NULL DEFAULT '',
  `type` varchar(45) NOT NULL,
  `publicationIssue` varchar(45) NOT NULL,
  `numberPages` int(11) NOT NULL,
  `FK_Student` varchar(16) NOT NULL,
  PRIMARY KEY (`idPublication`),
  KEY `FK_Student` (`FK_Student`),
  KEY `FK_Student_2` (`FK_Student`),
  KEY `FK_Student_3` (`FK_Student`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `rejected_training_message`
--

CREATE TABLE IF NOT EXISTS `rejected_training_message` (
  `id_rejected_training_message` int(11) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `fk_person` varchar(16) NOT NULL,
  PRIMARY KEY (`id_rejected_training_message`),
  KEY `fk_RejectedTrainingMessage_Student1_idx` (`fk_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `student_information`
--

CREATE TABLE IF NOT EXISTS `student_information` (
  `curriculum_vitae_path` varchar(200) DEFAULT NULL,
  `accademic_transcript_path` varchar(200) DEFAULT NULL,
  `SSN` varchar(16) NOT NULL,
  `fk_student_status` int(11) NOT NULL,
  PRIMARY KEY (`SSN`),
  UNIQUE KEY `fk_status` (`fk_student_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `student_phdclass`
--

CREATE TABLE IF NOT EXISTS `student_phdclass` (
  `FK_Student` varchar(16) CHARACTER SET utf8 NOT NULL,
  `FK_PhdClass` int(20) NOT NULL,
  PRIMARY KEY (`FK_Student`,`FK_PhdClass`),
  UNIQUE KEY `FK_Student` (`FK_Student`),
  KEY `FK_PhdClass` (`FK_PhdClass`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `student_status`
--

CREATE TABLE IF NOT EXISTS `student_status` (
  `id_student_status` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_student_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dump dei dati per la tabella `tag`
--

INSERT INTO `tag` (`ID`, `Name`) VALUES
(1, 'Open Data'),
(2, 'IS'),
(3, 'Service'),
(4, 'Big Data'),
(5, 'Control System'),
(6, 'Management');

-- --------------------------------------------------------

--
-- Struttura della tabella `teaching`
--

CREATE TABLE IF NOT EXISTS `teaching` (
  `matricula` varchar(10) NOT NULL,
  `title` varchar(500) NOT NULL,
  `abbreviation` varchar(10) NOT NULL,
  `link` varchar(500) NOT NULL,
  `year` int(11) NOT NULL,
  `semester` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `esse3_content` longtext,
  PRIMARY KEY (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `thesis`
--

CREATE TABLE IF NOT EXISTS `thesis` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Start_Date` date DEFAULT NULL,
  `End_Date` date DEFAULT NULL,
  `Expected_End_Date` date DEFAULT NULL,
  `Title` varchar(180) DEFAULT NULL,
  `Abstract` longtext,
  `Description` varchar(255) DEFAULT NULL,
  `ID_Student` varchar(16) NOT NULL,
  `Thesis_Status` enum('0','1','2','3','4','5') DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `fk_person` (`ID_Student`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Dump dei dati per la tabella `thesis`
--

INSERT INTO `thesis` (`ID`, `Start_Date`, `End_Date`, `Expected_End_Date`, `Title`, `Abstract`, `Description`, `ID_Student`, `Thesis_Status`) VALUES
(1, '2009-09-14', '2005-12-14', '2005-12-14', 'Open Data', 'Abstract della tesi "Open Data"', 'Descrizione della tesi "Open Data"', 'FRRMHL89M41H501S', '0'),
(2, '2013-03-03', '2013-12-19', '2013-09-17', 'La similarità dei motori scacchistici ai giorni nostri ', 'Abstract della tesi "La similarità dei motori scacchistici ai giorni nostri"', 'Descrizione della tesi "IS"', 'NNNDNL89M01A509C', '1'),
(4, '2011-05-14', '2011-11-14', '2011-11-14', 'La mia tesi', 'Abstract della tesi "La mia tesi"', 'Descrizione della tesi "La mia tesi"', 'DMNSNS90M0N309C', '2'),
(5, '2014-09-14', '2014-12-19', '2015-03-15', 'Sistemi di controllo', 'Abstract della tesi "Sistemi di controllo"', 'Descrizione della tesi "Sistemi di controllo"', 'CSMGRT89M0A309C', '0'),
(6, '2014-01-14', '2014-12-19', '2015-03-15', 'Management', 'Abstract della tesi "Management"', 'Descrizione della tesi "Management"', 'MRRRSS88M0A309C', '1'),
(7, '2005-01-14', '2005-08-09', '2005-10-15', 'App Android', 'Abstract della tesi "App Android"', 'Descrizione della tesi "App Android"', 'MRRTDD85M0A309S', '0');

-- --------------------------------------------------------

--
-- Struttura della tabella `thesis_supervisor`
--

CREATE TABLE IF NOT EXISTS `thesis_supervisor` (
  `ID_Thesis` int(11) NOT NULL,
  `ID_Professor` varchar(16) NOT NULL,
  KEY `ID_Thesis` (`ID_Thesis`),
  KEY `ID_Professor` (`ID_Professor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `thesis_supervisor`
--

INSERT INTO `thesis_supervisor` (`ID_Thesis`, `ID_Professor`) VALUES
(1, 'FLMFRR65M0A509S'),
(2, 'FLMFRR65M0A509S'),
(4, 'FLMFRR65M0A509S'),
(5, 'MRZTCC62M0A309C'),
(6, 'RTTFRN68M0A309S'),
(7, 'MRZTCC62M0A309C');

-- --------------------------------------------------------

--
-- Struttura della tabella `thesis_tag`
--

CREATE TABLE IF NOT EXISTS `thesis_tag` (
  `ID_thesis` int(11) NOT NULL,
  `ID_tag` int(11) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `fk_thesis` (`ID_thesis`) USING BTREE,
  KEY `fk_tag` (`ID_tag`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dump dei dati per la tabella `thesis_tag`
--

INSERT INTO `thesis_tag` (`ID_thesis`, `ID_tag`, `ID`) VALUES
(1, 1, 1),
(2, 2, 2),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6);

-- --------------------------------------------------------

--
-- Struttura della tabella `training_offer`
--

CREATE TABLE IF NOT EXISTS `training_offer` (
  `id_training_offer` int(11) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `fk_person` varchar(16) NOT NULL,
  `fk_department` varchar(50) DEFAULT NULL,
  `fk_organization` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id_training_offer`),
  KEY `fk_OfferTraining_Professor1_idx` (`fk_person`),
  KEY `fk_OfferTraining_Department1_idx` (`fk_department`),
  KEY `fk_OfferTraining_Organization1` (`fk_organization`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `training_request`
--

CREATE TABLE IF NOT EXISTS `training_request` (
  `id_training_request` int(11) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `title` varchar(45) DEFAULT NULL,
  `fk_training_status` int(11) NOT NULL,
  `fk_person` varchar(16) NOT NULL,
  `fk_organization` varchar(16) DEFAULT NULL,
  `student_information_SSN` varchar(16) DEFAULT '',
  PRIMARY KEY (`id_training_request`),
  KEY `fk_ClaimTraining_ClaimStatus1_idx` (`fk_training_status`),
  KEY `fk_ClaimTraining_Professor1_idx` (`fk_person`),
  KEY `training_request_ibfk_2` (`fk_organization`),
  KEY `fk_training_request_student_information1` (`student_information_SSN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `training_status`
--

CREATE TABLE IF NOT EXISTS `training_status` (
  `id_training_status` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_training_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `attachment`
--
ALTER TABLE `attachment`
  ADD CONSTRAINT `attachment_ibfk_1` FOREIGN KEY (`ID_Thesis`) REFERENCES `thesis` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Limiti per la tabella `chronology`
--
ALTER TABLE `chronology`
  ADD CONSTRAINT `chronology_ibfk_1` FOREIGN KEY (`ID_Professor`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `chronology_ibfk_2` FOREIGN KEY (`ID_Student`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `fk_class_teaching1` FOREIGN KEY (`teaching_matricula`) REFERENCES `teaching` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `class_event`
--
ALTER TABLE `class_event`
  ADD CONSTRAINT `class_event_ibfk_2` FOREIGN KEY (`FK_Event`) REFERENCES `event` (`idEvent`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `class_notice`
--
ALTER TABLE `class_notice`
  ADD CONSTRAINT `class_notice_ibfk_1` FOREIGN KEY (`FK_Event`) REFERENCES `event` (`idEvent`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `collaboration`
--
ALTER TABLE `collaboration`
  ADD CONSTRAINT `collaboration_ibfk_1` FOREIGN KEY (`FK_Student`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `curriculum`
--
ALTER TABLE `curriculum`
  ADD CONSTRAINT `fk_curriculum_degree1` FOREIGN KEY (`degree_matricula`) REFERENCES `degree` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `curriculum_teaching`
--
ALTER TABLE `curriculum_teaching`
  ADD CONSTRAINT `curriculum_teaching_ibfk_1` FOREIGN KEY (`curriculum_matricula`) REFERENCES `curriculum` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `curriculum_teaching_ibfk_2` FOREIGN KEY (`teaching_matricula`) REFERENCES `teaching` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `degree`
--
ALTER TABLE `degree`
  ADD CONSTRAINT `fk_degree_cycle1` FOREIGN KEY (`cycle_number`) REFERENCES `cycle` (`cycle_number`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_degree_department1` FOREIGN KEY (`department_abbreviation`) REFERENCES `department` (`abbreviation`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `event_professor`
--
ALTER TABLE `event_professor`
  ADD CONSTRAINT `event_professor_ibfk_1` FOREIGN KEY (`FK_Event`) REFERENCES `event` (`idEvent`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `event_professor_ibfk_2` FOREIGN KEY (`FK_Professor`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `lesson`
--
ALTER TABLE `lesson`
  ADD CONSTRAINT `lesson_ibfk_1` FOREIGN KEY (`FK_Event`) REFERENCES `event` (`idEvent`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `lesson_student`
--
ALTER TABLE `lesson_student`
  ADD CONSTRAINT `lesson_student_ibfk_1` FOREIGN KEY (`FK_Student`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lesson_student_ibfk_2` FOREIGN KEY (`FK_Lesson`) REFERENCES `lesson` (`idLesson`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `mission`
--
ALTER TABLE `mission`
  ADD CONSTRAINT `mission_ibfk_1` FOREIGN KEY (`FK_Student`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `module`
--
ALTER TABLE `module`
  ADD CONSTRAINT `fk_module_teaching1` FOREIGN KEY (`teaching_matricula`) REFERENCES `teaching` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `organization`
--
ALTER TABLE `organization`
  ADD CONSTRAINT `fk_acc` FOREIGN KEY (`fk_account`) REFERENCES `account` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_prof` FOREIGN KEY (`fk_professor`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tutor` FOREIGN KEY (`fk_external_tutor`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `pending_acceptance`
--
ALTER TABLE `pending_acceptance`
  ADD CONSTRAINT `fk_StudentAttendence_Student1` FOREIGN KEY (`fk_person`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `person`
--
ALTER TABLE `person`
  ADD CONSTRAINT `fk_Person_Account` FOREIGN KEY (`Account_email`) REFERENCES `account` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Person_Department1` FOREIGN KEY (`Department_abbreviation`) REFERENCES `department` (`abbreviation`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `person_ibfk_1` FOREIGN KEY (`cycle`) REFERENCES `cycle` (`cycle_number`),
  ADD CONSTRAINT `person_ibfk_2` FOREIGN KEY (`degree_matricula`) REFERENCES `degree` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `phdclass`
--
ALTER TABLE `phdclass`
  ADD CONSTRAINT `phdclass_ibfk_1` FOREIGN KEY (`FK_PhdCycle`) REFERENCES `phdcycle` (`idPhdCycle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `phdclass_ibfk_2` FOREIGN KEY (`FK_PhdCurriculum`) REFERENCES `phdcurriculum` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `phdcurriculum`
--
ALTER TABLE `phdcurriculum`
  ADD CONSTRAINT `phdcurriculum_ibfk_1` FOREIGN KEY (`FK_Professor`) REFERENCES `person` (`SSN`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `phdcycle`
--
ALTER TABLE `phdcycle`
  ADD CONSTRAINT `phdcycle_ibfk_1` FOREIGN KEY (`FK_Professor`) REFERENCES `person` (`SSN`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `professor_phdcycle`
--
ALTER TABLE `professor_phdcycle`
  ADD CONSTRAINT `professor_phdcycle_ibfk_1` FOREIGN KEY (`FK_Professor`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `professor_phdcycle_ibfk_2` FOREIGN KEY (`FK_PhdCycle`) REFERENCES `phdcycle` (`idPhdCycle`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `publication`
--
ALTER TABLE `publication`
  ADD CONSTRAINT `publication_ibfk_1` FOREIGN KEY (`FK_Student`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `rejected_training_message`
--
ALTER TABLE `rejected_training_message`
  ADD CONSTRAINT `rejected_training_message_ibfk_1` FOREIGN KEY (`fk_person`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `student_information`
--
ALTER TABLE `student_information`
  ADD CONSTRAINT `student_information_ibfk_1` FOREIGN KEY (`SSN`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_information_ibfk_2` FOREIGN KEY (`fk_student_status`) REFERENCES `student_status` (`id_student_status`) ON UPDATE CASCADE;

--
-- Limiti per la tabella `student_phdclass`
--
ALTER TABLE `student_phdclass`
  ADD CONSTRAINT `student_phdclass_ibfk_1` FOREIGN KEY (`FK_Student`) REFERENCES `person` (`SSN`),
  ADD CONSTRAINT `student_phdclass_ibfk_2` FOREIGN KEY (`FK_PhdClass`) REFERENCES `phdclass` (`idClass`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `thesis`
--
ALTER TABLE `thesis`
  ADD CONSTRAINT `thesis_ibfk_1` FOREIGN KEY (`ID_Student`) REFERENCES `person` (`SSN`);

--
-- Limiti per la tabella `thesis_supervisor`
--
ALTER TABLE `thesis_supervisor`
  ADD CONSTRAINT `thesis_supervisor_ibfk_1` FOREIGN KEY (`ID_Thesis`) REFERENCES `thesis` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `thesis_supervisor_ibfk_2` FOREIGN KEY (`ID_Professor`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `thesis_tag`
--
ALTER TABLE `thesis_tag`
  ADD CONSTRAINT `thesis_tag_ibfk_1` FOREIGN KEY (`ID_thesis`) REFERENCES `thesis` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `thesis_tag_ibfk_2` FOREIGN KEY (`ID_tag`) REFERENCES `tag` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Limiti per la tabella `training_offer`
--
ALTER TABLE `training_offer`
  ADD CONSTRAINT `fk_OfferTraining_Department1` FOREIGN KEY (`fk_department`) REFERENCES `department` (`abbreviation`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_OfferTraining_Organization1` FOREIGN KEY (`fk_organization`) REFERENCES `organization` (`vat_number`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_OfferTraining_Professor1` FOREIGN KEY (`fk_person`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `training_request`
--
ALTER TABLE `training_request`
  ADD CONSTRAINT `fk_ClaimTraining_ClaimStatus1` FOREIGN KEY (`fk_training_status`) REFERENCES `training_status` (`id_training_status`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_training_request_student_information1` FOREIGN KEY (`student_information_SSN`) REFERENCES `student_information` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `training_request_ibfk_1` FOREIGN KEY (`fk_person`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `training_request_ibfk_2` FOREIGN KEY (`fk_organization`) REFERENCES `organization` (`vat_number`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
