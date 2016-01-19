set search_path=project;

create table Guest(
Guestnumber integer default 1, 
firstname name not null, 
lastname name not null, 
description name, 
topic name, 
rating integer default 5,
 check (rating >0),
 check(rating <10),
timeslotnumber integer references timeslot(timeslotnumber),
constraint guest_pk primary key (guestnumber)
);