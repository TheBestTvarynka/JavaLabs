
insert into rooms values
(uuid_generate_v4(), '1-01', 4, 'ROOM', 'AVAILABLE', 3.26),
(uuid_generate_v4(), '1-02', 4, 'ROOM', 'AVAILABLE', 3.26),
(uuid_generate_v4(), '1-03', 6, 'ROOM', 'AVAILABLE', 3.26),
(uuid_generate_v4(), '1-04', 4, 'ROOM', 'AVAILABLE', 3.26),
(uuid_generate_v4(), '1-05', 8, 'ROOM', 'AVAILABLE', 3.26);

insert into rooms values
(uuid_generate_v4(), '2-01', 2, 'VIP', 'AVAILABLE', 9.32),
(uuid_generate_v4(), '2-02', 2, 'VIP', 'BOOKED', 9.32),
(uuid_generate_v4(), '2-03', 3, 'VIP', 'BOOKED', 9.32),
(uuid_generate_v4(), '2-04', 3, 'VIP', 'AVAILABLE', 9.32),
(uuid_generate_v4(), '2-05', 3, 'VIP', 'AVAILABLE', 9.32);

insert into rooms values
(uuid_generate_v4(), '3-01', 1, 'PRESIDENT', 'BOOKED', 12),
(uuid_generate_v4(), '3-02', 1, 'PRESIDENT', 'AVAILABLE', 12),
(uuid_generate_v4(), '3-03', 1, 'PRESIDENT', 'AVAILABLE', 12),
(uuid_generate_v4(), '3-04', 1, 'PRESIDENT', 'REPAIR', 12),
(uuid_generate_v4(), '3-05', 1, 'PRESIDENT', 'REPAIR', 12);
