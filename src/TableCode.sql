create database OOPCA4

drop table if exists Expenses;
drop table if exists Income;

create table Expenses (
    id int not null AUTO_INCREMENT,
    title varchar(60) not null,
    category varchar(30) not null,
    amount double not null,
    incurred date not null,
    primary key (id)
);

create table Income (
    id int not null AUTO_INCREMENT,
    title varchar(60) not null,
    amount double not null,
    earned date not null,
    primary key (id)
);

insert into Expenses(title, category, amount, incurred) values
    ('Weekly shop', 'Groceries', 47.50, "2025-01-25"),
	('Gym membership', 'Fitness', 30.79, "2025-01-09"),
    ('Electricity bill', 'Bill', 25.17, "2025-02-14");

insert into Income(title, amount, earned) values
    ('Babysitting', 60.18, '2025-01-14'),
	('Bar work', 100.87, '2024-02-09'),
    ('Birthday', 20.13, '2024-03-29');

ALTER TABLE Expenses MODIFY COLUMN title VARCHAR(20) NOT NULL;
ALTER TABLE Expenses MODIFY COLUMN category VARCHAR(20) NOT NULL;
ALTER TABLE Income MODIFY COLUMN title VARCHAR(20) NOT NULL;