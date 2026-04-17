DROP DATABASE IF EXISTS AntiCape_db;
CREATE DATABASE AntiCape_db;
USE AntiCape_db;

DROP TABLE IF EXISTS Asistencia;
DROP TABLE IF EXISTS Citas;
DROP TABLE IF EXISTS Traje;
DROP TABLE IF EXISTS Taller;
DROP TABLE IF EXISTS Empleado;
DROP TABLE IF EXISTS Cliente;


CREATE TABLE Cliente (
  id_cliente integer PRIMARY KEY auto_increment,
  nombre varchar(255),
  colores varchar(255),
  superpoder varchar(255)
);

CREATE TABLE Traje (
  id_traje integer PRIMARY KEY auto_increment,
  id_cliente integer,
  nombre varchar(255),
  estado varchar(255)
);

CREATE TABLE Taller (
  id_taller integer PRIMARY KEY auto_increment,
  tipo_sala varchar(255),
  nombre_sala varchar(255)
);

CREATE TABLE Empleado (
  id_empleado integer PRIMARY KEY auto_increment,
  nombre varchar(255),
  apellidos varchar(255),
  apodo varchar(255),
  categoria varchar(255),
  contraseña varchar(255)
);

CREATE TABLE Citas (
  id_cita integer PRIMARY KEY auto_increment,
  fecha date,
  duracion varchar(255),
  id_cliente integer,
  id_encargado integer,
  id_taller integer,
  id_traje integer
);

CREATE TABLE Asistencia (
  id_empleado integer,
  id_cita integer
);

ALTER TABLE Traje ADD FOREIGN KEY (id_cliente) REFERENCES Cliente (id_cliente);

ALTER TABLE Citas ADD FOREIGN KEY (id_cliente) REFERENCES Cliente (id_cliente);

ALTER TABLE Citas ADD FOREIGN KEY (id_encargado) REFERENCES Empleado (id_empleado);

ALTER TABLE Citas ADD FOREIGN KEY (id_taller) REFERENCES Taller (id_taller);

ALTER TABLE Citas ADD FOREIGN KEY (id_traje) REFERENCES Traje (id_traje);

ALTER TABLE Asistencia ADD FOREIGN KEY (id_empleado) REFERENCES Empleado (id_empleado);

ALTER TABLE Asistencia ADD FOREIGN KEY (id_cita) REFERENCES Citas (id_cita);


INSERT INTO Cliente (id_cliente, nombre, colores, superpoder) VALUES(001, 'Spiderman', 'Rojo', 'Trepar paredes, sentido arácnido y lanzar telarañas');
INSERT INTO Cliente (id_cliente, nombre, colores, superpoder) VALUES(002, 'Batman', 'Negro', 'Inteligencia extrema, tecnología avanzada y habilidades de combate');
INSERT INTO Cliente (id_cliente, nombre, colores, superpoder) VALUES(003, 'Superman', 'Azul', 'Súper fuerza, vuelo, visión láser e invulnerabilidad');
INSERT INTO Cliente (id_cliente, nombre, colores, superpoder) VALUES(004, 'IronMan', 'Dorado', 'Armadura tecnológica con armas y capacidad de volar');
INSERT INTO Cliente (id_cliente, nombre, colores, superpoder) VALUES(005, 'Hulk', 'Verde', 'Fuerza ilimitada y resistencia extrema');
INSERT INTO Cliente (id_cliente, nombre, colores, superpoder) VALUES(006, 'Flah', 'Rojo', 'Súper velocidad');
INSERT INTO Cliente (id_cliente, nombre, colores, superpoder) VALUES(007, 'Thor', 'Plateado', 'Control del rayo y fuerza divina');
INSERT INTO Cliente (id_cliente, nombre, colores, superpoder) VALUES(008, 'ElasticGirl', 'Rojo', 'Estirar su cuerpo y cambiar de forma');
INSERT INTO Cliente (id_cliente, nombre, colores, superpoder) VALUES(009, 'Violet', 'Morado', 'Volverse invisible y crear campos de fuerza');
INSERT INTO Cliente (id_cliente, nombre, colores, superpoder) VALUES(010, 'Capitana Marvel', 'Rojo', 'Volar, absorber energía y súper fuerza');

