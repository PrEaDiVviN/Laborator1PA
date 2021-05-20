CREATE TABLE persoane ( id INT AUTO_INCREMENT PRIMARY KEY, nume VARCHAR(50), prenume VARCHAR(70), varsta INT);
CREATE TABLE relatii (id_rel INT AUTO_INCREMENT PRIMARY, id_prima_persoana INT, id_a_doua_persoana INT, tipul_relatiei VARCHAR(50) NOT NULL,
                        FOREIGN KEY (id_prima_persoana) REFERENCES persoane(id) ON DELETE CASCADE,
                        FOREIGN KEY (id_a_doua_persoana) REFERENCES persoane(id) ON DELETE CASCADE);