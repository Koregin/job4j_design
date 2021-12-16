create table presidents(
	id serial primary key,
	name varchar(255)
);

create table countries(
	id serial primary key,
	name varchar(255),
	president_id int references presidents(id) unique
);

insert into presidents(name) values ('Joe Baiden');
insert into presidents(name) values ('Emmanuel Macron');
insert into countries(name, president_id) values ('USA', 1);
insert into countries(name, president_id) values ('France', 2);