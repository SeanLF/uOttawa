set search_path=project;
drop table host;

create table host(
enumb integer NOT NULL DEFAULT 1, 
firstname name, 
lastname name, 
stagename name UNIQUE,
dob date NOT NULL, 
rating integer, 
contractsd date NOT NULL, 
salary numeric NOT NULL DEFAULT 9999.99,
CONSTRAINT employee_PK PRIMARY KEY (enumb),
check(rating>0), check(rating<10)
)