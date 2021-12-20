create table students (
    id serial primary key,
    name varchar(50)
);

insert into students (name) values ('Иван Иванов');
insert into students (name) values ('Петр Петров');
insert into students (name) values ('Алексей Копейкин');

create table authors (
    id serial primary key,
    name varchar(50)
);

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id)
);

insert into books (name, author_id) values ('Евгений Онегин', 1);
insert into books (name, author_id) values ('Капитанская дочка', 1);
insert into books (name, author_id) values ('Дубровский', 1);
insert into books (name, author_id) values ('Мертвые души', 2);
insert into books (name, author_id) values ('Вий', 2);

create table orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),
    student_id integer references students(id)
);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);
insert into orders (book_id, student_id) values (2, 1);
insert into orders (book_id, student_id) values (5, 3);
insert into orders (book_id, student_id) values (5, 1);


select s.name, count(a.name), a.name from students as s
join orders o on s.id = o.student_id
join books b on o.book_id = b.id
join authors a on a.id = b.author_id
group by (s.name, a.name) having count(a.name) >= 2;

create view show_students_with_2_or_more_books as
select s.name as student, count(a.name), a.name as author from students as s
join orders o on s.id = o.student_id
join books b on o.book_id = b.id
join authors a on a.id = b.author_id
group by (s.name, a.name) having count(a.name) >= 2;

select * from show_students_with_2_or_more_books;

-- Запрос выводящий книги и их авторов и купленное кол-во, которые купили больше n - раз. Ограничить вывод 3-мя книгами --
select b.name, a.name, count(o.id)
from books as b
join authors a on a.id = b.author_id
join orders o on b.id = o.book_id
group by (b.name, a.name) having count(o.id) > 1
order by count(o.id) ASC
limit 3;

-- Представление на основании запроса --
create view show_books_which_buy_more_tnen_n_times as
select b.name as book, a.name as author, count(o.id) as Количество
from books as b
join authors a on a.id = b.author_id
join orders o on b.id = o.book_id
group by (b.name, a.name) having count(o.id) > 1
order by count(o.id) ASC
limit 3;

select * from show_books_which_buy_more_tnen_n_times;
