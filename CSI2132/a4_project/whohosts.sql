create table project.whohosts(
host integer references project.host(enumb),
show integer references project.show(shownumber),
constraint who_pk primary key (host, show)
);