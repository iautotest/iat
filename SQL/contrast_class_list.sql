CREATE TABLE `contrast_class_list` (
   `id` mediumint(4) unsigned NOT NULL AUTO_INCREMENT,
   `inCaseName` char(64) DEFAULT '' COMMENT '接口短名',
   `className` char(64) DEFAULT '' COMMENT '类名',
   `classFullName` varchar(512) DEFAULT '' COMMENT '类全名',
   `func_parameters` varchar(512) DEFAULT '' COMMENT '参数说明',
   `Example` varchar(128) DEFAULT '' COMMENT '示例',
   `API_name` varchar(128) DEFAULT '' COMMENT '接口名称',
   `module` char(64) DEFAULT '' COMMENT '模块',
   `COMMENT` text COMMENT '说明',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC
 
INSERT INTO `public_contrast_class_list` (`inCaseName`, `className`, `classFullName`, `func_parameters`, `Example`, `API_name`, `module`, `COMMENT`) VALUES('sqle','executeSql','com.caishuo.IAT.publicFunction.util.ExecuteSql','sql_string','SELECT func_short_name FROM public_contrast_table WHERE id = \'0\';','执行SQL语句','','select 或 update');
INSERT INTO `public_contrast_class_list` (`inCaseName`, `className`, `classFullName`, `func_parameters`, `Example`, `API_name`, `module`, `COMMENT`) VALUES('login','login','com.caishuo.IAT.scripts.others.Login','<token>|<SN>|<X-Client_Key>|<X-Client-Version>|账号|密码','|||accout|passwd','登录接口','','登录接口');
