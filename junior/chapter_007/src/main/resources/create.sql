-- Создать SQL скрипт инициализирующий создание новой базы данных.
create database first_db;

-- Создать SQL скрипт создающий таблицы для хранения структуры системы заявок.
create table category (
  id serial primary key,
  name character varchar(50)
);

create table state (
  id serial primary key,
  name character varchar(50)
);

create table access (
  id serial primary key,
  name character varchar(50)
);

create table role (
  id serial primary key,
  name character varchar(50)
);

create table role_access (
  role_id integer references role(id),
  access_id integer references access(id)
);

create table Uuser (
  id serial primary key,
  role_id integer references role(id),
  name character varchar(50)
);

create table item(
  id serial primary key,
  user_id integer references Uuser(id),
  category_id integer references category(id),
  state_id integer references state(id),
  name character varchar(50)
);

create table attach(
  id serial primary key,
  item_id integer references item(id),
  name character varchar(50)
);

create table comment(
  id serial primary key,
  item_id integer references item(id),
  name character varchar(50)
);
-- Создать SQL скрипт заполняющий начальные данные для системы заявок.
insert into category (name)
values ('minor');

insert into category(name) values('major');
insert into category(name) values('block');

insert into state(name) values('active');
insert into state(name) values('disable');

insert into access(name) values('full');
insert into access(name) values('not-full');

insert into role(name) values('admin');
insert into role(name) values('everyone');

insert into role_access(role_id, access_id) values(1, 1);
insert into role_access(role_id, access_id) values(2, 2);

insert into Uuser(role_id, name) values(1, 'Ivan');
insert into Uuser(role_id, name) values(2, 'Vasya');

insert into item(user_id, category_id, state_id, name) values(1, 2, 1, 'Huston we have a problem');