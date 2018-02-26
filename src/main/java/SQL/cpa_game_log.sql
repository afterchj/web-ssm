/*
Navicat MySQL Data Transfer

Source Server         : 10.10.11.57
Source Server Version : 50617
Source Host           : 10.10.11.57:3306
Source Database       : boss_locker_data

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2018-02-26 10:47:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cpa_game_log
-- ----------------------------
DROP TABLE IF EXISTS `cpa_game_log`;
CREATE TABLE `cpa_game_log` (
  `task_type` varchar(255) NOT NULL,
  `log_date` date NOT NULL,
  `new_user` int(11) DEFAULT NULL,
  `task_user` int(11) DEFAULT NULL,
  `task_total` int(11) DEFAULT NULL,
  `expend_sum` double(11,2) DEFAULT NULL,
  `download_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`log_date`,`task_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cpa_game_log
-- ----------------------------
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-12', '23', '2152', '5239', '377.25', 'cpaLog-20171212.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-12', '0', '9', '22', '18.90', 'gameLog-20171212.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-13', '12', '1992', '4133', '264.79', 'cpaLog-20171213.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-13', '0', '13', '35', '87.28', 'gameLog-20171213.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-14', '10', '1792', '3225', '219.95', 'cpaLog-20171214.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-14', '0', '7', '14', '16.04', 'gameLog-20171214.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-15', '17', '1714', '3240', '213.43', 'cpaLog-20171215.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-15', '0', '5', '23', '11.06', 'gameLog-20171215.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-16', '40', '1751', '3324', '221.28', 'cpaLog-20171216.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-16', '1', '6', '11', '17.60', 'gameLog-20171216.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-17', '20', '1796', '3374', '218.50', 'cpaLog-20171217.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-17', '1', '9', '23', '56.22', 'gameLog-20171217.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-18', '30', '1833', '3412', '219.30', 'cpaLog-20171218.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-18', '1', '8', '22', '37.98', 'gameLog-20171218.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-19', '31', '1872', '3360', '213.51', 'cpaLog-20171219.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-19', '3', '11', '36', '87.90', 'gameLog-20171219.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-20', '21', '1842', '3164', '193.46', 'cpaLog-20171220.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-20', '1', '8', '18', '33.82', 'gameLog-20171221.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-21', '27', '1682', '2811', '171.52', 'cpaLog-20171221.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-21', '1', '5', '13', '7.20', 'gameLog-20171221.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-22', '25', '1647', '2779', '165.12', 'cpaLog-20171222.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-22', '1', '9', '19', '51.36', 'gameLog-20171222.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-23', '31', '1654', '2860', '176.63', 'cpaLog-20171223.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-23', '3', '9', '35', '57.52', 'gameLog-20171223.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-24', '28', '1677', '3011', '186.68', 'cpaLog-20171224.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-24', '1', '8', '23', '44.54', 'gameLog-20171224.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-25', '33', '1663', '3006', '194.52', 'cpaLog-20171225.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-25', '1', '6', '20', '35.96', 'gameLog-20171225.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-26', '35', '1695', '2982', '192.43', 'cpaLog-20171226.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-26', '0', '6', '14', '28.76', 'gameLog-20171226.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-27', '36', '1789', '3035', '198.76', 'cpaLog-20171227.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-27', '0', '9', '25', '44.42', 'gameLog-20171227.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-28', '33', '1782', '2904', '181.34', 'cpaLog-20171228.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-28', '0', '6', '20', '39.48', 'gameLog-20171228.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-29', '21', '368', '499', '49.27', 'cpaLog-20171229.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-29', '0', '7', '24', '45.08', 'gameLog-20171229.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-30', '23', '369', '498', '49.13', 'cpaLog-20171230.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-30', '0', '9', '25', '71.84', 'gameLog-20171230.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2017-12-31', '22', '347', '474', '46.80', 'cpaLog-20171231.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2017-12-31', '0', '13', '33', '87.66', 'gameLog-20171231.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-01', '46', '361', '515', '62.55', 'cpaLog-20180101.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-01', '0', '5', '17', '64.60', 'gameLog-20180101.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-02', '45', '348', '486', '47.99', 'cpaLog-20180102.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-02', '0', '7', '18', '83.04', 'gameLog-20180102.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-03', '23', '317', '455', '50.87', 'cpaLog-20180103.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-03', '1', '4', '9', '4.72', 'gameLog-20180103.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-04', '26', '315', '452', '50.78', 'cpaLog-20180104.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-04', '0', '13', '24', '109.88', 'gameLog-20180104.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-05', '19', '831', '1079', '100.35', 'cpaLog-20180105.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-05', '0', '15', '29', '55.72', 'gameLog-20180105.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-06', '20', '845', '1149', '104.20', 'cpaLog-20180106.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-06', '0', '5', '13', '7.58', 'gameLog-20180106.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-07', '29', '1153', '1548', '146.82', 'cpaLog-20180107.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-07', '0', '6', '12', '26.28', 'gameLog-20180107.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-08', '18', '805', '1034', '100.66', 'cpaLog-20180108.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-08', '0', '10', '26', '84.88', 'gameLog-20180108.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-09', '21', '722', '968', '95.82', 'cpaLog-20180109.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-09', '0', '8', '18', '45.60', 'gameLog-20180109.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-10', '30', '417', '619', '65.55', 'cpaLog-20180110.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-10', '0', '11', '39', '119.60', 'gameLog-20180110.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-11', '27', '360', '529', '55.99', 'cpaLog-20180111.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-11', '1', '6', '21', '71.92', 'gameLog-20180111.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-12', '23', '336', '423', '41.40', 'cpaLog-20180112.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-12', '0', '11', '25', '44.22', 'gameLog-20180112.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-13', '38', '361', '517', '54.66', 'cpaLog-20180113.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-13', '0', '7', '29', '78.42', 'gameLog-20180113.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-14', '38', '364', '538', '55.81', 'cpaLog-20180114.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-14', '0', '8', '19', '43.92', 'gameLog-20180114.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-15', '28', '373', '527', '54.36', 'cpaLog-20180115.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-15', '0', '8', '29', '137.20', 'gameLog-20180115.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-16', '28', '415', '559', '54.89', 'cpaLog-20180116.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-16', '0', '10', '19', '42.36', 'gameLog-20180116.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-17', '20', '415', '528', '52.10', 'cpaLog-20180117.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-17', '0', '9', '46', '155.46', 'gameLog-20180117.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-18', '18', '527', '694', '62.16', 'cpaLog-20180118.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-19', '19', '551', '725', '65.34', 'cpaLog-20180119.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-19', '0', '8', '13', '8.40', 'gameLog-20180119.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-20', '17', '323', '432', '47.75', 'cpaLog-20180120.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-20', '2', '7', '12', '26.00', 'gameLog-20180121.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-21', '23', '343', '490', '57.33', 'cpaLog-20180121.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-22', '26', '333', '457', '52.92', 'cpaLog-20180122.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-23', '25', '388', '515', '56.61', 'cpaLog-20180123.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-23', '1', '1', '6', '23.72', 'gameLog-20180123.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-24', '22', '414', '587', '64.57', 'cpaLog-20180124.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-24', '1', '1', '1', '1.08', 'gameLog-20180125.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-25', '21', '545', '715', '85.06', 'cpaLog-20180125.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-25', '1', '6', '14', '34.16', 'gameLog-20180126.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-26', '27', '421', '534', '76.23', 'cpaLog-20180126.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-27', '22', '389', '494', '71.31', 'cpaLog-20180127.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-27', '2', '5', '24', '40.28', 'gameLog-20180127.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-28', '20', '401', '543', '76.45', 'cpaLog-20180128.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-29', '22', '360', '496', '57.06', 'cpaLog-20180129.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-29', '2', '8', '28', '69.80', 'gameLog-20180129.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-30', '12', '491', '630', '63.78', 'cpaLog-20180130.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-01-30', '2', '6', '20', '34.64', 'gameLog-20180130.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-01-31', '22', '532', '716', '69.55', 'cpaLog-20180131.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-01', '19', '443', '607', '59.80', 'cpaLog-20180201.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-01', '1', '3', '3', '3.28', 'gameLog-20180201.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-02', '22', '383', '507', '52.73', 'cpaLog-20180202.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-03', '16', '292', '403', '47.17', 'cpaLog-20180203.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-03', '0', '7', '12', '95.74', 'gameLog-20180203.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-04', '16', '316', '464', '53.68', 'cpaLog-20180204.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-04', '0', '4', '8', '22.36', 'gameLog-20180205.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-05', '25', '255', '335', '38.76', 'cpaLog-20180205.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-06', '13', '224', '287', '32.73', 'cpaLog-20180206.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-06', '1', '7', '21', '13.88', 'gameLog-20180206.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-07', '17', '275', '368', '42.58', 'cpaLog-20180207.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-07', '1', '6', '19', '25.88', 'gameLog-20180207.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-08', '15', '245', '327', '36.73', 'cpaLog-20180208.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-08', '1', '6', '27', '43.44', 'gameLog-20180209.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-09', '15', '111', '159', '18.12', 'cpaLog-20180209.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-10', '17', '115', '204', '24.78', 'cpaLog-20180210.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-10', '1', '5', '8', '29.44', 'gameLog-20180210.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-11', '13', '124', '186', '22.89', 'cpaLog-20180211.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-11', '0', '11', '37', '113.00', 'gameLog-20180212.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-12', '12', '105', '158', '18.89', 'cpaLog-20180212.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-13', '14', '105', '144', '19.28', 'cpaLog-20180213.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-13', '0', '7', '13', '17.96', 'gameLog-20180213.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-14', '14', '101', '195', '29.01', 'cpaLog-20180214.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-15', '28', '73', '101', '12.76', 'cpaLog-20180215.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-15', '0', '2', '9', '21.14', 'gameLog-20180215.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-16', '11', '101', '168', '20.95', 'cpaLog-20180216.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-16', '0', '7', '8', '34.48', 'gameLog-20180216.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-17', '20', '108', '185', '23.00', 'cpaLog-20180217.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-18', '20', '104', '184', '24.19', 'cpaLog-20180218.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-18', '0', '7', '11', '16.72', 'gameLog-20180218.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-19', '19', '60', '89', '11.75', 'cpaLog-20180219.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-20', '26', '127', '233', '31.70', 'cpaLog-20180220.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-20', '0', '3', '4', '4.64', 'gameLog-20180220.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-21', '24', '118', '194', '27.49', 'cpaLog-20180221.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-21', '1', '4', '7', '36.60', 'gameLog-20180221.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-22', '33', '144', '241', '36.11', 'cpaLog-20180222.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-23', '33', '92', '146', '19.15', 'cpaLog-20180223.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-23', '1', '5', '14', '76.66', 'gameLog-20180223.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-24', '10', '193', '300', '35.92', 'cpaLog-20180224.xls');
INSERT INTO `cpa_game_log` VALUES ('游戏', '2018-02-24', '1', '6', '7', '13.36', 'gameLog-20180225.xls');
INSERT INTO `cpa_game_log` VALUES ('新快速', '2018-02-25', '29', '171', '274', '35.09', 'cpaLog-20180225.xls');
