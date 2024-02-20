drop table person;
drop table news;

create table person (
                        id int primary key generated by default as identity,
                        username varchar(255) not null unique,
                        password varchar(255) not null,
                        full_name varchar(255),
                        salary int,
                        role varchar(100)
);
create table news (
                      id int primary key generated by default as identity,
                      name varchar(255) not null,
                      description varchar(1024),
                      created date not null

);
