set search_path = 'project';

select *
from artist AR, album A, song S
where S.artistid = AR.artistid and S.albumid=A.albumid