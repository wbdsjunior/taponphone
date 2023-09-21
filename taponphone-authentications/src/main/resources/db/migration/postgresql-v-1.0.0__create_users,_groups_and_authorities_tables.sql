CREATE TABLE users (
   username CHARACTER VARYING(256) NOT NULL
 , password CHARACTER VARYING(128) NOT NULL
 , enabled BOOLEAN NOT NULL DEFAULT FALSE
 , PRIMARY KEY (username)
);

INSERT INTO users VALUES ('test', '{bcrypt}$2a$10$GqXvkA0JaRyXlFKshyG7mOqyIpgRORhUiYjQ6n0iE35qZK9ITMc6G', TRUE); -- test:test

CREATE TABLE authorities (
   authority CHARACTER VARYING(256) NOT NULL
 , username CHARACTER VARYING(256) NOT NULL
 , FOREIGN KEY (username) REFERENCES users (username)
);

INSERT INTO authorities VALUES ('user', 'test');

CREATE UNIQUE INDEX ON authorities (username, authority);

CREATE TABLE groups (
   id BIGSERIAL
 , group_name CHARACTER VARYING(256) NOT NULL
 , PRIMARY KEY (id)
 , UNIQUE (group_name)
);

CREATE TABLE group_authorities (
   group_id BIGINT NOT NULL
 , authority CHARACTER VARYING(256) NOT NULL
 , foreign key (group_id) references groups(id)
);

CREATE UNIQUE INDEX ON group_authorities (group_id, authority);

CREATE TABLE group_members (
   id BIGSERIAL
 , group_id BIGINT NOT NULL
 , username CHARACTER VARYING(256) NOT NULL
 , foreign key (group_id) references groups (id)
 , foreign key (username) references users (username)
 , PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ON group_members (group_id, username);
