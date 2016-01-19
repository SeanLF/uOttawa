set search_path=project;

create table HostsShow(
Contractnum integer not null default 1, 
empnum integer references host(enumb), 
shownum integer references show(shownumber), 
hoststartyear integer default 1970,
hoststartmonth integer default 1,
constraint hostshow_pk primary key (contractnum));