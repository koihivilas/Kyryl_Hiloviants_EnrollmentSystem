-- -----------------------------------------------------
-- Table `roles`
-- -----------------------------------------------------
INSERT INTO `roles` (`code`, `name`)
VALUES ("ADMIN", "Administrator");

INSERT INTO `roles` (`code`, `name`)
VALUES ("STUDENT", "Student (applicant)");

-- -----------------------------------------------------
-- Table `specializations`
-- -----------------------------------------------------
INSERT INTO `specializations` (`code`, `name`, `description`, `limit`)
VALUES (0, "None", "For administrators", 0);
INSERT INTO `specializations` (`code`, `name`, `description`, `limit`)
VALUES (022, "Design", "Design involves the creation of visual or 
functional solutions to problems, often with a focus on aesthetics, 
usability, and user experience.", 3);
INSERT INTO `specializations` (`code`, `name`, `description`, `limit`)
VALUES (051, "Economics", "Economics studies how individuals, businesses, 
and governments make decisions about resource allocation, distribution, 
and consumption of goods and services.", 2);
INSERT INTO `specializations` (`code`, `name`, `description`, `limit`)
VALUES (122, "Computer science", "Computer science involves the study of 
algorithms, programming languages, software engineering, and computer 
hardware to develop solutions to computational problems.", 1);
INSERT INTO `specializations` (`code`, `name`, `description`, `limit`)
VALUES (171, "Electronics", "Electronics involves the study of electronic 
components, circuits, and systems, as well as their design, analysis, and 
application in a range of devices and technologies.", 4);
INSERT INTO `specializations` (`code`, `name`, `description`, `limit`)
VALUES (222, "Medicine", "Medicine is the study of the human body and its 
diseases, as well as the prevention, diagnosis, and treatment of illnesses 
through medical interventions and therapies.", 3);

-- -----------------------------------------------------
-- Table `user_statuses`
-- -----------------------------------------------------
INSERT INTO `user_statuses` (`code`, `name`)
VALUES ("ADMIN", "For administrators");
INSERT INTO `user_statuses` (`code`, `name`)
VALUES ("REG", "Registered for testing");
INSERT INTO `user_statuses` (`code`, `name`)
VALUES ("WAIT", "Waiting for evaluation");
INSERT INTO `user_statuses` (`code`, `name`)
VALUES ("RATED", "Rated by administrator");
INSERT INTO `user_statuses` (`code`, `name`)
VALUES ("ENR", "Enrolled at the university");
INSERT INTO `user_statuses` (`code`, `name`)
VALUES ("REJ", "Rejected from the university");

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
INSERT INTO `users` (`username`, `password`, `first_name`, `last_name`, 
`email`, `phone`, `role_id`, `user_status_id`, `specialization_id`)
VALUES ('admin', 'admin', 'Admin', 'Adminenko', 'admin@gmail.com', 
'380501111111', 1, 1, 1);
INSERT INTO `users` (`username`, `password`, `first_name`, `last_name`, 
`email`, `phone`, `role_id`, `user_status_id`)
VALUES ('stud', 'stud', 'Student', 'Studentenko', 'student@gmail.com', 
'380502222222', 2, 2);
INSERT INTO `users` (`username`, `password`, `first_name`, `last_name`, 
`email`, `phone`, `role_id`, `user_status_id`)
VALUES ('walter.hadley', 'qqqtbzr223', 'Deondre', 'Keebler', 
'beverly46@yahoo.com', '380933282473', 2, 2);
INSERT INTO `users` (`username`, `password`, `first_name`, `last_name`, 
`email`, `phone`, `role_id`, `user_status_id`)
VALUES ('ischaden', 'vcqbmop199', 'Leanne', 'Crooks', 'adrienne97@gmail.com', 
'380503282751', 2, 2);
INSERT INTO `users` (`username`, `password`, `first_name`, `last_name`, 
`email`, `phone`, `role_id`, `user_status_id`)
VALUES ('gbahringer', 'fxmqwzn349', 'Stephany', 'Hudson', 
'brayan52@hotmail.com', '380963432789', 2, 2);

-- -----------------------------------------------------
-- Table `subjects`
-- -----------------------------------------------------
INSERT INTO `subjects` (`name`, `description`, `closed`)
VALUES ("Ukrainian", "Study of the rules and structures of 
Ukrainian language and analysis of Ukrainian literature", 0);
INSERT INTO `subjects` (`name`, `description`, `closed`)
VALUES ("Math", "Study of numbers, quantities, and shapes.", 0);
INSERT INTO `subjects` (`name`, `description`, `closed`)
VALUES ("English", "Study of English language and literature", 0);
INSERT INTO `subjects` (`name`, `description`, `closed`)
VALUES ("History", "Study of past events and societies", 0);
INSERT INTO `subjects` (`name`, `description`, `closed`)
VALUES ("Physics", "Study of matter and energy in motion", 0);
INSERT INTO `subjects` (`name`, `description`, `closed`)
VALUES ("Biology", "Study of living organisms and their interactions", 0);
INSERT INTO `subjects` (`name`, `description`, `closed`)
VALUES ("Geography", "Study of the Earth's surface and its features", 0);
INSERT INTO `subjects` (`name`, `description`, `closed`)
VALUES ("Art", "Study of visual arts and their historical contexts", 0);

-- -----------------------------------------------------
-- Table `specialization_subject`
-- -----------------------------------------------------
INSERT INTO `specialization_subject` (`specialization_id`, `subject_id`, `subject_weight`)
VALUES (2, 1, 0.25), (2, 3, 0.25), (2, 8, 0.5);
INSERT INTO `specialization_subject` (`specialization_id`, `subject_id`, `subject_weight`)
VALUES (3, 1, 0.2), (3, 2, 0.5), (3, 7, 0.3);
INSERT INTO `specialization_subject` (`specialization_id`, `subject_id`, `subject_weight`)
VALUES (4, 1, 0.2), (4, 2, 0.5), (4, 3, 0.3);
INSERT INTO `specialization_subject` (`specialization_id`, `subject_id`, `subject_weight`)
VALUES (5, 1, 0.2), (5, 2, 0.4), (5, 5, 0.4);
INSERT INTO `specialization_subject` (`specialization_id`, `subject_id`, `subject_weight`)
VALUES (6, 1, 0.25), (6, 4, 0.25), (6, 6, 0.5);
