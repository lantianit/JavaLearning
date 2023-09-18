-- 习惯上把那建库建表的数据库操作单独存储为.sql文件，方便后面的数据库迁移和部署

drop database if exists BlogSystem;
-- 创建数据库
create database if not exists BlogSystem DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

use BlogSystem;

-- 创建博客表
drop table if exists blog;
create table blog
(
    blogId   int primary key auto_increment, -- 自增主键
    title    varchar(1024) not null,         -- 博客标题
    content  mediumtext,                     -- 博客正文
    postTime datetime,                       -- 发布时间
    userId   int                             -- 用户Id
);

drop table if exists user;
create table user
(
    userId   int primary key auto_increment, -- 自增主键
    username varchar(255) not null,          -- 用户名
    password varchar(255) not null           -- 密码
    -- 用户的头像地址、Github地址....都可以放在这里，为了演示方便这里暂时先不加，后面同学可以自己实现
);

insert into user values (1, '张三', '123456');
insert into user values (2, 'admin', '123456');
insert into user(username, password) values ('小哪吒', '123456');
select * from user;
