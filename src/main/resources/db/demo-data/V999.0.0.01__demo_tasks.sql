insert into category values(1, 0, 'Uncategorized', 1);
insert into category values(2, 0, 'School', 1);
insert into category values(3, 0, 'Work', 1);

insert into category values(4, 0, 'Uncategorized', 2);
insert into category values(5, 0, 'Home', 2);
insert into category values(6, 0, 'Work', 2);

insert into task values (1, 0, 'Task1', 'description1', 1, 1, TRUE, 1, 0);
insert into task values (2, 0, 'Task2', 'description2', 4, 1, TRUE, 1, 1);
insert into task values (3, 0, 'Task3', 'description3', 2, 2, FALSE, 1, 0);
insert into task values (4, 0, 'Task4', 'description4', 3, 3, FALSE, 1, 0);

insert into task values (5, 0, 'Task5', 'description5', 5, 4, TRUE, 2, 0);
insert into task values (6, 0, 'Task6', 'description5', 5, 5, FALSE, 2, 0);
insert into task values (7, 0, 'Task7', 'description5', 5, 5, FALSE, 2, 1);
insert into task values (8, 0, 'Task8', 'description5', 5, 6, FALSE, 2, 0);