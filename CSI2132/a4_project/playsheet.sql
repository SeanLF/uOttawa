set search_path=project;

create table PlaySheet(
Playsheetnum integer, 
date date, 
dayofweek name,
constraint playsheet_pk primary key (playsheetnum) );