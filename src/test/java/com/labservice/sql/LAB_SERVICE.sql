/*
 Navicat Premium Data Transfer

 Source Server         : 程晗
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 39.106.95.28:3306
 Source Schema         : LAB_SERVICE

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 05/10/2019 21:54:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`  (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `scid` int(11) NOT NULL,
  `lname` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`lid`) USING BTREE,
  INDEX `scid`(`scid`) USING BTREE,
  CONSTRAINT `label_ibfk_1` FOREIGN KEY (`scid`) REFERENCES `school` (`scid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES (1, 1, '电子创新实验室');
INSERT INTO `label` VALUES (2, 1, '物联网实验室');

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pname` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `plimit` int(11) NULL DEFAULT NULL,
  `account` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `passwd` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `scid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `scid`(`scid`) USING BTREE,
  CONSTRAINT `scid` FOREIGN KEY (`scid`) REFERENCES `school` (`scid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of people
-- ----------------------------
INSERT INTO `people` VALUES (1, '165150222', '孙雨帆', 1, 'strikebiood@163.com', '000', 1);
INSERT INTO `people` VALUES (2, '17515636', '周启航', 1, '17515636', '123', 1);
INSERT INTO `people` VALUES (10, '192168', '于静', 2, 'yujing@gmail.com', '000', 1);

-- ----------------------------
-- Table structure for ppl
-- ----------------------------
DROP TABLE IF EXISTS `ppl`;
CREATE TABLE `ppl`  (
  `pplid` int(10) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `pnum` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `lid` int(11) NOT NULL,
  `stat` bit(1) NULL DEFAULT b'0',
  PRIMARY KEY (`pplid`) USING BTREE,
  INDEX `lid`(`lid`) USING BTREE,
  INDEX `pnum`(`pnum`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  CONSTRAINT `lid` FOREIGN KEY (`lid`) REFERENCES `label` (`lid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `pid` FOREIGN KEY (`pid`) REFERENCES `people` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `pnum` FOREIGN KEY (`pnum`) REFERENCES `project` (`pnum`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ppl
-- ----------------------------
INSERT INTO `ppl` VALUES (1, 1, '151', 1, b'1');
INSERT INTO `ppl` VALUES (3, 2, '151', 1, b'1');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `pnum` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `proname` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `sid` int(11) NULL DEFAULT NULL,
  `tid` int(11) NULL DEFAULT NULL,
  `status` bit(1) NULL DEFAULT b'0',
  `lid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`pnum`) USING BTREE,
  INDEX `pnum`(`pnum`) USING BTREE,
  INDEX `sid`(`sid`) USING BTREE,
  INDEX `tid`(`tid`) USING BTREE,
  INDEX `la`(`lid`) USING BTREE,
  CONSTRAINT `la` FOREIGN KEY (`lid`) REFERENCES `label` (`lid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sid` FOREIGN KEY (`sid`) REFERENCES `people` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tid` FOREIGN KEY (`tid`) REFERENCES `people` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('151', '基于人脸识别的开放性实验室管理系统', 1, 10, b'0', 1);

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `pplid` int(11) NOT NULL,
  `retime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`pplid`) USING BTREE,
  CONSTRAINT `ppl` FOREIGN KEY (`pplid`) REFERENCES `ppl` (`pplid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (1, '2019-06-10 21:31:50');
INSERT INTO `record` VALUES (3, '2019-06-17 09:40:36');

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school`  (
  `scid` int(4) NOT NULL AUTO_INCREMENT,
  `scname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`scid`) USING BTREE,
  UNIQUE INDEX `scname`(`scname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_hungarian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES (3, '北京科技大学');
INSERT INTO `school` VALUES (1, '北京科技大学天津学院');
INSERT INTO `school` VALUES (2, '天津财经大学珠江学院');
INSERT INTO `school` VALUES (4, '北京理工大学');
INSERT INTO `school` VALUES (5, '北京邮电大学');

-- ----------------------------
-- View structure for peopleinfo
-- ----------------------------
DROP VIEW IF EXISTS `peopleinfo`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `peopleinfo` AS select `people`.`pid` AS `pid`,`people`.`num` AS `num`,`people`.`account` AS `account`,`school`.`scname` AS `scname`,`people`.`pname` AS `pname` from (`people` join `school` on((`people`.`scid` = `school`.`scid`)));

-- ----------------------------
-- View structure for projectinfo
-- ----------------------------
DROP VIEW IF EXISTS `projectinfo`;
CREATE ALGORITHM = UNDEFINED DEFINER = `chegnhan`@`%` SQL SECURITY DEFINER VIEW `projectinfo` AS select `project`.`status` AS `status`,`project`.`tid` AS `tid`,`project`.`pnum` AS `pnum`,`project`.`proname` AS `proname`,`people`.`scid` AS `scid`,`people`.`pname` AS `pname` from ((`project` join `people` on((`project`.`sid` = `people`.`pid`))) join `label` on((`label`.`lid` = `project`.`lid`)));

-- ----------------------------
-- View structure for Recordtime
-- ----------------------------
DROP VIEW IF EXISTS `Recordtime`;
CREATE ALGORITHM = UNDEFINED DEFINER = `chegnhan`@`%` SQL SECURITY DEFINER VIEW `Recordtime` AS select `project`.`tid` AS `tid`,`project`.`sid` AS `sid`,`people`.`pid` AS `pid`,`people`.`pname` AS `pname`,`project`.`proname` AS `proname`,`label`.`lname` AS `lname`,`record`.`retime` AS `retime` from ((((`ppl` join `people` on((`people`.`pid` = `ppl`.`pid`))) join `label` on((`label`.`lid` = `ppl`.`lid`))) join `project` on((`project`.`pnum` = `ppl`.`pnum`))) join `record` on((`record`.`pplid` = `ppl`.`pplid`))) where (`ppl`.`stat` = 1);

SET FOREIGN_KEY_CHECKS = 1;
