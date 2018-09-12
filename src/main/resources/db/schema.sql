DROP TABLE `interviewer`;
DROP TABLE `interview`;

CREATE TABLE `interviewer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `interviewer_uc` (`first_name`,`last_name`)
);

CREATE TABLE `interview` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `interview_date` datetime DEFAULT NULL,
  `interviewer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_interviewer` (`interviewer_id`)
);