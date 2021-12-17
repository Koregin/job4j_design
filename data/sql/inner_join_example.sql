drop table publisher cascade;
create table publisher(
    id serial primary key,
    name varchar(255)
);
drop table books;
create table books(
    id serial primary key,
    name varchar(255),
    ISBN varchar(17),
    publisher_id int references publisher(id)
);

insert into publisher(name) values ('Вильямс');
insert into publisher(name) values ('Прогресс книга');
insert into publisher(name) values ('Диалектика-Вильямс');
insert into books(name, ISBN, publisher_id) VALUES ('Java. Библиотека профессионала. Том 1. Основы | Хорстманн Кей С.', '978-5-907114-79-1', 1);
insert into books(name, ISBN, publisher_id) VALUES ('Философия Java. 4-е полное изд.', '978-5-4461-1107-7', 2);
insert into books(name, ISBN, publisher_id) VALUES ('Java. Полное руководство | Шилдт Герберт', '978-5-6040043-6-4', 3);
insert into books(name, ISBN) VALUES ('Легкий способ выучить Java | Пэйн Брайсон', '978-5-04-093540-6');

select b.name as Название, b.ISBN as ISBN, p.name as Издательство
from books b inner join public.publisher p on p.id = b.publisher_id;

select b.id as ID, b.ISBN as ISBN
from books b inner join public.publisher p on p.id = b.publisher_id;

select b.name as Название, p.name as Издательство
from books b inner join public.publisher p on p.id = b.publisher_id;




