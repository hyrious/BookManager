
create table book (
  id        integer primary key,
  title     text    not null,
  available integer default 1 -- 0: borrowed
);

create table user (
  id          integer primary key,
  name        text    not null,
  password    text    not null,
              -- sha1(concat('salt', 'user password'))
  permission  integer default 1
              -- 1:borrow/return ; 2:add/modify/delete(book)
              --                 ; 4:add/modify/delete(user)
);

create table borrow (
  user_id     integer,
  book_id     integer,
  due         text, -- date('now', '+14 day')
  primary key(user_id, book_id),
  foreign key(user_id) references user(id),
  foreign key(book_id) references book(id)
);

create table return (
  user_id     integer,
  book_id     integer,
  ret         text,
  foreign key(user_id) references user(id),
  foreign key(book_id) references book(id)
);
