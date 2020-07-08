/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.30 : Database - city_crowd_data
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`city_crowd_data` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `city_crowd_data`;

/*Table structure for table `t_city` */

DROP TABLE IF EXISTS `t_city`;

CREATE TABLE `t_city` (
  `code` int(11) NOT NULL,
  `name` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `pinyin` varchar(62) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_city` */

insert  into `t_city`(`code`,`name`,`pinyin`) values 
(110000,'北京','bei jing'),
(120000,'天津','tian jin'),
(130100,'石家庄','shi jia zhuang'),
(130200,'唐山','tang shan'),
(130300,'秦皇岛','qin huang dao'),
(130400,'邯郸','han dan'),
(130500,'邢台','xing tai'),
(130600,'保定','bao ding'),
(130700,'张家口','zhang jia kou'),
(130900,'沧州','cang zhou'),
(131000,'廊坊','lang fang'),
(140100,'太原','tai yuan'),
(140200,'大同','da tong'),
(150100,'呼和浩特','hu he hao te'),
(150600,'鄂尔多斯','e er duo si'),
(210100,'沈阳','shen yang'),
(210200,'大连','da lian'),
(220100,'长春','chang chun'),
(230100,'哈尔滨','ha er bin'),
(310000,'上海','shang hai'),
(320100,'南京','nan jing'),
(320200,'无锡','wu xi'),
(320300,'徐州','xu zhou'),
(320400,'常州','chang zhou'),
(320500,'苏州','su zhou'),
(320600,'南通','nan tong'),
(320700,'连云港','lian yun gang'),
(320800,'淮安','huai an'),
(320900,'盐城','yan cheng'),
(321000,'扬州','yang zhou'),
(321100,'镇江','zhen jiang'),
(321200,'泰州','tai zhou'),
(321300,'宿迁','su qian'),
(330100,'杭州','hang zhou'),
(330200,'宁波','ning bo'),
(330300,'温州','wen zhou'),
(330400,'嘉兴','jia xing'),
(330500,'湖州','hu zhou'),
(330600,'绍兴','shao xing'),
(330700,'金华','jin hua'),
(330800,'衢州','qu zhou'),
(331000,'台州','tai zhou'),
(340100,'合肥','he fei'),
(340200,'芜湖','wu hu'),
(341100,'滁州','chu zhou'),
(350100,'福州','fu zhou'),
(350200,'厦门','xia men'),
(350500,'泉州','quan zhou'),
(350600,'漳州','zhang zhou'),
(360100,'南昌','nan chang'),
(360700,'赣州','gan zhou'),
(370100,'济南','ji nan'),
(370200,'青岛','qing dao'),
(370300,'淄博','zi bo'),
(370600,'烟台','yan tai'),
(370700,'潍坊','wei fang'),
(370800,'济宁','ji ning'),
(370900,'泰安','tai an'),
(371300,'临沂','lin yi'),
(371400,'德州','de zhou'),
(410100,'郑州','zheng zhou'),
(410300,'洛阳','luo yang'),
(410700,'新乡','xin xiang'),
(411300,'南阳','nan yang'),
(420100,'武汉','wu han'),
(430100,'长沙','chang sha'),
(430400,'衡阳','heng yang'),
(440100,'广州','guang zhou'),
(440200,'韶关','shao guan'),
(440300,'深圳','shen zuo'),
(440400,'珠海','zhu hai'),
(440500,'汕头','shan tou'),
(440600,'佛山','fo shan'),
(440700,'江门','jiang men'),
(440800,'湛江','zhan jiang'),
(440900,'茂名','mao ming'),
(441200,'肇庆','zhao qing'),
(441300,'惠州','hui zhou'),
(441800,'清远','qing yuan'),
(441900,'东莞','dong zuo'),
(442000,'中山','zhong shan'),
(450100,'南宁','nan ning'),
(450200,'柳州','liu zhou'),
(450300,'桂林','gui lin'),
(460100,'海口','hai kou'),
(460200,'三亚','san ya'),
(500000,'重庆','chong qing'),
(510100,'成都','cheng du'),
(510600,'德阳','de yang'),
(510700,'绵阳','mian yang'),
(511300,'南充','nan chong'),
(520100,'贵阳','gui yang'),
(530100,'昆明','kun ming'),
(610100,'西安','xi an'),
(610400,'咸阳','xian yang'),
(620100,'兰州','lan zhou'),
(630100,'西宁','xi ning'),
(640100,'银川','yin chuan'),
(650100,'乌鲁木齐','wu lu mu qi'),
(654000,'伊犁','yi li'),
(810000,'香港','xiang gang');

/*Table structure for table `t_crowd_data` */

DROP TABLE IF EXISTS `t_crowd_data`;

CREATE TABLE `t_crowd_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_code` varchar(8) COLLATE utf8_bin NOT NULL,
  `crowd` float NOT NULL,
  `time` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_crowd_data` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
