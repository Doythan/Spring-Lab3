delete from board_tb;
delete from user_tb;

insert into user_tb(id, username) values (1, 'ssar');
insert into user_tb(id, username) values (2, 'cos');

insert into board_tb(id, title, content, user_id, created_at)
values (1, '첫 글', '안녕하세요', 1, CURRENT_TIMESTAMP());
insert into board_tb(id, title, content, user_id, created_at)
values (2, '두 번째 글', '내용입니다', 2, CURRENT_TIMESTAMP());
