create database if not exists book_management;
use book_management;

drop table if exists bm_book;
create table bm_book
(
    id        int primary key auto_increment not null comment '书本ID',
    book_name varchar(60)                    not null comment '书本名称',
    author    varchar(20)                    null comment '作者',
    price     double                         not null default 0.0 comment '价格',
    stock     int                            not null default 0 comment '库存数量'
);

drop table if exists bm_admin;
create table bm_admin
(
    id       int primary key auto_increment not null comment '管理员ID',
    username varchar(40)                    not null comment '管理员用户名',
    password varchar(200)                   not null comment '管理员密码',
    phone    varchar(20)                    not null comment '电话号码',
    email    varchar(40)                    not null comment '电子邮箱'
);

drop table if exists bm_user;
create table bm_user
(
    id       int primary key auto_increment not null comment '书本ID',
    username varchar(40)                    not null comment '用户名',
    password varchar(200)                   not null comment '密码',
    phone    varchar(20)                    not null comment '电话号码',
    email    varchar(40)                    not null comment '电子邮箱',
    status   int                            not null default 1 comment '用户状态，1-available，2-banned，3-deleted'
);