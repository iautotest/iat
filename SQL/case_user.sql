CREATE TABLE `case_user` (
   `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
   `case` varchar(1024) DEFAULT '' COMMENT '用例名',
   `group` char(8) DEFAULT '' COMMENT 'case组编号',
   `script` char(64) DEFAULT '' COMMENT '脚本名',
   `run` char(1) DEFAULT '',
   `input` text COMMENT '输入数据',
   `expect` text COMMENT '预期返回值',
   `result` char(8) DEFAULT '' COMMENT '执行结果',
   `errormsg` text COMMENT '错误日志',
   `comment` varchar(255) DEFAULT '' COMMENT '说明',
   `time` datetime DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
 
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('账号未登录','','','','','','','','','2015-09-22 21:07:28');
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('获取id','1','users_me','1','|||','\"id\":1273','FAIL','预期值检验失败：\r\n第0个预期值:\"id\":1273	未找到\r\n实际值为：{\"status\":0,\"error\":{\"code\":1007,\"msg\":\"没有访问权限，请重新登录\"}}','','2015-10-14 11:06:07');
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('','','','','','','','','','0000-00-00 00:00:00');
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('普通账号登录','','','','','','','','','2015-09-23 10:09:30');
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('普通登录','2','api_login','1','||||zhengyi@caishuo.com|123456','\"status\":0','FAIL','预期值检验失败：\r\n第0个预期值:\"status\":0	未找到\r\n实际值为：{\"status\":1,\"data\":{\"id\":10433,\"username\":\"C00081T\",\"avatar\":\"https://testing.caishuo.com/images/v3/userEditLogo.png\",\"headline\":null,\"gender\":null,\"province\":null,\"city\":null,\"intro\":null,\"relationship\":0,\"position_scale\":0,\"mobile\":\"15068160665\",\"email\":\"zhengyi@caishuo.com\",\"password_exist\":true,\"following_baskets_count\":0,\"following_stocks_count\":0,\"user_baskets_count\":0,\"followings_count\":0,\"followers_count\":0,\"notification_count\":0,\"shipan_data\":null,\"friends_count\":0,\"new_friends_count\":0,\"new_followers_count\":0,\"common_friends_count\":0,\"orientation\":null,\"period\":null,\"concern\":null,\"tags\":[],\"providers\":{\"wechat\":null,\"weibo\":null,\"qq\":null},\"access_token\":\"2df993aa37da2eeab03460b9d65751a6\",\"is_new\":null}}','','2015-10-14 11:06:07');
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('获取id','2','users_me','1','|||','\"uid\":1273','FAIL','预期值检验失败：\r\n第0个预期值:\"uid\":1273	未找到\r\n实际值为：{\"status\":0,\"error\":{\"code\":1007,\"msg\":\"没有访问权限，请重新登录\"}}','','2015-10-12 18:53:50');
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('','','','','','','',NULL,'',NULL);
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('普通账号登录2-错误密码','','','','','','',NULL,'',NULL);
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('普通登录','3','api_login','1','||||zhengyi@caishuo.com|123457\r\n','\"status\":0','PASS','','','2015-10-14 11:06:08');
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('获取id','3','users_me','1','|||','\"status\":0','PASS','','','2015-10-14 11:06:08');
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('','','','','',NULL,'',NULL,'',NULL);
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('第三方账号注册','','','','',NULL,'',NULL,'',NULL);
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('第三方账号注册','4','provider','1','||||qq|42323334||332||1',NULL,'PASS','','','2015-10-14 11:06:08');
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('获取id','4','users_me','1','|||',NULL,'PASS','','','2015-10-14 11:06:08');
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('','','','',' ',NULL,'',NULL,'',NULL);
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('第三方账号登录','','','',' ',NULL,'',NULL,'',NULL);
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('第三方账号登录','5','provider','1','||||qq|42323334||332||1','\"code\":1002|\"msg\":\"该QQ已与您的财说账户绑定\"','FAIL','预期值检验失败：\r\n第0个预期值:\"code\":1002	未找到\r\n第1个预期值:\"msg\":\"该QQ已与您的财说账户绑定\"	未找到\r\n实际值为：{\"status\":0,\"error\":{\"code\":1001,\"msg\":\"该QQ已与您的财说账户绑定\"}}','','2015-10-14 11:06:08');
INSERT INTO `users_me_login` (`case`, `group`, `script`, `run`, `input`, `expect`, `result`, `errormsg`, `comment`, `time`) VALUES('获取id','5','users_me','1','|||','\"code\":1007|\"msg\":\"没有访问权限，请重新登录\"','PASS','','','2015-09-24 14:10:38');
