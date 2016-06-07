CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fio` varchar(100) NOT NULL,
  `login` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_587tdsv8u5cvheyo9i261xhry` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES (1,'Vitalii','leonard','$2a$10$.8GsO1YdoWndfG/OWEpf/.0CnLxCkhRTOk6IiZUpnT24YMk85dONO');

CREATE TABLE `phonebook` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `lastname` varchar(30) NOT NULL,
  `mobPhone` varchar(15) NOT NULL,
  `name` varchar(30) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `surname` varchar(30) NOT NULL,
  `userInfo` varchar(60) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fgl0bafhiyxcwmglsdlwfi0sr` (`userid`),
  CONSTRAINT `FK_fgl0bafhiyxcwmglsdlwfi0sr` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

INSERT INTO `phonebook` VALUES (1,'London','ajohn@gmail.com','Adams','+38(097)0472067','John','+38(095)0472067','Adams','dev',1),(9,'London','tony@gmail.com','TBler','+38(095)0472067','Toni','','Bler','',1),(10,'Mexico','miron@gmail.com','Kiese','+38(095)0572067','Miron','','Nikel','',1);
