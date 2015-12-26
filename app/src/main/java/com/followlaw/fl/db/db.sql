Province:
create table Province (
    id integer primary key autoincrement,
    province_name text,
    Province_code text)
City:
create table City (
    id integer primary key autoincrement,
    city_name text,
    city_code text,
    province_id integer)
County:
create table Country (
    in integer primary key autoincrement,
    county_name text,
    city_code text,
    city_id integer)