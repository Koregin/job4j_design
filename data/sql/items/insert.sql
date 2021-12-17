insert into roles(name) values ('user');
insert into roles(name) values ('admin');
insert into users(name, role_id) values ('Ivanov', 1);
insert into users(name, role_id) values ('Petrov', 2);
insert into rules(name) values ('create');
insert into rules(name) values ('delete');
insert into rules(name) values ('change');
insert into roles_to_rules(role_id, rule_id) values (1, 1);
insert into roles_to_rules(role_id, rule_id) values (2, 1);
insert into roles_to_rules(role_id, rule_id) values (2, 2);
insert into roles_to_rules(role_id, rule_id) values (2, 3);

insert into categories(name) values ('IT');
insert into categories(name) values ('SALES');

insert into states(name) values ('new');
insert into states(name) values ('in process');
insert into states(name) values ('closed');

insert into items(name, user_id, category_id, state_id) values ('Install new program', 1, 1, 1);
insert into items(name, user_id, category_id, state_id) values ('Buy new server', 2, 2, 1);
insert into comments(text, item_id) values ('It should be done before Friday', 1);
insert into comments(text, item_id) values ('Core-i7, SSD500Gb, 32GB RAM', 2);