CREATE TABLE project.user
(
  username name NOT NULL,
  email name not null,
  country name not null,
  password name not null,
  CONSTRAINT user_pkey PRIMARY KEY (username, email)
)