set search_path=project;

create table Timeslot(
Timeslotnumber integer not null, 
starttime time default '00:00:00', 
endtime time default '24:00:00', 
Shownumber integer references show(shownumber), 
Playsheetnumber integer references playsheet(playsheetnum),
constraint timeslot_pk primary key (timeslotnumber)
);