INSERT INTO Traje(id_traje, nombre, estado) VALUES(001, 'principal', 'taller');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(002, 'específico', 'diseño');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(003, 'principal', 'costura');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(004, 'específico', 'taller');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(005, 'principal', 'diseño');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(006, 'específico', 'costura');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(007, 'principal', 'taller');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(008, 'específico', 'diseño');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(009, 'principal', 'costura');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(010, 'específico', 'taller');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(011, 'principal', 'diseño');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(012, 'específico', 'costura');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(013, 'principal', 'taller');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(014, 'específico', 'diseño');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(015, 'principal', 'costura');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(016, 'específico', 'taller');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(017, 'principal', 'diseño');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(018, 'específico', 'costura');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(019, 'principal', 'taller');
INSERT INTO Traje(id_traje, nombre, estado) VALUES(020, 'específico', 'diseño');


INSERT INTO Taller(id_taller, tipo_sala, nombre_sala) VALUES (001, 'Paris', 'diseño');
INSERT INTO Taller(id_taller, tipo_sala, nombre_sala) VALUES (002, 'Nueva York', 'diseño');
INSERT INTO Taller(id_taller, tipo_sala, nombre_sala) VALUES (003, 'Madrid', 'pruebas');
INSERT INTO Taller(id_taller, tipo_sala, nombre_sala) VALUES (004, 'Praga', 'pruebas');
INSERT INTO Taller(id_taller, tipo_sala, nombre_sala) VALUES (005, 'Londres', 'pruebas');
INSERT INTO Taller(id_taller, tipo_sala, nombre_sala) VALUES (006, 'Milan', 'costura');
INSERT INTO Taller(id_taller, tipo_sala, nombre_sala) VALUES (007, 'Tokio', 'costura');
INSERT INTO Taller(id_taller, tipo_sala, nombre_sala) VALUES (008, 'Barcelona', 'costura');

INSERT INTO Empleado(id_empleado, nombre, apellidos, apodo, categoria, contraseña) VALUES (001, 'Edna', 'Marie Mode', 'E.Moda', 'Maestro', 'admin1974');
INSERT INTO Empleado(id_empleado, nombre, apellidos, apodo, categoria, contraseña) VALUES (002, 'Valerio', 'Montclair', 'V.Montclair', 'Oficial', 'admin1978');
INSERT INTO Empleado(id_empleado, nombre, apellidos, apodo, categoria, contraseña) VALUES (003, 'Alessandro', 'Virelli', 'A.Virelli', 'Oficial', 'admin1982');
INSERT INTO Empleado(id_empleado, nombre, apellidos, apodo, categoria, contraseña) VALUES (004, 'Luca', 'Ferrán Vidal', 'L.F.Vidal', 'Aprendiz', 'admin2001');
INSERT INTO Empleado(id_empleado, nombre, apellidos, apodo, categoria, contraseña) VALUES (005, 'Noa', 'Beltrán Cruz', 'N.B.Cruz', 'Aprendiz', 'admin2003');
INSERT INTO Empleado(id_empleado, nombre, apellidos, apodo, categoria, contraseña) VALUES (006, 'Thiago', 'Rivas Soler', 'T.R.Soler', 'Aprendiz', 'admin2002');


INSERT INTO Citas (id_cita, fecha, duracion, id_cliente, id_encargado, id_taller, id_traje) 
VALUES (1, '2024-05-10', '2024-05-10 01:30:00', 001, 001, 001, 001);

INSERT INTO Citas (id_cita, fecha, duracion, id_cliente, id_encargado, id_taller, id_traje) 
VALUES (2, '2024-05-11', '2024-05-11 00:45:00', 002, 002, 003, 002);

INSERT INTO Citas (id_cita, fecha, duracion, id_cliente, id_encargado, id_taller, id_traje) 
VALUES (3, '2024-05-12', '2024-05-12 02:00:00', 003, 001, 006, 003);

INSERT INTO Citas (id_cita, fecha, duracion, id_cliente, id_encargado, id_taller, id_traje) 
VALUES (4, '2024-05-13', '2024-05-13 01:00:00', 004, 003, 002, 004);

INSERT INTO Citas (id_cita, fecha, duracion, id_cliente, id_encargado, id_taller, id_traje) 
VALUES (5, '2024-05-14', '2024-05-14 03:30:00', 008, 002, 001, 008);


