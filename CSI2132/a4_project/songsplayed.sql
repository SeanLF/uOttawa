CREATE TABLE project.songsplayed
(
  playsheet integer NOT NULL,
  song integer NOT NULL,
  CONSTRAINT songplayed_pkey PRIMARY KEY (playsheet, song),
  CONSTRAINT songsplayed_playsheet_fkey FOREIGN KEY (playsheet)
      REFERENCES project.playsheet (playsheetnum) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT songsplayed_song_fkey FOREIGN KEY (song)
      REFERENCES project.song (songid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)