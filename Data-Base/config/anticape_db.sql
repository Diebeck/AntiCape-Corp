DROP DATABASE IF EXISTS AntiCape_db;
CREATE DATABASE AntiCape_db;
USE AntiCape_db;

CREATE TABLE Cliente (
  id_cliente integer PRIMARY KEY auto_increment,
  nombre varchar(255),
  colores varchar(255),
  superpoder varchar(255),
  alineacion varchar(255)
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
  hora time,
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

-- referencia del traje a la cita
ALTER TABLE Citas ADD FOREIGN KEY (id_traje) REFERENCES Traje (id_traje);

-- Si se elimina un cliente, se eliminan sus trajes
ALTER TABLE Traje ADD FOREIGN KEY (id_cliente) REFERENCES Cliente (id_cliente) ON DELETE CASCADE;

-- Si se elimina un cliente, se eliminan sus citas
ALTER TABLE Citas ADD FOREIGN KEY (id_cliente) REFERENCES Cliente (id_cliente) ON DELETE CASCADE;

-- No se puedden eliminar empleados encargados de una cita
ALTER TABLE Citas ADD FOREIGN KEY (id_encargado) REFERENCES Empleado (id_empleado) ON DELETE RESTRICT;

-- No se puede eliminar un taller con citas asignadas
ALTER TABLE Citas ADD FOREIGN KEY (id_taller) REFERENCES Taller (id_taller) ON DELETE RESTRICT;

-- Si se elimina un empleado asistente, se elimina su registro en asistencia
ALTER TABLE Asistencia ADD FOREIGN KEY (id_empleado) REFERENCES Empleado (id_empleado) ON DELETE CASCADE;

-- Si se elimina una cita, se eliminan sus registros en asistencia
ALTER TABLE Asistencia ADD FOREIGN KEY (id_cita) REFERENCES Citas (id_cita) ON DELETE CASCADE;

-- Clientes
INSERT INTO Cliente (id_cliente, nombre, colores, superpoder, alineacion) VALUES 
(001, 'Spiderman', 'Rojo', 'Trepar paredes, sentido arácnido y lanzar telarañas', 'Heroe'),
(002, 'Batman', 'Negro', 'Inteligencia extrema, tecnología avanzada y habilidades de combate', 'Heroe'),
(003, 'Superman', 'Azul', 'Súper fuerza, vuelo, visión láser e invulnerabilidad', 'Heroe'),
(004, 'IronMan', 'Dorado', 'Armadura tecnológica con armas y capacidad de volar', 'Heroe'),
(005, 'Hulk', 'Verde', 'Fuerza ilimitada y resistencia extrema', 'Heroe'),
(006, 'Flash', 'Rojo', 'Súper velocidad', 'Heroe'),
(007, 'Thor', 'Plateado', 'Control del rayo y fuerza divina', 'Heroe'),
(008, 'ElasticGirl', 'Rojo', 'Estirar su cuerpo y cambiar de forma', 'Heroe'),
(009, 'Violet', 'Morado', 'Volverse invisible y crear campos de fuerza', 'Heroe'),
(010, 'Capitana Marvel', 'Rojo', 'Volar, absorber energía y súper fuerza', 'Heroe');

-- Trajes
INSERT INTO Traje(id_cliente, nombre, estado) VALUES 
(001, 'principal', 'taller'),
(001, 'específico', 'diseño'),
(003, 'principal', 'costura'),
(004, 'específico', 'taller'),
(005, 'principal', 'diseño'),
(006, 'específico', 'costura'),
(007, 'principal', 'taller'),
(008, 'específico', 'diseño'),
(009, 'principal', 'costura'),
(010, 'específico', 'taller');

-- Talleres
INSERT INTO Taller(id_taller, nombre_sala, tipo_sala) VALUES 
(001, 'Paris', 'diseño'),
(002, 'Nueva York', 'diseño'),
(003, 'Madrid', 'pruebas'),
(004, 'Praga', 'pruebas'),
(005, 'Londres', 'pruebas'),
(006, 'Milan', 'costura'),
(007, 'Tokio', 'costura'),
(008, 'Barcelona', 'costura');

-- Empleados
INSERT INTO Empleado(id_empleado, nombre, apellidos, apodo, categoria, contraseña) VALUES 
(001, 'Edna', 'Marie Mode', 'E.Moda', 'Maestro', 'admin1974'),
(002, 'Valerio', 'Montclair', 'V.Montclair', 'Oficial', 'admin1978'),
(003, 'Alessandro', 'Virelli', 'A.Virelli', 'Oficial', 'admin1982'),
(004, 'Luca', 'Ferrán Vidal', 'L.F.Vidal', 'Aprendiz', 'admin2001'),
(005, 'Noa', 'Beltrán Cruz', 'N.B.Cruz', 'Aprendiz', 'admin2003'),
(006, 'Thiago', 'Rivas Soler', 'T.R.Soler', 'Aprendiz', 'admin2002'),
(007, 'Elena', 'Castillo Mora', 'E.C.Mora', 'Oficial', 'admin1985'),
(008, 'Javier', 'Luna Paredes', 'J.L.Paredes', 'Aprendiz', 'admin2004'),
(009, 'Carmen', 'Reyes Flores', 'C.R.Flores', 'Maestro', 'admin1976'),
(010, 'Miguel', 'Serrano Gil', 'M.S.Gil', 'Oficial', 'admin1988'),
(011, 'Sofía', 'Cabrera Gómez', 'S.C.Gómez', 'Aprendiz', 'admin2005');

-- Citas
INSERT INTO Citas (id_cita, fecha, hora, duracion, id_cliente, id_encargado, id_taller, id_traje) VALUES 
(1, '2024-05-10', '13:00:00', '5 H', 001, 001, 001, 001),
(2, '2026-05-11', '15:00:00', '4 H', 002, 002, 003, 002),
(3, '2026-05-12', '12:00:00', '3 H', 003, 001, 006, 003),
(4, '2026-05-13', '12:00:00', '3 H', 004, 003, 002, 004),
(5, '2026-05-14', '17:00:00', '1 H', 008, 002, 001, 008);

-- Asistencias
INSERT INTO Asistencia (id_empleado, id_cita) VALUES
-- Cita 1 (Spiderman, encargado Edna id=001)
(002, 1), (004, 1),
-- Cita 2 (Batman, encargado Valerio id=002)
(003, 2), (005, 2),
-- Cita 3 (Superman, encargado Edna id=001)
(007, 3), (009, 3),
-- Cita 4 (IronMan, encargado Alessandro id=003)
(008, 4), (010, 4),
-- Cita 5 (ElasticGirl, encargado Valerio id=002)
(006, 5), (011, 5);
