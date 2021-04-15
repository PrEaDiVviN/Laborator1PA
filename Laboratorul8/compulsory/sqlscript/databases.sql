DROP TABLE linkmovtogen;
DROP TABLE movies;
DROP TABLE genres;

CREATE TABLE movies(id int PRIMARY KEY, titlu VARCHAR(30) NOT NULL UNIQUE, release_date DATE NOT NULL, duration TIME NOT NULL, score int);
CREATE TABLE genres(id int PRIMARY KEY, titlu VARCHAR(30) NOT NULL UNIQUE);
CREATE TABLE linkmovtogen(id_mov int, id_gen int, FOREIGN KEY (id_mov) REFERENCES movies(id), FOREIGN KEY (id_gen) REFERENCES genres(id));