drop table if exists report;
drop table if exists deposit_trade;
drop table if exists qr_code;
drop table if exists account;
drop table if exists member_roles;
drop table if exists members;

create table if not exists members
(
    member_id          bigint primary key not null auto_increment,
    login_id           varchar(20)        not null unique,
    encrypted_pwd      varchar(100)       not null,
    name               varchar(20)        not null,
    phone_number       varchar(13)        not null unique,
    active             tinyint            not null,
    created_date       timestamp          not null default now(),
    last_modified_date timestamp          not null default now()
);

insert into members (login_id, encrypted_pwd, name, phone_number, active)
values ('ssafy', '$2a$10$wvYmQMOsyUTuRd98yBsdSuDDuOglYJEyo2Ciaijb53hmgCXhN7MQK', '김신한', '010-1234-5678', true),
       ('test', '$2a$10$u4xO.BTMpFZqbX.ypXpmLegYY8mSSUBveY7R0gBz.2T3woWJ/VKNa', '홍진식', '010-9051-1609', true);

create table if not exists member_roles
(
    member_member_id bigint,
    roles            varchar(255) default null,
    foreign key (member_member_id) references members (member_id)
);

insert into member_roles (member_member_id, roles)
values (1, 'MEMBER'),
       (2, 'STORE');

create table if not exists account
(
    account_id         bigint primary key not null auto_increment,
    member_id          bigint             not null,
    bank_code          varchar(5)         not null,
    account_number     varchar(14)        not null unique,
    balance            int                not null,
    product_name       varchar(20)        not null,
    customer_name      varchar(20)        not null,
    title              varchar(50) unique,
    main_account       tinyint            not null,
    active             tinyint            not null,
    created_date       timestamp          not null default now(),
    last_modified_date timestamp          not null default now(),
    foreign key (member_id) references members (member_id)
);

insert into account (member_id, bank_code, account_number, balance, product_name, customer_name, title, main_account,
                     active)
values (1, '088', '110184999999', 33155100, '예금', '김신한', '싸피월급통장', true, true),
       (2, '088', '110290999999', 9400000, '예금', '홍진식', '가게운영통장', true, true);

create table if not exists qr_code
(
    qr_code_id         bigint primary key not null auto_increment,
    account_id         bigint             not null,
    url                varchar(255)       not null,
    amount             int                not null,
    title              varchar(30)        not null,
    base64_qr_code     varchar(10000)     not null,
    active             tinyint            not null,
    created_date       timestamp          not null default now(),
    last_modified_date timestamp          not null default now(),
    foreign key (account_id) references account (account_id)
);

