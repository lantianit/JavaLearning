create database if not exists java105_blog_system;

use java105_blog_system;

drop table if exists blog;
create table blog (
    blogId int primary key auto_increment,
    title varchar(256),
    content text,
    postTime datetime,
    -- userId 就是文章作者的用户 id
    userId int
);

drop table if exists user;
create table user (
    userId int primary key auto_increment,
    username varchar(50) unique,
    password varchar(50)
);

insert into blog values(null, "这是第一篇博客", "从今天开始, 我要认真写代码", '2022-11-24 20:00:00', 1);
insert into blog values(null, "这是第二篇博客", "从今天开始, 我要认真写代码", '2022-11-25 20:00:00', 1);
insert into blog values(null, "这是第三篇博客", "从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码从今天开始, 我要认真写代码", '2022-11-26 20:00:00', 1);

insert into user values(null, "zhangsan", "123");
insert into user values(null, "lisi", "123");