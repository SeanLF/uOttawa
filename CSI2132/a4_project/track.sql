set search_path=project;

create table Track(
Trackid integer not null default 1,
title name not null ,
starttime time default '00:00:00' not null,
endtime time default '00:03:30' not null,
type name,
songid integer references song(songid),
constraint track_pk primary key (trackid));