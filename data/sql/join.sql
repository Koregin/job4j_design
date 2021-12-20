create table departments(
    id serial primary key,
    name varchar(255)
);

create table emploers(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('IT');
insert into departments(name) values ('SALES');
insert into departments(name) values ('MANAGE');
insert into departments(name) values ('SCIENCE');

insert into emploers(name, department_id) values ('Ivanov', 1);
insert into emploers(name, department_id) values ('Petrov', 2);
insert into emploers(name, department_id) values ('Sidorov', 3);
insert into emploers(name, department_id) values ('Sokrat', null);
insert into emploers(name, department_id) values ('Seneka', null);
insert into emploers(name, department_id) values ('Epiktet', null);

-- 2. Выполнить запросы с left, rigth, full, cross соединениями
select *
from emploers e
left join departments d on d.id = e.department_id;

select *
from emploers e
right join departments d on d.id = e.department_id;

select  *
from emploers e
full join departments d on d.id = e.department_id;

select *
from emploers e
cross join departments d;

-- 3. Используя left join найти департаменты, у которых нет работников --
select *
from departments d
left join emploers e on d.id = e.department_id where e.department_id is null;

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат. --
select e.name, d.name
from emploers e
left join departments d on d.id = e.department_id;

select e.name, d.name
from departments d
right join emploers e on d.id = e.department_id;

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(10)
);

insert into teens(name, gender) values ('Ivan', 'male');
insert into teens(name, gender) values ('Svetlana', 'female');
insert into teens(name, gender) values ('Petr', 'male');
insert into teens(name, gender) values ('Olga', 'female');

select t1.name, t2.name
from teens t1
cross join teens t2 where t1.gender != t2.gender;