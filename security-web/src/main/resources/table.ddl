
-- ----------------------------
-- Table structure for `authorities`
-- ----------------------------
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) COLLATE utf8_bin NOT NULL,
  `authority` varchar(50) COLLATE utf8_bin NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of authorities
-- ----------------------------
INSERT INTO `authorities` VALUES ('A', 'ROLE_ADMIN');
INSERT INTO `authorities` VALUES ('A', 'ROLE_USER');
INSERT INTO `authorities` VALUES ('B', 'ROLE_USER');
INSERT INTO `authorities` VALUES ('C', 'ROLE_USER');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(50) COLLATE utf8_bin NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('A', '123456', '1');
INSERT INTO `users` VALUES ('B', '123456', '1');
INSERT INTO `users` VALUES ('C', '123456', '1');