insert into qr_code (account_id, url, amount, title, base64_qr_code, active)
values (2, 'https://j9c205.ssafy.io/remittance/deposit?qrCodeId=1', 20000, '럭셔리 글램핑 객실이용',
        'iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAIAAAAiOjnJAAAJpklEQVR4Xu2SQY4jSQwD50X7lH3efncWeTFgKhFVKknpdoMBnpqkstrgn7/GDPBH/2BMBx6WGcHDMiN4WGYED8uM4GGZETwsM4KHZUbwsMwIHpYZwcMyI3hYZgQPy4zgYZkRPCwzgodlRvCwzAgelhnBwzIjeFhmBA/LjOBhmRE8LDOCh2VG8LDMCB6WGaF/WP/8+98Z6cPvcJjdFPHDHmvuMkvebcHD2rgp4oc91txllrzbgoe1cVPED3usucssebcFD2vjpogf9lhzl1nybgse1sZNET/sseYus+TdFsaHpXYBvhx/L5B0mViHU+wKHGa3wtzlFx7WNbEOp9gVOMxuhbnLLzysa2IdTrErcJjdCnOXX3hY18Q6nGJX4DC7FeYuv/Cwrol1OMWuwGF2K8xdfnF6WOKyuCuu0BgWlyXdCnw5Pg3irrgteFgLDovLkm4FvhyfBnFX3BY8rAWHxWVJtwJfjk+DuCtuCx7WgsPisqRbgS/Hp0HcFbcFD2vBYXFZ0q3Al+PTIO6K28LvGRa7QnzrsSqXpStwOF4DcVfcFjysqiqXpStwOF4DcVfcFjysqiqXpStwOF4DcVfcFjysqiqXpStwOF4DcVfcFjysqiqXpStwOF4DcVfcFn7PsJi5rrgV8WV2WdwVtwUP6xruilsRX2aXxV1xW/CwruGuuBXxZXZZ3BW3BQ/rGu6KWxFfZpfFXXFb8LCu4a64FfFldlncFbeF08OqwJfFZUk3RbwG0nKGxlPC3OUXHlaaeA2k5QyNp4S5yy88rDTxGkjLGRpPCXOXX3hYaeI1kJYzNJ4S5i6/8LDSxGsgLWdoPCXMXX4xPqw58bu/z52TvNuCh/U17pzk3RY8rK9x5yTvtuBhfY07J3m3BQ/ra9w5ybst9A/rhxB/vqGfMnU5fsn97nfhYVVJXY5fcr/7XXhYVVKX45fc734XHlaV1OX4Jfe734WHVSV1OX7J/e530T+s+Hs9VuWydJlYB2n5HQ7/THcCD2sR6yAtv8Phn+lO4GEtYh2k5Xc4/DPdCTysRayDtPwOh3+mO4GHtYh1kJbf4fDPdCfoH5Yg/xL/h+wy8fh98SlxK6Quc5hdptK9iYe1xKfErZC6zGF2mUr3Jh7WEp8St0LqMofZZSrdm3hYS3xK3Aqpyxxml6l0b+JhLfEpcSukLnOYXabSvcn4sFLwP8yuUAnPKfWuhL8LD2sh4Tml3pXwd+FhLSQ8p9S7Ev4uPKyFhOeUelfC34WHtZDwnFLvSvi76B9W/IGGfiy+HJ+GMBPrIO6KK1TC3GV3Ag/rmlgHcVdcoRLmLrsTeFjXxDqIu+IKlTB32Z3Aw7om1kHcFVeohLnL7gQe1jWxDuKuuEIlzF12J+gfFhN/AhB32eWwEPP3xafYZXGXXQ4fwMNaxPx98Sl2Wdxll8MH8LAWMX9ffIpdFnfZ5fABPKxFzN8Xn2KXxV12OXwAD2sR8/fFp9hlcZddDh/gm4bFpLqN4ZTL4m7KZSrdm3hY1XDKZXE35TKV7k08rGo45bK4m3KZSvcmHlY1nHJZ3E25TKV7Ew+rGk65LO6mXKbSvUn/sOSj+X9IualwyhU4LC6HG+GH4pdA+AAe1gYOi8vhRvih+CUQPoCHtYHD4nK4EX4ofgmED+BhbeCwuBxuhB+KXwLhA3hYGzgsLocb4Yfil0D4AKeHxf9wDNwPs8uSrpAKM/Hpx5dT3VS4BQ9r0xVSYSY+/fhyqpsKt+BhbbpCKszEpx9fTnVT4RY8rE1XSIWZ+PTjy6luKtyCh7XpCqkwE59+fDnVTYVb6B8WE39NUKqbCjeK3xWXaexWTrXgYVXF74rLNHYrp1rwsKrid8VlGruVUy14WFXxu+Iyjd3KqRY8rKr4XXGZxm7lVAv9w4r/1dB/WLnMXXYFCX9K/FUptwUPa9NlV5Dwp8RflXJb8LA2XXYFCX9K/FUptwUPa9NlV5Dwp8RflXJb8LA2XXYFCX9K/FUpt4XxYan9joRTqpySbopjp+Jng7gr7gE8rDTHTsXPBnFX3AN4WGmOnYqfDeKuuAfwsNIcOxU/G8RdcQ/gYaU5dip+Noi74h7g9LAqbiPyEL9bcVPELxm6rPYAHtZGHE65KeKXDF1WewAPayMOp9wU8UuGLqs9gIe1EYdTbor4JUOX1R7Aw9qIwyk3RfySoctqD3B6WCnijwuXYwDCQsyDtPxOJVxR5bJ0W/CwFjEP0vI7lXBFlcvSbcHDWsQ8SMvvVMIVVS5LtwUPaxHzIC2/UwlXVLks3RY8rEXMg7T8TiVcUeWydFvoH9aniL9X12/Hp+JbIO6Ky8Tjj6WnO/CwruFT8S0Qd8Vl4vHH0tMdeFjX8Kn4Foi74jLx+GPp6Q48rGv4VHwLxF1xmXj8sfR0Bx7WNXwqvgXirrhMPP5YerqD/mHF7x5S6l0JCxyO10BzXXZZ0j2Ah7XgcLwGmuuyy5LuATysBYfjNdBcl12WdA/gYS04HK+B5rrssqR7AA9rweF4DTTXZZcl3QOMD0vtAqnLHBaXw0IqLMS3uk6p/Wk8rI0kLKTCQnyr65Tan8bD2kjCQiosxLe6Tqn9aTysjSQspMJCfKvrlNqfxsPaSMJCKizEt7pOqf1pTg8r/pog7rLLkq7A4YorSLgivizuATysDRyuuIKEK+LL4h7Aw9rA4YorSLgivizuATysDRyuuIKEK+LL4h7Aw9rA4YorSLgivizuAX7tsNidC4srxGuPu+ymwuK24GE1h8UV4rXHXXZTYXFb8LCaw+IK8drjLrupsLgteFjNYXGFeO1xl91UWNwWPKzmsLhCvPa4y24qLG4Lv3ZYLOkKMQ/Sch/8UPyS+5JTE3hYG2IepOU++KH4JfclpybwsDbEPEjLffBD8UvuS05N4GFtiHmQlvvgh+KX3JecmsDD2hDzIC33wQ/FL7kvOTXB6WFV4MvsMtwVl1XpiuQUw112J/CwFtwVl1XpiuQUw112J/CwFtwVl1XpiuQUw112J/CwFtwVl1XpiuQUw112J/CwFtwVl1XpiuQUw112Jxgf1pz4XXFTxLceiy+LK6TCQqXbgoe1Ib71WHxZXCEVFirdFjysDfGtx+LL4gqpsFDptuBhbYhvPRZfFldIhYVKtwUPa0N867H4srhCKixUui30D8uYvx6WGcLDMiN4WGYED8uM4GGZETwsM4KHZUbwsMwIHpYZwcMyI3hYZgQPy4zgYZkRPCwzgodlRvCwzAgelhnBwzIjeFhmBA/LjOBhmRE8LDOCh2VG8LDMCB6WGcHDMiN4WGaE/wGov8dp6c+QsAAAAABJRU5ErkJggg==',
        true);

