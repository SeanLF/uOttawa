set search_path=project;
drop table show;

create table show(
Shownumber integer not null, 
Showname name not null default 'show', 
description name default 'A show hosted by the best company in the world... us!', 
category name not null default 'Talk',
constraint show_pk primary key (shownumber)
);