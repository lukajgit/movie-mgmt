[
"CREATE DATABASE IF NOT EXISTS `movie_mgmt`;"

"USE `movie_mgmt`;"

"DROP TABLE IF EXISTS `role`;"
"DROP TABLE IF EXISTS `movie`;"
"DROP TABLE IF EXISTS `actor`;"
"DROP TABLE IF EXISTS `country`;"

"CREATE TABLE `country` (
  `id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;"

"LOCK TABLES `country` WRITE;"

"INSERT INTO `country` VALUES (1,'Serbia'),(2,'USA'),(3,'Germany'),(4,'United Kingdom'),(5, 'Denmark');"

"UNLOCK TABLES;"

"CREATE TABLE `movie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `year` int NOT NULL,
  `id_country` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_movie_country_idx` (`id_country`),
  CONSTRAINT `fk_movie_country` FOREIGN KEY (`id_country`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;"

"LOCK TABLES `movie` WRITE;"

"INSERT INTO `movie` VALUES (1,'Ko to tamo peva','Its April 5, 1941, somewhere in Serbia. A group of people go on a bus to Belgrade, on a journey that will change their lives forever.',1980,1),(2,'Inception','A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.',2010,2),(3,'Another round','Four high-school teachers consume alcohol on a daily basis to see how it affects their social and professional lives.',2020,5),(4,'Last night in Soho','An aspiring fashion designer is mysteriously able to enter the 1960s where she encounters a dazzling wannabe singer. But the glamour is not all it appears to be and the dreams of the past start to crack and splinter into something darker.',2021,4),(5,'Inglourious Basterds','In Nazi-occupied France during World War II, a plan to assassinate Nazi leaders by a group of Jewish U.S. soldiers coincides with a theatre owners vengeful plans for the same.',2009,2);"

"UNLOCK TABLES;"

"CREATE TABLE `actor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `birthday` date NOT NULL,
  `id_country` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_actor_country_idx` (`id_country`),
  CONSTRAINT `fk_actor_country` FOREIGN KEY (`id_country`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;"

"LOCK TABLES `actor` WRITE;"

"INSERT INTO actor VALUES (1, 'Mads Mikkelsen', '1965-11-22', 5),(2, 'Pavle Vuisic', '1926-07-10', 1),(3, 'Leonardo DiCaprio', '1974-11-11', 2),(4, 'Anya Taylor-Joy', '1996-04-16', 4),(5, 'Brad Pitt', '1963-12-18', 2);"

"UNLOCK TABLES;"

"CREATE TABLE `role` (
  `id_actor` int NOT NULL,
  `id_movie` int NOT NULL,
  PRIMARY KEY (`id_actor`,`id_movie`),
  KEY `fk_role_movie_idx` (`id_movie`),
  CONSTRAINT `fk_role_actor` FOREIGN KEY (`id_actor`) REFERENCES `actor` (`id`),
  CONSTRAINT `fk_role_movie` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;"
]