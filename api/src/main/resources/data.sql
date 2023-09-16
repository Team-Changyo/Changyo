drop table if exists trade;
drop table if exists transfer;
drop table if exists account;

create table account
(
    account_id         bigint primary key not null auto_increment,
    account_number     varchar(100)       not null,
    balance            int                not null default 0,
    product_name       varchar(100)       not null,
    customer_name      varchar(100)       not null,
    bank_code          varchar(10)        not null,
    created_date       timestamp          not null default now(),
    last_modified_date timestamp          not null default now()
);

insert into account(account_number, balance, bank_code, customer_name, product_name)
values ('110184999999', 33155100, '088', '김신한', '예금'),
       ('110280999999', 1000000, '088', '전인혁', '예금'),
       ('110281999999', 2700000, '088', '전인혁', '예금'),
       ('110282999999', 4100000, '088', '전인혁', '예금'),
       ('110283999999', 5300000, '088', '최영환', '예금'),
       ('110284999999', 6700000, '088', '최영환', '예금'),
       ('110285999999', 7800000, '088', '최영환', '예금'),
       ('110286999999', 7900000, '088', '임우택', '예금'),
       ('110287999999', 8100000, '088', '임우택', '예금'),
       ('110288999999', 8300000, '088', '임우택', '예금'),
       ('110289999999', 9000000, '088', '홍진식', '예금'),
       ('110290999999', 9400000, '088', '홍진식', '예금'),
       ('110291999999', 9800000, '088', '홍진식', '예금')
       ('110112999999', 2100000000, '088', '챙겨요', '예금');


create table trade
(
    trade_id           bigint primary key not null auto_increment,
    account_id         bigint             not null,
    trade_date_time    timestamp          not null,
    summary            varchar(100)       not null,
    withdrawal_amount  int                not null,
    deposit_amount     int                not null,
    content            varchar(100)       not null,
    balance            int                not null,
    status             int                not null,
    dealership_name    varchar(100)       not null,
    created_date       timestamp          not null default now(),
    last_modified_date timestamp          not null default now(),
    foreign key (account_id) references account (account_id)
);

insert into trade(account_id, trade_date_time, summary, withdrawal_amount, deposit_amount, content, balance, status,
                  dealership_name)
values (1, '2023-03-18 15:46:02', '이자', 0, 1404, '12.17~03.17', 331551, 1, '영업부'),
       (1, '2023-03-02 17:54:43', '신한체크', 11000, 0, '김밥천국', 330147, 2, '신한'),
       (1, '2023-02-20 14:37:53', '모바일', 11000000, 0, '전세보증금', 341147, 2, '영업부'),
       (1, '2023-02-05 22:28:28', '신한체크', 4700, 0, '스타벅스', 11341147, 2, '신한'),
       (1, '2023-02-01 08:27:36', '신한체크', 18000, 0, '티머니', 11345847, 2, '신한');

create table transfer
(
    transfer_id           bigint primary key not null auto_increment,
    deposit_account_id    bigint             not null,
    withdrawal_account_id bigint             not null,
    amount                int                not null,
    deposit_memo          varchar(100)       null,
    withdrawal_memo       varchar(100)       null,
    result                int                not null,
    created_date          timestamp          not null default now(),
    last_modified_date    timestamp          not null default now(),
    foreign key (deposit_account_id) references account (account_id),
    foreign key (withdrawal_account_id) references account (account_id)
);
