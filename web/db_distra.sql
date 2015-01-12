-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Gen 12, 2015 alle 20:12
-- Versione del server: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_distra`
--
CREATE DATABASE IF NOT EXISTS `db_distra` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `db_distra`;

-- --------------------------------------------------------

--
-- Struttura della tabella `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `email` varchar(100) NOT NULL COMMENT 'Nome con il quale l''utente viene riconosciuto da un',
  `password` varchar(45) NOT NULL,
  `typeOfAccount` varchar(45) NOT NULL,
  `active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabella adibita alla gestione dei dati principali per l''acesso al sistema';

--
-- Dump dei dati per la tabella `account`
--

INSERT INTO `account` (`email`, `password`, `typeOfAccount`, `active`) VALUES
('a.longo@studenti.unisa.it', '0000', 'Mstudent', 1),
('a.saulino@studenti.unisa.it', 'a.saulino', 'Mstudent', 1),
('adl@unisa.it', '0000', 'professor', 1),
('admin@unisa.it', '0000', 'admin', 1),
('c.borges@studenti.unisa.it', 'c.borges', 'phd', 1),
('ciro@gmail.com', 'ciropass', 'Mstudente', 1),
('ciro@unisa.it', '123123', 'Mstudent', 1),
('cosimo@gmail.com', 'cosimo', 'Mstudent', 1),
('d.dechiara7@studenti.unisa.it', '0000', 'Mstudent', 1),
('d.iannone@gmail.com', 'daniele', 'Mstudent', 1),
('damiano@gmail.com', 'dami', 'Mstudent', 0),
('delucia@gmail.com', 'andrea', 'professor', 1),
('f.polese@professori.unisa.it', 'f.polese', 'professor', 1),
('ferrucci@gmail.com', '0000', 'professor', 1),
('fpalomba@unisa.it', 'fabio', 'phdadmin', 1),
('francese@gmail.com', '0000', 'professor', 1),
('g.vaia@professori.unisa.it', 'g.vaia', 'professor', 1),
('gemma.catolino91@gmail.com', 'CIAO', 'Mstudent', 1),
('m.taddeo@gmail.com', 'taddeo', 'Bstudent', 0),
('mariorossi@gmail.com', 'mario', 'Bstudent', 0),
('martina@unisa.it', '123123', 'Mstudent', 1),
('mikela_f@hotmail.it', 'ciao', 'Mstudent', 1),
('moderna@azienda.unisa.it', 'moderna', 'company', 1),
('nuovo@unisa.it', '123123', 'Bstudent', 1),
('nuovoprof@unisa.it', '123123', 'professor', 1),
('polese@gmail.com', 'polese', 'professor', 0),
('prisma_s.r.l.@azienda.unisa.it', 'prisma', 'company', 1),
('prof@unisa.it', '123123', 'professor', 1),
('staff@staff.unisa.it', 'staff', 'admin', 1),
('tucci@gmail.com', '0000', 'professor', 0),
('v.vivone@studenti.unisa.it', 'v.vivone', 'Bstudent', 1),
('vaia@gmail.com', 'vaia', 'professor', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
`ID` int(11) NOT NULL,
  `Object` varchar(255) NOT NULL,
  `ID_Thesis` int(11) NOT NULL,
  `Status` enum('0','1') NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `chronology`
--

CREATE TABLE IF NOT EXISTS `chronology` (
`ID` int(11) NOT NULL,
  `Text` varchar(1000) NOT NULL,
  `ID_Professor` varchar(16) NOT NULL,
  `ID_Student` varchar(16) NOT NULL,
  `Notice_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Type` varchar(255) DEFAULT NULL COMMENT 'values: richiesta, accetta, rifiuta, modifica'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `class`
--

CREATE TABLE IF NOT EXISTS `class` (
  `title` varchar(50) NOT NULL,
  `teaching_matricula` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `class`
--

INSERT INTO `class` (`title`, `teaching_matricula`) VALUES
('Unica', '0222500002'),
('Unica', '0222500005'),
('Unica', '0222500008');

-- --------------------------------------------------------

--
-- Struttura della tabella `class_event`
--

CREATE TABLE IF NOT EXISTS `class_event` (
  `FK_Class` int(11) NOT NULL,
  `FK_Event` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `class_notice`
--

CREATE TABLE IF NOT EXISTS `class_notice` (
  `FK_Class` int(11) NOT NULL,
  `FK_Event` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `collaboration`
--

CREATE TABLE IF NOT EXISTS `collaboration` (
`idCollaboration` int(20) NOT NULL,
  `istitution` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `FK_Student` varchar(16) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dump dei dati per la tabella `collaboration`
--

INSERT INTO `collaboration` (`idCollaboration`, `istitution`, `description`, `startDate`, `endDate`, `FK_Student`) VALUES
(1, 'The College of William & Mary', ' Preparazione paper ''When and Why Your Code Starts to Smell Bad'' sottomesso ad ICSE il 05/09/2014', '2014-06-01', '2014-09-05', 'PLMFBA89M03F839I'),
(2, 'The College of William & Mary', 'Preparazione paper su app Android sottomesso ad ICSE il 05/09/2014  ', '2014-07-03', '2014-09-05', 'PLMFBA89M03F839I'),
(3, 'Ecole Polytechnique de Montreal', ' Preparazione paper su prioritizzazione dei bad smell ', '2014-04-01', '2015-01-11', 'PLMFBA89M03F839I');

-- --------------------------------------------------------

--
-- Struttura della tabella `curriculum`
--

CREATE TABLE IF NOT EXISTS `curriculum` (
  `title` varchar(100) NOT NULL,
  `matricula` varchar(45) NOT NULL,
  `degree_matricula` varchar(5) NOT NULL,
  `active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `curriculum`
--

