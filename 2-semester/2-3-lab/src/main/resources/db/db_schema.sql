create table rooms (
    id          uuid primary key,
    number      varchar(32) not null,
    seat_number integer not null,
    type        varchar(128) not null,
    status      varchar(128) not null,
    price       float not null
);