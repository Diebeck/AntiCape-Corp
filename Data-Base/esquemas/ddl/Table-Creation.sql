CREATE TABLE `Cliente` (
  `id_cliente` integer PRIMARY KEY,
  `nombre` varchar(255),
  `colores` varchar(255),
  `superpoder` varchar(255)
);

CREATE TABLE `Traje` (
  `id_traje` integer PRIMARY KEY,
  `id_cliente` integer,
  `nombre` varchar(255),
  `estado` varchar(255)
);

CREATE TABLE `Taller` (
  `id_taller` integer PRIMARY KEY,
  `tipo_sala` varchar(255),
  `nombre_sala` varchar(255)
);

CREATE TABLE `Empleado` (
  `id_empleado` integer PRIMARY KEY,
  `nombre` varchar(255),
  `apellidos` varchar(255),
  `apodo` varchar(255),
  `categoria` varchar(255),
  `contraseña` varchar(255)
);

CREATE TABLE `Citas` (
  `id_cita` integer PRIMARY KEY,
  `fecha` date,
  `duracion` timestamp,
  `id_cliente` integer,
  `id_encargado` integer,
  `id_taller` integer,
  `id_traje` integer
);

CREATE TABLE `asistencia` (
  `id_empleado` integer,
  `id_cita` integer
);

ALTER TABLE `Traje` ADD FOREIGN KEY (`id_cliente`) REFERENCES `Cliente` (`id_cliente`);

ALTER TABLE `Citas` ADD FOREIGN KEY (`id_cliente`) REFERENCES `Cliente` (`id_cliente`);

ALTER TABLE `Citas` ADD FOREIGN KEY (`id_encargado`) REFERENCES `Empleado` (`id_empleado`);

ALTER TABLE `Citas` ADD FOREIGN KEY (`id_taller`) REFERENCES `Taller` (`id_taller`);

ALTER TABLE `Citas` ADD FOREIGN KEY (`id_traje`) REFERENCES `Traje` (`id_traje`);

ALTER TABLE `asistencia` ADD FOREIGN KEY (`id_empleado`) REFERENCES `Empleado` (`id_empleado`);

ALTER TABLE `asistencia` ADD FOREIGN KEY (`id_cita`) REFERENCES `Citas` (`id_cita`);
