create table if not exists `itoken-service-admin`.tb_sys_user
(
    user_code varchar(100) not null comment '用户编码'
    primary key,
    login_code varchar(100) not null comment '登录账号',
    user_name varchar(100) not null comment '用户昵称',
    password varchar(100) not null comment '登录密码',
    email varchar(300) null comment '电子邮箱',
    mobile varchar(100) null comment '手机号码',
    phone varchar(100) null comment '办公电话',
    sex char null comment '用户性别',
    avatar varchar(1000) null comment '头像路径',
    sign varchar(200) null comment '个性签名',
    wx_openid varchar(100) null comment '绑定的微信号',
    mobile_imei varchar(100) null comment '绑定的手机串号',
    user_type varchar(16) not null comment '用户类型',
    ref_code varchar(64) null comment '用户类型引用编号',
    ref_name varchar(100) null comment '用户类型引用姓名',
    mgr_type char not null comment '管理员类型（0非管理员 1系统管理员  2二级管理员）',
    pwd_security_level decimal(1) null comment '密码安全级别（0初始 1很弱 2弱 3安全 4很安全）',
    pwd_update_date datetime null comment '密码最后更新时间',
    pwd_update_record varchar(1000) null comment '密码修改记录',
    pwd_question varchar(200) null comment '密保问题',
    pwd_question_answer varchar(200) null comment '密保问题答案',
    pwd_question_2 varchar(200) null comment '密保问题2',
    pwd_question_answer_2 varchar(200) null comment '密保问题答案2',
    pwd_question_3 varchar(200) null comment '密保问题3',
    pwd_question_answer_3 varchar(200) null comment '密保问题答案3',
    pwd_quest_update_date datetime null comment '密码问题修改时间',
    last_login_ip varchar(100) null comment '最后登陆IP',
    last_login_date datetime null comment '最后登陆时间',
    freeze_date datetime null comment '冻结时间',
    freeze_cause varchar(200) null comment '冻结原因',
    user_weight decimal(8) default 0 null comment '用户权重（降序）',
    status char not null comment '状态（0正常 1删除 2停用 3冻结）',
    create_by varchar(64) not null comment '创建者',
    create_date datetime not null comment '创建时间',
    update_by varchar(64) not null comment '更新者',
    update_date datetime not null comment '更新时间',
    remarks varchar(500) null comment '备注信息',
    corp_code varchar(64) default '0' not null comment '归属集团Code',
    corp_name varchar(100) default 'iToken' not null comment '归属集团Name',
    extend_s1 varchar(500) null comment '扩展 String 1',
    extend_s2 varchar(500) null comment '扩展 String 2',
    extend_s3 varchar(500) null comment '扩展 String 3',
    extend_s4 varchar(500) null comment '扩展 String 4',
    extend_s5 varchar(500) null comment '扩展 String 5',
    extend_s6 varchar(500) null comment '扩展 String 6',
    extend_s7 varchar(500) null comment '扩展 String 7',
    extend_s8 varchar(500) null comment '扩展 String 8',
    extend_i1 decimal(19) null comment '扩展 Integer 1',
    extend_i2 decimal(19) null comment '扩展 Integer 2',
    extend_i3 decimal(19) null comment '扩展 Integer 3',
    extend_i4 decimal(19) null comment '扩展 Integer 4',
    extend_f1 decimal(19,4) null comment '扩展 Float 1',
    extend_f2 decimal(19,4) null comment '扩展 Float 2',
    extend_f3 decimal(19,4) null comment '扩展 Float 3',
    extend_f4 decimal(19,4) null comment '扩展 Float 4',
    extend_d1 datetime null comment '扩展 Date 1',
    extend_d2 datetime null comment '扩展 Date 2',
    extend_d3 datetime null comment '扩展 Date 3',
    extend_d4 datetime null comment '扩展 Date 4'
    )
    comment '用户表';

create index idx_sys_user_cc
	on `itoken-service-admin`.tb_sys_user (corp_code);

create index idx_sys_user_email
	on `itoken-service-admin`.tb_sys_user (email);

create index idx_sys_user_imei
	on `itoken-service-admin`.tb_sys_user (mobile_imei);

create index idx_sys_user_lc
	on `itoken-service-admin`.tb_sys_user (login_code);

create index idx_sys_user_mobile
	on `itoken-service-admin`.tb_sys_user (mobile);

create index idx_sys_user_mt
	on `itoken-service-admin`.tb_sys_user (mgr_type);

create index idx_sys_user_rc
	on `itoken-service-admin`.tb_sys_user (ref_code);

create index idx_sys_user_rt
	on `itoken-service-admin`.tb_sys_user (user_type);

create index idx_sys_user_status
	on `itoken-service-admin`.tb_sys_user (status);

create index idx_sys_user_ud
	on `itoken-service-admin`.tb_sys_user (update_date);

create index idx_sys_user_us
	on `itoken-service-admin`.tb_sys_user (user_weight);

create index idx_sys_user_wo
	on `itoken-service-admin`.tb_sys_user (wx_openid);

