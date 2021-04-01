drop table if exists team_coder;
drop table if exists teams;
drop table if exists coders;
drop table if exists clients;

--
create table clients (
    client_id integer auto_increment primary key,
    name varchar(25) not null,
    nickname varchar(10)
);

insert into clients (name, nickname) values('Aleph Microservices', 'Alpha');
insert into clients (name, nickname) values('Heavy Bertha Industries', 'Beta');
insert into clients (name, nickname) values('Teragamma Consulting', 'Gamma');
insert into clients (name, nickname) values('Lima Delta Services', 'Delta');

--
create table coders (
    coder_id integer auto_increment primary key,
    first_name varchar(20),
    last_name varchar(25) not null,
    hire_date date not null,
    salary decimal(8,2),

    constraint coders_name_uq unique(first_name, last_name)
);

insert into coders (coder_id, first_name, last_name, hire_date, salary)
	values (103,'Alexander','Hunold', '2006-01-03', 9000.00);
insert into coders (coder_id, first_name, last_name, hire_date, salary)
	values (104, 'Bruce', 'Ernst', '2007-05-21', 6000.00);	
insert into coders (coder_id, first_name, last_name, hire_date, salary)
	values (105, 'David', 'Austin', '2005-06-25', 4800.00);
insert into coders (coder_id, first_name, last_name, hire_date, salary)
	values (106, 'Valli', 'Pataballa', '2006-02-05', 4800.00);
insert into coders (coder_id, first_name, last_name, hire_date, salary)
	values (107, 'Diana', 'Lorentz', '2007-02-07', 4200.00);

insert into coders (coder_id, first_name, last_name, hire_date, salary)
	values (201, 'Tim', 'Ice', TODAY, 5760);

--
create table teams(
    team_id integer auto_increment primary key,
    name varchar(25),
    leader_id integer unique,
    client_id integer not null,

    constraint teams_leader_fk foreign key(leader_id) references coders(coder_id) on delete cascade,
    constraint teams_client_fk foreign key(client_id) references clients(client_id)
);

insert into teams (name, leader_id, client_id) values ('red', 103, 1);
insert into teams (name, leader_id, client_id) values ('blue', 107, 1);
insert into teams (name, leader_id, client_id) values ('green', 105, 2);

--
create table team_coder(
    team_id integer,
    coder_id integer,

    constraint team_coder_pk primary key(team_id, coder_id),
    constraint team_coder_fk foreign key(team_id) references teams(team_id) on delete cascade,
    constraint coder_team_fk foreign key(coder_id) references coders(coder_id) on delete cascade
);

insert into team_coder values (1, 104);
insert into team_coder values (1, 106);
insert into team_coder values (1, 201);
insert into team_coder values (2, 105);
insert into team_coder values (2, 106);
insert into team_coder values (2, 107);
insert into team_coder values (3, 105);
insert into team_coder values (3, 106);
insert into team_coder values (3, 103);
