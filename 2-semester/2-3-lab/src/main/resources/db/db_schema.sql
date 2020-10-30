create table rooms (
    id          uuid primary key,
    number      varchar(32) not null,
    seat_number integer not null,
    type        varchar(128) not null,
    status      varchar(128) not null,
    price       float not null
);

create table requests (
    id          uuid primary key,
    seat_number integer not null,
    type        varchar(32) not null,
    date_from   timestamp not null,
    date_to     timestamp not null
);

create table orders (
    id            uuid primary key,
    creation_date timestamp not null,
    price         float not null,
    room_number   varchar(32) not null,
    type          varchar(128) not null,
    date_from     timestamp not null,
    date_to       timestamp not null
);

alter table requests add column phone varchar(10);
alter table orders add column phone varchar(10);
alter table orders add column paid boolean;
