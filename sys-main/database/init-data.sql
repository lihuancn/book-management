/* bm_admin */
# 密码8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92为123456
# 加密采用SHA256算法
insert into bm_admin(username, password, phone, email)
VALUES ('vorbote', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '19918903791',
        'theodore0126@outlook.com'),
       # 8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918为admin
       ('lihuan', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '111', '111');

insert into bm_user(username, password, phone, email, status)
VALUES ('user001', 'c23162ffc1a535af2ee09588469194816e60cb437e30d78c5617b5d3f1304d6a', '111', '111', 1);

insert into bm_book(book_name, author, price, stock)
values ('西游记', '吴承恩', 19.98, 100),
       ('三国演义', '罗贯中', 19.98, 100);
