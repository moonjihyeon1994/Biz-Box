create table user(
	email varchar(500) not null primary key,
    name varchar(500) not null,
    pw varchar(500) not null
);

create table pass(
	email varchar(500) not null primary key,
	salt varchar(500) not null
);