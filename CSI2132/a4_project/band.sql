set search_path=project;

create table Band(
Artistid integer references artist(artistid), 
name name, 
startyear integer default 1970, 
nationality name,
constraint band_pk primary key (artistid));