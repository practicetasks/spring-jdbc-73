drop table if exists continents cascade;
drop table if exists countries cascade;
drop table if exists cities cascade;
drop table if exists languages cascade;
drop table if exists countries_languages cascade;

create table if not exists continents
(
    id   bigserial primary key,
    name varchar(50) not null
);

create table if not exists countries
(
    id           bigserial primary key,
    name         varchar(50)                       not null,
    continent_id bigint references continents (id) not null
);

create table if not exists cities
(
    id         bigserial primary key,
    name       varchar(50)                      not null,
    population bigint                           not null,
    country_id bigint references countries (id) not null
);

create table if not exists languages
(
    id   serial primary key,
    name varchar(50) not null unique
);

create table if not exists countries_languages
(
    country_id  integer references countries (id) not null,
    language_id integer references languages (id) not null,
    primary key (country_id, language_id)
);
