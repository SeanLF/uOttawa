set search_path=project;

create table Artist(
Artistid integer not null default 1, 
firstname name not null, 
lastname name not null,
stagename name,
nationality name,
dob date,
constraint artist_pk primary key (artistid));