CREATE TABLE user  (
    id varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
    user_code varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户编码',
    user_name varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
    password varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
    email varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;


insert into user (id,user_code,user_name,password,email) values ('1','1001','聂文学','123456','414394568@qq.com');
insert into user (id,user_code,user_name,password,email) values ('2','1002','张三','123456','414394568@qq.com');
insert into user (id,user_code,user_name,password,email) values ('3','1003','李四','123456','414394568@qq.com');
insert into user (id,user_code,user_name,password,email) values ('4','1004','王五','123456','414394568@qq.com');
insert into user (id,user_code,user_name,password,email) values ('5','1005','于六','123456','414394568@qq.com');