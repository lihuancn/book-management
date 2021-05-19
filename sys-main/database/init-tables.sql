create database if not exists book_management;
use book_management;

drop table if exists bm_book; /* 书籍表 */
create table bm_book
(
    id        int primary key auto_increment not null comment '书本ID',
    book_name varchar(60)                    not null comment '书本名称',
    author    varchar(20)                    null comment '作者',
    price     double                         not null default 0.0 comment '价格',
    stock     int                            not null default 0 comment '库存数量'
);

drop table if exists bm_admin; /* 管理员表 */
create table bm_admin
(
    id       int primary key auto_increment not null comment '管理员ID',
    username varchar(40)                    not null comment '管理员用户名',
    password varchar(200)                   not null comment '管理员密码',
    phone    varchar(20)                    not null comment '电话号码',
    email    varchar(40)                    not null comment '电子邮箱'
);

drop table if exists bm_user; /* 用户表 */
create table bm_user
(
    id              int primary key auto_increment not null comment '书本ID',
    username        varchar(40)                    not null comment '用户名',
    password        varchar(200)                   not null comment '密码',
    phone           varchar(20)                    not null comment '电话号码',
    email           varchar(40)                    not null comment '电子邮箱',
    status          int                            not null default 1 comment '用户状态，1-available，2-banned，3-deleted',
    level           int                            not null default 1 comment '用户登记，1-青铜会员，2-白银会员，3-黄金会员，4-钻石会员，5-优享会员',
    available_count int                            not null default 5 comment '用户可借书籍数目'
);

drop table if exists bm_order_rent; /* 租赁表 */
create table bm_order_rent
(
    id                  int primary key auto_increment not null comment '借阅记录ID',
    user_id             int                            not null comment '用户ID',
    rent_date           datetime                       not null default now() comment '租借时间',
    planned_return_date datetime                       not null default now() comment '应当归还时间',
    return_date         datetime                       null comment '实际归还时间',
    is_returned         int                            not null default 0 comment '是否归还，0-未归还，1-已经归还',
    book_count          int                            not null default 0 comment '书本数量',
    constraint fk_bm_order_rent_bm_user
        foreign key (user_id) references bm_user (id)
);

drop table if exists bm_order_rent_detail; /* 租赁详情表 */
create table bm_order_rent_detail
(
    id       int primary key auto_increment not null comment '借阅记录详情ID',
    order_id int                            not null comment '借阅记录ID',
    book_id  int                            not null comment '书本ID',
    constraint fk_order_rent_detail_order_rent
        foreign key (order_id) references bm_order_rent (id),
    constraint fk_order_rent_detail_book
        foreign key (book_id) references bm_book (id)
);