INSERT INTO `curriculum` (`title`, `matricula`, `degree_matricula`, `active`) VALUES
('Tecnologie Informatiche e Management', 'PDS0-2014', '02225', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `curriculum_teaching`
--

CREATE TABLE IF NOT EXISTS `curriculum_teaching` (
  `curriculum_matricula` varchar(45) NOT NULL,
  `teaching_matricula` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `curriculum_teaching`
--

INSERT INTO `curriculum_teaching` (`curriculum_matricula`, `teaching_matricula`) VALUES
('PDS0-2014', '0222500002'),
('PDS0-2014', '0222500008'),
('PDS0-2014', '0222500005');

-- --------------------------------------------------------

--
-- Struttura della tabella `cycle`
--

CREATE TABLE IF NOT EXISTS `cycle` (
  `cycle_number` int(11) NOT NULL,
  `title` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `cycle`
--

INSERT INTO `cycle` (`cycle_number`, `title`) VALUES
(1, 'Laurea Triennale'),
(2, 'Laurea Magistrale'),
(3, 'Dottorato'),
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
  `esse3_content` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `degree`
--

INSERT INTO `degree` (`title`, `matricula`, `link`, `department_abbreviation`, `cycle_number`, `active`, `esse3_content`) VALUES
('Tecnologie Informatiche e Management', '02225', 'https://esse3web.unisa.it/unisa/Guide/PaginaCorso.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?corso_id=500408', 'DISTRA', 2, 1, '<div id="infobox" class="record"> \n <dl class="record-riga"> \n  <dt>\n   Ordinamento\n  </dt> \n  <dd> \n   <description> \n    <ordinamento>\n     TECNOLOGIE INFORMATICHE E MANAGEMENT\n    </ordinamento> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Durata\n  </dt> \n  <dd> \n   <description> \n    <durata>\n     2\n    </durata> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Crediti\n  </dt> \n  <dd> \n   <description> \n    <ncrediti>\n     120\n    </ncrediti> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Tipo di Corso\n  </dt> \n  <dd> \n   <description> \n    <tipocorso_des>\n     CORSO DI LAUREA MAGISTRALE\n    </tipocorso_des> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Normativa\n  </dt> \n  <dd> \n   <description> \n    <normativa>\n     D.M. 270/2004\n    </normativa> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Classe di Laurea\n  </dt> \n  <dd> \n   <a id="box-item-anchorinfobox-6-1" href="Guide/PaginaClasse.do;jsessionid=67CABF2FC57761268E10D255BE086497.jvm6?classe_id=LM-18" title="Accedi alla pagina della&nbsp;Classe delle lauree magistrali in Informatica">LM-18 - Classe delle lauree magistrali in Informatica</a> \n   <br> \n  </dd> \n  <dt>\n   Tipo di Accesso\n  </dt> \n  <dd> \n   <description>\n    Accesso Libero\n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Sedi Didattiche\n  </dt> \n  <dd> \n   <description>\n    UNIVERSITA&apos; DEGLI STUDI DI SALERNO - FISCIANO\n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Elenco Insegnamenti per Percorso/Curriculum\n  </dt> \n  <dd> \n   <a id="box-item-anchorinfobox-9-1" href="Guide/PaginaPercorso.do;jsessionid=67CABF2FC57761268E10D255BE086497.jvm6?corso_id=500408&amp;percorso_id=500408*2014*9999&amp;ANNO_ACCADEMICO=2014" title="Accedi alla pagina del percorso:&nbsp;COMUNE">COMUNE - PDS0-2014</a> \n   <br> \n  </dd> \n </dl> \n <br> \n</div>'),
('Economia e gestione delle imprese', '02256', 'https://esse3web.unisa.it/unisa/Guide/PaginaCorso.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?corso_id=500307', 'DISTRA', 1, 1, '<div id="infobox" class="record"> \n <dl class="record-riga"> \n  <dt>\n   Ordinamento\n  </dt> \n  <dd> \n   <description> \n    <ordinamento>\n     ECONOMIA E GESTIONE DELLE IMPRESE\n    </ordinamento> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Durata\n  </dt> \n  <dd> \n   <description> \n    <durata>\n     3\n    </durata> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Crediti\n  </dt> \n  <dd> \n   <description> \n    <ncrediti>\n     180\n    </ncrediti> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Tipo di Corso\n  </dt> \n  <dd> \n   <description> \n    <tipocorso_des>\n     CORSO DI LAUREA\n    </tipocorso_des> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Normativa\n  </dt> \n  <dd> \n   <description> \n    <normativa>\n     D.M. 270/2004\n    </normativa> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Classe di Laurea\n  </dt> \n  <dd> \n   <a id="box-item-anchorinfobox-6-1" href="Guide/PaginaClasse.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?classe_id=L-18" title="Accedi alla pagina della&nbsp;Classe delle lauree in Scienze dell&apos;economia e della gestione aziendale">L-18 - Classe delle lauree in Scienze dell&apos;economia e della gestione aziendale</a> \n   <br> \n  </dd> \n  <dt>\n   Tipo di Accesso\n  </dt> \n  <dd> \n   <description>\n    Immatricolazione non consentita\n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Sedi Didattiche\n  </dt> \n  <dd> \n   <description>\n    UNIVERSITA&apos; DEGLI STUDI DI SALERNO - FISCIANO\n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Elenco Insegnamenti per Percorso/Curriculum\n  </dt> \n  <dd> \n   <a id="box-item-anchorinfobox-9-1" href="Guide/PaginaPercorso.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?corso_id=500307&amp;percorso_id=500307*2011*9999&amp;ANNO_ACCADEMICO=2014" title="Accedi alla pagina del percorso:&nbsp;COMUNE">COMUNE - PDS0-2011</a> \n   <br> \n  </dd> \n </dl> \n <br> \n</div>'),
('Economia e Management', '03345', 'https://esse3web.unisa.it/unisa/Guide/PaginaCorso.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?corso_id=500410', 'DISTRA', 1, 1, '<div id="infobox" class="record"> \n <dl class="record-riga"> \n  <dt>\n   Ordinamento\n  </dt> \n  <dd> \n   <description> \n    <ordinamento>\n     ECONOMIA E MANAGEMENT\n    </ordinamento> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Durata\n  </dt> \n  <dd> \n   <description> \n    <durata>\n     3\n    </durata> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Crediti\n  </dt> \n  <dd> \n   <description> \n    <ncrediti>\n     180\n    </ncrediti> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Tipo di Corso\n  </dt> \n  <dd> \n   <description> \n    <tipocorso_des>\n     CORSO DI LAUREA\n    </tipocorso_des> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Normativa\n  </dt> \n  <dd> \n   <description> \n    <normativa>\n     D.M. 270/2004\n    </normativa> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Classe di Laurea\n  </dt> \n  <dd> \n   <a id="box-item-anchorinfobox-6-1" href="Guide/PaginaClasse.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?classe_id=L-18" title="Accedi alla pagina della&nbsp;Classe delle lauree in Scienze dell&apos;economia e della gestione aziendale">L-18 - Classe delle lauree in Scienze dell&apos;economia e della gestione aziendale</a> \n   <br> \n  </dd> \n  <dt>\n   Tipo di Accesso\n  </dt> \n  <dd> \n   <description>\n    Accesso Programmato\n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Sedi Didattiche\n  </dt> \n  <dd> \n   <description>\n    UNIVERSITA&apos; DEGLI STUDI DI SALERNO - FISCIANO\n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Elenco Insegnamenti per Percorso/Curriculum\n  </dt> \n  <dd> \n   <a id="box-item-anchorinfobox-9-1" href="Guide/PaginaPercorso.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?corso_id=500410&amp;percorso_id=500410*2014*10001&amp;ANNO_ACCADEMICO=2014" title="Accedi alla pagina del percorso:&nbsp;AMMINISTRAZIONE, FINANZA E CONTROLLO ">AMMINISTRAZIONE, FINANZA E CONTROLLO - 02127P0002</a> \n   <br> \n   <a id="box-item-anchorinfobox-9-2" href="Guide/PaginaPercorso.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?corso_id=500410&amp;percorso_id=500410*2014*9999&amp;ANNO_ACCADEMICO=2014" title="Accedi alla pagina del percorso:&nbsp;COMUNE">COMUNE - PDS0-2014</a> \n   <br> \n   <a id="box-item-anchorinfobox-9-3" href="Guide/PaginaPercorso.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?corso_id=500410&amp;percorso_id=500410*2014*10000&amp;ANNO_ACCADEMICO=2014" title="Accedi alla pagina del percorso:&nbsp;ECONOMIA E GESTIONE DELLE IMPRESE ">ECONOMIA E GESTIONE DELLE IMPRESE - 02127P0001</a> \n   <br> \n   <a id="box-item-anchorinfobox-9-4" href="Guide/PaginaPercorso.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?corso_id=500410&amp;percorso_id=500410*2014*10003&amp;ANNO_ACCADEMICO=2014" title="Accedi alla pagina del percorso:&nbsp;ESPERTO IN GESTIONE DEI BENI E SERVIZI TURISTICO-CULTURALI E DEI GRANDI EVENTI">ESPERTO IN GESTIONE DEI BENI E SERVIZI TURISTICO-CULTURALI E DEI GRANDI EVENTI - 02127P0004</a> \n   <br> \n   <a id="box-item-anchorinfobox-9-5" href="Guide/PaginaPercorso.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?corso_id=500410&amp;percorso_id=500410*2014*10002&amp;ANNO_ACCADEMICO=2014" title="Accedi alla pagina del percorso:&nbsp;MANAGEMENT E NUOVE TECNOLOGIE">MANAGEMENT E NUOVE TECNOLOGIE - 02127P0003</a> \n   <br> \n  </dd> \n </dl> \n <br> \n</div>'),
('Consulenza e Management Aziendale', '04453', 'https://esse3web.unisa.it/unisa/Guide/PaginaCorso.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?corso_id=500158', 'DISTRA', 2, 1, '<div id="infobox" class="record"> \n <dl class="record-riga"> \n  <dt>\n   Ordinamento\n  </dt> \n  <dd> \n   <description> \n    <ordinamento>\n     CONSULENZA E MANAGEMENT AZIENDALE\n    </ordinamento> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Durata\n  </dt> \n  <dd> \n   <description> \n    <durata>\n     2\n    </durata> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Crediti\n  </dt> \n  <dd> \n   <description> \n    <ncrediti>\n     120\n    </ncrediti> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Tipo di Corso\n  </dt> \n  <dd> \n   <description> \n    <tipocorso_des>\n     CORSO DI LAUREA MAGISTRALE\n    </tipocorso_des> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Normativa\n  </dt> \n  <dd> \n   <description> \n    <normativa>\n     D.M. 270/2004\n    </normativa> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Classe di Laurea\n  </dt> \n  <dd> \n   <a id="box-item-anchorinfobox-6-1" href="Guide/PaginaClasse.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?classe_id=LM-77" title="Accedi alla pagina della&nbsp;Classe delle lauree magistrali in Scienze economico-aziendali">LM-77 - Classe delle lauree magistrali in Scienze economico-aziendali</a> \n   <br> \n  </dd> \n  <dt>\n   Tipo di Accesso\n  </dt> \n  <dd> \n   <description>\n    Accesso Programmato\n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Sedi Didattiche\n  </dt> \n  <dd> \n   <description>\n    UNIVERSITA&apos; DEGLI STUDI DI SALERNO - FISCIANO\n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Elenco Insegnamenti per Percorso/Curriculum\n  </dt> \n  <dd> \n   <a id="box-item-anchorinfobox-9-1" href="Guide/PaginaPercorso.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?corso_id=500158&amp;percorso_id=500158*2014*9999&amp;ANNO_ACCADEMICO=2014" title="Accedi alla pagina del percorso:&nbsp;COMUNE">COMUNE - PDS0-2014</a> \n   <br> \n   <a id="box-item-anchorinfobox-9-2" href="Guide/PaginaPercorso.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?corso_id=500158&amp;percorso_id=500158*2014*10002&amp;ANNO_ACCADEMICO=2014" title="Accedi alla pagina del percorso:&nbsp;CONSULENZA PROFESSIONALE PER L&apos;IMPRESA">CONSULENZA PROFESSIONALE PER L&apos;IMPRESA - 02221P0003</a> \n   <br> \n   <a id="box-item-anchorinfobox-9-3" href="Guide/PaginaPercorso.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?corso_id=500158&amp;percorso_id=500158*2014*10001&amp;ANNO_ACCADEMICO=2014" title="Accedi alla pagina del percorso:&nbsp;MANAGEMENT PUBBLICO">MANAGEMENT PUBBLICO - 02221P0002</a> \n   <br> \n   <a id="box-item-anchorinfobox-9-4" href="Guide/PaginaPercorso.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?corso_id=500158&amp;percorso_id=500158*2014*10000&amp;ANNO_ACCADEMICO=2014" title="Accedi alla pagina del percorso:&nbsp;MANAGEMENT STRATEGICO ">MANAGEMENT STRATEGICO - 02221P0001</a> \n   <br> \n  </dd> \n </dl> \n <br> \n</div>'),
('Management and Information Technology', '88876', 'https://esse3web.unisa.it/unisa/Guide/PaginaCorso.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?corso_id=500373', 'DISTRA', 3, 1, '<div id="infobox" class="record"> \n <dl class="record-riga"> \n  <dt>\n   Ordinamento\n  </dt> \n  <dd> \n   <description> \n    <ordinamento>\n     MANAGEMENT&amp;INFORMATION TECHNOLOGY\n    </ordinamento> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Durata\n  </dt> \n  <dd> \n   <description> \n    <durata>\n     3\n    </durata> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Crediti\n  </dt> \n  <dd> \n   <description> \n    <ncrediti></ncrediti> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Tipo di Corso\n  </dt> \n  <dd> \n   <description> \n    <tipocorso_des>\n     CORSO DI DOTTORATO\n    </tipocorso_des> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Normativa\n  </dt> \n  <dd> \n   <description> \n    <normativa>\n     Ante Riforma\n    </normativa> \n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Classe di Laurea\n  </dt> \n  <dd>\n   &nbsp;\n   <br> \n  </dd> \n  <dt>\n   Tipo di Accesso\n  </dt> \n  <dd> \n   <description>\n    Immatricolazione non consentita\n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Sedi Didattiche\n  </dt> \n  <dd> \n   <description>\n    UNIVERSITA&apos; DEGLI STUDI DI SALERNO - FISCIANO\n   </description>&nbsp;\n   <br> \n  </dd> \n  <dt>\n   Elenco Insegnamenti per Percorso/Curriculum\n  </dt> \n  <dd>\n   &nbsp;\n   <br> \n  </dd> \n </dl> \n <br> \n</div>');

-- --------------------------------------------------------

--
-- Struttura della tabella `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `abbreviation` varchar(50) NOT NULL,
  `title` varchar(500) DEFAULT NULL,
  `url_moodle` varchar(1000) DEFAULT NULL,
  `token` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `department`
--

INSERT INTO `department` (`abbreviation`, `title`, `url_moodle`, `token`) VALUES
('DISTRA', 'Dipartimento di Studi e Ricerche Aziendali ', 'http://localhost/moodle', '2c6f2faa8aac80fc912cff9f6f8bad66');

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
  `type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `event_professor`
--

CREATE TABLE IF NOT EXISTS `event_professor` (
  `FK_Event` int(11) NOT NULL,
  `FK_Professor` varchar(16) NOT NULL
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
  `FK_Event` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `lesson_student`
--

CREATE TABLE IF NOT EXISTS `lesson_student` (
  `FK_Lesson` int(20) NOT NULL,
  `FK_Student` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `mission`
--

CREATE TABLE IF NOT EXISTS `mission` (
`idMission` int(20) NOT NULL,
  `place` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `FK_Student` varchar(16) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dump dei dati per la tabella `mission`
--

INSERT INTO `mission` (`idMission`, `place`, `description`, `startDate`, `endDate`, `FK_Student`) VALUES
(1, 'Victoria, British Columbia - Canada', 'Partecipazione alla International Conference on Software Maintenance and Evolution (ICSME''14) ', '2014-09-27', '2014-10-04', 'PLMFBA89M03F839I'),
(2, 'Palo Alto, California - USA', ' Partecipazione alla International Conference on Automated Software Engineering (ASE''13).', '2013-11-16', '2013-11-21', 'PLMFBA89M03F839I');

-- --------------------------------------------------------

--
-- Struttura della tabella `module`
--

CREATE TABLE IF NOT EXISTS `module` (
  `title` varchar(50) NOT NULL,
  `teaching_matricula` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `module`
--

INSERT INTO `module` (`title`, `teaching_matricula`) VALUES
('IT Project Management', '0222500002'),
('IT Service Management', '0222500002'),
('Sviluppo applicazioni mobili', '0222500005'),
('Sistemi Informativi e Territoriali', '0222500008');

-- --------------------------------------------------------

--
-- Struttura della tabella `notice`
--

CREATE TABLE IF NOT EXISTS `notice` (
  `idNotice` int(11) NOT NULL,
  `object` varchar(45) NOT NULL,
  `text` text NOT NULL
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
  `fk_professor` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `organization`
--

INSERT INTO `organization` (`vat_number`, `company_name`, `city`, `address`, `phone`, `email`, `fk_account`, `fk_external_tutor`, `fk_professor`) VALUES
('ABC852', 'Prisma S.r.L.', 'Potenza', 'via xxx', '082658741', 'prisma_s.r.l.@gmail.com', 'prisma_s.r.l.@azienda.unisa.it', NULL, 'VIAGVN66'),
('XYZ523', 'Moderna', 'Modena', 'via xxx', '333963258', 'moderna@gmail.com', 'moderna@azienda.unisa.it', NULL, 'PLSFRN66');

-- --------------------------------------------------------

--
-- Struttura della tabella `pending_acceptance`
--

CREATE TABLE IF NOT EXISTS `pending_acceptance` (
`id_pending_acceptance` int(11) NOT NULL,
  `request_date` date DEFAULT NULL,
  `fk_person` varchar(16) NOT NULL
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
  `cover_letter` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `person`
--

INSERT INTO `person` (`SSN`, `name`, `surname`, `phone`, `city`, `address`, `zip_code`, `gender`, `citizenship`, `Account_email`, `Department_abbreviation`, `web_page`, `university`, `matricula`, `position`, `cycle`, `degree_matricula`, `cover_letter`) VALUES
('ALSLNG87C23H703Y', 'Alessandro', 'Longo', '83013865051', 'Roma', 'via verdi', '83452', 'M', 'Italiana', 'a.longo@studenti.unisa.it', 'DISTRA', 'null', 'UniversitÃ  degli studi Salerno', '3532532', 'null', 2, '02225', 'null'),
('ANDDLC65M0N409C', 'Andrea', 'De Lucia', '3384673356', 'Salerno', NULL, NULL, 'M', NULL, 'adl@unisa.it', 'DISTRA', 'ww.delucia.it', 'Università di Salerno', '022552585', 'professor', 1, '02225', NULL),
('BNCMRA53', 'Mario', 'Bianchi', NULL, NULL, NULL, NULL, 'M', 'Italiana', 'prisma_s.r.l.@azienda.unisa.it', 'distra', NULL, 'Unisa', NULL, NULL, 5, NULL, NULL),
('BRGCRL89', 'Carlos', 'Borges', '7852146', 'Barquisimeto', 'via xxx', '11111', 'M', 'Venezuelana', 'c.borges@studenti.unisa.it', 'distra', NULL, 'Unisa', '00012', NULL, 3, '02225', NULL),
('CRRPLMC91M0N409C', 'Ciro', 'Palumbo', NULL, NULL, NULL, NULL, 'M', NULL, 'ciro@unisa.it', 'DISTRA', NULL, NULL, '022552585', 'student', 1, '02225', NULL),
('CSMGRT89M0A309C', 'Cosimo', 'Grattacaso', '3334536354', 'Paestum', 'via sicilia', '87832', 'M', 'italiana', 'cosimo@gmail.com', 'DISTRA', 'www.cosimo.it', 'Università di Salerno', '022274382', 'student', 1, '02225', NULL),
('CSRNTN91L26C!")J', 'Mario', 'Rossi', NULL, NULL, NULL, NULL, NULL, NULL, 'admin@unisa.it', 'DISTRA', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('CTLGMM91A71B519A', 'Golli', 'Catolino', '3272844649', 'Baranello', 'Contrada Gaudo n. 27', '86011', 'F', 'Italiana', 'gemma.catolino91@gmail.com', 'DISTRA', 'http://www.unisa.it/docenti/filomenaferrucci/index', 'UniversitÃ  degli Studi di Salerno', '022552585', 'null', 1, '02225', '     Nel mio percorso di studente di dottorato del curriculum EDAP, intendo occuparmi del management delle Pubbliche Amministrazioni, in ottica sistemico-relazionale. Lo studio della gestione delle PA sarÃ????Ã???Ã??Ã?Â  sviluppato attraverso lÃ???Ã??Ã?Â¢??analisi di nuovi e piÃ????Ã???Ã??Ã?Â¹ recenti studi che propongono per il management pubblico nuovi approcci come ad esempio il Ã???Ã??Ã?Â¢??Public Service-DominantÃ???Ã??Ã?Â¢??, ovvero un approccio basato sui servizi pubblici e che enfatizza i processi di trasformazione della conoscenza. I miei interessi dunque, includono lo studio di quelle prospettive teoriche che tendono a fornire una lettura relazionale de     '),
('DCHDVD91B22H703Y', 'Davide', 'De Chiara', '392949', 'Baronissi', 'Rossini', '84081', 'M', 'Italiana', 'd.dechiara7@studenti.unisa.it', 'DISTRA', 'null', 'UniversitÃ  di Salerno', '1151135135', 'null', 2, '02225', 'null'),
('DMNSNS90M0N309C', 'Damiano', 'Senese', '3456789098', 'Salerno', 'via casilina', '08768', 'M', 'italiano', 'damiano@gmail.com', 'DISTRA', 'www.dami.it', 'Università degli Studi di Salerno', '022552283', 'student', 1, '02225', NULL),
('FLMFRR65M0A509S', 'Filomena', 'Ferrucci', NULL, NULL, NULL, NULL, 'F', 'italiana', 'ferrucci@gmail.com', 'DISTRA', NULL, 'Università degli Studi di Salerno', '022552585', 'professor', 1, '02225', NULL),
('FRRMHL89M41H501S', 'Michela', 'Ferrante', '3456787917', 'Frosinone', 'via casilina', '03100', 'F', 'Italiana', 'mikela_f@hotmail.it', 'DISTRA', 'www.miki.it', 'Università degli Studi di Salerno', '022554678', 'student', 1, '02225', NULL),
('MARTDD85M0A309S', 'Martina', 'Munno', '265454', 'Salerno', NULL, '84014', 'F', NULL, 'martina@unisa.it', 'DISTRA', NULL, NULL, '0225512311', 'student', 1, '02225', NULL),
('MRRRSS88M0A309C', 'Mario', 'Rossi', '3334536354', 'Napoli', 'via le lame', '83645', 'M', 'italiana', 'mariorossi@gmail.com', 'DISTRA', 'www.rossi.it', 'Università di Salerno', '0222736745', 'student', 1, '02225', NULL),
('MRRTDD85M0A309S', 'Maria', 'Taddeo', '3487654362', 'Matera', 'via roma', '65374', 'F', 'italiana', 'm.taddeo@gmail.com', 'DISTRA', 'taddeo.it', 'Università di Salerno', '0222736453', 'student', 1, '02225', NULL),
('MRZTCC62M0A309C', 'Maurizio', 'Tucci', '3456253625', 'Salerno', 'via verdi', '84536', 'M', 'italiana', 'tucci@gmail.com', 'DISTRA', 'tucci.com', 'Università di Salerno', '022552585', 'professor', 1, '02225', NULL),
('NNNDNL89M01A509C', 'Daniele', 'Iannone', '3456786764', 'Montoro', 'via verdi', '87654', 'M', 'italiano', 'd.iannone@gmail.com', 'DISTRA', NULL, 'Università degli Studi di Salerno', '022554736', 'student', 1, '02225', NULL),
('PLMFBA89M03F839I', 'Fabio', 'Palomba', '3884430360', 'Fisciano', 'Via Tenente Nastri 112', '84084', 'M', 'italiana', 'fpalomba@unisa.it', 'DISTRA', 'https://dibt.unimol.it/fpalomba', 'UniversitÃ  degli Studi di Salerno', 'null', 'null', 3, '88876', 'BAD CODE SMELLS DETECTION <br>\r\nBad code smells have been defined by Martin Fowler as symptoms of poor design and implementation choices. Bad smells are usually introduced in software systems because developers poorly conceived the design of the code component or because they did not care about properly designing the solution due to strict deadlines. Complex Class, i.e., a class that contain complex methods and it is very large in terms of LOC; or God Class, i.e., a class that does too much/knows too much about other classes, are only some examples of a plethora of bad smells identified and characterized in well-known catalogues. Recent empirical studies showed that code smells hinder comprehensibility, and possibly increase change- and fault- proneness. For these reasons, the main research topic in this area is the definition of new approaches able to detect bad code smells in the source code.<br><br>\r\n\r\nMINING SOFTWARE REPOSITORIES<br>\r\nSoftware repositories such as source control systems, archived communications between project personnel, and defect tracking systems are used to help manage the progress of software projects. Software practitioners and researchers are recognizing the benefits of mining this information to support the maintenance of software systems, improve software design/reuse, and empirically validate novel ideas and techniques. Research is now proceeding to uncover the ways in which mining these repositories can help to understand software development and software evolution, to support predictions about software development, and to exploit this knowledge in planning future development.<br><br>\r\n\r\nEMPIRICAL SOFTWARE ENGINEERING<br>\r\nEmpirical software engineering is a sub-domain of software engineering focusing on experiments on software systems (software products, processes, and resources). It is interested in devising experiments on software, in collecting data from these experiments, and in devising laws and theories from this data. Proponents of experimental software engineering advocate that the nature of software is such that we can advance the knowledge on software through experiments only. The scientific method suggests a cycle of observations, laws, and theories to advance science. Empirical software engineering applies this method to software.<br><br>\r\n\r\nTRACEABILITY MANAGEMENT<br>\r\nTraceability has been defined as ''the ability to describe and follow the life of an artifact, in both a forwards and backwards direction''. Thus, traceability links help software engineers to understand the relationships and dependencies among various software artifacts (requirements, code, tests, models, etc.) developed during the software lifecycle. The two main research topics related to the traceability management are event-based systems for traceability management and information retrieval based methods and tools supporting the software engineer in the traceability link recovery. '),
('PLSFRN66', 'Francesco', 'Polese', '789654123', 'XXX', 'XXX', 'XXX', 'M', 'Italiana', 'f.polese@professori.unisa.it', 'distra', NULL, 'Unisa', NULL, NULL, 4, NULL, NULL),
('RSSMRA23', 'Mario', 'Rossi', NULL, NULL, NULL, NULL, 'M', 'Italiana', 'staff@staff.unisa.it', 'distra', NULL, 'Unisa', NULL, NULL, NULL, NULL, NULL),
('RSSPLA59', 'Paolo', 'Rossi', NULL, NULL, NULL, NULL, 'M', 'Italiana', 'moderna@azienda.unisa.it', 'distra', NULL, 'Unisa', NULL, NULL, 5, NULL, NULL),
('RTTFRN68M0A309S', 'Rita', 'Francese', '3487652362', 'Fisciano', 'via le lame', '65334', 'F', 'italiana', 'francese@gmail.com', 'DISTRA', 'www.francese.it', 'Università di Salerno', '022552585', 'professor', 1, '02225', NULL),
('SLNNLL88', 'Aniello', 'Saulino', '333987452', 'Nola', 'via xxx', '89523', 'M', 'Italiana', 'a.saulino@studenti.unisa.it', 'distra', NULL, 'Unisa', '00013', NULL, 2, '02225', NULL),
('VIAGVN66', 'Giovanni', 'Vaia', NULL, NULL, NULL, NULL, 'M', 'Italiana', 'g.vaia@professori.unisa.it', 'distra', NULL, 'Unisa', NULL, NULL, 4, NULL, NULL),
('VVNVNT89', 'Valentino', 'Vivone', '0333852369', 'Battipaglia', 'via xxx', '84091', 'M', 'Italiana', 'v.vivone@studenti.unisa.it', 'distra', NULL, 'Unisa', '00099', NULL, 1, '02225', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `phdclass`
--

CREATE TABLE IF NOT EXISTS `phdclass` (
`idClass` int(20) NOT NULL,
  `FK_PhdCycle` int(11) NOT NULL,
  `FK_PhdCurriculum` varchar(100) NOT NULL DEFAULT ''
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dump dei dati per la tabella `phdclass`
--

INSERT INTO `phdclass` (`idClass`, `FK_PhdCycle`, `FK_PhdCurriculum`) VALUES
(1, 15, 'Marketing e Comunicazione'),
(3, 16, 'Economia e Direzione delle Aziende Pubbliche');

-- --------------------------------------------------------

--
-- Struttura della tabella `phdcurriculum`
--

CREATE TABLE IF NOT EXISTS `phdcurriculum` (
  `name` varchar(100) NOT NULL,
  `description` text,
  `FK_Professor` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `phdcurriculum`
--

INSERT INTO `phdcurriculum` (`name`, `description`, `FK_Professor`) VALUES
('Economia e Direzione delle Aziende Pubbliche', 'Il curriculum “Economia e Direzione delle Aziende Pubbliche” ha l’obiettivo di trasferire conoscenze relative a principi, teorie e modelli di gestione, elaborati con riferimento all''''economia e alla gestione delle aziende pubbliche, fornire gli strumenti e le metodologie di analisi più innovative, nonché, sviluppare competenze professionali specializzate per il management di enti, istituzioni ed aziende afferenti al settore pubblico. Settore che negli ultimi decenni la Pubblica Amministrazione (P.A.) ha sperimentato un profondo processo di riforma che si è concretizzato, da un lato, in un riposizionamento dei confini tra stato, mercato e società civile e, dall''''altro, nell''''affermarsi di nuovi modelli di funzionamento che vanno sotto il nome di New Public Management. Recependo tali mutamenti la PA, pur conservando la propria missione, si è trovata nella necessità di innovare le proprie logiche di funzionamento ed i correlati strumenti di analisi e valutazione.\r\n\r\nCambiando le logiche organizzative ed i fabbisogni professionali, diviene strategica l''''acquisizione, da parte dei livelli dirigenziali, di competenze specifiche e di capacità di problem solving attraverso il costante confronto con casi ed esperienze concrete anche di origine internazionale.\r\n\r\nIl curriculum in Economia e Direzione delle Aziende Pubbliche rappresenta, quindi, un percorso elettivamente individuato dal mondo universitario per la preparazione di giovani studiosi e potenziali dirigenti attenti all''''esigenza di una moderna ed efficiente pubblica amministrazione. Esso ha lo scopo di presentare le più affermate e consolidate teorie e impostazioni dottrinarie elaborate in riferimento all''''economia e al governo dell''''azienda pubblica (anche nella sua espressione più allargata di pubblici servizi).\r\n\r\nIl curriculum, pertanto, offre una specifica formazione sui principali temi del public management e della governance pubblica:\r\n\r\nDottrine economico-aziendali;\r\nEconomia e contabilità dell''''azienda pubblica;\r\nGestione e management delle amministrazioni pubbliche;\r\nOrganizzazione e gestione delle risorse umane;\r\nGovernance degli enti locali;\r\nI controlli interni nella P.A.;\r\nStrategie e politiche innovative nella P.A.;\r\nValutazione e controllo dei dirigenti;\r\nMarketing territoriale;\r\nRelazioni con il cittadino e CRM.\r\nIn definitiva, il Dottore di Ricerca con curriculum in Economia e Direzione delle Aziende Pubbliche si può inquadrare in almeno 3 figure professionali (di dirigente pubblico) emerse nel corso di una recente indagine svolta dal Dipartimento della Funzione Pubblica ovvero la figura di ''in-and-outer'', l''''altrettanto recente ''high flier'' e il ''libero professionista riconvertito''.\r\n\r\nIl dottorato, inoltre, rappresenta una valida premessa per la formazione di figure professionali destinate a ricoprire il ruolo di city-manager (figura del direttore generale negli Enti locali voluta dalla legge 127/1997) e di consulente per gli Enti locali.'', NULL),\n(''Informatica, Sistemi Informativi e Tecnologie del Software'', ''\r\nIl curriculum, Informatica, Sistemi Informativi e Tecnologie del Software, ha l’obiettivo di formare figure professionali dotate di una preparazione scientifica teorica e pratica idonea ad operare con piena professionalità e competenza, sia in ambito accademico che industriale, nelle varie fasi che caratterizzano la ricerca, lo sviluppo, il controllo di qualità e la produzione nel settore dei sistemi informativi e delle tecnologie del software. In particolare, il corso di dottorato di ricerca mira alla formazione di ricercatori con elevata conoscenza degli aspetti teorici, metodologici, sperimentali e applicativi di settore quali quelli dei sistemi informativi e delle basi di dati, dell’ingegneria del software, dell''''ingegneria della conoscenza, del web engineering e dell''''interazione uomo-macchina, con una elevata capacità di trasferire i risultati della ricerca in ambito industriale e di applicarli ai settori dell’economia e del management aziendale, del marketing e della comunicazione.\r\n\r\n  Le tematiche scientifiche del curriculum includono:\r\n\r\nProject management\r\nSoftware quality assurance\r\nMetodi per la stima dei costi\r\nSistemi a supporto delle decisioni e business intelligence\r\nData warehousing\r\nBig data\r\nOpen data\r\nDocument and content management\r\nWorkflow and process management\r\nModellazione e analisi delle prestazioni dei processi\r\nBusiness process reengineering\r\nWeb engineering\r\nSistemi cloud-based\r\nIngegneria dei requisiti e progettazione di sistemi software\r\nManutenzione ed evoluzione di sistemi software\r\nAnalisi e testing del software\r\nIngegneria del software empirica\r\nMetodi e strumenti per il lavoro collaborativo\r\nLinguaggi visuali e interazione uomo-macchina\r\nComputer graphics e realtà virtuale\r\nInterfacce web avanzate, immersive, 3D e aptiche\r\nDomotica e sistemi di videosorveglianza intelligenti.\r\nInterfacce per sistemi domotici\r\nRiconoscimento di immagini e sistemi biometrici\r\nComputational intelligence\r\nInformation retrieval\r\nTecniche di clustering e data mining, machine learning e classificazione\r\nSistemi informativi geografici e territoriali\r\nE-learning e tecnologie per la didattica a distanza\r\nModelli matematici e ottimizzazione\r\nModellazione ed analisi di prestazioni e affidabilità dei sistemi\r\nIl Dottore di Ricerca con curriculum in Informatica, Sistemi Informativi e Tecnologie del Software potrà avere diversi sbocchi professionali, che non si fermano a quello di ricercatore accademico o nei centri di ricerca di organizzazioni ed aziende. Infatti, grazie anche alle competenze acquisite con la formazione di tipo manageriale acquisita durante il corso di dottorato, il Dottore di Ricerca potrà ricoprire ruoli di consulente ed esperto di innovazione e trasferimento tecnologico per le aziende del comparto ICT, nonché ruoli di direzione di progetti di ricerca e funzioni direzionali in aziende del comparto ICT.', 'RTTFRN68M0A309S'),
('Marketing e Comunicazione', 'Il curriculum “Marketing e Comunicazione” mira a trasferire conoscenze e approcci metodologici relativi a principi, teorie e modelli di ideazione e governo delle strategie e delle politiche di marketing e della comunicazione. Il principale obiettivo è formare figure professionali dotate di una solida preparazione scientifica, teorica e pratica idonea ad operare nelle università, nei centri di ricerca, negli enti pubblici e nelle imprese private e pubbliche svolgendo attività di ricerca qualificata nelle seguenti aree disciplinari e professionali: marketing strategico e operativo, analisi di mercato e del consumo, comunicazione istituzionale e d''''impresa.\n\nIl programma formativo si riferisce all’ambito disciplinare dell''''Economia d''''Impresa e prevede che i frequentanti, al termine del percorso di apprendimento, acquisiscano un uso agevole delle più avanzate ed affidabili metodologie di ricerca scientifica in campo economico-sociale, oltre ad un’approfondita conoscenza delle teorie e delle più recenti ed innovative impostazioni riguardo al marketing ed alla comunicazione.\n\nParticolare attenzione di studio viene rivolta all’area dei social media e della rete per supportare le scelte strategiche di marketing e di comunicazione delle organizzazioni imprenditoriali e delle istituzioni.\n\nLe tematiche scientifiche del curriculum includono:\n\nMarketing Strategy;\nCompetition dynamics;\nChannel management;\nSalesforce management;\nMarketing e performance aziendali;\nBehavioral Constructs;\nDecision making;\nConsumer judgment;\nCorporate Communication;\nCorporate identity;\nOrganizational culture;\nReputation management;\nIntegrated Marketing Communication;\nDigital communication;\nValue creation and innovation;\nOn..', 'MRZTCC62M0A309C');

-- --------------------------------------------------------

--
-- Struttura della tabella `phdcycle`
--

CREATE TABLE IF NOT EXISTS `phdcycle` (
  `idPhdCycle` int(11) NOT NULL,
  `description` text,
  `year` year(4) NOT NULL,
  `FK_Professor` varchar(16) DEFAULT NULL
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
  `FK_PhdCurriculum` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `professor_phdcycle`
--

CREATE TABLE IF NOT EXISTS `professor_phdcycle` (
  `FK_Professor` varchar(16) NOT NULL,
  `FK_PhdCycle` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `professor_phdcycle`
--

INSERT INTO `professor_phdcycle` (`FK_Professor`, `FK_PhdCycle`) VALUES
('FLMFRR65M0A509S', 15),
('ANDDLC65M0N409C', 16),
('FLMFRR65M0A509S', 16);

-- --------------------------------------------------------

--
-- Struttura della tabella `professor_student`
--

CREATE TABLE IF NOT EXISTS `professor_student` (
  `FK_Professor` varchar(16) NOT NULL,
  `FK_Student` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `prof_module_class`
--

CREATE TABLE IF NOT EXISTS `prof_module_class` (
`ID` int(11) NOT NULL,
  `class_title` varchar(50) NOT NULL,
  `teaching_matricula` varchar(10) NOT NULL,
  `module_title` varchar(50) NOT NULL,
  `email_account` varchar(100) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dump dei dati per la tabella `prof_module_class`
--

INSERT INTO `prof_module_class` (`ID`, `class_title`, `teaching_matricula`, `module_title`, `email_account`) VALUES
(1, 'Unica', '0222500002', 'IT Service Management', 'adl@unisa.it'),
(2, 'Unica', '0222500002', 'IT Project Management', 'ferrucci@gmail.com'),
(3, 'Unica', '0222500002', 'prova', 'null'),
(4, 'Unica', '0222500008', 'Sistemi Informativi e Territoriali', 'tucci@gmail.com'),
(5, 'Unica', '0222500005', 'Sviluppo applicazioni mobili', 'ferrucci@gmail.com');

-- --------------------------------------------------------

--
-- Struttura della tabella `publication`
--

CREATE TABLE IF NOT EXISTS `publication` (
`idPublication` int(20) NOT NULL,
  `title` varchar(100) NOT NULL DEFAULT '',
  `authors` varchar(150) NOT NULL DEFAULT '',
  `abstract` text NOT NULL,
  `file` longblob,
  `year` varchar(4) NOT NULL DEFAULT '',
  `type` varchar(45) NOT NULL,
  `publicationIssue` varchar(100) NOT NULL DEFAULT '',
  `numberPages` int(11) NOT NULL,
  `FK_Student` varchar(16) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Dump dei dati per la tabella `publication`
--

INSERT INTO `publication` (`idPublication`, `title`, `authors`, `abstract`, `file`, `year`, `type`, `publicationIssue`, `numberPages`, `FK_Student`) VALUES
(1, 'Mining Version Histories for Detecting Code Smells.', 'F. Palomba, G. Bavota, M. Di Penta, R.Oliveto, D. Poshyvanyk, A. De Lucia. ', ' ', NULL, '2015', 'Journal', 'Transactions on Software Engineering (TSE)', 34, 'PLMFBA89M03F839I'),
(2, 'When and Why Your Code Starts to Smell Bad', 'M. Tufano, F. Palomba, G. Bavota, R. Oliveto, M. Di Penta, A. De Lucia, D. Poshyvanyk. ', ' ', NULL, '2015', 'Conference', 'International Conference on Software Engineering (ICSE 2015)', 11, 'PLMFBA89M03F839I'),
(4, 'Supporting Extract Class Refactoring in Eclipse: The ARIES Project.', 'G. Bavota, A. De Lucia, A. Marcus, R. Oliveto, and F. Palomba.', ' ', NULL, '2012', 'Conference', 'International Conference on Software Engineering (ICSE 2012)', 4, 'PLMFBA89M03F839I'),
(5, 'Anti-Pattern Detection: Methods, Challanges, and Open Issues.', 'F. Palomba, G. Bavota, R. Oliveto, and A. De Lucia. ', ' ', NULL, '2015', 'Book Chapter', 'Advances in Computers', 28, 'PLMFBA89M03F839I'),
(6, 'ARIES: An Eclipse plug-in to Support Extract Class Refactoring.', 'G. Bavota, A. De Lucia, A. Marcus, R. Oliveto, F. Palomba, and M. Tufano. ', '   ', NULL, '2013', 'Workshop', 'Italian Workshop on Eclipse Technologies', 2, 'PLMFBA89M03F839I'),
(8, 'Do They Really Smell Bad? A Study on Developers Perception of Bad Code Smells.', 'F. Palomba, G. Bavota, R. Oliveto, and A. De Lucia. ', ' ', NULL, '2014', 'Conference', 'International Conference on Software Maintenance and Evolution (ICSME 2014)', 10, 'PLMFBA89M03F839I');

-- --------------------------------------------------------

--
-- Struttura della tabella `questionnaire`
--

CREATE TABLE IF NOT EXISTS `questionnaire` (
  `student_ssn` varchar(16) NOT NULL,
  `company_name` varchar(45) NOT NULL,
  `typology_organization` varchar(45) DEFAULT NULL,
  `first_question` varchar(45) DEFAULT NULL,
  `second_question` varchar(45) DEFAULT NULL,
  `third_question` varchar(45) DEFAULT NULL,
  `fourth_question` varchar(45) DEFAULT NULL,
  `fifth_question` varchar(45) DEFAULT NULL,
  `sixth_question` varchar(45) DEFAULT NULL,
  `seventh_question` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `rejected_training_message`
--

CREATE TABLE IF NOT EXISTS `rejected_training_message` (
`id_rejected_training_message` int(11) NOT NULL,
  `description` longtext,
  `fk_person` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `student_information`
--

CREATE TABLE IF NOT EXISTS `student_information` (
  `curriculum_vitae_path` varchar(200) DEFAULT NULL,
  `accademic_transcript_path` varchar(200) DEFAULT NULL,
  `SSN` varchar(16) NOT NULL,
  `fk_student_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `student_phdclass`
--

CREATE TABLE IF NOT EXISTS `student_phdclass` (
  `FK_Student` varchar(16) CHARACTER SET utf8 NOT NULL,
  `FK_PhdClass` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `student_phdclass`
--

INSERT INTO `student_phdclass` (`FK_Student`, `FK_PhdClass`) VALUES
('PLMFBA89M03F839I', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `student_status`
--

CREATE TABLE IF NOT EXISTS `student_status` (
`id_student_status` int(11) NOT NULL,
  `description` varchar(45) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dump dei dati per la tabella `student_status`
--

INSERT INTO `student_status` (`id_student_status`, `description`) VALUES
(1, 'Rifiutato'),
(2, 'Accettato'),
(3, 'In attesa'),
(4, 'Non inviato');

-- --------------------------------------------------------

--
-- Struttura della tabella `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
`ID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dump dei dati per la tabella `tag`
--

INSERT INTO `tag` (`ID`, `Name`) VALUES
(1, 'Open Data'),
(2, 'IS'),
(3, 'Service'),
(4, 'Big Data'),
(5, 'Control System'),
(6, 'Management'),
(7, 'tag tesi');

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
  `esse3_content` longtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `teaching`
--

INSERT INTO `teaching` (`matricula`, `title`, `abbreviation`, `link`, `year`, `semester`, `active`, `esse3_content`) VALUES
('0222500002', 'IT Project and Service Management', 'ITPSM', 'https://esse3web.unisa.it/unisa/Guide/PaginaADErogata.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?ad_er_id=2014*N0*N0*S1*39751*509790&ANNO_ACCADEMICO=2014&mostra_percorsi=N', 1, 1, 1, '<div class="portlet-section-body" id="column1of2"> \n <div class="portlet-section-body" id="floating"> \n  <div id="header" class="portlet-section-header  header-hx header-h1"> \n   <h1>0222500002 - IT PROJECT AND SERVICE MANAGEMENT</h1> \n   <p>Anno Accademico&nbsp;2014/2015</p> \n  </div> \n  <div id="infobox" class="record"> \n   <dl class="record-riga"> \n    <dt>\n     Docente\n    </dt> \n    <dd> \n     <a id="box-item-anchorinfobox-1-1" href="http://www.unisa.it/rubrica/curriculum/persona/001775" title="Visualizza i dettagli del Docente">FILOMENA&nbsp;FERRUCCI</a> \n     <br> \n     <a id="box-item-anchorinfobox-1-2" href="http://www.unisa.it/rubrica/curriculum/persona/003241" title="Visualizza i dettagli del Docente">ANDREA&nbsp;DE LUCIA</a> \n     <br> \n    </dd> \n    <dt>\n     Periodo\n    </dt> \n    <dd> \n     <description>\n      PRIMO SEMESTRE\n     </description>&nbsp;\n     <br> \n    </dd> \n    <dt>\n     Modalità d&apos;Erogazione\n    </dt> \n    <dd> \n     <description></description>&nbsp;\n     <br> \n    </dd> \n   </dl> \n   <br> \n  </div> \n  <br class="pulisci"> \n  <div id="header-1" class="portlet-section-header  header-hx header-h3"> \n   <h3>Contenuti</h3> \n   <p>IL CORSO SI ARTICOLA IN DUE MODULI FORMATIVI, DENOMINATI IT PROJECT MANAGEMENT (6 CFU, INF/01) E IT SERVICE MANAGEMENT (6 CFU, ING-INF/05).<br> <br>IL PRIMO MODULO SI FOCALIZZA SULLE TECNICHE E LE METODOLOGIE DI GESTIONE DEI PROGETTI IT E TRATTA ASPETTI RELATIVI ALLE SEGUENTI AREE DI CONOSCENZA:<br>GESTIONE DELLO "SCOPE" E GESTIONE DEI REQUISITI, <br>GESTIONE DEI TEMPI, DEFINIZIONE DELLA WORK BREAKDOWN STRUCTURE DI PROGETTO E STIMA DI DETTAGLIO DELLE ATTIVITÀ, <br>GESTIONE DEI COSTI, <br>GESTIONE DELLA QUALITÀ E CMMI,<br>GESTIONE DELLE RISORSE UMANE, <br>GESTIONE DELLA COMUNICAZIONE, <br>GESTIONE DEI RISCHI, <br>GESTIONE DEGLI "STAKEHOLDER".<br> <br>IL SECONDO MODULO PREVEDE DA UNA PARTE CONTENUTI GENERALI DELL&apos;IT SERVICE MANAGEMENT, CON INDICAZIONE DEGLI STANDARD E DEI FRAMEWORK DI RIFERIMENTO E DALL&apos;ALTRA ASPETTI PIÙ SPECIFICI CHE RIGUARDANO LA GESTIONE DI SERVIZI DI TIPO SOFTWARE E DEI SISTEMI INFORMATIVI AZIENDALI. <br>IN PARTICOLARE, I CONTENTUTI DEL CORSO INCLUDONO:<br>CONCETTI DI SERVIZI IT E DI GESTIONE DEI SERVIZI IT;<br>ALLINEAMENTO DELL&apos;IT AL BUSINESS;<br>IT SERVICE MANAGEMENT E STANDARD ISO-IEC 20000;<br>IT SERVICE MANAGEMENT E FRAMEWORK ITIL (INFORMATION TECHNOLOGY INFRASTRUCTURE LIBRARY);<br>SERVICE STRATEGY, DESIGN, TRANSITION E OPERATION;<br>MIGLIORAMENTO CONTINUO DEI SERVIZI;<br>PROCESSI DEL CICLO DI VITA DEL SOFTWARE E STANDARD ISO-IEC 12207;<br>GESTIONE DEI SISTEMI INFORMATIVI LEGACY;<br>CENNI SU: BUSINESS PROCESS REENGINERING; REINGEGNERIZZAZIONE ED INTEGRAZIONE DI SISTEMI SOFTWARE; WORKFLOW MANAGEMENT AND AUTOMATION.<br> </p> \n  </div> \n  <div id="header-2" class="portlet-section-header  header-hx header-h3"> \n   <h3>Obiettivi</h3> \n   <p>CONOSCENZA E CAPACITÀ DI COMPRENSIONE:<br>GLI STUDENTI ACQUISIRANNO CONOSCENZE SULLA GESTIONE DEI PROGETTI E DEI SERVIZI DI INFORMATION TECHNOLOGY, CHE TIPICAMENTE NON VENGONO AFFRONTATI NEI CORSI DI PRIMO LIVELLO, IN PARTICOLARE DI AREA INGEGNERIA DEL SOFTWARE.<br>IL CORSO INTRODUCE GLI STUDENTI ALLA GESTIONE DI PROGETTI IT CARATTERIZZATI DA OBIETTIVI PRECISI, DURATA FINITA E RISORSE LIMITATE. SI ARTICOLA NELLE FASI DI AVVIO, PIANIFICAZIONE, ESECUZIONE, MONITORAGGIO E CONTROLLO E CHIUSURA. INOLTRE, IL CORSO INTRODUCE GLI STUDENTI AI PROBLEMI DELLA GESTIONE DEI SERVIZI IT SU LARGA SCALA, FILOSOFICAMENTE CONCENTRATA SUI PROCESSI, SULLA QUALITÀ DEL SERVIZIO PERCEPITA DAL CLIENTE E SUL CONTRIBUTO DELL&apos;IT AL BUSINESS. PARTICOLARE ATTENZIONE SARÀ DEDICATA A SERVIZI IT DI TIPO SOFTWARE E IN PARTICOLARE AI PROCESSI DEL CICLO DI VITA DEL SOFTWARE E ALLA GESTIONE DELL&apos;EVOLUZIONE DEI SISTEMI INFORMATIVI AZIENDALI E AL LORO ALLINEAMENTO CON IL BUSINESS. <br>GLI STUDENTI ACQUISIRANNO LA CAPACITÀ DI COMPRENDERE LO STATO DELL&apos;ARTE, LA LETTERATURA SCIENTIFICA E GLI STANDARD INTERNAZIONALI NEL SETTORE DELL&apos;IT PROJECT E SERVICE MANAGEMENT.<br> <br>CAPACITÀ DI APPLICARE CONOSCENZA E COMPRENSIONE:<br>GLI STUDENTI SARANNO IN GRADO DI USARE UN APPROCCIO SISTEMATICO E ORGANIZZATO ALLA GESTIONE DEI PROGETTI IT, DI SVILUPPARE PROPOSTE E PIANI DI PROGETTO, PIANI DI QUALITÀ E RAPPORTI SULLO STATO DI AVANZAMENTO, NONCHÉ DI POST-MORTEM. SARANNO INOLTRE IN GRADO DI GESTIRE SERVIZI IT, MANTENENDONE L&apos;ALLINEAMENTO AL BUSINESS AZIENDALE; DI PIANIFICARE E GESTIRE LE ATTIVITÀ DEI PROCESSI LEGATI ALLA GESTIONE DEI SERVIZI IT E A PRODURRE DOCUMENTI IN ACCORDO A STANDARD E PIANI DI QUALITÀ.<br> <br>ABILITÀ COMUNICATIVE:<br>GLI STUDENTI ACQUISIRANNO SOFT SKILL ED IN PARTICOLARE LA PADRONANZA DEI MECCANISMI CHE CARATTERIZZANO LA COMUNICAZIONE NELL&apos;AMBITO DI PROGETTI IT, COME LA CONDIVISIONE DI MODELLI E DOCUMENTI CON ALTRI MEMBRI DI UN TEAM DI PROGETTO E STAKEHOLDER AZIENDALE, PRESENTAZIONE DI PROPOSTE DI PROGETTO NELL&apos;AMBITO DELLA GESTIONE DEI PROGETTI E DEI SERVIZI IT, CONDUZIONE DI MEETING E DI REVIEW DI PROGETTO, RICHIESTE DI MODIFICA, RISOLUZIONE DI PROBLEMI E REPORT DI ATTIVITÀ DI GESTIONE.<br> <br>AUTONOMIA DI GIUDIZIO:<br>IL PROJECT MANAGER DEVE ESSERE IN GRADO DI INDIVIDUARE SOLUZIONI CHE BILANCINO AL MEGLIO OBIETTIVI DIVERSI E SPESSO CONTRASTANTI E DEVE OPPORTUNAMENTE MOTIVARLE. IL CORSO MIRA ANCHE AD APPROFONDIRE LE IMPLICAZIONI SOCIALI, SCIENTIFICHE ED ETICHE DELLA PROFESSIONE. GLI STUDENTI DISPORRANNO DI UNA VISIONE DI INSIEME DEI PROCESSI LEGATI ALLA GESTIONE DEI SERVIZI IT, NONCHÉ DEI PROCESSI DEL CICLO DI VITA DEL SOFTWARE E SARANNO IN GRADO DI VALUTARE TRA DIVERSE ALTERNATIVE ALLA SOLUZIONE DI PROBLEMI LEGATI ALLA GESTIONE DELL&apos;EVOLUZIONE DEI SISTEMI INFORMATIVI AZIENDALI E AL LORO ALLINEAMENTO AL BUSINESS. GLI STUDENTI SARANNO IN GRADO DI CONFRONTARSI CON ALTRI STUDENTI E STAKEHOLDER NELL&apos;AMBITO DI GRUPPI DI LAVORO E DI VALUTARE LE IMPLICAZIONI DERIVANTI DA TRADE-OFF TRA COSTI, TEMPI E REQUISITI DI QUALITÀ.<br> <br>CAPACITÀ DI APPRENDIMENTO<br>IL CORSO MIRA A SVILUPPARE LA CAPACITÀ DI APPRENDIMENTO DEGLI STUDENTI CHE CONSENTA LORO DI AGGIORNARE CONTINUAMENTE LE PROPRIE CONOSCENZE E COMPETENZE IN FUNZIONE DELL&rsquo;INNOVAZIONE TECNOLOGICA E METODOLOGICA DEL SETTORE, PROPONENDO ARGOMENTI DI APPROFONDIMENTO DA STUDIARE AUTONOMAMENTE. GLI STUDENTI ACQUISIRANNO LA CAPACITÀ DI RECUPERARE, CONSULTARE E COMPRENDERE LA LETTERATURA TECNICA E SCIENTIFICA DEL SETTORE DELL&apos;IT PROJECT E SERVICE MANAGEMENT E DELL&apos;INGEGNERIA DEL SOFTWARE (ANCHE IN LINGUA INGLESE) ATTRAVERSO L&apos;INDIVIDUAZIONE DEGLI STANDARD INTERNAZIONALI DEL SETTORE E DEGLI STRUMENTI METODOLOGICI E TECNOLOGICI PIÙ ADATTI ALLA SOLUZIONE DI NUOVI PROBLEMI.</p> \n  </div> \n  <div id="header-3" class="portlet-section-header  header-hx header-h3"> \n   <h3>Prerequisiti</h3> \n   <p>GLI STUDENTI DEVONO AVERE CONOSCENZA PREGRESSA DI CONCETTI DI BASE DELL&apos;INFORMATICA, TIPICAMENTE ACQUISITI IN CORSI DI LAUREA TRIENNALE, IN PARTICOLARE DI AREA INGEGNERIA DEL SOFTWARE.</p> \n  </div> \n  <div id="header-4" class="portlet-section-header  header-hx header-h3"> \n   <h3>Metodi Didattici</h3> \n   <p>IL CORSO PREVEDE 72 ORE DI LEZIONI FRONTALI A CARATTERE PREVALENTEMENTE TEORICO-METODOLOGICO PER IL TRASFERIMENTO DELLE CONOSCENZE RELATIVE AI CONTENUTI DEL CORSO, CON MOMENTI DI ESERCITAZIONE E DI APPROFONDIMENTO DI ASPETTI PRATICI E DI INTERAZIONE TRA GLI STUDENTI E IL DOCENTE.<br>AGLI STUDENTI VERRÀ ASSEGNATO UN PROGETTO DI GRUPPO, TRAMITE IL QUALE POTRANNO APPROFONDIRE GLI ASPETTI PRATICI TRATTATI DURANTE IL CORSO E SIMULARE DINAMICHE DI GRUPPO CHE AVVENGONO NEL MONDO DEL LAVORO.<br> </p> \n  </div> \n  <div id="header-5" class="portlet-section-header  header-hx header-h3"> \n   <h3>Verifica dell&apos;apprendimento</h3> \n   <p>- REALIZZAZIONE E DISCUSSIONE DI UN PROGETTO DI GRUPPO PER ACCERTARE LE CAPACITÀ PRATICHE ACQUISITE. I CRITERI DI VALUTAZIONE RIGUARDERANNO LA COMPLETEZZA, LA CORRETTEZZA E LA SINTESI DELLA DOCUMENTAZIONE DI PROGETTO.<br>- PROVA ORALE PER ACCERTARE LE CONOSCENZE ACQUISITE. I CRITERI DI VALUTAZIONE RIGUARDERANNO LA COMPLETEZZA E LA CORRETTEZZA DELL&apos;APPRENDIMENTO E LA CHIAREZZA ESPOSITIVA.<br> </p> \n  </div> \n  <div id="header-6" class="portlet-section-header  header-hx header-h3"> \n   <h3>Altre Informazioni</h3> \n   <p>PROF. ANDREA DE LUCIA ADELUCIA@UNISA.IT<br>PROF.SSA FILOMENA FERRUCCI FFERRUCCI@UNISA.IT</p> \n  </div> \n  <div id="header-7" class="portlet-section-header  header-hx header-h3"> \n   <h3>Testi</h3> \n   <p>- I SOMMERVILLE, "SOFTWARE ENGINEERING", ADDISON WESLEY<br>- R. ADDY, EFFECTIVE IT SERVICE MANAGEMENT: TO ITL AND BEYOND, SPRINGER, 2007<br>-IT SERVICE MANAGEMENT FORUM, FOUNDATIONS OF IT SERVICE MANAGEMENT BASED ON ITIL V3, VAN HAREN PUBLISHING<br>- KATHY SCHWALBE, "INFORMATION TECHNOLOGY PROJECT MANAGEMENT", INTERNATIONAL EDITION 7E, CENGAGE LEARNING, ©2014 <br>- PMBOK® GUIDE -5° EDITION<br>- ALTRO MATERIALE FORNITO DAI DOCENTI<br> </p> \n  </div> \n  <div id="header-8" class="portlet-section-header  header-hx header-h3"> \n   <h3>Unità Didattica: IT SERVICE MANAGEMENT - Contenuti</h3> \n   <p>IL CORSO PREVEDE DA UNA PARTE CONTENUTI GENERALI DELL&apos;&apos;IT SERVICE MANAGEMENT, CON INDICAZIONE DEGLI STANDARD E DEI FRAMEWORK DI RIFERIMENTO E DALL&apos;&apos;ALTRA ASPETTI PIÙ SPECIFICI CHE RIGUARDANO LA GESTIONE DI SERVIZI DI TIPO SOFTWARE E DEI SISTEMI INFORMATIVI AZIENDALI. <br>IN PARTICOLARE, I CONTENTUTI DEL CORSO INCLUDONO:<br>CONCETTI DI SERVIZI IT E DI GESTIONE DEI SERVIZI IT;<br>ALLINEAMENTO DELL&apos;&apos;IT AL BUSINESS;<br>IT SERVICE MANAGEMENT E STANDARD ISO-IEC 20000;<br>IT SERVICE MANAGEMENT E FRAMEWORK ITIL (INFORMATION TECHNOLOGY INFRASTRUCTURE LIBRARY);<br>SERVICE STRATEGY, DESIGN, TRANSITION E OPERATION;<br>MIGLIORAMENTO CONTINUO DEI SERVIZI;<br>PROCESSI DEL CICLO DI VITA DEL SOFTWARE E STANDARD ISO-IEC/IEEE 12207;<br>GESTIONE DEI SISTEMI INFORMATIVI LEGACY;<br>CENNI SU: BUSINESS PROCESS REENGINERING; REINGEGNERIZZAZIONE ED INTEGRAZIONE DI SISTEMI SOFTWARE; WORKFLOW MANAGEMENT AND AUTOMATION.<br> </p> \n  </div> \n  <div id="header-9" class="portlet-section-header  header-hx header-h3"> \n   <h3>Unità Didattica: IT SERVICE MANAGEMENT - Obiettivi</h3> \n   <p>CONOSCENZA E CAPACITÀ DI COMPRENSIONE:<br>GLI STUDENTI ACQUISIRANNO CONOSCENZE SULLA GESTIONE DEI SERVIZI DI INFORMATION TECHNOLOGY, CHE TIPICAMENTE NON VENGONO AFFRONTATI NEI CORSI DI PRIMO LIVELLO, IN PARTICOLARE DI AREA INGEGNERIA DEL SOFTWARE.<br>IL CORSO INTRODUCE GLI STUDENTI AI PROBLEMI DELLA GESTIONE DEI SERVIZI IT SU LARGA SCALA, FILOSOFICAMENTE CONCENTRATA SUI PROCESSI, SULLA QUALITÀ DEL SERVIZIO PERCEPITA DAL CLIENTE E SUL CONTRIBUTO DELL&apos;&apos;&apos;&apos;IT AL BUSINESS. PARTICOLARE ATTENZIONE SARÀ DEDICATA A SERVIZI IT DI TIPO SOFTWARE E IN PARTICOLARE AI PROCESSI DEL CICLO DI VITA DEL SOFTWARE E ALLA GESTIONE DELL&apos;&apos;&apos;&apos;EVOLUZIONE DEI SISTEMI INFORMATIVI AZIENDALI E AL LORO ALLINEAMENTO CON IL BUSINESS. <br>GLI STUDENTI ACQUISIRANNO LA CAPACITÀ DI COMPRENDERE LO STATO DELL&apos;&apos;&apos;&apos;ARTE, LA LETTERATURA SCIENTIFICA E GLI STANDARD INTERNAZIONALI NEL SETTORE DELL&apos;&apos;&apos;&apos;IT SERVICE MANAGEMENT.<br> <br>CAPACITÀ DI APPLICARE CONOSCENZA E COMPRENSIONE:<br>GLI STUDENTI SARANNO IN GRADO DI GESTIRE SERVIZI IT, MANTENENDONE L&apos;&apos;&apos;&apos;ALLINEAMENTO AL BUSINESS AZIENDALE; DI PIANIFICARE E GESTIRE LE ATTIVITÀ DEI PROCESSI LEGATI ALLA GESTIONE DEI SERVIZI IT E A PRODURRE DOCUMENTI IN ACCORDO A STANDARD E PIANI DI QUALITÀ.<br> <br>ABILITÀ COMUNICATIVE:<br>GLI STUDENTI ACQUISIRANNO SOFT SKILL ED IN PARTICOLARE LA PADRONANZA DEI MECCANISMI CHE CARATTERIZZANO LA COMUNICAZIONE NELL&apos;&apos;&apos;&apos;AMBITO DELLA GESTIONE DEI SERVIZI IT, COME LA CONDIVISIONE DI DOCUMENTI CON ALTRI STAKEHOLDER AZIENDALI, PRODUZIONE DI PIANI E REPORT RELATIVI ALLE ATTIVITÀ DI GESTIONE DI SERVIZI IT, CONDUZIONE DI MEETING E DI REVIEW DI PROGETTO.<br> <br>AUTONOMIA DI GIUDIZIO:<br>IL CORSO MIRA AD APPROFONDIRE LE IMPLICAZIONI SOCIALI, SCIENTIFICHE ED ETICHE DELLA PROFESSIONE. GLI STUDENTI DISPORRANNO DI UNA VISIONE DI INSIEME DEI PROCESSI LEGATI ALLA GESTIONE DEI SERVIZI IT, NONCHÉ DEI PROCESSI DEL CICLO DI VITA DEL SOFTWARE E SARANNO IN GRADO DI VALUTARE TRA DIVERSE ALTERNATIVE ALLA SOLUZIONE DI PROBLEMI LEGATI ALLA GESTIONE DELL&apos;&apos;&apos;&apos;EVOLUZIONE DEI SISTEMI INFORMATIVI AZIENDALI E AL LORO ALLINEAMENTO AL BUSINESS. GLI STUDENTI SARANNO IN GRADO DI CONFRONTARSI CON ALTRI STUDENTI E STAKEHOLDER NELL&apos;&apos;&apos;&apos;AMBITO DI GRUPPI DI LAVORO E DI VALUTARE LE IMPLICAZIONI DERIVANTI DA TRADE-OFF TRA COSTI, TEMPI E REQUISITI DI QUALITÀ.<br> <br>CAPACITÀ DI APPRENDIMENTO<br>IL CORSO MIRA A SVILUPPARE LA CAPACITÀ DI APPRENDIMENTO DEGLI STUDENTI CHE CONSENTA LORO DI AGGIORNARE CONTINUAMENTE LE PROPRIE CONOSCENZE E COMPETENZE IN FUNZIONE DELL&rsquo;INNOVAZIONE TECNOLOGICA E METODOLOGICA DEL SETTORE, PROPONENDO ARGOMENTI DI APPROFONDIMENTO DA STUDIARE AUTONOMAMENTE. GLI STUDENTI ACQUISIRANNO LA CAPACITÀ DI RECUPERARE, CONSULTARE E COMPRENDERE LA LETTERATURA TECNICA E SCIENTIFICA DEL SETTORE DELL&apos;&apos;&apos;&apos;IT SERVICE MANAGEMENT E DELL&apos;&apos;&apos;&apos;INGEGNERIA DEL SOFTWARE (ANCHE IN LINGUA INGLESE) ATTRAVERSO L&apos;&apos;&apos;&apos;INDIVIDUAZIONE DEGLI STANDARD INTERNAZIONALI DEL SETTORE E DEGLI STRUMENTI METODOLOGICI E TECNOLOGICI PIÙ ADATTI ALLA SOLUZIONE DI NUOVI PROBLEMI.<br> </p> \n  </div> \n  <div id="header-10" class="portlet-section-header  header-hx header-h3"> \n   <h3>Unità Didattica: IT SERVICE MANAGEMENT - Prerequisiti</h3> \n   <p>GLI STUDENTI DEVONO AVERE CONOSCENZA PREGRESSA DI CONCETTI DI BASE DELL&apos;&apos;INFORMATICA, TIPICAMENTE ACQUISITI IN CORSI DI LAUREA TRIENNALE, IN PARTICOLARE DI AREA INGEGNERIA DEL SOFTWARE.</p> \n  </div> \n  <div id="header-11" class="portlet-section-header  header-hx header-h3"> \n   <h3>Unità Didattica: IT SERVICE MANAGEMENT - Testi</h3> \n   <p>- R. ADDY, EFFECTIVE IT SERVICE MANAGEMENT: TO ITL AND BEYOND, SPRINGER<br>- IT SERVICE MANAGEMENT FORUM, FOUNDATIONS OF IT SERVICE MANAGEMENT BASED ON ITIL V3, VAN HAREN PUBLISHING<br>- I SOMMERVILLE, "SOFTWARE ENGINEERING", ADDISON WESLEY<br>- ALTRO MATERIALE FORNITO DAL DOCENTE</p> \n  </div> \n </div> \n</div>'),
('0222500005', 'Sviluppo Applicazioni Mobili', 'SAM', 'https://esse3web.unisa.it/unisa/Guide/PaginaADErogata.do;jsessionid=311F2AABC0EACC900E225837A7ED6918.jvm6?ad_er_id=2014*N0*N0*S2*39754*509793&ANNO_ACCADEMICO=2014&mostra_percorsi=N', 1, 2, 1, '<div class="portlet-section-body" id="column1of2"> \n <div class="portlet-section-body" id="floating"> \n  <div id="header" class="portlet-section-header  header-hx header-h1"> \n   <h1>0222500005 - INTERAZIONE UOMO MACCHINA E SVILUPPO DI APPLICAZIONI MOBILI</h1> \n   <p>Anno Accademico&nbsp;2014/2015</p> \n  </div> \n  <div id="infobox" class="record"> \n   <dl class="record-riga"> \n    <dt>\n     Docente\n    </dt> \n    <dd> \n     <a id="box-item-anchorinfobox-1-1" href="http://www.unisa.it/rubrica/curriculum/persona/003730" title="Visualizza i dettagli del Docente">GIULIANA&nbsp;VITIELLO</a> \n     <br> \n    </dd> \n    <dt>\n     Periodo\n    </dt> \n    <dd> \n     <description>\n      SECONDO SEMESTRE\n     </description>&nbsp;\n     <br> \n    </dd> \n    <dt>\n     Modalità d&apos;Erogazione\n    </dt> \n    <dd> \n     <description></description>&nbsp;\n     <br> \n    </dd> \n   </dl> \n   <br> \n  </div> \n  <br class="pulisci"> \n  <div id="header-1" class="portlet-section-header  header-hx header-h3"> \n   <h3>Contenuti</h3> \n   <p>MODULO DI INTERAZIONE UOMO MACCHINA E USABILITY ENGINEERING (6 CFU)<br>IL CORSO PRESENTA CONCETTI E TECNICHE BEN ASSESTATE DI INTERAZIONE UOMO-MACCHINA, ILLUSTRANDONE L&rsquo;UTILIZZO NELLE VARIE FASI DI SVILUPPO USER CENTERED. ESSO PREVEDE UNA PRIMA PARTE FOCALIZZATA SULLE ATTIVITÀ DI ANALISI E RACCOLTA DEI REQUISITI UTENTE, UNA SECONDA PARTE DEDICATA ALLE METODOLOGIE DI DESIGN DI SISTEMI INTERATTIVI E UNA TERZA DEDICATA ALLA VALUTAZIONE DELL&rsquo;USABILITÀ.<br>• VANTAGGI DI UNO SVILUPPO ‘USER-CENTERED&rsquo; DI UN SISTEMA SOFTWARE E FONDAMENTI DI USABILITY ENGINEERING. <br>• GLI STUDI ETNOGRAFICI E LE INDAGINI CONTESTUALI COME STRUMENTI PER LA COMPRENSIONE DEL CONTESTO D&rsquo;USO E PER LA RACCOLTA DEI REQUISITI UTENTE. <br>• L&rsquo;APPROCCIO SCENARIO-BASED AL DESIGN ITERATIVO DI SISTEMI USER CENTERED. <br>• LE TECNOLOGIE USER CENTERED DI SUPPORTO AL PRINCIPIO DEL DESIGN UNIVERSALE. <br>• SVILUPPO USER-CENTERED DI SISTEMI MULTIMODALI E MULTI-UTENTE NEI DOMINI DELL&rsquo;UBIQUITOUS COMPUTING E DELLA COLLABORAZIONE TRAMITE ARTEFATTI. PROBLEMATICHE DI USABILITY ENGINEERING SPECIFICHE DI QUEI SISTEMI. <br>• METODI EMPIRICI DI VALUTAZIONE DELL&rsquo;USABILITÀ.<br>• L&rsquo;INGEGNERIA DELL&rsquo;USABILITÀ NELLA PRATICA: IL RUOLO DEGLI SPECIALISTI DELL&rsquo;USABILITÀ NEI TEAM DI SVILUPPO; GLI STANDARD DELLE INTERFACCE UTENTE; QUESTIONI ETICHE CHE INFLUISCONO SULL&rsquo;USABILITÀ.<br>MODULO DI PROGETTAZIONE E SVILUPPO DI APPLICAZIONI MOBILI: (6 CFU)<br>ARCHITETTURA DEL SISTEMA OPERATIVO ANDROID, CICLO DI UN&rsquo;ATTIVITÀ IN ANDROID, WIDGETS, LISTE, CONTAINER, ACCESSO AD INTERNET DA ANDROID, ROTAZIONE DEL DEVICE, THREAD, INTENTI, SERVIZI, GESTIRE IL MULTITOUCH, LA LOCALIZZAZIONE, LE MAPPE E LE TELEFONATE E I SENSORI DEL DISPOSITIVO, SQLITE. LE METODOLOGIE DI SVILUPPO AGILI. PUBBLICAZIONE SUL MARKET E MONETIZZAZIONE.<br> </p> \n  </div> \n  <div id="header-2" class="portlet-section-header  header-hx header-h3"> \n   <h3>Obiettivi</h3> \n   <p>OBIETTIVO DEL CORSO È FAR ACQUISIRE AGLI STUDENTI CONCETTI AVANZATI CHE INTERESSANO LE DIVERSE FASI DEL PROCESSO INGEGNERIA DELL&rsquo;USABILITÀ, DALLA SPECIFICA DEI REQUISITI UTENTE, ALLA PROGETTAZIONE, ALLO SVILUPPO E AL TESTING. <br>- INTERAZIONE UOMO MACCHINA E USABILITY ENGINEERING (6 CFU)<br>- PROGETTAZIONE E SVILUPPO DI APPLICAZIONI MOBILI: (6 CFU)<br> <br>CONOSCENZA E CAPACITÀ DI COMPRENSIONE:<br>CONOSCERE L&rsquo;APPROCCIO ‘USER CENTERED&rsquo;, ALLA PROGETTAZIONE FINALIZZATA ALL&rsquo;USABILITÀ E ALL&rsquo;INTERACTION DESIGN. GLI STUDENTI APPRENDERANNO L&rsquo;USO DI METODOLOGIE E TECNICHE DI INDAGINE ETNOGRAFICA VOLTE ALL&rsquo;INDIVIDUAZIONE DEI REQUISITI UTENTE, CONOSCERANNO I VANTAGGI DI UN APPROCCIO ITERATIVO AL DESIGN INCENTRATO SULL&rsquo;UTENTE E IMPARERANNO TECNICHE DI INGEGNERIA DELL&rsquo;USABILITÀ, UTILI SIA IN FASE DI DESIGN CHE IN FASE DI VALUTAZIONE E DI TESTING. <br>CONOSCERE LE PRINCIPALI FUNZIONALITÀ DEL SISTEMA OPERATIVO ANDROID<br>CONOSCERE LE MODALITA&apos; DI SVILUPPO AGILE<br>CAPACITÀ DI APPLICARE CONOSCENZA E COMPRENSIONE:<br>DA UN PUNTO DI VISTA PRATICO, ALLA FINE DEL CORSO, GLI STUDENTI SARANNO IN GRADO DI SVILUPPARE SISTEMI SOFTWARE USER CENTERED, ANCHE ALL&rsquo;INTERNO DI AMBIENTI COLLABORATIVI E DI SCEGLIERE TRA LE METODOLOGIE E I PARADIGMI DI INTERAZIONE APPRESI, QUELLI PIÙ ADATTI AL CONTESTO D&rsquo;USO CUI UN DETERMINATO SISTEMA INFORMATIVO È DESTINATO, SIA QUESTO DI TIPO SINGOLO- O MULTI-UTENTE. SARANNO INOLTRE IN GRADO DI APPLICARE TECNICHE DI EMPIRICAL USABILITY ENGINEERING PER LA VALUTAZIONE SPERIMENTALE DEI PROTOTIPI SVILUPPATI.<br>CAPACITÀ DI INVESTIGARE IL MERCATO MOBILE, DI PROGETTARE E IMPLEMENTARE UN&apos;APPLICAZIONE SU DISPOSITIVI MOBILI UTILIZZANDO UN PROCESSO ITERATIVO DI PROTOTIPAZIONE RAPIDA, IN LINEA CON I PRINCIPI DI USABILITY ENGINEERING.APPRESI NEL PRIMO MODULO.<br>AUTONOMIA DI GIUDIZIO:<br>GLI STUDENTI SARANNO GUIDATI AD APPRENDERE IN MANIERA CRITICA E RESPONSABILE TUTTO CIÒ CHE VIENE SPIEGATO LORO IN CLASSE E AD ARRICCHIRE LE PROPRIE CAPACITÀ DI GIUDIZIO ATTRAVERSO LO STUDIO DEL MATERIALE DIDATTICO INDICATO DAL DOCENTE. L&rsquo;AUTONOMIA DI GIUDIZIO SI CONCRETIZZERÀ INOLTRE ATTRAVERSO IL LAVORO DI GRUPPO E IL CONFRONTO CON GLI ALTRI MEMBRI DEL TEAM DI PROGETTO.<br>ABILITÀ COMUNICATIVE:<br>LA SPERIMENTAZIONE IN TEAM DEL PROCESSO DI PROGETTAZIONE USER CENTERED SU UN CASO DI STUDIO CONCRETO, MIGLIORERÀ LE CAPACITÀ COLLABORATIVE DI DESIGN E PROBLEM SOLVING DEL SINGOLO STUDENTE. LE ABILITÀ COMUNICATIVE SI CONCRETIZZERANNO INFATTI CON L&rsquo;ACQUISIZIONE E LA PADRONANZA DI MECCANISMI CHE CARATTERIZZANO LA COMUNICAZIONE NELL&rsquo;AMBITO DI PROGETTI DI GRUPPO, COME LA CONDIVISIONE DI MODELLI E DOCUMENTI SOFTWARE, LE ATTIVITÀ DI BRAINSTORMING CONDOTTE PER DELINEARE LE IDEE DI PROGETTO, LA CONDUZIONE DI MEETING PERIODICI PER REVISIONI DI PROGETTO E L&rsquo;ORGANIZZAZIONE DI STRATEGIE IBRIDE DI TESTING E DI VALUTAZIONE DELL&rsquo;USABILITÀ, CHE PREVEDONO UN&rsquo;OPPORTUNA RIPARTIZIONE DEI RUOLI E L&rsquo;UTILIZZO DI ADEGUATI STRUMENTI DI COMUNICAZIONE SINCRONA E ASINCRONA. IL CARATTERE USER CENTERED DEL PROCESSO DI SVILUPPO FAVORIRÀ INOLTRE L&rsquo;ACQUISIZIONE DI ABILITÀ COMUNICATIVE ANCHE NEI CONFRONTI DI TUTTI GLI STAKEHOLDER INDIVIDUATI DURANTE L&rsquo;INDAGINE ETNOGRAFICA E IN PARTICOLARE DEGLI UTENTI FINALI DEL SISTEMA, IL CUI FEEDBACK SARÀ INDISPENSABILE DURANTE LE DIVERSE FASI DI DESIGN, SVILUPPO E TESTING, SEGUENDO I PRINCIPI DEL ‘PARTICIPATORY DESIGN&rsquo;. <br>CAPACITÀ DI APPRENDIMENTO:<br>IL CARATTERE ITERATIVO DELLE METODOLOGIE DI ANALISI, DESIGN E VALUTAZIONE CHE GLI STUDENTI SPERIMENTERANNO DURANTE IL CORSO, BEN SI PRESTA AD ACCRESCERE LE LORO CAPACITÀ DI APPRENDIMENTO, SOPRATTUTTO SULLA BASE DELLE REVISIONI, DELLE MODIFICHE O DEGLI AFFINAMENTI, CHE SI RENDERANNO NECESSARI DURANTE IL PROCESSO DI PROTOTIPAZIONE EVOLUTIVA.<br>CAPACITÀ DI COMPRENDERE LA LETTERATURA TECNICA E SCIENTIFICA CIRCA LE APPLICAZIONI ANDROID. </p> \n  </div> \n  <div id="header-3" class="portlet-section-header  header-hx header-h3"> \n   <h3>Prerequisiti</h3> \n   <p>NESSUNA PARTICOLARE CONOSCENZA TECNICA E&apos; NECESSARIA. SONO DA RITENERE VANTAGGIOSE CONOSCENZE RELATIVE ALLA PROGRAMMAZIONE ORIENTATA AGLI OGGETTI, ALLE TECNOLOGIE DI SVILUPPO PER IL WEB, NONCHÉ AI CONCETTI DI BASE DI INGEGNERIA DEL SOFTWARE.</p> \n  </div> \n  <div id="header-4" class="portlet-section-header  header-hx header-h3"> \n   <h3>Metodi Didattici</h3> \n   <p>LE LEZIONI SI SVOLGERANNO CON L&rsquo;AUSILIO DI PRESENTAZIONI MULTIMEDIALI. TUTTI GLI ARGOMENTI VERRANNO ACCOMPAGNATI DA ESERCITAZIONI PRATICHE SU CASI DI STUDIO SIGNIFICATIVI.<br>AGLI STUDENTI VERRÀ ASSEGNATO UN PROGETTO DI SVILUPPO DI UN SISTEMA INFORMATIVO SU RETE CON DISPOSITIVI MOBILI DI GRUPPO (2-4 PERSONE) TRAMITE IL QUALE LO STUDENTE POTRÀ APPROFONDIRE GLI ASPETTI PRATICI E SIMULARE DINAMICHE DI GRUPPO CHE AVVENGONO NEL MONDO DEL LAVORO.</p> \n  </div> \n  <div id="header-5" class="portlet-section-header  header-hx header-h3"> \n   <h3>Verifica dell&apos;apprendimento</h3> \n   <p>1. REALIZZAZIONE E DISCUSSIONE DI UN PROGETTO DI GRUPPO PER ACCERTARE LE CAPACITÀ PRATICHE ACQUISITE. I CRITERI DI VALUTAZIONE RIGUARDERANNO LA COMPLETEZZA, LA CORRETTEZZA E LA SINTESI DELLA DOCUMENTAZIONE DI PROGETTO<br>2. PROVA SCRITTA PER ACCERTARE LE CONOSCENZE TEORICHE ACQUISITE.</p> \n  </div> \n  <div id="header-6" class="portlet-section-header  header-hx header-h3"> \n   <h3>Altre Informazioni</h3> \n   <p>LA FREQUENZA DEL CORSO È FORTEMENTE CONSIGLIATA. GLI STUDENTI DEVONO ESSERE PREPARATI A TRASCORRERE UNA CONGRUA QUANTITÀ DI TEMPO NELLO STUDIO AL DI FUORI DELLE LEZIONI. UNA PREPARAZIONE SODDISFACENTE RICHIEDE IN MEDIA DUE ORE DI STUDIO PER CIASCUNA ORA TRASCORSA IN AULA</p> \n  </div> \n  <div id="header-7" class="portlet-section-header  header-hx header-h3"> \n   <h3>Testi</h3> \n   <p>MARY BETH ROSSON &amp; JOHN M. CARROLL. "USABILITY ENGINEERING- SCENARIO-BASED DEVELOPMENT OF HUMAN-COMPUTER INTERACTION", MORGAN KAUFMANN PUBLISHERS-ISBN: 978-1-55860-712-5.<br> </p> \n  </div> \n </div> \n</div>'),
('0222500008', 'Sistemi Informativi e Territoriali', 'SIT', 'https://esse3web.unisa.it/unisa/Guide/PaginaADContest.do;jsessionid=FFDBC42126C697CC71DD219C77BE06F8.jvm6?ad_cont_id=500408*509790*2014*2014*9999&ANNO_ACCADEMICO=2014&ANNO_COORTE=2014', 2, 1, 1, 'Link di esse3 errato');

-- --------------------------------------------------------

--
-- Struttura della tabella `thesis`
--

CREATE TABLE IF NOT EXISTS `thesis` (
`ID` int(11) NOT NULL,
  `Start_Date` date DEFAULT NULL,
  `End_Date` date DEFAULT NULL,
  `Expected_End_Date` date DEFAULT NULL,
  `Title` varchar(180) DEFAULT NULL,
  `Abstract` longtext,
  `Description` varchar(255) DEFAULT NULL,
  `ID_Student` varchar(16) NOT NULL,
  `Thesis_Status` enum('0','1','2','3','4','5') DEFAULT '0'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

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
  `ID_Professor` varchar(16) NOT NULL
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
`ID` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

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
`id_training_offer` int(11) NOT NULL,
  `description` longtext,
  `fk_person` varchar(16) NOT NULL,
  `fk_department` varchar(50) DEFAULT NULL,
  `fk_organization` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `training_request`
--

CREATE TABLE IF NOT EXISTS `training_request` (
`id_training_request` int(11) NOT NULL,
  `description` longtext,
  `title` varchar(45) DEFAULT NULL,
  `fk_training_status` int(11) NOT NULL,
  `fk_person` varchar(16) NOT NULL,
  `fk_organization` varchar(16) DEFAULT NULL,
  `student_information_SSN` varchar(16) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `training_status`
--

CREATE TABLE IF NOT EXISTS `training_status` (
`id_training_status` int(11) NOT NULL,
  `description` varchar(45) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dump dei dati per la tabella `training_status`
--

INSERT INTO `training_status` (`id_training_status`, `description`) VALUES
(1, 'Iniziato'),
(2, 'Pre-completamento'),
(3, 'Completo');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
 ADD PRIMARY KEY (`email`);

--
-- Indexes for table `attachment`
--
ALTER TABLE `attachment`
 ADD PRIMARY KEY (`ID`), ADD KEY `ID_Thesis` (`ID_Thesis`);

--
-- Indexes for table `chronology`
--
ALTER TABLE `chronology`
 ADD PRIMARY KEY (`ID`), ADD KEY `fk_person1` (`ID_Student`) USING BTREE, ADD KEY `fk_person2` (`ID_Professor`) USING BTREE;

--
-- Indexes for table `class`
--
ALTER TABLE `class`
 ADD PRIMARY KEY (`title`,`teaching_matricula`), ADD KEY `title` (`title`), ADD KEY `teaching_matricula` (`teaching_matricula`);

--
-- Indexes for table `class_event`
--
ALTER TABLE `class_event`
 ADD PRIMARY KEY (`FK_Class`,`FK_Event`), ADD KEY `FK_Event` (`FK_Event`), ADD KEY `FK_Class` (`FK_Class`);

--
-- Indexes for table `class_notice`
--
ALTER TABLE `class_notice`
 ADD PRIMARY KEY (`FK_Class`,`FK_Event`), ADD KEY `FK_Event` (`FK_Event`), ADD KEY `FK_Class` (`FK_Class`);

--
-- Indexes for table `collaboration`
--
ALTER TABLE `collaboration`
 ADD PRIMARY KEY (`idCollaboration`), ADD KEY `FK_Student` (`FK_Student`);

--
-- Indexes for table `curriculum`
--
ALTER TABLE `curriculum`
 ADD PRIMARY KEY (`matricula`), ADD KEY `fk_curriculum_degree1_idx` (`degree_matricula`);

--
-- Indexes for table `curriculum_teaching`
--
ALTER TABLE `curriculum_teaching`
 ADD KEY `curriculum_matricula` (`curriculum_matricula`), ADD KEY `teaching_matricula` (`teaching_matricula`);

--
-- Indexes for table `cycle`
--
ALTER TABLE `cycle`
 ADD PRIMARY KEY (`cycle_number`);

--
-- Indexes for table `degree`
--
ALTER TABLE `degree`
 ADD PRIMARY KEY (`matricula`), ADD KEY `fk_degree_department1_idx` (`department_abbreviation`), ADD KEY `fk_degree_cycle1_idx` (`cycle_number`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
 ADD PRIMARY KEY (`abbreviation`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
 ADD PRIMARY KEY (`idEvent`);

--
-- Indexes for table `event_professor`
--
ALTER TABLE `event_professor`
 ADD PRIMARY KEY (`FK_Event`,`FK_Professor`), ADD KEY `FK_Professor` (`FK_Professor`), ADD KEY `FK_Event` (`FK_Event`);

--
-- Indexes for table `lesson`
--
ALTER TABLE `lesson`
 ADD PRIMARY KEY (`idLesson`), ADD KEY `FK_Event` (`FK_Event`);

--
-- Indexes for table `lesson_student`
--
ALTER TABLE `lesson_student`
 ADD PRIMARY KEY (`FK_Lesson`,`FK_Student`), ADD KEY `FK_Student` (`FK_Student`), ADD KEY `FK_Lesson` (`FK_Lesson`);

--
-- Indexes for table `mission`
--
ALTER TABLE `mission`
 ADD PRIMARY KEY (`idMission`), ADD KEY `FK_Student` (`FK_Student`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
 ADD PRIMARY KEY (`title`,`teaching_matricula`), ADD KEY `fk_module_teaching1_idx` (`teaching_matricula`);

--
-- Indexes for table `notice`
--
ALTER TABLE `notice`
 ADD PRIMARY KEY (`idNotice`);

--
-- Indexes for table `organization`
--
ALTER TABLE `organization`
 ADD PRIMARY KEY (`vat_number`), ADD KEY `fk_acc` (`fk_account`), ADD KEY `fk_tutor` (`fk_external_tutor`), ADD KEY `fk_prof` (`fk_professor`);

--
-- Indexes for table `pending_acceptance`
--
ALTER TABLE `pending_acceptance`
 ADD PRIMARY KEY (`id_pending_acceptance`), ADD KEY `fk_StudentAttendence_Student1_idx` (`fk_person`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
 ADD PRIMARY KEY (`SSN`), ADD KEY `fk_Person_Account_idx` (`Account_email`), ADD KEY `fk_Person_Department1_idx` (`Department_abbreviation`), ADD KEY `fk_cycle` (`cycle`), ADD KEY `degree_matricula` (`degree_matricula`);

--
-- Indexes for table `phdclass`
--
ALTER TABLE `phdclass`
 ADD PRIMARY KEY (`idClass`), ADD UNIQUE KEY `FK_PhdCycle` (`FK_PhdCycle`,`FK_PhdCurriculum`), ADD KEY `FK_PhdCurriculum` (`FK_PhdCurriculum`), ADD KEY `FK_cycle` (`FK_PhdCycle`);

--
-- Indexes for table `phdcurriculum`
--
ALTER TABLE `phdcurriculum`
 ADD PRIMARY KEY (`name`), ADD KEY `FK_Professor` (`FK_Professor`);

--
-- Indexes for table `phdcycle`
--
ALTER TABLE `phdcycle`
 ADD PRIMARY KEY (`idPhdCycle`), ADD KEY `FK_Professor` (`FK_Professor`);

--
-- Indexes for table `professor_phdcurriculum`
--
ALTER TABLE `professor_phdcurriculum`
 ADD PRIMARY KEY (`FK_Professor`,`FK_PhdCurriculum`), ADD KEY `FK_Professor` (`FK_Professor`), ADD KEY `FK_PhdCurriculum` (`FK_PhdCurriculum`);

--
-- Indexes for table `professor_phdcycle`
--
ALTER TABLE `professor_phdcycle`
 ADD PRIMARY KEY (`FK_Professor`,`FK_PhdCycle`), ADD KEY `FK_PhdCycle` (`FK_PhdCycle`), ADD KEY `FK_Professor` (`FK_Professor`);

--
-- Indexes for table `professor_student`
--
ALTER TABLE `professor_student`
 ADD PRIMARY KEY (`FK_Professor`,`FK_Student`), ADD KEY `FK_Professor` (`FK_Professor`), ADD KEY `FK_Student` (`FK_Student`);

--
-- Indexes for table `prof_module_class`
--
ALTER TABLE `prof_module_class`
 ADD PRIMARY KEY (`ID`), ADD KEY `class_title` (`class_title`), ADD KEY `module_teaching_matricula` (`teaching_matricula`), ADD KEY `module_title` (`module_title`), ADD KEY `email_account` (`email_account`);

--
-- Indexes for table `publication`
--
ALTER TABLE `publication`
 ADD PRIMARY KEY (`idPublication`), ADD KEY `FK_Student` (`FK_Student`), ADD KEY `FK_Student_2` (`FK_Student`), ADD KEY `FK_Student_3` (`FK_Student`);

--
-- Indexes for table `questionnaire`
--
ALTER TABLE `questionnaire`
 ADD PRIMARY KEY (`student_ssn`);

--
-- Indexes for table `rejected_training_message`
--
ALTER TABLE `rejected_training_message`
 ADD PRIMARY KEY (`id_rejected_training_message`), ADD KEY `fk_RejectedTrainingMessage_Student1_idx` (`fk_person`);

--
-- Indexes for table `student_information`
--
ALTER TABLE `student_information`
 ADD PRIMARY KEY (`SSN`), ADD UNIQUE KEY `fk_status` (`fk_student_status`);

--
-- Indexes for table `student_phdclass`
--
ALTER TABLE `student_phdclass`
 ADD PRIMARY KEY (`FK_Student`,`FK_PhdClass`), ADD UNIQUE KEY `FK_Student` (`FK_Student`), ADD KEY `FK_PhdClass` (`FK_PhdClass`);

--
-- Indexes for table `student_status`
--
ALTER TABLE `student_status`
 ADD PRIMARY KEY (`id_student_status`);

--
-- Indexes for table `tag`
--
ALTER TABLE `tag`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `teaching`
--
ALTER TABLE `teaching`
 ADD PRIMARY KEY (`matricula`);

--
-- Indexes for table `thesis`
--
ALTER TABLE `thesis`
 ADD PRIMARY KEY (`ID`), ADD UNIQUE KEY `fk_person` (`ID_Student`);

--
-- Indexes for table `thesis_supervisor`
--
ALTER TABLE `thesis_supervisor`
 ADD KEY `ID_Thesis` (`ID_Thesis`), ADD KEY `ID_Professor` (`ID_Professor`);

--
-- Indexes for table `thesis_tag`
--
ALTER TABLE `thesis_tag`
 ADD PRIMARY KEY (`ID`), ADD KEY `fk_thesis` (`ID_thesis`) USING BTREE, ADD KEY `fk_tag` (`ID_tag`) USING BTREE;

--
-- Indexes for table `training_offer`
--
ALTER TABLE `training_offer`
 ADD PRIMARY KEY (`id_training_offer`), ADD KEY `fk_OfferTraining_Professor1_idx` (`fk_person`), ADD KEY `fk_OfferTraining_Department1_idx` (`fk_department`), ADD KEY `fk_OfferTraining_Organization1` (`fk_organization`);

--
-- Indexes for table `training_request`
--
ALTER TABLE `training_request`
 ADD PRIMARY KEY (`id_training_request`), ADD KEY `fk_ClaimTraining_ClaimStatus1_idx` (`fk_training_status`), ADD KEY `fk_ClaimTraining_Professor1_idx` (`fk_person`), ADD KEY `training_request_ibfk_2` (`fk_organization`), ADD KEY `fk_training_request_student_information1` (`student_information_SSN`);

--
-- Indexes for table `training_status`
--
ALTER TABLE `training_status`
 ADD PRIMARY KEY (`id_training_status`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attachment`
--
ALTER TABLE `attachment`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `chronology`
--
ALTER TABLE `chronology`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `collaboration`
--
ALTER TABLE `collaboration`
MODIFY `idCollaboration` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `mission`
--
ALTER TABLE `mission`
MODIFY `idMission` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `pending_acceptance`
--
ALTER TABLE `pending_acceptance`
MODIFY `id_pending_acceptance` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `phdclass`
--
ALTER TABLE `phdclass`
MODIFY `idClass` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `prof_module_class`
--
ALTER TABLE `prof_module_class`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `publication`
--
ALTER TABLE `publication`
MODIFY `idPublication` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `rejected_training_message`
--
ALTER TABLE `rejected_training_message`
MODIFY `id_rejected_training_message` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `student_status`
--
ALTER TABLE `student_status`
MODIFY `id_student_status` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `tag`
--
ALTER TABLE `tag`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `thesis`
--
ALTER TABLE `thesis`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `thesis_tag`
--
ALTER TABLE `thesis_tag`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `training_offer`
--
ALTER TABLE `training_offer`
MODIFY `id_training_offer` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `training_request`
--
ALTER TABLE `training_request`
MODIFY `id_training_request` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `training_status`
--
ALTER TABLE `training_status`
MODIFY `id_training_status` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
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
ADD CONSTRAINT `fk_degree_cycle1` FOREIGN KEY (`cycle_number`) REFERENCES `cycle` (`cycle_number`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_degree_department1` FOREIGN KEY (`department_abbreviation`) REFERENCES `department` (`abbreviation`) ON DELETE CASCADE ON UPDATE CASCADE;

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
ADD CONSTRAINT `fk_module_teaching1` FOREIGN KEY (`teaching_matricula`) REFERENCES `teaching` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE;

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
ADD CONSTRAINT `fk_Person_Account` FOREIGN KEY (`Account_email`) REFERENCES `account` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_Person_Department1` FOREIGN KEY (`Department_abbreviation`) REFERENCES `department` (`abbreviation`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `person_ibfk_1` FOREIGN KEY (`cycle`) REFERENCES `cycle` (`cycle_number`) ON DELETE CASCADE ON UPDATE CASCADE,
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
