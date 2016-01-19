set search_path=project;

create table Album(
Albumid integer not null, 
type name, 
label name, 
albumrecordingdate date,
constraint albumid_pk primary key(albumid));