set search_path=project;

create table Song(
Songid integer not null default 1, 
title name not null, 
cancon boolean, 
instrumental boolean, 
albumid integer references album(albumid), 
artistid integer references artist(artistid),
constraint song_pk primary key (songid));