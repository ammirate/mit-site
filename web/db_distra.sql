# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.21)
# Database: db_distra
# Generation Time: 2015-01-12 16:06:29 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `email` varchar(100) NOT NULL COMMENT 'Nome con il quale l''utente viene riconosciuto da un',
  `password` varchar(45) NOT NULL,
  `typeOfAccount` varchar(45) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabella adibita alla gestione dei dati principali per l''acesso al sistema';

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;

INSERT INTO `account` (`email`, `password`, `typeOfAccount`, `active`)
VALUES
	('adl@unisa.it','test','professor',1),
	('ciro@gmail.com','ciropass','Mstudente',1),
	('ciro@unisa.it','123123','Mstudent',1),
	('cosimo@gmail.com','cosimo','Mstudent',1),
	('d.iannone@gmail.com','daniele','Mstudent',1),
	('damiano@gmail.com','dami','Mstudent',0),
	('delucia@gmail.com','andrea','professor',1),
	('ferrucci@gmail.com','filomena','professor',1),
	('fpalomba@unisa.it','fabio','phdadmin',1),
	('francese@gmail.com','francese','professor',1),
	('gemma.catolino91@gmail.com','CIAO','Mstudent',1),
	('m.taddeo@gmail.com','taddeo','Bstudent',0),
	('mariorossi@gmail.com','mario','Bstudent',0),
	('mikela_f@hotmail.it','ciao','Mstudent',1),
	('nuovo@unisa.it','123123','Bstudent',1),
	('nuovoprof@unisa.it','123123','professor',1),
	('polese@gmail.com','polese','professor',0),
	('prof@unisa.it','123123','professor',1),
	('tucci@gmail.com','Tucci','professor',0),
	('vaia@gmail.com','vaia','professor',0);

/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table attachment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `attachment`;