create table if not exists deposit_trade
(
    deposit_trade_id   bigint primary key not null auto_increment,
    qr_code_id         bigint             not null,
    account_id         bigint             not null,
    summary            varchar(100)       not null,
    withdrawal_amount  int                not null,
    deposit_amount     int                not null,
    content            varchar(100)       not null,
    balance            int                not null,
    status             varchar(255)       not null,
    dealership_name    varchar(100)       not null,
    created_date       timestamp          not null default now(),
    last_modified_date timestamp          not null default now(),
    foreign key (qr_code_id) references qr_code (qr_code_id),
    foreign key (account_id) references account (account_id)
);

insert into deposit_trade (qr_code_id, account_id, summary, withdrawal_amount, deposit_amount, content, balance, status,
                           dealership_name)
values (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WAIT', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'WDONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요'),
       (1, 1, '이체', 20000, 20000, '김신한', 33155100, 'DONE', '챙겨요')
       ;

create table if not exists report
(
    report             bigint primary key not null auto_increment,
    member_id          bigint             not null,
    deposit_trade_id   bigint             not null,
    reason             varchar(50)        not null,
    description        varchar(100)       not null,
    fee                int                not null,
    amount             int                not null,
    created_date       timestamp          not null default now(),
    last_modified_date timestamp          not null default now(),
    foreign key (member_id) references members (member_id),
    foreign key (deposit_trade_id) references deposit_trade (deposit_trade_id)
);