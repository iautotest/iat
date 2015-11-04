CREATE TABLE `dispatch` (
   `id` MEDIUMINT(4) NOT NULL AUTO_INCREMENT,
   `case_tablename` CHAR(64) DEFAULT NULL COMMENT 'case表名称',
   `is_running` TINYINT(1) DEFAULT NULL COMMENT '是否执行',
   `execute_result` CHAR(128) DEFAULT NULL COMMENT 'case执行结果',
   `execute_errormsg` VARCHAR(512) DEFAULT NULL COMMENT '错误结果（若有）',
   `execute_Time` DATETIME DEFAULT NULL,
   `num_pass_case` INT(4) DEFAULT NULL,
   `num_fail_case` INT(4) DEFAULT NULL,
   `num_run_case` INT(4) DEFAULT NULL,
   `num_all_case` INT(4) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
 
 
insert into `dispatch` (`case_tablename`, `is_running`, `execute_result`, `execute_errormsg`, `execute_Time`, `num_pass_case`, `num_fail_case`, `num_run_case`, `num_all_case`) values('case_login_12','0','FAIL',' ','2015-10-14 11:06:07','0','1','1','9');
insert into `dispatch` (`case_tablename`, `is_running`, `execute_result`, `execute_errormsg`, `execute_Time`, `num_pass_case`, `num_fail_case`, `num_run_case`, `num_all_case`) values('case_sqle','0','FAIL','','2015-10-14 11:06:07','0','0','0',NULL);
insert into `dispatch` (`case_tablename`, `is_running`, `execute_result`, `execute_errormsg`, `execute_Time`, `num_pass_case`, `num_fail_case`, `num_run_case`, `num_all_case`) values('case_trading','0','FAIL','','2015-10-14 11:06:07','0','0','0',NULL);
insert into `dispatch` (`case_tablename`, `is_running`, `execute_result`, `execute_errormsg`, `execute_Time`, `num_pass_case`, `num_fail_case`, `num_run_case`, `num_all_case`) values('users_me_login','0','FAIL',' ','2015-10-14 11:06:08','4','3','7','9');
insert into `dispatch` (`case_tablename`, `is_running`, `execute_result`, `execute_errormsg`, `execute_Time`, `num_pass_case`, `num_fail_case`, `num_run_case`, `num_all_case`) values('users_provider_s','0','FAIL',' ','2015-10-14 11:06:09','1','13','14','19');
insert into `dispatch` (`case_tablename`, `is_running`, `execute_result`, `execute_errormsg`, `execute_Time`, `num_pass_case`, `num_fail_case`, `num_run_case`, `num_all_case`) values('users_provider_e','0','FAIL',' ','2015-10-14 11:06:10','1','5','6','7');
insert into `dispatch` (`case_tablename`, `is_running`, `execute_result`, `execute_errormsg`, `execute_Time`, `num_pass_case`, `num_fail_case`, `num_run_case`, `num_all_case`) values('users_test','0',NULL,NULL,'2015-10-14 11:06:07',NULL,NULL,NULL,NULL);
insert into `dispatch` (`case_tablename`, `is_running`, `execute_result`, `execute_errormsg`, `execute_Time`, `num_pass_case`, `num_fail_case`, `num_run_case`, `num_all_case`) values('case_user','1','FAIL',' ','2015-11-03 20:51:54','7','2','9','9');