CREATE TABLE `attachment` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Object` varchar(255) NOT NULL,
  `ID_Thesis` int(11) NOT NULL,
  `Status` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `ID_Thesis` (`ID_Thesis`),
  CONSTRAINT `attachment_ibfk_1` FOREIGN KEY (`ID_Thesis`) REFERENCES `thesis` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table chronology
# ------------------------------------------------------------

DROP TABLE IF EXISTS `chronology`;

CREATE TABLE `chronology` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Text` varchar(1000) NOT NULL,
  `ID_Professor` varchar(16) NOT NULL,
  `ID_Student` varchar(16) NOT NULL,
  `Notice_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Type` varchar(255) DEFAULT NULL COMMENT 'values: richiesta, accetta, rifiuta, modifica',
  PRIMARY KEY (`ID`),
  KEY `fk_person1` (`ID_Student`) USING BTREE,
  KEY `fk_person2` (`ID_Professor`) USING BTREE,
  CONSTRAINT `chronology_ibfk_1` FOREIGN KEY (`ID_Professor`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `chronology_ibfk_2` FOREIGN KEY (`ID_Student`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `chronology` WRITE;
/*!40000 ALTER TABLE `chronology` DISABLE KEYS */;

INSERT INTO `chronology` (`ID`, `Text`, `ID_Professor`, `ID_Student`, `Notice_Date`, `Type`)
VALUES
	(14,'lo studente Palumbo Ciro ha inviato una richiesta di tesi al prof. De Lucia Andrea con il seguente messaggio: jokljogie7se5dritfuogyiuhiuggctuftguyiuoihgufyyguhij ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2014-12-22 17:03:54','null'),
	(15,'il prof.De Lucia Andrea ha accettato la richiesta di Palumbo Ciro per avviare un lavoro di tesi ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2014-12-22 17:05:01','null'),
	(16,'il prof.De Lucia Andrea ha convalidato la richiesta di Palumbo Ciro per confermare il completamento del lavoro di tesi.  ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2014-12-22 17:09:32','null'),
	(17,'il prof.De Lucia Andrea ha accettato la richiesta di Palumbo Ciro per avviare un lavoro di tesi. La seduta di laurea è prevista il giorno 2015-01-23 ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2014-12-22 17:10:30','null'),
	(18,'il prof.null ha rifiutato la richiesta di null per confermare il completamento del lavoro di tesi. Controlla che tutti i campi siano stati compilati correttamente o prova a contattare il docente. ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2014-12-22 17:33:35','null'),
	(19,'il prof.De Lucia Andrea ha rifiutato la richiesta di Palumbo Ciro per confermare il completamento del lavoro di tesi. Controlla che tutti i campi siano stati compilati correttamente o prova a contattare il docente. ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2014-12-22 17:42:51','null'),
	(20,'lo studente Utente Nuovo ha inviato una richiesta di tesi al prof. De Lucia Andrea con il seguente messaggio: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaassssssssss ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2014-12-22 17:45:44','null'),
	(21,'il prof.De Lucia Andrea ha rifiutato la richiesta di Utente Nuovo per avviare un lavoro di tesi ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2014-12-22 17:46:03','null'),
	(22,'lo studente Catolino Golli ha inviato una richiesta di tesi al prof. De Lucia Andrea con il seguente messaggio: asdsacacsascsacscaaccssacascsccacacacasccsacacacacacac ','ANDDLC65M0N409C','CTLGMM91A71B519A','2015-01-11 17:29:21','richiesta'),
	(23,'il prof.De Lucia Andrea ha accettato la richiesta di Catolino Golli per avviare un lavoro di tesi ','ANDDLC65M0N409C','CTLGMM91A71B519A','2015-01-11 17:37:27','accetta'),
	(24,'il prof.De Lucia Andrea ha accettato la richiesta di Palumbo Ciro per avviare un lavoro di tesi. La seduta di laurea è prevista il giorno 2008-12-14 ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2015-01-11 17:37:57','accetta'),
	(25,'lo studente Catolino Golli ha apportato delle modifiche alla propria Tesi in Corso. ','ANDDLC65M0N409C','CTLGMM91A71B519A','2015-01-11 18:32:33','modifica'),
	(26,'lo studente Catolino Golli ha apportato delle modifiche alla propria Tesi in Corso. ','ANDDLC65M0N409C','CTLGMM91A71B519A','2015-01-11 18:33:19','modifica'),
	(27,'lo studente Catolino Golli ha apportato delle modifiche alla propria Tesi in Corso. ','ANDDLC65M0N409C','CTLGMM91A71B519A','2015-01-11 18:36:28','modifica'),
	(28,'lo studente Catolino Golli ha apportato delle modifiche alla propria Tesi in Corso. ','ANDDLC65M0N409C','CTLGMM91A71B519A','2015-01-11 18:38:10','modifica'),
	(29,'lo studente Catolino Golli ha apportato delle modifiche alla propria Tesi in Corso. ','ANDDLC65M0N409C','CTLGMM91A71B519A','2015-01-11 18:38:31','modifica'),
	(30,'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ','FLMFRR65M0A509S','NNNDNL89M01A509C','2015-01-11 18:48:55','modifica'),
	(31,'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ','FLMFRR65M0A509S','NNNDNL89M01A509C','2015-01-11 18:49:20','modifica'),
	(32,'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ','FLMFRR65M0A509S','NNNDNL89M01A509C','2015-01-11 18:50:25','modifica'),
	(33,'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ','FLMFRR65M0A509S','NNNDNL89M01A509C','2015-01-11 18:51:22','modifica'),
	(34,'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ','FLMFRR65M0A509S','NNNDNL89M01A509C','2015-01-11 18:52:41','modifica'),
	(35,'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ','FLMFRR65M0A509S','NNNDNL89M01A509C','2015-01-11 18:53:43','modifica'),
	(36,'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ','FLMFRR65M0A509S','NNNDNL89M01A509C','2015-01-11 18:57:34','modifica'),
	(37,'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ','FLMFRR65M0A509S','NNNDNL89M01A509C','2015-01-11 19:03:35','modifica'),
	(38,'lo studente Iannone Daniele ha apportato delle modifiche alla propria Tesi in Corso. ','FLMFRR65M0A509S','NNNDNL89M01A509C','2015-01-11 19:04:14','modifica'),
	(39,'lo studente Palumbo Ciro ha inviato una richiesta di tesi al prof. De Lucia Andrea con il seguente messaggio: ssssssssssssssssssssseeempre la steeeeeessa cosa. Messaggio fittizzio, prof si ricorda di me? ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2015-01-11 21:45:54','richiesta'),
	(40,'il prof.De Lucia Andrea ha accettato la richiesta di Palumbo Ciro per avviare un lavoro di tesi ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2015-01-11 22:01:25','accetta'),
	(41,'lo studente Palumbo Ciro ha inviato una richiesta di tesi al prof. De Lucia Andrea con il seguente messaggio: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2015-01-12 10:52:26','richiesta'),
	(42,'il prof.De Lucia Andrea ha rifiutato la richiesta di Palumbo Ciro per avviare un lavoro di tesi ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2015-01-12 10:52:37','rifiuta'),
	(43,'lo studente Palumbo Ciro ha inviato una richiesta di tesi al prof. De Lucia Andrea con il seguente messaggio: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2015-01-12 10:53:07','richiesta'),
	(44,'il prof.De Lucia Andrea ha accettato la richiesta di Palumbo Ciro per avviare un lavoro di tesi ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2015-01-12 10:53:17','accetta'),
	(45,'lo studente Palumbo Ciro ha inviato una richiesta per il completamento della tesi al prof. De Lucia Andrea ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2015-01-12 10:53:59','richiesta_completamento'),
	(46,'il prof.De Lucia Andrea ha rifiutato la richiesta di Palumbo Ciro di completamento del lavoro di tesi. Controlla che tutti i campi siano stati compilati correttamente o prova a contattare il docente. ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2015-01-12 10:54:12','rifiuta'),
	(47,'lo studente Palumbo Ciro ha inviato una richiesta per il completamento della tesi al prof. De Lucia Andrea ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2015-01-12 10:54:33','richiesta_completamento'),
	(48,'il prof.De Lucia Andrea ha convalidato la richiesta di Palumbo Ciro per confermare il completamento del lavoro di tesi.  ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2015-01-12 10:54:43','accetta'),
	(49,'lo studente Palumbo Ciro ha inviato una richiesta di tesi al prof. De Lucia Andrea con il seguente messaggio: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2015-01-12 11:10:52','richiesta'),
	(50,'il prof.De Lucia Andrea ha accettato la richiesta di Palumbo Ciro per avviare un lavoro di tesi ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2015-01-12 11:11:09','accetta'),
	(51,'lo studente Palumbo Ciro ha apportato delle modifiche alla propria Tesi in Corso. ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2015-01-12 11:11:28','modifica'),
	(52,'lo studente Palumbo Ciro ha apportato delle modifiche alla propria Tesi in Corso. ','ANDDLC65M0N409C','CRRPLMC91M0N409C','2015-01-12 11:13:45','modifica');

/*!40000 ALTER TABLE `chronology` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table class
# ------------------------------------------------------------

DROP TABLE IF EXISTS `class`;

CREATE TABLE `class` (
  `title` varchar(50) NOT NULL,
  `teaching_matricula` varchar(10) NOT NULL,
  PRIMARY KEY (`title`,`teaching_matricula`),
  KEY `title` (`title`),
  KEY `teaching_matricula` (`teaching_matricula`),
  CONSTRAINT `fk_class_teaching1` FOREIGN KEY (`teaching_matricula`) REFERENCES `teaching` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table class_event
# ------------------------------------------------------------

DROP TABLE IF EXISTS `class_event`;

CREATE TABLE `class_event` (
  `FK_Class` int(11) NOT NULL,
  `FK_Event` int(11) NOT NULL,
  PRIMARY KEY (`FK_Class`,`FK_Event`),
  KEY `FK_Event` (`FK_Event`),
  KEY `FK_Class` (`FK_Class`),
  CONSTRAINT `class_event_ibfk_2` FOREIGN KEY (`FK_Event`) REFERENCES `event` (`idEvent`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table class_notice
# ------------------------------------------------------------

DROP TABLE IF EXISTS `class_notice`;

CREATE TABLE `class_notice` (
  `FK_Class` int(11) NOT NULL,
  `FK_Event` int(11) NOT NULL,
  PRIMARY KEY (`FK_Class`,`FK_Event`),
  KEY `FK_Event` (`FK_Event`),
  KEY `FK_Class` (`FK_Class`),
  CONSTRAINT `class_notice_ibfk_1` FOREIGN KEY (`FK_Event`) REFERENCES `event` (`idEvent`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table collaboration
# ------------------------------------------------------------

DROP TABLE IF EXISTS `collaboration`;

CREATE TABLE `collaboration` (
  `idCollaboration` int(20) NOT NULL AUTO_INCREMENT,
  `istitution` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `FK_Student` varchar(16) NOT NULL,
  PRIMARY KEY (`idCollaboration`),
  KEY `FK_Student` (`FK_Student`),
  CONSTRAINT `collaboration_ibfk_1` FOREIGN KEY (`FK_Student`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `collaboration` WRITE;
/*!40000 ALTER TABLE `collaboration` DISABLE KEYS */;

INSERT INTO `collaboration` (`idCollaboration`, `istitution`, `description`, `startDate`, `endDate`, `FK_Student`)
VALUES
	(1,'The College of William & Mary',' Preparazione paper \'When and Why Your Code Starts to Smell Bad\' sottomesso ad ICSE il 05/09/2014','2014-06-01','2014-09-05','PLMFBA89M03F839I'),
	(2,'The College of William & Mary','Preparazione paper su app Android sottomesso ad ICSE il 05/09/2014  ','2014-07-03','2014-09-05','PLMFBA89M03F839I'),
	(3,'Ecole Polytechnique de Montreal',' Preparazione paper su prioritizzazione dei bad smell ','2014-04-01','2015-01-11','PLMFBA89M03F839I');

/*!40000 ALTER TABLE `collaboration` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table curriculum
# ------------------------------------------------------------

DROP TABLE IF EXISTS `curriculum`;

CREATE TABLE `curriculum` (
  `title` varchar(100) NOT NULL,
  `matricula` varchar(45) NOT NULL,
  `degree_matricula` varchar(5) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`matricula`),
  KEY `fk_curriculum_degree1_idx` (`degree_matricula`),
  CONSTRAINT `fk_curriculum_degree1` FOREIGN KEY (`degree_matricula`) REFERENCES `degree` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table curriculum_teaching
# ------------------------------------------------------------

DROP TABLE IF EXISTS `curriculum_teaching`;

CREATE TABLE `curriculum_teaching` (
  `curriculum_matricula` varchar(45) NOT NULL,
  `teaching_matricula` varchar(10) NOT NULL,
  KEY `curriculum_matricula` (`curriculum_matricula`),
  KEY `teaching_matricula` (`teaching_matricula`),
  CONSTRAINT `curriculum_teaching_ibfk_1` FOREIGN KEY (`curriculum_matricula`) REFERENCES `curriculum` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `curriculum_teaching_ibfk_2` FOREIGN KEY (`teaching_matricula`) REFERENCES `teaching` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table cycle
# ------------------------------------------------------------

DROP TABLE IF EXISTS `cycle`;

CREATE TABLE `cycle` (
  `cycle_number` int(11) NOT NULL,
  `title` varchar(20) NOT NULL,
  PRIMARY KEY (`cycle_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `cycle` WRITE;
/*!40000 ALTER TABLE `cycle` DISABLE KEYS */;

INSERT INTO `cycle` (`cycle_number`, `title`)
VALUES
	(1,'studente triennale'),
	(2,'studente magistrale'),
	(3,'phd student'),
	(4,'professore'),
	(5,'azienda');

/*!40000 ALTER TABLE `cycle` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table degree
# ------------------------------------------------------------

DROP TABLE IF EXISTS `degree`;

CREATE TABLE `degree` (
  `title` varchar(500) NOT NULL,
  `matricula` varchar(5) NOT NULL,
  `link` varchar(500) DEFAULT NULL,
  `department_abbreviation` varchar(100) NOT NULL,
  `cycle_number` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`matricula`),
  KEY `fk_degree_department1_idx` (`department_abbreviation`),
  KEY `fk_degree_cycle1_idx` (`cycle_number`),
  CONSTRAINT `fk_degree_cycle1` FOREIGN KEY (`cycle_number`) REFERENCES `cycle` (`cycle_number`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_degree_department1` FOREIGN KEY (`department_abbreviation`) REFERENCES `department` (`abbreviation`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `degree` WRITE;
/*!40000 ALTER TABLE `degree` DISABLE KEYS */;

INSERT INTO `degree` (`title`, `matricula`, `link`, `department_abbreviation`, `cycle_number`, `active`)
VALUES
	('MIT','02255',NULL,'distra',2,1),
	('EM','03345',NULL,'distra',1,1),
	('CM','04453',NULL,'distra',2,1),
	('DMIT','88876',NULL,'distra',3,1);

/*!40000 ALTER TABLE `degree` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table department
# ------------------------------------------------------------

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `abbreviation` varchar(50) NOT NULL,
  `title` varchar(500) DEFAULT NULL,
  `url_moodle` varchar(1000) DEFAULT NULL,
  `token` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`abbreviation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;

INSERT INTO `department` (`abbreviation`, `title`, `url_moodle`, `token`)
VALUES
	('distra','Dipartimento di Studi e Ricerche Aziendali ',NULL,NULL);

/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table event
# ------------------------------------------------------------

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `idEvent` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `hours` int(1) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`idEvent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table event_professor
# ------------------------------------------------------------

DROP TABLE IF EXISTS `event_professor`;

CREATE TABLE `event_professor` (
  `FK_Event` int(11) NOT NULL,
  `FK_Professor` varchar(16) NOT NULL,
  PRIMARY KEY (`FK_Event`,`FK_Professor`),
  KEY `FK_Professor` (`FK_Professor`),
  KEY `FK_Event` (`FK_Event`),
  CONSTRAINT `event_professor_ibfk_1` FOREIGN KEY (`FK_Event`) REFERENCES `event` (`idEvent`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `event_professor_ibfk_2` FOREIGN KEY (`FK_Professor`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table lesson
# ------------------------------------------------------------

DROP TABLE IF EXISTS `lesson`;

CREATE TABLE `lesson` (
  `idLesson` int(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  `speaker` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `place` varchar(45) NOT NULL,
  `FK_Event` int(11) NOT NULL,
  PRIMARY KEY (`idLesson`),
  KEY `FK_Event` (`FK_Event`),
  CONSTRAINT `lesson_ibfk_1` FOREIGN KEY (`FK_Event`) REFERENCES `event` (`idEvent`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table lesson_student
# ------------------------------------------------------------

DROP TABLE IF EXISTS `lesson_student`;

CREATE TABLE `lesson_student` (
  `FK_Lesson` int(20) NOT NULL,
  `FK_Student` varchar(16) NOT NULL,
  PRIMARY KEY (`FK_Lesson`,`FK_Student`),
  KEY `FK_Student` (`FK_Student`),
  KEY `FK_Lesson` (`FK_Lesson`),
  CONSTRAINT `lesson_student_ibfk_1` FOREIGN KEY (`FK_Student`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lesson_student_ibfk_2` FOREIGN KEY (`FK_Lesson`) REFERENCES `lesson` (`idLesson`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table mission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mission`;

CREATE TABLE `mission` (
  `idMission` int(20) NOT NULL AUTO_INCREMENT,
  `place` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `FK_Student` varchar(16) NOT NULL,
  PRIMARY KEY (`idMission`),
  KEY `FK_Student` (`FK_Student`),
  CONSTRAINT `mission_ibfk_1` FOREIGN KEY (`FK_Student`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `mission` WRITE;
/*!40000 ALTER TABLE `mission` DISABLE KEYS */;

INSERT INTO `mission` (`idMission`, `place`, `description`, `startDate`, `endDate`, `FK_Student`)
VALUES
	(1,'Victoria, British Columbia - Canada','Partecipazione alla International Conference on Software Maintenance and Evolution (ICSME\'14) ','2014-09-27','2014-10-04','PLMFBA89M03F839I'),
	(2,'Palo Alto, California - USA',' Partecipazione alla International Conference on Automated Software Engineering (ASE\'13).','2013-11-16','2013-11-21','PLMFBA89M03F839I');

/*!40000 ALTER TABLE `mission` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table module
# ------------------------------------------------------------

DROP TABLE IF EXISTS `module`;

CREATE TABLE `module` (
  `title` varchar(50) NOT NULL,
  `teaching_matricula` varchar(10) NOT NULL,
  PRIMARY KEY (`title`,`teaching_matricula`),
  KEY `fk_module_teaching1_idx` (`teaching_matricula`),
  CONSTRAINT `fk_module_teaching1` FOREIGN KEY (`teaching_matricula`) REFERENCES `teaching` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table notice
# ------------------------------------------------------------

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `idNotice` int(11) NOT NULL,
  `object` varchar(45) NOT NULL,
  `text` text NOT NULL,
  PRIMARY KEY (`idNotice`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table organization
# ------------------------------------------------------------

DROP TABLE IF EXISTS `organization`;

CREATE TABLE `organization` (
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
  KEY `fk_prof` (`fk_professor`),
  CONSTRAINT `fk_acc` FOREIGN KEY (`fk_account`) REFERENCES `account` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_prof` FOREIGN KEY (`fk_professor`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tutor` FOREIGN KEY (`fk_external_tutor`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table pending_acceptance
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pending_acceptance`;

CREATE TABLE `pending_acceptance` (
  `id_pending_acceptance` int(11) NOT NULL AUTO_INCREMENT,
  `request_date` date DEFAULT NULL,
  `fk_person` varchar(16) NOT NULL,
  PRIMARY KEY (`id_pending_acceptance`),
  KEY `fk_StudentAttendence_Student1_idx` (`fk_person`),
  CONSTRAINT `fk_StudentAttendence_Student1` FOREIGN KEY (`fk_person`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table person
# ------------------------------------------------------------

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
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
  KEY `degree_matricula` (`degree_matricula`),
  CONSTRAINT `fk_Person_Account` FOREIGN KEY (`Account_email`) REFERENCES `account` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Person_Department1` FOREIGN KEY (`Department_abbreviation`) REFERENCES `department` (`abbreviation`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `person_ibfk_1` FOREIGN KEY (`cycle`) REFERENCES `cycle` (`cycle_number`),
  CONSTRAINT `person_ibfk_2` FOREIGN KEY (`degree_matricula`) REFERENCES `degree` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;

INSERT INTO `person` (`SSN`, `name`, `surname`, `phone`, `city`, `address`, `zip_code`, `gender`, `citizenship`, `Account_email`, `Department_abbreviation`, `web_page`, `university`, `matricula`, `position`, `cycle`, `degree_matricula`, `cover_letter`)
VALUES
	('ANDDLC65M0N409C','Andrea','De Lucia','3384673356','Salerno',NULL,NULL,'M',NULL,'adl@unisa.it','DISTRA','ww.delucia.it','Università di Salerno','022552585','professor',1,'02255',NULL),
	('CRRPLMC91M0N409C','Ciro','Palumbo',NULL,NULL,NULL,NULL,'M',NULL,'ciro@unisa.it','DISTRA',NULL,NULL,'022552585','student',1,'02255',NULL),
	('CSMGRT89M0A309C','Cosimo','Grattacaso','3334536354','Paestum','via sicilia','87832','M','italiana','cosimo@gmail.com','DISTRA','www.cosimo.it','Università di Salerno','022274382','student',1,'02255',NULL),
	('CTLGMM91A71B519A','Golli','Catolino','3272844649','Baranello','Contrada Gaudo n. 27','86011','F','Italiana','gemma.catolino91@gmail.com','distra','http://www.unisa.it/docenti/filomenaferrucci/index','UniversitÃ  degli Studi di Salerno','022552585','null',1,'02255','     Nel mio percorso di studente di dottorato del curriculum EDAP, intendo occuparmi del management delle Pubbliche Amministrazioni, in ottica sistemico-relazionale. Lo studio della gestione delle PA sarÃ????Ã???Ã??Ã?Â  sviluppato attraverso lÃ???Ã??Ã?Â¢??analisi di nuovi e piÃ????Ã???Ã??Ã?Â¹ recenti studi che propongono per il management pubblico nuovi approcci come ad esempio il Ã???Ã??Ã?Â¢??Public Service-DominantÃ???Ã??Ã?Â¢??, ovvero un approccio basato sui servizi pubblici e che enfatizza i processi di trasformazione della conoscenza. I miei interessi dunque, includono lo studio di quelle prospettive teoriche che tendono a fornire una lettura relazionale de     '),
	('DMNSNS90M0N309C','Damiano','Senese','3456789098','Salerno','via casilina','08768','M','italiano','damiano@gmail.com','DISTRA','www.dami.it','Università degli Studi di Salerno','022552283','student',1,'02255',NULL),
	('FLMFRR65M0A509S','Filomena','Ferrucci',NULL,NULL,NULL,NULL,'F','italiana','ferrucci@gmail.com','DISTRA',NULL,'Università degli Studi di Salerno','022552585','professor',1,'02255',NULL),
	('FRRMHL89M41H501S','Michela','Ferrante','3456787917','Frosinone','via casilina','03100','F','Italiana','mikela_f@hotmail.it','DISTRA','www.miki.it','Università degli Studi di Salerno','022554678','student',1,'02255',NULL),
	('MRRRSS88M0A309C','Mario','Rossi','3334536354','Napoli','via le lame','83645','M','italiana','mariorossi@gmail.com','DISTRA','www.rossi.it','Università di Salerno','0222736745','student',1,'02255',NULL),
	('MRRTDD85M0A309S','Maria','Taddeo','3487654362','Matera','via roma','65374','F','italiana','m.taddeo@gmail.com','DISTRA','taddeo.it','Università di Salerno','0222736453','student',1,'02255',NULL),
	('MRZTCC62M0A309C','Maurizio','Tucci','3456253625','Salerno','via verdi','84536','M','italiana','tucci@gmail.com','DISTRA','tucci.com','Università di Salerno','022552585','professor',1,'02255',NULL),
	('NNNDNL89M01A509C','Daniele','Iannone','3456786764','Montoro','via verdi','87654','M','italiano','d.iannone@gmail.com','DISTRA',NULL,'Università degli Studi di Salerno','022554736','student',1,'02255',NULL),
	('PLMFBA89M03F839I','Fabio','Palomba','3884430360','Fisciano','Via Tenente Nastri 112','84084','M','italiana','fpalomba@unisa.it','distra','https://dibt.unimol.it/fpalomba','UniversitÃ  degli Studi di Salerno','null','null',3,'88876','BAD CODE SMELLS DETECTION <br>\r\nBad code smells have been defined by Martin Fowler as symptoms of poor design and implementation choices. Bad smells are usually introduced in software systems because developers poorly conceived the design of the code component or because they did not care about properly designing the solution due to strict deadlines. Complex Class, i.e., a class that contain complex methods and it is very large in terms of LOC; or God Class, i.e., a class that does too much/knows too much about other classes, are only some examples of a plethora of bad smells identified and characterized in well-known catalogues. Recent empirical studies showed that code smells hinder comprehensibility, and possibly increase change- and fault- proneness. For these reasons, the main research topic in this area is the definition of new approaches able to detect bad code smells in the source code.<br><br>\r\n\r\nMINING SOFTWARE REPOSITORIES<br>\r\nSoftware repositories such as source control systems, archived communications between project personnel, and defect tracking systems are used to help manage the progress of software projects. Software practitioners and researchers are recognizing the benefits of mining this information to support the maintenance of software systems, improve software design/reuse, and empirically validate novel ideas and techniques. Research is now proceeding to uncover the ways in which mining these repositories can help to understand software development and software evolution, to support predictions about software development, and to exploit this knowledge in planning future development.<br><br>\r\n\r\nEMPIRICAL SOFTWARE ENGINEERING<br>\r\nEmpirical software engineering is a sub-domain of software engineering focusing on experiments on software systems (software products, processes, and resources). It is interested in devising experiments on software, in collecting data from these experiments, and in devising laws and theories from this data. Proponents of experimental software engineering advocate that the nature of software is such that we can advance the knowledge on software through experiments only. The scientific method suggests a cycle of observations, laws, and theories to advance science. Empirical software engineering applies this method to software.<br><br>\r\n\r\nTRACEABILITY MANAGEMENT<br>\r\nTraceability has been defined as \'the ability to describe and follow the life of an artifact, in both a forwards and backwards direction\'. Thus, traceability links help software engineers to understand the relationships and dependencies among various software artifacts (requirements, code, tests, models, etc.) developed during the software lifecycle. The two main research topics related to the traceability management are event-based systems for traceability management and information retrieval based methods and tools supporting the software engineer in the traceability link recovery. '),
	('RTTFRN68M0A309S','Rita','Francese','3487652362','Fisciano','via le lame','65334','F','italiana','francese@gmail.com','DISTRA','www.francese.it','Università di Salerno','022552585','professor',1,'02255',NULL);

/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table phdclass
# ------------------------------------------------------------

DROP TABLE IF EXISTS `phdclass`;

CREATE TABLE `phdclass` (
  `idClass` int(20) NOT NULL AUTO_INCREMENT,
  `FK_PhdCycle` int(11) NOT NULL,
  `FK_PhdCurriculum` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`idClass`),
  UNIQUE KEY `FK_PhdCycle` (`FK_PhdCycle`,`FK_PhdCurriculum`),
  KEY `FK_PhdCurriculum` (`FK_PhdCurriculum`),
  KEY `FK_cycle` (`FK_PhdCycle`),
  CONSTRAINT `phdclass_ibfk_1` FOREIGN KEY (`FK_PhdCycle`) REFERENCES `phdcycle` (`idPhdCycle`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `phdclass_ibfk_2` FOREIGN KEY (`FK_PhdCurriculum`) REFERENCES `phdcurriculum` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `phdclass` WRITE;
/*!40000 ALTER TABLE `phdclass` DISABLE KEYS */;

INSERT INTO `phdclass` (`idClass`, `FK_PhdCycle`, `FK_PhdCurriculum`)
VALUES
	(1,15,'Marketing e Comunicazione'),
	(3,16,'Economia e Direzione delle Aziende Pubbliche');

/*!40000 ALTER TABLE `phdclass` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table phdcurriculum
# ------------------------------------------------------------

DROP TABLE IF EXISTS `phdcurriculum`;

CREATE TABLE `phdcurriculum` (
  `name` varchar(100) NOT NULL,
  `description` text,
  `FK_Professor` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `FK_Professor` (`FK_Professor`),
  CONSTRAINT `phdcurriculum_ibfk_1` FOREIGN KEY (`FK_Professor`) REFERENCES `person` (`SSN`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `phdcurriculum` WRITE;
/*!40000 ALTER TABLE `phdcurriculum` DISABLE KEYS */;

INSERT INTO `phdcurriculum` (`name`, `description`, `FK_Professor`)
VALUES
	('Economia e Direzione delle Aziende Pubbliche','Il curriculum “Economia e Direzione delle Aziende Pubbliche” ha l’obiettivo di trasferire conoscenze relative a principi, teorie e modelli di gestione, elaborati con riferimento all\'\'economia e alla gestione delle aziende pubbliche, fornire gli strumenti e le metodologie di analisi più innovative, nonché, sviluppare competenze professionali specializzate per il management di enti, istituzioni ed aziende afferenti al settore pubblico. Settore che negli ultimi decenni la Pubblica Amministrazione (P.A.) ha sperimentato un profondo processo di riforma che si è concretizzato, da un lato, in un riposizionamento dei confini tra stato, mercato e società civile e, dall\'\'altro, nell\'\'affermarsi di nuovi modelli di funzionamento che vanno sotto il nome di New Public Management. Recependo tali mutamenti la PA, pur conservando la propria missione, si è trovata nella necessità di innovare le proprie logiche di funzionamento ed i correlati strumenti di analisi e valutazione.\r\n\r\nCambiando le logiche organizzative ed i fabbisogni professionali, diviene strategica l\'\'acquisizione, da parte dei livelli dirigenziali, di competenze specifiche e di capacità di problem solving attraverso il costante confronto con casi ed esperienze concrete anche di origine internazionale.\r\n\r\nIl curriculum in Economia e Direzione delle Aziende Pubbliche rappresenta, quindi, un percorso elettivamente individuato dal mondo universitario per la preparazione di giovani studiosi e potenziali dirigenti attenti all\'\'esigenza di una moderna ed efficiente pubblica amministrazione. Esso ha lo scopo di presentare le più affermate e consolidate teorie e impostazioni dottrinarie elaborate in riferimento all\'\'economia e al governo dell\'\'azienda pubblica (anche nella sua espressione più allargata di pubblici servizi).\r\n\r\nIl curriculum, pertanto, offre una specifica formazione sui principali temi del public management e della governance pubblica:\r\n\r\nDottrine economico-aziendali;\r\nEconomia e contabilità dell\'\'azienda pubblica;\r\nGestione e management delle amministrazioni pubbliche;\r\nOrganizzazione e gestione delle risorse umane;\r\nGovernance degli enti locali;\r\nI controlli interni nella P.A.;\r\nStrategie e politiche innovative nella P.A.;\r\nValutazione e controllo dei dirigenti;\r\nMarketing territoriale;\r\nRelazioni con il cittadino e CRM.\r\nIn definitiva, il Dottore di Ricerca con curriculum in Economia e Direzione delle Aziende Pubbliche si può inquadrare in almeno 3 figure professionali (di dirigente pubblico) emerse nel corso di una recente indagine svolta dal Dipartimento della Funzione Pubblica ovvero la figura di \'in-and-outer\', l\'\'altrettanto recente \'high flier\' e il \'libero professionista riconvertito\'.\r\n\r\nIl dottorato, inoltre, rappresenta una valida premessa per la formazione di figure professionali destinate a ricoprire il ruolo di city-manager (figura del direttore generale negli Enti locali voluta dalla legge 127/1997) e di consulente per gli Enti locali.\', NULL),\n(\'Informatica, Sistemi Informativi e Tecnologie del Software\', \'\r\nIl curriculum, Informatica, Sistemi Informativi e Tecnologie del Software, ha l’obiettivo di formare figure professionali dotate di una preparazione scientifica teorica e pratica idonea ad operare con piena professionalità e competenza, sia in ambito accademico che industriale, nelle varie fasi che caratterizzano la ricerca, lo sviluppo, il controllo di qualità e la produzione nel settore dei sistemi informativi e delle tecnologie del software. In particolare, il corso di dottorato di ricerca mira alla formazione di ricercatori con elevata conoscenza degli aspetti teorici, metodologici, sperimentali e applicativi di settore quali quelli dei sistemi informativi e delle basi di dati, dell’ingegneria del software, dell\'\'ingegneria della conoscenza, del web engineering e dell\'\'interazione uomo-macchina, con una elevata capacità di trasferire i risultati della ricerca in ambito industriale e di applicarli ai settori dell’economia e del management aziendale, del marketing e della comunicazione.\r\n\r\n  Le tematiche scientifiche del curriculum includono:\r\n\r\nProject management\r\nSoftware quality assurance\r\nMetodi per la stima dei costi\r\nSistemi a supporto delle decisioni e business intelligence\r\nData warehousing\r\nBig data\r\nOpen data\r\nDocument and content management\r\nWorkflow and process management\r\nModellazione e analisi delle prestazioni dei processi\r\nBusiness process reengineering\r\nWeb engineering\r\nSistemi cloud-based\r\nIngegneria dei requisiti e progettazione di sistemi software\r\nManutenzione ed evoluzione di sistemi software\r\nAnalisi e testing del software\r\nIngegneria del software empirica\r\nMetodi e strumenti per il lavoro collaborativo\r\nLinguaggi visuali e interazione uomo-macchina\r\nComputer graphics e realtà virtuale\r\nInterfacce web avanzate, immersive, 3D e aptiche\r\nDomotica e sistemi di videosorveglianza intelligenti.\r\nInterfacce per sistemi domotici\r\nRiconoscimento di immagini e sistemi biometrici\r\nComputational intelligence\r\nInformation retrieval\r\nTecniche di clustering e data mining, machine learning e classificazione\r\nSistemi informativi geografici e territoriali\r\nE-learning e tecnologie per la didattica a distanza\r\nModelli matematici e ottimizzazione\r\nModellazione ed analisi di prestazioni e affidabilità dei sistemi\r\nIl Dottore di Ricerca con curriculum in Informatica, Sistemi Informativi e Tecnologie del Software potrà avere diversi sbocchi professionali, che non si fermano a quello di ricercatore accademico o nei centri di ricerca di organizzazioni ed aziende. Infatti, grazie anche alle competenze acquisite con la formazione di tipo manageriale acquisita durante il corso di dottorato, il Dottore di Ricerca potrà ricoprire ruoli di consulente ed esperto di innovazione e trasferimento tecnologico per le aziende del comparto ICT, nonché ruoli di direzione di progetti di ricerca e funzioni direzionali in aziende del comparto ICT.','RTTFRN68M0A309S'),
	('Marketing e Comunicazione','Il curriculum “Marketing e Comunicazione” mira a trasferire conoscenze e approcci metodologici relativi a principi, teorie e modelli di ideazione e governo delle strategie e delle politiche di marketing e della comunicazione. Il principale obiettivo è formare figure professionali dotate di una solida preparazione scientifica, teorica e pratica idonea ad operare nelle università, nei centri di ricerca, negli enti pubblici e nelle imprese private e pubbliche svolgendo attività di ricerca qualificata nelle seguenti aree disciplinari e professionali: marketing strategico e operativo, analisi di mercato e del consumo, comunicazione istituzionale e d\'\'impresa.\n\nIl programma formativo si riferisce all’ambito disciplinare dell\'\'Economia d\'\'Impresa e prevede che i frequentanti, al termine del percorso di apprendimento, acquisiscano un uso agevole delle più avanzate ed affidabili metodologie di ricerca scientifica in campo economico-sociale, oltre ad un’approfondita conoscenza delle teorie e delle più recenti ed innovative impostazioni riguardo al marketing ed alla comunicazione.\n\nParticolare attenzione di studio viene rivolta all’area dei social media e della rete per supportare le scelte strategiche di marketing e di comunicazione delle organizzazioni imprenditoriali e delle istituzioni.\n\nLe tematiche scientifiche del curriculum includono:\n\nMarketing Strategy;\nCompetition dynamics;\nChannel management;\nSalesforce management;\nMarketing e performance aziendali;\nBehavioral Constructs;\nDecision making;\nConsumer judgment;\nCorporate Communication;\nCorporate identity;\nOrganizational culture;\nReputation management;\nIntegrated Marketing Communication;\nDigital communication;\nValue creation and innovation;\nOn..','MRZTCC62M0A309C');

/*!40000 ALTER TABLE `phdcurriculum` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table phdcycle
# ------------------------------------------------------------

DROP TABLE IF EXISTS `phdcycle`;

CREATE TABLE `phdcycle` (
  `idPhdCycle` int(11) NOT NULL,
  `description` text,
  `year` year(4) NOT NULL,
  `FK_Professor` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`idPhdCycle`),
  KEY `FK_Professor` (`FK_Professor`),
  CONSTRAINT `phdcycle_ibfk_1` FOREIGN KEY (`FK_Professor`) REFERENCES `person` (`SSN`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `phdcycle` WRITE;
/*!40000 ALTER TABLE `phdcycle` DISABLE KEYS */;

INSERT INTO `phdcycle` (`idPhdCycle`, `description`, `year`, `FK_Professor`)
VALUES
	(15,'Il corso di Dottorato di Ricerca in Management & Information Technology ha come obiettivo la formazione di specialisti della ricerca in ambito economico-aziendale ed informatico. Il corso è strutturato in tre curricula, denominati (i) Economia e Direzione delle Aziende Pubbliche, (i) Marketing e Comunicazione e (iii) Informatica, Sistemi Informativi e Tecnologie del Software. Attraverso i tre curricula, il corso di Dottorato intende formare figure professionali diverse, ma che riescano ad interagire per la soluzione di problemi complessi in ambito economico-aziendale, grazie all\'utilizzo di tecnologie dell\'informazione innovative e ad un approccio inter-disciplinare che favorisca la reciproca condivisione di idee e competenze. Il primo curriculum mira a formare specialisti nel settore del management di enti, istituzioni ed aziende afferenti al settore pubblico, con conoscenze relative a principi, teorie e modelli di gestione dei processi di innovazione nella Pubblica Amministrazione. Il secondo curriculum mira a formare specialisti in grado di utilizzare le più avanzate, innovative ed affidabili metodologie di ricerca scientifica in campo economico-sociale, con particolare riferimento al marketing ed alla comunicazione. Infine, il terzo curriculum mira a formare specialisti nel settore dell\'Informatica, con conoscenza degli aspetti teorici, metodologici e sperimentali dei sistemi informativi, dell\'ingegneria del software, dei dati e della conoscenza, dell\'elaborazione di immagini e dell\'interazione uomo-macchina, con applicazioni in particolare alla economia e alla gestione aziendale.\r\n\r\nIl completamento del Corso di Dottorato ed il superamento dell\'esame finale consente per tutti e tre i curricula lo svolgimento di attività di ricerca in ambito accademico, nei settori dell\'economia aziendale e dell\'informatica, costituendo un titolo legalmente riconosciuto nei concorsi universitari, nonché in enti di ricerca e nelle divisioni ricerca e sviluppo di aziende. Inoltre, la qualità del percorso formativo e le competenze specialistiche acquisite consentono l\'inserimento dei dottori di ricerca anche nel mondo del lavoro e delle professioni, nella Pubblica Amministrazione e nelle aziende.','2014',NULL),
	(16,'Il corso di Dottorato di Ricerca in Management & Information Technology ha come obiettivo la formazione di specialisti della ricerca in ambito economico-aziendale ed informatico. Il corso è strutturato in tre curricula, denominati (i) Economia e Direzione delle Aziende Pubbliche, (i) Marketing e Comunicazione e (iii) Informatica, Sistemi Informativi e Tecnologie del Software. Attraverso i tre curricula, il corso di Dottorato intende formare figure professionali diverse, ma che riescano ad interagire per la soluzione di problemi complessi in ambito economico-aziendale, grazie all\'utilizzo di tecnologie dell\'informazione innovative e ad un approccio inter-disciplinare che favorisca la reciproca condivisione di idee e competenze. Il primo curriculum mira a formare specialisti nel settore del management di enti, istituzioni ed aziende afferenti al settore pubblico, con conoscenze relative a principi, teorie e modelli di gestione dei processi di innovazione nella Pubblica Amministrazione. Il secondo curriculum mira a formare specialisti in grado di utilizzare le più avanzate, innovative ed affidabili metodologie di ricerca scientifica in campo economico-sociale, con particolare riferimento al marketing ed alla comunicazione. Infine, il terzo curriculum mira a formare specialisti nel settore dell\'Informatica, con conoscenza degli aspetti teorici, metodologici e sperimentali dei sistemi informativi, dell\'ingegneria del software, dei dati e della conoscenza, dell\'elaborazione di immagini e dell\'interazione uomo-macchina, con applicazioni in particolare alla economia e alla gestione aziendale.\n\nIl completamento del Corso di Dottorato ed il superamento dell\'esame finale consente per tutti e tre i curricula lo svolgimento di attività di ricerca in ambito accademico, nei settori dell\'economia aziendale e dell\'informatica, costituendo un titolo legalmente riconosciuto nei concorsi universitari, nonché in enti di ricerca e nelle divisioni ricerca e sviluppo di aziende. Inoltre, la qualità del percorso formativo e le competenze specialistiche acquisite consentono l\'inserimento dei dottori di ricerca anche nel mondo del lavoro e delle professioni, nella Pubblica Amministrazione e nelle aziende.','2014',NULL);

/*!40000 ALTER TABLE `phdcycle` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table prof_module_class
# ------------------------------------------------------------

DROP TABLE IF EXISTS `prof_module_class`;

CREATE TABLE `prof_module_class` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table professor_phdcurriculum
# ------------------------------------------------------------

DROP TABLE IF EXISTS `professor_phdcurriculum`;

CREATE TABLE `professor_phdcurriculum` (
  `FK_Professor` varchar(16) NOT NULL,
  `FK_PhdCurriculum` varchar(100) NOT NULL,
  PRIMARY KEY (`FK_Professor`,`FK_PhdCurriculum`),
  KEY `FK_Professor` (`FK_Professor`),
  KEY `FK_PhdCurriculum` (`FK_PhdCurriculum`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table professor_phdcycle
# ------------------------------------------------------------

DROP TABLE IF EXISTS `professor_phdcycle`;

CREATE TABLE `professor_phdcycle` (
  `FK_Professor` varchar(16) NOT NULL,
  `FK_PhdCycle` int(11) NOT NULL,
  PRIMARY KEY (`FK_Professor`,`FK_PhdCycle`),
  KEY `FK_PhdCycle` (`FK_PhdCycle`),
  KEY `FK_Professor` (`FK_Professor`),
  CONSTRAINT `professor_phdcycle_ibfk_1` FOREIGN KEY (`FK_Professor`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `professor_phdcycle_ibfk_2` FOREIGN KEY (`FK_PhdCycle`) REFERENCES `phdcycle` (`idPhdCycle`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `professor_phdcycle` WRITE;
/*!40000 ALTER TABLE `professor_phdcycle` DISABLE KEYS */;

INSERT INTO `professor_phdcycle` (`FK_Professor`, `FK_PhdCycle`)
VALUES
	('FLMFRR65M0A509S',15),
	('ANDDLC65M0N409C',16),
	('FLMFRR65M0A509S',16);

/*!40000 ALTER TABLE `professor_phdcycle` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table professor_student
# ------------------------------------------------------------

DROP TABLE IF EXISTS `professor_student`;

CREATE TABLE `professor_student` (
  `FK_Professor` varchar(16) NOT NULL,
  `FK_Student` varchar(16) NOT NULL,
  PRIMARY KEY (`FK_Professor`,`FK_Student`),
  KEY `FK_Professor` (`FK_Professor`),
  KEY `FK_Student` (`FK_Student`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table publication
# ------------------------------------------------------------

DROP TABLE IF EXISTS `publication`;

CREATE TABLE `publication` (
  `idPublication` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '',
  `authors` varchar(150) NOT NULL DEFAULT '',
  `abstract` text NOT NULL,
  `file` longblob,
  `year` varchar(4) NOT NULL DEFAULT '',
  `type` varchar(45) NOT NULL,
  `publicationIssue` varchar(100) NOT NULL DEFAULT '',
  `numberPages` int(11) NOT NULL,
  `FK_Student` varchar(16) NOT NULL,
  PRIMARY KEY (`idPublication`),
  KEY `FK_Student` (`FK_Student`),
  KEY `FK_Student_2` (`FK_Student`),
  KEY `FK_Student_3` (`FK_Student`),
  CONSTRAINT `publication_ibfk_1` FOREIGN KEY (`FK_Student`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `publication` WRITE;
/*!40000 ALTER TABLE `publication` DISABLE KEYS */;

INSERT INTO `publication` (`idPublication`, `title`, `authors`, `abstract`, `file`, `year`, `type`, `publicationIssue`, `numberPages`, `FK_Student`)
VALUES
	(1,'Mining Version Histories for Detecting Code Smells.','F. Palomba, G. Bavota, M. Di Penta, R.Oliveto, D. Poshyvanyk, A. De Lucia. ',' ',NULL,'2015','Journal','Transactions on Software Engineering (TSE)',34,'PLMFBA89M03F839I'),
	(2,'When and Why Your Code Starts to Smell Bad','M. Tufano, F. Palomba, G. Bavota, R. Oliveto, M. Di Penta, A. De Lucia, D. Poshyvanyk. ',' ',NULL,'2015','Conference','International Conference on Software Engineering (ICSE 2015)',11,'PLMFBA89M03F839I'),
	(4,'Supporting Extract Class Refactoring in Eclipse: The ARIES Project.','G. Bavota, A. De Lucia, A. Marcus, R. Oliveto, and F. Palomba.',' ',NULL,'2012','Conference','International Conference on Software Engineering (ICSE 2012)',4,'PLMFBA89M03F839I'),
	(5,'Anti-Pattern Detection: Methods, Challanges, and Open Issues.','F. Palomba, G. Bavota, R. Oliveto, and A. De Lucia. ',' ',NULL,'2015','Book Chapter','Advances in Computers',28,'PLMFBA89M03F839I'),
	(6,'ARIES: An Eclipse plug-in to Support Extract Class Refactoring.','G. Bavota, A. De Lucia, A. Marcus, R. Oliveto, F. Palomba, and M. Tufano. ','   ',NULL,'2013','Workshop','Italian Workshop on Eclipse Technologies',2,'PLMFBA89M03F839I'),
	(8,'Do They Really Smell Bad? A Study on Developers Perception of Bad Code Smells.','F. Palomba, G. Bavota, R. Oliveto, and A. De Lucia. ',' ',NULL,'2014','Conference','International Conference on Software Maintenance and Evolution (ICSME 2014)',10,'PLMFBA89M03F839I');

/*!40000 ALTER TABLE `publication` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table questionnaire
# ------------------------------------------------------------

DROP TABLE IF EXISTS `questionnaire`;

CREATE TABLE `questionnaire` (
  `student_ssn` varchar(16) NOT NULL,
  `company_name` varchar(45) NOT NULL,
  `typology_organization` varchar(45) DEFAULT NULL,
  `first_question` varchar(45) DEFAULT NULL,
  `second_question` varchar(45) DEFAULT NULL,
  `third_question` varchar(45) DEFAULT NULL,
  `fourth_question` varchar(45) DEFAULT NULL,
  `fifth_question` varchar(45) DEFAULT NULL,
  `sixth_question` varchar(45) DEFAULT NULL,
  `seventh_question` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`student_ssn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table rejected_training_message
# ------------------------------------------------------------

DROP TABLE IF EXISTS `rejected_training_message`;

CREATE TABLE `rejected_training_message` (
  `id_rejected_training_message` int(11) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `fk_person` varchar(16) NOT NULL,
  PRIMARY KEY (`id_rejected_training_message`),
  KEY `fk_RejectedTrainingMessage_Student1_idx` (`fk_person`),
  CONSTRAINT `rejected_training_message_ibfk_1` FOREIGN KEY (`fk_person`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table student_information
# ------------------------------------------------------------

DROP TABLE IF EXISTS `student_information`;

CREATE TABLE `student_information` (
  `curriculum_vitae_path` varchar(200) DEFAULT NULL,
  `accademic_transcript_path` varchar(200) DEFAULT NULL,
  `SSN` varchar(16) NOT NULL,
  `fk_student_status` int(11) NOT NULL,
  PRIMARY KEY (`SSN`),
  UNIQUE KEY `fk_status` (`fk_student_status`),
  CONSTRAINT `student_information_ibfk_1` FOREIGN KEY (`SSN`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_information_ibfk_2` FOREIGN KEY (`fk_student_status`) REFERENCES `student_status` (`id_student_status`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table student_phdclass
# ------------------------------------------------------------

DROP TABLE IF EXISTS `student_phdclass`;

CREATE TABLE `student_phdclass` (
  `FK_Student` varchar(16) CHARACTER SET utf8 NOT NULL,
  `FK_PhdClass` int(20) NOT NULL,
  PRIMARY KEY (`FK_Student`,`FK_PhdClass`),
  UNIQUE KEY `FK_Student` (`FK_Student`),
  KEY `FK_PhdClass` (`FK_PhdClass`),
  CONSTRAINT `student_phdclass_ibfk_1` FOREIGN KEY (`FK_Student`) REFERENCES `person` (`SSN`),
  CONSTRAINT `student_phdclass_ibfk_2` FOREIGN KEY (`FK_PhdClass`) REFERENCES `phdclass` (`idClass`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `student_phdclass` WRITE;
/*!40000 ALTER TABLE `student_phdclass` DISABLE KEYS */;

INSERT INTO `student_phdclass` (`FK_Student`, `FK_PhdClass`)
VALUES
	('PLMFBA89M03F839I',1);

/*!40000 ALTER TABLE `student_phdclass` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table student_status
# ------------------------------------------------------------

DROP TABLE IF EXISTS `student_status`;

CREATE TABLE `student_status` (
  `id_student_status` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_student_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `student_status` WRITE;
/*!40000 ALTER TABLE `student_status` DISABLE KEYS */;

INSERT INTO `student_status` (`id_student_status`, `description`)
VALUES
	(1,'Rifiutato'),
	(2,'Accettato'),
	(3,'In attesa'),
	(4,'Non inviato');

/*!40000 ALTER TABLE `student_status` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tag
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;

INSERT INTO `tag` (`ID`, `Name`)
VALUES
	(1,'Open Data'),
	(2,'IS'),
	(3,'Service'),
	(4,'Big Data'),
	(5,'Control System'),
	(6,'Management');

/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table teaching
# ------------------------------------------------------------

DROP TABLE IF EXISTS `teaching`;

CREATE TABLE `teaching` (
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



# Dump of table thesis
# ------------------------------------------------------------

DROP TABLE IF EXISTS `thesis`;

CREATE TABLE `thesis` (
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
  UNIQUE KEY `fk_person` (`ID_Student`),
  CONSTRAINT `thesis_ibfk_1` FOREIGN KEY (`ID_Student`) REFERENCES `person` (`SSN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `thesis` WRITE;
/*!40000 ALTER TABLE `thesis` DISABLE KEYS */;

INSERT INTO `thesis` (`ID`, `Start_Date`, `End_Date`, `Expected_End_Date`, `Title`, `Abstract`, `Description`, `ID_Student`, `Thesis_Status`)
VALUES
	(1,'2009-09-14','2005-12-14','2005-12-14','Open Data','Abstract della tesi \"Open Data\"','Descrizione della tesi \"Open Data\"','FRRMHL89M41H501S','0'),
	(2,'2013-03-03','2013-12-19','2013-09-17','La similarità dei motori scacchistici ai giorni nostri ','Abstract della tesi \"La similarità dei motori scacchistici ai giorni nostri\"','Descrizione della tesi \"IS\"','NNNDNL89M01A509C','1'),
	(4,'2011-05-14','2011-11-14','2011-11-14','La mia tesi','Abstract della tesi \"La mia tesi\"','Descrizione della tesi \"La mia tesi\"','DMNSNS90M0N309C','2'),
	(5,'2014-09-14','2014-12-19','2015-03-15','Sistemi di controllo','Abstract della tesi \"Sistemi di controllo\"','Descrizione della tesi \"Sistemi di controllo\"','CSMGRT89M0A309C','0'),
	(6,'2014-01-14','2014-12-19','2015-03-15','Management','Abstract della tesi \"Management\"','Descrizione della tesi \"Management\"','MRRRSS88M0A309C','1'),
	(7,'2005-01-14','2005-08-09','2005-10-15','App Android','Abstract della tesi \"App Android\"','Descrizione della tesi \"App Android\"','MRRTDD85M0A309S','0');

/*!40000 ALTER TABLE `thesis` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table thesis_supervisor
# ------------------------------------------------------------

DROP TABLE IF EXISTS `thesis_supervisor`;

CREATE TABLE `thesis_supervisor` (
  `ID_Thesis` int(11) NOT NULL,
  `ID_Professor` varchar(16) NOT NULL,
  KEY `ID_Thesis` (`ID_Thesis`),
  KEY `ID_Professor` (`ID_Professor`),
  CONSTRAINT `thesis_supervisor_ibfk_1` FOREIGN KEY (`ID_Thesis`) REFERENCES `thesis` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `thesis_supervisor_ibfk_2` FOREIGN KEY (`ID_Professor`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `thesis_supervisor` WRITE;
/*!40000 ALTER TABLE `thesis_supervisor` DISABLE KEYS */;

INSERT INTO `thesis_supervisor` (`ID_Thesis`, `ID_Professor`)
VALUES
	(1,'FLMFRR65M0A509S'),
	(2,'FLMFRR65M0A509S'),
	(4,'FLMFRR65M0A509S'),
	(5,'MRZTCC62M0A309C'),
	(6,'RTTFRN68M0A309S'),
	(7,'MRZTCC62M0A309C');

/*!40000 ALTER TABLE `thesis_supervisor` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table thesis_tag
# ------------------------------------------------------------

DROP TABLE IF EXISTS `thesis_tag`;

CREATE TABLE `thesis_tag` (
  `ID_thesis` int(11) NOT NULL,
  `ID_tag` int(11) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `fk_thesis` (`ID_thesis`) USING BTREE,
  KEY `fk_tag` (`ID_tag`) USING BTREE,
  CONSTRAINT `thesis_tag_ibfk_1` FOREIGN KEY (`ID_thesis`) REFERENCES `thesis` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `thesis_tag_ibfk_2` FOREIGN KEY (`ID_tag`) REFERENCES `tag` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `thesis_tag` WRITE;
/*!40000 ALTER TABLE `thesis_tag` DISABLE KEYS */;

INSERT INTO `thesis_tag` (`ID_thesis`, `ID_tag`, `ID`)
VALUES
	(1,1,1),
	(2,2,2),
	(4,4,4),
	(5,5,5),
	(6,6,6);

/*!40000 ALTER TABLE `thesis_tag` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table training_offer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `training_offer`;

CREATE TABLE `training_offer` (
  `id_training_offer` int(11) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `fk_person` varchar(16) NOT NULL,
  `fk_department` varchar(50) DEFAULT NULL,
  `fk_organization` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id_training_offer`),
  KEY `fk_OfferTraining_Professor1_idx` (`fk_person`),
  KEY `fk_OfferTraining_Department1_idx` (`fk_department`),
  KEY `fk_OfferTraining_Organization1` (`fk_organization`),
  CONSTRAINT `fk_OfferTraining_Department1` FOREIGN KEY (`fk_department`) REFERENCES `department` (`abbreviation`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_OfferTraining_Organization1` FOREIGN KEY (`fk_organization`) REFERENCES `organization` (`vat_number`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_OfferTraining_Professor1` FOREIGN KEY (`fk_person`) REFERENCES `person` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table training_request
# ------------------------------------------------------------

DROP TABLE IF EXISTS `training_request`;

CREATE TABLE `training_request` (
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
  KEY `fk_training_request_student_information1` (`student_information_SSN`),
  CONSTRAINT `fk_ClaimTraining_ClaimStatus1` FOREIGN KEY (`fk_training_status`) REFERENCES `training_status` (`id_training_status`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_training_request_student_information1` FOREIGN KEY (`student_information_SSN`) REFERENCES `student_information` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `training_request_ibfk_1` FOREIGN KEY (`fk_person`) REFERENCES `person` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `training_request_ibfk_2` FOREIGN KEY (`fk_organization`) REFERENCES `organization` (`vat_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table training_status
# ------------------------------------------------------------

DROP TABLE IF EXISTS `training_status`;

CREATE TABLE `training_status` (
  `id_training_status` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_training_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `training_status` WRITE;
/*!40000 ALTER TABLE `training_status` DISABLE KEYS */;

INSERT INTO `training_status` (`id_training_status`, `description`)
VALUES
	(1,'Iniziato'),
	(2,'Pre-completamento'),
	(3,'Completo');

/*!40000 ALTER TABLE `training_status` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
