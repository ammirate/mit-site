README FILE [Gruppo Tirocinio]

Nella cartella in cui ci si trova, sono presenti 4 files .txt. Più precisamente, essi sono:

- StoredProcedures.txt
- Functions.txt
- View.sql
- ViewProcedures.txt

Per poter rendere il sottosistema Tirocinio funzionante, è necessario caricare i files nel database aprendo un file alla volta ed inserndo singolarmente le funzioni. 

Esempio Stored Procedures e Functions:
una stored procedure e una function sono formate nel seguente modo:

-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertQuestionnaire`( in studentSSN VARCHAR(16), in companyName VARCHAR(45), in typologyOrganization VARCHAR(45), in firs VARCHAR(5), in secon VARCHAR(5), in third VARCHAR(5), in fourth VARCHAR(5), in fifth VARCHAR(5), in sixth VARCHAR(5), in seventh VARCHAR(5) )
BEGIN
INSERT INTO questionnaire VALUES (studentSSN,companyName,typologyOrganization,firs,secon,third,fourth,fifth,sixth,seventh);
END

Questa va caricata direttamente nel Database come se fosse una semplice query.

Stessa cosa per le functions:

-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `checkCVExistence`(studentInformation varchar(16)) RETURNS int(11)
BEGIN
DECLARE foundSet INT default false;
declare cvPath varchar (200);
declare selectCV cursor for select curriculum_vitae_path from student_information where curriculum_vitae_path = ''  and SSN = studentInformation;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET foundSet = true;  
open selectCV;
fetch selectCV into cvPath;
RETURN foundSet;
END


