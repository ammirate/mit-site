CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `numberoftrainingfororg` AS
    select 
        `training_request`.`fk_organization` AS `fk_organization`,
        count(0) AS `counter`
    from
        `training_request`
    group by `training_request`.`fk_organization`




CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `studentendedtraining` AS
    select 
        `training_request`.`student_information_SSN` AS `student_information_SSN`,
        `training_request`.`fk_person` AS `fk_person`
    from
        `training_request`
    where
        (`training_request`.`fk_training_status` = 3)


CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `studentinactivetraining` AS
    select 
        `student_information`.`SSN` AS `SSN`
    from
        `student_information`
    where
        ((`student_information`.`fk_student_status` = 4)
            or (`student_information`.`fk_student_status` = 'null'))



CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `studentstartedtraining` AS
    select 
        `student_information`.`SSN` AS `SSN`
    from
        (`student_information`
        join `training_status`)
    where
        (`training_status`.`id_training_status` = 1)



CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `studentwaitingquestionnaire` AS
    select 
        `training_request`.`student_information_SSN` AS `student_information_SSN`,
        `training_request`.`fk_person` AS `fk_person`
    from
        `training_request`
    where
        (`training_request`.`fk_training_status` = 2)